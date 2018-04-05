package com.bqiong.usercenter.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * **********************************************************
 * 内容摘要	：<p>
 * <p>
 * 作者	：94841
 * 创建时间	：2016年5月17日 下午3:03:26
 * 当前版本号：v1.0
 * 历史记录	:
 * 日期	: 2016年5月17日 下午3:03:26 	修改人：niuzan
 * 描述	:
 * **********************************************************
 */
public enum BUEnum {
    DOME_SDK("DOME001", "1"),
    DOME_APP_STORE("DOME002", "2"),
    PUBLISH("DOME003", "3"),
    TRADECENTER("DOME004", "4"),
    OVERSEASDK("DOME005", "5"),
    PASSPORT("DOME006", "6"),
    DMOE_H5("DOME007", "7"),
    H5_YOUPIAO("DOME008", "8"),
    OGP_WEB_GAME("DOME009", "9"),
    OPEN_PLATFORM("DOME010", "10"),
    WEIXIN_PUBLIC("DOME011", "11"),
    YEYOU("DOME012", "12"),
    LETV_GAME("DOME013", "13"),
    DISCUZ("DOME014", "14"),
    VR_PC("DOME015", "15");

    private String buId;
    //平台登录记录code
    private String loginPlatformCode;

    private BUEnum(String buId, String loginPlatformCode) {
        this.buId = buId;
        this.loginPlatformCode = loginPlatformCode;
    }

    public String getBuId() {
        return buId;
    }

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getLoginPlatformCode() {
        return loginPlatformCode;
    }

    public void setLoginPlatformCode(String loginPlatformCode) {
        this.loginPlatformCode = loginPlatformCode;
    }

    public static final BUEnum getFromKey(String buId) {
        if (StringUtils.isBlank(buId)) {
            return null;
        }
        for (BUEnum e : BUEnum.values()) {
            if (buId.equals(e.getBuId())) {
                return e;
            }
        }
        return null;
    }

    /**
     * 手游
     *
     * @param buId
     * @return
     */
    public static boolean isMobileGameBU(String buId) {
        return (StringUtils.isNotBlank(buId) && ("DOME001".equals(buId) || "DOME003".equals(buId)));
    }

}
