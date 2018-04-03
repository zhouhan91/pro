package com.wemeCity.components.sms.mapper;

import java.util.List;
import java.util.Map;

import com.wemeCity.components.sms.model.Sms;

/**
 * SmsMapper数据库访问类
 *
 * @author 
 * @since JDK1.8
 * @history 2017-6-6 新建
 */
public interface SmsMapper {

	/**
	 * 新增sms
	 *
	 * @param sms
	 * @return 新增的对象
	 * @author  2017-6-6 新建
	 */
	void insertSms(Sms sms);

	/**
	 * 删除sms
	 *
	 * @param id 主键
	 * @return 
	 * @author  2017-6-6 新建
	 */
	int deleteSms(long id);

	/**
	 * 修改sms
	 *
	 * @param condition
	 * @return 
	 * @author  2017-6-6 新建
	 */
	int updateSms(Map<String, Object> condition);

	/**
	 * 查询sms
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author  2017-6-6 新建
	 */
	Sms readSms(long id);

	/**
	 * 查询sms集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author  2017-6-6 新建
	 */
	List<Sms> querySmsList(Map<String, Object> condition);

	/**
	 * 查询sms集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author  2017-6-6 新建
	 */
	long querySmsCount(Map<String, Object> condition);

}