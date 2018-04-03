package com.wemeCity.components.wechatPay.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.common.utils.BigDecimalUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.HttpsUtils;
import com.wemeCity.components.wechatPay.dto.PayDTO;
import com.wemeCity.components.wechatPay.exception.WechatUnifiedOrderException;
import com.wemeCity.components.wechatPay.mapper.WechatUnifiedOrderMapper;
import com.wemeCity.components.wechatPay.model.WechatConfig;
import com.wemeCity.components.wechatPay.model.WechatUnifiedOrder;
import com.wemeCity.components.wechatPay.service.WechatUnifiedOrderService;
import com.wemeCity.components.wechatPay.utils.WechatPayUtils;
import com.wemeCity.web.user.model.UserSession;

/**
 * WechatUnifiedOrderServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-8 新建
 */
@Service
public class WechatUnifiedOrderServiceImpl implements WechatUnifiedOrderService {

	@Autowired
	private WechatConfig wechatConfig;

	private Logger logger = LoggerFactory.getLogger(WechatUnifiedOrderServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private WechatUnifiedOrderMapper wechatUnifiedOrderMapper;

	/**
	 * 新增wechatUnifiedOrder
	 *
	 * @param wechatUnifiedOrder
	 * @return 新增的对象
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public void insertWechatUnifiedOrder(WechatUnifiedOrder wechatUnifiedOrder) throws WechatUnifiedOrderException {
		try {
			wechatUnifiedOrderMapper.insertWechatUnifiedOrder(wechatUnifiedOrder);
		} catch (Exception e) {
			logger.error("新增WechatUnifiedOrder时报错", e);
			throw new WechatUnifiedOrderException("新增WechatUnifiedOrder时报错", e);
		}
	}

	/**
	 * 删除wechatUnifiedOrder
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public int deleteWechatUnifiedOrder(long id) throws WechatUnifiedOrderException {
		try {
			return this.wechatUnifiedOrderMapper.deleteWechatUnifiedOrder(id);
		} catch (Exception e) {
			logger.error("删除WechatUnifiedOrder时报错", e);
			throw new WechatUnifiedOrderException("删除WechatUnifiedOrder时报错", e);
		}
	}

	/**
	 * 修改wechatUnifiedOrder
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public int updateWechatUnifiedOrder(Map<String, Object> condition) throws WechatUnifiedOrderException {
		try {
			return this.wechatUnifiedOrderMapper.updateWechatUnifiedOrder(condition);
		} catch (Exception e) {
			logger.error("修改WechatUnifiedOrder时报错", e);
			throw new WechatUnifiedOrderException("修改WechatUnifiedOrder时报错", e);
		}
	}

	/**
	 * 查询wechatUnifiedOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public WechatUnifiedOrder readWechatUnifiedOrder(long id) throws WechatUnifiedOrderException {
		try {
			return this.wechatUnifiedOrderMapper.readWechatUnifiedOrder(id);
		} catch (Exception e) {
			logger.error("查询WechatUnifiedOrder时报错", e);
			throw new WechatUnifiedOrderException("查询WechatUnifiedOrder时报错", e);
		}
	}

	/**
	 * 查询wechatUnifiedOrder集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public List<WechatUnifiedOrder> queryWechatUnifiedOrderList(Map<String, Object> condition) {
		try {
			return this.wechatUnifiedOrderMapper.queryWechatUnifiedOrderList(condition);
		} catch (Exception e) {
			logger.error("查询WechatUnifiedOrder时报错", e);
			return null;
		}
	}

	/**
	 * 查询wechatUnifiedOrder集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-8 新建
	 */
	@Override
	public long queryWechatUnifiedOrderCount(Map<String, Object> condition) throws WechatUnifiedOrderException {
		try {
			return this.wechatUnifiedOrderMapper.queryWechatUnifiedOrderCount(condition);
		} catch (Exception e) {
			logger.error("查询WechatUnifiedOrder时报错", e);
			throw new WechatUnifiedOrderException("查询WechatUnifiedOrder时报错", e);
		}
	}

	@Override
	public Map<String, String> unifiedorder(PayDTO payDTO, UserSession userSession) throws WechatUnifiedOrderException {
		try {
			final String appid = Constants.ORDER_SOURCE_APP.equals(payDTO.getOrderSource()) ? wechatConfig.getSdkAppId() : wechatConfig.getProgramAppId();
			// 构建统一下单参数
			TreeMap<String, String> params = new TreeMap<>();
			params.put("appid", appid);
			params.put("mch_id", wechatConfig.getMchId());
			params.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", ""));
			params.put("sign_type", "MD5");
			params.put("body", "wemecity-" + payDTO.getOrderCode());
			params.put("attach", payDTO.getOrderCode());
			params.put("out_trade_no", payDTO.getOrderCode() + "-" + new Random().nextInt(10000));
			params.put("total_fee", BigDecimalUtils.multiply(payDTO.getAmount(), new BigDecimal("100")).intValue() + "");
			params.put("spbill_create_ip", "192.168.1.1");
			params.put("notify_url", wechatConfig.getPayNotifyUrl());
			params.put("trade_type", "JSAPI");
			params.put("openid", userSession.getOpenId());
			// 创建请求参数
			String xml = WechatPayUtils.createUnifiedorderStr(params, wechatConfig.getPaySecret());
			// post请求统一下单接口
			String body = HttpsUtils.post(wechatConfig.getUnifiedorderUrl(), null, null, new StringEntity(xml, "UTF-8"));
			logger.debug("wechat unifiedorder request success, body={}", body);
			// 解析统一下单结果
			TreeMap<String, String> result = WechatPayUtils.xmlToTreeMap(body);
			// 构造wechatUnifiedOrder对象
			WechatUnifiedOrder wechatUnifiedOrder = new WechatUnifiedOrder(result, payDTO.getOrderCode(), userSession);
			wechatUnifiedOrderMapper.insertWechatUnifiedOrder(wechatUnifiedOrder);
			logger.debug("wechat unifiedorder success, wechatUnifiedOrder={}", GsonUtils.toSimpleJson(wechatUnifiedOrder));
			// 创建微信支付参数返回
			Map<String, String> payParams = WechatPayUtils.createWechatPayParams(wechatUnifiedOrder.getPrepayId(), wechatConfig.getPaySecret(), appid);
			payParams.put("orderCode", payDTO.getOrderCode());
			logger.debug("create wechat pay params success: payParams={}", GsonUtils.toSimpleJson(payParams));
			return payParams;
		} catch (Exception e) {
			logger.error("微信下单报错!payDTO={}, userSession={}", new Object[] { GsonUtils.toSimpleJson(payDTO), GsonUtils.toSimpleJson(userSession) }, e);
			throw new WechatUnifiedOrderException("微信下单报错!", e);
		}
	}
}