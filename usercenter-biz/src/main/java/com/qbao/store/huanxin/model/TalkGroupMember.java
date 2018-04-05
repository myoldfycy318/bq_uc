package com.qbao.store.huanxin.model;

public class TalkGroupMember {
	private Boolean description;
	private Boolean groupname;
	private Boolean maxusers;
	private String member;
	private String newowner;
	private String owner;
	public Boolean getDescription() {
		return description;
	}
	public void setDescription(Boolean description) {
		this.description = description;
	}
	public Boolean getGroupname() {
		return groupname;
	}
	public void setGroupname(Boolean groupname) {
		this.groupname = groupname;
	}
	public Boolean getMaxusers() {
		return maxusers;
	}
	public void setMaxusers(Boolean maxusers) {
		this.maxusers = maxusers;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getNewowner() {
		return newowner;
	}
	public void setNewowner(String newowner) {
		this.newowner = newowner;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
}
