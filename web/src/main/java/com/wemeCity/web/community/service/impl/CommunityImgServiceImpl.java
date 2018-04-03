package com.wemeCity.web.community.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.common.utils.Constants;
import com.wemeCity.web.community.enums.CommunityImgBusiCodeEnum;
import com.wemeCity.web.community.exception.CommunityImgException;
import com.wemeCity.web.community.mapper.CommunityImgMapper;
import com.wemeCity.web.community.model.CommunityImg;
import com.wemeCity.web.community.service.CommunityImgService;

/**
 * CommunityImgServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Service
public class CommunityImgServiceImpl implements CommunityImgService {

	private Logger logger = LoggerFactory.getLogger(CommunityImgServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CommunityImgMapper communityImgMapper;

	/**
	 * 新增communityImg
	 *
	 * @param communityImg
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public void insertCommunityImg(CommunityImg communityImg) throws CommunityImgException {
		try {
			communityImgMapper.insertCommunityImg(communityImg);
		} catch (Exception e) {
			logger.error("新增CommunityImg时报错", e);
			throw new CommunityImgException("新增CommunityImg时报错", e);
		}
	}

	/**
	 * 删除communityImg
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int deleteCommunityImg(long id) throws CommunityImgException {
		try {
			return this.communityImgMapper.deleteCommunityImg(id);
		} catch (Exception e) {
			logger.error("删除CommunityImg时报错", e);
			throw new CommunityImgException("删除CommunityImg时报错", e);
		}
	}

	/**
	 * 修改communityImg
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public int updateCommunityImg(Map<String, Object> condition) throws CommunityImgException {
		try {
			return this.communityImgMapper.updateCommunityImg(condition);
		} catch (Exception e) {
			logger.error("修改CommunityImg时报错", e);
			throw new CommunityImgException("修改CommunityImg时报错", e);
		}
	}

	/**
	 * 查询communityImg
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public CommunityImg readCommunityImg(long id) throws CommunityImgException {
		try {
			return this.communityImgMapper.readCommunityImg(id);
		} catch (Exception e) {
			logger.error("查询CommunityImg时报错", e);
			throw new CommunityImgException("查询CommunityImg时报错", e);
		}
	}

	/**
	 * 查询communityImg集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public List<CommunityImg> queryCommunityImgList(Map<String, Object> condition) {
		try {
			return this.communityImgMapper.queryCommunityImgList(condition);
		} catch (Exception e) {
			logger.error("查询CommunityImg时报错", e);
			return null;
		}
	}

	/**
	 * 查询communityImg集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	@Override
	public long queryCommunityImgCount(Map<String, Object> condition) throws CommunityImgException {
		try {
			return this.communityImgMapper.queryCommunityImgCount(condition);
		} catch (Exception e) {
			logger.error("查询CommunityImg时报错", e);
			throw new CommunityImgException("查询CommunityImg时报错", e);
		}
	}

	@Override
	public List<CommunityImg> queryCommunityImgList(CommunityImgBusiCodeEnum busiCode, long busiId) throws CommunityImgException {
		Map<String, Object> condition = new HashMap<>();
		condition.put("busiCode", busiCode.getKey());
		condition.put("busiId", busiId);
		condition.put("isDeleted", Constants.NO);
		condition.put("sortColumn", "sort_num");
		condition.put("sortType", "asc");
		return communityImgMapper.queryCommunityImgList(condition);
	}
}