
package com.wemeCity.web.community.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.community.model.Facilities;

/**
 * FacilitiesMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Repository
public interface FacilitiesMapper {

	/**
	 * 新增facilities
	 *
	 * @param facilities
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertFacilities(Facilities facilities);

	/**
	 * 删除facilities
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteFacilities(long id);

	/**
	 * 修改facilities
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateFacilities(Map<String, Object> condition);

	/**
	 * 查询facilities
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	Facilities readFacilities(long id);

	/**
	 * 查询facilities集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<Facilities> queryFacilitiesList(Map<String, Object> condition);

	/**
	 * 查询facilities集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryFacilitiesCount(Map<String, Object> condition);

}