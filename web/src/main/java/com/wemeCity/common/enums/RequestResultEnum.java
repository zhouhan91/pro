package com.wemeCity.common.enums;

public enum RequestResultEnum {

	/** 处理成功 */
	SUCCESS("999999", "成功！"),
	/** 处理失败 */
	FAILURE("000000", "未知错误！"),
	/** 非空参数验证失败 */
	NOT_NULL_PARAM_ERROR("000001", "请填写完整资料！"),
	/** 订单来源错误 */
	ORDER_SOURCE_ERROR("000002", "订单来源错误！"),
	/** 用户不匹配！ */
	USER_NOT_MATCH("000003", "用户不匹配！"),

	/**
	 * 租房系列
	 */
	/** 房源未找到 */
	COMMUNITY_NOT_FOUND("000101", "房源未找到"),
	/** 房型未找到 */
	ROOM_NOT_FOUND("000102", "房型未找到"),
	
	/**
	 * 新闻板块
	 */
	/** 新闻信息未找到 */
	NEWS_NOT_FOUND("000201", "新闻信息未找到"),
	/** 评论内容不允许超过500字 */
	NEWS_COMMENT_TOO_LONG("000202", "评论内容不允许超过500字"),
	
	
	/**
	 * 美食板块
	 */
	/** 店铺不存在！*/
	RESTAURANT_NOT_FOUND("000301", "店铺不存在！"),
	/** 非法的支付方式 */
	INVALID_PAY_TYPE("000302", "非法的支付方式！"),
	/** 参数错误：商品与数量不匹配！ */
	INVALID_GOODS_COUNT("000303", "参数错误：商品与数量不匹配！"),
	/** 商品不存在！ */
	GOODS_NOT_FOUND("000304", "商品不存在！"),
	/** 配送点不存在！ */
	LOCATION_NOT_FOUND("000305", "配送点不存在！"),
	/** 联系人不存在！ */
	CONTACTS_NOT_FOUND("000306", "联系人不存在！"),
	/** 用户名已存在！ */
	USER_NAME_REPEAT("000307", "用户名已存在！"),
	/** 密码必须大于6位！ */
	PASSWORD_TOO_SHORT("000308", "密码必须大于6位！"),
	/** 用户不存在！ */
	CATERING_MANAGER_NOT_FOUND("000309", "用户不存在！"),
	/** 用户不存在！ */
	WRONG_CATERING_MANAGER_PASSWORD("000310", "用户名或密码错误！"),
	/** 用户不匹配！ */
	RESTAURANT_MANAGER_NOT_MATCH("000311", "用户不匹配！"),
	/** 商品分类不存在！ */
	GOODS_CATEGORY_NOT_FOUND("000312", "商品分类不存在！"),
	/** 商品分类不属于该店铺！ */
	GOODS_CATEGORY_NOT_MATCH("000312", "商品分类不属于该店铺！"),
	/** 订单不存在！ */
	CATERING_ORDER_NOT_FOUND("000313", "订单不存在！"),
	/** 订单状态异常！ */
	WRONG_CATERING_ORDER_STATUS("000314", "订单状态异常！"),
	/** 配送员不存在！ */
	CATERING_COURIER_NOT_FOUND("000315", "配送员不存在！"),
	/** 只允许评论一次！ */
	CATERING_ORDER_COMMENT_ONLY_ONCE("000316", "只允许评论一次！"),
	
	/**
	 * 个人中心端
	 */
	/** UserSession未找到！ */
	USER_SESSION_NOT_FOUND("000501", "用户信息未找到！"),
	/** 登录已失效！ */
	SESSION_TIME_OUT("000502", "用户信息不存在或登录已过期！"),
	/** 登录失败：从微信获取SessionKey失败！ */
	GET_SESSION_KEY_FAIL("000503", "登录失败：从微信获取SessionKey失败！"),
	/** 优惠券未找到 */
	COUPON_NOT_FOUND("000504", "优惠券未找到"),
	/** 优惠券用户不匹配 */
	COUPON_NOT_MATCH("000505", "非法优惠券"),
	
	
	
	
	/**
	 * 短信端1001
	 */
	/** 短信业务编码错误 */
	WRONG_SMS_BUSI_CODE("100101", "短信业务编码错误！"),
	/** 短信图片验证码错误 */
	WRONG_SMS_IMAGE_CODE("101002", "图片验证码错误！"),
	
	/***文件上传段**/
	/** 文件写入失败 */
	FILE_WRITE_FAIL("100201", "文件上传失败！"),
	
	/**
	 * 支付 
	 */
	/** 支付方式错误 */
	WRONG_PAY_TYPE("100301", "支付方式只允许为：wxpay/alipay")
	;
	
	private String key;

	private String description;

	private RequestResultEnum(String key, String description) {
		this.key = key;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}
}
