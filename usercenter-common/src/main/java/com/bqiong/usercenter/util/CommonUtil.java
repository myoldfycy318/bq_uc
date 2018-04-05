package com.bqiong.usercenter.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.util.aes.AESUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * CommonUtil
 *
 * @author Zhang ShanMin
 * @date 2017/7/15
 * @time 16:44
 */
public class CommonUtil {
    /**
     * 验证BU ID
     *
     * @param buId
     */
    public static BUEnum validateBuId(String buId) throws BizException {
        BUEnum buEnum = BUEnum.getFromKey(buId);
        if (buEnum == null) {
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode(), ErrorCode.SYSTEM_EXCEPTION.getMessage());
        }
        return buEnum;
    }

    /**
     * 验证 clientId
     *
     * @param clientId
     */
    protected void validateClientId(String clientId) throws BizException {
        if (StringUtils.isEmpty(clientId)) {
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode(), ErrorCode.SYSTEM_EXCEPTION.getMessage());
        }
    }

    /**
     * 验证国内的手机号
     *
     * @return
     */
    public static void validateMobileInner(String mobile) throws BizException {

        if (StringUtils.isEmpty(mobile)) {
            throw new BizException(ErrorCode.PASSPORT_NULL.getCode(), ErrorCode.PASSPORT_NULL.getMessage());
        }

        String regMobile = "^[1][3-8]\\d{9}$";
        if (!mobile.matches(regMobile)) {
            throw new BizException(ErrorCode.PASSPORT_ILLEGLE.getCode(), ErrorCode.PASSPORT_ILLEGLE.getMessage());
        }
    }

    /**
     * 验证国外的手机号
     *
     * @return
     */
    public static void validateMobileAbroad(String mobile) throws BizException {
        if (StringUtils.isEmpty(mobile)) {
            throw new BizException(ErrorCode.PASSPORT_NULL.getCode(), ErrorCode.PASSPORT_NULL.getMessage());
        }
        String otherMobile = "^[0-9]*$";
        if (!mobile.matches(otherMobile)) {
            throw new BizException(ErrorCode.PASSPORT_ILLEGLE.getCode(), ErrorCode.PASSPORT_ILLEGLE.getMessage());
        }
    }

    /**
     * 验证国内姓名
     */
    public static void validateNameInner(String name) throws BizException {

        if (StringUtils.isEmpty(name)) {
            throw new BizException(ErrorCode.NAME_NOT_NULL.getCode(), ErrorCode.NAME_NOT_NULL.getMessage());
        }
        String nameReg = "^[\u4E00-\u9FFF]+$";
        if (name.length() < 2 || name.length() > 6 || !name.matches(nameReg)) {
            throw new BizException(ErrorCode.NAME_ILLEGLE.getCode(), ErrorCode.NAME_ILLEGLE.getMessage());
        }
    }

    /**
     * 验证身份证正则
     *
     * @param card
     * @return
     */
    public static void validateCardInner(String card) throws BizException {
        String reg15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        String reg19 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

        String str = "{11:\"北京\",12:\"天津\",13:\"河北\",14:\"山西\",15:\"内蒙古\",21:\"辽宁\",22:\"吉林\",23:\"黑龙江\",31:\"上海\",32:\"江苏\",33:\"浙江\",34:\" 安徽\",35:\"福建\",36:\"江西\",37:\"山东\",41:\"河南\",42:\"湖北\",43:\"湖南\",44:\"广东\",45:\"广西\",46:\"海南\",50:\"重庆\",51:\"四川\",52:\"贵州\" ,53:\"云南\",54:\"西藏\",61:\"陕西\",62:\"甘肃\",63:\"青海\",64:\"宁夏\",65:\"新疆\",71:\"台湾\",81:\"香港\",82:\"澳门\",91:\"国外\"}";

        if (card.length() != 15 && card.length() != 18) {
            throw new BizException(ErrorCode.IDCARD_ILLEGLE.getCode(), ErrorCode.IDCARD_ILLEGLE.getMessage());
        }
        boolean flag = false;
        if (card.length() == 15) {
            flag = card.matches(reg15);
        }

        if (card.length() == 18) {
            flag = card.toUpperCase().matches(reg19);
        }
        if (!flag) {
            throw new BizException(ErrorCode.IDCARD_ILLEGLE.getCode(), ErrorCode.IDCARD_ILLEGLE.getMessage());
        }

        JSONObject obj = JSON.parseObject(str);
        if (StringUtils.isEmpty(obj.getString(card.substring(0, 2)))) {
            throw new BizException(ErrorCode.IDCARD_ILLEGLE.getCode(), ErrorCode.IDCARD_ILLEGLE.getMessage());
        }
    }

    /**
     * 验证邮箱格式
     *
     * @param email
     */
    public static void validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new BizException(ErrorCode.PASSPORT_NULL.getCode());
        }
        String regEmail = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        String regEmail4Pubish = "^.*@.*$";
        if (!email.matches(regEmail) && !email.matches(regEmail4Pubish)) {
            throw new BizException(ErrorCode.PASSPORT_ILLEGLE.getCode(), ErrorCode.PASSPORT_ILLEGLE.getMessage());
        }
    }

    /**
     * 验证IP地址
     *
     * @param userIp
     */
    protected void validateIp(String userIp) {
        String each = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        String regIP = "^" + each + "\\." + each + "\\." + each + "\\." + each
                + "$";
        if (StringUtils.isBlank(userIp)) {
            System.out.println(ErrorCode.IP_NULL.getMessage());
        } else if (!userIp.matches(regIP)) {
            System.out.println(ErrorCode.IP_ILLEGLE.getMessage() + ",userIp ="
                    + userIp);
        }
    }

    /**
     * 验证BizType
     *
     * @param bizType
     * @return
     * @throws Exception
     */
    protected BizType validateBizType(String bizType) throws Exception {
        if (StringUtils.isBlank(bizType)) {
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode(), ErrorCode.SYSTEM_EXCEPTION.getMessage());
        }
        BizType bizTypeEnum = BizType.getFromKey(bizType);

        if (bizTypeEnum == null) {
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode(), ErrorCode.SYSTEM_EXCEPTION.getMessage());
        }
        return bizTypeEnum;
    }

    /**
     * 验证账号
     *
     * @param countryCode
     * @param passport
     * @return
     */
    public static PassportType validatePassport(String countryCode, String passport) {

        if (StringUtils.isEmpty(passport)) {
            throw new BizException(ErrorCode.PASSPORT_NULL.getCode(), ErrorCode.PASSPORT_NULL.getMessage());
        }

        if (passport.contains("@")) {
            // 验证邮箱
            validateEmail(passport);
            return PassportType.email;
        }

        // 所有的游客，都作为手机号
        if (passport.startsWith("100")) {
            return PassportType.mobile;
        }

        // 国内的手机号码
        if (countryCode == null || countryCode.equals("86")) {
            validateMobileInner(passport);
            return PassportType.mobile;
        } else {
            validateMobileAbroad(passport);
            return PassportType.mobile;
        }
    }

    /**
     * 是否需要加密
     *
     * @param bu
     */
    public static boolean isAESContent(BUEnum bu) {
        switch (bu) {
            case H5_YOUPIAO:
            case DMOE_H5:
                return false;
            default:
                return true;
        }
    }

    /**
     * 验证，解密密码
     *
     * @param password
     * @param isAESPassword
     * @throws Exception
     */
    protected String validatePassword(String password, boolean isAESPassword)
            throws BizException {
        if (StringUtils.isBlank(password)) {
            throw new BizException(ErrorCode.PASSWORD_NULL.getCode(), ErrorCode.PASSWORD_NULL.getMessage());
        }
        // 解密密码
        if (isAESPassword) {
            password = AESUtils.decrypt(password);
        }
        // 密码长度不够
        if (UserCenterConstants.PASSWORD_MIN_LENGTH > password.length()
                || UserCenterConstants.PASSWORD_MAX_LENGTH < password.length()) {
            throw new BizException(ErrorCode.PASSWORD_ILLEGLE.getCode(), ErrorCode.PASSWORD_ILLEGLE.getMessage());
        }
        // 包含非法字符
        for (int i = 0; i < password.length(); i++) {
            if (UserCenterConstants.PASSWORD_ALLOW_CHAR.indexOf(password.charAt(i)) == -1) {

                throw new BizException(ErrorCode.PASSWORD_ILLEGLE.getCode(), ErrorCode.PASSWORD_ILLEGLE.getMessage());
            }
        }
        return password;
    }


    public static String isCheckPlatform(BUEnum bu) {
        switch (bu) {
            case DOME_APP_STORE:
            case DMOE_H5:
            case H5_YOUPIAO:
            case OGP_WEB_GAME:
            case YEYOU:
            case LETV_GAME:
                return "platform";
            case DOME_SDK:
                return "gameChange";
            default:
                return "";
        }
    }




}
