
package com.wemeCity.web.news.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.news.model.NewsBabieta;

/**
 * NewsBabietaMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-20 新建
 */
@Repository
public interface NewsBabietaMapper {

	/**
	 * 新增newsBabieta
	 *
	 * @param newsBabieta
	 * @return 新增的对象
	 * @author 向小文 2017-12-20 新建
	 */
	void insertNewsBabieta(NewsBabieta newsBabieta);

	/**
	 * 删除newsBabieta
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-20 新建
	 */
	int deleteNewsBabieta(long id);

	/**
	 * 修改newsBabieta
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-20 新建
	 */
	int updateNewsBabieta(Map<String, Object> condition);

	/**
	 * 查询newsBabieta
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-20 新建
	 */
	NewsBabieta readNewsBabieta(long id);

	/**
	 * 查询newsBabieta集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-20 新建
	 */
	List<NewsBabieta> queryNewsBabietaList(Map<String, Object> condition);

	/**
	 * 查询newsBabieta集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-20 新建
	 */
	long queryNewsBabietaCount(Map<String, Object> condition);

}