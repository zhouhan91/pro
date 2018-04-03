
package com.wemeCity.components.wechatPay.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.components.wechatPay.model.WechatCallback;

/**
 * WechatCallbackMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-8 新建
 */
@Repository
public interface WechatCallbackMapper {

	/**
	 * 新增wechatCallback
	 *
	 * @param wechatCallback
	 * @return 新增的对象
	 * @author 向小文 2017-10-8 新建
	 */
	void insertWechatCallback(WechatCallback wechatCallback);

	/**
	 * 删除wechatCallback
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-10-8 新建
	 */
	int deleteWechatCallback(long id);

	/**
	 * 修改wechatCallback
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-10-8 新建
	 */
	int updateWechatCallback(Map<String, Object> condition);

	/**
	 * 查询wechatCallback
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-10-8 新建
	 */
	WechatCallback readWechatCallback(long id);

	/**
	 * 查询wechatCallback集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-10-8 新建
	 */
	List<WechatCallback> queryWechatCallbackList(Map<String, Object> condition);

	/**
	 * 查询wechatCallback集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-10-8 新建
	 */
	long queryWechatCallbackCount(Map<String, Object> condition);

}