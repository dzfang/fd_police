package com.soft.manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.RoleData;
import com.soft.common.domain.RoleInfo;
import com.soft.common.domain.RoleMenu;
import com.soft.common.domain.TreeNode;
import com.soft.manager.Constants;
import com.soft.manager.dao.RoleDataDao;
import com.soft.manager.dao.RoleInfoDao;
import com.soft.manager.dao.RoleMenuDao;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

/**
 * 
 * 
 * @类名称：RoleInfoService
 * @类描述：(角色信息业务处理类)
 * @创建人：段志芳
 * @创建时间：2015年4月30日 下午3:50:28
 * @修改人：段志芳
 * @修改时间：2015年4月30日 下午3:50:28
 * @修改备注：
 * @version 1.0.0
 *
 */
@Service
public class RoleInfoService {
	@Resource
	private RoleInfoDao roleInfoDao;
	@Resource
	private RoleMenuDao roleMenuDao;
	@Resource
	private RoleDataDao roleDataDao;

	/**
	 * 
	 * findRoleById(根据角色ID查询角色信息)
	 * 
	 * @param roleId
	 *            角色ID
	 * @return RoleInfo 角色信息对象
	 * @exception
	 */
	public RoleInfo findRoleById(Integer roleId) {
		// 返回角色信息
		return roleInfoDao.findRoleById(roleId);
	}

	/**
	 * 
	 * deleteRolesById(根据角色ID批量删除角色信息)
	 * 
	 * @param idArray
	 *            角色ID数组
	 * @return String 返回执行结果(成功：DELETE_SUCCESS;失败：DELETE_FAILED)
	 * @exception
	 */
	public String deleteRolesById(Integer[] idArray) {
		// 创建Map对象，用于参数传递
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		int result = roleInfoDao.deleteRolesById(params);
		// 删除已分配的菜单信息
		if (result > 0) {
			roleMenuDao.deleteRoleMenusByRoleId(params);
		}
		return result > 0 ? Constants.DELETE_SUCCESS : Constants.DELETE_FAILED;
	}

