package com.wemeCity.web.community.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.web.community.exception.CommunityMarkException;
import com.wemeCity.web.community.mapper.CommunityMarkMapper;
import com.wemeCity.web.community.model.Community;
import com.wemeCity.web.community.model.CommunityMark;
import com.wemeCity.web.community.service.CommunityMarkService;
import com.wemeCity.web.community.service.CommunityService;

/**
 * CommunityMarkServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-28 新建
 */
@Service
public class CommunityMarkServiceImpl implements CommunityMarkService {

	private Logger logger = LoggerFactory.getLogger(CommunityMarkServiceImpl.class);

	@Autowired
	private CommunityService communityService;

	/** 数据访问接口 */
	@Autowired
	private CommunityMarkMapper communityMarkMapper;

	/**
	 * 新增communityMark
	 *
	 * @param communityMark
	 * @return 新增的对象
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public void insertCommunityMark(CommunityMark communityMark) throws CommunityMarkException {
		try {
			communityMarkMapper.insertCommunityMark(communityMark);
		} catch (Exception e) {
			logger.error("新增CommunityMark时报错", e);
			throw new CommunityMarkException("新增CommunityMark时报错", e);
		}
	}

	/**
	 * 删除communityMark
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public int deleteCommunityMark(long id) throws CommunityMarkException {
		try {
			return this.communityMarkMapper.deleteCommunityMark(id);
		} catch (Exception e) {
			logger.error("删除CommunityMark时报错", e);
			throw new CommunityMarkException("删除CommunityMark时报错", e);
		}
	}

	/**
	 * 修改communityMark
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public int updateCommunityMark(Map<String, Object> condition) throws CommunityMarkException {
		try {
			return this.communityMarkMapper.updateCommunityMark(condition);
		} catch (Exception e) {
			logger.error("修改CommunityMark时报错", e);
			throw new CommunityMarkException("修改CommunityMark时报错", e);
		}
	}

	/**
	 * 查询communityMark
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public CommunityMark readCommunityMark(long id) throws CommunityMarkException {
		try {
			return this.communityMarkMapper.readCommunityMark(id);
		} catch (Exception e) {
			logger.error("查询CommunityMark时报错", e);
			throw new CommunityMarkException("查询CommunityMark时报错", e);
		}
	}

	/**
	 * 查询communityMark集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public List<CommunityMark> queryCommunityMarkList(Map<String, Object> condition) {
		try {
			return this.communityMarkMapper.queryCommunityMarkList(condition);
		} catch (Exception e) {
			logger.error("查询CommunityMark时报错", e);
			return null;
		}
	}

	/**
	 * 查询communityMark集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public long queryCommunityMarkCount(Map<String, Object> condition) throws CommunityMarkException {
		try {
			return this.communityMarkMapper.queryCommunityMarkCount(condition);
		} catch (Exception e) {
			logger.error("查询CommunityMark时报错", e);
			throw new CommunityMarkException("查询CommunityMark时报错", e);
		}
	}

	@Override
	public BaseDTO mark(long userId, long communityId) throws CommunityMarkException {
		try {
			CommunityMark communityMark = this.checkMarked(userId, communityId);
			if (communityMark != null) {
				return new BaseDTO(RequestResultEnum.SUCCESS, null);
			}
			communityMark = new CommunityMark();
			communityMark.setCommunityId(communityId);
			communityMark.setUserId(userId);
			communityMark.setCreateBy(userId);
			communityMark.setCreateTime(LocalDateTime.now());
			communityMark.setIsDeleted(Constants.NO);
			communityMarkMapper.insertCommunityMark(communityMark);
			// 将公寓收藏数+1
			Community community = communityService.readCommunity(communityId);
			if (community != null) {
				Map<String, Object> condition = new HashMap<>();
				condition.put("id", communityId);
				condition.put("markCount", community.getMarkCount() + 1);
				communityService.updateCommunity(condition);
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("收藏公寓失败，服务器内部错误!userId={}, communityId={}", new Object[] { userId, communityId }, e);
			throw new CommunityMarkException("收藏公寓失败，服务器内部错误！", e);
		}
	}

	@Override
	public BaseDTO cancelMark(long userId, long communityId) throws CommunityMarkException {
		Map<String, Object> condition = new HashMap<>();
		condition.put("userId", userId);
		condition.put("communityId", communityId);
		condition.put("modifyBy", userId);
		condition.put("modifyTime", LocalDateTime.now());
		condition.put("isDeleted", Constants.YES);
		communityMarkMapper.cancelMark(condition);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@Override
	public CommunityMark checkMarked(long userId, long communityId) throws CommunityMarkException {
		Map<String, Object> condition = new HashMap<>();
		condition.put("userId", userId);
		condition.put("communityId", communityId);
		condition.put("isDeleted", Constants.NO);
		List<CommunityMark> lst = communityMarkMapper.queryCommunityMarkList(condition);
		if (CollectionUtils.isEmpty(lst)) {
			return null;
		}
		return lst.get(0);
	}

}