package com.qbao.store.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.qbao.store.entity.log.UserOperationLogEntity;
import com.qbao.store.service.log.AsyncLog2DBService;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年7月6日 下午3:33:46 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年7月6日 下午3:33:46 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
@Scope("prototype")
@Service("log2DBThread")
public class Log2DBThread implements Runnable 
{
	private UserOperationLogEntity entity;
	
	@Autowired
	private AsyncLog2DBService asyncLog2DBService;
	
	@Override
	public void run() 
	{
		asyncLog2DBService.insertLog2DB(entity);
	}

	public UserOperationLogEntity getEntity() 
	{
		return entity;
	}

	public void setEntity(UserOperationLogEntity entity) 
	{
		this.entity = entity;
	}
	
}
