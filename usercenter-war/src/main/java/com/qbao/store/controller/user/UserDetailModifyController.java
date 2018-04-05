package com.qbao.store.controller.user;

import com.bqiong.usercenter.annotation.OperateType;
import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.service.UserService;
import com.qbao.store.util.BizUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.qbao.store.controller.BaseController;
import com.qbao.store.huanxin.service.impl.TalkDataServiceImpl;
import com.qbao.store.util.TokenUtil;

import java.util.Date;

/**
 * **********************************************************
 * 内容摘要	：<p>
 * <p/>
 * 作者	：94841
 * 创建时间	：2016年4月29日 下午2:18:48
 * 当前版本号：v1.0
 * 历史记录	:
 * 日期	: 2016年4月29日 下午2:18:48 	修改人：
 * 描述	:
 * **********************************************************
 */
@Controller
@RequestMapping("/user")
public class UserDetailModifyController extends BaseController {

    TalkDataServiceImpl huanXinService = TalkDataServiceImpl.getInstance();
    @Autowired
    private UserService userService;


    @OperateType(bizType = BizType.modifyAttr)
    @RequestMapping("/modAvatar")
    @ResponseBody
    public String modAvatar(RequestData requestData) {
        try {
            if (StringUtils.isBlank(requestData.getAvatarUrl())) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.AVATAR_PARAM_NULL));
            }
            String userValue = RedisUtil.hget(RedisContant.USER_BASIC_IDX, requestData.getUserId());
            UserEntity _userEntity = null;
            if (StringUtils.isBlank(userValue) || (_userEntity = JSONObject.parseObject(userValue, UserEntity.class)) == null) {
                log.error("缓存用户不存在");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.USER_NOT_EXIST));
            }
            log.info("查询所得用户信息redis_user=" + userValue);
            String[] dbTblIdx = requestData.getDbTblIdx().split("-");
            TokenUtil.validateTokenSign(requestData.getAccessToken(), _userEntity.getSalt());

            if (StringUtils.equals(requestData.getAvatarUrl(), _userEntity.getAvatarUrl())) {
                return JSONObject.toJSONString(BaseResponse.success());
            }
            _userEntity.setAvatarUrl(requestData.getAvatarUrl());
            UserEntity newUserBuilder = new UserEntity.Builder().avatarUrl(_userEntity.getAvatarUrl()).updateTime(new Date()).build();
            if (!userService.updateUserById(dbTblIdx[1], dbTblIdx[2], newUserBuilder)) {
                log.error("用户信息不存在,修改头像失败!userId:{}", requestData.getUserId());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            BizUtil.updateUserCache(_userEntity);//更新缓存信息
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("token=" + requestData.getAccessToken() + ",clientId=" + requestData.getClientId() + ",buId=" + requestData.getBuId(), e);
        }
    }

    @OperateType(bizType = BizType.modifyAttr)
    @RequestMapping("/modGender")
    @ResponseBody
    public String modGender(RequestData requestData) {
        try {
            if (StringUtils.isBlank(requestData.getGender())) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.GENDER_PARAM_NULL));
            }
            String userValue = RedisUtil.hget(RedisContant.USER_BASIC_IDX, requestData.getUserId());
            UserEntity _userEntity = null;
            if (StringUtils.isBlank(userValue) || (_userEntity = JSONObject.parseObject(userValue, UserEntity.class)) == null) {
                log.error("缓存用户不存在,userId=" + requestData.getUserId());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.USER_NOT_EXIST));
            }
            log.info("查询所得用户信息redis_user=" + userValue);
            String[] dbTblIdx = requestData.getDbTblIdx().split("-");
            TokenUtil.validateTokenSign(requestData.getAccessToken(), _userEntity.getSalt());
            if (StringUtils.equals(requestData.getGender(), _userEntity.getGender())) {
                return JSONObject.toJSONString(BaseResponse.success());
            }
            _userEntity.setGender(GenderEnum.getFromKey(requestData.getGender()) == null ? GenderEnum.N.name() : GenderEnum.getFromKey(requestData.getGender()).name());
            UserEntity newUserBuilder = new UserEntity.Builder().gender(_userEntity.getGender()).updateTime(new Date()).build();
            if (!userService.updateUserById(dbTblIdx[1], dbTblIdx[2], newUserBuilder)) {
                log.error("用户信息不存在,修改性别失败!");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            BizUtil.updateUserCache(_userEntity);//更新缓存信息
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("token=" + requestData.getAccessToken() + ",clientId=" + requestData.getClientId() + ",buId=" + requestData.getBuId(), e);
        }
    }

    @OperateType(bizType = BizType.modifyAttr)
    @RequestMapping("/modAge")
    @ResponseBody
    public String modAge(RequestData requestData) {
        try {
            String userValue = RedisUtil.hget(RedisContant.USER_BASIC_IDX, requestData.getUserId());
            UserEntity _userEntity = null;
            if (StringUtils.isBlank(userValue) || (_userEntity = JSONObject.parseObject(userValue, UserEntity.class)) == null) {
                log.error("缓存用户不存在,userId=" + requestData.getUserId());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.USER_NOT_EXIST));
            }
            log.info("查询所得用户信息redis_user=" + userValue);
            String[] dbTblIdx = requestData.getDbTblIdx().split("-");
            TokenUtil.validateTokenSign(requestData.getAccessToken(), _userEntity.getSalt());

            if (requestData.getAge() == _userEntity.getAge()) {
                return JSONObject.toJSONString(BaseResponse.success());
            }
            _userEntity.setAge(requestData.getAge());
            UserEntity newUserBuilder = new UserEntity.Builder().age(_userEntity.getAge()).updateTime(new Date()).build();
            if (!userService.updateUserById(dbTblIdx[1], dbTblIdx[2], newUserBuilder)) {
                log.error("用户信息不存在,修改年龄失败!");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            BizUtil.updateUserCache(_userEntity);//更新缓存信息
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("token=" + requestData.getAccessToken() + ",clientId=" + requestData.getClientId() + ",buId=" + requestData.getBuId(), e);
        }
    }

    @OperateType(bizType = BizType.modifyAttr)
    @RequestMapping("/modUserName")
    @ResponseBody
    public String modUserName(RequestData requestData) {
        try {
            String userValue = RedisUtil.hget(RedisContant.USER_BASIC_IDX, requestData.getUserId());
            UserEntity _userEntity = null;
            if (StringUtils.isBlank(userValue) || (_userEntity = JSONObject.parseObject(userValue, UserEntity.class)) == null) {
                log.error("缓存用户不存在,userId=" + requestData.getUserId());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.USER_NOT_EXIST));
            }
            log.info("查询所得用户信息redis_user=" + userValue);
            String[] dbTblIdx = requestData.getDbTblIdx().split("-");
            TokenUtil.validateTokenSign(requestData.getAccessToken(), _userEntity.getSalt());

            if (StringUtils.equals(requestData.getUserName(), _userEntity.getUserName())) {
                return JSONObject.toJSONString(BaseResponse.success());
            }
            _userEntity.setUserName(requestData.getUserName());
            UserEntity newUserBuilder = new UserEntity.Builder().userName(_userEntity.getUserName()).updateTime(new Date()).build();
            if (!userService.updateUserById(dbTblIdx[1], dbTblIdx[2], newUserBuilder)) {
                log.error("用户信息不存在,修改昵称失败!");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            BizUtil.updateUserCache(_userEntity);//更新缓存信息
            //修改环信昵称
            try {
                huanXinService.userModifyNickname(requestData.getUserId(), requestData.getUserName());
            } catch (Exception e) {
                log.info("修改环信昵称发生异常:" + requestData.getUserId(), e);
            }
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("token=" + requestData.getAccessToken() + ",clientId=" + requestData.getClientId() + ",buId=" + requestData.getBuId() + ",userName=" + requestData.getUserName(), e);
        }
    }


}
