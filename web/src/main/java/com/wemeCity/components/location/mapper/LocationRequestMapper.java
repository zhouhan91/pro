
package com.wemeCity.components.location.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.components.location.model.LocationRequest;

/**
 * LocationRequestMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-17 新建
 */
@Repository
public interface LocationRequestMapper {

	/**
	 * 新增locationRequest
	 *
	 * @param locationRequest
	 * @return 新增的对象
	 * @author 向小文 2017-9-17 新建
	 */
	void insertLocationRequest(LocationRequest locationRequest);

	/**
	 * 删除locationRequest
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-17 新建
	 */
	int deleteLocationRequest(long id);

	/**
	 * 修改locationRequest
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-17 新建
	 */
	int updateLocationRequest(Map<String, Object> condition);

	/**
	 * 查询locationRequest
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-17 新建
	 */
	LocationRequest readLocationRequest(long id);

	/**
	 * 查询locationRequest集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-17 新建
	 */
	List<LocationRequest> queryLocationRequestList(Map<String, Object> condition);

	/**
	 * 查询locationRequest集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-17 新建
	 */
	long queryLocationRequestCount(Map<String, Object> condition);

}