package com.bqiong.usercenter.constants;

/**
 * RedisContant
 *
 * @author Zhang ShanMin
 * @date 2017/7/12
 * @time 9:12
 */
public interface RedisContant {

    //uc库表主键索引
    String DB_TBL_ID_IDX = "uc:db:tbl:idx";
    //手机号库表索引
    String MOBILE_IDX = "uc:db:idx:mobile";
    //邮箱库表索引
    String EMAIL_IDX = "uc:db:idx:email";
    //三方钱宝库表索引
    String QBAO_THIRD_IDX = "uc:db:idx:qbao";
    //设备唯一标识imsi库表索引key
    String IMSI_DB_IDX = "uc:db:idx:imsi";
    //三方facebook库表索引
    String FB_THIRD_IDX = "uc:db:idx:fb";
    //三方新浪库表索引
    String SINA_THIRD_IDX = "uc:db:idx:sina";
    //三方qq库表索引
    String QQ_THIRD_IDX = "uc:db:idx:qq";
    //三方微信库表索引
    String WEIXIN_THIRD_IDX = "uc:db:idx:wx";
    //三方OGP库表索引
    String OGP_THIRD_IDX = "uc:db:idx:ogp";
    //三方letv库表索引
    String LETV_THIRD_IDX = "uc:db:idx:letv";


    //手机用户基本信息
    String MOBILE_BASIC_IDX = "uc:basic:mobile:idx";
    //邮箱用户基本信息
    String EMAIL_BASIC_IDX = "uc:basic:email:idx";
    //用户基本信息 user_id
    String USER_BASIC_IDX = "uc:basic:uid:idx";
    //三方钱宝用户基础信息key
    String QBAO_THIRD_BASIC_IDX = "uc:basic:qbao:idx";
    //设备唯一标识imsi用户基础信息key
    String IMSI_BAISC_IDX = "uc:basic:imsi:idx";
    //三方facebook用户基础信息key
    String FB_THIRD_BASIC_IDX = "uc:basic:fb:idx";
    //三方新浪用户基础信息key
    String SINA_THIRD_BASIC_IDX = "uc:basic:sina:idx";
    //三方qq用户基础信息key
    String QQ_THIRD_BASIC_IDX = "uc:basic:qq:idx";
    //三方微信用户基础信息key
    String WEIXIN_THIRD_BASIC_IDX = "uc:basic:wx:idx";
    //三方OGP 用户基础信息key
    String OGP_THIRD_BASIC_IDX = "uc:basic:ogp:idx";

    //三方LETV 用户基础信息key
    String LETV_THIRD_BASIC_IDX = "uc:basic:letv:idx";
    //bi  report 平台用户留存表 平台用户统计表 激活数记录key
    String PLATFORM_ACTIVATE_KEY = "domedata:platform:active";//domedata:platform:active:2017-09-22

    //bi  report 平台用户留存表 平台用户统计表 注册数记录key
    String PLATFORM_REGISTER_KEY = "domedata:platform:register";//domedata:platform:register:2017-09-22 14
    //微信公众号支付 userId --> appid --> wxOpenId
    String WEIXIN_PUBLIC_IDX = "uc:wx:public:idx";

}
