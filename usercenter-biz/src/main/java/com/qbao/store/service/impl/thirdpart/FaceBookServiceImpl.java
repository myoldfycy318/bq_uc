package com.qbao.store.service.impl.thirdpart;

import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.service.thirdpart.ThirdPartyAdapterService;
import com.qbao.store.service.thirdpart.ThirdPartyService;
import org.springframework.stereotype.Service;

/**
 * QBaoServiceImpl
 *
 * @author Zhang ShanMin
 * @date 2017/7/22
 * @time 15:53
 */
@Service("faceBookService")
public class FaceBookServiceImpl extends ThirdPartyAdapterService implements ThirdPartyService {


    @Override
    public UserEntity login(RequestData requestData) throws Exception {
        return registerIfNE(requestData);
    }
}
