
package com.wemeCity.web.community.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.community.mapper.CityCommunityMapper;
import com.wemeCity.web.community.model.CityCommunity;
import com.wemeCity.web.community.exception.CityCommunityException;
import com.wemeCity.web.community.service.CityCommunityService;
/**
 * CityCommunityServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Service
public class CityCommunityServiceImpl implements CityCommunityService{

	private Logger logger=LoggerFactory.getLogger(CityCommunityServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CityCommunityMapper cityCommunityMapper;

	/**
	 * 新增cityCommunity
	 *
	 * @param cityCommunity
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public void insertCityCommunity(CityCommunity cityCommunity) throws CityCommunityException{
		try{
			cityCommunityMapper.insertCityCommunity(cityCommunity);
		}catch(Exception e){
			logger.error("新增CityCommunity时报错", e);
			throw new CityCommunityException("新增CityCommunity时报错", e);
		}
	}

	/**
	 * 删除cityCommunity
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int deleteCityCommunity(long id) throws CityCommunityException{
		try{
			return this.cityCommunityMapper.deleteCityCommunity(id);
		}catch(Exception e){
			logger.error("删除CityCommunity时报错", e);
			throw new CityCommunityException("删除CityCommunity时报错", e);
		}
	}

	/**
	 * 修改cityCommunity
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int updateCityCommunity(Map<String, Object> condition) throws CityCommunityException{
		try{
			return this.cityCommunityMapper.updateCityCommunity(condition);
		}catch(Exception e){
			logger.error("修改CityCommunity时报错", e);
			throw new CityCommunityException("修改CityCommunity时报错", e);
		}
	}

	/**
	 * 查询cityCommunity
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public CityCommunity readCityCommunity(long id) throws CityCommunityException{
		try{
			return this.cityCommunityMapper.readCityCommunity(id);
		}catch(Exception e){
			logger.error("查询CityCommunity时报错", e);
			throw new CityCommunityException("查询CityCommunity时报错", e);
		}
	}

	/**
	 * 查询cityCommunity集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public List<CityCommunity> queryCityCommunityList(Map<String, Object> condition){
		try{
			return this.cityCommunityMapper.queryCityCommunityList(condition);
		}catch(Exception e){
			logger.error("查询CityCommunity时报错", e);
			return null;
		}
	}

	/**
	 * 查询cityCommunity集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public long queryCityCommunityCount(Map<String, Object> condition) throws CityCommunityException{
		try{
			return this.cityCommunityMapper.queryCityCommunityCount(condition);
		}catch(Exception e){
			logger.error("查询CityCommunity时报错", e);
			throw new CityCommunityException("查询CityCommunity时报错", e);
		}
	}

}