
package com.wemeCity.web.region.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.region.mapper.DistrictMapper;
import com.wemeCity.web.region.model.District;
import com.wemeCity.web.region.exception.DistrictException;
import com.wemeCity.web.region.service.DistrictService;
/**
 * DistrictServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-18 新建
 */
@Service
public class DistrictServiceImpl implements DistrictService{

	private Logger logger=LoggerFactory.getLogger(DistrictServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private DistrictMapper districtMapper;

	/**
	 * 新增district
	 *
	 * @param district
	 * @return 新增的对象
	 * @author 向小文 2017-9-18 新建
	 */
	@Override
	public void insertDistrict(District district) throws DistrictException{
		try{
			districtMapper.insertDistrict(district);
		}catch(Exception e){
			logger.error("新增District时报错", e);
			throw new DistrictException("新增District时报错", e);
		}
	}

	/**
	 * 删除district
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-18 新建
	 */
	@Override
	public int deleteDistrict(long id) throws DistrictException{
		try{
			return this.districtMapper.deleteDistrict(id);
		}catch(Exception e){
			logger.error("删除District时报错", e);
			throw new DistrictException("删除District时报错", e);
		}
	}

	/**
	 * 修改district
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-18 新建
	 */
	@Override
	public int updateDistrict(Map<String, Object> condition) throws DistrictException{
		try{
			return this.districtMapper.updateDistrict(condition);
		}catch(Exception e){
			logger.error("修改District时报错", e);
			throw new DistrictException("修改District时报错", e);
		}
	}

	/**
	 * 查询district
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-18 新建
	 */
	@Override
	public District readDistrict(long id) throws DistrictException{
		try{
			return this.districtMapper.readDistrict(id);
		}catch(Exception e){
			logger.error("查询District时报错", e);
			throw new DistrictException("查询District时报错", e);
		}
	}

	/**
	 * 查询district集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-18 新建
	 */
	@Override
	public List<District> queryDistrictList(Map<String, Object> condition){
		try{
			return this.districtMapper.queryDistrictList(condition);
		}catch(Exception e){
			logger.error("查询District时报错", e);
			return null;
		}
	}

	/**
	 * 查询district集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-18 新建
	 */
	@Override
	public long queryDistrictCount(Map<String, Object> condition) throws DistrictException{
		try{
			return this.districtMapper.queryDistrictCount(condition);
		}catch(Exception e){
			logger.error("查询District时报错", e);
			throw new DistrictException("查询District时报错", e);
		}
	}

}