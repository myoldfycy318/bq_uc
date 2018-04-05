package com.qbao.store.service;

import java.util.List;

import com.qbao.store.entity.init.InitEntity;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：niuzan
 *  创建时间	：2016年4月12日 上午11:13:31 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年8月12日 上午11:13:31 	修改人：
 *  	描述	:
 ***********************************************************
 */
public interface InitService {
	
	/**
	 * 
	 *  函数名称 : queryAll
	 *  功能描述 :  分页返回
	 *  参数及返回值说明：
	 * @param name TODO
	 * @param buId TODO
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月12日 上午11:41:21	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	public List<InitEntity> query(String name, String buId);
	
	public void add(InitEntity record);
	
	public void modify(InitEntity record);
	
	public InitEntity queryById(Integer id);
	
}
