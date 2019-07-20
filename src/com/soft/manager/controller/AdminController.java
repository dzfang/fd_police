package com.soft.manager.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.soft.common.domain.LoginRecord;
import com.soft.common.domain.MenuInfo;
import com.soft.common.domain.UserInfo;
import com.soft.manager.Constants;
import com.soft.manager.service.MenuInfoService;
import com.soft.manager.service.UserInfoService;
import com.soft.util.LoginInfoUtil;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {
	@Resource
	private MenuInfoService menuInfoService;
	@Resource
	private UserInfoService userInfoService;

	/**
	 * 登录画面初始化
	 * 
	 * @return
	 */
	@RequestMapping("login.do")
	public ModelAndView init() {
		ModelAndView modelAndView=new ModelAndView("login");
		modelAndView.addObject("webSite",LoginInfoUtil.getWebSiteConfig());
		return modelAndView;
	}

	/**
	 * 用户登录
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("doLogin.do")
	public @ResponseBody String login(HttpSession session, UserInfo userInfo) {
		// MD5加密密码
//		String password = EncryptUtil.GetMD5Code(userInfo.getPassword());
//		userInfo.setPassword(password);
		// 用戶登录校验
		UserInfo user = userInfoService.doLogin(userInfo);
		if (user != null) {
			// 判断是否为登录用户分配角色
			if (user.getRoleId() == null) {
				return Constants.UNAUTHORIZED;
			} else if (Constants.STATUS_FROZEN.equals(user.getUserStatus())) {
				return Constants.FROZEN;
			}
			// 将用户对象添加到session中
			session.setAttribute("loginInfo", user);

			// 插入登录记录表
			LoginRecord record = new LoginRecord();
			// 设置登录人ID
			record.setUserId(user.getUserId());
			// 设置登录时间
			record.setLoginDate(new Date());
			// 添加登录日志
			userInfoService.inserLoginRecord(record);
			// 返回成功
			return Constants.LOGIN_SUCCESS;
		}

		return Constants.LOGIN_FAILED;
	}

	/**
	 * 后台框架页
	 * 
	 * @return
	 */
	@RequestMapping("index.do")
	public ModelAndView index() {
		UserInfo userInfo = LoginInfoUtil.getCurrentUser();
		MenuInfo menuInfo = menuInfoService.getMenuInfo(userInfo.getRoleId());
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("menuInfo", menuInfo);
		modelAndView.addObject("webSite",LoginInfoUtil.getWebSiteConfig());
		return modelAndView;
	}

	/**
	 * 默认首页
	 * 
	 * @return
	 */
	@RequestMapping("default.do")
	public String defaultPage() {
		return "default";
	}

	/**
	 * 
	 * getMenuList(查询当前登录用户角色分配的菜单)
	 * 
	 * @return String 返回menuList.jsp页面
	 * @exception
	 */
	@RequestMapping("menuListInit.do")
	public ModelAndView menuListInit() {
		// 取得当前登录用户信息
		UserInfo userInfo = LoginInfoUtil.getCurrentUser();
		// 指定视图页
		ModelAndView modelAndView = new ModelAndView("menuInfo/menuList");
		// 将菜单数据添加到modelAndView中，返回到页面
		modelAndView.addObject("roleId", userInfo.getRoleId());
		return modelAndView;
	}

	/**
	 * getSubmenuByRoleId(查询当前登录用户所属角色分配的菜单)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("getSubmenuByRoleId.do")
	public @ResponseBody Map<String, Object> getSubmenuByRoleId(Integer roleId) {
		return menuInfoService.getSubmenuByRoleId(roleId);
	}

	/**
	 * 
	 * saveMenu(保存个人快捷菜单)
	 * 
	 * @param idArray
	 *            菜单ID
	 * @return String 返回执行结果(成功：SAVE_SUCCESS;失败：SAVE_FAILED)
	 * @exception
	 */
	@RequestMapping("insertPersonalMenu.do")
	public @ResponseBody String insertPersonalMenu(
			@RequestParam(value = "idArray[]") Integer[] idArray) {
		return menuInfoService.insertPersonalMenu(idArray);
	}

	/**
	 * 登出系统
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("userInfo");
		return "login";
	}
}
