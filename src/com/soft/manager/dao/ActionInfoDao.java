package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.ActionInfo;

public interface ActionInfoDao {
	/**
	 * 
	 * getActionInfoListByMenuId(根据菜单ID查询按钮动作信息)
	 * 
	 * @param params
	 *            菜单ID,角色ID
	 * @return List<ActionInfo> 按钮动作信息列表
	 * @exception
	 */
	public List<ActionInfo> getActionInfoList(Map<String, Object> params);

	/**
	 * 
	 * getActionInfoListByRoleId(查询角色所能分配的请求动作)
	 * 
	 * @param roleId
	 *            角色ID
	 * @return List<ActionInfo> 请求动作列表
	 * @exception
	 */
	public List<ActionInfo> getAllocActionInfoList(Map<String, Object> params);
}