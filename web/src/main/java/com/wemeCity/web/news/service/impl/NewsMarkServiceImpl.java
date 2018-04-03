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
import com.wemeCity.web.news.exception.NewsMarkException;
import com.wemeCity.web.news.mapper.NewsMarkMapper;
import com.wemeCity.web.news.model.News;
import com.wemeCity.web.news.model.NewsMark;
import com.wemeCity.web.news.service.NewsMarkService;
import com.wemeCity.web.news.service.NewsService;
import com.wemeCity.web.user.model.UserSession;

/**
 * NewsMarkServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
@Service
public class NewsMarkServiceImpl implements NewsMarkService {

	private Logger logger = LoggerFactory.getLogger(NewsMarkServiceImpl.class);
	
	@Autowired
	private NewsService newsService;

	/** 数据访问接口 */
	@Autowired
	private NewsMarkMapper newsMarkMapper;

	/**
	 * 新增newsMark
	 *
	 * @param newsMark
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public void insertNewsMark(NewsMark newsMark) throws NewsMarkException {
		try {
			newsMarkMapper.insertNewsMark(newsMark);
		} catch (Exception e) {
			logger.error("新增NewsMark时报错", e);
			throw new NewsMarkException("新增NewsMark时报错", e);
		}
	}

	/**
	 * 删除newsMark
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int deleteNewsMark(long id) throws NewsMarkException {
		try {
			return this.newsMarkMapper.deleteNewsMark(id);
		} catch (Exception e) {
			logger.error("删除NewsMark时报错", e);
			throw new NewsMarkException("删除NewsMark时报错", e);
		}
	}

	/**
	 * 修改newsMark
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int updateNewsMark(Map<String, Object> condition) throws NewsMarkException {
		try {
			return this.newsMarkMapper.updateNewsMark(condition);
		} catch (Exception e) {
			logger.error("修改NewsMark时报错", e);
			throw new NewsMarkException("修改NewsMark时报错", e);
		}
	}

	/**
	 * 查询newsMark
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public NewsMark readNewsMark(long id) throws NewsMarkException {
		try {
			return this.newsMarkMapper.readNewsMark(id);
		} catch (Exception e) {
			logger.error("查询NewsMark时报错", e);
			throw new NewsMarkException("查询NewsMark时报错", e);
		}
	}

	/**
	 * 查询newsMark集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public List<NewsMark> queryNewsMarkList(Map<String, Object> condition) {
		try {
			return this.newsMarkMapper.queryNewsMarkList(condition);
		} catch (Exception e) {
			logger.error("查询NewsMark时报错", e);
			return null;
		}
	}

	/**
	 * 查询newsMark集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public long queryNewsMarkCount(Map<String, Object> condition) throws NewsMarkException {
		try {
			return this.newsMarkMapper.queryNewsMarkCount(condition);
		} catch (Exception e) {
			logger.error("查询NewsMark时报错", e);
			throw new NewsMarkException("查询NewsMark时报错", e);
		}
	}

	@Override
	public NewsMark mark(UserSession userSession, News news) throws NewsMarkException {
		try {
			NewsMark newsMark = new NewsMark();
			newsMark.setUserId(userSession.getUserId());
			newsMark.setNewsId(news.getId());
			newsMark.setTitle(news.getTitle());
			newsMark.setCoverPicture(news.getCoverPicture());
			newsMark.setAuthor(news.getAuthor());
			newsMark.setNavigationCode(news.getNavigationCode());
			newsMark.setNavigationName(news.getNavigationName());
			newsMark.setSubNavigationCode(news.getSubNavigationCode());
			newsMark.setSubNavigationName(news.getSubNavigationName());
			newsMark.setKeyWords(news.getKeyWords());
			newsMark.setIsDeleted(Constants.NO);
			newsMark.setCreateBy(userSession.getUserId());
			newsMark.setCreateTime(LocalDateTime.now());
			newsMarkMapper.insertNewsMark(newsMark);
			// 新闻收藏数+1
			Map<String, Object> condition = new HashMap<>();
			condition.put("id", news.getId());
			condition.put("likeCount", news.getLikeCount() + 1);
			condition.put("modifyBy", userSession.getUserId());
			condition.put("modifyTime", LocalDateTime.now());
			newsService.updateNews(condition);
			return newsMark;
		} catch (Exception e) {
			logger.error("收藏新闻失败：服务器内部错误！userSession={}\r\n news={}\r\n e={}", new Object[] { GsonUtils.toSimpleJson(userSession), GsonUtils.toSimpleJson(news) }, e);
			throw new NewsMarkException("收藏新闻失败：服务器内部错误", e);
		}
	}
	
	@Override
	public int cancelMark(UserSession userSession, long newsId) throws NewsMarkException {
		try {
			Map<String, Object> condition=new HashMap<>();
			condition.put("userId", userSession.getUserId());
			condition.put("newsId", newsId);
			condition.put("modifyBy", userSession.getUserId());
			condition.put("modifyTime", LocalDateTime.now());
			return newsMarkMapper.cancelMark(condition);
		} catch (Exception e) {
			logger.error("取消收藏新闻失败：服务器内部错误！userSession={}\r\n newsId={}\r\n e={}", new Object[] { GsonUtils.toSimpleJson(userSession), newsId }, e);
			throw new NewsMarkException("取消收藏新闻失败：服务器内部错误", e);
		}
	}
}