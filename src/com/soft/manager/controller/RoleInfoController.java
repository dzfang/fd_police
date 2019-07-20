package com.soft.manager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.soft.common.domain.MenuInfo;
import com.soft.common.domain.RoleInfo;
import com.soft.common.domain.RoleMenu;
import com.soft.common.domain.TreeNode;
import com.soft.manager.Constants;
import com.soft.manager.service.MenuInfoService;
import com.soft.manager.service.RoleInfoService;

/**
 * 
 * 
 * @类名称：RoleInfoController
 * @类描述：(角色Controller,提供维护角色的功能)
 * @创建人：段志芳
 * @创建时间：2015年4月30日 下午1:52:23
 * @修改人：段志芳
 * @修改时间：2015年4月30日 下午1:52:23
 * @修改备注：
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping("role")
public class RoleInfoController extends BaseController {
	/**
	 * 角色Service
	 */
	@Resource
	private RoleInfoService roleInfoService;
	/**
	 * 菜单Service
	 */
	@Resource
	private MenuInfoService menuInfoService;

	/**
	 * 
	 * listInit(角色列表画面初始化)
	 * 
	 * @return String 返回roleList.jsp页面
	 * @exception
	 */
	@RequestMapping("listInit.do")
	public String listInit() {
		return "role/roleList";
	}

	/**
	 * 
	 * getRoleList(根据画面录入的查询条件，查询角色信息)
	 * 
	 * @param roleInfo
	 *            角色信息对象
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 * @return Map<String,Object> 返回数据总笔数及当前页的角色列表
	 * @exception
	 */
	@RequestMapping("getRoleList.do")
	public @ResponseBody Map<String, Object> getRoleList(RoleInfo roleInfo,
			int pageIndex, int pageSize) {
		return roleInfoService.getRoleList(roleInfo, pageIndex, pageSize);
	}

	/**
	 * 
	 * deleteRoleById(根据角色ID删除角色信息)
	 * 
	 * @param roleInfo
	 *            角色信息对象
	 * @return String 返回执行结果(成功：DELETE_SUCCESS;失败：DELETE_FAILED)
	 * @exception
	 */
	@RequestMapping("deleteRoleById.do")
	public @ResponseBody String deleteRoleById(RoleInfo roleInfo) {
		Integer[] idArray = new Integer[] { roleInfo.getRoleId() };
		RoleInfo role = roleInfoService.findRoleById(roleInfo.getRoleId());
		if (role.getParentId() == 0) {
			return Constants.IS_SYSTEM_ROLE;
		}
		String result = roleInfoService.deleteRolesById(idArray);
		// 重新加载菜单
		if (Constants.DELETE_SUCCESS.equals(result)) {
			menuInfoService.getMenuList();
		}
		return result;
	}

	/**
	 * 
	 * deleteRolesById(根据勾选的角色ID批量删除角色信息)
	 * 
	 * @param idArray
	 *            角色ID数组
	 * @return String 返回执行结果(成功：DELETE_SUCCESS;失败：DELETE_FAILED)
	 * @exception
	 */
	@RequestMapping("deleteRolesById.do")
	public @ResponseBody String deleteRolesById(
			@RequestParam(value = "idArray[]") Integer[] idArray) {
		return roleInfoService.deleteRolesById(idArray);
	}

	/**
	 * 
	 * addInit(初始化新增画面)
	 * 
	 * @return ModelAndView 返回roleSave.jsp页面
	 * @exception
	 */
	@RequestMapping("addInit.do")
	public String addInit() {
		return "role/roleSave";
	}

	/**
	 * 
	 * getMenuInfo(根据角色ID查询角色对应的菜单信息)
	 * 
	 * @param roleId
	 *            角色ID
	 * @return List<TreeNode> 菜单列表
	 * @exception
	 */
	@RequestMapping("getMenuInfo.do")
	public @ResponseBody List<TreeNode> getMenuInfo(Integer roleId) {
		// 角色菜单列表
		List<RoleMenu> roleMenuList = null;
		if (roleId != null) {
			// 根据角色ID查询角色对应的菜单信息
			roleMenuList = menuInfoService.getMenuListByRoleId(roleId);
		}
		// 所有菜单信息列表
		List<MenuInfo> menuList = menuInfoService.getMenuList();
		// 树型菜单结构列表
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		// 遍历所有菜单信息
		for (MenuInfo item : menuList) {
			// 判断当前菜单是否有父菜单，如果没有，将其添加到根节点
			if (item.getParentId() == null) {
				// 创建TreeNode对象
				TreeNode root = new TreeNode();
				// 设定节点ID
				root.setId(item.getMenuId().toString());
				// 设定节点文本
				root.setText(item.getMenuName());
				// 设定当前节点是否为选中状态
				root.setChecked(menuIsAuthed(item.getMenuId(), roleMenuList));
				// 遍历所有菜单信息，取得当前菜单的子菜单
				for (MenuInfo menu : menuList) {
					if (menu.getParentId() == item.getMenuId()) {
						// 创建子菜单节点
						TreeNode node = new TreeNode();
						// 子菜单ID
						node.setId(menu.getMenuId().toString());
						// 子菜单名称
						node.setText(menu.getMenuName());
						// 子菜单是否为选中状态
						node.setChecked(menuIsAuthed(menu.getMenuId(),
								roleMenuList));
						// 添加到父节点下
						root.getChildren().add(node);
					}
				}
				// 将节点对象添加到树型结构列表中
				nodeList.add(root);
			}
		}
		// 返回树型菜单结构列表
		return nodeList;
	}

	/**
	 * 
	 * menuIsAuthed(判断菜单是否已分配给角色)
	 * 
	 * @param menuId
	 *            菜单ID
	 * @param roleMenuList
	 *            角色菜单列表
	 * @return boolean 若改菜单已分配，返回true,否则,返回false
	 * @exception
	 */
	private boolean menuIsAuthed(Integer menuId, List<RoleMenu> roleMenuList) {
		// 角色菜单列表为空，返回false
		if (roleMenuList == null || roleMenuList.size() == 0) {
			return false;
		}
		// 遍历角色菜单信息列表，根据menuId判断该菜单是否已分配给当前角色
		for (RoleMenu roleMenu : roleMenuList) {
			// 已分配，返回true
			if (roleMenu.getMenuId().equals(menuId)) {
				return true;
			}
		}
		// 遍历完毕，说明没有分配，返回false
		return false;
	}

	/**
	 * 
	 * saveRole(新增或更新角色信息)
	 * 
	 * @param roleInfo
	 *            角色信息对象
	 * @return String 返回执行结果：成功：SAVE_SUCCESS;失败：SAVE_FAILED
	 * @exception
	 */
	@RequestMapping("saveRole.do")
	public @ResponseBody String saveRole(RoleInfo roleInfo) {
		String result = "";
		// 新增角色
		if (null == roleInfo.getRoleId()) {
			result = roleInfoService.insertRole(roleInfo);
		}
		// 更新角色
		else {
			result = roleInfoService.updateRoleById(roleInfo);
		}
		// 重新加载菜单
		if (Constants.SAVE_SUCCESS.equals(result)) {
			menuInfoService.getMenuList();
		}
		return result;
	}

	/**
	 * 
	 * getRoleById(初始化角色更新画面)
	 * 
	 * @param roleId
	 *            角色ID
	 * @return ModelAndView 返回roleSave.jsp页面
	 * @exception
	 */
	@RequestMapping("updateInit.do")
	public ModelAndView getRoleById(Integer roleId) {
		// 根据角色ID查询角色信息
		RoleInfo roleInfo = roleInfoService.findRoleById(roleId);
		ModelAndView modelAndView = new ModelAndView("role/roleSave");
		// 将角色信息对象返回到页面
		modelAndView.addObject("roleModel", roleInfo);
		return modelAndView;
	}

	/**
	 * 
	 * getAllRoles(查询所有角色信息)
	 * 
	 * @return List<RoleInfo> 返回角色信息列表
	 * @exception
	 */
	@RequestMapping("getAllRoles.do")
	public @ResponseBody List<RoleInfo> getAllRoles() {
		return roleInfoService.getAllRoles();
	}

	/**
	 * 
	 * listInit(角色列表画面初始化)
	 * 
	 * @return String 返回roleList.jsp页面
	 * @exception
	 */
	@RequestMapping("roleList.do")
	public String roleList() {
		return "role/roleList1";
	}

	@RequestMapping("findRoleById.do")
	public @ResponseBody List<TreeNode> findRoleById(Integer id) {
		RoleInfo role = roleInfoService.findRoleById(id);
		// 树型菜单结构列表
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		// 创建TreeNode对象
		TreeNode root = new TreeNode();
		// 设定节点ID
		root.setId(role.getRoleId().toString());
		// 设定节点文本
		root.setText(role.getRoleName());
		root.setIconCls("tree-collapsed");
		root.setState("closed");
		// 将节点对象添加到树型结构列表中
		nodeList.add(root);
//		// 添加教师角色
//		root = new TreeNode();
//		root.setId(Constants.ROLE_TEACHER_ID);
//		root.setText(Constants.ROLE_TEACHER_TEXT);
//		nodeList.add(root);
//		// 添加学生角色
//		root = new TreeNode();
//		root.setId(Constants.ROLE_STUDENT_ID);
//		root.setText(Constants.ROLE_STUDENT_TEXT);
//		nodeList.add(root);
		return nodeList;
	}

	/**
	 * 
	 * getRoleByParentId(根据父节点ID查询角色信息)
	 * 
	 * @param id
	 * @return List<TreeNode> 树型节点列表
	 * @exception
	 */
	@RequestMapping("getRoleByParentId.do")
	public @ResponseBody List<TreeNode> getRoleByParentId(Integer id) {
		return roleInfoService.getRoleByParentId(id);
	}

	@RequestMapping("addRole.do")
	public String addRole() {
		return "role/roleSave1";
	}

	@RequestMapping("updateRole.do")
	public ModelAndView updateRole(Integer roleId) {
		// 根据角色ID查询角色信息
		RoleInfo roleInfo = roleInfoService.findRoleById(roleId);
		ModelAndView modelAndView = new ModelAndView("role/roleSave1");
		// 将角色信息对象返回到页面
		modelAndView.addObject("record", roleInfo);
		return modelAndView;
	}

	/**
	 * 
	 * funcList(查询菜单，功能列表)
	 * 
	 * @param id
	 *            角色ID
	 * @return ModelAndView
	 * @exception
	 */
	@RequestMapping("funcList.do")
	public ModelAndView funcList(Integer id) {
		// if (null == id) {
		// id = LoginInfoUtil.getCurrentUser().getRoleId();
		// }

		List<MenuInfo> menuList = menuInfoService.getAdminMenuList(id);
		ModelAndView modelAndView = new ModelAndView("role/funcList");
		modelAndView.addObject("menuList", menuList);
		modelAndView.addObject("roleId", id);
		return modelAndView;
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
	@RequestMapping("saveRoleAuth.do")
	public @ResponseBody String saveRoleAuth(Integer roleId, String authString) {
		return roleInfoService.saveRoleAuth(roleId, authString);
	}
}
