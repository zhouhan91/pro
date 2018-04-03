
package com.wemeCity.web.community.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.community.exception.RoomException;
import com.wemeCity.web.community.model.Room;

/**
 * RoomService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
public interface RoomService {

	/**
	 * 新增room
	 *
	 * @param room
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertRoom(Room room) throws RoomException;

	/**
	 * 删除room
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteRoom(long id) throws RoomException;

	/**
	 * 修改room
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateRoom(Map<String, Object> condition) throws RoomException;

	/**
	 * 查询room
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	Room readRoom(long id) throws RoomException;

	/**
	 * 查询room集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<Room> queryRoomList(Map<String, Object> condition) ;

	/**
	 * 查询room集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryRoomCount(Map<String, Object> condition) throws RoomException;
	
	/**
	 * 根据社区id读取房型
	 *
	 * @param communityId
	 * @return
	 * @throws RoomException
	 * @Author Xiang xiaowen 2017年9月19日 新建
	 */
	List<Room> queryRoomListByCommunityId(long communityId) throws RoomException;

}