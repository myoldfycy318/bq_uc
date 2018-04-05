package com.qbao.store.service;

import com.bqiong.usercenter.constants.PassportType;
import com.qbao.store.entity.openPlatform.OpenPlatformBasicEntity;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserBasicEntity;
import com.qbao.store.entity.user.UserEntity;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：niuzan
 *  创建时间	：2016年4月12日 上午11:03:52 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月12日 上午11:03:52 	修改人：
 *  	描述	:
 ***********************************************************
 */
public interface RegisterService 
{
    /**
     *
     *  函数名称 : register
     *  功能描述 : 注册
     *  参数及返回值说明：
     * @param requestData TODO
     *  	@return
     *
     */
    public UserEntity register(RequestData requestData,PassportType type) throws Exception;
    public UserBasicEntity register(String countryCode, String passport, String password, String gender, String buId, String avatarUrl, String channelId, String clientId) throws Exception;

    /**
     * 
     *  函数名称 : register4OpenPlatform
     *  功能描述 :  
     *  参数及返回值说明：
     *  	@param passport
     * @param password
     *  	@return
     *  	@throws Exception
     *
     *  修改记录：
     *  	日期 ：2016年5月9日 下午4:38:31	修改人：  niuzan
     *  	描述	：
     *
     */
    public OpenPlatformBasicEntity register4OpenPlatform(String passport, String password) throws Exception;

}
