
package com.wemeCity.components.search.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.components.search.model.PopularWords;

/**
 * PopularWordsMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-16 新建
 */
@Repository
public interface PopularWordsMapper {

	/**
	 * 新增popularWords
	 *
	 * @param popularWords
	 * @return 新增的对象
	 * @author 向小文 2017-9-16 新建
	 */
	void insertPopularWords(PopularWords popularWords);

	/**
	 * 删除popularWords
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-16 新建
	 */
	int deletePopularWords(long id);

	/**
	 * 修改popularWords
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-16 新建
	 */
	int updatePopularWords(Map<String, Object> condition);

	/**
	 * 查询popularWords
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-16 新建
	 */
	PopularWords readPopularWords(long id);

	/**
	 * 查询popularWords集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-16 新建
	 */
	List<PopularWords> queryPopularWordsList(Map<String, Object> condition);

	/**
	 * 查询popularWords集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-16 新建
	 */
	long queryPopularWordsCount(Map<String, Object> condition);

}