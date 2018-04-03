
package com.wemeCity.web.news.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.news.mapper.NewsSubNavigationMapper;
import com.wemeCity.web.news.model.NewsSubNavigation;
import com.wemeCity.web.news.exception.NewsSubNavigationException;
import com.wemeCity.web.news.service.NewsSubNavigationService;
/**
 * NewsSubNavigationServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-19 新建
 */
@Service
public class NewsSubNavigationServiceImpl implements NewsSubNavigationService{

	private Logger logger=LoggerFactory.getLogger(NewsSubNavigationServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private NewsSubNavigationMapper newsSubNavigationMapper;

	/**
	 * 新增newsSubNavigation
	 *
	 * @param newsSubNavigation
	 * @return 新增的对象
	 * @author 向小文 2017-10-19 新建
	 */
	@Override
	public void insertNewsSubNavigation(NewsSubNavigation newsSubNavigation) throws NewsSubNavigationException{
		try{
			newsSubNavigationMapper.insertNewsSubNavigation(newsSubNavigation);
		}catch(Exception e){
			logger.error("新增NewsSubNavigation时报错", e);
			throw new NewsSubNavigationException("新增NewsSubNavigation时报错", e);
		}
	}

	/**
	 * 删除newsSubNavigation
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-19 新建
	 */
	@Override
	public int deleteNewsSubNavigation(long id) throws NewsSubNavigationException{
		try{
			return this.newsSubNavigationMapper.deleteNewsSubNavigation(id);
		}catch(Exception e){
			logger.error("删除NewsSubNavigation时报错", e);
			throw new NewsSubNavigationException("删除NewsSubNavigation时报错", e);
		}
	}

	/**
	 * 修改newsSubNavigation
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-19 新建
	 */
	@Override
	public int updateNewsSubNavigation(Map<String, Object> condition) throws NewsSubNavigationException{
		try{
			return this.newsSubNavigationMapper.updateNewsSubNavigation(condition);
		}catch(Exception e){
			logger.error("修改NewsSubNavigation时报错", e);
			throw new NewsSubNavigationException("修改NewsSubNavigation时报错", e);
		}
	}

	/**
	 * 查询newsSubNavigation
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-19 新建
	 */
	@Override
	public NewsSubNavigation readNewsSubNavigation(long id) throws NewsSubNavigationException{
		try{
			return this.newsSubNavigationMapper.readNewsSubNavigation(id);
		}catch(Exception e){
			logger.error("查询NewsSubNavigation时报错", e);
			throw new NewsSubNavigationException("查询NewsSubNavigation时报错", e);
		}
	}

	/**
	 * 查询newsSubNavigation集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-19 新建
	 */
	@Override
	public List<NewsSubNavigation> queryNewsSubNavigationList(Map<String, Object> condition){
		try{
			return this.newsSubNavigationMapper.queryNewsSubNavigationList(condition);
		}catch(Exception e){
			logger.error("查询NewsSubNavigation时报错", e);
			return null;
		}
	}

	/**
	 * 查询newsSubNavigation集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-19 新建
	 */
	@Override
	public long queryNewsSubNavigationCount(Map<String, Object> condition) throws NewsSubNavigationException{
		try{
			return this.newsSubNavigationMapper.queryNewsSubNavigationCount(condition);
		}catch(Exception e){
			logger.error("查询NewsSubNavigation时报错", e);
			throw new NewsSubNavigationException("查询NewsSubNavigation时报错", e);
		}
	}

}