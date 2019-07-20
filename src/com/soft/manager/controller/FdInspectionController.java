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

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soft.common.domain.FdInspection;
import com.soft.manager.service.FdInspectionService;
import com.soft.util.Config;
import com.soft.util.FileUtils;

@Controller
@RequestMapping("inspection")
public class FdInspectionController extends BaseController {
	@Resource
	private FdInspectionService inspectionService;

	/**
	 * 
	 * inspectionList(食品药品稽查行政处罚案件列表)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("inspectionList.do")
	public ModelAndView inspectionList(FdInspection record) {
		ModelAndView modelAndView = new ModelAndView("inspection/inspectionList");
		modelAndView.addObject("record", record);
		return modelAndView;
	}

	/**
	 * 
	 * addInspection(新增食品药品稽查行政处罚案件)
	 * 
	 * @return String @exception
	 * @throws Exception
	 * @throws SolrServerException
	 */
	@RequestMapping("addInspection.do")
	public ModelAndView addInspection(FdInspection record) {
		ModelAndView modelAndView = new ModelAndView("inspection/inspectionSave");
		modelAndView.addObject("record", record);
		return modelAndView;
	}

	/**
	 * 
	 * inspectionSearch(高級查詢)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("inspectionSearch.do")
	public String inspectionSearch() {
		return "inspection/inspectionSearch";
	}

	/**
	 * 
	 * getInspectionList(查询食品药品稽查行政处罚案件列表)
	 * 
	 * @param record
	 *            TInspection对象 @param pageIndex 当前页码 @param pageSize
	 *            每页数据笔数 @return Map
	 *            <String,Object> 返回一个Map对象，包含数据笔数及食品药品稽查行政处罚案件列表 @exception
	 */
	@RequestMapping("getInspectionList.do")
	public @ResponseBody Map<String, Object> getInspectionList(FdInspection record, int pageIndex, int pageSize) {
		return inspectionService.getInspectionList(record, pageIndex, pageSize);
	}

	/**
	 * 
	 * deleteInspectionById(根据食品药品稽查行政处罚案件ID删除食品药品稽查行政处罚案件)
	 * 
	 * @param record
	 *            TInspection对象 @return String
	 *            成功：DELETE_SUCCESS；失败：DELETE_FAILED @exception
	 */
	@RequestMapping("deleteInspectionById.do")
	public @ResponseBody String deleteInspectionById(FdInspection record) {
		Long[] idArray = new Long[] { record.getId() };
		return inspectionService.deleteInspectionsById(idArray);
	}

	/**
	 * 
	 * deleteInspectionsById(根据食品药品稽查行政处罚案件ID数组批量删除食品药品稽查行政处罚案件)
	 * 
	 * @param idArray
	 *            食品药品稽查行政处罚案件ID数组 @return String
	 *            成功：DELETE_SUCCESS；失败：DELETE_FAILED @exception
	 */
	@RequestMapping("deleteInspectionsById.do")
	public @ResponseBody String deleteInspectionsById(@RequestParam(value = "idArray[]") Long[] idArray) {
		return inspectionService.deleteInspectionsById(idArray);
	}

	/**
	 * 
	 * saveInspection(保存/更新食品药品稽查行政处罚案件)
	 * 
	 * @param record
	 *            TInspection对象 @return String
	 *            成功：SAVE_SUCCESS；失败：SAVE_FAILED @exception
	 */
	@RequestMapping("saveInspection.do")
	public @ResponseBody String saveInspection(FdInspection record) {
		String result = "";
		// 新增用户
		if (null == record.getId()) {
			result = inspectionService.insertInspection(record);
		}
		// 更新用户
		else {
			result = inspectionService.updateInspectionById(record);
		}
		return result;
	}

	/**
	 * 
	 * getInspectionById(根据食品药品稽查行政处罚案件ID查询食品药品稽查行政处罚案件)
	 * 
	 * @param id
	 *            食品药品稽查行政处罚案件ID @return ModelAndView @exception
	 */
	@RequestMapping("getInspectionById.do")
	public ModelAndView getInspectionById(Long id) {
		FdInspection record = inspectionService.findInspectionById(id);
		ModelAndView modelAndView = new ModelAndView("inspection/inspectionSave");
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
		String downLoadPath = FileUtils.toFileSeparator(Config.getUploadBasepath() + "template/食品药品稽查行政处罚案件.xls");
		response.setContentType("text/html;charset=utf-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		try {
			File file = new File(downLoadPath);
			if (!file.exists()) {
				PrintWriter out = response.getWriter();

				String script = "alert('文件不存在！'); window.location.href='inspectionList.do'";
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
		return "inspection/inspectionImport";
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
			inspectionService.insertInspection(in);
		} catch (IOException ex) {

		}
		return "redirect:/inspection/inspectionList.do";
	}

	/**
	 * 
	 * statisticsInit(食品药品稽查行政处罚案件统计画面初始化)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("statisticsInit.do")
	public String statisticsInit() {
		return "inspection/inspectionStatistics";
	}

	/**
	 * 
	 * getInspectionCountByYear(根据立案年份统计案件数量) @param record @return Map<String,
	 * Object> @exception
	 */
	@RequestMapping("getInspectionCountByYear.do")
	public @ResponseBody Map<String, Object> getInspectionCountByYear(String year) {
		return inspectionService.getInspectionCountByYear(year);
	}
}
