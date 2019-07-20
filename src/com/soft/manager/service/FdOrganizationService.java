package com.soft.manager.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.FdOrganization;
import com.soft.manager.Constants;
import com.soft.manager.dao.FdOrganizationDao;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

@Service
public class FdOrganizationService {
	@Resource
	private FdOrganizationDao organizationDao;

	/**
	 * 
	 * insertOrganization(新增从业企业信息)
	 * 
	 * @param record
	 *            FdOrganization对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	public String insertOrganization(FdOrganization record) {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		record.setDeleteSign(false);
		record.setCreateUser(userId);
		record.setCreateDate(createDate);
		record.setUpdateUser(userId);
		record.setUpdateDate(createDate);
		int result = organizationDao.insertOrganization(record);

		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * deleteOrganizationsById(根据从业企业信息ID批量删除从业企业信息)
	 * 
	 * @param params
	 *            Map对象，包含一个从业企业信息ID数组
	 * @return String 成功：DELETE_SUCCESS；失败：DELETE_FAILED
	 * @exception
	 */
	public String deleteOrganizationsById(Long[] idArray) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		params.put("updateUser", LoginInfoUtil.getCurrentUser().getUserId());
		params.put("updateDate", new Date());
		int result = organizationDao.deleteOrganizationsById(params);
		return result > 0 ? Constants.DELETE_SUCCESS : Constants.DELETE_FAILED;
	}

	/**
	 * 
	 * findOrganizationById(根据从业企业信息ID查询从业企业信息)
	 * 
	 * @param id
	 *            从业企业信息ID
	 * @return FdOrganization 从业企业信息对象
	 * @exception
	 */
	public FdOrganization findOrganizationById(Long id) {
		return organizationDao.findOrganizationById(id);
	}

	/**
	 * 
	 * updateOrganizationById(根据从业企业信息ID更新从业企业信息)
	 * 
	 * @param record
	 *            从业企业信息对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	public String updateOrganizationById(FdOrganization record) {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp updateDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		record.setUpdateUser(userId);
		record.setUpdateDate(updateDate);
		int result = organizationDao.updateOrganizationById(record);

		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * getOrganizationList(根据查询条件查询从业企业信息列表)
	 * 
	 * @param record
	 *            FdOrganization对象
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 * @return List<FdOrganization> 返回一个Map对象，包含数据笔数及从业企业信息列表
	 * @exception
	 */
	public Map<String, Object> getOrganizationList(FdOrganization record,
			int pageIndex, int pageSize) {
		// 查询数据笔数
		int count = organizationDao.getOrganizationCount(record);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageIndex, pageSize, count);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("record", record);
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		// 查询当前页数据
		List<FdOrganization> rows = organizationDao.getOrganizationList(params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", rows);
		return resultMap;
	}

}
