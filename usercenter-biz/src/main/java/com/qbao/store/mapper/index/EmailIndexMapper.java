package com.qbao.store.mapper.index;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qbao.store.entity.index.EmailIndexEntity;

@Repository
public interface EmailIndexMapper {

	/**
	 * 
	 *  函数名称 : insert
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param emailIndexEntity
	 *  	@param tableName
	 *  	@return
	 *  	@throws Exception
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月15日 下午12:15:47	修改人：  niuzan
	 *  	描述	：
	 *
	 */
    int insert(@Param("emailIndexEntity") EmailIndexEntity emailIndexEntity, @Param("tableName") String tableName) throws Exception;

    
   /**
    * 
    *  函数名称 : queryByEmail
    *  功能描述 :  
    *  参数及返回值说明：
    *  	@param email
    *  	@param tableName
    *  	@return
    *
    *  修改记录：
    *  	日期 ：2016年4月15日 下午12:15:40	修改人：  niuzan
    *  	描述	：
    *
    */
    EmailIndexEntity queryByEmail(@Param("email") String email, @Param("tableName") String tableName);

    /**
     * 更新
     * @param userId
     * @param passport
     * @return
     */
    int updateEmail(@Param("userId")String userId, @Param("email")String passport);

}