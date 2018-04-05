package com.qbao.store.util;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqiong.usercenter.constants.UserCenterConstants;
import com.qbao.store.service.sequence.SequenceService;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月15日 下午4:49:56 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月15日 下午4:49:56 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
@Service
public class MockUserIdGenerator
{
	@Autowired
	SequenceService sequenceService;
	
	/**
	 * 
	 *  函数名称 : generateUserId
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月19日 下午2:56:00	修改人：  
	 *  	描述	：
	 *
	 */
	public static String generateUserId()
	{
		int max=9;
	    int min=0;
        Random random = new Random();
        String s = "";
        for(int i = 0; i < 7; i++)
        {
        	s = random.nextInt(max)%(max-min+1) + min + s;
        }
        return UserCenterConstants.BQ_PREFIX + s + "00";
	}
	public static void main(String[] args) {
		System.out.println(generateUserId());
	}
}
