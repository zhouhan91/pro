
package com.wemeCity.web.catering.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.catering.mapper.CateringCategoryMapper;
import com.wemeCity.web.catering.model.CateringCategory;
import com.wemeCity.web.catering.exception.CateringCategoryException;
import com.wemeCity.web.catering.service.CateringCategoryService;
/**
 * CateringCategoryServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Service
public class CateringCategoryServiceImpl implements CateringCategoryService{

	private Logger logger=LoggerFactory.getLogger(CateringCategoryServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CateringCategoryMapper cateringCategoryMapper;

	/**
	 * 新增cateringCategory
	 *
	 * @param cateringCategory
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public void insertCateringCategory(CateringCategory cateringCategory) throws CateringCategoryException{
		try{
			cateringCategoryMapper.insertCateringCategory(cateringCategory);
		}catch(Exception e){
			logger.error("新增CateringCategory时报错", e);
			throw new CateringCategoryException("新增CateringCategory时报错", e);
		}
	}

	/**
	 * 删除cateringCategory
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int deleteCateringCategory(long id) throws CateringCategoryException{
		try{
			return this.cateringCategoryMapper.deleteCateringCategory(id);
		}catch(Exception e){
			logger.error("删除CateringCategory时报错", e);
			throw new CateringCategoryException("删除CateringCategory时报错", e);
		}
	}

	/**
	 * 修改cateringCategory
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int updateCateringCategory(Map<String, Object> condition) throws CateringCategoryException{
		try{
			return this.cateringCategoryMapper.updateCateringCategory(condition);
		}catch(Exception e){
			logger.error("修改CateringCategory时报错", e);
			throw new CateringCategoryException("修改CateringCategory时报错", e);
		}
	}

	/**
	 * 查询cateringCategory
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public CateringCategory readCateringCategory(long id) throws CateringCategoryException{
		try{
			return this.cateringCategoryMapper.readCateringCategory(id);
		}catch(Exception e){
			logger.error("查询CateringCategory时报错", e);
			throw new CateringCategoryException("查询CateringCategory时报错", e);
		}
	}

	/**
	 * 查询cateringCategory集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public List<CateringCategory> queryCateringCategoryList(Map<String, Object> condition){
		try{
			return this.cateringCategoryMapper.queryCateringCategoryList(condition);
		}catch(Exception e){
			logger.error("查询CateringCategory时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringCategory集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public long queryCateringCategoryCount(Map<String, Object> condition) throws CateringCategoryException{
		try{
			return this.cateringCategoryMapper.queryCateringCategoryCount(condition);
		}catch(Exception e){
			logger.error("查询CateringCategory时报错", e);
			throw new CateringCategoryException("查询CateringCategory时报错", e);
		}
	}

}