package com.qbao.store.entity.index;

import java.io.Serializable;

public class ImsiIndexEntity implements Serializable {
	
	/**
	 * id
	 */
	private Integer id;
    /**
     * 手机imsi号.
     */
    private String imsi;

    /**
     * 用户userid字段.
     */
    private String userId;
    
    /**
     * 绑定用户id
     */
    private Integer isBind;

    private static final long serialVersionUID = 1L;
    
    public ImsiIndexEntity()
    {
    	
	}
    
    public ImsiIndexEntity(int id, int isBind)
    {
    	this.id = id;
    	this.isBind = isBind;
    }
    
	public ImsiIndexEntity(String imsi, String userId)
	{
		super();
		this.imsi = imsi;
		this.userId = userId;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getIsBind() {
		return isBind;
	}

	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}
    
}