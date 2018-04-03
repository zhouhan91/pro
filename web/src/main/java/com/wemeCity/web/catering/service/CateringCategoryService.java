
package com.wemeCity.web.catering.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.catering.exception.CateringCategoryException;
import com.wemeCity.web.catering.model.CateringCategory;

/**
 * CateringCategoryService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public interface CateringCategoryService {

	/**
	 * 新增cateringCategory
	 *
	 * @param cateringCategory
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringCategory(CateringCategory cateringCategory) throws CateringCategoryException;

	/**
	 * 删除cateringCategory
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringCategory(long id) throws CateringCategoryException;

	/**
	 * 修改cateringCategory
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringCategory(Map<String, Object> condition) throws CateringCategoryException;

	/**
	 * 查询cateringCategory
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringCategory readCateringCategory(long id) throws CateringCategoryException;

	/**
	 * 查询cateringCategory集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringCategory> queryCateringCategoryList(Map<String, Object> condition) ;

	/**
	 * 查询cateringCategory集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringCategoryCount(Map<String, Object> condition) throws CateringCategoryException;

}