package com.soft.manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.ActionInfo;
import com.soft.common.domain.MenuInfo;
import com.soft.common.domain.RoleData;
import com.soft.common.domain.RoleInfo;
import com.soft.common.domain.RoleMenu;
import com.soft.common.domain.TOrganization;
import com.soft.common.domain.UserInfo;
import com.soft.manager.Constants;
import com.soft.manager.dao.ActionInfoDao;
import com.soft.manager.dao.MenuInfoDao;
import com.soft.manager.dao.RoleDataDao;
import com.soft.manager.dao.RoleInfoDao;
import com.soft.manager.dao.RoleMenuDao;
import com.soft.manager.dao.TOrganizationDao;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

@Service
public class MenuInfoService {
	@Resource
	private MenuInfoDao menuInfoDao;
	@Resource
	private RoleMenuDao roleMenuDao;
	@Resource
	private RoleInfoDao roleInfoDao;
	@Resource
	private ActionInfoDao actionInfoDao;
	@Resource
	private TOrganizationDao organizationDao;
	@Resource
	private RoleDataDao roleDataDao;

	private static List<MenuInfo> globalMenuList;

	/**
	 * 查询菜单列表
	 * 
	 * @return
	 */
	public List<MenuInfo> getMenuList() {
		// 查询菜单列表
		if (globalMenuList == null) {
			globalMenuList = menuInfoDao.getMenuList();
		}
		return globalMenuList;
	}

	/**
	 * 根据角色ID查询菜单列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleMenu> getMenuListByRoleId(Integer roleId) {
		return roleMenuDao.getMenuListByRoleId(roleId);
	}

	/**
	 * 查询角色对应的菜单信息
	 * 
	 * @param roleId
	 * @return
	 */
	public MenuInfo getMenuInfo(Integer roleId) {
		List<MenuInfo> tempMenuList = new ArrayList<MenuInfo>();
		// 查询菜单列表
		List<MenuInfo> menuList = menuInfoDao.getMenuList();

		// 查询角色菜单列表
		List<RoleMenu> roleMenuList = roleMenuDao.getMenuListByRoleId(roleId);
		// 通过角色菜单列表过滤菜单信息
		if (roleMenuList != null && roleMenuList.size() > 0) {
			for (MenuInfo menu : menuList) {
				for (RoleMenu roleMenu : roleMenuList) {
					if (menu.getMenuId().equals(roleMenu.getMenuId())) {
						tempMenuList.add(menu);
					}
				}
			}
			List<MenuInfo> authedMenuList = new ArrayList<MenuInfo>();
			for (MenuInfo menu : tempMenuList) {
				getParentMenu(menu, menuList, authedMenuList);
			}
			menuList = authedMenuList;
		}
		return CreateTreeNodes(menuList);
	}

	/**
	 * 获得父节点菜单信息
	 * 
	 * @param menu
	 * @param menuList
	 * @param authedMenuList
	 */
	private void getParentMenu(MenuInfo menu, List<MenuInfo> menuList, List<MenuInfo> authedMenuList) {
		if (!authedMenuList.contains(menu)) {
			authedMenuList.add(menu);
		}
		if (menu.getParentId() != null) {
			for (MenuInfo item : menuList) {
				if (item.getMenuId().intValue() == menu.getParentId().intValue()) {
					if (!authedMenuList.contains(item)) {
						authedMenuList.add(item);
						getParentMenu(item, menuList, authedMenuList);
					}
				}
			}
		}
	}

	/**
	 * 构造树型结构菜单
	 * 
	 * @param menusList
	 * @return
	 */
	public MenuInfo CreateTreeNodes(List<MenuInfo> menusList) {
		MenuInfo rootNode = new MenuInfo();

		for (MenuInfo item : menusList) {
			if (item.getParentId() == null || item.getParentId().toString() == "") {
				rootNode.getChildren().add(item);
				FillChildren(item, menusList, item.getMenuId());
			}
		}
		return rootNode;
	}

	/**
	 * 填充子节点
	 * 
	 * @param node
	 * @param menusList
	 * @param nodeRootId
	 */
	private void FillChildren(MenuInfo node, List<MenuInfo> menusList, Integer nodeRootId) {
		for (MenuInfo item : menusList) {
			if (item.getParentId() != null && item.getParentId().intValue() == node.getMenuId().intValue()) {
				node.getChildren().add(item);
				FillChildren(item, menusList, item.getMenuId());
			}
		}
	}

