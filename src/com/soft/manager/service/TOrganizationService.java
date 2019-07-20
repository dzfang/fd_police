package com.soft.manager.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.TOrganization;
import com.soft.manager.Constants;
import com.soft.manager.dao.TOrganizationDao;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

@Service
public class TOrganizationService {
	@Resource
	private TOrganizationDao organizationDao;

	/**
	 * 
	 * insertOrganization(新增部门信息)
	 * 
	 * @param record
	 *            TOrganization对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	public String insertOrganization(TOrganization record) {
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
	 * deleteOrganizationsById(根据部门信息ID批量删除部门信息)
	 * 
	 * @param params
	 *            Map对象，包含一个部门信息ID数组
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
	 * findOrganizationById(根据部门信息ID查询部门信息)
	 * 
	 * @param id
	 *            部门信息ID
	 * @return TOrganization 部门信息对象
	 * @exception
	 */
	public TOrganization findOrganizationById(Long id) {
		return organizationDao.findOrganizationById(id);
	}

	/**
	 * 
	 * updateOrganizationById(根据部门信息ID更新部门信息)
	 * 
	 * @param record
	 *            部门信息对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	public String updateOrganizationById(TOrganization record) {
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
	 * getOrganizationList(根据查询条件查询部门信息列表)
	 * 
	 * @param record
	 *            TOrganization对象
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 * @return List<TOrganization> 返回一个Map对象，包含数据笔数及部门信息列表
	 * @exception
	 */
	public Map<String, Object> getOrganizationList(TOrganization record,
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
		List<TOrganization> rows = organizationDao.getOrganizationList(params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", rows);
		return resultMap;
	}

	/**
	 * 
	 * getAllOrganization(查询所有部门信息)
	 * 
	 * @return List<TOrganization> 返回部门信息列表
	 * @exception
	 */
	public List<TOrganization> getAllOrganization() {
		return organizationDao.getAllOrganization();
	}
}
