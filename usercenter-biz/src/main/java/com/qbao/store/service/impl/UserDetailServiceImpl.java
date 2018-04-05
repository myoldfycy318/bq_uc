package com.qbao.store.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.exception.BizException;
import com.qbao.store.entity.user.UserDetailEntity;
import com.qbao.store.helper.ShardHelper;
import com.qbao.store.mapper.user.UserDetailMapper;
import com.qbao.store.service.UserDetailService;

@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailService
{
	private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserDetailMapper userDetailMapper;

	@Override
	public void deleteById(String userId) 
	{
		String tableName = ShardHelper.getUserDetailTableName(userId);
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，userId=" + userId);
		}
		log.info("分表结果为tableName=" + tableName);
		userDetailMapper.deleteById(userId, userId);
	}

	@Override
	public List<UserDetailEntity> queryAllWithPagination(Integer pageNum, Integer eachPageCount) 
	{
		return null;
	}

	@Override
	public UserDetailEntity insert(UserDetailEntity userDetailEntity) throws Exception 
	{
		if(userDetailEntity == null || StringUtils.isBlank(userDetailEntity.getUserId()))
		{
			log.error("数据异常，userBasicEntity=" + JSONObject.toJSONString(userDetailEntity));
			return null;
		}
		String tableName = ShardHelper.getUserDetailTableName(userDetailEntity.getUserId());
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，userId=" + userDetailEntity.getUserId());
			return null;
		}
		int result = userDetailMapper.insert(userDetailEntity, tableName);
		if(result != 1)
		{
			log.error("入库失败，userBasicEntity=" + userDetailEntity);
			return null;
		}
		else
		{
			return userDetailMapper.queryById(userDetailEntity.getUserId(), tableName);
		}
	}

	@Override
	public void update(UserDetailEntity userEntity) throws Exception
	{
		String tableName = ShardHelper.getUserDetailTableName(userEntity.getUserId());
		int result = userDetailMapper.update(userEntity, tableName);
		if(result != 1)
		{
		    log.error("用户信息更新失败！");
		    throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
		}
	}

	@Override
	public UserDetailEntity queryById(String userId)
	{
		String tableName = ShardHelper.getUserDetailTableName(userId);
		if(StringUtils.isBlank(tableName))
		{
			log.error("分表信息获取失败，userId=" + userId);
			return null;
		}
		log.info("分表结果为tableName=" + tableName);
		return userDetailMapper.queryById(userId, tableName);
	}

	@Override
	public UserDetailEntity queryRandomUserByGender(String gender) 
	{
		String tableName = ShardHelper.getRandomDetailTableName();
		UserDetailEntity userDetailEntity = userDetailMapper.queryRandomUserByGender(gender, tableName);
		while(userDetailEntity == null)
		{
			tableName = getNextTableName(tableName);
			userDetailEntity = userDetailMapper.queryRandomUserByGender(gender, tableName);
		}
		return userDetailEntity;
	}
	
	private String getNextTableName(String tableName)
	{
		String oriSuffix = StringUtils.substringAfterLast(tableName, "_");
		String temp = String.valueOf(Integer.parseInt(oriSuffix) + 1);
		if(temp.length() == 1) return StringUtils.substringBeforeLast(tableName, "_") + "_0" + temp;
		if(temp.length() == 2) return StringUtils.substringBeforeLast(tableName, "_") + "_" + temp;
		if(temp.length() == 3) return StringUtils.substringBeforeLast(tableName, "_") + "_00";
		return StringUtils.substringBeforeLast(tableName, "_") + "_00";
	}
}
