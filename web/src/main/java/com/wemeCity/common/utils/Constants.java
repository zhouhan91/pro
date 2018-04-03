package com.wemeCity.common.utils;

public interface Constants {

	public static final String YES = "Y";

	public static final String NO = "N";

	/** 默认每页显示行数 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/** 订餐联系人显示条数 */
	public static final int CATERING_CONTACTS_NUM = 5;

	/** 支付方式-微信支付 */
	public static final String PAY_TYPE_WECHAT = "wxpay";

	/** 支付方式-支付宝支付 */
	public static final String PAY_TYPE_ALIPAY = "alipay";

	/** 支付方式-线下支付 */
	public static final String PAY_TYPE_OFFLINE = "offLine";

	/** 订单来源-移动端app */
	public static final String ORDER_SOURCE_APP = "app";

	/** 订单来源-小程序 */
	public static final String ORDER_SOURCE_PROGRAM = "program";

	/** 支付状态 - 未支付 */
	public static final String PAY_STATUS_NEW = "1";

	/** 支付状态 - 支付中 */
	public static final String PAY_STATUS_PAYING = "2";

	/** 支付状态 - 已支付 */
	public static final String PAY_STATUS_PAID = "3";

	/** 支付状态 - 支付失败 */
	public static final String PAY_STATUS_PAY_FAIL = "4";
}
