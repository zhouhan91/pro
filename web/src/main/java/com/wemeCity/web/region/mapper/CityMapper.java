
package com.wemeCity.web.region.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.region.model.City;

/**
 * CityMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Repository
public interface CityMapper {

	/**
	 * 新增city
	 *
	 * @param city
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertCity(City city);

	/**
	 * 删除city
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteCity(long id);

	/**
	 * 修改city
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateCity(Map<String, Object> condition);

	/**
	 * 查询city
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	City readCity(long id);

	/**
	 * 查询city集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<City> queryCityList(Map<String, Object> condition);

	/**
	 * 查询city集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryCityCount(Map<String, Object> condition);

}