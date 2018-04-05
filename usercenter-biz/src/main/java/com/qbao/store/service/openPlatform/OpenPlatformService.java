package com.qbao.store.service.openPlatform;

import java.util.List;

import com.qbao.store.entity.openPlatform.OpenPlatformBasicEntity;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：niuzan
 *  创建时间	：2016年4月12日 上午11:13:31 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月12日 上午11:13:31 	修改人：
 *  	描述	:
 ***********************************************************
 */
public interface OpenPlatformService {
    
    /**
     * 
     *  函数名称 : insert
     *  功能描述 :  
     *  参数及返回值说明：
     * @param passportType TODO
     * @param tableName TODO
     * @param user
     * @return
     *
     *  修改记录：
     *  	日期 ：2016年4月12日 上午11:40:55	修改人：  niuzan
     *  	描述	：
     *
     */
	public OpenPlatformBasicEntity insert(OpenPlatformBasicEntity OpenPlatformBasicEntity) throws Exception;
	
	/**
	 * 
	 *  函数名称 : queryById
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param userId
	 * @param passportType TODO
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月13日 下午5:50:23	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	public OpenPlatformBasicEntity queryById(String userId);
	
	/**
	 * 
	 *  函数名称 : queryByMobile
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param mobile
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年5月9日 下午4:43:36	修改人：  nz
	 *  	描述	：
	 *
	 */
	public OpenPlatformBasicEntity queryByMobile(String mobile);
	
	/**
	 * 
	 *  函数名称 : queryByEmail
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param email
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年5月9日 下午4:43:54	修改人：  nz
	 *  	描述	：
	 *
	 */
	public OpenPlatformBasicEntity queryByEmail(String email);

	/**
	 * 
	 *  函数名称 : updateByUserId
	 *  功能描述 :  
	 *  参数及返回值说明：
	 * @param passportType TODO
	 * @param user
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月12日 上午11:37:13	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	public int update(OpenPlatformBasicEntity userEntity) throws Exception;
	
	/**
	 * 
	 *  函数名称 : deleteById
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param userId
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月12日 上午11:40:12	修改人：  niuzan
	 *  	描述	：
	 * @param passportType TODO
	 *
	 */
	public void deleteById(String userId);
	
	/**
	 * 
	 *  函数名称 : queryAll
	 *  功能描述 :  分页返回
	 *  参数及返回值说明：
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月12日 上午11:41:21	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	public List<OpenPlatformBasicEntity> queryAllWithPagination(Integer pageNum, Integer eachPageCount);
	
	/**
	 * 根据token获取用户信息
	 *  函数名称 : getByToken
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param token
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月13日 下午3:21:12	修改人：  
	 *  	描述	：
	 *
	 */
}
