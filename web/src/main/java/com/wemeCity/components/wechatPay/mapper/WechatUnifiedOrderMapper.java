
package com.wemeCity.components.wechatPay.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.components.wechatPay.model.WechatUnifiedOrder;

/**
 * WechatUnifiedOrderMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-8 新建
 */
@Repository
public interface WechatUnifiedOrderMapper {

	/**
	 * 新增wechatUnifiedOrder
	 *
	 * @param wechatUnifiedOrder
	 * @return 新增的对象
	 * @author 向小文 2017-10-8 新建
	 */
	void insertWechatUnifiedOrder(WechatUnifiedOrder wechatUnifiedOrder);

	/**
	 * 删除wechatUnifiedOrder
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-8 新建
	 */
	int deleteWechatUnifiedOrder(long id);

	/**
	 * 修改wechatUnifiedOrder
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-8 新建
	 */
	int updateWechatUnifiedOrder(Map<String, Object> condition);

	/**
	 * 查询wechatUnifiedOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-8 新建
	 */
	WechatUnifiedOrder readWechatUnifiedOrder(long id);

	/**
	 * 查询wechatUnifiedOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-8 新建
	 */
	List<WechatUnifiedOrder> queryWechatUnifiedOrderList(Map<String, Object> condition);

	/**
	 * 查询wechatUnifiedOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-8 新建
	 */
	long queryWechatUnifiedOrderCount(Map<String, Object> condition);

}