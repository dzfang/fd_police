/** 
 * @项目名：Z-Cherry Mobile 
 * @包名：com.zcherry.common.domain 
 * @文件名：RoleInfo.java 
 * @版本信息：1.0.0 
 * @创建时间：2015年4月30日-下午1:05:31
 *  
 */
package com.soft.common.domain;

/**
 * 
 * @类名称：RoleInfo
 * @类描述：(角色实体类)
 * @创建人：段志芳
 * @创建时间：2015年4月30日 下午1:07:30
 * @修改人：段志芳
 * @修改时间：2015年4月30日 下午1:07:30
 * @修改备注：
 * @version 1.0.0
 *
 */
public class RoleInfo extends BaseEntity {
	/**
	 * 序列化的版本号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 父节点ID，表示当前角色是由那个角色创建的
	 */
	private Integer parentId;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 菜单ID组成的字符串
	 */
	private String menuIds;
	/**
	 * 当前角色创建的角色数量
	 */
	private Integer childrenCount;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public Integer getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}

}