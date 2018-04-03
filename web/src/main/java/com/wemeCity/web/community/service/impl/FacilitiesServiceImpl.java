package com.wemeCity.web.community.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.web.community.exception.FacilitiesException;
import com.wemeCity.web.community.mapper.FacilitiesMapper;
import com.wemeCity.web.community.model.Facilities;
import com.wemeCity.web.community.service.FacilitiesService;

/**
 * FacilitiesServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Service
public class FacilitiesServiceImpl implements FacilitiesService {

	private Logger logger = LoggerFactory.getLogger(FacilitiesServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private FacilitiesMapper facilitiesMapper;

	/**
	 * 新增facilities
	 *
	 * @param facilities
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public void insertFacilities(Facilities facilities) throws FacilitiesException {
		try {
			facilitiesMapper.insertFacilities(facilities);
		} catch (Exception e) {
			logger.error("新增Facilities时报错", e);
			throw new FacilitiesException("新增Facilities时报错", e);
		}
	}

	/**
	 * 删除facilities
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int deleteFacilities(long id) throws FacilitiesException {
		try {
			return this.facilitiesMapper.deleteFacilities(id);
		} catch (Exception e) {
			logger.error("删除Facilities时报错", e);
			throw new FacilitiesException("删除Facilities时报错", e);
		}
	}

	/**
	 * 修改facilities
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int updateFacilities(Map<String, Object> condition) throws FacilitiesException {
		try {
			return this.facilitiesMapper.updateFacilities(condition);
		} catch (Exception e) {
			logger.error("修改Facilities时报错", e);
			throw new FacilitiesException("修改Facilities时报错", e);
		}
	}

	/**
	 * 查询facilities
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public Facilities readFacilities(long id) throws FacilitiesException {
		try {
			return this.facilitiesMapper.readFacilities(id);
		} catch (Exception e) {
			logger.error("查询Facilities时报错", e);
			throw new FacilitiesException("查询Facilities时报错", e);
		}
	}

	/**
	 * 查询facilities集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public List<Facilities> queryFacilitiesList(Map<String, Object> condition) {
		try {
			return this.facilitiesMapper.queryFacilitiesList(condition);
		} catch (Exception e) {
			logger.error("查询Facilities时报错", e);
			return null;
		}
	}

	/**
	 * 查询facilities集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public long queryFacilitiesCount(Map<String, Object> condition) throws FacilitiesException {
		try {
			return this.facilitiesMapper.queryFacilitiesCount(condition);
		} catch (Exception e) {
			logger.error("查询Facilities时报错", e);
			throw new FacilitiesException("查询Facilities时报错", e);
		}
	}

	@Override
	public List<Facilities> queryFacilitiesList(String idStr) throws FacilitiesException {
		if (StringUtils.isEmpty(idStr) || idStr.length() < 3) {
			return null;
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("isDeleted", Constants.NO);
		condition.put("arrId", idStr.substring(1, idStr.length()).split(","));
		condition.put("sortColumn", "sort_num");
		condition.put("sortType", "asc");
		return this.facilitiesMapper.queryFacilitiesList(condition);
	}

}