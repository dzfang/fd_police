 
package com.soft.manager.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft.common.domain.LoginRecord;
import com.soft.manager.service.LoginRecordService;
 
@Controller
@RequestMapping("loginRecord")
public class LoginRecordController extends BaseController {

	/**
	 * 后台用户登录记录的业务逻辑
	 */
	@Resource
	private LoginRecordService loginRecordService;

	@RequestMapping("recordList.do")
	public String recordList() {
		return "loginRecord/recordList";
	}

	/**
	 * 
	 * getLoginRecordList(根据条件查询后台用户登录记录列表)
	 * 
	 * @param loginRecordInfo
	 *            登录日志对像
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 * @return Map<String,Object> 返回数据总笔数及当前页的数据列表
	 * @exception
	 */
	@RequestMapping("getLoginRecordList.do")
	public @ResponseBody Map<String, Object> getLoginRecordList(
			LoginRecord loginRecordInfo, int pageIndex, int pageSize) {
		return loginRecordService.getLoginRecordList(loginRecordInfo,
				pageIndex, pageSize);
	}
}
