package com.wemeCity.common.service.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池单例对象
 *
 * @author Xiang xiaowen
 * @since JDK1.7
 * @history 2017年1月9日 创建
 */
public class CommonThreadPool extends ThreadPoolExecutor {

	/** 单实例对象 */
	private static CommonThreadPool dataCollectThreadPool = new CommonThreadPool(3, 5, 4, TimeUnit.MINUTES,
			new LinkedBlockingQueue<Runnable>(10240));

	/**
	 * 重写构造函数以构造单例对象
	 * 
	 * @param corePoolSize 核心线程数
	 * @param maximumPoolSize 最大线程数
	 * @param keepAliveTime 超线程在无任务执行时最多等待销毁时间
	 * @param unit keepAliveTime的时间单位
	 * @param workQueue 工作队列
	 */
	public CommonThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	public static CommonThreadPool getInstance() {
		return dataCollectThreadPool;
	}
}
