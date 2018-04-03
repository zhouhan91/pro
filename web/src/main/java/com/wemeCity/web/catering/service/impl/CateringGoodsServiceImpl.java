
package com.wemeCity.web.catering.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wemeCity.web.catering.mapper.CateringGoodsMapper;
import com.wemeCity.web.catering.model.CateringGoods;
import com.wemeCity.web.catering.exception.CateringGoodsException;
import com.wemeCity.web.catering.service.CateringGoodsService;
/**
 * CateringGoodsServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Service
public class CateringGoodsServiceImpl implements CateringGoodsService{

	private Logger logger=LoggerFactory.getLogger(CateringGoodsServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private CateringGoodsMapper cateringGoodsMapper;

	/**
	 * 新增cateringGoods
	 *
	 * @param cateringGoods
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public void insertCateringGoods(CateringGoods cateringGoods) throws CateringGoodsException{
		try{
			cateringGoodsMapper.insertCateringGoods(cateringGoods);
		}catch(Exception e){
			logger.error("新增CateringGoods时报错", e);
			throw new CateringGoodsException("新增CateringGoods时报错", e);
		}
	}

	/**
	 * 删除cateringGoods
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int deleteCateringGoods(long id) throws CateringGoodsException{
		try{
			return this.cateringGoodsMapper.deleteCateringGoods(id);
		}catch(Exception e){
			logger.error("删除CateringGoods时报错", e);
			throw new CateringGoodsException("删除CateringGoods时报错", e);
		}
	}

	/**
	 * 修改cateringGoods
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int updateCateringGoods(Map<String, Object> condition) throws CateringGoodsException{
		try{
			return this.cateringGoodsMapper.updateCateringGoods(condition);
		}catch(Exception e){
			logger.error("修改CateringGoods时报错", e);
			throw new CateringGoodsException("修改CateringGoods时报错", e);
		}
	}

	/**
	 * 查询cateringGoods
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public CateringGoods readCateringGoods(long id) throws CateringGoodsException{
		try{
			return this.cateringGoodsMapper.readCateringGoods(id);
		}catch(Exception e){
			logger.error("查询CateringGoods时报错", e);
			throw new CateringGoodsException("查询CateringGoods时报错", e);
		}
	}

	/**
	 * 查询cateringGoods集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public List<CateringGoods> queryCateringGoodsList(Map<String, Object> condition){
		try{
			return this.cateringGoodsMapper.queryCateringGoodsList(condition);
		}catch(Exception e){
			logger.error("查询CateringGoods时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringGoods集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public long queryCateringGoodsCount(Map<String, Object> condition) throws CateringGoodsException{
		try{
			return this.cateringGoodsMapper.queryCateringGoodsCount(condition);
		}catch(Exception e){
			logger.error("查询CateringGoods时报错", e);
			throw new CateringGoodsException("查询CateringGoods时报错", e);
		}
	}

}