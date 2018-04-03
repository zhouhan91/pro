package com.wemeCity.web.catering.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.web.catering.exception.CateringDiscountException;
import com.wemeCity.web.catering.model.CateringDiscount;

/**
 * CateringDiscountService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public interface CateringDiscountService {

	/**
	 * 新增cateringDiscount
	 *
	 * @param cateringDiscount
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringDiscount(CateringDiscount cateringDiscount) throws CateringDiscountException;

	/**
	 * 删除cateringDiscount
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringDiscount(long id) throws CateringDiscountException;

	/**
	 * 修改cateringDiscount
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringDiscount(Map<String, Object> condition) throws CateringDiscountException;

	/**
	 * 查询cateringDiscount
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringDiscount readCateringDiscount(long id) throws CateringDiscountException;

	/**
	 * 查询cateringDiscount集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringDiscount> queryCateringDiscountList(Map<String, Object> condition);

	/**
	 * 查询cateringDiscount集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringDiscountCount(Map<String, Object> condition) throws CateringDiscountException;

	/**
	 * 查询店铺优惠
	 *
	 * @param restaurantId
	 * @return
	 * @history 2017年12月9日 新建
	 * @auther xiaowen
	 */
	List<CateringDiscount> queryRestaurantDisCount(long restaurantId);
	
	/**
	 * 修改店铺优惠
	 *
	 * @param lstCateringDiscount
	 * @return
	 * @throws CateringDiscountException
	 * @history 2017年12月24日 新建
	 * @auther xiaowen
	 */
	BaseDTO updateDiscountInfo(@RequestBody List<CateringDiscount> lstCateringDiscount) throws CateringDiscountException;
	
	/**
	 * 生成店铺时初始化优惠信息
	 *
	 * @param restaurantId
	 * @param managerId
	 * @return
	 * @throws CateringDiscountException
	 * @history 2017年12月24日 新建
	 * @auther xiaowen
	 */
	List<CateringDiscount> genRestaurantDisCount(long restaurantId, long managerId) throws CateringDiscountException;

}