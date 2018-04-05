package com.qbao.store.mapper.index;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qbao.store.entity.index.MobileIndexEntity;

@Repository
public interface MobileIndexMapper {

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
    int insert(@Param("mobileIndexEntity") MobileIndexEntity mobileIndexEntity, @Param("tableName") String tableName) throws Exception;

    
    /**
     * 
     *  函数名称 : queryByMobile
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param mobile
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年4月15日 上午11:46:27	修改人：  niuzan
     *  	描述	：
     *
     */
    MobileIndexEntity queryByMobile(@Param("countryCode") String countryCode, @Param("mobile") String mobile, @Param("tableName") String tableName);
    
    /**
     * 
     * @param mobileIndexEntity
     * @param tableName
     * @return
     */
    int updateMobile(@Param("mobileIndexEntity") MobileIndexEntity mobileIndexEntity, @Param("tableName") String tableName);
    
    /**
     * 
     * @param userId
     * @param tableName
     * @return
     */
    int delete(@Param("userId")String userId, @Param("tableName")String tableName);

}