package com.qbao.store.mapper.user;

import com.qbao.store.entity.user.UserCard;
import com.qbao.store.entity.user.UserCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCardMapper {
    long countByExample(UserCardExample example);

    int deleteByExample(UserCardExample example);

    int deleteByPrimaryKey(String uid);

    int insert(UserCard record);

    int insertSelective(UserCard record);

    List<UserCard> selectByExample(UserCardExample example);

    UserCard selectByPrimaryKey(@Param("uid") String uid);

    int updateByExampleSelective(@Param("record") UserCard record, @Param("example") UserCardExample example);

    int updateByExample(@Param("record") UserCard record, @Param("example") UserCardExample example);

    int updateByPrimaryKeySelective(UserCard record);

    int updateByPrimaryKey(UserCard record);
}