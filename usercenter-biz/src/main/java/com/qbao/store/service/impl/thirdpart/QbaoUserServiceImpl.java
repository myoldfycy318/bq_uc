package com.qbao.store.service.impl.thirdpart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.PropertiesUtil;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.service.thirdpart.QbaoUserService;
import com.qbao.store.util.ApiConnector;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * QbaoUserServiceImpl
 *
 * @author Zhang ShanMin
 * @date 2017/11/27
 * @time 10:29
 */
@Service("qbaoUserService")
public class QbaoUserServiceImpl implements QbaoUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PropertiesUtil propertiesUtil;

    /**
     * 根据账户获取钱宝username
     *
     * @param account
     * @return
     */
    @Override
    public JSONObject qurUameNameByAccount(String account) {
        JSONObject obj = null;
        String url = propertiesUtil.getString("qbao.qur.uame.by.acount.url");
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        paramsList.add(new BasicNameValuePair("account", account));
        paramsList.add(new BasicNameValuePair("appId", propertiesUtil.getString("qbao.user.uc.appid")));
        String response = ApiConnector.post(url, paramsList);
        //logger.info("根据账户获取钱宝username,请求url:{},请求参数：{},响应报文：{}", url, JSONObject.toJSONString(paramsList), response);
        if (StringUtils.isBlank(response) || (obj = JSON.parseObject(response)) == null || 1 != obj.getIntValue("code") || null == obj.getString("data")) {
            return null;
        }
        return obj.getJSONObject("data");
    }


    /**
     * 根据钱宝username获取钱宝userId
     *
     * @param username
     * @return
     */
    @Override
    public String queryQBUserName(String username) {
        JSONObject jsonObject = null;
        String url = propertiesUtil.getString("qbao.userid.get.url");
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        paramsList.add(new BasicNameValuePair("userName", username));
        paramsList.add(new BasicNameValuePair("appId", propertiesUtil.getString("qbao.user.uc.appid")));
        String response = ApiConnector.post(url, paramsList);
        //logger.info("根据userName获取钱宝用户userid,请求url:{},请求参数：{},响应报文：{}", url, JSONObject.toJSONString(paramsList), response);
        if (StringUtils.isNotBlank(response) && (jsonObject = JSONObject.parseObject(response)) != null && 1 == jsonObject.getInteger("code") && StringUtils.isNotBlank(jsonObject.getString("data"))) {
            return jsonObject.getString("data");
        }
        return null;
    }

    /**
     * 根据钱宝userId加载钱宝用户信息
     *
     * @param userId
     * @return
     */
    public JSONObject loadQbaoUserInfo(String userId) {
        String url = propertiesUtil.getString("qbao.user.detail.url");
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        paramsList.add(new BasicNameValuePair("userId", userId));
        paramsList.add(new BasicNameValuePair("appId", propertiesUtil.getString("qbao.user.uc.appid")));
        String response = ApiConnector.post(url, paramsList);
        //logger.info("查询钱宝用户信息,请求url:{},请求参数：{},响应报文：{}", url, JSONObject.toJSONString(paramsList), response);
        if (StringUtils.isBlank(response)) {
            return null;
        }
        JSONObject obj = JSON.parseObject(response);
        if (1 != obj.getIntValue("code") || obj == null) {
            return null;
        }
        return obj.getJSONObject("data");
    }


    /**
     * 钱宝用户登录密码校验
     *
     * @param qbUserId
     * @param password
     * @return
     */
    @Override
    public Boolean checkQbaoUserPw(String qbUserId, String password) {
        JSONObject jsonObject = null;
        String result = null;
        try {
            List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
            paramsList.add(new BasicNameValuePair("appId", propertiesUtil.getString("qbao.user.uc.appid")));
            paramsList.add(new BasicNameValuePair("userId", qbUserId));
            paramsList.add(new BasicNameValuePair("password", password));
            String url = propertiesUtil.getString("pbao.user.check.password.url");
            result = ApiConnector.post(url, paramsList);
            //logger.info("校验钱宝登录密码，请求url:{}，请求参数：{},响应报文：{}", url, JSONObject.toJSONString(paramsList), result);
            if (StringUtils.isNotBlank(result) && (jsonObject = JSONObject.parseObject(result)) != null && 1 == jsonObject.getInteger("code") && StringUtils.isNotBlank(jsonObject.getString("data"))) {
                return jsonObject.getBoolean("data");
            }
        } catch (Exception e) {
            logger.error(">>>>>>>>校验钱宝登录密码异常:", e);
        }
        return false;
    }


    /**
     * 钱宝账户登录
     *
     * @param requestData
     * @return
     */
    @Override
    public BaseResponse qbaoAccountLogin(RequestData requestData) {
        JSONObject jsonObject = qurUameNameByAccount(requestData.getPassport());
        if (jsonObject == null || StringUtils.isBlank(jsonObject.getString("username")))
            return BaseResponse.fail(ErrorCode.USER_NOT_EXIST);
        String qbaoUserId = queryQBUserName(jsonObject.getString("username"));
        if (StringUtils.isBlank(qbaoUserId)) return BaseResponse.fail(ErrorCode.USER_NOT_EXIST);
        requestData.setQbaoUid(qbaoUserId);
        requestData.setUserName(jsonObject.getString("username"));
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String signPwd = md5PasswordEncoder.encodePassword(requestData.getPassword(), jsonObject.getString("username")).toLowerCase();
        Boolean result = checkQbaoUserPw(qbaoUserId, signPwd);
        if (!result) return BaseResponse.fail(ErrorCode.PASSWORD_WRONG);
        return BaseResponse.success();
    }
}
