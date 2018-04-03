package com.wemeCity.components.wechatPay.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wemeCity.common.dto.PayCallback;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.StringUtils;

/**
 * WechatCallback实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-8 新建
 */
public class WechatCallback extends PayCallback {

	private Logger logger = LoggerFactory.getLogger(WechatCallback.class);

	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (return_code,VARCHAR(32), null)返回状态码 */
	private String returnCode;

	/** (return_msg,VARCHAR(256), null)返回信息 */
	private String returnMsg;

	/** (app_id,VARCHAR(32), null)应用ID */
	private String appId;

	/** (mch_id,VARCHAR(32), null)商户号 */
	private String mchId;

	/** (device_info,VARCHAR(32), null)设备号 */
	private String deviceInfo;

	/** (nonce_str,VARCHAR(32), null)随机字符串 */
	private String nonceStr;

	/** (sign,VARCHAR(32), null)签名 */
	private String sign;

	/** (sign_type,VARCHAR(32), null)签名类型 */
	private String signType;

	/** (result_code,VARCHAR(16), null)业务结果 */
	private String resultCode;

	/** (err_code,VARCHAR(32), null)错误代码 */
	private String errCode;

	/** (err_code_des,VARCHAR(256), null)错误代码描述 */
	private String errCodeDes;

	/** (open_id,VARCHAR(256), null)用户标识 */
	private String openId;

	/** (is_subscribe,VARCHAR(1), null)用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 */
	private String isSubscribe;

	/** (trade_type,VARCHAR(16), null)交易类型 */
	private String tradeType;

	/** (bank_type,VARCHAR(32), null)银行类型，采用字符串类型的银行标识，银行类型见微信银行列表 */
	private String bankType;

	/** (total_fee,INT(11), null)订单总金额，单位为分 */
	private int totalFee;

	/** (settlement_total_fee,INT(11), null)应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。 */
	private int settlementTotalFee;

	/** (fee_type,VARCHAR(8), null)货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型 */
	private String feeType;

	/** (cash_fee,INT(11), null)现金支付金额订单现金支付金额，详见支付金额 */
	private int cashFee;

	/** (cash_fee_type,VARCHAR(16), null)货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型 */
	private String cashFeeType;

	/** (coupon_fee,INT(11), null)代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额，详见支付金额 */
	private int couponFee;

	/** (coupon_count,INT(11), null)代金券或立减优惠使用数量 */
	private int couponCount;

	/** (coupon_type_n,INT(11), null)仅在使用了免充值代金券时有返回（取值：CASH、NO_CASH）。n为下标,从0开始编号，举例：coupon_type_0 */
	private int couponTypeN;

	/** (coupon_id_n,VARCHAR(32), null)代金券或立减优惠ID,n为下标，从0开始编号 */
	private String couponIdN;

	/** (coupon_fee_n,INT(11), null)单个代金券或立减优惠支付金额,n为下标，从0开始编号 */
	private int couponFeeN;

	/** (transaction_id,VARCHAR(32), null)微信支付订单号 */
	private String transactionId;

	/** (out_trade_no,VARCHAR(32), null)商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。 */
	private String outTradeNo;

	/** (attach,VARCHAR(256), null)商家数据包，原样返回 */
	private String attach;

	/** (time_end,VARCHAR(32), null)支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则 */
	private String timeEnd;

	/** (request_body,VARCHAR(3072), null)请求体 */
	private String requestBody;

