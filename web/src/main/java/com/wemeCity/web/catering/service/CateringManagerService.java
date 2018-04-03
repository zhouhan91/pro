
package com.wemeCity.web.catering.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.web.catering.exception.CateringManagerException;
import com.wemeCity.web.catering.model.CateringManager;

/**
 * CateringManagerService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-23 新建
 */
public interface CateringManagerService {

	/**
	 * 新增cateringManager
	 *
	 * @param cateringManager
	 * @return 新增的对象
	 * @author 向小文 2017-12-23 新建
	 */
	void insertCateringManager(CateringManager cateringManager) throws CateringManagerException;

	/**
	 * 删除cateringManager
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-23 新建
	 */
	int deleteCateringManager(long id) throws CateringManagerException;

	/**
	 * 修改cateringManager
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-23 新建
	 */
	int updateCateringManager(Map<String, Object> condition) throws CateringManagerException;

	/**
	 * 查询cateringManager
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-23 新建
	 */
	CateringManager readCateringManager(long id) throws CateringManagerException;

	/**
	 * 查询cateringManager集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-23 新建
	 */
	List<CateringManager> queryCateringManagerList(Map<String, Object> condition) ;

	/**
	 * 查询cateringManager集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-23 新建
	 */
	long queryCateringManagerCount(Map<String, Object> condition) throws CateringManagerException;
	
	/**
	 * 注册店铺
	 *
	 * @param manager
	 * @return
	 * @throws CateringManagerException
	 * @history 2017年12月23日 新建
	 * @auther xiaowen
	 */
	BaseDTO register(CateringManager manager) throws CateringManagerException;
	
	/**
	 * 根据用户名读取店铺管理员信息
	 *
	 * @param userName
	 * @return
	 * @throws CateringManagerException
	 * @history 2017年12月23日 新建
	 * @auther xiaowen
	 */
	CateringManager readManagerByUserName(String userName) throws CateringManagerException;

}