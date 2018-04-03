
package com.wemeCity.web.news.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.news.exception.NewsSubNavigationException;
import com.wemeCity.web.news.model.NewsSubNavigation;

/**
 * NewsSubNavigationService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-19 新建
 */
public interface NewsSubNavigationService {

	/**
	 * 新增newsSubNavigation
	 *
	 * @param newsSubNavigation
	 * @return 新增的对象
	 * @author 向小文 2017-10-19 新建
	 */
	void insertNewsSubNavigation(NewsSubNavigation newsSubNavigation) throws NewsSubNavigationException;

	/**
	 * 删除newsSubNavigation
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-19 新建
	 */
	int deleteNewsSubNavigation(long id) throws NewsSubNavigationException;

	/**
	 * 修改newsSubNavigation
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-19 新建
	 */
	int updateNewsSubNavigation(Map<String, Object> condition) throws NewsSubNavigationException;

	/**
	 * 查询newsSubNavigation
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-19 新建
	 */
	NewsSubNavigation readNewsSubNavigation(long id) throws NewsSubNavigationException;

	/**
	 * 查询newsSubNavigation集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-19 新建
	 */
	List<NewsSubNavigation> queryNewsSubNavigationList(Map<String, Object> condition) ;

	/**
	 * 查询newsSubNavigation集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-19 新建
	 */
	long queryNewsSubNavigationCount(Map<String, Object> condition) throws NewsSubNavigationException;

}