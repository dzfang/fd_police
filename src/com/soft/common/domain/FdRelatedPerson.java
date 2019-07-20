package com.soft.common.domain;

import java.util.Date;

import javax.annotation.Resource;

@SuppressWarnings("serial")
public class FdRelatedPerson extends BaseEntity {
	@Resource(name = "ID")
	private Long id;
	
	@Resource(name = "案件ID")
	private Long caseId;
	
	@Resource(name = "姓名")
	private String name;
	
	@Resource(name = "年龄")
	private int age;
	
	@Resource(name = "性别")
	private String gender;
	
	@Resource(name = "出生日期")
	private Date birthday;
	
	@Resource(name = "身份证号")
	private String idCard;
	
	@Resource(name = "家庭住址")
	private String address;
	
	@Resource(name = "案件关系")
	private String relatedKind;
	
	@Resource(name = "保证形式")
	private String pledgeType;

	public Long getId() {
		return id;
	}

	public Long getCaseId() {
		return caseId;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getIdCard() {
		return idCard;
	}

	public String getAddress() {
		return address;
	}

	public String getRelatedKind() {
		return relatedKind;
	}

	public String getPledgeType() {
		return pledgeType;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRelatedKind(String relatedKind) {
		this.relatedKind = relatedKind;
	}

	public void setPledgeType(String pledgeType) {
		this.pledgeType = pledgeType;
	}
	
	
}
