
package com.wemeCity.components.wechatPay.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.components.wechatPay.dto.PayDTO;
import com.wemeCity.components.wechatPay.exception.WechatUnifiedOrderException;
import com.wemeCity.components.wechatPay.model.WechatUnifiedOrder;
import com.wemeCity.web.user.model.UserSession;

/**
 * WechatUnifiedOrderService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-8 新建
 */
public interface WechatUnifiedOrderService {

	/**
	 * 新增wechatUnifiedOrder
	 *
	 * @param wechatUnifiedOrder
	 * @return 新增的对象
	 * @author 向小文 2017-10-8 新建
	 */
	void insertWechatUnifiedOrder(WechatUnifiedOrder wechatUnifiedOrder) throws WechatUnifiedOrderException;

	/**
	 * 删除wechatUnifiedOrder
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-8 新建
	 */
	int deleteWechatUnifiedOrder(long id) throws WechatUnifiedOrderException;

	/**
	 * 修改wechatUnifiedOrder
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-8 新建
	 */
	int updateWechatUnifiedOrder(Map<String, Object> condition) throws WechatUnifiedOrderException;

	/**
	 * 查询wechatUnifiedOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-8 新建
	 */
	WechatUnifiedOrder readWechatUnifiedOrder(long id) throws WechatUnifiedOrderException;

	/**
	 * 查询wechatUnifiedOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-8 新建
	 */
	List<WechatUnifiedOrder> queryWechatUnifiedOrderList(Map<String, Object> condition) ;

	/**
	 * 查询wechatUnifiedOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-8 新建
	 */
	long queryWechatUnifiedOrderCount(Map<String, Object> condition) throws WechatUnifiedOrderException;
	
	/**
	 * 下单
	 *
	 * @param orderCode 业务编码
	 * @param amount 金额
	 * @param userSession 用户标识
	 * @return
	 * @throws WechatUnifiedOrderException
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	Map<String, String> unifiedorder(PayDTO payDTO, UserSession userSession) throws WechatUnifiedOrderException;

}