	/**
	 * 
	 * getAdminMenuList(查询管理员可分配菜单)
	 * 
	 * @param roleId 角色ID @return List<MenuInfo> 菜单列表 @exception
	 */
	public List<MenuInfo> getAdminMenuList(Integer roleId) {
		UserInfo currentUser = LoginInfoUtil.getCurrentUser();

		RoleInfo roleInfo = roleInfoDao.findRoleById(roleId);

		List<MenuInfo> menuList = null;
		// 判断当前登录者是否是管理员，如果是管理员，查询管理员菜单
		if (0 == roleInfo.getParentId()
				|| (currentUser.getRoleId().equals(roleInfo.getParentId()) && 0 == currentUser.getRoleParentId())) {
			menuList = menuInfoDao.getAdminMenuList(roleId);
			if (menuList != null) {
				for (MenuInfo menu : menuList) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("roleId", roleId);
					params.put("menuId", menu.getMenuId());
					List<ActionInfo> actionList = actionInfoDao.getActionInfoList(params);
					menu.setActionList(actionList);

					List<RoleData> resourceList = new ArrayList<RoleData>();

					List<RoleData> roleDataList = roleDataDao.getRoleData(params);

					if (Utils.isNotEmptyString(menu.getIsPersonal()) && "1".equals(menu.getIsPersonal())) {
						RoleData data = new RoleData();
						data.setDataId("-1");
						data.setDataName("个人数据");
						data.setIsChecked(isChecked(roleDataList, "-1"));
						resourceList.add(data);
					}
					if (Utils.isNotEmptyString(menu.getIsOrganization()) && "1".equals(menu.getIsOrganization())) {
						List<TOrganization> orgList = organizationDao.getAllOrganization();
						for (TOrganization org : orgList) {
							RoleData data = new RoleData();
							data.setDataId(org.getId().toString());
							data.setDataName(org.getOrgName());
							data.setIsChecked(isChecked(roleDataList, org.getId().toString()));
							resourceList.add(data);
						}
					}
					menu.setResourceList(resourceList);
				}
			}
		}
		// 否则查询上级角色给当前角色分配的菜单
		else {
			Map<String, Object> param = new HashMap<String, Object>();
			Integer currtRoleId = roleId.equals(currentUser.getRoleId()) ? roleId : roleInfo.getParentId();
			param.put("roleId", roleId);
			param.put("currtRoleId", currtRoleId);
			menuList = menuInfoDao.getAllocateMenuList(param);
			if (menuList != null) {
				for (MenuInfo menu : menuList) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("roleId", roleId);
					params.put("currtRoleId", currtRoleId);
					params.put("menuId", menu.getMenuId());
					List<ActionInfo> actionList = actionInfoDao.getAllocActionInfoList(params);
					menu.setActionList(actionList);

					List<RoleData> dataList = roleDataDao.getAllocDataList(params);
					menu.setResourceList(dataList);
				}
			}
		}

		return menuList;
	}

	private String isChecked(List<RoleData> roleDataList, String id) {
		if (Utils.isNotEmptyString(id)) {
			for (RoleData item : roleDataList) {
				if (id.equals(item.getDataId())) {
					return "1";
				}
			}
		}
		return "0";
	}

	/**
	 * 
	 * getMenuListByMenuIdAndRoleId(根据菜单，角色ID查询菜单授权信息)
	 * 
	 * @param roleMenu @return List<RoleMenu> @exception
	 */
	public List<RoleMenu> getMenuListByMenuIdAndRoleId(Integer menuId) {
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setMenuId(menuId);
		roleMenu.setRoleId(LoginInfoUtil.getCurrentUser().getRoleId());
		return roleMenuDao.getMenuListByMenuIdAndRoleId(roleMenu);
	}

	/**
	 * 
	 * getSubmenuByRoleId(根据当前登录用户的角色查询菜单信息)
	 * 
	 * @param roleId 角色ID @return List<MenuInfo> 菜单列表 @exception
	 */
	public Map<String, Object> getSubmenuByRoleId(Integer roleId) {
		// 当前登录用户的ID
		Integer userId = Integer.parseInt(LoginInfoUtil.getCurrentUser().getUserId().toString());
		RoleMenu roleMenu = new RoleMenu();
		// 用户角色
		roleMenu.setRoleId(roleId);
		// 用户ID
		roleMenu.setUserId(userId);
		// 查询菜单信息
		List<MenuInfo> menuList = roleMenuDao.getSubmenuByRoleId(roleMenu);
		// 结果集
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// APP用户信息
		resultMap.put("rows", menuList);
		return resultMap;
	}

	/**
	 * 
	 * saveMenu(保存个人快捷菜单)
	 * 
	 * @param idArray 菜单ID @return String
	 * 返回执行结果(成功：SAVE_SUCCESS;失败：SAVE_FAILED) @exception
	 */
	public String insertPersonalMenu(Integer[] idArray) {
		// 当前登录用户ID
		Integer userId = Integer.parseInt(LoginInfoUtil.getCurrentUser().getUserId().toString());
		int result = 0;
		if (idArray.length > 0) {
			// 根据用户ID删除用户快捷菜单
			roleMenuDao.deletePersonalMenuByUserId(userId);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userId);
			params.put("idArray", idArray);
			result = roleMenuDao.insertPersonalMenu(params);
		} else {
			result = roleMenuDao.deletePersonalMenuByUserId(userId);
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

}