	/** (create_time,DATETIME, null)创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	public WechatCallback() {
	}

	public WechatCallback(Map<String, String> resultXML, String requestBody) {
		try {
			this.payType = Constants.PAY_TYPE_WECHAT;
			this.returnCode = resultXML.get("return_code");
			this.returnMsg = resultXML.get("return_msg");
			this.appId = resultXML.get("appid");
			this.mchId = resultXML.get("mch_id");
			this.deviceInfo = resultXML.get("device_info");
			this.nonceStr = resultXML.get("nonce_str");
			this.sign = resultXML.get("sign");
			this.signType = resultXML.get("sign_type");
			this.resultCode = resultXML.get("result_code");
			this.errCode = resultXML.get("err_code");
			this.errCodeDes = resultXML.get("err_code_des");
			this.openId = resultXML.get("openid");
			this.isSubscribe = resultXML.get("is_subscribe");
			this.tradeType = resultXML.get("trade_type");
			this.bankType = resultXML.get("bank_type");
			this.feeType = resultXML.get("fee_type");
			this.cashFeeType = resultXML.get("cash_fee_type");
			this.couponIdN = resultXML.get("coupon_id_$n");
			this.transactionId = resultXML.get("transaction_id");
			this.outTradeNo = resultXML.get("out_trade_no");
			if (!StringUtils.isEmpty(this.outTradeNo)) {
				this.orderCode = this.outTradeNo.substring(0, this.outTradeNo.lastIndexOf("-"));
			}
			this.attach = resultXML.get("attach");
			this.timeEnd = resultXML.get("time_end");
			this.totalFee = StringUtils.isEmpty(resultXML.get("total_fee")) ? 0 : Integer.valueOf(resultXML.get("total_fee"));
			this.settlementTotalFee = StringUtils.isEmpty(resultXML.get("settlement_total_fee")) ? 0 : Integer.valueOf(resultXML.get("settlement_total_fee"));
			this.cashFee = StringUtils.isEmpty(resultXML.get("cash_fee")) ? 0 : Integer.valueOf(resultXML.get("cash_fee"));
			this.couponFee = StringUtils.isEmpty(resultXML.get("coupon_fee")) ? 0 : Integer.valueOf(resultXML.get("coupon_fee"));
			this.couponCount = StringUtils.isEmpty(resultXML.get("coupon_count")) ? 0 : Integer.valueOf(resultXML.get("coupon_count"));
			this.couponTypeN = StringUtils.isEmpty(resultXML.get("coupon_type_$n")) ? 0 : Integer.valueOf(resultXML.get("coupon_type_$n"));
			this.couponFeeN = StringUtils.isEmpty(resultXML.get("coupon_fee_$n")) ? 0 : Integer.valueOf(resultXML.get("coupon_fee_$n"));
		} catch (Exception e) {
			logger.error("初始化微信回调参数失败, requestBody={}, e={}", requestBody, e);
		}
		this.requestBody = requestBody;
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

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignType() {
		return this.signType;
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

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getIsSubscribe() {
		return this.isSubscribe;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeType() {
		return this.tradeType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankType() {
		return this.bankType;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public int getTotalFee() {
		return this.totalFee;
	}

	public void setSettlementTotalFee(int settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}

	public int getSettlementTotalFee() {
		return this.settlementTotalFee;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getFeeType() {
		return this.feeType;
	}

	public void setCashFee(int cashFee) {
		this.cashFee = cashFee;
	}

	public int getCashFee() {
		return this.cashFee;
	}

	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public String getCashFeeType() {
		return this.cashFeeType;
	}

	public void setCouponFee(int couponFee) {
		this.couponFee = couponFee;
	}

	public int getCouponFee() {
		return this.couponFee;
	}

	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}

	public int getCouponCount() {
		return this.couponCount;
	}

	public void setCouponTypeN(int couponTypeN) {
		this.couponTypeN = couponTypeN;
	}

	public int getCouponTypeN() {
		return this.couponTypeN;
	}

	public void setCouponIdN(String couponIdN) {
		this.couponIdN = couponIdN;
	}

	public String getCouponIdN() {
		return this.couponIdN;
	}

	public void setCouponFeeN(int couponFeeN) {
		this.couponFeeN = couponFeeN;
	}

	public int getCouponFeeN() {
		return this.couponFeeN;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getOutTradeNo() {
		return this.outTradeNo;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getAttach() {
		return this.attach;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getTimeEnd() {
		return this.timeEnd;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getRequestBody() {
		return this.requestBody;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

}