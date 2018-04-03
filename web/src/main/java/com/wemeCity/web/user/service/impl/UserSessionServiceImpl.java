package com.wemeCity.web.user.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.web.user.mapper.UserSessionMapper;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.exception.UserSessionException;
import com.wemeCity.web.user.service.UserSessionService;

/**
 * UserSessionServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
@Service
public class UserSessionServiceImpl implements UserSessionService {

	private Logger logger = LoggerFactory.getLogger(UserSessionServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private UserSessionMapper userSessionMapper;

	@Autowired
	@Qualifier("yamlproperties")
	private Properties properties;

	/**
	 * 新增userSession
	 *
	 * @param userSession
	 * @return 新增的对象
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public void insertUserSession(UserSession userSession) throws UserSessionException {
		try {
			userSessionMapper.insertUserSession(userSession);
		} catch (Exception e) {
			logger.error("新增UserSession时报错", e);
			throw new UserSessionException("新增UserSession时报错", e);
		}
	}

	/**
	 * 删除userSession
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public int deleteUserSession(long id) throws UserSessionException {
		try {
			return this.userSessionMapper.deleteUserSession(id);
		} catch (Exception e) {
			logger.error("删除UserSession时报错", e);
			throw new UserSessionException("删除UserSession时报错", e);
		}
	}

	/**
	 * 修改userSession
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public int updateUserSession(Map<String, Object> condition) throws UserSessionException {
		try {
			return this.userSessionMapper.updateUserSession(condition);
		} catch (Exception e) {
			logger.error("修改UserSession时报错", e);
			throw new UserSessionException("修改UserSession时报错", e);
		}
	}

	/**
	 * 查询userSession
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public UserSession readUserSession(long id) throws UserSessionException {
		try {
			return this.userSessionMapper.readUserSession(id);
		} catch (Exception e) {
			logger.error("查询UserSession时报错", e);
			throw new UserSessionException("查询UserSession时报错", e);
		}
	}

	/**
	 * 查询userSession集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public List<UserSession> queryUserSessionList(Map<String, Object> condition) {
		try {
			return this.userSessionMapper.queryUserSessionList(condition);
		} catch (Exception e) {
			logger.error("查询UserSession时报错", e);
			return null;
		}
	}

	/**
	 * 查询userSession集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public long queryUserSessionCount(Map<String, Object> condition) throws UserSessionException {
		try {
			return this.userSessionMapper.queryUserSessionCount(condition);
		} catch (Exception e) {
			logger.error("查询UserSession时报错", e);
			throw new UserSessionException("查询UserSession时报错", e);
		}
	}

	@Override
	public UserSession readUserSessionByUserKey(String userKey) throws UserSessionException {
		try {
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("userKey", userKey);
			List<UserSession> lstSession = userSessionMapper.queryUserSessionList(condition);
			if (CollectionUtils.isEmpty(lstSession)) {
				logger.warn("未找到userSession，userKey={}", userKey);
				return null;
			}
			UserSession userSession = lstSession.get(0);
			if (userSession.getCreateTime() != null) {
				Duration duration = Duration.between(LocalDateTime.now(), userSession.getCreateTime());
				// 登录有效期15天 =60*24*15=21600分钟
				String sessionTimeMinutes = properties.getProperty("system.user.sessionTimeMinutes");
				if (duration.toMinutes() < Long.valueOf(sessionTimeMinutes)) {
					return userSession;
				}
			}
			return null;
		} catch (Exception e) {
			logger.error("根据userKey获取userSession出错！", e);
			return null;
		}
	}

	@Override
	public BaseDTO checkSession(String userKey) throws UserSessionException {
		UserSession userSession = this.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("登录已失效或未找到userSession，userKey={}", userKey);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

}