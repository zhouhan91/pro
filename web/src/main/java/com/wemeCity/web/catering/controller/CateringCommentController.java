package com.wemeCity.web.catering.controller;

import java.util.HashMap;
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
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.web.catering.model.CateringComment;
import com.wemeCity.web.catering.service.CateringCommentImgService;
import com.wemeCity.web.catering.service.CateringCommentService;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.service.UserSessionService;

@Controller
@RequestMapping("/catering/comment")
public class CateringCommentController extends BaseController {

	@Autowired
	private CateringCommentService cateringCommentService;

	@Autowired
	private CateringCommentImgService cateringCommentImgService;

	@Autowired
	private UserSessionService userSessionService;

	@ResponseBody
	@PostMapping("/publish")
	public BaseDTO publish(@RequestBody CateringComment comment) {
		try {
			if (comment == null || StringUtils.isEmpty(comment.getUserKey()) || comment.getOrderId() <= 0) {
				logger.warn("评论发布失败：非空参数校验失败！comment={}", GsonUtils.toSimpleJson(comment));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			UserSession userSession = userSessionService.readUserSessionByUserKey(comment.getUserKey());
			if (userSession == null) {
				logger.warn("评论发布失败：用户不存在或登录已失效！comment={}", GsonUtils.toSimpleJson(comment));
				return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
			}
			return cateringCommentService.publish(comment, userSession);
		} catch (Exception e) {
			logger.error("评论发布失败：服务器内部错误！comment={}", GsonUtils.toSimpleJson(comment), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@GetMapping("/readComment/{id}")
	public BaseDTO readComment(@PathVariable long id) {
		try {
			CateringComment comment = cateringCommentService.readCateringComment(id);
			comment.setLstCommentImg(cateringCommentImgService.queryCommentImg(comment.getId()));
			return new BaseDTO(RequestResultEnum.SUCCESS, comment);
		} catch (Exception e) {
			logger.error("读取评论失败：服务器内部错误！id={}", id, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@GetMapping("/queryCommentListByRestaurantId/{restaurantId}/{pageNum}")
	public BaseDTO queryCommentListByRestaurantId(@PathVariable long restaurantId, @PathVariable int pageNum) {
		Map<String, Object> condition = new HashMap<>(5);
		condition.put("restaurantId", restaurantId);
		condition.put("isDeleted", Constants.NO);
		Page<CateringComment> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringCommentService.queryCateringCommentList(condition));
		if (!CollectionUtils.isEmpty(page)) {
			for (CateringComment comment : page) {
				comment.setLstCommentImg(cateringCommentImgService.queryCommentImg(comment.getId()));
			}
		}
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	@ResponseBody
	@GetMapping("/readCommentByOrderId/{orderId}")
	public BaseDTO readCommentByOrderId(@PathVariable long orderId) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("orderId", orderId);
		condition.put("isDeleted", Constants.NO);
		Page<CateringComment> page = PageHelper.startPage(1, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringCommentService.queryCateringCommentList(condition));
		if (!CollectionUtils.isEmpty(page)) {
			for (CateringComment comment : page) {
				comment.setLstCommentImg(cateringCommentImgService.queryCommentImg(comment.getId()));
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, page.get(0));
		} else {
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		}
	}
}
