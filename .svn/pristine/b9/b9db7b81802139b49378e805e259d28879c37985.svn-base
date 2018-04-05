package com.qbao.store.service.index;

import com.qbao.store.entity.index.EmailIndexEntity;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月21日 下午3:55:10 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月21日 下午3:55:10 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public interface EmailIndexService 
{
	/**
	 * 
	 *  函数名称 : insert
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param email
	 *  	@return
	 *  	@throws Exception
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月21日 下午3:55:49	修改人：  
	 *  	描述	：
	 *
	 */
	EmailIndexEntity insert(String email) throws Exception;
	
	/**
	 * 
	 * @param email
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	EmailIndexEntity insertWithUserId(String email, String userId) throws Exception;
		
	/**
	 * 
	 *  函数名称 : queryByEmail
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param email
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月21日 下午3:55:42	修改人：  
	 *  	描述	：
	 *
	 */
	EmailIndexEntity queryByEmail(String email);

	/**
	 * 更新
	 * @param userId
	 * @param passport
	 * @return
	 */
	boolean updateEmail(String userId, String passport);
	
}
