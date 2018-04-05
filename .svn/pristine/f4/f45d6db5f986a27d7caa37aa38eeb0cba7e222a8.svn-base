
package com.qbao.store.mapper.user;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qbao.store.entity.user.UserAllInOne;
import com.qbao.store.entity.user.UserBasicEntity;

@Repository
public interface UserBasicMapper {
    
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
    int insert(@Param("userEntity") UserBasicEntity userEntity, @Param("tableName") String tableName) throws Exception;

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
    UserBasicEntity queryById(@Param("userId") String userId, @Param("tableName") String tableName);
    
    /**
     * 
     * @param userId
     * @param tableName
     * @return
     */
    UserBasicEntity queryRandomUserByGender(@Param("gender") String gender, @Param("tableName") String tableName);

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
    int update(@Param("userEntity") UserBasicEntity userEntity, @Param("tableName") String tableName) throws Exception;
    
    /**
     * 
     * @param userEntity
     * @param tableName
     * @return
     * @throws Exception
     */
    int updateMobile(@Param("userId") String userId, @Param("mobile") String mobile, @Param("tableName") String tableName) throws Exception;

    /**
     * 获取用户的所有信息
     * 
     * @param userId
     */
    UserAllInOne queryAllInfo(@Param("userId")String userId,@Param("suffix")String suffix);
    
    
    
}