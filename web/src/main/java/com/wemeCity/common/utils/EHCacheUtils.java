package com.wemeCity.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EHCacheUtils {

	private static Logger logger = LoggerFactory.getLogger(EHCacheUtils.class);

	/**
	 * 将数据放入缓存
	 *
	 * @param cacheName
	 * @param key
	 * @param value
	 * @Author Xiang xiaowen 2017年6月5日 新建
	 */
	public static void put(String cacheName, String key, Object value) {
		logger.debug("put object to ehcache:[cacheName={}, key={}, value={}]", new Object[] { cacheName, key, value });
		if (StringUtils.isEmpty(cacheName) || StringUtils.isEmpty(key) || value == null) {
			return;
		}
		CacheManager cacheManager = CacheManager.create();
		Cache cache = cacheManager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}

	/**
	 * 从缓存中获取值
	 *
	 * @param cacheName
	 * @param key
	 * @return
	 * @Author Xiang xiaowen 2017年6月5日 新建
	 */
	public static Object get(String cacheName, String key) {
		logger.debug("get object from ehcache:cacheName={},key={}", cacheName, key);
		if (StringUtils.isEmpty(cacheName) || StringUtils.isEmpty(key)) {
			return null;
		}
		CacheManager cacheManager = CacheManager.create();
		Cache cache = cacheManager.getCache(cacheName);
		Element element = cache.get(key);
		if (element == null) {
			return null;
		}
		Object value = element.getObjectValue();
		logger.debug("get object from ehcache:[cacheName={},key={},value={}]", new Object[] { cacheName, key, value });
		return value;
	}

	/**
	 * 从缓存中移除
	 *
	 * @param cacheName
	 * @param key
	 * @return
	 * @Author Xiang xiaowen 2017年6月14日 新建
	 */
	public static boolean remove(String cacheName, String key) {
		logger.debug("remove object from ehcache:cacheName={},key={}", cacheName, key);
		if (StringUtils.isEmpty(cacheName) || StringUtils.isEmpty(key)) {
			return false;
		}
		CacheManager cacheManager = CacheManager.create();
		Cache cache = cacheManager.getCache(cacheName);
		return cache.remove(key);
	}

}
