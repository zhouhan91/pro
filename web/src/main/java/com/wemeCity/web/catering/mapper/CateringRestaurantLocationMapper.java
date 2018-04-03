
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringRestaurantLocation;

/**
 * CateringRestaurantLocationMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-8 新建
 */
@Repository
public interface CateringRestaurantLocationMapper {

	/**
	 * 新增cateringRestaurantLocation
	 *
	 * @param cateringRestaurantLocation
	 * @return 新增的对象
	 * @author 向小文 2017-12-8 新建
	 */
	void insertCateringRestaurantLocation(CateringRestaurantLocation cateringRestaurantLocation);

	/**
	 * 删除cateringRestaurantLocation
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-8 新建
	 */
	int deleteCateringRestaurantLocation(long id);

	/**
	 * 修改cateringRestaurantLocation
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-8 新建
	 */
	int updateCateringRestaurantLocation(Map<String, Object> condition);

	/**
	 * 查询cateringRestaurantLocation
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-8 新建
	 */
	CateringRestaurantLocation readCateringRestaurantLocation(long id);

	/**
	 * 查询cateringRestaurantLocation集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-8 新建
	 */
	List<CateringRestaurantLocation> queryCateringRestaurantLocationList(Map<String, Object> condition);

	/**
	 * 查询cateringRestaurantLocation集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-8 新建
	 */
	long queryCateringRestaurantLocationCount(Map<String, Object> condition);

}