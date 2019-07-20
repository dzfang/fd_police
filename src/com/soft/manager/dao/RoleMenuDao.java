package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.MenuInfo;
import com.soft.common.domain.RoleMenu;

/**
 * 
 * 
 * @类名称：RoleMenuDao
 * @类描述：(角色菜单数据持久化接口)
 * @创建人：段志芳
 * @创建时间：2015年4月30日 下午3:09:49
 * @修改人：段志芳
 * @修改时间：2015年4月30日 下午3:09:49
 * @修改备注：
 * @version 1.0.0
 *
 */
public interface RoleMenuDao {
	/**
	 * 
	 * getMenuListByRoleId(根据角色ID查询角色菜单信息)
	 * 
	 * @param roleId
	 *            角色ID
	 * @return List<RoleMenu> 角色菜单列表
	 * @exception
	 */
	public List<RoleMenu> getMenuListByRoleId(Integer roleId);

	/**
	 * 
	 * deleteRoleMenusByRoleId(根据角色ID批量删除角色菜单信息)
	 * 
	 * @param params
	 *            Map对象，包含了一个角色ID数组
	 * @return int 返回删除的数据笔数
	 * @exception
	 */
	public int deleteRoleMenusByRoleId(Map<String, Object> params);

	/**
	 * 
	 * insertRoleMenu(添加角色菜单信息)
	 * 
	 * @param roleMenu
	 *            角色菜单对象
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertRoleMenu(RoleMenu roleMenu);

	/**
	 * 
	 * getSubmenuByRoleId(根据当前登录用户的角色查询菜单信息)
	 * 
	 * @param roleMenu
	 *            角色菜单对象
	 * @return List<MenuInfo> 菜单列表
	 * @exception
	 */
	public List<MenuInfo> getSubmenuByRoleId(RoleMenu roleMenu);

	/**
	 * 
	 * deletePersonalMenuByUserId(根据用户ID删除用户快捷菜单)
	 * 
	 * @param userId
	 *            用户ID
	 * @return int 返回删除数据笔数
	 * @exception
	 */
	public int deletePersonalMenuByUserId(Integer userId);

	/**
	 * 
	 * insertPersonalMenu(添加用户快捷菜单)
	 * 
	 * @param params
	 *            Map对象，包含用户ID以及一个菜单ID数组
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertPersonalMenu(Map<String, Object> params);

	/**
	 * 
	 * deleteAllocateMenu(根据角色ID删除该角色可分配的菜单信息)
	 * 
	 * @param params
	 *            菜单ID
	 * @return int 返回删除的数据笔数
	 * @exception
	 */
	public int deleteAllocateMenu(Map<String, Object> params);

	/**
	 * 
	 * insertAllocateMenu(新增角色可分配菜单信息)
	 * 
	 * @param roleMenu
	 *            角色菜单信息
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertAllocateMenu(RoleMenu roleMenu);

	/**
	 * 
	 * getMenuListByMenuIdAndRoleId(根据菜单，角色ID查询菜单授权信息)
	 * 
	 * @param roleMenu
	 * @return List<RoleMenu>
	 * @exception
	 */
	public List<RoleMenu> getMenuListByMenuIdAndRoleId(RoleMenu roleMenu);

}