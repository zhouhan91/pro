
package com.wemeCity.web.region.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.region.exception.CityException;
import com.wemeCity.web.region.model.City;

/**
 * CityService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
public interface CityService {

	/**
	 * 新增city
	 *
	 * @param city
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertCity(City city) throws CityException;

	/**
	 * 删除city
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteCity(long id) throws CityException;

	/**
	 * 修改city
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateCity(Map<String, Object> condition) throws CityException;

	/**
	 * 查询city
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	City readCity(long id) throws CityException;

	/**
	 * 查询city集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<City> queryCityList(Map<String, Object> condition) ;

	/**
	 * 查询city集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryCityCount(Map<String, Object> condition) throws CityException;

}