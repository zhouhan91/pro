
package com.wemeCity.web.news.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.news.model.NewsComment;

/**
 * NewsCommentMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
@Repository
public interface NewsCommentMapper {

	/**
	 * 新增newsComment
	 *
	 * @param newsComment
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	void insertNewsComment(NewsComment newsComment);

	/**
	 * 删除newsComment
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int deleteNewsComment(long id);

	/**
	 * 修改newsComment
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int updateNewsComment(Map<String, Object> condition);

	/**
	 * 查询newsComment
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	NewsComment readNewsComment(long id);

	/**
	 * 查询newsComment集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	List<NewsComment> queryNewsCommentList(Map<String, Object> condition);

	/**
	 * 查询newsComment集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	long queryNewsCommentCount(Map<String, Object> condition);

}