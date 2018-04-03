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
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.web.catering.exception.CateringContactsException;
import com.wemeCity.web.catering.mapper.CateringContactsMapper;
import com.wemeCity.web.catering.model.CateringContacts;
import com.wemeCity.web.catering.service.CateringContactsService;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.service.UserSessionService;

/**
 * CateringContactsServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-8 新建
 */
@Service
public class CateringContactsServiceImpl implements CateringContactsService {

	private Logger logger = LoggerFactory.getLogger(CateringContactsServiceImpl.class);

	@Autowired
	private UserSessionService userSessionService;

	/** 数据访问接口 */
	@Autowired
	private CateringContactsMapper cateringContactsMapper;

	/**
	 * 新增cateringContacts
	 *
	 * @param cateringContacts
	 * @return 新增的对象
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public void insertCateringContacts(CateringContacts cateringContacts) throws CateringContactsException {
		try {
			cateringContactsMapper.insertCateringContacts(cateringContacts);
		} catch (Exception e) {
			logger.error("新增CateringContacts时报错", e);
			throw new CateringContactsException("新增CateringContacts时报错", e);
		}
	}

	/**
	 * 删除cateringContacts
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public int deleteCateringContacts(long id) throws CateringContactsException {
		try {
			return this.cateringContactsMapper.deleteCateringContacts(id);
		} catch (Exception e) {
			logger.error("删除CateringContacts时报错", e);
			throw new CateringContactsException("删除CateringContacts时报错", e);
		}
	}

	/**
	 * 修改cateringContacts
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public int updateCateringContacts(Map<String, Object> condition) throws CateringContactsException {
		try {
			return this.cateringContactsMapper.updateCateringContacts(condition);
		} catch (Exception e) {
			logger.error("修改CateringContacts时报错", e);
			throw new CateringContactsException("修改CateringContacts时报错", e);
		}
	}

	/**
	 * 查询cateringContacts
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public CateringContacts readCateringContacts(long id) throws CateringContactsException {
		try {
			return this.cateringContactsMapper.readCateringContacts(id);
		} catch (Exception e) {
			logger.error("查询CateringContacts时报错", e);
			throw new CateringContactsException("查询CateringContacts时报错", e);
		}
	}

	/**
	 * 查询cateringContacts集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public List<CateringContacts> queryCateringContactsList(Map<String, Object> condition) {
		try {
			return this.cateringContactsMapper.queryCateringContactsList(condition);
		} catch (Exception e) {
			logger.error("查询CateringContacts时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringContacts集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-8 新建
	 */
	@Override
	public long queryCateringContactsCount(Map<String, Object> condition) throws CateringContactsException {
		try {
			return this.cateringContactsMapper.queryCateringContactsCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringContacts时报错", e);
			throw new CateringContactsException("查询CateringContacts时报错", e);
		}
	}

	@Override
	public BaseDTO updateCateringContacts(CateringContacts contacts) throws CateringContactsException {
		try {
			Map<String, Object> condition = new HashMap<>(15);
			UserSession userSession = userSessionService.readUserSessionByUserKey(contacts.getUserKey());
			if (userSession == null) {
				logger.warn("修改联系人失败：用户不存在或登录已失效！contacts={}", GsonUtils.toSimpleJson(contacts));
				return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
			}
			if (userSession.getUserId() != contacts.getUserId()) {
				logger.warn("修改联系人失败：用户不匹配！contacts={}", GsonUtils.toSimpleJson(contacts));
				return new BaseDTO(RequestResultEnum.USER_NOT_MATCH, null);
			}
			if (Constants.YES.equals(contacts.getDefaultFlag())) {
				condition.put("defaultFlag", Constants.NO);
				condition.put("userId", userSession.getUserId());
				cateringContactsMapper.updateCateringContacts(condition);
			}
			condition.clear();
			condition.put("id", contacts.getId());
			ConditionUtils.addStr(condition, "name", contacts.getName());
			ConditionUtils.addStr(condition, "phone", contacts.getPhone());
			ConditionUtils.addStr(condition, "cityCode", contacts.getCityCode());
			ConditionUtils.addStr(condition, "cityName", contacts.getCityName());
			ConditionUtils.addStr(condition, "districtCode", contacts.getDistrictCode());
			ConditionUtils.addStr(condition, "districtName", contacts.getDistrictName());
			ConditionUtils.addStr(condition, "address", contacts.getAddress());
			ConditionUtils.addStr(condition, "defaultFlag", contacts.getDefaultFlag());
			ConditionUtils.addStr(condition, "label", contacts.getLabel());
			ConditionUtils.addLong(condition, "modifyBy", userSession.getUserId());
			ConditionUtils.addObject(condition, "modifyTime", LocalDateTime.now());
			cateringContactsMapper.updateCateringContacts(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("查询CateringContacts时报错", e);
			throw new CateringContactsException("查询CateringContacts时报错", e);
		}
	}
}