
package com.wemeCity.components.wechatPay.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.components.wechatPay.mapper.WechatCallbackMapper;
import com.wemeCity.components.wechatPay.model.WechatCallback;
import com.wemeCity.components.wechatPay.exception.WechatCallbackException;
import com.wemeCity.components.wechatPay.service.WechatCallbackService;
/**
 * WechatCallbackServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-8 新建
 */
@Service
public class WechatCallbackServiceImpl implements WechatCallbackService{

	private Logger logger=LoggerFactory.getLogger(WechatCallbackServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private WechatCallbackMapper wechatCallbackMapper;

	/**
	 * 新增wechatCallback
	 *
	 * @param wechatCallback
	 * @return 新增的对象
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public void insertWechatCallback(WechatCallback wechatCallback){
		try{
			wechatCallbackMapper.insertWechatCallback(wechatCallback);
		}catch(Exception e){
			logger.error("新增WechatCallback时报错", e);
		}
	}

	/**
	 * 删除wechatCallback
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public int deleteWechatCallback(long id) throws WechatCallbackException{
		try{
			return this.wechatCallbackMapper.deleteWechatCallback(id);
		}catch(Exception e){
			logger.error("删除WechatCallback时报错", e);
			throw new WechatCallbackException("删除WechatCallback时报错", e);
		}
	}

	/**
	 * 修改wechatCallback
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public int updateWechatCallback(Map<String, Object> condition) throws WechatCallbackException{
		try{
			return this.wechatCallbackMapper.updateWechatCallback(condition);
		}catch(Exception e){
			logger.error("修改WechatCallback时报错", e);
			throw new WechatCallbackException("修改WechatCallback时报错", e);
		}
	}

	/**
	 * 查询wechatCallback
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public WechatCallback readWechatCallback(long id) throws WechatCallbackException{
		try{
			return this.wechatCallbackMapper.readWechatCallback(id);
		}catch(Exception e){
			logger.error("查询WechatCallback时报错", e);
			throw new WechatCallbackException("查询WechatCallback时报错", e);
		}
	}

	/**
	 * 查询wechatCallback集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public List<WechatCallback> queryWechatCallbackList(Map<String, Object> condition){
		try{
			return this.wechatCallbackMapper.queryWechatCallbackList(condition);
		}catch(Exception e){
			logger.error("查询WechatCallback时报错", e);
			return null;
		}
	}

	/**
	 * 查询wechatCallback集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public long queryWechatCallbackCount(Map<String, Object> condition) throws WechatCallbackException{
		try{
			return this.wechatCallbackMapper.queryWechatCallbackCount(condition);
		}catch(Exception e){
			logger.error("查询WechatCallback时报错", e);
			throw new WechatCallbackException("查询WechatCallback时报错", e);
		}
	}

}