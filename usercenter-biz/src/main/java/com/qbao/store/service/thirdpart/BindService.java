package com.qbao.store.service.thirdpart;

import com.qbao.store.entity.thirdpart.BindEntity;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 下午5:16:14 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 下午5:16:14 	修改人：
 *  	描述	:
 ***********************************************************
 */
public interface BindService 
{
    /**
     * 
     *  函数名称 : queryByThird
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param thirdId
     *  	@param openUid
     *  	@return
     *  	@throws Exception
     *
     *  修改记录：
     *  	日期 ：2016年5月17日 下午5:52:38	修改人：  niuzan
     *  	描述	：
     *
     */
    public BindEntity queryByThird(String thirdId, String openUid) throws Exception;
    
    /**
     * 
     *  函数名称 : queryByDomeId
     *  功能描述 :  
     *  参数及返回值说明：
     * @param thirdId TODO
     * @param userId
     * @return
     *
     *  修改记录：
     *  	日期 ：2016年5月17日 下午5:54:07	修改人：  niuzan
     *  	描述	：
     *
     */
    public BindEntity queryByDomeId(String thirdId, String userId) throws Exception;
    
    /**
     * 
     *  函数名称 : insert
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param entity
     *  	@throws Exception
     *
     *  修改记录：
     *  	日期 ：2016年5月18日 上午11:37:13	修改人：  niuzan
     *  	描述	：
     *
     */
    public void bind(BindEntity entity) throws Exception;
    
    /**
     * 
     *  函数名称 : isBind
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param thirdId
     *  	@param openId
     *  	@param userId
     *  	@return
     *
     *  修改记录：
     *  	日期 ：2016年5月18日 下午12:25:36	修改人：  niuzan
     *  	描述	：
     *
     */
    public boolean isBind(String thirdId, String openId, String userId) throws Exception ;
    
}
