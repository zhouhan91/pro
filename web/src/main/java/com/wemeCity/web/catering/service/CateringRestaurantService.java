
package com.wemeCity.web.catering.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.web.catering.dto.RestaurantUpdateDTO;
import com.wemeCity.web.catering.exception.CateringRestaurantException;
import com.wemeCity.web.catering.model.CateringRestaurant;

/**
 * CateringRestaurantService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public interface CateringRestaurantService {

	/**
	 * 新增cateringRestaurant
	 *
	 * @param cateringRestaurant
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringRestaurant(CateringRestaurant cateringRestaurant) throws CateringRestaurantException;

	/**
	 * 删除cateringRestaurant
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringRestaurant(long id) throws CateringRestaurantException;

	/**
	 * 修改cateringRestaurant
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringRestaurant(Map<String, Object> condition) throws CateringRestaurantException;

	/**
	 * 查询cateringRestaurant
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringRestaurant readCateringRestaurant(long id) throws CateringRestaurantException;

	/**
	 * 查询cateringRestaurant集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringRestaurant> queryCateringRestaurantList(Map<String, Object> condition) ;

	/**
	 * 查询cateringRestaurant集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringRestaurantCount(Map<String, Object> condition) throws CateringRestaurantException;
	
	/**
	 * 根据managerid读取店铺信息
	 *
	 * @param managerId
	 * @return
	 * @throws CateringRestaurantException
	 * @history 2017年12月23日 新建
	 * @auther xiaowen
	 */
	CateringRestaurant readRestaurantByManagerId(long managerId) throws CateringRestaurantException;
	
	
	/**
	 * 修改店铺信息
	 *
	 * @param updateDTO
	 * @return
	 * @throws CateringRestaurantException
	 * @history 2017年12月23日 新建
	 * @auther xiaowen
	 */
	BaseDTO updateRestaurantInfo(RestaurantUpdateDTO updateDTO) throws CateringRestaurantException;
	
	/**
	 * 获取店铺信息
	 *
	 * @param id
	 * @return
	 * @history 2018年2月4日 新建
	 * @auther xiaowen
	 */
	CateringRestaurant getRestaurantInfo(long id);

}