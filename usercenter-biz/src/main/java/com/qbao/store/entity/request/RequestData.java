package com.qbao.store.entity.request;

import com.bqiong.usercenter.constants.BizType;

import java.util.Date;

public class RequestData {

    private String uri;

    /**
     * 业务类型
     */
    private BizType operateType;

    //业务类型
    private Integer bizType;

    /**
     * 手机号码.
     */
    private String mobile;

    /**
     * captchaKey
     */
    private String captchaKey;

    //图片验证码
    private String captcha;

    /**
     * 邮箱.
     */
    private String email;

    /**
     * 来源业务单元.
     */
    private String buId;

    /**
     * 来源appcode.
     */
    private String clientId;

    /**
     * 第三方id.
     */
    private String thirdId;

    /**
     * 第三方对外openid.
     */
    private String openId;

    /**
     * 操作类型.
     */
    private Integer operationType;

    /**
     * 操作时间.
     */
    private Date operationTime;

    /**
     * 用户来源ip.
     */
    private String userIp;

    /**
     * app版本号
     */
    private String appVersion;

    /**
     * 渠道ID
     */

    private String channelId;
    /**
     * 渠道名称
     */

    private String channelName;

    //默认86
    private String countryCode = "86";
    /**
     * 手机验证码
     */
    private String mobileCode;
    /**
     * 密码
     */
    private String password;
    //确认密码
    private String confirmPassword;

    private String userName;
    //用户头像
    private String avatarUrl;

    private String gender;
    //手机注册 参数
    private String passport;
    private String emailCode;
    //库表索引  dataSource01-user_info_00-5628（以'-'分库）
    private String dbTblIdx;
    private String accessToken;
    private String userId;
    private String salt;
    private Integer age;
    private String smsToken;//手机验证码
    private String emailToken;//邮箱验证码
    private String passwordOld;//旧密码
    private String passwordNew;//新密码
    //设备唯一码
    private String imsi;
    //是否需要实名认证,true:需要实名|false:不需要
    private Boolean needCard = false;
    //验证码
    private String verifyCode;
    //身份证用户名
    private String name;
    //身份证编号
    private String card;
    //v2 更新user
    //生日
    private String birthday;
    //收货地址-省份
    private String province;
    //收货地址-城市
    private String city;
    //收货地址-区域
    private String district;
    //收货地址-街道
    private String street;
    //收货地址-详细地址
    private String addressDetail;
    //是否换绑
    private Boolean isChange;
    //钱宝账户userId
    private String qbaoUid;
    //第三方授权code
    private String code;
    //是否注册
    private Boolean registered = false;
    //QQ号码
    private String qqNo;
    //手机系统 ios/Android/页游/
    private String sysType;
    //验证码token
    private String verifyToken;
    //平台类型: 0 移动平台(手游) 1 H5平台 2 页游OGP平台 3 VR平台
    private String platformType;
    //微信公众号APPId
    private String appId;
    //微信公众号 秘钥
    private String secret;
    //微信公众号支付需要使用的openID
    private String wxOpenId;
    //微信公众号使用
    private String app_code;

    private String recType;//0 登录 1 注册 2 激活

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public BizType getOperateType() {
        return operateType;
    }

    public void setOperateType(BizType operateType) {
        this.operateType = operateType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBuId() {
        return buId;
    }

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String getDbTblIdx() {
        return dbTblIdx;
    }

    public void setDbTblIdx(String dbTblIdx) {
        this.dbTblIdx = dbTblIdx;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSmsToken() {
        return smsToken;
    }

    public void setSmsToken(String smsToken) {
        this.smsToken = smsToken;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getEmailToken() {
        return emailToken;
    }

    public void setEmailToken(String emailToken) {
        this.emailToken = emailToken;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public Boolean getNeedCard() {
        return needCard;
    }

    public void setNeedCard(Boolean needCard) {
        this.needCard = needCard;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Boolean getChange() {
        return isChange;
    }

    public void setChange(Boolean change) {
        isChange = change;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getQbaoUid() {
        return qbaoUid;
    }

    public void setQbaoUid(String qbaoUid) {
        this.qbaoUid = qbaoUid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    public String getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(String verifyToken) {
        this.verifyToken = verifyToken;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getApp_code() {
        return app_code;
    }

    public void setApp_code(String app_code) {
        this.app_code = app_code;
    }

    public String getRecType() {
        return recType;
    }

    public void setRecType(String recType) {
        this.recType = recType;
    }

    @Override
    public String toString() {
        return "RequestData [uri=" + uri + ", bizType=" + operateType + ", mobile=" + mobile + ", captchaKey=" + captchaKey
                + ", email=" + email + ", buId=" + buId + ", clientId=" + clientId + ", thirdId=" + thirdId
                + ", openId=" + openId + ", operationType=" + operationType + ", operationTime=" + operationTime
                + ", userIp=" + userIp + ", appVersion=" + appVersion + ", countryCode=" + countryCode
                + ",channelId=" + channelId + ",mobileCode =" + mobileCode + " ,password=" + password + ",userName=" + userName
                + ",avatarUrl=" + avatarUrl + ",gender=" + gender + ",passport=" + passport + ",emailCode=" + emailCode
                + "sysType=" + sysType + "platformType=" + platformType
                + "]";
    }

}