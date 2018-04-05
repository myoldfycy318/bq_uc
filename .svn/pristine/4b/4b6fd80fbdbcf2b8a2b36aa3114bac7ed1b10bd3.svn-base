package com.qbao.store.service.thirdpart;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.qbao.store.entity.request.RequestData;

/**
 * QbaoUserService
 *
 * @author Zhang ShanMin
 * @date 2017/11/27
 * @time 10:28
 */
public interface QbaoUserService {


    /**
     * 根据账户获取钱宝username
     *
     * @param account
     * @return
     */
    JSONObject qurUameNameByAccount(String account);


    /**
     * 根据钱宝username获取钱宝userId
     *
     * @param username
     * @return
     */
    String queryQBUserName(String username);


    /**
     * 根据钱宝userId加载钱宝用户信息
     *
     * @param userId
     * @return
     */
    JSONObject loadQbaoUserInfo(String userId);


    /**
     * 校验签名登录密码
     * @param qbUserId
     * @param password
     * @return
     */
    Boolean checkQbaoUserPw(String qbUserId, String password);


    /**
     * 钱宝账户登录
     * @param requestData
     * @return
     */
    BaseResponse qbaoAccountLogin(RequestData requestData);
}
