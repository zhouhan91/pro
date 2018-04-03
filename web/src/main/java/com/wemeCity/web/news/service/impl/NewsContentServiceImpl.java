package com.wemeCity.web.news.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.EHCacheUtils;
import com.wemeCity.web.news.exception.NewsContentException;
import com.wemeCity.web.news.mapper.NewsContentMapper;
import com.wemeCity.web.news.model.NewsContent;
import com.wemeCity.web.news.service.NewsContentService;

/**
 * NewsContentServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
@Service
public class NewsContentServiceImpl implements NewsContentService {

	private Logger logger = LoggerFactory.getLogger(NewsContentServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private NewsContentMapper newsContentMapper;

	/**
	 * 新增newsContent
	 *
	 * @param newsContent
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public void insertNewsContent(NewsContent newsContent) throws NewsContentException {
		try {
			newsContentMapper.insertNewsContent(newsContent);
		} catch (Exception e) {
			logger.error("新增NewsContent时报错", e);
			throw new NewsContentException("新增NewsContent时报错", e);
		}
	}

	/**
	 * 删除newsContent
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int deleteNewsContent(long id) throws NewsContentException {
		try {
			return this.newsContentMapper.deleteNewsContent(id);
		} catch (Exception e) {
			logger.error("删除NewsContent时报错", e);
			throw new NewsContentException("删除NewsContent时报错", e);
		}
	}

	/**
	 * 修改newsContent
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int updateNewsContent(Map<String, Object> condition) throws NewsContentException {
		try {
			return this.newsContentMapper.updateNewsContent(condition);
		} catch (Exception e) {
			logger.error("修改NewsContent时报错", e);
			throw new NewsContentException("修改NewsContent时报错", e);
		}
	}

	/**
	 * 查询newsContent
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public NewsContent readNewsContent(long id) throws NewsContentException {
		try {
			return this.newsContentMapper.readNewsContent(id);
		} catch (Exception e) {
			logger.error("查询NewsContent时报错", e);
			throw new NewsContentException("查询NewsContent时报错", e);
		}
	}

	/**
	 * 查询newsContent集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public List<NewsContent> queryNewsContentList(Map<String, Object> condition) {
		try {
			return this.newsContentMapper.queryNewsContentList(condition);
		} catch (Exception e) {
			logger.error("查询NewsContent时报错", e);
			return null;
		}
	}

	/**
	 * 查询newsContent集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public long queryNewsContentCount(Map<String, Object> condition) throws NewsContentException {
		try {
			return this.newsContentMapper.queryNewsContentCount(condition);
		} catch (Exception e) {
			logger.error("查询NewsContent时报错", e);
			throw new NewsContentException("查询NewsContent时报错", e);
		}
	}

	@Override
	public NewsContent readNewsContentByNewsId(long newsId) throws NewsContentException {
		if (newsId <= 0) {
			return null;
		}
		Object contentInCache = EHCacheUtils.get("newsContents", newsId + "");
		if (contentInCache != null) {
			logger.debug("缓存中读取新闻详情成功，newsId={}", newsId);
			return (NewsContent) contentInCache;
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("newsId", newsId);
		condition.put("isDeleted", Constants.NO);
		List<NewsContent> lstContent = newsContentMapper.queryNewsContentList(condition);
		if (CollectionUtils.isEmpty(lstContent)) {
			return null;
		}
		EHCacheUtils.put("newsContents", newsId + "", lstContent.get(0));
		return lstContent.get(0);
	}

}