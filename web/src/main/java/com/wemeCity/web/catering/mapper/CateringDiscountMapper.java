
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringDiscount;

/**
 * CateringDiscountMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Repository
public interface CateringDiscountMapper {

	/**
	 * 新增cateringDiscount
	 *
	 * @param cateringDiscount
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringDiscount(CateringDiscount cateringDiscount);

	/**
	 * 删除cateringDiscount
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringDiscount(long id);

	/**
	 * 修改cateringDiscount
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringDiscount(Map<String, Object> condition);

	/**
	 * 查询cateringDiscount
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringDiscount readCateringDiscount(long id);

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
	long queryCateringDiscountCount(Map<String, Object> condition);

}