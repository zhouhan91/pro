
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringCategory;

/**
 * CateringCategoryMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Repository
public interface CateringCategoryMapper {

	/**
	 * 新增cateringCategory
	 *
	 * @param cateringCategory
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringCategory(CateringCategory cateringCategory);

	/**
	 * 删除cateringCategory
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringCategory(long id);

	/**
	 * 修改cateringCategory
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringCategory(Map<String, Object> condition);

	/**
	 * 查询cateringCategory
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringCategory readCateringCategory(long id);

	/**
	 * 查询cateringCategory集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringCategory> queryCateringCategoryList(Map<String, Object> condition);

	/**
	 * 查询cateringCategory集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringCategoryCount(Map<String, Object> condition);

}