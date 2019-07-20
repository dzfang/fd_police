package com.soft.util;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.soft.common.domain.MenuInfo;
import com.soft.common.domain.RoleData;
import com.soft.common.domain.RoleMenu;
import com.soft.manager.service.MenuInfoService;
import com.soft.manager.service.RoleInfoService;

public class UserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		String url = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		String path = url.substring(url.indexOf(contextPath));

		MenuInfoService menuService = (MenuInfoService) MyApplicationListener
				.getSpringBean(MenuInfoService.class);
		List<MenuInfo> menuList = menuService.getMenuList();

		MenuInfo menu = null;
		for (MenuInfo item : menuList) {
			if (null != item.getMenuUrl()
					&& (contextPath + item.getMenuUrl()).equals(path)) {
				menu = item;
				break;
			}
		}
		if (menu != null) {
			if (modelAndView == null) {
				modelAndView = new ModelAndView();
			}
			RoleInfoService roleService = (RoleInfoService) MyApplicationListener
					.getSpringBean(RoleInfoService.class);
			List<RoleData> dataList = roleService.getRoleData(menu.getMenuId());
			modelAndView.addObject("menuId", menu.getMenuId());
			String orgIds = "";
			for (RoleData item : dataList) {
				if ("-1".equals(item.getDataId())
						&& "1".equals(menu.getIsPersonal())) {
					modelAndView.addObject("createUser", LoginInfoUtil
							.getCurrentUser().getUserId());
				} else if (!"-1".equals(item.getDataId())
						&& "1".equals(menu.getIsOrganization())) {
					if (!"".equals(orgIds)) {
						orgIds += ",";
					}
					orgIds += item.getDataId();
				}
			}

			modelAndView.addObject("orgIds", orgIds);

			String action = "";
			List<RoleMenu> roleMenuList = menuService
					.getMenuListByMenuIdAndRoleId(menu.getMenuId());
			for (RoleMenu item : roleMenuList) {
				if (!"".equals(action)) {
					action += ",";
				}
				action += item.getActionId();
			}
			modelAndView.addObject("action", action);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURL().toString();
		if (null != url && !"".equals(url)) {
			if (-1 != url.indexOf("/admin/login.do")
					|| -1 != url.indexOf("/admin/doLogin.do")
					|| -1 != url.indexOf("/admin/logout.do")
					|| -1 != url.indexOf("/user/registerInit.do")
					|| -1 != url.indexOf("/user/doRegister.do")
					|| -1 != url.indexOf("/user/registerSuccess.do")
					|| -1 != url.indexOf("/uploadify.do")
					|| -1 == url.indexOf(".do")
					|| -1 != url.indexOf("/app")) {
				return true;
			}
		}
		String requestType = request.getHeader("X-Requested-With");
		// 从session 里面获取用户信息
		Object obj = request.getSession().getAttribute("loginInfo");
		// 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
		if (null == obj || "".equals(obj.toString())) {
			if (-1 != url.indexOf("/admin/index.do")) {
				String contextPath = request.getContextPath();
				response.sendRedirect(contextPath + "/admin/login.do");
				return false;
			}
			if (Utils.isNotEmptyString(requestType)
					&& requestType.equalsIgnoreCase("XMLHttpRequest")) {
				response.setHeader("sessionstatus", "timeout");
				response.sendError(518, "session timeout.");
				return false;
			} else {
				String contextPath = request.getContextPath();
				String redirect = contextPath + "/admin/login.do";
				PrintWriter out = response.getWriter();

				String script = "";
				script += "var r = confirm('由于您长时间没有操作， session已过期， 请重新登录。');";
				script += "if(r){";
				script += "	window.open('" + redirect + "','_top')";
				script += "}";
				out.write("<html>"
						+ "<head><meta http-equiv='Content-Type' content='text/html;charset=utf-8'>"
						+ "<script type='text/javascript'>" + script
						+ "</script>" + "</head></html>");
				return false;
			}
		}
		return true;
	}
}
