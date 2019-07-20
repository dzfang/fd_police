package com.soft.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.soft.common.domain.MenuInfo;
import com.soft.common.domain.UserInfo;
import com.soft.manager.service.MenuInfoService;
import com.soft.util.LoginInfoUtil;

@Controller
public class LoginController extends BaseController {
	@Resource
	private MenuInfoService menuInfoService;
	
	/**
	 * 登录画面初始化
	 * 
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView init() {
		ModelAndView modelAndView=new ModelAndView("login");
		modelAndView.addObject("webSite",LoginInfoUtil.getWebSiteConfig());
		return modelAndView;
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
