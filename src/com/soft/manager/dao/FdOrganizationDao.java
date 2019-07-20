package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.FdOrganization;

public interface FdOrganizationDao {

	/**
	 * 
	 * insertOrganization(新增从业企业信息)
	 * 
	 * @param record
	 *            从业企业信息
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertOrganization(FdOrganization record);

	/**
	 * 
	 * deleteOrganizationsById(根据从业企业信息ID批量删除从业企业信息)
	 * 
	 * @param params
	 *            Map对象，包含一个从业企业信息ID数组
	 * @return int 返回数据删除的数据笔数
	 * @exception
	 */
	public int deleteOrganizationsById(Map<String, Object> params);

	/**
	 * 
	 * findOrganizationById(根据从业企业信息ID查询从业企业信息)
	 * 
	 * @param id
	 *            从业企业信息ID
	 * @return FdOrganization 从业企业信息
	 * @exception
	 */
	public FdOrganization findOrganizationById(Long id);

	/**
	 * 
	 * updateOrganizationById(根据从业企业信息ID更新从业企业信息)
	 * 
	 * @param record
	 *            从业企业信息
	 * @return int 返回更新的数据笔数
	 * @exception
	 */
	public int updateOrganizationById(FdOrganization record);

	/**
	 * 
	 * getOrganizationCount(根据查询条件查询从业企业信息笔数)
	 * 
	 * @param record
	 *            从业企业信息
	 * @return int 返回数据总笔数
	 * @exception
	 */
	public int getOrganizationCount(FdOrganization record);

	/**
	 * 
	 * getOrganizationList(根据查询条件查询从业企业信息列表)
	 * 
	 * @param params
	 *            Map对象，包含FdOrganization对象,当前页码以及每页数据笔数
	 * @return List<FdOrganization> 返回从业企业信息列表
	 * @exception
	 */
	public List<FdOrganization> getOrganizationList(Map<String, Object> params);

}