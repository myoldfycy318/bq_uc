package com.qbao.store.service.impl.index;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.PassportType;
import com.bqiong.usercenter.exception.BizException;
import com.qbao.store.entity.index.EmailIndexEntity;
import com.qbao.store.helper.ShardHelper;
import com.qbao.store.mapper.index.EmailIndexMapper;
import com.qbao.store.service.index.EmailIndexService;
import com.qbao.store.util.UserIdGenerator;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月21日 下午3:56:25 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月21日 下午3:56:25 	修改人：
 *  	描述	:
 ***********************************************************
 */
@Service("emailIndexService")
public class EmailIndexServiceImpl implements EmailIndexService 
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EmailIndexMapper emailIndexMapper;
	
	@Autowired
	UserIdGenerator userIdGenerator;

	/**
	 * 
	 *  函数名称 ：insert
	 *  功能描述 ：  
	 *  参数说明 ：
	 *  	@param email
	 *  	@return
	 *  	@throws Exception
	 *  返回值：
	 *  	
	 *  修改记录：
	 *  日期 ：2016年4月21日 下午3:56:29	修改人：  
	 *  描述 ：
	 *
	 */
	@Override
	public EmailIndexEntity insert(String email) throws Exception 
	{
		String userId = null;
		try 
		{
			userId = userIdGenerator.generateUserId();
		} catch (Exception e) 
		{
			log.error("生成用户id失败,请检查序列表数据！", e);
			throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
		}
		if(StringUtils.isBlank(userId))
		{
			log.error("生成用户id为空！");
			throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
		}
		//String userId = MockUserIdGenerator.generateUserId();
		EmailIndexEntity emailIndexEntity = new EmailIndexEntity();
		emailIndexEntity.setEmail(email);
		emailIndexEntity.setUserId(userId);
		String tableName = ShardHelper.getIndexTableName(email, PassportType.email);
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，mobile=" + email);
			return null;
		}
		int result = emailIndexMapper.insert(emailIndexEntity, tableName);
		if(result != 1)
		{
			log.error("手机索引表入库失败，mobile =" + email + "tableName =" + tableName);
			return null;
		}
		return emailIndexEntity;
	}

	/**
	 * 
	 *  函数名称 ：queryByEmail
	 *  功能描述 ：  
	 *  参数说明 ：
	 *  	@param email
	 *  	@return
	 *  返回值：
	 *  	
	 *  修改记录：
	 *  日期 ：2016年4月21日 下午3:56:33	修改人：  
	 *  描述 ：
	 *
	 */
	@Override
	public EmailIndexEntity queryByEmail(String email) 
	{
		String tableName = ShardHelper.getIndexTableName(email, PassportType.email);
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，email=" + email);
			return null;
		}
		return emailIndexMapper.queryByEmail(email, tableName);
	}

	
	@Override
	public EmailIndexEntity insertWithUserId(String email, String userId) throws Exception 
	{
		EmailIndexEntity emailIndexEntity = new EmailIndexEntity();
		emailIndexEntity.setEmail(email);
		emailIndexEntity.setUserId(userId);
		String tableName = ShardHelper.getIndexTableName(email, PassportType.email);
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，mobile=" + email);
			return null;
		}
		int result = emailIndexMapper.insert(emailIndexEntity, tableName);
		if(result != 1)
		{
			log.error("手机索引表入库失败，mobile =" + email + "tableName =" + tableName);
			return null;
		}
		return emailIndexEntity;
	}
	
	@Override
	public boolean updateEmail(String userId, String passport) {
		
		int i = emailIndexMapper.updateEmail(userId,passport);
		return i == 1;
	}

}
