package com.bqiong.usercenter.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年7月6日 下午4:11:28 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年7月6日 下午4:11:28 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public class ThreadPoolUtil
{

	protected static Logger log = LoggerFactory.getLogger(ThreadPoolUtil.class);

	/**
	 * 默认线程池大小
	 */
	public static final int DEFAULT_POOL_SIZE = 1000;

	private static volatile ExecutorService executorService;

	private static final ExecutorService destroyExecutorService = Executors.newSingleThreadExecutor();

	public static void load()
	{
		log.info("开始初始化线程池！");
		final ExecutorService tempExecutorService = executorService;
		executorService = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);
		// 刷新后释放原线程池
		if (tempExecutorService != null)
		{
			destroyExecutorService.execute(new Runnable()
			{
				@Override
				public void run()
				{
					destoryExecutorService(tempExecutorService, 60000);
				}
			});

		}
	}

	public static void destoryExecutorService(ExecutorService executorService, long timeout)
	{
		if (executorService != null && !executorService.isShutdown())
		{
			try
			{
				executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e)
			{
				log.error(e.getMessage(), e);
			}
			executorService.shutdown();
		}
	}

	/**
	 * 使用线程池中的线程来执行任务
	 */
	public static void execute(Runnable task)
	{
		if (executorService == null)
		{
			load();
		}
		executorService.execute(task);
	}

}
