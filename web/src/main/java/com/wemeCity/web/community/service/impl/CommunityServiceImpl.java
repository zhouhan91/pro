package com.wemeCity.web.community.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.web.community.exception.CommunityException;
import com.wemeCity.web.community.mapper.CommunityMapper;
import com.wemeCity.web.community.model.Community;
import com.wemeCity.web.community.service.CommunityService;

/**
 * CommunityServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Service
public class CommunityServiceImpl implements CommunityService {

	private Logger logger = LoggerFactory.getLogger(CommunityServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CommunityMapper communityMapper;

	/**
	 * 新增community
	 *
	 * @param community
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public void insertCommunity(Community community) throws CommunityException {
		try {
			communityMapper.insertCommunity(community);
		} catch (Exception e) {
			logger.error("新增Community时报错", e);
			throw new CommunityException("新增Community时报错", e);
		}
	}

	/**
	 * 删除community
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int deleteCommunity(long id) throws CommunityException {
		try {
			return this.communityMapper.deleteCommunity(id);
		} catch (Exception e) {
			logger.error("删除Community时报错", e);
			throw new CommunityException("删除Community时报错", e);
		}
	}

	/**
	 * 修改community
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int updateCommunity(Map<String, Object> condition) throws CommunityException {
		try {
			return this.communityMapper.updateCommunity(condition);
		} catch (Exception e) {
			logger.error("修改Community时报错", e);
			throw new CommunityException("修改Community时报错", e);
		}
	}

	/**
	 * 查询community
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public Community readCommunity(long id) throws CommunityException {
		try {
			return this.communityMapper.readCommunity(id);
		} catch (Exception e) {
			logger.error("查询Community时报错", e);
			throw new CommunityException("查询Community时报错", e);
		}
	}

	/**
	 * 查询community集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public List<Community> queryCommunityList(Map<String, Object> condition) {
		try {
			return this.communityMapper.queryCommunityList(condition);
		} catch (Exception e) {
			logger.error("查询Community时报错", e);
			return null;
		}
	}

	/**
	 * 查询community集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public long queryCommunityCount(Map<String, Object> condition) throws CommunityException {
		try {
			return this.communityMapper.queryCommunityCount(condition);
		} catch (Exception e) {
			logger.error("查询Community时报错", e);
			throw new CommunityException("查询Community时报错", e);
		}
	}
}