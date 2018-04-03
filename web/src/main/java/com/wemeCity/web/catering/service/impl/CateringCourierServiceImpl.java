package com.wemeCity.web.catering.service.impl;

import java.time.LocalDateTime;
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
import com.wemeCity.web.catering.dto.CourierSaveDTO;
import com.wemeCity.web.catering.exception.CateringCourierException;
import com.wemeCity.web.catering.mapper.CateringCourierMapper;
import com.wemeCity.web.catering.model.CateringCourier;
import com.wemeCity.web.catering.model.CateringManager;
import com.wemeCity.web.catering.model.CateringRestaurant;
import com.wemeCity.web.catering.service.CateringCourierService;
import com.wemeCity.web.catering.service.CateringManagerService;
import com.wemeCity.web.catering.service.CateringRestaurantService;

/**
 * CateringCourierServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-25 新建
 */
@Service
public class CateringCourierServiceImpl implements CateringCourierService {

	private Logger logger = LoggerFactory.getLogger(CateringCourierServiceImpl.class);

	@Autowired
	private CateringRestaurantService cateringRestaurantService;

	@Autowired
	private CateringManagerService cateringManagerService;

	/** 数据访问接口 */
	@Autowired
	private CateringCourierMapper cateringCourierMapper;

	/**
	 * 新增cateringCourier
	 *
	 * @param cateringCourier
	 * @return 新增的对象
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public void insertCateringCourier(CateringCourier cateringCourier) throws CateringCourierException {
		try {
			cateringCourierMapper.insertCateringCourier(cateringCourier);
		} catch (Exception e) {
			logger.error("新增CateringCourier时报错", e);
			throw new CateringCourierException("新增CateringCourier时报错", e);
		}
	}

	/**
	 * 删除cateringCourier
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public int deleteCateringCourier(long id) throws CateringCourierException {
		try {
			return this.cateringCourierMapper.deleteCateringCourier(id);
		} catch (Exception e) {
			logger.error("删除CateringCourier时报错", e);
			throw new CateringCourierException("删除CateringCourier时报错", e);
		}
	}

	/**
	 * 修改cateringCourier
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public int updateCateringCourier(Map<String, Object> condition) throws CateringCourierException {
		try {
			return this.cateringCourierMapper.updateCateringCourier(condition);
		} catch (Exception e) {
			logger.error("修改CateringCourier时报错", e);
			throw new CateringCourierException("修改CateringCourier时报错", e);
		}
	}

	/**
	 * 查询cateringCourier
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public CateringCourier readCateringCourier(long id) throws CateringCourierException {
		try {
			return this.cateringCourierMapper.readCateringCourier(id);
		} catch (Exception e) {
			logger.error("查询CateringCourier时报错", e);
			throw new CateringCourierException("查询CateringCourier时报错", e);
		}
	}

	/**
	 * 查询cateringCourier集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public List<CateringCourier> queryCateringCourierList(Map<String, Object> condition) {
		try {
			return this.cateringCourierMapper.queryCateringCourierList(condition);
		} catch (Exception e) {
			logger.error("查询CateringCourier时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringCourier集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-25 新建
	 */
	@Override
	public long queryCateringCourierCount(Map<String, Object> condition) throws CateringCourierException {
		try {
			return this.cateringCourierMapper.queryCateringCourierCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringCourier时报错", e);
			throw new CateringCourierException("查询CateringCourier时报错", e);
		}
	}

	@Override
	public BaseDTO saveCateringCourierList(CourierSaveDTO dto) throws CateringCourierException {
		try {
			CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(dto.getRestaurantId());
			if (restaurant == null || Constants.YES.equals(restaurant.getIsDeleted())) {
				logger.warn("保存配送员信息失败：店铺不存在！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND, null);
			}
			CateringManager manager = cateringManagerService.readCateringManager(dto.getManagerId());
			if (manager == null || Constants.YES.equals(manager.getIsDeleted())) {
				logger.warn("保存配送员信息失败：管理员不存在！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.CATERING_MANAGER_NOT_FOUND, null);
			}
			if (restaurant.getManagerId() != manager.getId()) {
				logger.warn("保存配送员信息失败：用户不匹配！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			// 保存
			List<CateringCourier> lstCourier = dto.getLstCourier();
			for (CateringCourier courier : lstCourier) {
				courier.setRestaurantId(restaurant.getId());
				courier.setIsDeleted(Constants.NO);
				courier.setCreateBy(manager.getId());
				courier.setCreateTime(LocalDateTime.now());
				cateringCourierMapper.insertCateringCourier(courier);
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("保存配送员信息失败：服务器内部错误！dto={}", GsonUtils.toSimpleJson(dto), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}