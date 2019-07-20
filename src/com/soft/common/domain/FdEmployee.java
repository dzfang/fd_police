package com.soft.common.domain;

import java.util.Date;

import javax.annotation.Resource;

@SuppressWarnings("serial")
public class FdEmployee extends BaseEntity{
	@Resource(name = "ID")
	private Long id;
	
	@Resource(name = "身份证号")
	private String idCard;
	
	@Resource(name = "姓名")
	private String name;
	
	@Resource(name = "性别")
	private String gender;
	
	@Resource(name = "出生日期")
	private Date birthday;
	
	@Resource(name = "籍贯")
	private String birthPlace;
	
	@Resource(name = "民族")
	private String nation;
	
	@Resource(name = "毕业院校")
	private String school;
	
	@Resource(name = "专业")
	private String major;
	
	@Resource(name = "学历")
	private String degree;
	
	@Resource(name = "职称")
	private String position;
	
	@Resource(name = "住址")
	private String address;
	
	@Resource(name = "联系电话")
	private String telephone;
	
	@Resource(name = "所在单位机构组织代码")
	private String orgCode;
	
	@Resource(name = "所在单位名称")
	private String orgName;

	private String genderName;
	
	private String nationName;
	
	private String degreeName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	
}
