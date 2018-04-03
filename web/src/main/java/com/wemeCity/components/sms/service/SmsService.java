package com.wemeCity.components.sms.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.components.sms.exception.SmsException;
import com.wemeCity.components.sms.model.Sms;

/**
 * SmsService Service接口
 *
 * @author 
 * @since JDK1.8
 * @history 2017-6-6 新建
 */
public interface SmsService {

	/**
	 * 新增sms
	 *
	 * @param sms
	 * @return 新增的对象
	 * @author  2017-6-6 新建
	 */
	void insertSms(Sms sms) throws SmsException;

	/**
	 * 删除sms
	 *
	 * @param id 主键
	 * @return 
	 * @author  2017-6-6 新建
	 */
	int deleteSms(long id) throws SmsException;

	/**
	 * 修改sms
	 *
	 * @param condition
	 * @return 
	 * @author  2017-6-6 新建
	 */
	int updateSms(Map<String, Object> condition) throws SmsException;

	/**
	 * 查询sms
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author  2017-6-6 新建
	 */
	Sms readSms(long id) throws SmsException;

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
	long querySmsCount(Map<String, Object> condition) throws SmsException;
	
	/**
	 * 发送短信
	 *
	 * @param busiCode 业务编码
	 * @param content 内容
	 * @param reciver 接收手机号码
	 * @throws SmsException
	 * @Author Xiang xiaowen 2017年6月6日 新建
	 */
	void sendSms(String busiCode, String content, String reciver) throws SmsException;

}