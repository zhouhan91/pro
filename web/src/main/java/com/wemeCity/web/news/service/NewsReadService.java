
package com.wemeCity.web.news.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.news.exception.NewsReadException;
import com.wemeCity.web.news.model.NewsRead;

/**
 * NewsReadService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
public interface NewsReadService {

	/**
	 * 新增newsRead
	 *
	 * @param newsRead
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	void insertNewsRead(NewsRead newsRead) throws NewsReadException;

	/**
	 * 删除newsRead
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int deleteNewsRead(long id) throws NewsReadException;

	/**
	 * 修改newsRead
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int updateNewsRead(Map<String, Object> condition) throws NewsReadException;

	/**
	 * 查询newsRead
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	NewsRead readNewsRead(long id) throws NewsReadException;

	/**
	 * 查询newsRead集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	List<NewsRead> queryNewsReadList(Map<String, Object> condition) ;

	/**
	 * 查询newsRead集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	long queryNewsReadCount(Map<String, Object> condition) throws NewsReadException;

}