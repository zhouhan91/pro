package com.wemeCity.web.catering.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.EHCacheUtils;
import com.wemeCity.web.catering.dto.CateringGoodsSalesVolume;
import com.wemeCity.web.catering.exception.CateringOrderDetailException;
import com.wemeCity.web.catering.mapper.CateringOrderDetailMapper;
import com.wemeCity.web.catering.model.CateringOrderDetail;
import com.wemeCity.web.catering.service.CateringOrderDetailService;

/**
 * CateringOrderDetailServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Service
public class CateringOrderDetailServiceImpl implements CateringOrderDetailService {

	private Logger logger = LoggerFactory.getLogger(CateringOrderDetailServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CateringOrderDetailMapper cateringOrderDetailMapper;

	/**
	 * 新增cateringOrderDetail
	 *
	 * @param cateringOrderDetail
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public void insertCateringOrderDetail(CateringOrderDetail cateringOrderDetail) throws CateringOrderDetailException {
		try {
			cateringOrderDetailMapper.insertCateringOrderDetail(cateringOrderDetail);
		} catch (Exception e) {
			logger.error("新增CateringOrderDetail时报错", e);
			throw new CateringOrderDetailException("新增CateringOrderDetail时报错", e);
		}
	}

	/**
	 * 删除cateringOrderDetail
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int deleteCateringOrderDetail(long id) throws CateringOrderDetailException {
		try {
			return this.cateringOrderDetailMapper.deleteCateringOrderDetail(id);
		} catch (Exception e) {
			logger.error("删除CateringOrderDetail时报错", e);
			throw new CateringOrderDetailException("删除CateringOrderDetail时报错", e);
		}
	}

	/**
	 * 修改cateringOrderDetail
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int updateCateringOrderDetail(Map<String, Object> condition) throws CateringOrderDetailException {
		try {
			return this.cateringOrderDetailMapper.updateCateringOrderDetail(condition);
		} catch (Exception e) {
			logger.error("修改CateringOrderDetail时报错", e);
			throw new CateringOrderDetailException("修改CateringOrderDetail时报错", e);
		}
	}

	/**
	 * 查询cateringOrderDetail
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public CateringOrderDetail readCateringOrderDetail(long id) throws CateringOrderDetailException {
		try {
			return this.cateringOrderDetailMapper.readCateringOrderDetail(id);
		} catch (Exception e) {
			logger.error("查询CateringOrderDetail时报错", e);
			throw new CateringOrderDetailException("查询CateringOrderDetail时报错", e);
		}
	}

	/**
	 * 查询cateringOrderDetail集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public List<CateringOrderDetail> queryCateringOrderDetailList(Map<String, Object> condition) {
		try {
			return this.cateringOrderDetailMapper.queryCateringOrderDetailList(condition);
		} catch (Exception e) {
			logger.error("查询CateringOrderDetail时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringOrderDetail集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public long queryCateringOrderDetailCount(Map<String, Object> condition) throws CateringOrderDetailException {
		try {
			return this.cateringOrderDetailMapper.queryCateringOrderDetailCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringOrderDetail时报错", e);
			throw new CateringOrderDetailException("查询CateringOrderDetail时报错", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
    public List<CateringOrderDetail> queryOrderDetailList(long orderId) throws CateringOrderDetailException {
		try {
			Object orderDetailInCache = EHCacheUtils.get("cateringOrderDetail", orderId + "");
			if (orderDetailInCache == null) {
				Map<String, Object> condition = new HashMap<>(8);
				condition.put("orderId", orderId);
				condition.put("isDeleted", Constants.NO);
				List<CateringOrderDetail> lstOrderDetail = cateringOrderDetailMapper.queryCateringOrderDetailList(condition);
				EHCacheUtils.put("cateringOrderDetail", orderId + "", lstOrderDetail);
				return lstOrderDetail;
			}
			return (List<CateringOrderDetail>) orderDetailInCache;
		} catch (Exception e) {
			logger.error("读取订单明细失败：服务器内部错误！orderId={}", orderId, e);
			throw new CateringOrderDetailException("读取订单明细失败：服务器内部错误！", e);
		}
	}

	@Override
    public List<CateringGoodsSalesVolume> queryCateringGoodsSalesVolume(Map<String, Object> condition) {
	    return cateringOrderDetailMapper.queryCateringGoodsSalesVolume(condition);
    }

}