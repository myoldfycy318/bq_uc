package com.qbao.store.service.impl.index;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.PassportType;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.exception.BizException;
import com.qbao.store.entity.index.MobileIndexEntity;
import com.qbao.store.helper.ShardHelper;
import com.qbao.store.mapper.index.MobileIndexMapper;
import com.qbao.store.service.index.MobileIndexService;
import com.qbao.store.util.UserIdGenerator;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月15日 下午12:23:08 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月15日 下午12:23:08 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
@Service("mobileIndexService")
public class MobileIndexServiceImpl implements MobileIndexService 
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MobileIndexMapper mobileIndexMapper;
	
	@Autowired
	UserIdGenerator userIdGenerator;

	/**
	 * 
	 *  函数名称 ：insert
	 *  功能描述 ：  
	 *  参数说明 ：
	 * @param mobile
	 *  	@return
	 *  	@throws Exception
	 *  返回值：
	 *  	
	 *  修改记录：
	 *  日期 ：2016年4月15日 下午12:23:24	修改人：  
	 *  描述 ：
	 *
	 */
	@Override
	public MobileIndexEntity insert(String countryCode, String mobile) throws Exception 
	{
		String userId = null;
		if(StringUtils.isBlank(countryCode))
		{
			countryCode = UserCenterConstants.DEFAULT_COUNTRY_CODE;
		}
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
		MobileIndexEntity mobileIndexEntity = buildEntity(countryCode, mobile, userId);
		String tableName = ShardHelper.getIndexTableName(mobile, PassportType.mobile);
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，mobile=" + mobile);
			return null;
		}
		int result = mobileIndexMapper.insert(mobileIndexEntity, tableName);
		if(result != 1)
		{
			log.error("手机索引表入库失败，mobile =" + mobile + "tableName =" + tableName);
			return null;
		}
		return queryByMobile(countryCode, mobile);
	}
	
	@Override
	public MobileIndexEntity insertWithUserId(String countryCode, String mobile, String userId) throws Exception 
	{
		MobileIndexEntity mobileIndexEntity = buildEntity(countryCode, mobile, userId);
		String tableName = ShardHelper.getIndexTableName(mobile, PassportType.mobile);
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，mobile=" + mobile);
			return null;
		}
		int result = mobileIndexMapper.insert(mobileIndexEntity, tableName);
		if(result != 1)
		{
			log.error("手机索引表入库失败，mobile =" + mobile + "tableName =" + tableName);
			return null;
		}
		return queryByMobile(countryCode, mobile);
	}

	@Override
	public MobileIndexEntity queryByMobile(String countryCode, String mobile) 
	{
		String tableName = ShardHelper.getIndexTableName(mobile, PassportType.mobile);
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，mobile=" + mobile);
			return null;
		}
		return mobileIndexMapper.queryByMobile(countryCode, mobile, tableName);
	}
	
	/**
	 * 
	 *  函数名称 : buildEntity
	 *  功能描述 :  
	 *  参数及返回值说明：
	 * @param countryCode TODO
	 * @param mobile
	 * @param userId
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月15日 下午4:42:34	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	private MobileIndexEntity buildEntity(String countryCode, String mobile, String userId)
	{
		MobileIndexEntity mobileIndexEntity = new MobileIndexEntity();
		mobileIndexEntity.setCountryCode(countryCode);
		mobileIndexEntity.setMobile(mobile);
		mobileIndexEntity.setUserId(userId);
		return mobileIndexEntity;
	}

	@Override
	public void updateMobile(String userId, String mobile) throws Exception 
	{
		String tableName = ShardHelper.getIndexTableName(mobile, PassportType.mobile);
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，mobile=" + mobile);
			return;
		}
		MobileIndexEntity mobileIndexEntity = new MobileIndexEntity();
		mobileIndexEntity.setMobile(mobile);
		mobileIndexEntity.setUserId(userId);
		int result = mobileIndexMapper.updateMobile(mobileIndexEntity, tableName);
		if(result != 1)
		{
			log.error("update index row error!userId=" +userId +",mobile=" + mobile);
			throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
		}
	}
	
	@Override
	public void updateMobileWithCountryCode(String userId, String mobile,String countryCode) throws Exception 
	{
		String tableName = ShardHelper.getIndexTableName(mobile, PassportType.mobile);
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，mobile=" + mobile);
			return;
		}
		MobileIndexEntity mobileIndexEntity = new MobileIndexEntity();
		mobileIndexEntity.setCountryCode(countryCode);
		mobileIndexEntity.setMobile(mobile);
		mobileIndexEntity.setUserId(userId);
		int result = mobileIndexMapper.updateMobile(mobileIndexEntity, tableName);
		if(result != 1)
		{
			log.error("update index row error!userId=" +userId +",mobile=" + mobile);
			throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
		}
	}

	@Override
	public void delete(String mobile, String userId)
	{
		String tableName = ShardHelper.getIndexTableName(mobile, PassportType.mobile);
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，mobile=" + mobile);
			return;
		}
		int result = mobileIndexMapper.delete(userId, tableName);
		if(result != 1)
		{
			log.error("delete index row error!userId=" +userId +",mobile=" + mobile);
			throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
		}
	}

}
