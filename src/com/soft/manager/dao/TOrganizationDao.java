package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.TOrganization;

public interface TOrganizationDao {

	/**
	 * 
	 * insertOrganization(新增部门信息)
	 * 
	 * @param record
	 *            部门信息
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertOrganization(TOrganization record);

	/**
	 * 
	 * deleteOrganizationsById(根据部门信息ID批量删除部门信息)
	 * 
	 * @param params
	 *            Map对象，包含一个部门信息ID数组
	 * @return int 返回数据删除的数据笔数
	 * @exception
	 */
	public int deleteOrganizationsById(Map<String, Object> params);

	/**
	 * 
	 * findOrganizationById(根据部门信息ID查询部门信息)
	 * 
	 * @param id
	 *            部门信息ID
	 * @return TOrganization 部门信息
	 * @exception
	 */
	public TOrganization findOrganizationById(Long id);

	/**
	 * 
	 * updateOrganizationById(根据部门信息ID更新部门信息)
	 * 
	 * @param record
	 *            部门信息
	 * @return int 返回更新的数据笔数
	 * @exception
	 */
	public int updateOrganizationById(TOrganization record);

	/**
	 * 
	 * getOrganizationCount(根据查询条件查询部门信息笔数)
	 * 
	 * @param record
	 *            部门信息
	 * @return int 返回数据总笔数
	 * @exception
	 */
	public int getOrganizationCount(TOrganization record);

	/**
	 * 
	 * getOrganizationList(根据查询条件查询部门信息列表)
	 * 
	 * @param params
	 *            Map对象，包含TOrganization对象,当前页码以及每页数据笔数
	 * @return List<TOrganization> 返回部门信息列表
	 * @exception
	 */
	public List<TOrganization> getOrganizationList(Map<String, Object> params);

	/**
	 * 
	 * getAllOrganization(查询所有部门信息)
	 * 
	 * @return List<TOrganization> 返回部门信息列表
	 * @exception
	 */
	public List<TOrganization> getAllOrganization();
}