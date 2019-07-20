package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.FdCompany;

public interface FdCompanyDao {

	/**
	 * 
	 * insertCompany(新增从业企业信息)
	 * 
	 * @param record
	 *            从业企业信息
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertCompany(FdCompany record);

	/**
	 * 
	 * deleteCompanysById(根据从业企业信息ID批量删除从业企业信息)
	 * 
	 * @param params
	 *            Map对象，包含一个从业企业信息ID数组
	 * @return int 返回数据删除的数据笔数
	 * @exception
	 */
	public int deleteCompanysById(Map<String, Object> params);

	/**
	 * 
	 * findCompanyById(根据从业企业信息ID查询从业企业信息)
	 * 
	 * @param id
	 *            从业企业信息ID
	 * @return FdCompany 从业企业信息
	 * @exception
	 */
	public FdCompany findCompanyById(Long id);

	/**
	 * 
	 * updateCompanyById(根据从业企业信息ID更新从业企业信息)
	 * 
	 * @param record
	 *            从业企业信息
	 * @return int 返回更新的数据笔数
	 * @exception
	 */
	public int updateCompanyById(FdCompany record);

	/**
	 * 
	 * getCompanyCount(根据查询条件查询从业企业信息笔数)
	 * 
	 * @param record
	 *            从业企业信息
	 * @return int 返回数据总笔数
	 * @exception
	 */
	public int getCompanyCount(FdCompany record);

	/**
	 * 
	 * getCompanyList(根据查询条件查询从业企业信息列表)
	 * 
	 * @param params
	 *            Map对象，包含FdCompany对象,当前页码以及每页数据笔数
	 * @return List<FdCompany> 返回从业企业信息列表
	 * @exception
	 */
	public List<FdCompany> getCompanyList(Map<String, Object> params);

}