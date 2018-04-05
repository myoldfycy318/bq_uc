package com.qbao.store.util;

import java.util.ArrayList;
import java.util.List;

import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.RequestDataHelper;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年7月6日 下午3:21:53 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年7月6日 下午3:21:53 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public class NeedBuildErrorDataList 
{
	private static List<ErrorCode> needBuildErrorDataList = new ArrayList<ErrorCode>();
	
	private static List<ErrorCode> excludeNeedBuildErrorDataList = new ArrayList<ErrorCode>();
	
	
	public static void init()
	{
		needBuildErrorDataList.add(ErrorCode.PASSWORD_WRONG);
		needBuildErrorDataList.add(ErrorCode.USER_NOT_EXIST);
		needBuildErrorDataList.add(ErrorCode.PASSPORT_NOT_EXIST);
		needBuildErrorDataList.add(ErrorCode.CAPTCHA_NULL);
		needBuildErrorDataList.add(ErrorCode.CAPTCHA_WRONG);
		excludeNeedBuildErrorDataList.add(ErrorCode.SEND_SMS_ONCE_MIN);
		excludeNeedBuildErrorDataList.add(ErrorCode.SEND_SMS_LIMIT);
	}
	
	public static boolean isNeedBuildData(ErrorCode errorCode)
	{
		if(errorCode == null && RequestDataHelper.isNeedRiskUri(UserCenterContext.getReqestData().getUri()))
		{
			return true;
		}
		if(!RequestDataHelper.isNeedRiskUri(UserCenterContext.getReqestData().getUri())
				|| excludeNeedBuildErrorDataList.contains(errorCode))
		{
			return false;
		}
		for(ErrorCode errCode : needBuildErrorDataList)
		{
			if(errorCode != null && errCode == errorCode);
			{
				return true;
			}
		}
		return false;
	}
	
}