	/**
	 * 
	 * insertRole(新增角色)
	 * 
	 * @param roleInfo
	 *            角色信息对象
	 * @return String
	 *         返回执行结果(成功：SAVE_SUCCESS;失败：SAVE_FAILED;角色名已存在：ROLE_NAME_EXISTS)
	 * @exception
	 */
	public String insertRole(RoleInfo roleInfo) {
		// 判断角色名是否已存在
		RoleInfo role = roleInfoDao.getRoleInfoByName(roleInfo);
		if (role != null) {
			return Constants.ROLE_NAME_EXISTS;
		}
		Integer roleId = LoginInfoUtil.getCurrentUser().getRoleId();
		roleInfo.setParentId(roleId);
		// 新增角色
		int result = roleInfoDao.insertRole(roleInfo);
		if (result > 0) {
			// 新增角色菜单信息
			insertRoleMenu(roleInfo);
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * updateRoleById(根据角色ID更新角色信息)
	 * 
	 * @param roleInfo
	 *            角色信息对象
	 * @return String
	 *         返回执行结果(成功：SAVE_SUCCESS;失败：SAVE_FAILED;角色名已存在：ROLE_NAME_EXISTS)
	 * @exception
	 */
	public String updateRoleById(RoleInfo roleInfo) {
		// 根据角色ID查询角色信息
		RoleInfo currentRoleInfo = roleInfoDao.findRoleById(roleInfo
				.getRoleId());
		// 数据版本校验
		String message = Utils.versionCheck(roleInfo, currentRoleInfo);
		// 校验未通过，返回提示信息
		if (Utils.isNotEmptyString(message)) {
			return message;
		}
		// 判断角色名称是否已存在
		RoleInfo role = roleInfoDao.getRoleInfoByName(roleInfo);
		if (role != null) {
			if (!role.getRoleId().equals(roleInfo.getRoleId())) {
				return Constants.ROLE_NAME_EXISTS;
			}
		}
		// 更新角色信息
		int result = roleInfoDao.updateRoleById(roleInfo);
		if (result > 0) {
			// 新增角色菜单信息
			insertRoleMenu(roleInfo);
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * getRoleList(根据查询条件查询角色信息)
	 * 
	 * @param roleInfo
	 *            角色信息对象
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 * @return Map<String,Object> 包含数据总笔数及当前页角色信息列表
	 * @exception
	 */
	public Map<String, Object> getRoleList(RoleInfo roleInfo, int pageIndex,
			int pageSize) {
		// 查询数据笔数
		int count = roleInfoDao.getRoleCount(roleInfo);
		// 当前页码开始下标大于记录总数时，重置当前页码，主要用于删除时，数据笔数不足时的页码重置
		if ((pageIndex - 1) * pageSize + 1 > count) {
			pageIndex = (int) Math.ceil(count / pageSize);
			pageIndex = pageIndex == 0 ? 1 : pageIndex;
		}
		// 计算每页起始下标
		int startIndex = (pageIndex - 1) * pageSize;

		Map<String, Object> params = new HashMap<String, Object>();
		// 查询条件对象
		params.put("roleInfo", roleInfo);
		// 当前页数据起始下标
		params.put("startIndex", startIndex);
		// 每页数据笔数
		params.put("pageSize", pageSize);
		// 查询角色列表
		List<RoleInfo> roleList = roleInfoDao.getRoleList(params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 数据笔数
		resultMap.put("total", count);
		// 角色信息列表
		resultMap.put("rows", roleList);
		return resultMap;
	}

	/**
	 * 
	 * insertRoleMenu(新增角色菜单信息)
	 * 
	 * @param roleInfo
	 *            角色信息
	 * @exception
	 */
	public void insertRoleMenu(RoleInfo roleInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		Integer roleId = roleInfo.getRoleId();
		Integer[] roleIdArray = new Integer[] { roleId };
		params.put("idArray", roleIdArray);
		// 删除角色原有菜单信息
		roleMenuDao.deleteRoleMenusByRoleId(params);
		// 获取当前角色的菜单ID字符串，该字符串以“,”分割
		String menuIds = roleInfo.getMenuIds();
		if (Utils.isNotEmptyString(menuIds)) {
			String[] menuIdArray = menuIds.split(",");
			// 添加角色新的菜单信息
			for (String menuId : menuIdArray) {
				RoleMenu roleMenu = new RoleMenu();
				// 设定角色ID
				roleMenu.setRoleId(roleId);
				// 设定菜单ID
				roleMenu.setMenuId(Integer.parseInt(menuId));
				// 新增角色菜单信息
				roleMenuDao.insertRoleMenu(roleMenu);
			}
		}
	}

	/**
	 * 
	 * getAllRoles(查询所有角色信息)
	 * 
	 * @return List<RoleInfo> 角色信息列表
	 * @exception
	 */
	public List<RoleInfo> getAllRoles() {
		// 查询角色列表
		return roleInfoDao.getAllRoles();
	}

	/**
	 * 
	 * getRoleByParentId(根据父节点ID查询角色信息)
	 * 
	 * @param id
	 *            父节点ID
	 * @return List<TreeNode> 树型节点列表
	 * @exception
	 */
	public List<TreeNode> getRoleByParentId(Integer id) {
		List<RoleInfo> roleList = roleInfoDao.getRoleByParentId(id);
		// 树型菜单结构列表
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		// 遍历所有菜单信息
		for (RoleInfo item : roleList) {
			// 创建TreeNode对象
			TreeNode root = new TreeNode();
			// 设定节点ID
			root.setId(item.getRoleId().toString());
			// 设定节点文本
			root.setText(item.getRoleName());
			if (item.getChildrenCount() > 0) {
				root.setIconCls("tree-collapsed");

				root.setState("closed");
			}

			// 将节点对象添加到树型结构列表中
			nodeList.add(root);
		}
		return nodeList;
	}

	/**
	 * 
	 * saveRoleAuth(保存角色权限信息)
	 * 
	 * @param roleId
	 *            角色ID
	 * @param authString
	 *            权限字符串
	 * @return String
	 * @exception
	 */
	public String saveRoleAuth(Integer roleId, String authString) {
		if (roleId == null || Utils.isEmptyString(authString)) {
			return Constants.SAVE_FAILED;
		}
		String[] array = null;
		if (authString.contains(";")) {
			array = authString.split(";");
		} else {
			array = new String[] { authString };
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", new Integer[] { roleId });
		// 删除角色菜单
		roleMenuDao.deleteRoleMenusByRoleId(params);
		// 删除角色数据范围
		roleDataDao.deleteRoleDataByRoleId(roleId);

		Integer currentRoleId = LoginInfoUtil.getCurrentUser().getRoleId();
		if (roleId.intValue() != currentRoleId.intValue()) {
			// 根据角色ID删除该角色可分配的数据范围
			roleDataDao.deleteAllocateData(roleId);
			// 根据角色ID删除该角色可分配的菜单信息
			roleMenuDao.deleteAllocateMenu(params);
		}

		int result = 0;
		List<RoleMenu> menuList = new ArrayList<RoleMenu>();

		for (String item : array) {
			String[] itemArray = item.split(",");
			if (itemArray.length == 3 && Utils.isNotEmptyString(itemArray[2])) {
				RoleData roleData = new RoleData();
				roleData.setRoleId(roleId);
				roleData.setMenuId(Integer.parseInt(itemArray[0]));
				roleData.setDataId(itemArray[2]);
				result += roleDataDao.insertRoleData(roleData);
				if (roleId.intValue() != currentRoleId.intValue()) {
					// 新增角色可分配数据范围
					roleDataDao.insertAllocateData(roleData);
				}
			} else {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuId(Integer.parseInt(itemArray[0]));
				roleMenu.setActionId(itemArray.length == 2 ? itemArray[1] : "");
				// 排除重复项
				if (!menuList.contains(roleMenu)) {
					result += roleMenuDao.insertRoleMenu(roleMenu);
					if (roleId.intValue() != currentRoleId.intValue()) {
						// 新增角色可分配菜单
						roleMenuDao.insertAllocateMenu(roleMenu);
					}
				}
				menuList.add(roleMenu);
			}
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * getRoleData(查询数据范围)
	 * 
	 * @param menuId
	 * @return List<RoleData>
	 * @exception
	 */
	public List<RoleData> getRoleData(Integer menuId) {
		Integer roleId = LoginInfoUtil.getCurrentUser().getRoleId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		params.put("menuId", menuId);

		List<RoleData> roleDataList = roleDataDao.getRoleData(params);
		return roleDataList;
	}

}
