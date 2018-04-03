package com.wemeCity.web.catering.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.dto.PageDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.web.catering.model.CateringContacts;
import com.wemeCity.web.catering.service.CateringContactsService;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.service.UserSessionService;

@Controller
@RequestMapping("/catering/contacts")
public class CateringContactsController extends BaseController {

	@Autowired
	private CateringContactsService cateringContactsService;

	@Autowired
	private UserSessionService userSessionService;

	@ResponseBody
	@GetMapping("/queryCateringContactsList/{userKey}/{pageNum}")
	public BaseDTO queryCateringContactsList(@PathVariable String userKey, @PathVariable int pageNum) {
		try {
			UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
			if (userSession == null) {
				logger.warn("获取联系人失败：用户不存在或登录已失效！userKey={}, pageNum={}", userKey, pageNum);
				return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
			}
			Map<String, Object> condition = new HashMap<>();
			condition.put("isDeleted", Constants.NO);
			condition.put("userId", userSession.getUserId());
			condition.put("sortColumn","create_time");
			condition.put("sortType","DESC");
			Page<CateringContacts> page = PageHelper.startPage(pageNum, Constants.CATERING_CONTACTS_NUM).doSelectPage(() -> cateringContactsService.queryCateringContactsList(condition));
			return new PageDTO(RequestResultEnum.SUCCESS, page);
		} catch (Exception e) {
			logger.error("获取联系人失败：服务器内部错误！userKey={}, pageNum={}, e={}", userKey, pageNum, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@GetMapping("/readCateringContacts/{id}")
	public BaseDTO readCateringContacts(@PathVariable long id) {
		try {
			return new BaseDTO(RequestResultEnum.SUCCESS, cateringContactsService.readCateringContacts(id));
		} catch (Exception e) {
			logger.error("读取联系人失败：服务器内部错误！id={}, e={}", id, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/insertCateringContacts")
	public BaseDTO insertCateringContacts(@RequestBody CateringContacts contacts) {
		try {
			UserSession userSession = userSessionService.readUserSessionByUserKey(contacts.getUserKey());
			if (userSession == null) {
				logger.warn("新建联系人失败：用户不存在或登录已失效！contacts={}", GsonUtils.toSimpleJson(contacts));
				return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
			}
			contacts.setIsDeleted(Constants.NO);
			contacts.setUserId(userSession.getUserId());
			contacts.setCreateBy(userSession.getUserId());
			contacts.setCreateTime(LocalDateTime.now());
			cateringContactsService.insertCateringContacts(contacts);
			return new BaseDTO(RequestResultEnum.SUCCESS, contacts);
		} catch (Exception e) {
			logger.error("新建联系人失败：服务器内部错误！contacts={}", GsonUtils.toSimpleJson(contacts), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/updateCateringContacts")
	public BaseDTO updateCateringContacts(@RequestBody CateringContacts contacts) {
		try {
			return cateringContactsService.updateCateringContacts(contacts);
		} catch (Exception e) {
			logger.error("修改联系人失败：服务器内部错误！contacts={}", GsonUtils.toSimpleJson(contacts), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/deleteCateringContacts/{userKey}/{id}")
	public BaseDTO deleteCateringContacts(@PathVariable String userKey, @PathVariable long id) {
		try {
			UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
			if (userSession == null) {
				logger.warn("删除联系人失败：用户不存在或登录已失效！userKey={}, id={}", userKey, id);
				return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
			}
			CateringContacts contacts = cateringContactsService.readCateringContacts(id);
			if (contacts == null) {
				logger.warn("删除联系人失败：联系人不存在！userKey={}, id={}", userKey, id);
				return new BaseDTO(RequestResultEnum.CONTACTS_NOT_FOUND, null);
			}
			if (contacts.getUserId() != userSession.getUserId()) {
				logger.warn("删除联系人失败：用户不匹配！userKey={}, id={}", userKey, id);
				return new BaseDTO(RequestResultEnum.USER_NOT_MATCH, null);
			}
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("id", id);
			condition.put("isDeleted", Constants.YES);
			condition.put("modifyBy", userSession.getUserId());
			condition.put("modifyTime", LocalDateTime.now());
			cateringContactsService.updateCateringContacts(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("删除联系人失败：用户不存在或登录已失效！userKey={}, id={}, e={}", userKey, id, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@GetMapping("/queryDefaultCateringContacts/{userKey}")
	public BaseDTO queryDefaultCateringContacts(@PathVariable String userKey) {
		try {
			UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
			if (userSession == null) {
				logger.warn("删除联系人失败：用户不存在或登录已失效！userKey={}, id={}", userKey);
				return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
			}
			Map<String, Object> condition = new HashMap<>();
			condition.put("userId", userSession.getUserId());
			condition.put("isDeleted", Constants.NO);
			Page<CateringContacts> page = PageHelper.startPage(1, 1).doSelectPage(() -> cateringContactsService.queryCateringContactsList(condition));
			if (CollectionUtils.isEmpty(page)) {
				return new BaseDTO(RequestResultEnum.SUCCESS, null);
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, page.get(0));
		} catch (Exception e) {
			logger.error("获取默认联系人失败：用户不存在或登录已失效！userKey={}, e={}", userKey, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

}
