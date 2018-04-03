
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringComment;

/**
 * CateringCommentMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Repository
public interface CateringCommentMapper {

	/**
	 * 新增cateringComment
	 *
	 * @param cateringComment
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringComment(CateringComment cateringComment);

	/**
	 * 删除cateringComment
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringComment(long id);

	/**
	 * 修改cateringComment
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringComment(Map<String, Object> condition);

	/**
	 * 查询cateringComment
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringComment readCateringComment(long id);

	/**
	 * 查询cateringComment集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringComment> queryCateringCommentList(Map<String, Object> condition);

	/**
	 * 查询cateringComment集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringCommentCount(Map<String, Object> condition);

}