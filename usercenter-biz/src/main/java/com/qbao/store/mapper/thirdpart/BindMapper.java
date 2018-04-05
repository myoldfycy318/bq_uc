package com.qbao.store.mapper.thirdpart;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qbao.store.entity.thirdpart.BindEntity;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 下午6:21:37 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 下午6:21:37 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
@Repository
public interface BindMapper {

	/**
	 * 
	 *  函数名称 : insert
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param mobileIndexEntity
	 *  	@param tableName
	 *  	@return
	 *  	@throws Exception
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月15日 上午11:45:30	修改人：  niuzan
	 *  	描述	：
	 *
	 */
    int insert(@Param("entity") BindEntity entity, @Param("tableName") String tableName) throws Exception;

    
    /**
     * 
     *  函数名称 : queryByOpenUid
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param openId
     *  	@param tableName
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年5月17日 下午6:31:18	修改人：  niuzan
     *  	描述	：
     *
     */
    BindEntity queryByThirdUid(@Param("openId") String openId, @Param("tableName") String tableName);
    
    /**
     * 
     *  函数名称 : queryByDomeUid
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param userId
     *  	@param tableName
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年5月18日 上午11:22:38	修改人：  niuzan
     *  	描述	：
     *
     */
    BindEntity queryByDomeUid(@Param("thirdId") String thirdId, @Param("userId") String userId, @Param("tableName") String tableName);
    
    /**
     * 
     *  函数名称 : deleteByUid
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param uid
     *  	@param tableName
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年5月18日 上午11:20:32	修改人：  
     *  	描述	：
     *
     */
    int deleteByThirdUid(@Param("openId") String openId, @Param("tableName") String tableName);
    
    
    /**
     * 
     *  函数名称 : deleteByDomeUid
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param userId
     *  	@param tableName
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年5月18日 上午11:33:31	修改人：  
     *  	描述	：
     *
     */
    int deleteByDomeUid(@Param("userId") String userId, @Param("tableName") String tableName);
    
}