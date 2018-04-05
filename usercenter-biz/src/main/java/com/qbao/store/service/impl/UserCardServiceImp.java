package com.qbao.store.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.exception.BizException;
import com.qbao.store.entity.user.UserCard;
import com.qbao.store.mapper.user.UserCardMapper;
import com.qbao.store.service.UserCardService;

/**
 * Created by hunsy on 2017/5/5.
 */
@Service
public class UserCardServiceImp implements UserCardService {

    private Logger logger = LoggerFactory.getLogger(UserCardServiceImp.class);
    /**
     * 用于存放已验证用户uid的redis的set的key
     */
    public static final String USER_IDCARD_CHECKED = UserCenterConstants.REDIS_USERCENTER_APPLICATION + "idcard:hash";


    @Autowired
    private UserCardMapper mapper;

    @Override
    public UserCard findById(String uid) {

        return mapper.selectByPrimaryKey(uid);
    }

    @Override
    public void insert(UserCard userCard) throws Exception {
        userCard.setCreatedAt(new Date());
        if (mapper.insert(userCard) == 0) {
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
        }
    }
}
