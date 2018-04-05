package com.qbao.store.service.impl.openPlatform;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.store.entity.openPlatform.OpenPlatformBasicEntity;
import com.qbao.store.mapper.openPlatform.OpenPlatformBasicMapper;
import com.qbao.store.service.openPlatform.OpenPlatformService;

@Service("openPlatformService")
public class OpenPlatformServiceImpl implements OpenPlatformService
{
	private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private OpenPlatformBasicMapper openPlatformBasicMapper;

	@Override
	public void deleteById(String userId) 
	{
		openPlatformBasicMapper.deleteById(userId);
	}

	@Override
	public List<OpenPlatformBasicEntity> queryAllWithPagination(Integer pageNum, Integer eachPageCount) 
	{
		return null;
	}

	@Override
	public OpenPlatformBasicEntity insert(OpenPlatformBasicEntity openPlatformBasicEntity) throws Exception 
	{
		if((StringUtils.isBlank(openPlatformBasicEntity.getEmail()) && StringUtils.isBlank(openPlatformBasicEntity.getMobile())) 
				|| StringUtils.isBlank(openPlatformBasicEntity.getUserId()))
		{
			log.error("数据异常，OpenPlatformBasicEntity=" + openPlatformBasicEntity);
			return null;
		}
		int result = openPlatformBasicMapper.insert(openPlatformBasicEntity);
		if(result != 1)
		{
			log.error("入库失败，OpenPlatformBasicEntity=" + openPlatformBasicEntity);
			return null;
		}
		else
		{
			return openPlatformBasicMapper.queryById(openPlatformBasicEntity.getUserId());
		}
	}

	@Override
	public int update(OpenPlatformBasicEntity userEntity) throws Exception
	{
		return openPlatformBasicMapper.update(userEntity);
	}

	@Override
	public OpenPlatformBasicEntity queryById(String userId) 
	{
		return openPlatformBasicMapper.queryById(userId);
	}

	@Override
	public OpenPlatformBasicEntity queryByMobile(String mobile) 
	{
		return openPlatformBasicMapper.queryByMobile(mobile);
	}

	@Override
	public OpenPlatformBasicEntity queryByEmail(String email) 
	{
		return openPlatformBasicMapper.queryByEmail(email);
	}

}
