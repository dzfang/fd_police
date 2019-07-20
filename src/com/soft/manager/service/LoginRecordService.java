
package com.soft.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.LoginRecord;
import com.soft.manager.dao.LoginRecordDao;
import com.soft.util.Utils;

@Service
public class LoginRecordService {

	/**
	 * 后台用户登录记录的数据持久化接口
	 */
	@Resource
	private LoginRecordDao loginRecordDao;

	/**
	 * 
	 * getLoginRecordList(根据条件查询后台用户登录记录列表)
	 * 
	 * @param record
	 *            用户登录记录对象
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 * @return Map<String,Object> 包含数据总笔数及当前页用户登录记录列表
	 * @exception
	 */
	public Map<String, Object> getLoginRecordList(
			LoginRecord record, int pageIndex, int pageSize) {
		// 查询数据笔数
		int count = loginRecordDao.getLoginRecordCount(record);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageIndex, pageSize, count);
		// 参数列表
		Map<String, Object> params = new HashMap<String, Object>();
		// 后台用户对象
		params.put("record", record);
		// 当前页起始下标
		params.put("startIndex", startIndex);
		// 每页数据笔数
		params.put("pageSize", pageSize);
		// 查询后台用户登录记录列表
		List<LoginRecord> recordList = loginRecordDao
				.getLoginRecordList(params);
		// 结果集
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 数据笔数
		resultMap.put("total", count);
		// APP用户信息
		resultMap.put("rows", recordList);
		return resultMap;
	}
}
