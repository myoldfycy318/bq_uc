package com.bqiong.usercenter.constants;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 下午3:03:26 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 下午3:03:26 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public enum LoginTypeEnum 
{
	passport,
	thirdpart,
	tourist;
	
	
	private LoginTypeEnum() 
	{
		
	}

    public static final LoginTypeEnum getByName(String name) 
	{
        for (LoginTypeEnum e : LoginTypeEnum.values()) 
        {
            if (e.name().equalsIgnoreCase(name))
            {
                return e;
            }
        }
        return null;
    }
}
