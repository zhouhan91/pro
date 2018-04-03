package com.wemeCity.web.news.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.web.news.dto.NewsCommentDTO;
import com.wemeCity.web.news.exception.NewsCommentException;
import com.wemeCity.web.news.mapper.NewsCommentMapper;
import com.wemeCity.web.news.model.News;
import com.wemeCity.web.news.model.NewsComment;
import com.wemeCity.web.news.service.NewsCommentService;
import com.wemeCity.web.news.service.NewsService;
import com.wemeCity.web.user.model.User;

/**
 * NewsCommentServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
@Service
public class NewsCommentServiceImpl implements NewsCommentService {

	private Logger logger = LoggerFactory.getLogger(NewsCommentServiceImpl.class);

	@Autowired
	private NewsService newsService;

	/** 数据访问接口 */
	@Autowired
	private NewsCommentMapper newsCommentMapper;

	/**
	 * 新增newsComment
	 *
	 * @param newsComment
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public void insertNewsComment(NewsComment newsComment) throws NewsCommentException {
		try {
			newsCommentMapper.insertNewsComment(newsComment);
		} catch (Exception e) {
			logger.error("新增NewsComment时报错", e);
			throw new NewsCommentException("新增NewsComment时报错", e);
		}
	}

	/**
	 * 删除newsComment
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int deleteNewsComment(long id) throws NewsCommentException {
		try {
			return this.newsCommentMapper.deleteNewsComment(id);
		} catch (Exception e) {
			logger.error("删除NewsComment时报错", e);
			throw new NewsCommentException("删除NewsComment时报错", e);
		}
	}

	/**
	 * 修改newsComment
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int updateNewsComment(Map<String, Object> condition) throws NewsCommentException {
		try {
			return this.newsCommentMapper.updateNewsComment(condition);
		} catch (Exception e) {
			logger.error("修改NewsComment时报错", e);
			throw new NewsCommentException("修改NewsComment时报错", e);
		}
	}

	/**
	 * 查询newsComment
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public NewsComment readNewsComment(long id) throws NewsCommentException {
		try {
			return this.newsCommentMapper.readNewsComment(id);
		} catch (Exception e) {
			logger.error("查询NewsComment时报错", e);
			throw new NewsCommentException("查询NewsComment时报错", e);
		}
	}

	/**
	 * 查询newsComment集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public List<NewsComment> queryNewsCommentList(Map<String, Object> condition) {
		try {
			return this.newsCommentMapper.queryNewsCommentList(condition);
		} catch (Exception e) {
			logger.error("查询NewsComment时报错", e);
			return null;
		}
	}

	/**
	 * 查询newsComment集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public long queryNewsCommentCount(Map<String, Object> condition) throws NewsCommentException {
		try {
			return this.newsCommentMapper.queryNewsCommentCount(condition);
		} catch (Exception e) {
			logger.error("查询NewsComment时报错", e);
			throw new NewsCommentException("查询NewsComment时报错", e);
		}
	}

	@Override
	public NewsComment publish(News news, User user, NewsCommentDTO commentDTO) throws NewsCommentException {
		try {
			NewsComment comment = new NewsComment();
			comment.setUserId(user.getId());
			comment.setNickName(user.getNickName());
			comment.setNewsId(news.getId());
			comment.setTitle(news.getTitle());
			comment.setCoverPicture(news.getCoverPicture());
			comment.setAuthor(news.getAuthor());
			comment.setNavigationCode(news.getNavigationCode());
			comment.setNavigationName(news.getNavigationName());
			comment.setSubNavigationCode(news.getSubNavigationCode());
			comment.setSubNavigationName(news.getSubNavigationName());
			comment.setKeyWords(news.getKeyWords());
			comment.setContent(commentDTO.getContent());
			comment.setParentId(commentDTO.getParentId());
			comment.setReplyCount(0);
			comment.setLikeCount(0);
			comment.setIsDeleted(Constants.NO);
			comment.setCreateBy(user.getId());
			comment.setCreateTime(LocalDateTime.now());
			newsCommentMapper.insertNewsComment(comment);
			// 如果父id大于0，则把父id回复数+1
			if (commentDTO.getParentId() > 0) {
				NewsComment parent = newsCommentMapper.readNewsComment(commentDTO.getParentId());
				if (parent != null) {
					Map<String, Object> condition = new HashMap<>();
					condition.put("id", parent.getId());
					condition.put("replyCount", parent.getReplyCount() + 1);
					condition.put("modifyBy", user.getId());
					condition.put("modifyTime", LocalDateTime.now());
					newsCommentMapper.updateNewsComment(condition);
				}
			}
			// 新闻评论数+1
			Map<String, Object> condition = new HashMap<>();
			condition.put("id", news.getId());
			condition.put("commentCount", news.getCommentCount() + 1);
			condition.put("modifyBy", user.getId());
			condition.put("modifyTime", LocalDateTime.now());
			newsService.updateNews(condition);
			return comment;
		} catch (Exception e) {
			logger.error("发表新闻评论失败：服务器内部错误！news={}\r\n user={}\r\n commentDTO={}\r\n e={}", new Object[] { GsonUtils.toSimpleJson(news), GsonUtils.toSimpleJson(user), GsonUtils.toSimpleJson(commentDTO) }, e);
			throw new NewsCommentException("查询NewsComment时报错", e);
		}
	}
}