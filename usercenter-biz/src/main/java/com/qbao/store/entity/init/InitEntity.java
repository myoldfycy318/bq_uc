package com.qbao.store.entity.init;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年8月2日 下午4:09:36 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年8月2日 下午4:09:36 	修改人：
 *  	描述	:
 ***********************************************************
 */
public class InitEntity
{
	private String name;
	
	private String value;
	
	private String buId;

	public String getBuId() {
		return buId;
	}

	public void setBuId(String buId) {
		this.buId = buId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "InitEntity [name=" + name + ", value=" + value + ", buId=" + buId + "]";
	}
	
}
