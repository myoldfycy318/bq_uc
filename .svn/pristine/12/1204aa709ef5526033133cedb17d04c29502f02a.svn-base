package com.qbao.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.store.entity.user.UserRegisterExtraBean;
import com.qbao.store.helper.ShardHelper;
import com.qbao.store.mapper.user.UserRegisterExtraBeanMapper;
import com.qbao.store.service.UserRegisterExtraService;

@Service("userRegisterExtraService")
public class UserRegisterExtraServiceImpl implements UserRegisterExtraService {
	
	@Autowired
	private UserRegisterExtraBeanMapper userRegisterExtraBeanMapper;

	@Override
	public void addRegisterExtra(String userId, String channelId) 
	{
		String tableName = ShardHelper.getUserExtraTableName(userId);
		UserRegisterExtraBean userRegisterExtraBean = new UserRegisterExtraBean(userId, channelId);
		userRegisterExtraBeanMapper.insert(userRegisterExtraBean, tableName);
	}

	@Override
	public UserRegisterExtraBean queryExtraByUserId(String userId) 
	{
		String tableName = ShardHelper.getUserExtraTableName(userId);
		UserRegisterExtraBean userRegisterExtraBean = userRegisterExtraBeanMapper.selectByUserId(userId, tableName);
		return userRegisterExtraBean;
	}

}
