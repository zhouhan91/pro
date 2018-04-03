
package com.wemeCity.web.catering.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.catering.mapper.CateringGoodsCategoryMapper;
import com.wemeCity.web.catering.model.CateringGoodsCategory;
import com.wemeCity.web.catering.exception.CateringGoodsCategoryException;
import com.wemeCity.web.catering.service.CateringGoodsCategoryService;
/**
 * CateringGoodsCategoryServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-8 新建
 */
@Service
public class CateringGoodsCategoryServiceImpl implements CateringGoodsCategoryService{

	private Logger logger=LoggerFactory.getLogger(CateringGoodsCategoryServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CateringGoodsCategoryMapper cateringGoodsCategoryMapper;

	/**
	 * 新增cateringGoodsCategory
	 *
	 * @param cateringGoodsCategory
	 * @return 新增的对象
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public void insertCateringGoodsCategory(CateringGoodsCategory cateringGoodsCategory) throws CateringGoodsCategoryException{
		try{
			cateringGoodsCategoryMapper.insertCateringGoodsCategory(cateringGoodsCategory);
		}catch(Exception e){
			logger.error("新增CateringGoodsCategory时报错", e);
			throw new CateringGoodsCategoryException("新增CateringGoodsCategory时报错", e);
		}
	}

	/**
	 * 删除cateringGoodsCategory
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public int deleteCateringGoodsCategory(long id) throws CateringGoodsCategoryException{
		try{
			return this.cateringGoodsCategoryMapper.deleteCateringGoodsCategory(id);
		}catch(Exception e){
			logger.error("删除CateringGoodsCategory时报错", e);
			throw new CateringGoodsCategoryException("删除CateringGoodsCategory时报错", e);
		}
	}

	/**
	 * 修改cateringGoodsCategory
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public int updateCateringGoodsCategory(Map<String, Object> condition) throws CateringGoodsCategoryException{
		try{
			return this.cateringGoodsCategoryMapper.updateCateringGoodsCategory(condition);
		}catch(Exception e){
			logger.error("修改CateringGoodsCategory时报错", e);
			throw new CateringGoodsCategoryException("修改CateringGoodsCategory时报错", e);
		}
	}

	/**
	 * 查询cateringGoodsCategory
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public CateringGoodsCategory readCateringGoodsCategory(long id) throws CateringGoodsCategoryException{
		try{
			return this.cateringGoodsCategoryMapper.readCateringGoodsCategory(id);
		}catch(Exception e){
			logger.error("查询CateringGoodsCategory时报错", e);
			throw new CateringGoodsCategoryException("查询CateringGoodsCategory时报错", e);
		}
	}

	/**
	 * 查询cateringGoodsCategory集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public List<CateringGoodsCategory> queryCateringGoodsCategoryList(Map<String, Object> condition){
		try{
			return this.cateringGoodsCategoryMapper.queryCateringGoodsCategoryList(condition);
		}catch(Exception e){
			logger.error("查询CateringGoodsCategory时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringGoodsCategory集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public long queryCateringGoodsCategoryCount(Map<String, Object> condition) throws CateringGoodsCategoryException{
		try{
			return this.cateringGoodsCategoryMapper.queryCateringGoodsCategoryCount(condition);
		}catch(Exception e){
			logger.error("查询CateringGoodsCategory时报错", e);
			throw new CateringGoodsCategoryException("查询CateringGoodsCategory时报错", e);
		}
	}

}