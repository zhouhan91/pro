package com.wemeCity.web.user.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.web.user.exception.CouponException;
import com.wemeCity.web.user.model.Coupon;

/**
 * CouponService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-28 新建
 */
public interface CouponService {

	/**
	 * 新增coupon
	 *
	 * @param coupon
	 * @return 新增的对象
	 * @author 向小文 2017-9-28 新建
	 */
	void insertCoupon(Coupon coupon) throws CouponException;

	/**
	 * 删除coupon
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-28 新建
	 */
	int deleteCoupon(long id) throws CouponException;

	/**
	 * 修改coupon
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-28 新建
	 */
	int updateCoupon(Map<String, Object> condition) throws CouponException;

	/**
	 * 查询coupon
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-28 新建
	 */
	Coupon readCoupon(long id) throws CouponException;

	/**
	 * 查询coupon集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-28 新建
	 */
	List<Coupon> queryCouponList(Map<String, Object> condition);

	/**
	 * 查询coupon集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-28 新建
	 */
	long queryCouponCount(Map<String, Object> condition) throws CouponException;
	

	/**
	 * 获取用户可用代金券
	 *
	 * @param userId
	 * @param busiCode
	 * @return
	 * @throws CouponException
	 * @history 2017年9月28日 新建
	 * @auther xiaowen
	 */
	List<Coupon> queryUsableCoupons(long userId, String busiCode) throws CouponException;
}