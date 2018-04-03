package com.wemeCity.web.catering.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.web.catering.dto.CourierSaveDTO;
import com.wemeCity.web.catering.exception.CateringCourierException;
import com.wemeCity.web.catering.model.CateringCourier;

/**
 * CateringCourierService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-25 新建
 */
public interface CateringCourierService {

	/**
	 * 新增cateringCourier
	 *
	 * @param cateringCourier
	 * @return 新增的对象
	 * @author 向小文 2017-12-25 新建
	 */
	void insertCateringCourier(CateringCourier cateringCourier) throws CateringCourierException;

	/**
	 * 删除cateringCourier
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-25 新建
	 */
	int deleteCateringCourier(long id) throws CateringCourierException;

	/**
	 * 修改cateringCourier
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-25 新建
	 */
	int updateCateringCourier(Map<String, Object> condition) throws CateringCourierException;

	/**
	 * 查询cateringCourier
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-25 新建
	 */
	CateringCourier readCateringCourier(long id) throws CateringCourierException;

	/**
	 * 查询cateringCourier集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-25 新建
	 */
	List<CateringCourier> queryCateringCourierList(Map<String, Object> condition);

	/**
	 * 查询cateringCourier集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-25 新建
	 */
	long queryCateringCourierCount(Map<String, Object> condition) throws CateringCourierException;

	/**
	 * 保存配送员信息
	 *
	 * @param dto
	 * @return
	 * @throws CateringCourierException
	 * @history 2017年12月25日 新建
	 * @auther xiaowen
	 */
	BaseDTO saveCateringCourierList(CourierSaveDTO dto) throws CateringCourierException;
}