package com.wemeCity.web.catering.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.EHCacheUtils;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.web.catering.dto.RestaurantUpdateDTO;
import com.wemeCity.web.catering.exception.CateringRestaurantException;
import com.wemeCity.web.catering.mapper.CateringRestaurantMapper;
import com.wemeCity.web.catering.model.CateringRestaurant;
import com.wemeCity.web.catering.service.CateringRestaurantService;

/**
 * CateringRestaurantServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Service
public class CateringRestaurantServiceImpl implements CateringRestaurantService {

	private Logger logger = LoggerFactory.getLogger(CateringRestaurantServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CateringRestaurantMapper cateringRestaurantMapper;

	/**
	 * 新增cateringRestaurant
	 *
	 * @param cateringRestaurant
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public void insertCateringRestaurant(CateringRestaurant cateringRestaurant) throws CateringRestaurantException {
		try {
			cateringRestaurantMapper.insertCateringRestaurant(cateringRestaurant);
		} catch (Exception e) {
			logger.error("新增CateringRestaurant时报错", e);
			throw new CateringRestaurantException("新增CateringRestaurant时报错", e);
		}
	}

	/**
	 * 删除cateringRestaurant
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int deleteCateringRestaurant(long id) throws CateringRestaurantException {
		try {
			return this.cateringRestaurantMapper.deleteCateringRestaurant(id);
		} catch (Exception e) {
			logger.error("删除CateringRestaurant时报错", e);
			throw new CateringRestaurantException("删除CateringRestaurant时报错", e);
		}
	}

	/**
	 * 修改cateringRestaurant
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int updateCateringRestaurant(Map<String, Object> condition) throws CateringRestaurantException {
		try {
			return this.cateringRestaurantMapper.updateCateringRestaurant(condition);
		} catch (Exception e) {
			logger.error("修改CateringRestaurant时报错", e);
			throw new CateringRestaurantException("修改CateringRestaurant时报错", e);
		}
	}

	/**
	 * 查询cateringRestaurant
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public CateringRestaurant readCateringRestaurant(long id) throws CateringRestaurantException {
		try {
			return this.cateringRestaurantMapper.readCateringRestaurant(id);
		} catch (Exception e) {
			logger.error("查询CateringRestaurant时报错", e);
			throw new CateringRestaurantException("查询CateringRestaurant时报错", e);
		}
	}

	/**
	 * 查询cateringRestaurant集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public List<CateringRestaurant> queryCateringRestaurantList(Map<String, Object> condition) {
		try {
			return this.cateringRestaurantMapper.queryCateringRestaurantList(condition);
		} catch (Exception e) {
			logger.error("查询CateringRestaurant时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringRestaurant集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public long queryCateringRestaurantCount(Map<String, Object> condition) throws CateringRestaurantException {
		try {
			return this.cateringRestaurantMapper.queryCateringRestaurantCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringRestaurant时报错", e);
			throw new CateringRestaurantException("查询CateringRestaurant时报错", e);
		}
	}

	@Override
	public CateringRestaurant readRestaurantByManagerId(long managerId) throws CateringRestaurantException {
		try {
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("managerId", managerId);
			Page<CateringRestaurant> page = PageHelper.startPage(1, 1).doSelectPage(() -> cateringRestaurantMapper.queryCateringRestaurantList(condition));
			if (CollectionUtils.isEmpty(page)) {
				return null;
			}
			return page.get(0);
		} catch (Exception e) {
			logger.error("根据managerid读取店铺信息失败：服务器内部错误！managerId={}", managerId, e);
			throw new CateringRestaurantException("根据managerid读取店铺信息失败：服务器内部错误！", e);
		}
	}

	@Override
	public BaseDTO updateRestaurantInfo(RestaurantUpdateDTO updateDTO) throws CateringRestaurantException {
		try {
			Map<String, Object> condition = new HashMap<>(30);
			condition.put("id", updateDTO.getId());
			condition.put("modifyBy", updateDTO.getManagerId());
			condition.put("modifyTime", LocalDateTime.now());
			ConditionUtils.addStr(condition, "name", updateDTO.getName());
			ConditionUtils.addStr(condition, "localName", updateDTO.getLocalName());
			ConditionUtils.addLong(condition, "parentCategoryId", updateDTO.getParentCategoryId());
			ConditionUtils.addStr(condition, "parentCategoryCode", updateDTO.getParentCategoryCode());
			ConditionUtils.addLong(condition, "categoryId", updateDTO.getCategoryId());
			ConditionUtils.addStr(condition, "categoryCode", updateDTO.getCategoryCode());
			ConditionUtils.addStr(condition, "coverPicture", updateDTO.getCoverPicture());
			ConditionUtils.addStr(condition, "countryCode", updateDTO.getCountryCode());
			ConditionUtils.addStr(condition, "countryName", updateDTO.getCountryName());
			ConditionUtils.addStr(condition, "cityCode", updateDTO.getCityCode());
			ConditionUtils.addStr(condition, "cityName", updateDTO.getCityName());
			ConditionUtils.addStr(condition, "districtCode", updateDTO.getDistrictCode());
			ConditionUtils.addStr(condition, "districtName", updateDTO.getDistrictName());
			ConditionUtils.addStr(condition, "address", updateDTO.getAddress());
			ConditionUtils.addStr(condition, "managerPhone", updateDTO.getManagerPhone());
			ConditionUtils.addStr(condition, "phone", updateDTO.getPhone());
			ConditionUtils.addStr(condition, "email", updateDTO.getEmail());
			ConditionUtils.addStr(condition, "notice", updateDTO.getNotice());
			ConditionUtils.addStr(condition, "serviceTimeCode", updateDTO.getServiceTimeCode());
			ConditionUtils.addStr(condition, "serviceTimeDesc", updateDTO.getServiceTimeDesc());
			ConditionUtils.addDecimal(condition, "amountLimit", updateDTO.getAmountLimit());
			ConditionUtils.addDecimal(condition, "distributionAmount", updateDTO.getDistributionAmount());
			ConditionUtils.addObject(condition, "longitude", updateDTO.getLongitude());
			ConditionUtils.addObject(condition, "latitude", updateDTO.getLatitude());
			ConditionUtils.addStr(condition, "openFlag", updateDTO.getOpenFlag());
			ConditionUtils.addStr(condition, "pauseFlag", updateDTO.getPauseFlag());
			ConditionUtils.addStr(condition, "distributionRemark", updateDTO.getDistributionRemark());
			cateringRestaurantMapper.updateCateringRestaurant(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("修改店铺信息失败：服务器内部错误！updateDTO={}", GsonUtils.toSimpleJson(updateDTO), e);
			throw new CateringRestaurantException("修改店铺信息失败：服务器内部错误！", e);
		}
	}

	@Override
	public CateringRestaurant getRestaurantInfo(long id) {
		try {
			Object cateringRestaurantInCache = EHCacheUtils.get("cateringRestaurantInfo", "" + id);
			if (cateringRestaurantInCache == null) {
				CateringRestaurant restaurant = cateringRestaurantMapper.readCateringRestaurant(id);
				if (restaurant != null) {
					EHCacheUtils.put("cateringRestaurantInfo", "" + id, restaurant);
				}
				return restaurant;
			}
			return (CateringRestaurant) cateringRestaurantInCache;
		} catch (Exception e) {
			logger.warn("获取店铺信息失败：服务器内部错误！id={}", id, e);
			return null;
		}
	}

}