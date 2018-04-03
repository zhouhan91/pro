
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringRestaurant;

/**
 * CateringRestaurantMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Repository
public interface CateringRestaurantMapper {

	/**
	 * 新增cateringRestaurant
	 *
	 * @param cateringRestaurant
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringRestaurant(CateringRestaurant cateringRestaurant);

	/**
	 * 删除cateringRestaurant
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringRestaurant(long id);

	/**
	 * 修改cateringRestaurant
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringRestaurant(Map<String, Object> condition);

	/**
	 * 查询cateringRestaurant
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringRestaurant readCateringRestaurant(long id);

	/**
	 * 查询cateringRestaurant集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringRestaurant> queryCateringRestaurantList(Map<String, Object> condition);

	/**
	 * 查询cateringRestaurant集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringRestaurantCount(Map<String, Object> condition);

}