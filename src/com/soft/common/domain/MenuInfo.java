package com.soft.common.domain;

import java.util.ArrayList;
import java.util.List;

public class MenuInfo extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 菜单ID
	 */
	private Integer menuId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 请求url
	 */
	private String menuUrl;
	/**
	 * 父节点
	 */
	private Integer parentId;
	/**
	 * 菜单图标
	 */
	private String menuIcon;
	/**
	 * 菜单排序序号
	 */
	private Integer menuSort;

	/**
	 * 子节点
	 */
	private List<MenuInfo> children;

	/**
	 * 按钮动作列表
	 */
	private List<ActionInfo> actionList;

	/**
	 * 数据范围列表
	 */
	private List<RoleData> resourceList;
	/**
	 * 是否按照个人过滤数据
	 */
	private String isPersonal;
	/**
	 * 是否按照组织机构过滤数据
	 */
	private String isOrganization;
	/**
	 * 菜单是否被勾选
	 */
	private String isChecked;

	/**
	 * 构造函数
	 */
	public MenuInfo() {
		children = new ArrayList<MenuInfo>();
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon == null ? null : menuIcon.trim();
	}

	public Integer getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

	public List<MenuInfo> getChildren() {
		return children;
	}

	public void setChildren(List<MenuInfo> children) {
		this.children = children;
	}

	/**
	 * 是否有子节点
	 * 
	 * @return
	 */
	public boolean hasChildren() {
		if (children != null && children.size() > 0) {
			return true;
		}
		return false;
	}

	public List<ActionInfo> getActionList() {
		return actionList;
	}

	public void setActionList(List<ActionInfo> actionList) {
		this.actionList = actionList;
	}

	public List<RoleData> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<RoleData> resourceList) {
		this.resourceList = resourceList;
	}

	public String getIsPersonal() {
		return isPersonal;
	}

	public void setIsPersonal(String isPersonal) {
		this.isPersonal = isPersonal;
	}

	public String getIsOrganization() {
		return isOrganization;
	}

	public void setIsOrganization(String isOrganization) {
		this.isOrganization = isOrganization;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	/**
	 * 重写equals方法
	 */
	public boolean equals(Object o) {
		if (!(o instanceof MenuInfo)) {
			return false;
		}
		MenuInfo menu = (MenuInfo) o;
		return menu.menuId == this.menuId;
	}

	/**
	 * 重写hashCode方法
	 */
	public int hashCode() {
		int result = 17;
		result = 37 * result + (int) menuId;
		return result;
	}
}