
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringManager;

/**
 * CateringManagerMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-23 新建
 */
@Repository
public interface CateringManagerMapper {

	/**
	 * 新增cateringManager
	 *
	 * @param cateringManager
	 * @return 新增的对象
	 * @author 向小文 2017-12-23 新建
	 */
	void insertCateringManager(CateringManager cateringManager);

	/**
	 * 删除cateringManager
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-23 新建
	 */
	int deleteCateringManager(long id);

	/**
	 * 修改cateringManager
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-23 新建
	 */
	int updateCateringManager(Map<String, Object> condition);

	/**
	 * 查询cateringManager
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-23 新建
	 */
	CateringManager readCateringManager(long id);

	/**
	 * 查询cateringManager集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-23 新建
	 */
	List<CateringManager> queryCateringManagerList(Map<String, Object> condition);

	/**
	 * 查询cateringManager集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-23 新建
	 */
	long queryCateringManagerCount(Map<String, Object> condition);

}