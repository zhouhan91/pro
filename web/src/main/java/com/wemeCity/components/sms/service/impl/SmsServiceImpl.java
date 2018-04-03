package com.wemeCity.components.sms.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.common.service.impl.CommonThreadPool;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.components.sms.exception.SmsException;
import com.wemeCity.components.sms.mapper.SmsMapper;
import com.wemeCity.components.sms.model.Sms;
import com.wemeCity.components.sms.service.SmsService;

/**
 * SmsServiceImpl AppService类
 *
 * @author 
 * @since JDK1.8
 * @history 2017-6-6 新建
 */
@Service
public class SmsServiceImpl implements SmsService {

	private Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private SmsMapper smsMapper;

	public SmsMapper getSmsDAO() {
		return this.smsMapper;
	}

	public void setSmsDAO(SmsMapper smsMapper) {
		this.smsMapper = smsMapper;
	}

	/**
	 * 新增sms
	 *
	 * @param sms
	 * @return 新增的对象
	 * @author  2017-6-6 新建
	 */
	public void insertSms(Sms sms) throws SmsException {
		try {
			this.smsMapper.insertSms(sms);
		} catch (Exception e) {
			logger.error("新增Sms时报错", e);
			throw new SmsException("新增Sms时报错", e);
		}
	}

	/**
	 * 删除sms
	 *
	 * @param id 主键
	 * @return
	 * @author  2017-6-6 新建
	 */
	public int deleteSms(long id) throws SmsException {
		try {
			return this.smsMapper.deleteSms(id);
		} catch (Exception e) {
			logger.error("删除Sms时报错", e);
			throw new SmsException("删除Sms时报错", e);
		}
	}

	/**
	 * 修改sms
	 *
	 * @param condition
	 * @return
	 * @author  2017-6-6 新建
	 */
	public int updateSms(Map<String, Object> condition) throws SmsException {
		try {
			return this.smsMapper.updateSms(condition);
		} catch (Exception e) {
			logger.error("修改Sms时报错", e);
			throw new SmsException("修改Sms时报错", e);
		}
	}

	/**
	 * 查询sms
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author  2017-6-6 新建
	 */
	public Sms readSms(long id) throws SmsException {
		try {
			return this.smsMapper.readSms(id);
		} catch (Exception e) {
			logger.error("查询Sms时报错", e);
			throw new SmsException("查询Sms时报错", e);
		}
	}

	/**
	 * 查询sms集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author  2017-6-6 新建
	 */
	public List<Sms> querySmsList(Map<String, Object> condition) {
		try {
			return this.smsMapper.querySmsList(condition);
		} catch (Exception e) {
			logger.error("查询Sms时报错", e);
			return null;
		}
	}

	/**
	 * 查询sms集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author  2017-6-6 新建
	 */
	public long querySmsCount(Map<String, Object> condition) throws SmsException {
		try {
			return this.smsMapper.querySmsCount(condition);
		} catch (Exception e) {
			logger.error("查询Sms时报错", e);
			throw new SmsException("查询Sms时报错", e);
		}
	}

	public void sendSms(String busiCode, String content, String reciver) throws SmsException {
		logger.debug("sendSMS params:[busiCode={},content={},reciver={}]", new Object[] { busiCode, content, reciver });
		try {
			Sms sms = new Sms();
			sms.setBusiCode(busiCode);
			sms.setResult(Constants.YES);
			sms.setIsDeleted(Constants.NO);
			sms.setContent(content);
			sms.setReciver(reciver);
			this.smsMapper.insertSms(sms);
			CommonThreadPool threadPool=CommonThreadPool.getInstance();
			threadPool.execute(new SmsSendThread(sms));
			logger.debug("sendSMS result:sms={}", GsonUtils.toSimpleJson(sms));
		} catch (Exception e) {
			logger.error("发送短信报错", e);
			throw new SmsException("发送短信报错", e);
		}
	}

}