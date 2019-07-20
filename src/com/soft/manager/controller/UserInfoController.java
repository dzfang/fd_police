package com.soft.manager.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.soft.common.domain.UserInfo;
import com.soft.manager.Constants;
import com.soft.manager.service.UserInfoService;
import com.soft.util.Config;
import com.soft.util.LoginInfoUtil;

@Controller
@RequestMapping("user")
public class UserInfoController extends BaseController {

	@Resource
	private UserInfoService userInfoService;
 
	/**
	 * 
	 * userList(用户列表)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("userList.do")
	public ModelAndView userList() {
		ModelAndView modelAndView = new ModelAndView("userInfo/userList");
		return modelAndView;
	}
	
	/**
	 * 
	 * userSearch(用户列表)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("userSearch.do")
	public ModelAndView userSearch() {
		ModelAndView modelAndView = new ModelAndView("userInfo/userSearch");
		return modelAndView;
	}
	/**
	 * 
	 * addUser(新增用户)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("addUser.do")
	public ModelAndView addUser(String orgIds) {
		ModelAndView modelAndView = new ModelAndView("userInfo/userSave");
		UserInfo user = new UserInfo();
		user.setUserStatus(Constants.STATUS_NORMAL);
		user.setPassword(Config.getDefaultPassword());
		modelAndView.addObject("userModel", user);
		modelAndView.addObject("canChangeRole", true);
		modelAndView.addObject("orgIds", orgIds);
		return modelAndView;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param userInfo
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("getUserList.do")
	public @ResponseBody Map<String, Object> getUserList(UserInfo userInfo,
			int pageIndex, int pageSize) {
		return userInfoService.getUserList(userInfo, pageIndex, pageSize);
	}

	/**
	 * 删除用户
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("deleteUserById.do")
	public @ResponseBody String deleteUserById(UserInfo userInfo) {
		Long[] idArray = new Long[] { userInfo.getUserId() };
		return userInfoService.deleteUsersById(idArray);
	}

	/**
	 * 批量删除用户
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("deleteUsersById.do")
	public @ResponseBody String deleteUsersById(
			@RequestParam(value = "idArray[]") Long[] idArray) {
		return userInfoService.deleteUsersById(idArray);
	}

	/**
	 * 添加/更新用户
	 * 
	 * @return
	 */
	@RequestMapping("saveUser.do")
	public @ResponseBody String saveUser(UserInfo userInfo) {
		String result = "";
		// 新增用户
		if (null == userInfo.getUserId()) {
			result = userInfoService.insertUser(userInfo);
		}
		// 更新用户
		else {
			result = userInfoService.updateUserById(userInfo);
		}
		return result;
	}

	/**
	 * 更新画面初始化
	 * 
	 * @param roleId
	 * @param page
	 * @return
	 */
	@RequestMapping("updateUser.do")
	public ModelAndView getUserById(Integer userId,String orgIds) {
		// 根据用户ID查询用户信息
		UserInfo userInfo = userInfoService.findUserById(userId);
		ModelAndView modelAndView = new ModelAndView("userInfo/userSave");
		modelAndView.addObject("userModel", userInfo);
		String[] childRoleIdArray = LoginInfoUtil.getCurrentUser()
				.getChildRoleIdArray();
		if (childRoleIdArray != null) {
			boolean canChange = false;
			for (String item : childRoleIdArray) {
				if (item.equals(userInfo.getRoleId().toString())) {
					canChange = true;
					break;
				}
			}
			modelAndView.addObject("canChangeRole", canChange);
		} else {
			modelAndView.addObject("canChangeRole", false);
		}
		modelAndView.addObject("orgIds", orgIds);
		return modelAndView;
	}
 
	/**
	 * 
	 * updatePasswordById(根据用户ID批量重置用户密码)
	 * 
	 * @param idArray
	 *            用户ID数组
	 * @return String 返回执行结果(成功：DELETE_SUCCESS;失败：DELETE_FAILED)
	 * @exception
	 */
	@RequestMapping("updatePasswordById.do")
	public @ResponseBody String updatePasswordById(
			@RequestParam(value = "idArray[]") Integer[] idArray) {
		return userInfoService.updatePasswordById(idArray);
	}

	/**
	 * 
	 * updatePasswordInit(初始化用户密码修改页面)
	 * 
	 * @return ModelAndView 返回userPwd.jsp页面
	 * @exception
	 */
	@RequestMapping("updatePasswordInit.do")
	public ModelAndView updatePasswordInit() {
		UserInfo userInfo = LoginInfoUtil.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView("userInfo/userPwd");
		modelAndView.addObject("userId", userInfo.getUserId());
		return modelAndView;
	}

	/**
	 * 
	 * updatePassword(修改用户密码)
	 * 
	 * @param userInfo
	 *            用户信息对象
	 * @return String 返回执行结果 ：成功：SAVE_SUCCESS;失败：SAVE_FAILED
	 * @exception
	 */
	@RequestMapping("updatePassword.do")
	public @ResponseBody String updatePassword(UserInfo userInfo) {
		return userInfoService.updatePassword(userInfo);
	}
 
	/**
	 * 添加/更新用户
	 * 
	 * @return
	 */
	@RequestMapping("doRegister.do")
	public @ResponseBody String doRegister(UserInfo userInfo) {
		userInfo.setUserType(Constants.STUDENT);
		userInfo.setUserStatus(Constants.STATUS_NORMAL);
		userInfo.setRoleId(Integer.parseInt(Constants.ROLE_STUDENT_ID));
		String result = userInfoService.insertUser(userInfo);
		return result;
	}
 
}
