package com.soft.common.domain;

import javax.annotation.Resource;

@SuppressWarnings("serial")
public class FdOrganization extends BaseEntity {
	@Resource(name = "ID")
	private Long id;

	@Resource(name = "单位名称")
	private String orgName;

	@Resource(name = "组织机构代码")
	private String orgCode;

	@Resource(name = "营业执照注册号")
	private String license;

	@Resource(name = "从业单位类型")
	private String orgType;

	@Resource(name = "从业资质")
	private String qualified;

	@Resource(name = "资质类别")
	private String qualifiedType;

	@Resource(name = "法人代表")
	private String corporationName;

	private String orgTypeName;

	private String qualifiedTypeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getQualified() {
		return qualified;
	}

	public void setQualified(String qualified) {
		this.qualified = qualified;
	}

	public String getQualifiedType() {
		return qualifiedType;
	}

	public void setQualifiedType(String qualifiedType) {
		this.qualifiedType = qualifiedType;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getQualifiedTypeName() {
		return qualifiedTypeName;
	}

	public void setQualifiedTypeName(String qualifiedTypeName) {
		this.qualifiedTypeName = qualifiedTypeName;
	}

}
