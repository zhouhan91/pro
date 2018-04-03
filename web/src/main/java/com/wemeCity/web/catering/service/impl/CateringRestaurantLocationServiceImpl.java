package com.wemeCity.web.catering.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.web.catering.dto.RestaurantLocationSaveDTO;
import com.wemeCity.web.catering.exception.CateringRestaurantLocationException;
import com.wemeCity.web.catering.mapper.CateringRestaurantLocationMapper;
import com.wemeCity.web.catering.model.CateringManager;
import com.wemeCity.web.catering.model.CateringRestaurant;
import com.wemeCity.web.catering.model.CateringRestaurantLocation;
import com.wemeCity.web.catering.service.CateringManagerService;
import com.wemeCity.web.catering.service.CateringRestaurantLocationService;
import com.wemeCity.web.catering.service.CateringRestaurantService;

/**
 * CateringRestaurantLocationServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-8 新建
 */
@Service
public class CateringRestaurantLocationServiceImpl implements CateringRestaurantLocationService {

	private Logger logger = LoggerFactory.getLogger(CateringRestaurantLocationServiceImpl.class);

	@Autowired
	private CateringRestaurantService cateringRestaurantService;

	@Autowired
	private CateringManagerService cateringManagerService;

	/** 数据访问接口 */
	@Autowired
	private CateringRestaurantLocationMapper cateringRestaurantLocationMapper;

	/**
	 * 新增cateringRestaurantLocation
	 *
	 * @param cateringRestaurantLocation
	 * @return 新增的对象
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public void insertCateringRestaurantLocation(CateringRestaurantLocation cateringRestaurantLocation) throws CateringRestaurantLocationException {
		try {
			cateringRestaurantLocationMapper.insertCateringRestaurantLocation(cateringRestaurantLocation);
		} catch (Exception e) {
			logger.error("新增CateringRestaurantLocation时报错", e);
			throw new CateringRestaurantLocationException("新增CateringRestaurantLocation时报错", e);
		}
	}

	/**
	 * 删除cateringRestaurantLocation
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public int deleteCateringRestaurantLocation(long id) throws CateringRestaurantLocationException {
		try {
			return this.cateringRestaurantLocationMapper.deleteCateringRestaurantLocation(id);
		} catch (Exception e) {
			logger.error("删除CateringRestaurantLocation时报错", e);
			throw new CateringRestaurantLocationException("删除CateringRestaurantLocation时报错", e);
		}
	}

	/**
	 * 修改cateringRestaurantLocation
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public int updateCateringRestaurantLocation(Map<String, Object> condition) throws CateringRestaurantLocationException {
		try {
			return this.cateringRestaurantLocationMapper.updateCateringRestaurantLocation(condition);
		} catch (Exception e) {
			logger.error("修改CateringRestaurantLocation时报错", e);
			throw new CateringRestaurantLocationException("修改CateringRestaurantLocation时报错", e);
		}
	}

	/**
	 * 查询cateringRestaurantLocation
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public CateringRestaurantLocation readCateringRestaurantLocation(long id) throws CateringRestaurantLocationException {
		try {
			return this.cateringRestaurantLocationMapper.readCateringRestaurantLocation(id);
		} catch (Exception e) {
			logger.error("查询CateringRestaurantLocation时报错", e);
			throw new CateringRestaurantLocationException("查询CateringRestaurantLocation时报错", e);
		}
	}

	/**
	 * 查询cateringRestaurantLocation集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public List<CateringRestaurantLocation> queryCateringRestaurantLocationList(Map<String, Object> condition) {
		try {
			return this.cateringRestaurantLocationMapper.queryCateringRestaurantLocationList(condition);
		} catch (Exception e) {
			logger.error("查询CateringRestaurantLocation时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringRestaurantLocation集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public long queryCateringRestaurantLocationCount(Map<String, Object> condition) throws CateringRestaurantLocationException {
		try {
			return this.cateringRestaurantLocationMapper.queryCateringRestaurantLocationCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringRestaurantLocation时报错", e);
			throw new CateringRestaurantLocationException("查询CateringRestaurantLocation时报错", e);
		}
	}

	@Override
	public BaseDTO saveRestaurantLocationList(RestaurantLocationSaveDTO dto) throws CateringRestaurantLocationException {
		try {
			CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(dto.getRestaurantId());
			if (restaurant == null || Constants.YES.equals(restaurant.getIsDeleted())) {
				logger.warn("保存店铺配送信息失败：店铺不存在！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND, null);
			}
			CateringManager manager = cateringManagerService.readCateringManager(dto.getManagerId());
			if (manager == null || Constants.YES.equals(manager.getIsDeleted())) {
				logger.warn("保存店铺配送信息失败：管理员不存在！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.CATERING_MANAGER_NOT_FOUND, null);
			}
			if (restaurant.getManagerId() != manager.getId()) {
				logger.warn("保存店铺信息失败：用户不匹配！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			// 修改配送费和起送金额
			Map<String, Object> restaurantCondition = new HashMap<>(8);
			restaurantCondition.put("id", restaurant.getId());
			restaurantCondition.put("amountLimit", dto.getAmountLimit());
			restaurantCondition.put("distributionAmount", dto.getDistributionAmount());
			restaurantCondition.put("modifyBy", manager.getId());
			restaurantCondition.put("modifyTime", LocalDateTime.now());
			cateringRestaurantService.updateCateringRestaurant(restaurantCondition);

			// 保存配送信息
			List<CateringRestaurantLocation> lstLocation = dto.getLstLocation();
			Map<String, Object> locationCondition = new HashMap<>(10);
			for (CateringRestaurantLocation location : lstLocation) {
				if (location.getId() <= 0) {
					// 没有id就新增
					location.setRestaurantId(restaurant.getId());
					location.setCityCode(restaurant.getCityCode());
					location.setCityName(restaurant.getCityName());
					location.setDistrictCode(restaurant.getDistrictCode());
					location.setDistrictName(restaurant.getDistrictName());
					location.setIsDeleted(Constants.NO);
					location.setCreateBy(manager.getId());
					location.setCreateTime(LocalDateTime.now());
					cateringRestaurantLocationMapper.insertCateringRestaurantLocation(location);
				} else {
					// 有id就修改
					locationCondition.put("id", location.getId());
					locationCondition.put("address", location.getAddress());
					locationCondition.put("modifyBy", manager.getId());
					locationCondition.put("modifyTime", LocalDateTime.now());
					cateringRestaurantLocationMapper.updateCateringRestaurantLocation(locationCondition);
				}
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("保存店铺配送信息失败：服务器内部错误！dto={}", GsonUtils.toSimpleJson(dto), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}