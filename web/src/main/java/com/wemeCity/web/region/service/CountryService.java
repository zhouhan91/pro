
package com.wemeCity.web.region.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.region.exception.CountryException;
import com.wemeCity.web.region.model.Country;

/**
 * CountryService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
public interface CountryService {

	/**
	 * 新增country
	 *
	 * @param country
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertCountry(Country country) throws CountryException;

	/**
	 * 删除country
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteCountry(long id) throws CountryException;

	/**
	 * 修改country
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateCountry(Map<String, Object> condition) throws CountryException;

	/**
	 * 查询country
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	Country readCountry(long id) throws CountryException;

	/**
	 * 查询country集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<Country> queryCountryList(Map<String, Object> condition) ;

	/**
	 * 查询country集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryCountryCount(Map<String, Object> condition) throws CountryException;

}