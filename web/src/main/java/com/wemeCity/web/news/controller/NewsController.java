package com.wemeCity.web.news.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.dto.PageDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.EHCacheUtils;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.common.utils.YamlUtils;
import com.wemeCity.web.news.dto.NewsCommentDTO;
import com.wemeCity.web.news.model.Navigation;
import com.wemeCity.web.news.model.News;
import com.wemeCity.web.news.model.NewsComment;
import com.wemeCity.web.news.model.NewsContent;
import com.wemeCity.web.news.model.NewsMark;
import com.wemeCity.web.news.model.NewsSubNavigation;
import com.wemeCity.web.news.service.NewsBabietaService;
import com.wemeCity.web.news.service.NewsCommentLikeService;
import com.wemeCity.web.news.service.NewsCommentService;
import com.wemeCity.web.news.service.NewsContentService;
import com.wemeCity.web.news.service.NewsLikeService;
import com.wemeCity.web.news.service.NewsMarkService;
import com.wemeCity.web.news.service.NewsService;
import com.wemeCity.web.news.service.NewsSubNavigationService;
import com.wemeCity.web.user.model.User;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.service.UserService;
import com.wemeCity.web.user.service.UserSessionService;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

	@Autowired
	private YamlUtils yamlUtils;

	@Autowired
	private NewsSubNavigationService newsSubNavigationService;

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsContentService newsContentService;

	@Autowired
	private NewsCommentService newsCommentService;

	@Autowired
	private UserSessionService userSessionService;

	@Autowired
	private NewsMarkService newsMarkService;

	@Autowired
	private NewsLikeService newsLikeService;

	@Autowired
	private NewsCommentLikeService newsCommentLikeService;

	@Autowired
	private UserService userService;

	@Autowired
	private NewsBabietaService newsBabietaService;

	/**
	 * 获取导航栏
	 *
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	@ResponseBody
	@GetMapping("/getNavigations")
	@SuppressWarnings("unchecked")
	public BaseDTO queryNavigationList() throws Exception {
		List<Navigation> lstNavigation = (List<Navigation>) yamlUtils.getObject("system.news.navigation", List.class);
		return new BaseDTO(RequestResultEnum.SUCCESS, lstNavigation);
	}

	/**
	 * 根据导航栏获取子栏目
	 *
	 * @param parentCode
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	@ResponseBody
	@GetMapping("/querySubNavigationList/{navigationCode}")
	public BaseDTO querySubNavigationList(@PathVariable String navigationCode) throws Exception {
		// 优先缓存读取
		Object subNavigationListInCache = EHCacheUtils.get("subNavigation", navigationCode);
		if (subNavigationListInCache != null) {
			return new BaseDTO(RequestResultEnum.SUCCESS, subNavigationListInCache);
		}
		// 数据库查询
		Map<String, Object> condition = new HashMap<>();
		condition.put("parentCode", navigationCode);
		condition.put("isDeleted", Constants.NO);
		condition.put("sortColumn", "sort_num");
		condition.put("sortType", "asc");
		List<NewsSubNavigation> lstSubNavigation = newsSubNavigationService.queryNewsSubNavigationList(condition);
		EHCacheUtils.put("subNavigation", navigationCode, lstSubNavigation);
		return new BaseDTO(RequestResultEnum.SUCCESS, lstSubNavigation);
	}

	/**
	 * 获取首页新闻信息返回3条
	 *
	 * @param subNavigationCode
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	@ResponseBody
	@GetMapping("/queryIndexList/{subNavigationCode}")
	public BaseDTO queryIndexList(@PathVariable String subNavigationCode) throws Exception {
		// 缓存中没有就查一遍
		Map<String, Object> condition = new HashMap<>();
		condition.put("subNavigationCode", subNavigationCode);
		condition.put("isDeleted", Constants.NO);
		condition.put("sortColumn", "id");
		condition.put("sortType", "desc");
		Page<News> page = PageHelper.startPage(1, 10).doSelectPage(() -> newsService.queryNewsList(condition));
		EHCacheUtils.put("navigationNews", "subNavigationCode", page);
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}

	/**
	 * 获取新闻列表
	 *
	 * @param subNavigationCode
	 * @param pageNum
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	@ResponseBody
	@GetMapping("/queryNewsList/{subNavigationCode}/{pageNum}")
	public BaseDTO queryNewsList(@PathVariable String subNavigationCode, @PathVariable int pageNum) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("isDeleted", Constants.NO);
		condition.put("subNavigationCode", subNavigationCode);
		condition.put("sortColumn", "id");
		condition.put("sortType", "desc");
		Page<News> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> newsService.queryNewsList(condition));
		if ("babieta".equals(subNavigationCode)) {
			for (News news : page) {
				news.setContent(newsContentService.readNewsContentByNewsId(news.getId()));
			}
		}
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	/**
	 * 读取新闻详情
	 *
	 * @param newsId 新闻id
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	@ResponseBody
	@GetMapping("/readNewsInfo/{newsId}")
	public BaseDTO readNewsInfo(@PathVariable long newsId) throws Exception {
		Object newsInfoInCache = EHCacheUtils.get("newsInfo", newsId + "");
		if (newsInfoInCache != null) {
			logger.debug("缓存中读取新闻信息成功，newsId={}", newsId);
			return new BaseDTO(RequestResultEnum.SUCCESS, newsInfoInCache);
		}
		News news = newsService.readNews(newsId);
		if (news == null) {
			logger.warn("读取新闻详情失败：新闻信息未找到！newsId={}", newsId);
			return new BaseDTO(RequestResultEnum.NEWS_NOT_FOUND, null);
		}
		// 读取新闻内容
		NewsContent content = newsContentService.readNewsContentByNewsId(newsId);
		news.setContent(content);
		EHCacheUtils.put("newsInfo", newsId + "", news);
		return new BaseDTO(RequestResultEnum.SUCCESS, news);
	}

	/**
	 * 新闻详情读取评论概要信息
	 *
	 * @param newsId
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	@ResponseBody
	@GetMapping("/readNewsCommentOnDetail/{newsId}")
	public BaseDTO readNewsCommentOnDetail(@PathVariable long newsId) throws Exception {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> condition = new HashMap<>();
		// 获取评论数
		condition.put("newsId", newsId);
		condition.put("isDeleted", Constants.NO);
		long count = newsCommentService.queryNewsCommentCount(condition);
		result.put("count", count);
		// 获取最新3条评论信息
		if (count > 0) {
			condition.put("sortColumn", "id");
			condition.put("sortType", "desc");
			Page<NewsComment> page = PageHelper.startPage(1, 3).doSelectPage(() -> newsCommentService.queryNewsCommentList(condition));
			result.put("lstNewsComment", page);
		} else {
			result.put("lstNewsComment", new ArrayList<NewsComment>());
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, result);
	}

	/**
	 * 查询用户是否对文章收藏和点赞过
	 *
	 * @param newsId 新闻id
	 * @param userKey 用户信息
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	@ResponseBody
	@GetMapping("/getUserMarkNewsInfo/{newsId}/{userKey}")
	public BaseDTO getUserMarkNewsInfo(@PathVariable long newsId, @PathVariable String userKey) throws Exception {
		Map<String, Object> result = new HashMap<>();
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("查询用户收藏新闻失败：用户信息未找到或登录已失效! userKey={}, newsId={}", userKey, newsId);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("userId", userSession.getUserId());
		condition.put("newsId", newsId);
		condition.put("isDeleted", Constants.NO);
		// 查询是否已过赞
		long markCount = newsMarkService.queryNewsMarkCount(condition);
		result.put("marked", markCount > 0 ? Constants.YES : Constants.NO);
		// 查询是否已收藏
		long likeCount = newsLikeService.queryNewsLikeCount(condition);
		result.put("liked", likeCount > 0 ? Constants.YES : Constants.NO);
		return new BaseDTO(RequestResultEnum.SUCCESS, result);
	}

	/**
	 * 收藏新闻
	 *
	 * @param newsId 新闻id
	 * @param userKey 用户标识
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	@ResponseBody
	@PostMapping("/mark/{newsId}/{userKey}")
	public BaseDTO mark(@PathVariable long newsId, @PathVariable String userKey) throws Exception {
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("收藏新闻失败：用户信息未找到或登录已失效! userKey={}, newsId={}", userKey, newsId);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		News news = newsService.readNews(newsId);
		if (news == null) {
			logger.warn("收藏新闻失败：新闻信息未找到！newsId={}", newsId);
			return new BaseDTO(RequestResultEnum.NEWS_NOT_FOUND, null);
		}
		newsMarkService.mark(userSession, news);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	/**
	 * 取消收藏新闻
	 *
	 * @param newsId 新闻id
	 * @param userKey 用户标识
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	@ResponseBody
	@PostMapping("/cancelMark/{newsId}/{userKey}")
	public BaseDTO cancelMark(@PathVariable long newsId, @PathVariable String userKey) throws Exception {
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("收藏新闻失败：用户信息未找到或登录已失效! userKey={}, newsId={}", userKey, newsId);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		newsMarkService.cancelMark(userSession, newsId);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	/**
	 * 点赞新闻
	 *
	 * @param newsId 新闻id
	 * @param userKey 用户标识
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	@ResponseBody
	@PostMapping("/like/{newsId}/{userKey}")
	public BaseDTO like(@PathVariable long newsId, @PathVariable String userKey) throws Exception {
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("点赞新闻失败：用户信息未找到或登录已失效! userKey={}, newsId={}", userKey, newsId);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		News news = newsService.readNews(newsId);
		if (news == null) {
			logger.warn("收藏新闻失败：新闻信息未找到！newsId={}", newsId);
			return new BaseDTO(RequestResultEnum.NEWS_NOT_FOUND, null);
		}
		newsLikeService.like(userSession, news);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	/**
	 * 获取延伸阅读
	 *
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月20日 新建
	 */
	@ResponseBody
	@GetMapping("/queryExtendedNewsList")
	public BaseDTO queryExtendedNewsList() throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("notNavigationCode", "babieta");
		condition.put("isDeleted", Constants.NO);
		condition.put("sortColumn", "id");
		condition.put("sortType", "desc");
		Page<News> page = PageHelper.startPage(1, 3).doSelectPage(() -> newsService.queryNewsList(condition));
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	/**
	 * 获取评论列表
	 *
	 * @param newsId 新闻id
	 * @param pageNum 页码
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月20日 新建
	 */
	@ResponseBody
	@GetMapping("/queryNewsCommentList/{newsId}/{pageNum}")
	public BaseDTO queryNewsCommentList(@PathVariable long newsId, @PathVariable int pageNum) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("isDeleted", Constants.NO);
		condition.put("parentId", 0);
		condition.put("newsId", newsId);
		condition.put("sortColumn", "news_comment.id");
		condition.put("sortType", "desc");
		Page<NewsComment> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> newsCommentService.queryNewsCommentList(condition));
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	@ResponseBody
	@GetMapping("/querySubNewsCommentList/{commentId}")
	public BaseDTO querySubNewsCommentList(@PathVariable long commentId) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("parentId", commentId);
		condition.put("isDeleted", Constants.NO);
		Page<NewsComment> page = PageHelper.startPage(1, 100).doSelectPage(() -> newsCommentService.queryNewsCommentList(condition));
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	/**
	 * 点赞新闻评论
	 *
	 * @param userKey
	 * @param newsCommentId
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月20日 新建
	 */
	@ResponseBody
	@PostMapping("/likeComment/{userKey}/{newsCommentId}")
	public BaseDTO likeComment(@PathVariable String userKey, @PathVariable long newsCommentId) throws Exception {
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("点赞新闻评论失败：用户信息未找到或登录已失效! userKey={}, newsCommentId={}", userKey, newsCommentId);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		newsCommentLikeService.like(newsCommentId, userSession.getUserId());
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	/**
	 * 发表评论
	 *
	 * @param commentDTO
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年10月20日 新建
	 */
	@ResponseBody
	@PostMapping("/publishComment")
	public BaseDTO publishComment(@RequestBody NewsCommentDTO commentDTO) throws Exception {
		if (commentDTO == null || commentDTO.getNewsId() <= 0 || StringUtils.isEmpty(commentDTO.getUserKey()) || StringUtils.isEmpty(commentDTO.getContent())) {
			logger.warn("发表评论失败：必填参数校验失败！commentDTO={}", GsonUtils.toSimpleJson(commentDTO));
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
		}
		if (commentDTO.getContent().length() > 500) {
			logger.warn("发表评论失败：评论内容超长！commentDTO={}", GsonUtils.toSimpleJson(commentDTO));
			return new BaseDTO(RequestResultEnum.NEWS_COMMENT_TOO_LONG, null);
		}
		UserSession userSession = userSessionService.readUserSessionByUserKey(commentDTO.getUserKey());
		if (userSession == null) {
			logger.warn("发表评论失败：用户信息未找到或登录已失效! commentDTO={}", GsonUtils.toSimpleJson(commentDTO));
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		News news = newsService.readNews(commentDTO.getNewsId());
		if (news == null) {
			logger.warn("发表评论失败：新闻信息未找到！ commentDTO={}", GsonUtils.toSimpleJson(commentDTO));
			return new BaseDTO(RequestResultEnum.NEWS_NOT_FOUND, null);
		}
		User user = userService.readUser(userSession.getUserId());
		newsCommentService.publish(news, user, commentDTO);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	/**
	 * 获取用户收藏的新闻
	 *
	 * @param userKey
	 * @param pageNum
	 * @return
	 * @throws Exception
	 * @history 2017年10月26日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryMarkedNews/{userKey}/{pageNum}")
	public BaseDTO queryMarkedNews(@PathVariable String userKey, @PathVariable int pageNum) throws Exception {
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("获取用户收藏新闻失败：用户信息未找到或登录已失效! userKey={}", userKey);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("userId", userSession.getUserId());
		condition.put("isDeleted", Constants.NO);
		Page<NewsMark> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> newsMarkService.queryNewsMarkList(condition));
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	@ResponseBody
	@GetMapping("/queryBabietaList/{pageNum}")
	public BaseDTO queryBabietaList(@PathVariable int pageNum) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("subNavigationCode", "babieta");
		condition.put("isDeleted", Constants.NO);
		condition.put("sortColumn", "id");
		condition.put("sortType", "desc");
		Page<News> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> newsService.queryNewsList(condition));
		if (!CollectionUtils.isEmpty(page)) {
			Map<String, Object> babietaCondition = new HashMap<>();
			babietaCondition.put("isDeleted", Constants.NO);
			for (News news : page) {
				babietaCondition.put("newsId", news.getId());
				news.setLstNewsBabieta(newsBabietaService.queryNewsBabietaList(babietaCondition));
			}
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}
}
