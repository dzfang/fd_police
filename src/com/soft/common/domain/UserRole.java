package com.soft.common.domain;

public class UserRole {
	//角色ID
    private Integer roleId;
    //用户ID
    private Long userId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}