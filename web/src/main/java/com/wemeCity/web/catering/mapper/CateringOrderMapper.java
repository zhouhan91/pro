
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.dto.CateringAllData;
import com.wemeCity.web.catering.dto.CateringMonthData;
import com.wemeCity.web.catering.dto.TodayStatisticsInfo;
import com.wemeCity.web.catering.model.CateringOrder;

/**
 * CateringOrderMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Repository
public interface CateringOrderMapper {

	/**
	 * 新增cateringOrder
	 *
	 * @param cateringOrder
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringOrder(CateringOrder cateringOrder);

	/**
	 * 删除cateringOrder
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringOrder(long id);

	/**
	 * 修改cateringOrder
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringOrder(Map<String, Object> condition);

	/**
	 * 查询cateringOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringOrder readCateringOrder(long id);

	/**
	 * 查询cateringOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringOrder> queryCateringOrderList(Map<String, Object> condition);

	/**
	 * 查询cateringOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringOrderCount(Map<String, Object> condition);
	
	/**
	 * 统计今天的数据
	 *
	 * @param condition
	 * @return
	 * @history 2017年12月28日 新建
	 * @auther xiaowen
	 */
	TodayStatisticsInfo queryTodayStatisticsInfo(Map<String, Object> condition);

	/**
	 * 统计所有数据
	 *
	 * @param condition
	 * @return
	 * @history 2018年2月12日 新建
	 * @auther xiaowen
	 */
	CateringAllData queryAllData(Map<String, Object> condition);
	
	/**
	 * 统计本月数据
	 *
	 * @param condition
	 * @return
	 * @history 2018年2月12日 新建
	 * @auther xiaowen
	 */
	CateringMonthData queryMonthData(Map<String, Object> condition);
}