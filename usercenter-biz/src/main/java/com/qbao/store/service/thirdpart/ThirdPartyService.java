package com.qbao.store.service.thirdpart;

import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;

/**
 * QBaoService
 *
 * @author Zhang ShanMin
 * @date 2017/7/22
 * @time 15:51
 */
public interface ThirdPartyService {

    public UserEntity login(RequestData requestData) throws Exception;
}
