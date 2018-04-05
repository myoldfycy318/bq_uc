package com.qbao.store.service.impl.log;

import com.bqiong.usercenter.annotation.DefDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.store.entity.log.UserOperationLogEntity;
import com.qbao.store.helper.ShardHelper;
import com.qbao.store.mapper.log.UserOperationLogEntityMapper;
import com.qbao.store.service.log.AsyncLog2DBService;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年7月6日 下午3:46:22 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年7月6日 下午3:46:22 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
@Service("asyncLog2DBService")
public class AsyncLog2DBServiceImpl implements AsyncLog2DBService 
{
	@Autowired
	private UserOperationLogEntityMapper userOperationLogEntityMapper;
	
	private static final Logger log = LoggerFactory.getLogger(AsyncLog2DBServiceImpl.class);
	
	/**
	 * 
	 *  函数名称 ：log2DB
	 *  功能描述 ：  
	 *  参数说明 ：
	 *  	@param entity
	 *  返回值：
	 *  	
	 *  修改记录：
	 *  日期 ：2016年7月6日 下午3:46:29	修改人：  niuzan
	 *  描述 ：
	 *
	 */
    @DefDataSource
	@Override
	public void insertLog2DB(UserOperationLogEntity entity) 
	{
		String tableName = ShardHelper.getLogTableName(entity.getOperationTime());
		log.info("执行异步日志入库！tableName=" + tableName + ",entity=" + entity);
		try {
			userOperationLogEntityMapper.insert(entity, tableName);
		} catch (Exception e) {
			log.error("用户日志异步入库失败！", e);
		}
	}
	
}
