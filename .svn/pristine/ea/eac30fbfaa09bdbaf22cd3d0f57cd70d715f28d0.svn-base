package com.qbao.store.service;

import java.util.List;

import com.qbao.store.entity.user.UserAllInOne;
import com.qbao.store.entity.user.UserBasicEntity;

/**
 * **********************************************************
 * 内容摘要	：<p>
 * <p>
 * 作者	：niuzan
 * 创建时间	：2016年4月12日 上午11:13:31
 * 当前版本号：v1.0
 * 历史记录	:
 * 日期	: 2016年4月12日 上午11:13:31 	修改人：
 * 描述	:
 * **********************************************************
 */
public interface UserBasicService {

    /**
     * 函数名称 : insert
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @param userBasicEntity TODO
     * @return 修改记录：
     * 日期 ：2016年4月12日 上午11:40:55	修改人：  niuzan
     * 描述	：
     */
    public UserBasicEntity insert(UserBasicEntity userBasicEntity) throws Exception;

    /**
     * 函数名称 : queryById
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @param userId
     * @return 修改记录：
     * 日期 ：2016年4月13日 下午5:50:23	修改人：  niuzan
     * 描述	：
     */
    public UserBasicEntity queryById(String userId);

    /**
     * @param userId
     * @param mobile
     * @return
     * @throws Exception
     */
    public int updateMobile(String userId, String mobile) throws Exception;

    /**
     * 函数名称 : updateByUserId
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @param userEntity
     * @return 修改记录：
     * 日期 ：2016年4月12日 上午11:37:13	修改人：  niuzan
     * 描述	：
     */
    public int update(UserBasicEntity userEntity) throws Exception;

    /**
     * 函数名称 : deleteById
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @param userId 修改记录：
     *               日期 ：2016年4月12日 上午11:40:12	修改人：  niuzan
     *               描述	：
     */
    public void deleteById(String userId);

    /**
     * 函数名称 : queryAll
     * 功能描述 :  分页返回
     * 参数及返回值说明：
     *
     * @return 修改记录：
     * 日期 ：2016年4月12日 上午11:41:21	修改人：  niuzan
     * 描述	：
     */
    public List<UserBasicEntity> queryAllWithPagination(Integer pageNum, Integer eachPageCount);

    /**
     * 查询用户的所有信息
     *
     * @param userId
     * @return
     */
    public UserAllInOne queryAllInfoById(String userId);
}
