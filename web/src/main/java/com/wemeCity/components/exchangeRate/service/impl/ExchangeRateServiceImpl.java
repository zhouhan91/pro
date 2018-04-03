package com.wemeCity.components.exchangeRate.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.BigDecimalUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.HttpUtils;
import com.wemeCity.components.exchangeRate.dto.ExchangeRateBody;
import com.wemeCity.components.exchangeRate.dto.ExchangeRateResult;
import com.wemeCity.components.exchangeRate.exception.ExchangeRateException;
import com.wemeCity.components.exchangeRate.mapper.ExchangeRateMapper;
import com.wemeCity.components.exchangeRate.model.ExchangeRate;
import com.wemeCity.components.exchangeRate.service.ExchangeRateService;

/**
 * ExchangeRateServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-16 新建
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	private Logger logger = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

	@Autowired
	@Qualifier("yamlproperties")
	private Properties properties;

	/** 数据访问接口 */
	@Autowired
	private ExchangeRateMapper exchangeRateMapper;

	/**
	 * 新增exchangeRate
	 *
	 * @param exchangeRate
	 * @return 新增的对象
	 * @author 向小文 2017-12-16 新建
	 */
	@Override
	public void insertExchangeRate(ExchangeRate exchangeRate) throws ExchangeRateException {
		try {
			exchangeRateMapper.insertExchangeRate(exchangeRate);
		} catch (Exception e) {
			logger.error("新增ExchangeRate时报错", e);
			throw new ExchangeRateException("新增ExchangeRate时报错", e);
		}
	}

	/**
	 * 删除exchangeRate
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-16 新建
	 */
	@Override
	public int deleteExchangeRate(long id) throws ExchangeRateException {
		try {
			return this.exchangeRateMapper.deleteExchangeRate(id);
		} catch (Exception e) {
			logger.error("删除ExchangeRate时报错", e);
			throw new ExchangeRateException("删除ExchangeRate时报错", e);
		}
	}

	/**
	 * 修改exchangeRate
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-16 新建
	 */
	@Override
	public int updateExchangeRate(Map<String, Object> condition) throws ExchangeRateException {
		try {
			return this.exchangeRateMapper.updateExchangeRate(condition);
		} catch (Exception e) {
			logger.error("修改ExchangeRate时报错", e);
			throw new ExchangeRateException("修改ExchangeRate时报错", e);
		}
	}

	/**
	 * 查询exchangeRate
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-16 新建
	 */
	@Override
	public ExchangeRate readExchangeRate(long id) throws ExchangeRateException {
		try {
			return this.exchangeRateMapper.readExchangeRate(id);
		} catch (Exception e) {
			logger.error("查询ExchangeRate时报错", e);
			throw new ExchangeRateException("查询ExchangeRate时报错", e);
		}
	}

	/**
	 * 查询exchangeRate集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-16 新建
	 */
	@Override
	public List<ExchangeRate> queryExchangeRateList(Map<String, Object> condition) {
		try {
			return this.exchangeRateMapper.queryExchangeRateList(condition);
		} catch (Exception e) {
			logger.error("查询ExchangeRate时报错", e);
			return null;
		}
	}

	/**
	 * 查询exchangeRate集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-16 新建
	 */
	@Override
	public long queryExchangeRateCount(Map<String, Object> condition) throws ExchangeRateException {
		try {
			return this.exchangeRateMapper.queryExchangeRateCount(condition);
		} catch (Exception e) {
			logger.error("查询ExchangeRate时报错", e);
			throw new ExchangeRateException("查询ExchangeRate时报错", e);
		}
	}

	@Override
	public BaseDTO getExchangeRate() throws ExchangeRateException {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("isDeleted", Constants.NO);
			condition.put("sortColumn", "id");
			condition.put("sortType", "desc");
			Page<ExchangeRate> page = PageHelper.startPage(1, 1).doSelectPage(() -> exchangeRateMapper.queryExchangeRateList(condition));
			if (!CollectionUtils.isEmpty(page)) {
				ExchangeRate last = page.get(0);
				if (last.getUpdateTime().plusMinutes(30L).compareTo(LocalDateTime.now()) > 0) {
					return new BaseDTO(RequestResultEnum.SUCCESS, last);
				}
			}
			// 没有获取到任何数据或旧数据已经超过30分钟，则重新获取
			ExchangeRate exchangeRate = this.getLastExchangeRateFromJuhe();
			return new BaseDTO(RequestResultEnum.SUCCESS, exchangeRate);
		} catch (Exception e) {
			logger.error("获取汇率出错：服务器内部错误！", e);
			throw new ExchangeRateException("获取汇率出错：服务器内部错误！", e);
		}
	}

	private ExchangeRate getLastExchangeRateFromJuhe() throws ExchangeRateException {
		try {
			String appKey = properties.getProperty("system.juhe.appKey");
			final String exchangeRateHost = "http://op.juhe.cn";
			final String exchangeRatePath = "/onebox/exchange/query?key=" + appKey;
			HttpResponse response = HttpUtils.doGet(exchangeRateHost, exchangeRatePath, "get", null, null);
			String responBody = EntityUtils.toString(response.getEntity());
			logger.warn("请求聚合数据返回汇率信息：responBody={}", responBody);
			ExchangeRateBody rateBody = GsonUtils.getGson().fromJson(responBody, ExchangeRateBody.class);
			if ("0".equals(rateBody.getError_code())) {
				ExchangeRate exchangeRate = new ExchangeRate();
				ExchangeRateResult result = rateBody.getResult();
				exchangeRate.setUpdateTime(result.getUpdate());
				List<Object[]> list = result.getList();
				for (int i = 0; i < list.size(); i++) {
					Object[] arrDate = list.get(i);
					BigDecimal rate = BigDecimalUtils.divide(new BigDecimal(arrDate[5] + ""), new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);
					if ("美元".equals(arrDate[0])) {
						exchangeRate.setDollar(rate);
					} else if ("日元".equals(arrDate[0])) {
						exchangeRate.setYen(rate);
					} else if ("欧元".equals(arrDate[0])) {
						exchangeRate.setEuros(rate);
					} else if ("英镑".equals(arrDate[0])) {
						exchangeRate.setPound(rate);
					} else if ("港币".equals(arrDate[0])) {
						exchangeRate.setHk(rate);
					}
				}
				exchangeRate.setCreateBy(0);
				exchangeRate.setCreateTime(LocalDateTime.now());
				exchangeRate.setIsDeleted(Constants.NO);
				exchangeRateMapper.insertExchangeRate(exchangeRate);
				return exchangeRate;
			}
			return null;
		} catch (Exception e) {
			logger.error("从聚合数据获取汇率出错：服务器内部错误！", e);
			throw new ExchangeRateException("从聚合数据获取汇率出错：服务器内部错误！", e);
		}
	}
}