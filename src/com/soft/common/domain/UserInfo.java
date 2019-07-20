package com.soft.common.domain;

import java.util.Date;

public class UserInfo extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户名
	 */
	private String loginId;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 手机号
	 */
	private String telephone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 用户状态
	 */
	private String userStatus;
	/**
	 * 用户性别
	 */
	private String userSex;
	/**
	 * 最后登录时间
	 */
	private Date loginTime;
	/**
	 * 性别名称
	 */
	private String sexName;
	/**
	 * 状态名称
	 */
	private String statusName;
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 出生日期
	 */
	private Date birthday;
	/**
	 * 身份证号
	 */
	private String idCard;
	/**
	 * 实验室/教室代码
	 */
	private Long orgId;
	/**
	 * 实验室/教室名称
	 */
	private String orgName;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 父角色ID
	 */
	private Integer roleParentId;

	private String[] childRoleIdArray;

	private String childRoleIdString;
	
	private String profession;

	private String professionName;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId == null ? null : loginId.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus == null ? null : userStatus.trim();
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex == null ? null : userSex.trim();
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleParentId() {
		return roleParentId;
	}

	public void setRoleParentId(Integer roleParentId) {
		this.roleParentId = roleParentId;
	}

	public String[] getChildRoleIdArray() {
		return childRoleIdArray;
	}

	public void setChildRoleIdArray(String[] childRoleIdArray) {
		this.childRoleIdArray = childRoleIdArray;
	}

	public String getChildRoleIdString() {
		return childRoleIdString;
	}

	public void setChildRoleIdString(String childRoleIdString) {
		this.childRoleIdString = childRoleIdString;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

}