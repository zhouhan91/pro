
package com.wemeCity.web.news.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.news.model.NewsContent;

/**
 * NewsContentMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
@Repository
public interface NewsContentMapper {

	/**
	 * 新增newsContent
	 *
	 * @param newsContent
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	void insertNewsContent(NewsContent newsContent);

	/**
	 * 删除newsContent
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int deleteNewsContent(long id);

	/**
	 * 修改newsContent
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int updateNewsContent(Map<String, Object> condition);

	/**
	 * 查询newsContent
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	NewsContent readNewsContent(long id);

	/**
	 * 查询newsContent集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	List<NewsContent> queryNewsContentList(Map<String, Object> condition);

	/**
	 * 查询newsContent集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	long queryNewsContentCount(Map<String, Object> condition);

}