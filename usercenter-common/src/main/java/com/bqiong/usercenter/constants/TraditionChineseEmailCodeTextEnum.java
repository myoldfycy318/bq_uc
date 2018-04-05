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
public enum TraditionChineseEmailCodeTextEnum 
{
	login("%s"),
	register("您此次申請註冊的驗證碼為:%s，工作人員不會向您索取，請勿泄露。如非本人操作，請忽略該郵件。"),
	getUserInfo("%s"),
	resetPassword("您此次申請找回密碼的驗證碼為:%s，工作人員不會向您索取，請勿泄露。如非本人操作，請忽略該郵件。"),
	modifyPassword("%s"),
	bind("您正在綁定郵箱號，驗證碼為:%s，工作人員不會向您索取，請勿泄露。如非本人操作，請忽略本條信息。"),
	changeBind("%s"),
	idcard("%s");
	
	private String text;
	
	private TraditionChineseEmailCodeTextEnum(String text) 
	{
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public static final TraditionChineseEmailCodeTextEnum getFromKey(String text) 
	{
        for (TraditionChineseEmailCodeTextEnum e : TraditionChineseEmailCodeTextEnum.values()) 
        {
            if (e.name().equalsIgnoreCase(text))
            {
                return e;
            }
        }
        return null;
    }
	
}
