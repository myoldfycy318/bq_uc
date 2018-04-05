package com.qbao.store.service;

import com.qbao.store.entity.user.UserEntity;

/**
 * Created by admin on 2017/7/18.
 */
public interface UserService {

    public UserEntity queryByDomeId(Integer thirdId, String userId,String tableName,String seqId) throws Exception;

    public UserEntity queryUserByUserId(String tableName,String userId) throws Exception;


    public boolean updateUserById(String tableName,String seqId,UserEntity userEntity) throws Exception;

    public UserEntity queryUserById(String tableName,String seqId) throws Exception;

    public String queryUserIdById(String tableName, String seqId);
}
