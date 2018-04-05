package com.qbao.store.service.impl.structure;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.RedisContant;
import com.bqiong.usercenter.constants.ThirdPartyEnum;
import com.bqiong.usercenter.util.RedisUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.entity.user.UserInfo;
import com.qbao.store.mapper.structure.UserStructureMapper;
import com.qbao.store.service.structure.UserStructureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserStructureServiceImpl
 *
 * @author Zhang ShanMin
 * @date 2017/7/10
 * @time 15:59
 */
@Service("userStructureService")
public class UserStructureServiceImpl implements UserStructureService {

    @Resource(name = "userStructureMapper")
    private UserStructureMapper userStructureMapper;

    @Override
    public boolean createUserTable(String tableName) {
        return userStructureMapper.createUserTable(tableName);
    }

    @Override
    public boolean insertUserInfo(String suffix, UserInfo userInfo) {
        String userTable = "user_info_" + suffix;
        return userStructureMapper.insertUserInfo(userTable, userInfo);
    }

    @Override
    public boolean insertUsers(String userTable, List<UserInfo> userInfos) {
        return userStructureMapper.insertUsers(userTable, userInfos);
    }

    @Override
    public void importOldUserInfo() {
        int PAGE_SIZE = 1000;
        int total = PAGE_SIZE;
        int count = 1;
        String dbTblIdx = null;
        ThirdPartyEnum thirdPartyEnum = null;
        for (int start = 0; start < total; start += PAGE_SIZE) {
            System.out.println("------------------>count:" + count + ",start=" + start);
            PageHelper.startPage(count++, PAGE_SIZE);
            Page<UserEntity> userInfoList = (Page<UserEntity>) userStructureMapper.queryUserInfoList();
            total = new Long(userInfoList.getTotal()).intValue();
            Map<String, String> user_basic_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> mobile_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> mobile_basic_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> email_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> email_basic_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> qbao_third_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> qbao_third_basic_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> weixin_third_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> weixin_third_basic_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> sina_third_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> sina_third_basic_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> qq_third_idx = new HashMap<String, String>(PAGE_SIZE);
            Map<String, String> qq_third_basic_idx = new HashMap<String, String>(PAGE_SIZE);
            for (UserEntity userEntity : userInfoList) {
                dbTblIdx = "dataSource01-user_info_00-" + userEntity.getId();
                userEntity.setDbTblIdx(dbTblIdx);//库表索引
                //domeuserId对应库表索引、用户基本信息加载到redis
                if (StringUtils.isNotBlank(userEntity.getUserId())) {
                    user_basic_idx.put(userEntity.getUserId(), JSONObject.toJSONString(userEntity));
                }
                //手机号对应库表索引、用户基本信息加载到redis
                if (StringUtils.isNotBlank(userEntity.getMobile()) && !userEntity.getMobile().startsWith("100")) {
                    mobile_idx.put(userEntity.getMobile(), dbTblIdx);
                    mobile_basic_idx.put(userEntity.getMobile(), JSONObject.toJSONString(userEntity));
                }
                //邮箱对应库表索引、用户基本信息加载到redis
                if (StringUtils.isNotBlank(userEntity.getEmail())) {
                    email_idx.put(userEntity.getEmail(), dbTblIdx);
                    email_basic_idx.put(userEntity.getEmail(), JSONObject.toJSONString(userEntity));
                }
                if (StringUtils.isNotBlank(userEntity.getThirdId()) && StringUtils.isNotBlank(userEntity.getOpenId()) &&
                        null != (thirdPartyEnum = ThirdPartyEnum.getFromKey(userEntity.getThirdId()))) {
                    switch (thirdPartyEnum) {
                        //第三方钱宝openId对应库表索引、用户基本信息加载到redis
                        case QBAO: {
                            qbao_third_idx.put(userEntity.getOpenId(), dbTblIdx);
                            qbao_third_basic_idx.put(userEntity.getOpenId(), JSONObject.toJSONString(userEntity));
                            break;
                        }
                        case WEIXIN: {
                            weixin_third_idx.put(userEntity.getOpenId(), dbTblIdx);
                            weixin_third_basic_idx.put(userEntity.getOpenId(), JSONObject.toJSONString(userEntity));
                            break;
                        }
                        case SINA: {
                            sina_third_idx.put(userEntity.getOpenId(), dbTblIdx);
                            sina_third_basic_idx.put(userEntity.getOpenId(), JSONObject.toJSONString(userEntity));
                            break;
                        }
                        case QQ: {
                            qq_third_idx.put(userEntity.getOpenId(), dbTblIdx);
                            qq_third_basic_idx.put(userEntity.getOpenId(), JSONObject.toJSONString(userEntity));
                            break;
                        }
                    }
                }

            }
            if (user_basic_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.USER_BASIC_IDX, user_basic_idx);
            }
            if (mobile_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.MOBILE_IDX, mobile_idx);
            }
            if (mobile_basic_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.MOBILE_BASIC_IDX, mobile_basic_idx);
            }
            if (email_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.EMAIL_IDX, email_idx);
            }
            if (email_basic_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.EMAIL_BASIC_IDX, email_basic_idx);
            }
            if (qbao_third_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.QBAO_THIRD_IDX, qbao_third_idx);
            }
            if (qbao_third_basic_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.QBAO_THIRD_BASIC_IDX, qbao_third_basic_idx);
            }
            if (weixin_third_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.WEIXIN_THIRD_IDX, weixin_third_idx);
            }
            if (weixin_third_basic_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.WEIXIN_THIRD_BASIC_IDX, weixin_third_basic_idx);
            }
            if (sina_third_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.SINA_THIRD_IDX, sina_third_idx);
            }
            if (sina_third_basic_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.SINA_THIRD_BASIC_IDX, sina_third_basic_idx);
            }
            if (qq_third_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.QQ_THIRD_IDX, qq_third_idx);
            }
            if (qq_third_basic_idx.size() > 0) {
                RedisUtil.hmset(RedisContant.QQ_THIRD_BASIC_IDX, qq_third_basic_idx);
            }
        }
    }

    /**
     * 加载游客信息到缓存
     */
    @Override
    public void loadTourist2cache() {
        List<UserEntity> userInfoList = userStructureMapper.queryTouristUsers();
        String dbTblIdx = null;
        System.out.println("----》游客数量：" + userInfoList.size());
        for (UserEntity userEntity : userInfoList) {
            dbTblIdx = "dataSource01-user_info_00-" + userEntity.getId();
            userEntity.setDbTblIdx(dbTblIdx);//库表索引
            if (StringUtils.isNotBlank(userEntity.getImsi())) {
                //设备号对应库表索引、用户基本信息加载到redis
                RedisUtil.hset(RedisContant.IMSI_DB_IDX, userEntity.getImsi(),dbTblIdx);
                RedisUtil.hset(RedisContant.IMSI_BAISC_IDX, userEntity.getImsi(), JSONObject.toJSONString(userEntity));
            }
        }
    }
}
