
package com.wemeCity.web.news.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.news.model.NewsMark;

/**
 * NewsMarkMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
@Repository
public interface NewsMarkMapper {

	/**
	 * 新增newsMark
	 *
	 * @param newsMark
	 * @return 新增的对象
	 * @author 向小文 2017-10-16 新建
	 */
	void insertNewsMark(NewsMark newsMark);

	/**
	 * 删除newsMark
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int deleteNewsMark(long id);

	/**
	 * 修改newsMark
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-16 新建
	 */
	int updateNewsMark(Map<String, Object> condition);

	/**
	 * 查询newsMark
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-16 新建
	 */
	NewsMark readNewsMark(long id);

	/**
	 * 查询newsMark集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-16 新建
	 */
	List<NewsMark> queryNewsMarkList(Map<String, Object> condition);

	/**
	 * 查询newsMark集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-16 新建
	 */
	long queryNewsMarkCount(Map<String, Object> condition);

	/**
	 * 取消收藏新闻
	 *
	 * @param condition
	 * @return
	 * @Author Xiang xiaowen 2017年10月19日 新建
	 */
	int cancelMark(Map<String, Object> condition);
}