package com.qbao.store.service.index;

import com.qbao.store.entity.index.MobileIndexEntity;

public interface MobileIndexService 
{
	/**
	 * 
	 *  函数名称 : insert
	 *  功能描述 :  
	 *  参数及返回值说明：
	 * @param countryCode TODO
	 * @param mobile
	 *  	@return
	 *  	@throws Exception
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月15日 下午12:21:15	修改人：  niuzan
	 *  	描述	：将生成userId放在mapper层
	 *
	 */
	MobileIndexEntity insert(String countryCode, String mobile) throws Exception;
	
	/**
	 * 
	 * @param countryCode TODO
	 * @param mobile
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public MobileIndexEntity insertWithUserId(String countryCode, String mobile, String userId) throws Exception;
	
	/**
	 * 
	 * @param userId
	 * @param mobile
	 * @throws Exception
	 */
	public void updateMobile(String userId, String mobile) throws Exception;
	
	public void updateMobileWithCountryCode(String userId, String mobile,String countryCode) throws Exception ;
	
	/**
	 * 
	 *  函数名称 : queryByMobile
	 *  功能描述 :  
	 *  参数及返回值说明：
	 * @param countryCode TODO
	 * @param mobile
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月15日 下午12:21:47	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	MobileIndexEntity queryByMobile(String countryCode, String mobile);
	
	/**
	 * 
	 * @param mobile
	 * @param userId
	 */
	public void delete(String mobile, String userId);
	
}
