package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.FdEmployee;

public interface FdEmployeeDao {

	/**
	 * 
	 * insertEmployee(新增从业人员信息)
	 * 
	 * @param record
	 *            从业人员信息
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertEmployee(FdEmployee record);

	/**
	 * 
	 * deleteEmployeesById(根据从业人员信息ID批量删除从业人员信息)
	 * 
	 * @param params
	 *            Map对象，包含一个从业人员信息ID数组
	 * @return int 返回数据删除的数据笔数
	 * @exception
	 */
	public int deleteEmployeesById(Map<String, Object> params);

	/**
	 * 
	 * findEmployeeById(根据从业人员信息ID查询从业人员信息)
	 * 
	 * @param id
	 *            从业人员信息ID
	 * @return FdEmployee 从业人员信息
	 * @exception
	 */
	public FdEmployee findEmployeeById(Long id);

	/**
	 * 
	 * updateEmployeeById(根据从业人员信息ID更新从业人员信息)
	 * 
	 * @param record
	 *            从业人员信息
	 * @return int 返回更新的数据笔数
	 * @exception
	 */
	public int updateEmployeeById(FdEmployee record);

	/**
	 * 
	 * getEmployeeCount(根据查询条件查询从业人员信息笔数)
	 * 
	 * @param record
	 *            从业人员信息
	 * @return int 返回数据总笔数
	 * @exception
	 */
	public int getEmployeeCount(FdEmployee record);

	/**
	 * 
	 * getEmployeeList(根据查询条件查询从业人员信息列表)
	 * 
	 * @param params
	 *            Map对象，包含FdEmployee对象,当前页码以及每页数据笔数
	 * @return List<FdEmployee> 返回从业人员信息列表
	 * @exception
	 */
	public List<FdEmployee> getEmployeeList(Map<String, Object> params);

}