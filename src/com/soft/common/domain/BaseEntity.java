package com.soft.common.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.soft.util.LoginInfoUtil;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntity implements Serializable {
	/**
	 * 删除标志
	 */
	protected Boolean deleteSign;

	/**
	 * 作成者
	 */
	protected Long createUser;

	/**
	 * 作成时间
	 */
	protected Date createDate;

	/**
	 * 更新者
	 */
	protected Long updateUser;

	/**
	 * 更新时间
	 */
	protected Date updateDate;
	/**
	 * 删除人
	 */
	protected String deleteUser;
	/**
	 * 删除原因
	 */
	protected String deleteReason;

	/**
	 * 版本号
	 */
	public Date version;
	/**
	 * 菜单ID
	 */
	public Integer menuId;

	private String orgIds;

	/**
	 * 查询条件-用于combogrid过滤数据
	 */
	public String sqlWhere;
	/**
	 * 修改或删除原因
	 */
	private String reason;

	public Boolean getDeleteSign() {
		return deleteSign;
	}

	public void setDeleteSign(Boolean deleteSign) {
		this.deleteSign = deleteSign;
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

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	/*
	 * public String getIsPersonal() { return isPersonal; }
	 * 
	 * public void setIsPersonal(String isPersonal) { this.isPersonal =
	 * isPersonal; }
	 * 
	 * public String getIsOrganization() { return isOrganization; }
	 * 
	 * public void setIsOrganization(String isOrganization) {
	 * this.isOrganization = isOrganization; }
	 */

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public Integer getCurrtRoleId() {
		return LoginInfoUtil.getCurrentUser().getRoleId();
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
