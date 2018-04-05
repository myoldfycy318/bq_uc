package com.qbao.store.entity.user;

import java.util.Date;

public class UserEntity {
    private Long id;
    // 用户id
    private String userId;
    //用户昵称
    private String userName;
    //国别码
    private String countryCode;
    //手机
    private String mobile;
    //邮箱
    private String email;
    //邮箱状态。0：已验证，1：未验证
    private Integer emailStatus;
    //密码
    private String password;
    //盐值
    private String salt;
    //用户状态
    private Integer status;
    //用户来源
    private String buId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //注册方式 mobile| email
    private String registerBy;
    //注册的客户端来源
    private String clientId;
    //年龄
    private Integer age;
    //性别
    private String gender;
    //用户头像
    private String avatarUrl;
    //个人简介
    private String profile;
    //生日
    private Date birthday;
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
    //身份证号码
    private String idCardNo;
    //身份证姓名
    private String idCardName;
    //三方登录id
    private String openId;
    //渠道ID
    private String channelId;
    //手机验证码
    private String mobileCode;
    //邮箱验证码
    private String emailCode;
    //库表索引  dataSource01-user_info_00-5628（以'-'分库）
    private String dbTblIdx;
    //设备号
    private String imsi;
    //第三方id.
    private String thirdId;
    //QQ号码
    private String qqNo;

    //各平台登录记录
    private String loginRec;

    public UserEntity() {

    }

    private UserEntity(Builder b) {
        this.countryCode = b.countryCode;
        this.mobile = b.mobile;
        this.email = b.email;
        this.password = b.password;
        this.salt = b.salt;
        this.updateTime = b.updateTime;
        this.idCardNo = b.idCardNo;
        this.idCardName = b.idCardName;
        this.userName = b.userName;
        this.avatarUrl = b.avatarUrl;
        this.gender = b.gender;
        this.age = b.age;
        this.birthday = b.birthday;
        this.province = b.province;
        this.city = b.city;
        this.addressDetail = b.addressDetail;
        this.clientId = b.clientId;
        this.registerBy = b.registerBy;
        this.qqNo = b.qqNo;
        this.loginRec = b.loginRec;
    }

    public static class Builder {
        private String userName;
        private String countryCode;
        private String mobile;
        private String email;
        private Integer emailStatus;
        private String password;
        private String salt;
        private Integer status;
        private String buId;
        private Date updateTime;
        private String registerBy;
        private String clientId;
        private Integer age;
        private String gender;
        private String avatarUrl;
        private String profile;
        private Date birthday;
        private String province;
        private String city;
        private String district;
        private String street;
        private String addressDetail;
        private String idCardNo;
        private String idCardName;
        private String openId;
        private String thirdId;
        private String channelId;
        private String mobileCode;
        private String emailCode;
        private String qqNo;
        private String loginRec;

        public Builder() {
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder salt(String salt) {
            this.salt = salt;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public Builder registerBy(String registerBy) {
            this.registerBy = registerBy;
            return this;
        }

        public Builder idCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
            return this;
        }

        public Builder idCardName(String idCardName) {
            this.idCardName = idCardName;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder province(String province) {
            this.province = province;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder addressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
            return this;
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder qqNo(String qqNo) {
            this.qqNo = qqNo;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public Integer getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(Integer emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBuId() {
        return buId;
    }

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRegisterBy() {
        return registerBy;
    }

    public void setRegisterBy(String registerBy) {
        this.registerBy = registerBy;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    public String getDbTblIdx() {
        return dbTblIdx;
    }

    public void setDbTblIdx(String dbTblIdx) {
        this.dbTblIdx = dbTblIdx;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    public String getLoginRec() {
        return loginRec;
    }

    public void setLoginRec(String loginRec) {
        this.loginRec = loginRec;
    }
}
