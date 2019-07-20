package com.soft.common.domain;

public class RoleMenu {
	/**
	 * 菜单ID
	 */
	private Integer menuId;
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 请求动作ID
	 */
	private String actionId;
	
	private Integer userId;
	
	private String menuName;
	
	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * 重写equals方法
	 */
	public boolean equals(Object o) {
		if (!(o instanceof RoleMenu)) {
			return false;
		}
		RoleMenu menu = (RoleMenu) o;
		return menu.menuId == this.menuId && menu.roleId == this.roleId
				&& menu.actionId.equals(this.actionId);
	}

	/**
	 * 重写hashCode方法
	 */
	public int hashCode() {
		int result = 17;
		result = 37 * result + (int) menuId + (int) roleId
				+ actionId.hashCode();
		return result;
	}
}