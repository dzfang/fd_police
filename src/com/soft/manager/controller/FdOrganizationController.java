package com.soft.manager.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.soft.common.domain.FdOrganization;
import com.soft.manager.service.FdOrganizationService;

@Controller
@RequestMapping("org")
public class FdOrganizationController extends BaseController {
	@Resource
	private FdOrganizationService organizationService;
	/**
	 * 
	 * orgList(从业企业信息列表)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("orgList.do")
	public String orgList() {
		return "org/orgList";
	}

	/**
	 * 
	 * addOrg(新增从业企业信息)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("addOrg.do")
	public String addOrg() {
		return "org/orgSave";
	}

	/**
	 * 
	 * orgSearch(高级查询)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("orgSearch.do")
	public String orgSearch() {
		return "org/orgSearch";
	}

	/**
	 * 
	 * getOrganizationList(查询从业企业信息列表)
	 * 
	 * @param record
	 *            FdOrganization对象
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 * @return Map<String,Object> 返回一个Map对象，包含数据笔数及从业企业信息列表
	 * @exception
	 */
	@RequestMapping("getOrganizationList.do")
	public @ResponseBody Map<String, Object> getOrganizationList(
			FdOrganization record, int pageIndex, int pageSize) {
		return organizationService.getOrganizationList(record, pageIndex,
				pageSize);
	}
 
	/**
	 * 
	 * deleteOrganizationsById(根据从业企业信息ID数组批量删除从业企业信息)
	 * 
	 * @param idArray
	 *            从业企业信息ID数组
	 * @return String 成功：DELETE_SUCCESS；失败：DELETE_FAILED
	 * @exception
	 */
	@RequestMapping("deleteOrganizationsById.do")
	public @ResponseBody String deleteOrganizationsById(
			@RequestParam(value = "idArray[]") Long[] idArray) {
		return organizationService.deleteOrganizationsById(idArray);
	}

	/**
	 * 
	 * saveOrganization(保存/更新从业企业信息)
	 * 
	 * @param record
	 *            FdOrganization对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	@RequestMapping("saveOrganization.do")
	public @ResponseBody String saveOrganization(FdOrganization record) {
		String result = "";
		// 新增用户
		if (null == record.getId()) {
			result = organizationService.insertOrganization(record);
		}
		// 更新用户
		else {
			result = organizationService.updateOrganizationById(record);
		}
		return result;
	}

	/**
	 * 
	 * getOrganizationById(根据从业企业信息ID查询从业企业信息)
	 * 
	 * @param id
	 *            从业企业信息ID
	 * @return ModelAndView
	 * @exception
	 */
	@RequestMapping("getOrganizationById.do")
	public ModelAndView getOrganizationById(Long id) {
		FdOrganization record = organizationService.findOrganizationById(id);
		ModelAndView modelAndView = new ModelAndView("org/orgSave");
		modelAndView.addObject("record", record);
		return modelAndView;
	}
}
