
package com.wemeCity.web.region.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.region.mapper.CountryMapper;
import com.wemeCity.web.region.model.Country;
import com.wemeCity.web.region.exception.CountryException;
import com.wemeCity.web.region.service.CountryService;
/**
 * CountryServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Service
public class CountryServiceImpl implements CountryService{

	private Logger logger=LoggerFactory.getLogger(CountryServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CountryMapper countryMapper;

	/**
	 * 新增country
	 *
	 * @param country
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public void insertCountry(Country country) throws CountryException{
		try{
			countryMapper.insertCountry(country);
		}catch(Exception e){
			logger.error("新增Country时报错", e);
			throw new CountryException("新增Country时报错", e);
		}
	}

	/**
	 * 删除country
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int deleteCountry(long id) throws CountryException{
		try{
			return this.countryMapper.deleteCountry(id);
		}catch(Exception e){
			logger.error("删除Country时报错", e);
			throw new CountryException("删除Country时报错", e);
		}
	}

	/**
	 * 修改country
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int updateCountry(Map<String, Object> condition) throws CountryException{
		try{
			return this.countryMapper.updateCountry(condition);
		}catch(Exception e){
			logger.error("修改Country时报错", e);
			throw new CountryException("修改Country时报错", e);
		}
	}

	/**
	 * 查询country
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public Country readCountry(long id) throws CountryException{
		try{
			return this.countryMapper.readCountry(id);
		}catch(Exception e){
			logger.error("查询Country时报错", e);
			throw new CountryException("查询Country时报错", e);
		}
	}

	/**
	 * 查询country集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public List<Country> queryCountryList(Map<String, Object> condition){
		try{
			return this.countryMapper.queryCountryList(condition);
		}catch(Exception e){
			logger.error("查询Country时报错", e);
			return null;
		}
	}

	/**
	 * 查询country集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public long queryCountryCount(Map<String, Object> condition) throws CountryException{
		try{
			return this.countryMapper.queryCountryCount(condition);
		}catch(Exception e){
			logger.error("查询Country时报错", e);
			throw new CountryException("查询Country时报错", e);
		}
	}

}