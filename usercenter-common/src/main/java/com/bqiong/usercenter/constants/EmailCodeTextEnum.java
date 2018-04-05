package com.bqiong.usercenter.constants;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月15日 上午10:19:58 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月15日 上午10:19:58 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public enum EmailCodeTextEnum 
{
	login(""),
	register("您此次申请注册的验证码为:%s，工作人员不会向您索取，请勿泄露。如非本人操作，请忽略该邮件。"),
	getUserInfo(""),
	resetPassword("您此次的验证码为:%s，工作人员不会向您索取，请勿泄露。如非本人操作，请忽略该邮件。"),
	modifyPassword(""),
	bind("您此次的验证码为:%s，工作人员不会向您索取，请勿泄露。如非本人操作，请忽略本条信息。"),
	changeBind("您此次的验证码为:%s，工作人员不会向您索取，请勿泄露。如非本人操作，请忽略该邮件。"),
	idcard("%s");
	
	private String text;
	
	private EmailCodeTextEnum(String text) 
	{
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public static final EmailCodeTextEnum getFromKey(String text) 
	{
        for (EmailCodeTextEnum e : EmailCodeTextEnum.values()) 
        {
            if (e.name().equalsIgnoreCase(text))
            {
                return e;
            }
        }
        return null;
    }
	
}
