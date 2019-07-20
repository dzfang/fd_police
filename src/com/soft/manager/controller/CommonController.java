package com.soft.manager.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.soft.common.domain.UserInfo;
import com.soft.manager.Constants;
import com.soft.manager.service.UserInfoService;
import com.soft.util.Config;
import com.soft.util.FileUtils;

@Controller
@RequestMapping("comm")
public class CommonController extends BaseController {
	@Resource
	private UserInfoService userInfoService;

	/**
	 *
	 * getUserList(根据查询条件获取用户列表)
	 * 
	 * @param UserInfo
	 *            UserInfo对象
	 * @param page
	 *            当前页码
	 * @param rows
	 *            每页数据笔数
	 * @return Map<String,Object> map对象，包含用户列表和数据笔数
	 * @exception
	 */
	@RequestMapping("getUserList.do")
	public @ResponseBody Map<String, Object> getUserList(UserInfo userInfo,
			int page, int rows) {
		return userInfoService.getUserList(userInfo, page, rows);
	}
	
	/**
	 *
	 * getTeacherList(根据查询条件获取教师列表)
	 * 
	 * @param UserInfo
	 *            UserInfo对象
	 * @param page
	 *            当前页码
	 * @param rows
	 *            每页数据笔数
	 * @return Map<String,Object> map对象，包含教师列表和数据笔数
	 * @exception
	 */
	@RequestMapping("getTeacherList.do")
	public @ResponseBody Map<String, Object> getTeacherList(UserInfo userInfo,
			int page, int rows) {
		userInfo.setUserType(Constants.TEACHER);
		return userInfoService.getUserList(userInfo, page, rows);
	}

  
	/**
	 * 
	 * uploadify(图片控件方法)
	 * 
	 * @param request
	 * @return String
	 * @exception
	 */
	@RequestMapping("uploadify.do")
	public @ResponseBody String uploadify(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String basePath = Config.getUploadBasepath();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM/dd");
		Date now = new Date();
		// 得到日历
		Calendar calendar = Calendar.getInstance();
		// 把当前时间赋给日历
		calendar.setTime(now);
		// 设置为前天
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		Date beforeDay = calendar.getTime();
		String beforeDirs = df.format(beforeDay);
		// 删除前天的临时文件夹
		File beforeFile = new File(basePath + "/temp/" + beforeDirs);
		if (beforeFile.exists()) {
			if (beforeFile.isDirectory()) {
				File[] files = beforeFile.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
				beforeFile.delete();
			}
		}
		// 创建当前日期的文件夹
		String dirs = "/temp/" + df.format(now);
		File file = new File(basePath + dirs);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = null;
		String path = null;
		File uploadFile = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			// 生成文件名
			fileName = FileUtils.getFileName(UUID.randomUUID().toString(),
					mf.getOriginalFilename());
			path = dirs + "/" + fileName;
			uploadFile = new File(basePath + path);
			try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}
}
