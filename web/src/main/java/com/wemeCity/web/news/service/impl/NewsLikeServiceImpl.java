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
import com.wemeCity.web.news.exception.NewsLikeException;
import com.wemeCity.web.news.mapper.NewsLikeMapper;
import com.wemeCity.web.news.model.News;
import com.wemeCity.web.news.model.NewsLike;
import com.wemeCity.web.news.service.NewsLikeService;
import com.wemeCity.web.news.service.NewsService;
import com.wemeCity.web.user.model.UserSession;

/**
 * NewsLikeServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
@Service
public class NewsLikeServiceImpl implements NewsLikeService {

	private Logger logger = LoggerFactory.getLogger(NewsLikeServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private NewsLikeMapper newsLikeMapper;
	
	@Autowired
	private NewsService newsService;

	/**
	 * 新增newsLike
	 *
	 * @param newsLike
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public void insertNewsLike(NewsLike newsLike) throws NewsLikeException {
		try {
			newsLikeMapper.insertNewsLike(newsLike);
		} catch (Exception e) {
			logger.error("新增NewsLike时报错", e);
			throw new NewsLikeException("新增NewsLike时报错", e);
		}
	}

	/**
	 * 删除newsLike
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int deleteNewsLike(long id) throws NewsLikeException {
		try {
			return this.newsLikeMapper.deleteNewsLike(id);
		} catch (Exception e) {
			logger.error("删除NewsLike时报错", e);
			throw new NewsLikeException("删除NewsLike时报错", e);
		}
	}

	/**
	 * 修改newsLike
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int updateNewsLike(Map<String, Object> condition) throws NewsLikeException {
		try {
			return this.newsLikeMapper.updateNewsLike(condition);
		} catch (Exception e) {
			logger.error("修改NewsLike时报错", e);
			throw new NewsLikeException("修改NewsLike时报错", e);
		}
	}

	/**
	 * 查询newsLike
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public NewsLike readNewsLike(long id) throws NewsLikeException {
		try {
			return this.newsLikeMapper.readNewsLike(id);
		} catch (Exception e) {
			logger.error("查询NewsLike时报错", e);
			throw new NewsLikeException("查询NewsLike时报错", e);
		}
	}

	/**
	 * 查询newsLike集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public List<NewsLike> queryNewsLikeList(Map<String, Object> condition) {
		try {
			return this.newsLikeMapper.queryNewsLikeList(condition);
		} catch (Exception e) {
			logger.error("查询NewsLike时报错", e);
			return null;
		}
	}

	/**
	 * 查询newsLike集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public long queryNewsLikeCount(Map<String, Object> condition) throws NewsLikeException {
		try {
			return this.newsLikeMapper.queryNewsLikeCount(condition);
		} catch (Exception e) {
			logger.error("查询NewsLike时报错", e);
			throw new NewsLikeException("查询NewsLike时报错", e);
		}
	}

	@Override
	public NewsLike like(UserSession userSession, News news) throws NewsLikeException {
		try {
			NewsLike newsLike = new NewsLike();
			newsLike.setUserId(userSession.getUserId());
			newsLike.setNewsId(news.getId());
			newsLike.setTitle(news.getTitle());
			newsLike.setCoverPicture(news.getCoverPicture());
			newsLike.setAuthor(news.getAuthor());
			newsLike.setNavigationCode(news.getNavigationCode());
			newsLike.setNavigationName(news.getNavigationName());
			newsLike.setSubNavigationCode(news.getSubNavigationCode());
			newsLike.setSubNavigationName(news.getSubNavigationName());
			newsLike.setKeyWords(news.getKeyWords());
			newsLike.setIsDeleted(Constants.NO);
			newsLike.setCreateBy(userSession.getUserId());
			newsLike.setCreateTime(LocalDateTime.now());
			newsLikeMapper.insertNewsLike(newsLike);
			// 新闻点赞数+1
			Map<String, Object> condition = new HashMap<>();
			condition.put("id", news.getId());
			condition.put("likeCount", news.getLikeCount() + 1);
			condition.put("modifyBy", userSession.getUserId());
			condition.put("modifyTime", LocalDateTime.now());
			newsService.updateNews(condition);
			return newsLike;
		} catch (Exception e) {
			logger.error("点赞新闻失败：服务器内部错误！userSession={}\r\n news={}\r\n e={}", new Object[] { GsonUtils.toSimpleJson(userSession), GsonUtils.toSimpleJson(news) }, e);
			throw new NewsLikeException("点赞新闻失败：服务器内部错误！", e);
		}
	}
}