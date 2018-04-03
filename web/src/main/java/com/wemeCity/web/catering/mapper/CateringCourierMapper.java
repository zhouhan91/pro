
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringCourier;

/**
 * CateringCourierMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-25 新建
 */
@Repository
public interface CateringCourierMapper {

	/**
	 * 新增cateringCourier
	 *
	 * @param cateringCourier
	 * @return 新增的对象
	 * @author 向小文 2017-12-25 新建
	 */
	void insertCateringCourier(CateringCourier cateringCourier);

	/**
	 * 删除cateringCourier
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-25 新建
	 */
	int deleteCateringCourier(long id);

	/**
	 * 修改cateringCourier
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-25 新建
	 */
	int updateCateringCourier(Map<String, Object> condition);

	/**
	 * 查询cateringCourier
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-25 新建
	 */
	CateringCourier readCateringCourier(long id);

	/**
	 * 查询cateringCourier集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-25 新建
	 */
	List<CateringCourier> queryCateringCourierList(Map<String, Object> condition);

	/**
	 * 查询cateringCourier集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-25 新建
	 */
	long queryCateringCourierCount(Map<String, Object> condition);

}