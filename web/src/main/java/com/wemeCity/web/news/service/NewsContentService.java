
package com.wemeCity.web.news.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.news.exception.NewsContentException;
import com.wemeCity.web.news.model.NewsContent;

/**
 * NewsContentService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
public interface NewsContentService {

	/**
	 * 新增newsContent
	 *
	 * @param newsContent
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	void insertNewsContent(NewsContent newsContent) throws NewsContentException;

	/**
	 * 删除newsContent
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int deleteNewsContent(long id) throws NewsContentException;

	/**
	 * 修改newsContent
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int updateNewsContent(Map<String, Object> condition) throws NewsContentException;

	/**
	 * 查询newsContent
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	NewsContent readNewsContent(long id) throws NewsContentException;

	/**
	 * 查询newsContent集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	List<NewsContent> queryNewsContentList(Map<String, Object> condition) ;

	/**
	 * 查询newsContent集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	long queryNewsContentCount(Map<String, Object> condition) throws NewsContentException;
	
	/**
	 * 根据新闻id获取新闻图文信息
	 *
	 * @param newsId 新闻图文信息
	 * @return
	 * @throws NewsContentException
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	NewsContent readNewsContentByNewsId(long newsId) throws NewsContentException;

}