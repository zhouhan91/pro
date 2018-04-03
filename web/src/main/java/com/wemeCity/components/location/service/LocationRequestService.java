
package com.wemeCity.components.location.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.components.location.exception.LocationRequestException;
import com.wemeCity.components.location.model.LocationRequest;

/**
 * LocationRequestService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-17 新建
 */
public interface LocationRequestService {

	/**
	 * 新增locationRequest
	 *
	 * @param locationRequest
	 * @return 新增的对象
	 * @author 向小文 2017-9-17 新建
	 */
	void insertLocationRequest(LocationRequest locationRequest) throws LocationRequestException;

	/**
	 * 删除locationRequest
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-17 新建
	 */
	int deleteLocationRequest(long id) throws LocationRequestException;

	/**
	 * 修改locationRequest
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-17 新建
	 */
	int updateLocationRequest(Map<String, Object> condition) throws LocationRequestException;

	/**
	 * 查询locationRequest
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-17 新建
	 */
	LocationRequest readLocationRequest(long id) throws LocationRequestException;

	/**
	 * 查询locationRequest集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-17 新建
	 */
	List<LocationRequest> queryLocationRequestList(Map<String, Object> condition) ;

	/**
	 * 查询locationRequest集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-17 新建
	 */
	long queryLocationRequestCount(Map<String, Object> condition) throws LocationRequestException;

}