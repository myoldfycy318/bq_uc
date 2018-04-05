package com.qbao.store.entity.user;

import java.io.Serializable;
import java.util.Date;

public class UserRegisterExtraBean implements Serializable {
    /**
     * 用户id.
     */
    private String userId;

    /**
     * 渠道id.
     */
    private String channelId;

    /**
     * 创建时间.
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
    
    public UserRegisterExtraBean(String userId, String channelId)
    {
    	this.userId = userId;
    	this.channelId = channelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}