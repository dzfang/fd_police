package com.soft.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.soft.common.domain.TWebSite;
import com.soft.common.domain.UserInfo;
import com.soft.manager.service.TWebSiteService;

public class LoginInfoUtil {
	/**
	 * 获取当前登录用户信息
	 * @return
	 */
	public static UserInfo getCurrentUser() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("loginInfo");
		UserInfo userInfo = (UserInfo) obj;
		return userInfo;
	}
	
	public static TWebSite getWebSiteConfig(){
		TWebSiteService webSiteService = (TWebSiteService)MyApplicationListener.getSpringBean(TWebSiteService.class);
		return webSiteService.getWebSiteConfig();
	}
}
