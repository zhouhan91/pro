
package com.wemeCity.web.news.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.news.model.NewsLike;

/**
 * NewsLikeMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
@Repository
public interface NewsLikeMapper {

	/**
	 * 新增newsLike
	 *
	 * @param newsLike
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	void insertNewsLike(NewsLike newsLike);

	/**
	 * 删除newsLike
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int deleteNewsLike(long id);

	/**
	 * 修改newsLike
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int updateNewsLike(Map<String, Object> condition);

	/**
	 * 查询newsLike
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	NewsLike readNewsLike(long id);

	/**
	 * 查询newsLike集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	List<NewsLike> queryNewsLikeList(Map<String, Object> condition);

	/**
	 * 查询newsLike集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	long queryNewsLikeCount(Map<String, Object> condition);

}