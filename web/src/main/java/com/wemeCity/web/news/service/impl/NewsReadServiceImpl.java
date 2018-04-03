
package com.wemeCity.web.news.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.news.mapper.NewsReadMapper;
import com.wemeCity.web.news.model.NewsRead;
import com.wemeCity.web.news.exception.NewsReadException;
import com.wemeCity.web.news.service.NewsReadService;
/**
 * NewsReadServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
@Service
public class NewsReadServiceImpl implements NewsReadService{

	private Logger logger=LoggerFactory.getLogger(NewsReadServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private NewsReadMapper newsReadMapper;

	/**
	 * 新增newsRead
	 *
	 * @param newsRead
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public void insertNewsRead(NewsRead newsRead) throws NewsReadException{
		try{
			newsReadMapper.insertNewsRead(newsRead);
		}catch(Exception e){
			logger.error("新增NewsRead时报错", e);
			throw new NewsReadException("新增NewsRead时报错", e);
		}
	}

	/**
	 * 删除newsRead
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int deleteNewsRead(long id) throws NewsReadException{
		try{
			return this.newsReadMapper.deleteNewsRead(id);
		}catch(Exception e){
			logger.error("删除NewsRead时报错", e);
			throw new NewsReadException("删除NewsRead时报错", e);
		}
	}

	/**
	 * 修改newsRead
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public int updateNewsRead(Map<String, Object> condition) throws NewsReadException{
		try{
			return this.newsReadMapper.updateNewsRead(condition);
		}catch(Exception e){
			logger.error("修改NewsRead时报错", e);
			throw new NewsReadException("修改NewsRead时报错", e);
		}
	}

	/**
	 * 查询newsRead
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public NewsRead readNewsRead(long id) throws NewsReadException{
		try{
			return this.newsReadMapper.readNewsRead(id);
		}catch(Exception e){
			logger.error("查询NewsRead时报错", e);
			throw new NewsReadException("查询NewsRead时报错", e);
		}
	}

	/**
	 * 查询newsRead集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public List<NewsRead> queryNewsReadList(Map<String, Object> condition){
		try{
			return this.newsReadMapper.queryNewsReadList(condition);
		}catch(Exception e){
			logger.error("查询NewsRead时报错", e);
			return null;
		}
	}

	/**
	 * 查询newsRead集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	@Override
	public long queryNewsReadCount(Map<String, Object> condition) throws NewsReadException{
		try{
			return this.newsReadMapper.queryNewsReadCount(condition);
		}catch(Exception e){
			logger.error("查询NewsRead时报错", e);
			throw new NewsReadException("查询NewsRead时报错", e);
		}
	}

}