package com.bqiong.usercenter.constants;
/**
 * **********************************************************
 *  内容摘要	：<p>繁体字版本的短信提醒</p>
 *
 *  作者	：liuzhongwei
 *  创建时间	：2017年3月13日 上午17:36:58 
 *  当前版本号：v1.0
 ***********************************************************
 */
public enum TraditionChineseMobileCodeTextEnum {
	
	
	login("%s"),
	register("您此次申請註冊的驗證碼為:%s，工作人員不會向您索取，請勿洩露。如非本人操作，請忽略本條簡訊。"),
	getUserInfo("%s"),
	resetPassword("您此次申請找回密碼的驗證碼為:%s，工作人員不會向您索取，請勿泄露。如非本人操作，請忽略本條短信。"),
	bind("您正在綁定手機號，驗證碼為:%s，工作人員不會向您索取，請勿泄露。如非本人操作，請忽略本條短信。"),
	modifyPassword("%s"),
	changeBind("%s"),
	idcard("%s");
	
	private String text;
	
	private TraditionChineseMobileCodeTextEnum(String text) 
	{
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public static final MobileCodeTextEnum getFromKey(String text) 
	{
        for (MobileCodeTextEnum e : MobileCodeTextEnum.values()) 
        {
            if (e.name().equalsIgnoreCase(text))
            {
                return e;
            }
        }
        return null;
    }

}
