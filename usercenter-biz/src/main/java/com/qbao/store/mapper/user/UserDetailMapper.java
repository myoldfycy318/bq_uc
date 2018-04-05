package com.qbao.store.mapper.user;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qbao.store.entity.user.UserDetailEntity;

@Repository
public interface UserDetailMapper {
    
	/**
	 * 
	 *  函数名称 : deleteById
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param userid
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月12日 上午11:43:02	修改人：  niuzan
	 *  	描述	：
	 *
	 */
    int deleteById(@Param("userId") String userId, @Param("tableName") String tableName);

    
    /**
     * 
     *  函数名称 : insert
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param entity
     *  	@param tableName
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年5月19日 上午11:51:57	修改人：  niuzan
     *  	描述	：
     *
     */
    int insert(@Param("entity") UserDetailEntity entity, @Param("tableName") String tableName);

    /**
     * 
     *  函数名称 : queryById
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param userid
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年4月12日 上午11:44:54	修改人：  niuzan
     *  	描述	：
     *
     */
    UserDetailEntity queryById(@Param("userId") String userId, @Param("tableName") String tableName);
    
    /**
     * 
     * @param gender
     * @param tableName
     * @return
     */
    UserDetailEntity queryRandomUserByGender(@Param("gender") String gender, @Param("tableName") String tableName);

    /**
     * 
     *  函数名称 : updateById
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param record
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年4月12日 上午11:45:18	修改人：  niuzan
     *  	描述	：
     *
     */
    int update(@Param("entity") UserDetailEntity entity, @Param("tableName") String tableName);
    
    
}