package com.qbao.store.service.impl.thirdpart;

import com.bqiong.usercenter.util.PropertiesUtil;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.service.thirdpart.ThirdPartyAdapterService;
import com.qbao.store.service.thirdpart.ThirdPartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * QBaoServiceImpl
 *
 * @author Zhang ShanMin
 * @date 2017/7/22
 * @time 15:53
 */
@Service("qbaoService")
public class QBaoServiceImpl extends ThirdPartyAdapterService implements ThirdPartyService {
    private final static Logger logger = LoggerFactory.getLogger(QBaoServiceImpl.class);

    @Resource(name = "propertiesUtil")
    private PropertiesUtil propertiesUtil;

    @Override
    public UserEntity login(RequestData requestData) throws Exception {
        //TODO 钱宝用户在uc是否有绑定？钱宝用户在passport中心是无法登录?钱宝用户注册类型？
        UserEntity userEntity = registerIfNE(requestData);
        return userEntity;
    }

}
