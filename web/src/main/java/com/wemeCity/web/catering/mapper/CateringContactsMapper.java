
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringContacts;

/**
 * CateringContactsMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-8 新建
 */
@Repository
public interface CateringContactsMapper {

	/**
	 * 新增cateringContacts
	 *
	 * @param cateringContacts
	 * @return 新增的对象
	 * @author 向小文 2017-12-8 新建
	 */
	void insertCateringContacts(CateringContacts cateringContacts);

	/**
	 * 删除cateringContacts
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-8 新建
	 */
	int deleteCateringContacts(long id);

	/**
	 * 修改cateringContacts
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-8 新建
	 */
	int updateCateringContacts(Map<String, Object> condition);

	/**
	 * 查询cateringContacts
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-8 新建
	 */
	CateringContacts readCateringContacts(long id);

	/**
	 * 查询cateringContacts集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-8 新建
	 */
	List<CateringContacts> queryCateringContactsList(Map<String, Object> condition);

	/**
	 * 查询cateringContacts集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-8 新建
	 */
	long queryCateringContactsCount(Map<String, Object> condition);

	/**
	 * 修改默认联系人
	 *
	 * @param condition
	 * @return
	 * @history 2018年1月5日 新建
	 * @auther xiaowen
	 */
	int changeDefaultContacts(Map<String, Object> condition);
}