package com.wemeCity.web.catering.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.web.catering.exception.CateringDiscountException;
import com.wemeCity.web.catering.mapper.CateringDiscountMapper;
import com.wemeCity.web.catering.model.CateringDiscount;
import com.wemeCity.web.catering.service.CateringDiscountService;
import com.wemeCity.web.catering.utils.CateringConstants;

/**
 * CateringDiscountServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Service
public class CateringDiscountServiceImpl implements CateringDiscountService {

	private Logger logger = LoggerFactory.getLogger(CateringDiscountServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CateringDiscountMapper cateringDiscountMapper;

	/**
	 * 新增cateringDiscount
	 *
	 * @param cateringDiscount
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public void insertCateringDiscount(CateringDiscount cateringDiscount) throws CateringDiscountException {
		try {
			cateringDiscountMapper.insertCateringDiscount(cateringDiscount);
		} catch (Exception e) {
			logger.error("新增CateringDiscount时报错", e);
			throw new CateringDiscountException("新增CateringDiscount时报错", e);
		}
	}

	/**
	 * 删除cateringDiscount
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int deleteCateringDiscount(long id) throws CateringDiscountException {
		try {
			return this.cateringDiscountMapper.deleteCateringDiscount(id);
		} catch (Exception e) {
			logger.error("删除CateringDiscount时报错", e);
			throw new CateringDiscountException("删除CateringDiscount时报错", e);
		}
	}

	/**
	 * 修改cateringDiscount
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int updateCateringDiscount(Map<String, Object> condition) throws CateringDiscountException {
		try {
			return this.cateringDiscountMapper.updateCateringDiscount(condition);
		} catch (Exception e) {
			logger.error("修改CateringDiscount时报错", e);
			throw new CateringDiscountException("修改CateringDiscount时报错", e);
		}
	}

	/**
	 * 查询cateringDiscount
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public CateringDiscount readCateringDiscount(long id) throws CateringDiscountException {
		try {
			return this.cateringDiscountMapper.readCateringDiscount(id);
		} catch (Exception e) {
			logger.error("查询CateringDiscount时报错", e);
			throw new CateringDiscountException("查询CateringDiscount时报错", e);
		}
	}

	/**
	 * 查询cateringDiscount集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public List<CateringDiscount> queryCateringDiscountList(Map<String, Object> condition) {
		try {
			return this.cateringDiscountMapper.queryCateringDiscountList(condition);
		} catch (Exception e) {
			logger.error("查询CateringDiscount时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringDiscount集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public long queryCateringDiscountCount(Map<String, Object> condition) throws CateringDiscountException {
		try {
			return this.cateringDiscountMapper.queryCateringDiscountCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringDiscount时报错", e);
			throw new CateringDiscountException("查询CateringDiscount时报错", e);
		}
	}

	@Override
	public List<CateringDiscount> queryRestaurantDisCount(long restaurantId) {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("restaurantId", restaurantId);
			condition.put("status", Constants.YES);
			condition.put("isDeleted", Constants.NO);
			return cateringDiscountMapper.queryCateringDiscountList(condition);
		} catch (Exception e) {
			logger.error("查询店铺优惠时出错！restaurantId={}", restaurantId, e);
			return null;
		}
	}

	@Override
	public BaseDTO updateDiscountInfo(@RequestBody List<CateringDiscount> lstCateringDiscount) throws CateringDiscountException {
		try {
			Map<String, Object> condition = new HashMap<>(10);
			for (CateringDiscount discount : lstCateringDiscount) {
				condition.put("id", discount.getId());
				condition.put("target", discount.getTarget());
				condition.put("discount", discount.getDiscount());
				condition.put("status", discount.getStatus());
				condition.put("modifyBy", discount.getModifyBy());
				condition.put("modifyTime", LocalDateTime.now());
				cateringDiscountMapper.updateCateringDiscount(condition);
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("修改店铺优惠出错：服务器内部错误！lstCateringDiscount={}", GsonUtils.toSimpleJson(lstCateringDiscount), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@Override
	public List<CateringDiscount> genRestaurantDisCount(long restaurantId, long managerId) throws CateringDiscountException {
		try {
			// 满减
			CateringDiscount manjian = new CateringDiscount();
			manjian.setRestaurantId(restaurantId);
			manjian.setType(CateringConstants.DISCOUNT_TYPE_MANJIAN);
			manjian.setTarget(BigDecimal.ZERO);
			manjian.setDiscount(BigDecimal.ZERO);
			manjian.setStatus(Constants.NO);
			manjian.setIsDeleted(Constants.NO);
			manjian.setCreateBy(managerId);
			manjian.setCreateTime(LocalDateTime.now());
			cateringDiscountMapper.insertCateringDiscount(manjian);
			// 全场X折
			CateringDiscount quanchangzhekou = new CateringDiscount();
			quanchangzhekou.setRestaurantId(restaurantId);
			quanchangzhekou.setType(CateringConstants.DISCOUNT_TYPE_QUANCHANGZHEKOU);
			quanchangzhekou.setTarget(BigDecimal.ZERO);
			quanchangzhekou.setDiscount(new BigDecimal("1"));
			quanchangzhekou.setStatus(Constants.NO);
			quanchangzhekou.setIsDeleted(Constants.NO);
			quanchangzhekou.setCreateBy(managerId);
			quanchangzhekou.setCreateTime(LocalDateTime.now());
			cateringDiscountMapper.insertCateringDiscount(quanchangzhekou);
			// 新人立减
			CateringDiscount xinren = new CateringDiscount();
			xinren.setRestaurantId(restaurantId);
			xinren.setType(CateringConstants.DISCOUNT_TYPE_XINREN);
			xinren.setTarget(BigDecimal.ZERO);
			xinren.setDiscount(BigDecimal.ZERO);
			xinren.setStatus(Constants.NO);
			xinren.setIsDeleted(Constants.NO);
			xinren.setCreateBy(managerId);
			xinren.setCreateTime(LocalDateTime.now());
			cateringDiscountMapper.insertCateringDiscount(xinren);
			// 返回
			List<CateringDiscount> allDiscount = new ArrayList<>(3);
			allDiscount.add(manjian);
			allDiscount.add(quanchangzhekou);
			allDiscount.add(xinren);
			return allDiscount;
		} catch (Exception e) {
			logger.error("生成店铺优惠出错：服务器内部错误！restaurantId={}", restaurantId, e);
			throw new CateringDiscountException("生成店铺优惠出错：服务器内部错误！", e);
		}
	}

}