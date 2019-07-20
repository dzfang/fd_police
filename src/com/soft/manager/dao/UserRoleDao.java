package com.soft.manager.dao;


import java.util.Map;

import com.soft.common.domain.UserRole;
/**
 * 
 *  
 * @类名称：UserRoleDao 
 * @类描述：(用户角色的持久化接口) 
 * @创建人：赵娜 
 * @创建时间：2015年7月21日 上午9:36:12 
 * @修改人：赵娜 
 * @修改时间：2015年7月21日 上午9:36:12 
 * @修改备注： 
 * @version 1.0.0 
 *
 */
public interface UserRoleDao {
	
	/**
	 * 
	 * insertUserRole(添加用户角色)  
	 * @param userRole
	 * @return  
	 * int 
	 * @exception
	 */
	public int insertUserRole(UserRole userRole);
	/**
	 * 
	 * updateUserRoleById(更新用户角色)  
	 * @param userRole
	 * @return  
	 * int 
	 * @exception
	 */
	public int updateUserRoleById(UserRole userRole);
	/**
	 * 
	 * deleteUserRolesByUserId(删除用户角色)  
	 * @param params
	 * @return  
	 * int 
	 * @exception
	 */
	public int deleteUserRolesByUserId(Map<String, Object> params);
	/**
	 * 
	 * findUserRoleById(根据roleID查找角色是否为空，为空时执行insert，不为空执行update)  
	 * @param employeeId
	 * @return  
	 * Object 
	 * @exception
	 */
	public Object findUserRoleById(Integer employeeId);
	
}