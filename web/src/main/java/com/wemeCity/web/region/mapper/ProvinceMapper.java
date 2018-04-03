
package com.wemeCity.web.region.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.region.model.Province;

/**
 * ProvinceMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Repository
public interface ProvinceMapper {

	/**
	 * 新增province
	 *
	 * @param province
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertProvince(Province province);

	/**
	 * 删除province
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteProvince(long id);

	/**
	 * 修改province
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateProvince(Map<String, Object> condition);

	/**
	 * 查询province
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	Province readProvince(long id);

	/**
	 * 查询province集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<Province> queryProvinceList(Map<String, Object> condition);

	/**
	 * 查询province集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryProvinceCount(Map<String, Object> condition);

}