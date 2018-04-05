package com.qbao.store.service.impl;

import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.mapper.user.UserMapper;
import com.qbao.store.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/7/18.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户是否有钱宝账号的绑定,前端通过这个确定是否展示钱宝支付
     *
     * @param thirdId
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public UserEntity queryByDomeId(Integer thirdId, String userId, String tableName, String seqId) throws Exception {
        return userMapper.queryByDomeId(thirdId, userId, tableName, seqId);
    }


    @Override
    public UserEntity queryUserByUserId(String tableName, String userId) throws Exception {
        return userMapper.queryUserByUserId(tableName, userId);
    }

    @Override
    public boolean updateUserById(String tableName, String seqId, UserEntity userEntity) throws Exception {
        return userMapper.updateUserById(tableName, seqId, userEntity);
    }

    @Override
    public UserEntity queryUserById(String tableName, String seqId) throws Exception {
        return userMapper.queryUserById(tableName, seqId);
    }

    public String queryUserIdById(String tableName, String seqId) {
        return userMapper.queryUserIdById(tableName, seqId);
    }
}
