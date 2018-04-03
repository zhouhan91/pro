
package com.wemeCity.web.news.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.news.model.NewsSubNavigation;

/**
 * NewsSubNavigationMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-19 新建
 */
@Repository
public interface NewsSubNavigationMapper {

	/**
	 * 新增newsSubNavigation
	 *
	 * @param newsSubNavigation
	 * @return 新增的对象
	 * @author 向小文 2017-10-19 新建
	 */
	void insertNewsSubNavigation(NewsSubNavigation newsSubNavigation);

	/**
	 * 删除newsSubNavigation
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-19 新建
	 */
	int deleteNewsSubNavigation(long id);

	/**
	 * 修改newsSubNavigation
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-19 新建
	 */
	int updateNewsSubNavigation(Map<String, Object> condition);

	/**
	 * 查询newsSubNavigation
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-19 新建
	 */
	NewsSubNavigation readNewsSubNavigation(long id);

	/**
	 * 查询newsSubNavigation集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-19 新建
	 */
	List<NewsSubNavigation> queryNewsSubNavigationList(Map<String, Object> condition);

	/**
	 * 查询newsSubNavigation集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-19 新建
	 */
	long queryNewsSubNavigationCount(Map<String, Object> condition);

}