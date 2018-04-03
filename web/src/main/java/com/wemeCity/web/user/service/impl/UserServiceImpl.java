package com.wemeCity.web.user.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.web.user.dto.WechatLoginSuccessDTO;
import com.wemeCity.web.user.exception.UserException;
import com.wemeCity.web.user.mapper.UserMapper;
import com.wemeCity.web.user.model.User;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.service.UserService;
import com.wemeCity.web.user.service.UserSessionService;

/**
 * UserServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserSessionService userSessionService;

	/**
	 * 新增user
	 *
	 * @param user
	 * @return 新增的对象
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public void insertUser(User user) throws UserException {
		try {
			userMapper.insertUser(user);
		} catch (Exception e) {
			logger.error("新增User时报错", e);
			throw new UserException("新增User时报错", e);
		}
	}

	/**
	 * 删除user
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public int deleteUser(long id) throws UserException {
		try {
			return this.userMapper.deleteUser(id);
		} catch (Exception e) {
			logger.error("删除User时报错", e);
			throw new UserException("删除User时报错", e);
		}
	}

	/**
	 * 修改user
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public int updateUser(Map<String, Object> condition) throws UserException {
		try {
			return this.userMapper.updateUser(condition);
		} catch (Exception e) {
			logger.error("修改User时报错", e);
			throw new UserException("修改User时报错", e);
		}
	}

	/**
	 * 查询user
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public User readUser(long id) throws UserException {
		try {
			return this.userMapper.readUser(id);
		} catch (Exception e) {
			logger.error("查询User时报错", e);
			throw new UserException("查询User时报错", e);
		}
	}

	/**
	 * 查询user集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public List<User> queryUserList(Map<String, Object> condition) {
		try {
			return this.userMapper.queryUserList(condition);
		} catch (Exception e) {
			logger.error("查询User时报错", e);
			return null;
		}
	}

	/**
	 * 查询user集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public long queryUserCount(Map<String, Object> condition) throws UserException {
		try {
			return this.userMapper.queryUserCount(condition);
		} catch (Exception e) {
			logger.error("查询User时报错", e);
			throw new UserException("查询User时报错", e);
		}
	}

	@Override
	public User readUserByUnionId(String unionId) throws UserException {
		Map<String, Object> condition = new HashMap<>(5);
		condition.put("unionId", unionId);
		condition.put("isDeleted", Constants.NO);
		List<User> lstUser = userMapper.queryUserList(condition);
		if (CollectionUtils.isEmpty(lstUser)) {
			return null;
		}
		User user = lstUser.get(0);
		user.setPassword("");
		return user;
	}
	
	@Override
	public User readUserByOpenId(String openId) throws UserException {
		Map<String, Object> condition = new HashMap<>(5);
		condition.put("openId", openId);
		condition.put("isDeleted", Constants.NO);
		List<User> lstUser = userMapper.queryUserList(condition);
		if (CollectionUtils.isEmpty(lstUser)) {
			return null;
		}
		User user = lstUser.get(0);
		user.setPassword("");
		return user;
	}

	@Override
	public BaseDTO login(WechatLoginSuccessDTO loginDTO) throws UserException {
		try {
			// 检查用户是否已经存在
			User user = this.readUserByOpenId(loginDTO.getOpenid());
			if (user == null) {
				// 如果用户不存在，调用注册
				user = new User();
				user.setOpenId(loginDTO.getOpenid());
				user.setUnionId(loginDTO.getUnionid());
				user.setStatus(Constants.YES);
				user.setIsDeleted(Constants.NO);
				user.setCreateTime(LocalDateTime.now());
				user.setNewUser(Constants.YES);
				userMapper.insertUser(user);
			}
			// 生成userKey=md5(uuid+userId)
			String uuid = UUID.randomUUID().toString();
			String userKey = StringUtils.md5(uuid + user.getId());
			// 更新userSession信息
			UserSession userSession = new UserSession();
			userSession.setUnionId(loginDTO.getUnionid());
			userSession.setOpenId(loginDTO.getOpenid());
			userSession.setSessionKey(loginDTO.getSession_key());
			userSession.setUserId(user.getId());
			userSession.setUserKey(userKey);
			userSession.setCreateTime(LocalDateTime.now());
			userSessionService.insertUserSession(userSession);
			user.setUserKey(userKey);
			user.setUnionId("");
			return new BaseDTO(RequestResultEnum.SUCCESS, user);
		} catch (Exception e) {
			logger.error("登录失败，服务器内部错误！loginDTO={}, e={}", GsonUtils.toSimpleJson(loginDTO), e);
			throw new UserException("登录失败，服务器内部错误！", e);
		}
	}

	@Override
	public BaseDTO updateUserInfo(User user) throws UserException {
		try {
			// 获取userSession
			UserSession userSession = userSessionService.readUserSessionByUserKey(user.getUserKey());
			// 构造条件
			Map<String, Object> condition=new HashMap<>(25);
			ConditionUtils.addLong(condition, "id", userSession.getUserId());
			ConditionUtils.addStr(condition, "phone", user.getPhone());
			ConditionUtils.addStr(condition, "nickname", user.getNickName());
			ConditionUtils.addStr(condition, "portrait", user.getPortrait());
			ConditionUtils.addStr(condition, "sex", user.getSex());
			ConditionUtils.addObject(condition, "birthday", user.getBirthday());
			ConditionUtils.addStr(condition, "country", user.getCountry());
			ConditionUtils.addLong(condition, "countryId", user.getCountryId());
			ConditionUtils.addStr(condition, "province", user.getProvince());
			ConditionUtils.addLong(condition, "provinceId", user.getProvinceId());
			ConditionUtils.addStr(condition, "city", user.getCity());
			ConditionUtils.addLong(condition, "cityId", user.getCityId());
			ConditionUtils.addStr(condition, "district", user.getDistrict());
			ConditionUtils.addLong(condition, "districtId", user.getDistrictId());
			ConditionUtils.addStr(condition, "address", user.getAddress());
			ConditionUtils.addStr(condition, "mail", user.getMail());
			ConditionUtils.addStr(condition, "qq", user.getQq());
			ConditionUtils.addStr(condition, "wechat", user.getWechat());
			ConditionUtils.addStr(condition, "facebook", user.getFacebook());
			ConditionUtils.addStr(condition, "instagram", user.getInstagram());
			ConditionUtils.addLong(condition, "modifyBy", userSession.getUserId());
			ConditionUtils.addObject(condition, "modifyTime", LocalDateTime.now());
			userMapper.updateUser(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("修改用户信息失败，服务器内部错误！user={}, e={}", GsonUtils.toSimpleJson(user), e);
			throw new UserException("修改用户信息失败，服务器内部错误！", e);
		}
	}

}