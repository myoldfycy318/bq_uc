package com.bqiong.usercenter.constants;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月12日 下午1:24:59 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月12日 下午1:24:59 	修改人：
 *  	描述	:
 ***********************************************************
 */
public enum PasswordType
{
	_old,_new,_confirm;
	
	public static final PasswordType getFromKey(String passwordType) 
	{
        for (PasswordType e : PasswordType.values())
        {
            if (e.toString().equals(passwordType))
            {
                return e;
            }
        }
        return null;
    }
}