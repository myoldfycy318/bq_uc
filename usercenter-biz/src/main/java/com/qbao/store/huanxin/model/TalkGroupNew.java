package com.qbao.store.huanxin.model;

import java.util.List;

public class TalkGroupNew {
	private Long affiliations_count;
	private Boolean allowinvites;
	private String description;
	private String error;
	private String id;
	private Long maxusers;
	private Boolean membersonly;
	private String name;
	private Boolean public_group;
	private List<TalkGroupMember>affiliations;
	public Long getAffiliations_count() {
		return affiliations_count;
	}
	public void setAffiliations_count(Long affiliations_count) {
		this.affiliations_count = affiliations_count;
	}
	public Boolean getAllowinvites() {
		return allowinvites;
	}
	public void setAllowinvites(Boolean allowinvites) {
		this.allowinvites = allowinvites;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getMaxusers() {
		return maxusers;
	}
	public void setMaxusers(Long maxusers) {
		this.maxusers = maxusers;
	}
	public Boolean getMembersonly() {
		return membersonly;
	}
	public void setMembersonly(Boolean membersonly) {
		this.membersonly = membersonly;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getPublic_group() {
		return public_group;
	}
	public void setPublic_group(Boolean public_group) {
		this.public_group = public_group;
	}
	public List<TalkGroupMember> getAffiliations() {
		return affiliations;
	}
	public void setAffiliations(List<TalkGroupMember> affiliations) {
		this.affiliations = affiliations;
	}
}
