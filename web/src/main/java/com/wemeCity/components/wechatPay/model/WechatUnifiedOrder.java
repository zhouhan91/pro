package com.wemeCity.components.wechatPay.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wemeCity.web.user.model.UserSession;

/**
 * WechatUnifiedOrder实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-8 新建
 */
public class WechatUnifiedOrder {
	/** (id,BIGINT(20),not null) */
	private long id;

	/** (return_code,VARCHAR(16), null)返回状态码 */
	private String returnCode;

	/** (return_msg,VARCHAR(128), null)返回信息 */
	private String returnMsg;

	/** (app_id,VARCHAR(32), null)小程序ID */
	private String appId;

	/** (mch_id,VARCHAR(32), null)商户号 */
	private String mchId;

	/** (device_info,VARCHAR(32), null)设备号 */
	private String deviceInfo;

	/** (nonce_str,VARCHAR(32), null)随机字符串 */
	private String nonceStr;

	/** (sign,VARCHAR(32), null)签名 */
	private String sign;

	/** (result_code,VARCHAR(16), null)业务结果 */
	private String resultCode;

	/** (err_code,VARCHAR(32), null)错误代码 */
	private String errCode;

	/** (err_code_des,VARCHAR(128), null)错误代码描述 */
	private String errCodeDes;

	/** (trade_type,VARCHAR(16), null)交易类型 */
	private String tradeType;

	/** (prepay_id,VARCHAR(64), null)预支付交易会话标识 */
	private String prepayId;

	/** (out_trade_no,VARCHAR(32), null)商户订单号 */
	private String outTradeNo;

	/** (open_id,VARCHAR(128), null)用户标识 */
	private String openId;

	/** (user_id,BIGINT(20), null)用户id */
	private long userId;

	/** (create_time,DATETIME, null)创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	public WechatUnifiedOrder() {
	}

	public WechatUnifiedOrder(Map<String, String> result, String outTradeNo, UserSession userSession) {
		this.resultCode = result.get("return_code");
		this.returnMsg = result.get("return_msg");
		this.appId = result.get("appid");
		this.mchId = result.get("mch_id");
		this.deviceInfo = result.get("device_info");
		this.nonceStr = result.get("nonce_str");
		this.sign = result.get("sign");
		this.errCode = result.get("err_code");
		this.errCodeDes = result.get("err_code_des");
		this.tradeType = result.get("trade_type");
		this.prepayId = result.get("prepay_id");
		this.outTradeNo = outTradeNo;
		this.openId = userSession.getOpenId();
		this.userId = userSession.getUserId();
		this.createTime = LocalDateTime.now();
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnCode() {
		return this.returnCode;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getReturnMsg() {
		return this.returnMsg;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchId() {
		return this.mchId;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getDeviceInfo() {
		return this.deviceInfo;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getNonceStr() {
		return this.nonceStr;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign() {
		return this.sign;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultCode() {
		return this.resultCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

	public String getErrCodeDes() {
		return this.errCodeDes;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeType() {
		return this.tradeType;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getPrepayId() {
		return this.prepayId;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getOutTradeNo() {
		return this.outTradeNo;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

}