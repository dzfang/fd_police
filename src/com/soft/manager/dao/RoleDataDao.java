package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.RoleData;

public interface RoleDataDao {
	/**
	 * 
	 * getRoleData(根据菜单ID，角色ID查询角色数据权限)
	 * 
	 * @param params
	 *            菜单ID，角色ID
	 * @return List<RoleData> 角色数据权限
	 * @exception
	 */
	public List<RoleData> getRoleData(Map<String, Object> params);

	/**
	 * 
	 * insertRoleData(新增角色数据范围信息)
	 * 
	 * @param roleData
	 *            角色数据范围信息
	 * @return int 返回新增数据笔数
	 * @exception
	 */
	public int insertRoleData(RoleData roleData);

	/**
	 * 
	 * deleteRoleDataByRoleId(根据角色ID删除角色数据范围信息)
	 * 
	 * @param roleId
	 *            角色ID
	 * @return int 返回删除的数据笔数
	 * @exception
	 */
	public int deleteRoleDataByRoleId(Integer roleId);

	/**
	 * 
	 * getRoleData(根据菜单ID，角色ID查询可分配的角色数据权限)
	 * 
	 * @param params
	 *            菜单ID，角色ID
	 * @return List<RoleData> 角色数据权限
	 * @exception
	 */
	public List<RoleData> getAllocDataList(Map<String, Object> params);

	/**
	 * 
	 * insertAllocateData(新增角色可分配数据范围信息)
	 * 
	 * @param roleData
	 *            角色数据范围信息
	 * @return int 返回新增数据笔数
	 * @exception
	 */
	public int insertAllocateData(RoleData roleData);
	
	/**
	 * 
	 * deleteAllocateData(根据角色ID删除该角色可分配的数据范围)
	 * 
	 * @param roleId
	 *            角色ID
	 * @return int 返回删除的数据笔数
	 * @exception
	 */
	public int deleteAllocateData(Integer roleId);

}