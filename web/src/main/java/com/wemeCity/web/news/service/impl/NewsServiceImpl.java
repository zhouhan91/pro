
package com.wemeCity.web.news.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.news.mapper.NewsMapper;
import com.wemeCity.web.news.model.News;
import com.wemeCity.web.news.exception.NewsException;
import com.wemeCity.web.news.service.NewsService;
/**
 * NewsServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
@Service
public class NewsServiceImpl implements NewsService{

	private Logger logger=LoggerFactory.getLogger(NewsServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private NewsMapper newsMapper;

	/**
	 * 新增news
	 *
	 * @param news
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public void insertNews(News news) throws NewsException{
		try{
			newsMapper.insertNews(news);
		}catch(Exception e){
			logger.error("新增News时报错", e);
			throw new NewsException("新增News时报错", e);
		}
	}

	/**
	 * 删除news
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int deleteNews(long id) throws NewsException{
		try{
			return this.newsMapper.deleteNews(id);
		}catch(Exception e){
			logger.error("删除News时报错", e);
			throw new NewsException("删除News时报错", e);
		}
	}

	/**
	 * 修改news
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int updateNews(Map<String, Object> condition) throws NewsException{
		try{
			return this.newsMapper.updateNews(condition);
		}catch(Exception e){
			logger.error("修改News时报错", e);
			throw new NewsException("修改News时报错", e);
		}
	}

	/**
	 * 查询news
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public News readNews(long id) throws NewsException{
		try{
			return this.newsMapper.readNews(id);
		}catch(Exception e){
			logger.error("查询News时报错", e);
			throw new NewsException("查询News时报错", e);
		}
	}

	/**
	 * 查询news集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public List<News> queryNewsList(Map<String, Object> condition){
		try{
			return this.newsMapper.queryNewsList(condition);
		}catch(Exception e){
			logger.error("查询News时报错", e);
			return null;
		}
	}

	/**
	 * 查询news集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public long queryNewsCount(Map<String, Object> condition) throws NewsException{
		try{
			return this.newsMapper.queryNewsCount(condition);
		}catch(Exception e){
			logger.error("查询News时报错", e);
			throw new NewsException("查询News时报错", e);
		}
	}

}