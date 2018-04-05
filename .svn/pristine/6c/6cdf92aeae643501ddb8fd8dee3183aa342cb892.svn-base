package com.qbao.store.service.impl.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.store.mapper.sequence.SequenceMapper;
import com.qbao.store.service.sequence.SequenceService;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月19日 下午2:52:24 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月19日 下午2:52:24 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
@Service("sequenceService")
public class SequenceServiceImpl implements SequenceService {

	@Autowired
	SequenceMapper sequenceMapper;
	/**
	 * 
	 *  函数名称 ：getNextVal
	 *  功能描述 ：  
	 *  参数说明 ：
	 *  	@param sequenceName
	 *  	@return
	 *  返回值：
	 *  	
	 *  修改记录：
	 *  日期 ：2016年4月19日 下午2:52:20	修改人：  niuzan
	 *  描述 ：
	 *
	 */
	@Override
	public Integer getNextVal(String sequenceName) 
	{
		return sequenceMapper.queryNextVal(sequenceName);
	}

}
