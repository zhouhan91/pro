package com.wemeCity.web.catering.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.EHCacheUtils;
import com.wemeCity.web.catering.exception.CateringCommentImgException;
import com.wemeCity.web.catering.mapper.CateringCommentImgMapper;
import com.wemeCity.web.catering.model.CateringCommentImg;
import com.wemeCity.web.catering.service.CateringCommentImgService;

/**
 * CateringCommentImgServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Service
public class CateringCommentImgServiceImpl implements CateringCommentImgService {

	private Logger logger = LoggerFactory.getLogger(CateringCommentImgServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CateringCommentImgMapper cateringCommentImgMapper;

	/**
	 * 新增cateringCommentImg
	 *
	 * @param cateringCommentImg
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public void insertCateringCommentImg(CateringCommentImg cateringCommentImg) throws CateringCommentImgException {
		try {
			cateringCommentImgMapper.insertCateringCommentImg(cateringCommentImg);
		} catch (Exception e) {
			logger.error("新增CateringCommentImg时报错", e);
			throw new CateringCommentImgException("新增CateringCommentImg时报错", e);
		}
	}

	/**
	 * 删除cateringCommentImg
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int deleteCateringCommentImg(long id) throws CateringCommentImgException {
		try {
			return this.cateringCommentImgMapper.deleteCateringCommentImg(id);
		} catch (Exception e) {
			logger.error("删除CateringCommentImg时报错", e);
			throw new CateringCommentImgException("删除CateringCommentImg时报错", e);
		}
	}

	/**
	 * 修改cateringCommentImg
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int updateCateringCommentImg(Map<String, Object> condition) throws CateringCommentImgException {
		try {
			return this.cateringCommentImgMapper.updateCateringCommentImg(condition);
		} catch (Exception e) {
			logger.error("修改CateringCommentImg时报错", e);
			throw new CateringCommentImgException("修改CateringCommentImg时报错", e);
		}
	}

	/**
	 * 查询cateringCommentImg
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public CateringCommentImg readCateringCommentImg(long id) throws CateringCommentImgException {
		try {
			return this.cateringCommentImgMapper.readCateringCommentImg(id);
		} catch (Exception e) {
			logger.error("查询CateringCommentImg时报错", e);
			throw new CateringCommentImgException("查询CateringCommentImg时报错", e);
		}
	}

	/**
	 * 查询cateringCommentImg集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public List<CateringCommentImg> queryCateringCommentImgList(Map<String, Object> condition) {
		try {
			return this.cateringCommentImgMapper.queryCateringCommentImgList(condition);
		} catch (Exception e) {
			logger.error("查询CateringCommentImg时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringCommentImg集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public long queryCateringCommentImgCount(Map<String, Object> condition) throws CateringCommentImgException {
		try {
			return this.cateringCommentImgMapper.queryCateringCommentImgCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringCommentImg时报错", e);
			throw new CateringCommentImgException("查询CateringCommentImg时报错", e);
		}
	}

    @Override
	@SuppressWarnings("unchecked")
	public List<CateringCommentImg> queryCommentImg(long commentId) {
		Object lstCommentImgInCache = EHCacheUtils.get("cateringCommentImg", "" + commentId);
		if (lstCommentImgInCache != null) {
			return (List<CateringCommentImg>) lstCommentImgInCache;
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("commentId", commentId);
		condition.put("isDeleted", Constants.NO);
		Page<CateringCommentImg> lstCommentImg = PageHelper.startPage(1, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringCommentImgMapper.queryCateringCommentImgList(condition));
		EHCacheUtils.put("cateringCommentImg", "" + commentId, lstCommentImg);
		return lstCommentImg;
	}

}