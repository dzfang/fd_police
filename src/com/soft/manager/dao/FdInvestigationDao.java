package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.FdInvestigation;

public interface FdInvestigationDao {

	/**
	 * 
	 * insertInvestigation(新增食品药品犯罪侦查案件)
	 * 
	 * @param record
	 *            食品药品犯罪侦查案件
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertInvestigation(FdInvestigation record);

	/**
	 * 
	 * deleteInvestigationsById(根据食品药品犯罪侦查案件ID批量删除食品药品犯罪侦查案件)
	 * 
	 * @param params
	 *            Map对象，包含一个食品药品犯罪侦查案件ID数组
	 * @return int 返回数据删除的数据笔数
	 * @exception
	 */
	public int deleteInvestigationsById(Map<String, Object> params);

	/**
	 * 
	 * findInvestigationById(根据食品药品犯罪侦查案件ID查询食品药品犯罪侦查案件)
	 * 
	 * @param id
	 *            食品药品犯罪侦查案件ID
	 * @return FdInvestigation 食品药品犯罪侦查案件
	 * @exception
	 */
	public FdInvestigation findInvestigationById(Long id);

	/**
	 * 
	 * updateInvestigationById(根据食品药品犯罪侦查案件ID更新食品药品犯罪侦查案件)
	 * 
	 * @param record
	 *            食品药品犯罪侦查案件
	 * @return int 返回更新的数据笔数
	 * @exception
	 */
	public int updateInvestigationById(FdInvestigation record);

	/**
	 * 
	 * getInvestigationCount(根据查询条件查询食品药品犯罪侦查案件笔数)
	 * 
	 * @param record
	 *            食品药品犯罪侦查案件
	 * @return int 返回数据总笔数
	 * @exception
	 */
	public int getInvestigationCount(FdInvestigation record);

	/**
	 * 
	 * getInvestigationList(根据查询条件查询食品药品犯罪侦查案件列表)
	 * 
	 * @param params
	 *            Map对象，包含FdInvestigation对象,当前页码以及每页数据笔数
	 * @return List<FdInvestigation> 返回食品药品犯罪侦查案件列表
	 * @exception
	 */
	public List<FdInvestigation> getInvestigationList(Map<String, Object> params);
	
	/**
	 * 
	 * getInspectionCountByYear(根据立案年份统计案件数量) @param year @return
	 * List<FdInvestigation> @exception
	 */
	public List<FdInvestigation> getInvestigationCountByYear(String year);
}