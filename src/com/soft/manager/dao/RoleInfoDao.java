package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.RoleInfo;

/**
 * 
 * 
 * @类名称：RoleInfoDao
 * @类描述：(角色信息数据持久化接口)
 * @创建人：段志芳
 * @创建时间：2015年4月30日 下午3:04:14
 * @修改人：段志芳
 * @修改时间：2015年4月30日 下午3:04:14
 * @修改备注：
 * @version 1.0.0
 *
 */
public interface RoleInfoDao {

	/**
	 * 
	 * findRoleById(根据角色ID查询角色信息)
	 * 
	 * @param roleId
	 *            角色ID
	 * @return RoleInfo 角色信息对象
	 * @exception
	 */
	public RoleInfo findRoleById(Integer roleId);

	/**
	 * 
	 * deleteRolesById(根据角色ID批量删除角色信息)
	 * 
	 * @param params
	 *            Map对象，包含了一个角色ID数组
	 * @return int 返回删除的数据笔数
	 * @exception
	 */
	public int deleteRolesById(Map<String, Object> params);

	/**
	 * 
	 * getRoleInfoByName(根据角色名查询角色信息)
	 * 
	 * @param roleInfo
	 *            角色信息对象
	 * @return RoleInfo 角色信息对象
	 * @exception
	 */
	public RoleInfo getRoleInfoByName(RoleInfo roleInfo);

	/**
	 * 
	 * insertRole(新增角色信息)
	 * 
	 * @param roleInfo
	 *            角色信息对象
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertRole(RoleInfo roleInfo);

	/**
	 * 
	 * updateRoleById(根据角色ID更新角色信息)
	 * 
	 * @param roleInfo
	 *            角色信息对象
	 * @return int 返回更新的数据笔数
	 * @exception
	 */
	public int updateRoleById(RoleInfo roleInfo);

	/**
	 * 
	 * getRoleCount(根据查询条件查询角色笔数)
	 * 
	 * @param roleInfo
	 *            角色信息对象
	 * @return int 返回数据笔数
	 * @exception
	 */
	public int getRoleCount(RoleInfo roleInfo);

	/**
	 * 
	 * getRoleList(根据查询条件查询角色列表)
	 * 
	 * @param params
	 *            Map对象，包含RoleInfo对象,当前页码以及每页数据笔数
	 * @return List<RoleInfo> 返回角色列表
	 * @exception
	 */
	public List<RoleInfo> getRoleList(Map<String, Object> params);

	/**
	 * 
	 * getAllRoles(查询所有角色信息)
	 * 
	 * @return List<RoleInfo> 返回角色列表
	 * @exception
	 */
	public List<RoleInfo> getAllRoles();

	/**
	 * 
	 * getRoleByParentId(根据父节点ID查询角色信息)
	 * 
	 * @param id
	 *            父节点ID
	 * @return List<RoleInfo> 角色列表
	 * @exception
	 */
	public List<RoleInfo> getRoleByParentId(Integer id);
}