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
import com.wemeCity.web.catering.exception.CateringCommentException;
import com.wemeCity.web.catering.mapper.CateringCommentMapper;
import com.wemeCity.web.catering.model.CateringComment;
import com.wemeCity.web.catering.model.CateringCommentImg;
import com.wemeCity.web.catering.model.CateringOrder;
import com.wemeCity.web.catering.service.CateringCommentImgService;
import com.wemeCity.web.catering.service.CateringCommentService;
import com.wemeCity.web.catering.service.CateringOrderService;
import com.wemeCity.web.user.model.UserSession;

/**
 * CateringCommentServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Service
public class CateringCommentServiceImpl implements CateringCommentService {

	private Logger logger = LoggerFactory.getLogger(CateringCommentServiceImpl.class);

	@Autowired
	private CateringCommentImgService cateringCommentImgService;

	@Autowired
	private CateringOrderService cateringOrderService;

	/** 数据访问接口 */
	@Autowired
	private CateringCommentMapper cateringCommentMapper;

	/**
	 * 新增cateringComment
	 *
	 * @param cateringComment
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public void insertCateringComment(CateringComment cateringComment) throws CateringCommentException {
		try {
			cateringCommentMapper.insertCateringComment(cateringComment);
		} catch (Exception e) {
			logger.error("新增CateringComment时报错", e);
			throw new CateringCommentException("新增CateringComment时报错", e);
		}
	}

	/**
	 * 删除cateringComment
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int deleteCateringComment(long id) throws CateringCommentException {
		try {
			return this.cateringCommentMapper.deleteCateringComment(id);
		} catch (Exception e) {
			logger.error("删除CateringComment时报错", e);
			throw new CateringCommentException("删除CateringComment时报错", e);
		}
	}

	/**
	 * 修改cateringComment
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int updateCateringComment(Map<String, Object> condition) throws CateringCommentException {
		try {
			return this.cateringCommentMapper.updateCateringComment(condition);
		} catch (Exception e) {
			logger.error("修改CateringComment时报错", e);
			throw new CateringCommentException("修改CateringComment时报错", e);
		}
	}

	/**
	 * 查询cateringComment
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public CateringComment readCateringComment(long id) throws CateringCommentException {
		try {
			return this.cateringCommentMapper.readCateringComment(id);
		} catch (Exception e) {
			logger.error("查询CateringComment时报错", e);
			throw new CateringCommentException("查询CateringComment时报错", e);
		}
	}

	/**
	 * 查询cateringComment集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public List<CateringComment> queryCateringCommentList(Map<String, Object> condition) {
		try {
			return this.cateringCommentMapper.queryCateringCommentList(condition);
		} catch (Exception e) {
			logger.error("查询CateringComment时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringComment集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public long queryCateringCommentCount(Map<String, Object> condition) throws CateringCommentException {
		try {
			return this.cateringCommentMapper.queryCateringCommentCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringComment时报错", e);
			throw new CateringCommentException("查询CateringComment时报错", e);
		}
	}

	@Override
	public List<CateringComment> queryRestaurantCommentInfoList(long restaurantId, int pageNum) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("isDeleted", Constants.NO);
		condition.put("restaurantId", restaurantId);
		condition.put("sortColumn", "catering_comment.id");
		condition.put("sortType", "desc");
		Page<CateringComment> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringCommentMapper.queryCateringCommentList(condition));
		if (!CollectionUtils.isEmpty(page)) {
			for (CateringComment comment : page) {
				comment.setLstCommentImg(cateringCommentImgService.queryCommentImg(comment.getId()));
			}
		}
		return page;
	}

	@Override
	public BaseDTO publish(CateringComment comment, UserSession userSession) throws CateringCommentException {
		try {
			CateringOrder order = cateringOrderService.readCateringOrder(comment.getOrderId());
			if (order == null || Constants.YES.equals(order.getIsDeleted())) {
				logger.warn("发表评论失败：订单不存在！comment={}", GsonUtils.toSimpleJson(comment));
				return new BaseDTO(RequestResultEnum.CATERING_ORDER_NOT_FOUND, null);
			}
			if (Constants.YES.equals(order.getCommentFlag())) {
				logger.warn("发表评论失败：每个订单只允许发布一次评论！comment={}", GsonUtils.toSimpleJson(comment));
				return new BaseDTO(RequestResultEnum.CATERING_ORDER_COMMENT_ONLY_ONCE, null);
			}
			// 插入评论
			comment.setOrderId(order.getId());
			comment.setRestaurantId(order.getRestaurantId());
			comment.setUserId(order.getUserId());
			comment.setIsDeleted(Constants.NO);
			comment.setCreateBy(userSession.getId());
			comment.setCreateTime(LocalDateTime.now());
			cateringCommentMapper.insertCateringComment(comment);
			List<CateringCommentImg> lstImg = comment.getLstCommentImg();
			if (!CollectionUtils.isEmpty(lstImg)) {
				for (CateringCommentImg img : lstImg) {
					img.setCommentId(comment.getId());
					img.setUserId(userSession.getId());
					img.setRestaurantId(order.getRestaurantId());
					img.setIsDeleted(Constants.NO);
					img.setCreateBy(userSession.getId());
					img.setCreateTime(LocalDateTime.now());
					cateringCommentImgService.insertCateringCommentImg(img);
				}
			}
			Map<String, Object> condition = new HashMap<>();
			condition.put("commentFlag", Constants.YES);
			condition.put("id", order.getId());
			condition.put("modifyBy", userSession.getUserId());
			condition.put("modfiyTime", LocalDateTime.now());
			cateringOrderService.updateCateringOrder(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, comment);
		} catch (Exception e) {
			logger.error("发布评论失败：服务器内部错误！comment={}", GsonUtils.toSimpleJson(comment), e);
			throw new CateringCommentException("发布评论失败：服务器内部错误！", e);
		}
	}

}