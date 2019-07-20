package com.soft.common.domain;

import java.util.Date;

import javax.annotation.Resource;

@SuppressWarnings("serial")
public class FdCompany extends BaseEntity {
	@Resource(name = "ID")
	private Long id;

	@Resource(name = "许可证号")
	private String license;

	@Resource(name = "单位名称")
	private String companyName;

	@Resource(name = "法人")
	private String legalPerson;

	@Resource(name = "负责人")
	private String director;

	@Resource(name = "业主")
	private String owner;

	@Resource(name = "联系电话")
	private String phone;

	@Resource(name = "地址")
	private String address;

	@Resource(name = "类型")
	private String companyType;

	@Resource(name = "备注")
	private String remark;

	@Resource(name = "发证时间")
	private Date issueDate;

	@Resource(name = "委托代理人")
	private String agent;

	@Resource(name = "联系电话")
	private String agentPhone;

	@Resource(name = "处理年份")
	private String handleYear;

	@Resource(name = "处理类型")
	private String handleType;

	@Resource(name = "处理类型文本")
	private String handleTypeName;
	
	@Resource(name = "企业类型文本")
	private String companyTypeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getAgentPhone() {
		return agentPhone;
	}

	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}

	public String getHandleYear() {
		return handleYear;
	}

	public void setHandleYear(String handleYear) {
		this.handleYear = handleYear;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getHandleTypeName() {
		return handleTypeName;
	}

	public void setHandleTypeName(String handleTypeName) {
		this.handleTypeName = handleTypeName;
	}

	public String getCompanyTypeName() {
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}
	 
}
