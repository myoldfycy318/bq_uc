package com.qbao.store.service;

import java.util.List;

import com.qbao.store.entity.user.UserDetailEntity;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月19日 下午12:10:08 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月19日 下午12:10:08 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public interface UserDetailService {
    
    /**
     * 
     *  函数名称 : insert
     *  功能描述 :  
     *  参数及返回值说明：
     * @param tableName TODO
     * @param user
     * @return
     *
     *  修改记录：
     *  	日期 ：2016年4月12日 上午11:40:55	修改人：  niuzan
     *  	描述	：
     *
     */
	public UserDetailEntity insert(UserDetailEntity userBasicEntity) throws Exception;
	
	/**
	 * 
	 *  函数名称 : queryById
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param userId
	 * @return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月13日 下午5:50:23	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	public UserDetailEntity queryById(String userId);
	
	/**
	 * 
	 * @param gender
	 * @return
	 */
	public UserDetailEntity queryRandomUserByGender(String gender);
	
	/**
	 * 
	 *  函数名称 : updateByUserId
	 *  功能描述 :  
	 *  参数及返回值说明：
	 * @param user
	 *
	 */
	public void update(UserDetailEntity userEntity) throws Exception;
	
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
	public List<UserDetailEntity> queryAllWithPagination(Integer pageNum, Integer eachPageCount);
	
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
