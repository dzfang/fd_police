package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.MenuInfo;

public interface MenuInfoDao {
	/**
	 * 查询菜单列表
	 * 
	 * @return
	 */
	List<MenuInfo> getMenuList();

	/**
	 * 
	 * getAdminMenuList(查询管理员可分配菜单)
	 * 
	 * @param roleId
	 *            角色ID
	 * @return List<MenuInfo> 菜单列表
	 * @exception
	 */
	public List<MenuInfo> getAdminMenuList(Integer roleId);

	/**
	 * 
	 * getMenuListByRoleId(根据角色ID查询上级角色分配的菜单)
	 * 
	 * @param roleId
	 *            角色ID
	 * @return List<MenuInfo> 菜单列表
	 * @exception
	 */
	public List<MenuInfo> getAllocateMenuList(Map<String, Object> param);
}