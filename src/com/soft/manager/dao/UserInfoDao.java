package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.UserInfo;

public interface UserInfoDao {
	/**
	 * 用户登录验证
	 * 
	 * @param userInfo
	 * @return
	 */
	public UserInfo doLogin(UserInfo userInfo);

	/**
	 * 
	 * getChildList(根据当前角色ID查询子角色ID)
	 * 
	 * @param roleId
	 *            当前角色ID
	 * @return String 父角色ID字符串
	 * @exception
	 */
	public String getChildList(Integer roleId);

	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo findUserById(Integer userId);

	/**
	 * 根据用户ID批量删除用户信息
	 * 
	 * @param params
	 * @return
	 */
	public int deleteUsersById(Map<String, Object> params);

	/**
	 * 根据用户名查询用户笔数
	 * 
	 * @param loginId
	 * @return
	 */
	public int getUserCountByLoginId(UserInfo userInfo);

	/**
	 * 新增用户信息
	 * 
	 * @param record
	 * @return
	 */
	public int insertUser(UserInfo userInfo);

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public UserInfo getUserByLoginId(UserInfo userInfo);

	/**
	 * 根据用户ID更新用户信息
	 * 
	 * @param record
	 * @return
	 */
	public int updateUserById(UserInfo userInfo);

	/**
	 * 根据条件查询用户笔数
	 * 
	 * @param userInfo
	 * @return
	 */
	public int getUserCount(UserInfo userInfo);

	/**
	 * 根据查询条件查询用户列表
	 * 
	 * @param params
	 * @return
	 */
	public List<UserInfo> getUserList(Map<String, Object> params);

	/**
	 * 
	 * updatePasswordById(根据用户ID批量重置用户密码)
	 * 
	 * @param params
	 *            Map对象，包含了一个用户ID数组、用户密码、更新者ID、更新时间
	 * @return int 返回更新数据的笔数
	 * @exception
	 */
	public int updatePasswordById(Map<String, Object> params);

	/**
	 * 
	 * updateLoginTimeById(根据用户ID更新用户最后登录时间)
	 * 
	 * @param userInfo
	 *            用户对象信息
	 * @return int 返回更新数据的笔数
	 * @exception
	 */
	public int updateLoginTimeById(UserInfo userInfo);
}