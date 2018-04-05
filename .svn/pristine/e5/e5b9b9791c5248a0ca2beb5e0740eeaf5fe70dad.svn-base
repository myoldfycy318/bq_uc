
package com.qbao.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qbao.store.entity.ClientEntity;

@Repository
public interface ClientMapper
{
	 /**
	  * 
	  *  函数名称 : insert
	  *  功能描述 :  
	  *  参数及返回值说明：
	  *  	@param entity
	  *  	@return
	  *
	  *  修改记录：
	  *  	日期 ：2016年4月28日 下午4:12:34	修改人：  nz
	  *  	描述	：
	  *
	  */
	 int insert(@Param("entity") ClientEntity entity);
    
	/**
	 * 
	 *  函数名称 : deleteById
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param clientId
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月28日 下午4:12:40	修改人：  nz
	 *  	描述	：
	 *
	 */
    int deleteById(@Param("clientId") String clientId);



	  /**
	   * 
	   *  函数名称 : queryById
	   *  功能描述 :  
	   *  参数及返回值说明：
	   *  	@param clientId
	   *  	@return
	   *
	   *  修改记录：
	   *  	日期 ：2016年4月28日 下午4:12:46	修改人：  nz
	   *  	描述	：
	   *
	   */
    ClientEntity queryById(@Param("clientId") String clientId);

	  /**
	   * 
	   *  函数名称 : updateById
	   *  功能描述 :  
	   *  参数及返回值说明：
	   *  	@param entity
	   *  	@return
	   *
	   *  修改记录：
	   *  	日期 ：2016年4月28日 下午4:13:04	修改人：  
	   *  	描述	：
	   *
	   */
    int updateById(@Param("entity") ClientEntity entity);
    
    /**
     * 
     *  函数名称 : queryAll
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年4月29日 下午3:57:37	修改人：  
     *  	描述	：
     *
     */
    List<ClientEntity> queryAll();
    
    /**
     * 
     *  函数名称 : query4OpenPlatform
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年5月9日 下午3:05:31	修改人：  nz
     *  	描述	：
     *
     */
    List<ClientEntity> query4OpenPlatform();
    
    
}