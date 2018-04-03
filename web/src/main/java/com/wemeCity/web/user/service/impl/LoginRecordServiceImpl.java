
package com.wemeCity.web.user.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.user.mapper.LoginRecordMapper;
import com.wemeCity.web.user.model.LoginRecord;
import com.wemeCity.web.user.exception.LoginRecordException;
import com.wemeCity.web.user.service.LoginRecordService;
/**
 * LoginRecordServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
@Service
public class LoginRecordServiceImpl implements LoginRecordService{

	private Logger logger=LoggerFactory.getLogger(LoginRecordServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private LoginRecordMapper loginRecordMapper;

	/**
	 * 新增loginRecord
	 *
	 * @param loginRecord
	 * @return 新增的对象
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public void insertLoginRecord(LoginRecord loginRecord) throws LoginRecordException{
		try{
			loginRecordMapper.insertLoginRecord(loginRecord);
		}catch(Exception e){
			logger.error("新增LoginRecord时报错", e);
			throw new LoginRecordException("新增LoginRecord时报错", e);
		}
	}

	/**
	 * 删除loginRecord
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public int deleteLoginRecord(long id) throws LoginRecordException{
		try{
			return this.loginRecordMapper.deleteLoginRecord(id);
		}catch(Exception e){
			logger.error("删除LoginRecord时报错", e);
			throw new LoginRecordException("删除LoginRecord时报错", e);
		}
	}

	/**
	 * 修改loginRecord
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public int updateLoginRecord(Map<String, Object> condition) throws LoginRecordException{
		try{
			return this.loginRecordMapper.updateLoginRecord(condition);
		}catch(Exception e){
			logger.error("修改LoginRecord时报错", e);
			throw new LoginRecordException("修改LoginRecord时报错", e);
		}
	}

	/**
	 * 查询loginRecord
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public LoginRecord readLoginRecord(long id) throws LoginRecordException{
		try{
			return this.loginRecordMapper.readLoginRecord(id);
		}catch(Exception e){
			logger.error("查询LoginRecord时报错", e);
			throw new LoginRecordException("查询LoginRecord时报错", e);
		}
	}

	/**
	 * 查询loginRecord集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public List<LoginRecord> queryLoginRecordList(Map<String, Object> condition){
		try{
			return this.loginRecordMapper.queryLoginRecordList(condition);
		}catch(Exception e){
			logger.error("查询LoginRecord时报错", e);
			return null;
		}
	}

	/**
	 * 查询loginRecord集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-26 新建
	 */
	@Override
	public long queryLoginRecordCount(Map<String, Object> condition) throws LoginRecordException{
		try{
			return this.loginRecordMapper.queryLoginRecordCount(condition);
		}catch(Exception e){
			logger.error("查询LoginRecord时报错", e);
			throw new LoginRecordException("查询LoginRecord时报错", e);
		}
	}

}