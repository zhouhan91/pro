
package com.wemeCity.web.catering.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.catering.mapper.CateringRecommendMapper;
import com.wemeCity.web.catering.model.CateringRecommend;
import com.wemeCity.web.catering.exception.CateringRecommendException;
import com.wemeCity.web.catering.service.CateringRecommendService;
/**
 * CateringRecommendServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Service
public class CateringRecommendServiceImpl implements CateringRecommendService{

	private Logger logger=LoggerFactory.getLogger(CateringRecommendServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CateringRecommendMapper cateringRecommendMapper;

	/**
	 * 新增cateringRecommend
	 *
	 * @param cateringRecommend
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public void insertCateringRecommend(CateringRecommend cateringRecommend) throws CateringRecommendException{
		try{
			cateringRecommendMapper.insertCateringRecommend(cateringRecommend);
		}catch(Exception e){
			logger.error("新增CateringRecommend时报错", e);
			throw new CateringRecommendException("新增CateringRecommend时报错", e);
		}
	}

	/**
	 * 删除cateringRecommend
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int deleteCateringRecommend(long id) throws CateringRecommendException{
		try{
			return this.cateringRecommendMapper.deleteCateringRecommend(id);
		}catch(Exception e){
			logger.error("删除CateringRecommend时报错", e);
			throw new CateringRecommendException("删除CateringRecommend时报错", e);
		}
	}

	/**
	 * 修改cateringRecommend
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int updateCateringRecommend(Map<String, Object> condition) throws CateringRecommendException{
		try{
			return this.cateringRecommendMapper.updateCateringRecommend(condition);
		}catch(Exception e){
			logger.error("修改CateringRecommend时报错", e);
			throw new CateringRecommendException("修改CateringRecommend时报错", e);
		}
	}

	/**
	 * 查询cateringRecommend
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public CateringRecommend readCateringRecommend(long id) throws CateringRecommendException{
		try{
			return this.cateringRecommendMapper.readCateringRecommend(id);
		}catch(Exception e){
			logger.error("查询CateringRecommend时报错", e);
			throw new CateringRecommendException("查询CateringRecommend时报错", e);
		}
	}

	/**
	 * 查询cateringRecommend集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public List<CateringRecommend> queryCateringRecommendList(Map<String, Object> condition){
		try{
			return this.cateringRecommendMapper.queryCateringRecommendList(condition);
		}catch(Exception e){
			logger.error("查询CateringRecommend时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringRecommend集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public long queryCateringRecommendCount(Map<String, Object> condition) throws CateringRecommendException{
		try{
			return this.cateringRecommendMapper.queryCateringRecommendCount(condition);
		}catch(Exception e){
			logger.error("查询CateringRecommend时报错", e);
			throw new CateringRecommendException("查询CateringRecommend时报错", e);
		}
	}

}