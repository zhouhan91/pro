
package com.wemeCity.web.news.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.news.mapper.NewsBabietaMapper;
import com.wemeCity.web.news.model.NewsBabieta;
import com.wemeCity.web.news.exception.NewsBabietaException;
import com.wemeCity.web.news.service.NewsBabietaService;
/**
 * NewsBabietaServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-20 新建
 */
@Service
public class NewsBabietaServiceImpl implements NewsBabietaService{

	private Logger logger=LoggerFactory.getLogger(NewsBabietaServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private NewsBabietaMapper newsBabietaMapper;

	/**
	 * 新增newsBabieta
	 *
	 * @param newsBabieta
	 * @return 新增的对象
	 * @author 向小文 2017-12-20 新建
	 */
	@Override
	public void insertNewsBabieta(NewsBabieta newsBabieta) throws NewsBabietaException{
		try{
			newsBabietaMapper.insertNewsBabieta(newsBabieta);
		}catch(Exception e){
			logger.error("新增NewsBabieta时报错", e);
			throw new NewsBabietaException("新增NewsBabieta时报错", e);
		}
	}

	/**
	 * 删除newsBabieta
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-20 新建
	 */
	@Override
	public int deleteNewsBabieta(long id) throws NewsBabietaException{
		try{
			return this.newsBabietaMapper.deleteNewsBabieta(id);
		}catch(Exception e){
			logger.error("删除NewsBabieta时报错", e);
			throw new NewsBabietaException("删除NewsBabieta时报错", e);
		}
	}

	/**
	 * 修改newsBabieta
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-20 新建
	 */
	@Override
	public int updateNewsBabieta(Map<String, Object> condition) throws NewsBabietaException{
		try{
			return this.newsBabietaMapper.updateNewsBabieta(condition);
		}catch(Exception e){
			logger.error("修改NewsBabieta时报错", e);
			throw new NewsBabietaException("修改NewsBabieta时报错", e);
		}
	}

	/**
	 * 查询newsBabieta
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-20 新建
	 */
	@Override
	public NewsBabieta readNewsBabieta(long id) throws NewsBabietaException{
		try{
			return this.newsBabietaMapper.readNewsBabieta(id);
		}catch(Exception e){
			logger.error("查询NewsBabieta时报错", e);
			throw new NewsBabietaException("查询NewsBabieta时报错", e);
		}
	}

	/**
	 * 查询newsBabieta集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-20 新建
	 */
	@Override
	public List<NewsBabieta> queryNewsBabietaList(Map<String, Object> condition){
		try{
			return this.newsBabietaMapper.queryNewsBabietaList(condition);
		}catch(Exception e){
			logger.error("查询NewsBabieta时报错", e);
			return null;
		}
	}

	/**
	 * 查询newsBabieta集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-20 新建
	 */
	@Override
	public long queryNewsBabietaCount(Map<String, Object> condition) throws NewsBabietaException{
		try{
			return this.newsBabietaMapper.queryNewsBabietaCount(condition);
		}catch(Exception e){
			logger.error("查询NewsBabieta时报错", e);
			throw new NewsBabietaException("查询NewsBabieta时报错", e);
		}
	}

}