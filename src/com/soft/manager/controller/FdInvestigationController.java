package com.soft.manager.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soft.common.domain.FdInvestigation;
import com.soft.manager.service.FdInvestigationService;
import com.soft.util.Config;
import com.soft.util.FileUtils;

@Controller
@RequestMapping("investigation")
public class FdInvestigationController extends BaseController {
	@Resource
	private FdInvestigationService investigationService;

	/**
	 * 
	 * investigationList(食品药品犯罪侦查案件列表)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("investigationList.do")
	public ModelAndView investigationList(FdInvestigation record) {
		ModelAndView modelAndView = new ModelAndView("investigation/investigationList");
		modelAndView.addObject("record", record);
		return modelAndView;
	}

	/**
	 * 
	 * addInvestigation(新增食品药品犯罪侦查案件)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("addInvestigation.do")
	public ModelAndView addInvestigation(FdInvestigation record) {
		ModelAndView modelAndView = new ModelAndView("investigation/investigationSave");
		modelAndView.addObject("record", record);
		return modelAndView;
	}

	/**
	 * 
	 * investigationSearch(高级查询)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("investigationSearch.do")
	public String investigationSearch() {
		return "investigation/investigationSearch";
	}

	/**
	 * 
	 * getInvestigationList(查询食品药品犯罪侦查案件列表)
	 * 
	 * @param record
	 *            TInvestigation对象 @param pageIndex 当前页码 @param pageSize
	 *            每页数据笔数 @return Map
	 *            <String,Object> 返回一个Map对象，包含数据笔数及食品药品犯罪侦查案件列表 @exception
	 */
	@RequestMapping("getInvestigationList.do")
	public @ResponseBody Map<String, Object> getInvestigationList(FdInvestigation record, int pageIndex, int pageSize) {
		return investigationService.getInvestigationList(record, pageIndex, pageSize);
	}

	/**
	 * 
	 * deleteInvestigationById(根据食品药品犯罪侦查案件ID删除食品药品犯罪侦查案件)
	 * 
	 * @param record
	 *            TInvestigation对象 @return String
	 *            成功：DELETE_SUCCESS；失败：DELETE_FAILED @exception
	 */
	@RequestMapping("deleteInvestigationById.do")
	public @ResponseBody String deleteInvestigationById(FdInvestigation record) {
		Long[] idArray = new Long[] { record.getId() };
		return investigationService.deleteInvestigationsById(idArray);
	}

	/**
	 * 
	 * deleteInvestigationsById(根据食品药品犯罪侦查案件ID数组批量删除食品药品犯罪侦查案件)
	 * 
	 * @param idArray
	 *            食品药品犯罪侦查案件ID数组 @return String
	 *            成功：DELETE_SUCCESS；失败：DELETE_FAILED @exception
	 */
	@RequestMapping("deleteInvestigationsById.do")
	public @ResponseBody String deleteInvestigationsById(@RequestParam(value = "idArray[]") Long[] idArray) {
		return investigationService.deleteInvestigationsById(idArray);
	}

	/**
	 * 
	 * saveInvestigation(保存/更新食品药品犯罪侦查案件)
	 * 
	 * @param record
	 *            TInvestigation对象 @return String
	 *            成功：SAVE_SUCCESS；失败：SAVE_FAILED @exception
	 */
	@RequestMapping("saveInvestigation.do")
	public @ResponseBody String saveInvestigation(FdInvestigation record) {
		String result = "";
		// 新增用户
		if (null == record.getId()) {
			result = investigationService.insertInvestigation(record);
		}
		// 更新用户
		else {
			result = investigationService.updateInvestigationById(record);
		}
		return result;
	}

	/**
	 * 
	 * getInvestigationById(根据食品药品犯罪侦查案件ID查询食品药品犯罪侦查案件)
	 * 
	 * @param id
	 *            食品药品犯罪侦查案件ID @return ModelAndView @exception
	 */
	@RequestMapping("getInvestigationById.do")
	public ModelAndView getInvestigationById(Long id) {
		FdInvestigation record = investigationService.findInvestigationById(id);
		ModelAndView modelAndView = new ModelAndView("investigation/investigationSave");
		modelAndView.addObject("record", record);
		return modelAndView;
	}

	/**
	 * 
	 * downloadTemplate(模板文档下载)
	 * 
	 * @param response
	 * @param id
	 *            记录ID @return @throws Exception ModelAndView @exception
	 */
	@RequestMapping("downloadTemplate.do")
	public ModelAndView downloadTemplate(HttpServletResponse response) throws Exception {
		String downLoadPath = FileUtils.toFileSeparator(Config.getUploadBasepath() + "template/食品药品犯罪侦查案件.xls");
		response.setContentType("text/html;charset=utf-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		try {
			File file = new File(downLoadPath);
			if (!file.exists()) {
				PrintWriter out = response.getWriter();

				String script = "alert('文件不存在！'); window.location.href='investigationList.do'";
				out.write("<html>" + "<head><meta http-equiv='Content-Type' content='text/html;charset=utf-8'>"
						+ "<script type='text/javascript'>" + script + "</script>" + "</head></html>");
				return null;
			}
			long fileLength = file.length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + URLEncoder.encode(file.getName(), "utf-8"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}

	/**
	 * 
	 * importInit(導入頁面初始化) @return String @exception
	 */
	@RequestMapping("importInit.do")
	public String importInit() {
		return "investigation/investigationImport";
	}

	/**
	 * 
	 * importExcel(導入Excel) @param file @param attr @return String @exception
	 */
	@RequestMapping("importExcel.do")
	public String importExcel(@RequestParam(value = "file", required = true) MultipartFile file,
			RedirectAttributes attr) {
		try {
			InputStream in = file.getInputStream();
			investigationService.insertInvestigation(in);
		} catch (IOException ex) {

		}
		return "redirect:/investigation/investigationList.do";
	}

	@RequestMapping("queryRelatedPerson.do")
	public @ResponseBody Map<String, Object> queryRelatedPerson(Long caseId) {
		return investigationService.queryRelatedPerson(caseId);
	}

	/**
	 * 
	 * statisticsInit(食品药品稽查行政处罚案件统计画面初始化)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("statisticsInit.do")
	public String statisticsInit() {
		return "investigation/investigationStatistics";
	}

	/**
	 * 
	 * getInvestigationCountByYear(根据立案年份统计案件数量) @param record @return
	 * Map<String, Object> @exception
	 */
	@RequestMapping("getInvestigationCountByYear.do")
	public @ResponseBody Map<String, Object> getInvestigationCountByYear(String year) {
		return investigationService.getInvestigationCountByYear(year);
	}
}
