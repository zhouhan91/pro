
package com.wemeCity.web.region.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.region.mapper.ProvinceMapper;
import com.wemeCity.web.region.model.Province;
import com.wemeCity.web.region.exception.ProvinceException;
import com.wemeCity.web.region.service.ProvinceService;
/**
 * ProvinceServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Service
public class ProvinceServiceImpl implements ProvinceService{

	private Logger logger=LoggerFactory.getLogger(ProvinceServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private ProvinceMapper provinceMapper;

	/**
	 * 新增province
	 *
	 * @param province
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public void insertProvince(Province province) throws ProvinceException{
		try{
			provinceMapper.insertProvince(province);
		}catch(Exception e){
			logger.error("新增Province时报错", e);
			throw new ProvinceException("新增Province时报错", e);
		}
	}

	/**
	 * 删除province
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int deleteProvince(long id) throws ProvinceException{
		try{
			return this.provinceMapper.deleteProvince(id);
		}catch(Exception e){
			logger.error("删除Province时报错", e);
			throw new ProvinceException("删除Province时报错", e);
		}
	}

	/**
	 * 修改province
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int updateProvince(Map<String, Object> condition) throws ProvinceException{
		try{
			return this.provinceMapper.updateProvince(condition);
		}catch(Exception e){
			logger.error("修改Province时报错", e);
			throw new ProvinceException("修改Province时报错", e);
		}
	}

	/**
	 * 查询province
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public Province readProvince(long id) throws ProvinceException{
		try{
			return this.provinceMapper.readProvince(id);
		}catch(Exception e){
			logger.error("查询Province时报错", e);
			throw new ProvinceException("查询Province时报错", e);
		}
	}

	/**
	 * 查询province集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public List<Province> queryProvinceList(Map<String, Object> condition){
		try{
			return this.provinceMapper.queryProvinceList(condition);
		}catch(Exception e){
			logger.error("查询Province时报错", e);
			return null;
		}
	}

	/**
	 * 查询province集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public long queryProvinceCount(Map<String, Object> condition) throws ProvinceException{
		try{
			return this.provinceMapper.queryProvinceCount(condition);
		}catch(Exception e){
			logger.error("查询Province时报错", e);
			throw new ProvinceException("查询Province时报错", e);
		}
	}

}