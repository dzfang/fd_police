package com.soft.manager.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.soft.common.domain.FdCompany;
import com.soft.manager.service.FdCompanyService;
import com.soft.util.Config;
import com.soft.util.FileUtils;

@Controller
@RequestMapping("company")
public class FdCompanyController extends BaseController {
	@Resource
	private FdCompanyService companyService;

	/**
	 * 
	 * companyList(从业企业信息列表)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("companyList.do")
	public ModelAndView companyList() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		List<String> yearList = new ArrayList<String>();
		for (int i = year; i > 2000; i--) {
			yearList.add(String.valueOf(i));
		}
		ModelAndView mv = new ModelAndView("company/companyList");
		mv.addObject("yearList", yearList);
		return mv;
	}

	/**
	 * 
	 * addCompany(新增从业企业信息)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("addCompany.do")
	public String addCompany() {
		return "company/companySave";
	}

	/**
	 * 
	 * companySearch(高级查询)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("companySearch.do")
	public String companySearch() {
		return "company/companySearch";
	}

	/**
	 * 
	 * getCompanyList(查询从业企业信息列表)
	 * 
	 * @param record
	 *            FdCompany对象 @param pageIndex 当前页码 @param pageSize
	 *            每页数据笔数 @return Map
	 *            <String,Object> 返回一个Map对象，包含数据笔数及从业企业信息列表 @exception
	 */
	@RequestMapping("getCompanyList.do")
	public @ResponseBody Map<String, Object> getCompanyList(FdCompany record, int pageIndex, int pageSize) {
		return companyService.getCompanyList(record, pageIndex, pageSize);
	}

	/**
	 * 
	 * deleteCompanysById(根据从业企业信息ID数组批量删除从业企业信息)
	 * 
	 * @param idArray
	 *            从业企业信息ID数组 @return String
	 *            成功：DELETE_SUCCESS；失败：DELETE_FAILED @exception
	 */
	@RequestMapping("deleteCompanysById.do")
	public @ResponseBody String deleteCompanysById(@RequestParam(value = "idArray[]") Long[] idArray) {
		return companyService.deleteCompanysById(idArray);
	}

	/**
	 * 
	 * saveCompany(保存/更新从业企业信息)
	 * 
	 * @param record
	 *            FdCompany对象 @return String
	 *            成功：SAVE_SUCCESS；失败：SAVE_FAILED @exception
	 */
	@RequestMapping("saveCompany.do")
	public @ResponseBody String saveCompany(FdCompany record) {
		String result = "";
		// 新增用户
		if (null == record.getId()) {
			result = companyService.insertCompany(record);
		}
		// 更新用户
		else {
			result = companyService.updateCompanyById(record);
		}
		return result;
	}

	/**
	 * 
	 * getCompanyById(根据从业企业信息ID查询从业企业信息)
	 * 
	 * @param id
	 *            从业企业信息ID @return ModelAndView @exception
	 */
	@RequestMapping("getCompanyById.do")
	public ModelAndView getCompanyById(Long id) {
		FdCompany record = companyService.findCompanyById(id);
		ModelAndView modelAndView = new ModelAndView("company/companySave");
		modelAndView.addObject("record", record);
		return modelAndView;
	}
	
	/**
	 * 
	 * downloadTemplate(模板文档下载)
	 * 
	 * @param response @param id 记录ID @return @throws Exception
	 * ModelAndView @exception
	 */
	@RequestMapping("downloadTemplate.do")
	public ModelAndView downloadTemplate(HttpServletResponse response) throws Exception {
		String downLoadPath = FileUtils.toFileSeparator(Config.getUploadBasepath() + "template/从业企业信息.xls");
		response.setContentType("text/html;charset=utf-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		try {
			File file = new File(downLoadPath);
			if (!file.exists()) {
				PrintWriter out = response.getWriter();

				String script = "alert('文件不存在！'); window.location.href='companyList.do'";
				out.write("<html>" + "<head><meta http-equiv='Content-Type' content='text/html;charset=utf-8'>" + "<script type='text/javascript'>" + script + "</script>" + "</head></html>");
				return null;
			}
			long fileLength = file.length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "utf-8"));
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
	 * importInit(导入页面初始化) @return String @exception
	 */
	@RequestMapping("importInit.do")
	public String importInit() {
		return "company/companyImport";
	}

	/**
	 * 
	 * importExcel(导入Excel) @param file @param attr @return String @exception
	 */
	@RequestMapping("importExcel.do")
	public String importExcel(@RequestParam(value = "file", required = true) MultipartFile file) {
		try {
			InputStream in = file.getInputStream();
			companyService.insertCompany(in);
		} catch (IOException ex) {

		}
		return "redirect:/company/companyList.do";
	}
}
