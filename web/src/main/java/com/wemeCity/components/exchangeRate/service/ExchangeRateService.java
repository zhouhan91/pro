
package com.wemeCity.components.exchangeRate.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.components.exchangeRate.exception.ExchangeRateException;
import com.wemeCity.components.exchangeRate.model.ExchangeRate;

/**
 * ExchangeRateService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-16 新建
 */
public interface ExchangeRateService {

	/**
	 * 新增exchangeRate
	 *
	 * @param exchangeRate
	 * @return 新增的对象
	 * @author 向小文 2017-12-16 新建
	 */
	void insertExchangeRate(ExchangeRate exchangeRate) throws ExchangeRateException;

	/**
	 * 删除exchangeRate
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-16 新建
	 */
	int deleteExchangeRate(long id) throws ExchangeRateException;

	/**
	 * 修改exchangeRate
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-16 新建
	 */
	int updateExchangeRate(Map<String, Object> condition) throws ExchangeRateException;

	/**
	 * 查询exchangeRate
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-16 新建
	 */
	ExchangeRate readExchangeRate(long id) throws ExchangeRateException;

	/**
	 * 查询exchangeRate集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-16 新建
	 */
	List<ExchangeRate> queryExchangeRateList(Map<String, Object> condition) ;

	/**
	 * 查询exchangeRate集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-16 新建
	 */
	long queryExchangeRateCount(Map<String, Object> condition) throws ExchangeRateException;

	/**
	 * 查询汇率
	 *
	 * @return
	 * @throws ExchangeRateException
	 * @history 2017年12月16日 新建
	 * @auther xiaowen
	 */
	BaseDTO getExchangeRate() throws ExchangeRateException;
}