package com.bqiong.usercenter.constants;

import org.apache.commons.lang3.StringUtils;

public enum ThirdPartyEnum {
    QBAO("1", RedisContant.QBAO_THIRD_IDX, RedisContant.QBAO_THIRD_BASIC_IDX),
    SINA("2", RedisContant.SINA_THIRD_IDX, RedisContant.SINA_THIRD_BASIC_IDX),
    QQ("3", RedisContant.QQ_THIRD_IDX, RedisContant.QQ_THIRD_BASIC_IDX),
    TOURIST("4", RedisContant.IMSI_DB_IDX, RedisContant.IMSI_BAISC_IDX),
    FACEBOOK("5", RedisContant.FB_THIRD_IDX, RedisContant.FB_THIRD_BASIC_IDX),
    WEIXIN("6", RedisContant.WEIXIN_THIRD_IDX, RedisContant.WEIXIN_THIRD_BASIC_IDX),
    OGP("7", RedisContant.OGP_THIRD_IDX, RedisContant.OGP_THIRD_BASIC_IDX),
    LETV("8", RedisContant.LETV_THIRD_IDX, RedisContant.LETV_THIRD_BASIC_IDX);

    private String thirdId;
    private String dbIdxKey;
    private String openIdUserKey;

    private ThirdPartyEnum(String thirdId, String dbIdxKey, String openIdUserKey) {
        this.thirdId = thirdId;
        this.dbIdxKey = dbIdxKey;
        this.openIdUserKey = openIdUserKey;
    }

    public String getThirdId() {
        return thirdId;
    }

    public String getDbIdxKey() {
        return dbIdxKey;
    }

    public String getOpenIdUserKey() {
        return openIdUserKey;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public static final ThirdPartyEnum getFromKey(String thirdId) {
        if (StringUtils.isBlank(thirdId))
            return null;
        for (ThirdPartyEnum e : ThirdPartyEnum.values()) {
            if (thirdId.equals(e.getThirdId()) && !TOURIST.getThirdId().equals(thirdId)) { //排除游客类型
                return e;
            }
        }
        return null;
    }

    public static final ThirdPartyEnum getFromName(String thirdpart) {
        if (StringUtils.isBlank(thirdpart))
            return null;
        for (ThirdPartyEnum e : ThirdPartyEnum.values()) {
            if (thirdpart.equalsIgnoreCase(e.name())) {
                return e;
            }
        }
        return null;
    }
}
