
package com.wemeCity.web.news.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.news.exception.NewsException;
import com.wemeCity.web.news.model.News;

/**
 * NewsService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
public interface NewsService {

	/**
	 * 新增news
	 *
	 * @param news
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	void insertNews(News news) throws NewsException;

	/**
	 * 删除news
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int deleteNews(long id) throws NewsException;

	/**
	 * 修改news
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int updateNews(Map<String, Object> condition) throws NewsException;

	/**
	 * 查询news
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	News readNews(long id) throws NewsException;

	/**
	 * 查询news集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	List<News> queryNewsList(Map<String, Object> condition) ;

	/**
	 * 查询news集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	long queryNewsCount(Map<String, Object> condition) throws NewsException;

}