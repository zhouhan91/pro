package com.wemeCity.web.catering.utils;

public interface CateringConstants {

	/** 优惠方式- 满减 */
	public static final String DISCOUNT_TYPE_MANJIAN = "manjian";

	/** 优惠方式-全场X折 */
	public static final String DISCOUNT_TYPE_QUANCHANGZHEKOU = "quanchangzhekou";

	/** 新人红包 */
	public static final String DISCOUNT_TYPE_XINREN = "xinren";
	
	/** 店铺状态-新申请 */
	public static final String RESTAURANT_STATUS_NEW="10";
	/** 店铺状态-通过 */
	public static final String RESTAURANT_STATUS_AUDITED="20";
	/** 店铺状态-禁用 */
	public static final String RESTAURANT_STATUS_CANCEL="100";
	
	
	/** 订单状态-新建 */
	public static final String ORDER_STATUS_NEW="10";
	/** 订单状态-待确认 */
	public static final String ORDER_STATUS_PAID="30";
	/** 订单状态-已确认/待配送 */
	public static final String ORDER_STATUS_CONFIRMED="50";
	/** 订单状态-已配送/待结算 */
	public static final String ORDER_STATUS_DISTRIBUTED="60";
	/** 订单状态-已完成 */
	public static final String ORDER_STATUS_COMPLETE="70";
	/** 订单状态-已取消*/
	public static final String ORDER_STATUS_CANCEL="80";
}
