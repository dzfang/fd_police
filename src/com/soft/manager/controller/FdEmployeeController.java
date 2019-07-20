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

import com.soft.common.domain.FdEmployee;
import com.soft.manager.service.FdEmployeeService;
import com.soft.util.Config;
import com.soft.util.FileUtils;

@Controller
@RequestMapping("employee")
public class FdEmployeeController extends BaseController {
	@Resource
	private FdEmployeeService employeeService;
	/**
	 * 
	 * employeeList(从业人员信息列表)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("employeeList.do")
	public String employeeList() {
		return "employee/employeeList";
	}

	/**
	 * 
	 * addEmployee(新增从业人员信息)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("addEmployee.do")
	public String addEmployee() {
		return "employee/employeeSave";
	}

	/**
	 * 
	 * addEmployee(新增从业人员信息)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("employeeSearch.do")
	public String employeeSearch() {
		return "employee/employeeSearch";
	}
	/**
	 * 
	 * getEmployeeList(查询从业人员信息列表)
	 * 
	 * @param record
	 *            FdEmployee对象
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 * @return Map<String,Object> 返回一个Map对象，包含数据笔数及从业人员信息列表
	 * @exception
	 */
	@RequestMapping("getEmployeeList.do")
	public @ResponseBody Map<String, Object> getEmployeeList(
			FdEmployee record, int pageIndex, int pageSize) {
		return employeeService.getEmployeeList(record, pageIndex,
				pageSize);
	}
 
	/**
	 * 
	 * deleteEmployeesById(根据从业人员信息ID数组批量删除从业人员信息)
	 * 
	 * @param idArray
	 *            从业人员信息ID数组
	 * @return String 成功：DELETE_SUCCESS；失败：DELETE_FAILED
	 * @exception
	 */
	@RequestMapping("deleteEmployeesById.do")
	public @ResponseBody String deleteEmployeesById(
			@RequestParam(value = "idArray[]") Long[] idArray) {
		return employeeService.deleteEmployeesById(idArray);
	}

	/**
	 * 
	 * saveEmployee(保存/更新从业人员信息)
	 * 
	 * @param record
	 *            FdEmployee对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	@RequestMapping("saveEmployee.do")
	public @ResponseBody String saveEmployee(FdEmployee record) {
		String result = "";
		// 新增用户
		if (null == record.getId()) {
			result = employeeService.insertEmployee(record);
		}
		// 更新用户
		else {
			result = employeeService.updateEmployeeById(record);
		}
		return result;
	}

	/**
	 * 
	 * getEmployeeById(根据从业人员信息ID查询从业人员信息)
	 * 
	 * @param id
	 *            从业人员信息ID
	 * @return ModelAndView
	 * @exception
	 */
	@RequestMapping("getEmployeeById.do")
	public ModelAndView getEmployeeById(Long id) {
		FdEmployee record = employeeService.findEmployeeById(id);
		ModelAndView modelAndView = new ModelAndView("employee/employeeSave");
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
		String downLoadPath = FileUtils.toFileSeparator(Config.getUploadBasepath() + "template/从业人员信息.xls");
		response.setContentType("text/html;charset=utf-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		try {
			File file = new File(downLoadPath);
			if (!file.exists()) {
				PrintWriter out = response.getWriter();

				String script = "alert('文件不存在！'); window.location.href='employeeList.do'";
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
	 * importInit(導入頁面初始化)  
	 * @return  
	 * String 
	 * @exception
	 */
	@RequestMapping("importInit.do")
	public String importInit(){
		return "employee/employeeImport";
	}
	
	/**
	 * 
	 * importExcel(導入Excel)  
	 * @param file
	 * @param attr
	 * @return  
	 * String 
	 * @exception
	 */
	@RequestMapping("importExcel.do")
	public String importExcel(@RequestParam(value = "file", required = true) MultipartFile file, RedirectAttributes attr) {
		try {
			InputStream in = file.getInputStream();
			employeeService.insertEmployee(in);
		} catch (IOException ex) {

		}
		return "redirect:/employee/employeeList.do";
	}
}
