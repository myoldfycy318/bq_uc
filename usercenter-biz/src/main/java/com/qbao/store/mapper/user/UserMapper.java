package com.qbao.store.mapper.user;

import com.qbao.store.entity.user.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/7/12.
 */
@Repository
public interface UserMapper {

    boolean updateUserById(@Param("tableName") String tableName, @Param("seqId") String seqId, @Param("user") UserEntity userEntity);

    UserEntity queryUserById(@Param("tableName") String tableName, @Param("seqId") String seqId);

    /**
     * 查询用户是否有钱宝账号的绑定,前端通过这个确定是否展示钱宝支付
     *
     * @param thirdId
     * @param userId
     * @param tableName
     * @param seqId
     * @return
     */
    UserEntity queryByDomeId(@Param("thirdId") Integer thirdId, @Param("userId") String userId, @Param("tableName") String tableName, @Param("seqId") String seqId);

    UserEntity queryUserByUserId(@Param("tableName") String tableName, @Param("userId") String userId);

    String queryUserIdById(@Param("tableName") String tableName, @Param("seqId") String seqId);
}
