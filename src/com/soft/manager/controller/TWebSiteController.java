package com.soft.manager.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.soft.common.domain.TWebSite;
import com.soft.manager.service.TWebSiteService;

@Controller
@RequestMapping("webSite")
public class TWebSiteController extends BaseController {

	@Resource
	private TWebSiteService webSiteService;

	/**
	 * Logo画面初始化
	 * 
	 * @return
	 */
	@RequestMapping("saveInit.do")
	public ModelAndView init() {
		TWebSite record = webSiteService.getWebSiteConfig();
		ModelAndView modelAndView = new ModelAndView("webSite/webSiteSave");
		modelAndView.addObject("record", record);
		return modelAndView;
	}

	/**
	 * 
	 * saveWebSite(保存/更新网站信息)
	 * 
	 * @param record
	 *            TWebSite对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	@RequestMapping("saveWebSite.do")
	public @ResponseBody String saveWebSite(TWebSite record) {
		String result = webSiteService.insertWebSite(record);
		return result;
	}
}
