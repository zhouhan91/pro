
package com.wemeCity.components.search.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.components.search.mapper.PopularWordsMapper;
import com.wemeCity.components.search.model.PopularWords;
import com.wemeCity.components.search.exception.PopularWordsException;
import com.wemeCity.components.search.service.PopularWordsService;
/**
 * PopularWordsServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-16 新建
 */
@Service
public class PopularWordsServiceImpl implements PopularWordsService{

	private Logger logger=LoggerFactory.getLogger(PopularWordsServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private PopularWordsMapper popularWordsMapper;

	/**
	 * 新增popularWords
	 *
	 * @param popularWords
	 * @return 新增的对象
	 * @author 向小文 2017-9-16 新建
	 */
	@Override
	public void insertPopularWords(PopularWords popularWords) throws PopularWordsException{
		try{
			popularWordsMapper.insertPopularWords(popularWords);
		}catch(Exception e){
			logger.error("新增PopularWords时报错", e);
			throw new PopularWordsException("新增PopularWords时报错", e);
		}
	}

	/**
	 * 删除popularWords
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-16 新建
	 */
	@Override
	public int deletePopularWords(long id) throws PopularWordsException{
		try{
			return this.popularWordsMapper.deletePopularWords(id);
		}catch(Exception e){
			logger.error("删除PopularWords时报错", e);
			throw new PopularWordsException("删除PopularWords时报错", e);
		}
	}

	/**
	 * 修改popularWords
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-16 新建
	 */
	@Override
	public int updatePopularWords(Map<String, Object> condition) throws PopularWordsException{
		try{
			return this.popularWordsMapper.updatePopularWords(condition);
		}catch(Exception e){
			logger.error("修改PopularWords时报错", e);
			throw new PopularWordsException("修改PopularWords时报错", e);
		}
	}

	/**
	 * 查询popularWords
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-16 新建
	 */
	@Override
	public PopularWords readPopularWords(long id) throws PopularWordsException{
		try{
			return this.popularWordsMapper.readPopularWords(id);
		}catch(Exception e){
			logger.error("查询PopularWords时报错", e);
			throw new PopularWordsException("查询PopularWords时报错", e);
		}
	}

	/**
	 * 查询popularWords集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-16 新建
	 */
	@Override
	public List<PopularWords> queryPopularWordsList(Map<String, Object> condition){
		try{
			return this.popularWordsMapper.queryPopularWordsList(condition);
		}catch(Exception e){
			logger.error("查询PopularWords时报错", e);
			return null;
		}
	}

	/**
	 * 查询popularWords集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-16 新建
	 */
	@Override
	public long queryPopularWordsCount(Map<String, Object> condition) throws PopularWordsException{
		try{
			return this.popularWordsMapper.queryPopularWordsCount(condition);
		}catch(Exception e){
			logger.error("查询PopularWords时报错", e);
			throw new PopularWordsException("查询PopularWords时报错", e);
		}
	}

}