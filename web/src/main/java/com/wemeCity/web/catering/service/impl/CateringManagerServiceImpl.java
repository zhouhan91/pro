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
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.web.catering.exception.CateringManagerException;
import com.wemeCity.web.catering.mapper.CateringManagerMapper;
import com.wemeCity.web.catering.model.CateringManager;
import com.wemeCity.web.catering.model.CateringRestaurant;
import com.wemeCity.web.catering.service.CateringDiscountService;
import com.wemeCity.web.catering.service.CateringManagerService;
import com.wemeCity.web.catering.service.CateringRestaurantService;
import com.wemeCity.web.catering.utils.CateringConstants;

/**
 * CateringManagerServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-23 新建
 */
@Service
public class CateringManagerServiceImpl implements CateringManagerService {

	private Logger logger = LoggerFactory.getLogger(CateringManagerServiceImpl.class);

	@Autowired
	private CateringRestaurantService cateringRestaurantService;
	
	@Autowired
	private CateringDiscountService cateringDiscountService;

	/** 数据访问接口 */
	@Autowired
	private CateringManagerMapper cateringManagerMapper;

	/**
	 * 新增cateringManager
	 *
	 * @param cateringManager
	 * @return 新增的对象
	 * @author 向小文 2017-12-23 新建
	 */
	@Override
	public void insertCateringManager(CateringManager cateringManager) throws CateringManagerException {
		try {
			cateringManagerMapper.insertCateringManager(cateringManager);
		} catch (Exception e) {
			logger.error("新增CateringManager时报错", e);
			throw new CateringManagerException("新增CateringManager时报错", e);
		}
	}

	/**
	 * 删除cateringManager
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-23 新建
	 */
	@Override
	public int deleteCateringManager(long id) throws CateringManagerException {
		try {
			return this.cateringManagerMapper.deleteCateringManager(id);
		} catch (Exception e) {
			logger.error("删除CateringManager时报错", e);
			throw new CateringManagerException("删除CateringManager时报错", e);
		}
	}

	/**
	 * 修改cateringManager
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-23 新建
	 */
	@Override
	public int updateCateringManager(Map<String, Object> condition) throws CateringManagerException {
		try {
			return this.cateringManagerMapper.updateCateringManager(condition);
		} catch (Exception e) {
			logger.error("修改CateringManager时报错", e);
			throw new CateringManagerException("修改CateringManager时报错", e);
		}
	}

	/**
	 * 查询cateringManager
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-23 新建
	 */
	@Override
	public CateringManager readCateringManager(long id) throws CateringManagerException {
		try {
			return this.cateringManagerMapper.readCateringManager(id);
		} catch (Exception e) {
			logger.error("查询CateringManager时报错", e);
			throw new CateringManagerException("查询CateringManager时报错", e);
		}
	}

	/**
	 * 查询cateringManager集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-23 新建
	 */
	@Override
	public List<CateringManager> queryCateringManagerList(Map<String, Object> condition) {
		try {
			return this.cateringManagerMapper.queryCateringManagerList(condition);
		} catch (Exception e) {
			logger.error("查询CateringManager时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringManager集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-23 新建
	 */
	@Override
	public long queryCateringManagerCount(Map<String, Object> condition) throws CateringManagerException {
		try {
			return this.cateringManagerMapper.queryCateringManagerCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringManager时报错", e);
			throw new CateringManagerException("查询CateringManager时报错", e);
		}
	}

	@Override
	public BaseDTO register(CateringManager manager) throws CateringManagerException {
		try {
			// 插入用户数据
			manager.setCreateTime(LocalDateTime.now());
			manager.setStatus(Constants.YES);
			manager.setPassword(StringUtils.md5(manager.getPassword()));
			cateringManagerMapper.insertCateringManager(manager);
			// 生成店铺对象
			CateringRestaurant restaurant = new CateringRestaurant();
			restaurant.setName(manager.getRestaurantName());
			restaurant.setCountryCode(manager.getCountryCode());
			restaurant.setCountryName(manager.getCountryName());
			restaurant.setCityCode(manager.getCityCode());
			restaurant.setCityName(manager.getCityName());
			restaurant.setDistrictCode(manager.getDistrictCode());
			restaurant.setDistrictName(manager.getDistrictName());
			restaurant.setAddress(manager.getAddress());
			restaurant.setStatus(CateringConstants.RESTAURANT_STATUS_NEW);
			restaurant.setIsDeleted(Constants.NO);
			restaurant.setCreateBy(manager.getId());
			restaurant.setCreateTime(LocalDateTime.now());
			restaurant.setManagerId(manager.getId());
			cateringRestaurantService.insertCateringRestaurant(restaurant);
			// 生成三个未启用的优惠对象
			cateringDiscountService.genRestaurantDisCount(restaurant.getId(), manager.getId());
			restaurant.setManager(manager);
			manager.setPassword("");
			return new BaseDTO(RequestResultEnum.SUCCESS, restaurant);
		} catch (Exception e) {
			logger.error("注册店铺失败：服务器内部错误！manager={}", GsonUtils.toSimpleJson(manager), e);
			throw new CateringManagerException("注册店铺失败：服务器内部错误！", e);
		}
	}

	@Override
	public CateringManager readManagerByUserName(String userName) throws CateringManagerException {
		try {
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("userName", userName);
			Page<CateringManager> page = PageHelper.startPage(1, 1).doSelectPage(() -> cateringManagerMapper.queryCateringManagerList(condition));
			if (CollectionUtils.isEmpty(page)) {
				return null;
			}
			return page.get(0);
		} catch (Exception e) {
			logger.error("根据用户名读取店铺管理员信息失败：服务器内部错误！userName={}", userName, e);
			throw new CateringManagerException("根据用户名读取店铺管理员信息失败：服务器内部错误！", e);
		}
	}
}