package com.qbao.store.entity.log;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年7月6日 下午2:41:52 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年7月6日 下午2:41:52 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public class UserOperationLogEntity {
    /**
     * 操作记录id.
     */
    private Integer id;

    /**
     * 用户id.
     */
    private String userId;

    /**
     * 用户昵称.
     */
    private String userName;
    
    /**
     * 国别码
     */
    private String countryCode;

    /**
     * 手机号码.
     */
    private String mobile;

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
     * 渠道id
     */
    private String channelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	
	@Override
	public String toString() {
		return "UserOperationLogEntity [id=" + id + ", userId=" + userId + ", userName=" + userName + ", mobile="
				+ mobile + ", email=" + email + ", buId=" + buId + ", clientId=" + clientId + ", thirdId=" + thirdId
				+ ", openId=" + openId + ", operationType=" + operationType + ", operationTime=" + operationTime
				+ ", userIp=" + userIp + ", channelId=" + channelId + "]";
	}

	public static boolean isEmptyEntity(UserOperationLogEntity entity)
	{
		boolean flag = true;
		for (Field f : entity.getClass().getDeclaredFields()) 
		{
		    f.setAccessible(true);
		    try 
		    {
				if(f.get(entity) != null )
				{
					flag = false;
					break;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) 
		    {
				return true;
			}
		}
		return flag;
	}
}