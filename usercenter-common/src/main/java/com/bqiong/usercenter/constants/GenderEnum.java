package com.bqiong.usercenter.constants;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月20日 下午12:15:11 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月20日 下午12:15:11 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public enum GenderEnum 
{
    M, F, N;
    
    public static final GenderEnum getFromKey(String gender) 
    {
        for (GenderEnum e : GenderEnum.values())
        {
            if (e.name().equalsIgnoreCase(gender))
            {
                return e;
            }
        }
        return null;
    }
    
}
