
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringGoodsCategory;

/**
 * CateringGoodsCategoryMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-8 新建
 */
@Repository
public interface CateringGoodsCategoryMapper {

	/**
	 * 新增cateringGoodsCategory
	 *
	 * @param cateringGoodsCategory
	 * @return 新增的对象
	 * @author 向小文 2017-12-8 新建
	 */
	void insertCateringGoodsCategory(CateringGoodsCategory cateringGoodsCategory);

	/**
	 * 删除cateringGoodsCategory
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-8 新建
	 */
	int deleteCateringGoodsCategory(long id);

	/**
	 * 修改cateringGoodsCategory
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-8 新建
	 */
	int updateCateringGoodsCategory(Map<String, Object> condition);

	/**
	 * 查询cateringGoodsCategory
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-8 新建
	 */
	CateringGoodsCategory readCateringGoodsCategory(long id);

	/**
	 * 查询cateringGoodsCategory集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-8 新建
	 */
	List<CateringGoodsCategory> queryCateringGoodsCategoryList(Map<String, Object> condition);

	/**
	 * 查询cateringGoodsCategory集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-8 新建
	 */
	long queryCateringGoodsCategoryCount(Map<String, Object> condition);

}