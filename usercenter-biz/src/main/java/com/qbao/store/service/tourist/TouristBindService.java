package com.qbao.store.service.tourist;


import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;

public interface TouristBindService {


    /**
     * @return
     * @throws Exception
     */
    public UserEntity touristLogin(RequestData requestData) throws Exception;

    /**
     * 游客绑定
     *
     * @param requestData
     * @return
     * @throws Exception
     */
    public UserEntity bind(RequestData requestData) throws Exception;

}
