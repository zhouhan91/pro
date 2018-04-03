package com.wemeCity.components.location.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.components.location.dto.LocationRequestDTO;
import com.wemeCity.components.location.exception.LocationRequestException;
import com.wemeCity.components.location.mapper.LocationRequestMapper;
import com.wemeCity.components.location.model.LocationRequest;
import com.wemeCity.components.location.service.LocationRequestService;

/**
 * LocationRequestServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-17 新建
 */
@Service
public class LocationRequestServiceImpl implements LocationRequestService {

	private Logger logger = LoggerFactory.getLogger(LocationRequestServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private LocationRequestMapper locationRequestMapper;

	/**
	 * 新增locationRequest
	 *
	 * @param locationRequest
	 * @return 新增的对象
	 * @author 向小文 2017-9-17 新建
	 */
	@Override
	public void insertLocationRequest(LocationRequest locationRequest) throws LocationRequestException {
		try {
			locationRequestMapper.insertLocationRequest(locationRequest);
		} catch (Exception e) {
			logger.error("新增LocationRequest时报错", e);
			throw new LocationRequestException("新增LocationRequest时报错", e);
		}
	}

	/**
	 * 删除locationRequest
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-17 新建
	 */
	@Override
	public int deleteLocationRequest(long id) throws LocationRequestException {
		try {
			return this.locationRequestMapper.deleteLocationRequest(id);
		} catch (Exception e) {
			logger.error("删除LocationRequest时报错", e);
			throw new LocationRequestException("删除LocationRequest时报错", e);
		}
	}

	/**
	 * 修改locationRequest
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-17 新建
	 */
	@Override
	public int updateLocationRequest(Map<String, Object> condition) throws LocationRequestException {
		try {
			return this.locationRequestMapper.updateLocationRequest(condition);
		} catch (Exception e) {
			logger.error("修改LocationRequest时报错", e);
			throw new LocationRequestException("修改LocationRequest时报错", e);
		}
	}

	/**
	 * 查询locationRequest
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-17 新建
	 */
	@Override
	public LocationRequest readLocationRequest(long id) throws LocationRequestException {
		try {
			return this.locationRequestMapper.readLocationRequest(id);
		} catch (Exception e) {
			logger.error("查询LocationRequest时报错", e);
			throw new LocationRequestException("查询LocationRequest时报错", e);
		}
	}

	/**
	 * 查询locationRequest集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-17 新建
	 */
	@Override
	public List<LocationRequest> queryLocationRequestList(Map<String, Object> condition) {
		try {
			return this.locationRequestMapper.queryLocationRequestList(condition);
		} catch (Exception e) {
			logger.error("查询LocationRequest时报错", e);
			return null;
		}
	}

	/**
	 * 查询locationRequest集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-17 新建
	 */
	@Override
	public long queryLocationRequestCount(Map<String, Object> condition) throws LocationRequestException {
		try {
			return this.locationRequestMapper.queryLocationRequestCount(condition);
		} catch (Exception e) {
			logger.error("查询LocationRequest时报错", e);
			throw new LocationRequestException("查询LocationRequest时报错", e);
		}
	}

	/**
	 * google获取位置信息
	 *
	 * @param dto 位置基本信息，经纬度必填
	 * @param userId 无用户填0
	 * @throws LocationRequestException
	 * @history 2017年9月17日 新建
	 * @auther xiaowen
	 */
	public LocationRequest getLocationGoogle(LocationRequestDTO dto, long userId) throws LocationRequestException {
		
		
		return null;
	}

}