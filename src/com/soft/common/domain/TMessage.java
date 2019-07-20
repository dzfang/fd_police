package com.soft.common.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class TMessage extends BaseEntity {
	private Long id;

	private Long orgId;

	private String messageDesc;

	private Boolean processSign;

	private Long createUser;

	private Date createDate;

	private Boolean deleteSign;
	
	private Boolean reciverDelete;

	private String orgName;

	private String userName;

	private String processStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getMessageDesc() {
		return messageDesc;
	}

	public void setMessageDesc(String messageDesc) {
		this.messageDesc = messageDesc;
	}

	public Boolean getProcessSign() {
		return processSign;
	}

	public void setProcessSign(Boolean processSign) {
		this.processSign = processSign;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Boolean getDeleteSign() {
		return deleteSign;
	}

	public void setDeleteSign(Boolean deleteSign) {
		this.deleteSign = deleteSign;
	}

	public Boolean getReciverDelete() {
		return reciverDelete;
	}

	public void setReciverDelete(Boolean reciverDelete) {
		this.reciverDelete = reciverDelete;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	
}