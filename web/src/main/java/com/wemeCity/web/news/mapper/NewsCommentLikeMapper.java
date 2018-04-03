
package com.wemeCity.web.news.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.news.model.NewsCommentLike;

/**
 * NewsCommentLikeMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-19 新建
 */
@Repository
public interface NewsCommentLikeMapper {

	/**
	 * 新增newsCommentLike
	 *
	 * @param newsCommentLike
	 * @return 新增的对象
	 * @author 向小文 2017-10-19 新建
	 */
	void insertNewsCommentLike(NewsCommentLike newsCommentLike);

	/**
	 * 删除newsCommentLike
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-19 新建
	 */
	int deleteNewsCommentLike(long id);

	/**
	 * 修改newsCommentLike
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-19 新建
	 */
	int updateNewsCommentLike(Map<String, Object> condition);

	/**
	 * 查询newsCommentLike
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-19 新建
	 */
	NewsCommentLike readNewsCommentLike(long id);

	/**
	 * 查询newsCommentLike集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-19 新建
	 */
	List<NewsCommentLike> queryNewsCommentLikeList(Map<String, Object> condition);

	/**
	 * 查询newsCommentLike集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-19 新建
	 */
	long queryNewsCommentLikeCount(Map<String, Object> condition);

}