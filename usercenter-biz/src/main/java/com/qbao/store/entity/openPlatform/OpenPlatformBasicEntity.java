package com.qbao.store.entity.openPlatform;

import java.util.Date;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月9日 下午3:13:01 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月9日 下午3:13:01 	修改人：
 *  	描述	:
 ***********************************************************
 */
public class OpenPlatformBasicEntity 
{
    /**
     * 用户id
     */
    private String userId;
    
    /**
     * 用户对外id
     */
    private String openId;
    
    /**
     * 用户昵称
     */
    private String userName;
    
    /**
     * 手机
     */
    private String mobile;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 邮箱状态。0：已验证，1：未验证。
     */
    private Integer emailStatus;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 盐值
     */
    private String salt;
    
    /**
     * 用户状态
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public String getOpenId() {
        return openId;
    }

    
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    
    public String getUserName() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName = userName;
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

    
	public Integer getEmailStatus() {
		return emailStatus;
	}


	public void setEmailStatus(Integer emailStatus) {
		this.emailStatus = emailStatus;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
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
    
    public String toString()
    {
    	return "OpenPlatformBasicEntity[" + "userId="+userId + ",openId=" + openId + ",userName=" + userName 
    			+ ",mobile=" + mobile + ",email=" + email + ",emailStatus=" + emailStatus
    			+ ",createTime=" + createTime + ",updateTime=" + updateTime + ",salt=" + salt + "]";
    }
    
}
