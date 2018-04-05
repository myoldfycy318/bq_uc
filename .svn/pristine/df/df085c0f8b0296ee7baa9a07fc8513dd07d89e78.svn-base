
package com.qbao.store.mapper.openPlatform;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qbao.store.entity.openPlatform.OpenPlatformBasicEntity;

@Repository
public interface OpenPlatformBasicMapper {
    
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
    int deleteById(@Param("userId") String userId);

  /**
   * 
   *  函数名称 : insert
   *  功能描述 :  
   *  参数及返回值说明：
   *  	@param userEntity
   *  	@param tableName
   *  	@return
   *  	@throws Exception
   *
   *  修改记录：
   *  	日期 ：2016年4月15日 上午11:33:34	修改人：  
   *  	描述	：
   *
   */
    int insert(@Param("entity") OpenPlatformBasicEntity entity) throws Exception;

    /**
     * 
     *  函数名称 : queryById
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param userid
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年4月15日 上午11:33:28	修改人：  
     *  	描述	：
     *
     */
    OpenPlatformBasicEntity queryById(@Param("userId") String userId);
    
    /**
     * 
     *  函数名称 : queryByMobile
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param mobile
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年5月11日 上午11:05:27	修改人：  nz
     *  	描述	：
     *
     */
    OpenPlatformBasicEntity queryByMobile(@Param("mobile") String mobile);
    
    /**
     * 
     *  函数名称 : queryByEmail
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param email
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年5月11日 上午11:05:46	修改人：  nz
     *  	描述	：
     *
     */
    OpenPlatformBasicEntity queryByEmail(@Param("email") String email);

	  /**
	   * 
	   *  函数名称 : updateById
	   *  功能描述 :  
	   *  参数及返回值说明：
	   *  	@param userEntity
	   *  	@param tableName
	   *  	@return
	   *
	   *  修改记录：
	   *  	日期 ：2016年4月15日 上午11:33:17	修改人：  
	   *  	描述	：
	   *
	   */
    int update(@Param("entity") OpenPlatformBasicEntity entity) throws Exception;
    
    
    
    
}