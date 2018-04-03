
package com.wemeCity.web.region.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.region.exception.DistrictException;
import com.wemeCity.web.region.model.District;

/**
 * DistrictService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-18 新建
 */
public interface DistrictService {

	/**
	 * 新增district
	 *
	 * @param district
	 * @return 新增的对象
	 * @author 向小文 2017-9-18 新建
	 */
	void insertDistrict(District district) throws DistrictException;

	/**
	 * 删除district
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-18 新建
	 */
	int deleteDistrict(long id) throws DistrictException;

	/**
	 * 修改district
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-18 新建
	 */
	int updateDistrict(Map<String, Object> condition) throws DistrictException;

	/**
	 * 查询district
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-18 新建
	 */
	District readDistrict(long id) throws DistrictException;

	/**
	 * 查询district集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-18 新建
	 */
	List<District> queryDistrictList(Map<String, Object> condition) ;

	/**
	 * 查询district集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-18 新建
	 */
	long queryDistrictCount(Map<String, Object> condition) throws DistrictException;

}