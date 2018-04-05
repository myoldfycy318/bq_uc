package com.qbao.store.service.structure;

import com.qbao.store.entity.user.UserInfo;

import java.util.List;

/**
 * UserStructureService
 *
 * @author Zhang ShanMin
 * @date 2017/7/10
 * @time 15:57
 */
public interface UserStructureService {

    boolean createUserTable(String tableName);

    boolean insertUserInfo(String suffix, UserInfo userInfo);

    boolean insertUsers(String userTable, List<UserInfo> userInfos);

    void importOldUserInfo();

    void loadTourist2cache();
}
