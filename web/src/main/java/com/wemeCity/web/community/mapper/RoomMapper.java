
package com.wemeCity.web.community.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.community.model.Room;

/**
 * RoomMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Repository
public interface RoomMapper {

	/**
	 * 新增room
	 *
	 * @param room
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertRoom(Room room);

	/**
	 * 删除room
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteRoom(long id);

	/**
	 * 修改room
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateRoom(Map<String, Object> condition);

	/**
	 * 查询room
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	Room readRoom(long id);

	/**
	 * 查询room集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<Room> queryRoomList(Map<String, Object> condition);

	/**
	 * 查询room集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryRoomCount(Map<String, Object> condition);

}