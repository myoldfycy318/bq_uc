package com.qbao.store.mapper.user;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qbao.store.entity.user.UserRegisterExtraBean;

@Repository
public interface UserRegisterExtraBeanMapper 
{
    /**
     * 向表user_register_profile_0中插入数据.
     */
    int insert(@Param("entity")UserRegisterExtraBean entity, @Param("tableName")String tableName);

    /**
     * 通过id查询表user_register_profile_0.
     */
    UserRegisterExtraBean selectByUserId(@Param("userId")String userId, @Param("tableName")String tableName);
    
    /**
     * 通过id查询表user_register_profile_0.
     */
    UserRegisterExtraBean selectByChannelId(@Param("channelId")String channelId, @Param("tableName")String tableName);

}