
package com.wemeCity.web.catering.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.web.catering.dto.RestaurantLocationSaveDTO;
import com.wemeCity.web.catering.exception.CateringRestaurantLocationException;
import com.wemeCity.web.catering.model.CateringRestaurantLocation;

/**
 * CateringRestaurantLocationService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-8 新建
 */
public interface CateringRestaurantLocationService {

	/**
	 * 新增cateringRestaurantLocation
	 *
	 * @param cateringRestaurantLocation
	 * @return 新增的对象
	 * @author 向小文 2017-12-8 新建
	 */
	void insertCateringRestaurantLocation(CateringRestaurantLocation cateringRestaurantLocation) throws CateringRestaurantLocationException;

	/**
	 * 删除cateringRestaurantLocation
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-8 新建
	 */
	int deleteCateringRestaurantLocation(long id) throws CateringRestaurantLocationException;

	/**
	 * 修改cateringRestaurantLocation
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-8 新建
	 */
	int updateCateringRestaurantLocation(Map<String, Object> condition) throws CateringRestaurantLocationException;

	/**
	 * 查询cateringRestaurantLocation
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-8 新建
	 */
	CateringRestaurantLocation readCateringRestaurantLocation(long id) throws CateringRestaurantLocationException;

	/**
	 * 查询cateringRestaurantLocation集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-8 新建
	 */
	List<CateringRestaurantLocation> queryCateringRestaurantLocationList(Map<String, Object> condition) ;

	/**
	 * 查询cateringRestaurantLocation集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-8 新建
	 */
	long queryCateringRestaurantLocationCount(Map<String, Object> condition) throws CateringRestaurantLocationException;
	
	
	/**
	 * 保存店铺配送信息
	 *
	 * @param dto
	 * @return
	 * @throws CateringRestaurantLocationException
	 * @history 2017年12月25日 新建
	 * @auther xiaowen
	 */
	BaseDTO saveRestaurantLocationList(RestaurantLocationSaveDTO dto) throws CateringRestaurantLocationException;

}