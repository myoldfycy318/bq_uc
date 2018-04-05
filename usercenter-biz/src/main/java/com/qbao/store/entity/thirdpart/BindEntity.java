package com.qbao.store.entity.thirdpart;

import java.util.Date;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 下午5:17:48 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 下午5:17:48 	修改人：
 *  	描述	:
 ***********************************************************
 */
public class BindEntity
{
    /**
     * 第三方标识id，由冰穹分配
     */
    private String thirdId;
    
    /**
     * 第三方的对外id
     */
    private String openId;
    
    /**
     * 冰穹用户id
     */
    private String userId;
    
    /**
     * 绑定状态
     */
    private Integer status;
    
    
    private Date createTime;
    
    private Date updateTime;

    
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

    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
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
        return "[BindEntity=" + "openId=" +openId +",userId=" + userId + ",status=" + status +
                ",createTime=" + createTime + ",updateTime=" + updateTime + "]";
    }
    
}
