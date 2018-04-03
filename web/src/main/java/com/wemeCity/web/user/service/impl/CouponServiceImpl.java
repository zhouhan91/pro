package com.wemeCity.web.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.common.utils.Constants;
import com.wemeCity.web.user.enums.CouponStatusEnum;
import com.wemeCity.web.user.exception.CouponException;
import com.wemeCity.web.user.mapper.CouponMapper;
import com.wemeCity.web.user.model.Coupon;
import com.wemeCity.web.user.service.CouponService;

/**
 * CouponServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-28 新建
 */
@Service
public class CouponServiceImpl implements CouponService {

	private Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CouponMapper couponMapper;

	/**
	 * 新增coupon
	 *
	 * @param coupon
	 * @return 新增的对象
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public void insertCoupon(Coupon coupon) throws CouponException {
		try {
			couponMapper.insertCoupon(coupon);
		} catch (Exception e) {
			logger.error("新增Coupon时报错", e);
			throw new CouponException("新增Coupon时报错", e);
		}
	}

	/**
	 * 删除coupon
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public int deleteCoupon(long id) throws CouponException {
		try {
			return this.couponMapper.deleteCoupon(id);
		} catch (Exception e) {
			logger.error("删除Coupon时报错", e);
			throw new CouponException("删除Coupon时报错", e);
		}
	}

	/**
	 * 修改coupon
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public int updateCoupon(Map<String, Object> condition) throws CouponException {
		try {
			return this.couponMapper.updateCoupon(condition);
		} catch (Exception e) {
			logger.error("修改Coupon时报错", e);
			throw new CouponException("修改Coupon时报错", e);
		}
	}

	/**
	 * 查询coupon
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public Coupon readCoupon(long id) throws CouponException {
		try {
			return this.couponMapper.readCoupon(id);
		} catch (Exception e) {
			logger.error("查询Coupon时报错", e);
			throw new CouponException("查询Coupon时报错", e);
		}
	}

	/**
	 * 查询coupon集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public List<Coupon> queryCouponList(Map<String, Object> condition) {
		try {
			return this.couponMapper.queryCouponList(condition);
		} catch (Exception e) {
			logger.error("查询Coupon时报错", e);
			return null;
		}
	}

	/**
	 * 查询coupon集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public long queryCouponCount(Map<String, Object> condition) throws CouponException {
		try {
			return this.couponMapper.queryCouponCount(condition);
		} catch (Exception e) {
			logger.error("查询Coupon时报错", e);
			throw new CouponException("查询Coupon时报错", e);
		}
	}

	@Override
	public List<Coupon> queryUsableCoupons(long userId, String busiCode) throws CouponException {
		Map<String, Object> condition = new HashMap<>();
		condition.put("userId", userId);
		condition.put("busiCode", busiCode);
		condition.put("status", CouponStatusEnum.USABLE.getKey());
		condition.put("isDeleted", Constants.NO);
		return couponMapper.queryCouponList(condition);
	}

}