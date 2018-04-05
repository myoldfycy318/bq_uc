package com.qbao.store.service;

import com.bqiong.usercenter.constants.PassportType;
import com.qbao.store.entity.openPlatform.OpenPlatformBasicEntity;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;


public interface PasswordService {

    public UserEntity resetPassword(RequestData requestData, PassportType passportType) throws Exception;

    public OpenPlatformBasicEntity resetPassword4Open(String passport, String password) throws Exception;

}
