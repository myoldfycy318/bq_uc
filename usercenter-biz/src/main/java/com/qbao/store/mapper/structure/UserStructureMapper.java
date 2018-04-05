package com.qbao.store.mapper.structure;

import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.entity.user.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserStructureMapper
 *
 * @author Zhang ShanMin
 * @date 2017/7/10
 * @time 13:59
 */
@Repository
public interface UserStructureMapper {

    boolean createUserTable(@Param("userTable") String userTable);

    boolean insertUserInfo(@Param("userTable") String userTable, @Param("user") UserInfo userInfo);

    boolean insertUsers(@Param("userTable") String userTable, @Param("list") List<UserInfo> userInfos);

    List<UserEntity> queryUserInfoList();

    List<UserEntity> queryTouristUsers();

}
