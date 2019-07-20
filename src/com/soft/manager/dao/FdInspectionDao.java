package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.FdInspection;

public interface FdInspectionDao {

	/**
	 * 
	 * insertInspection(新增食品药品稽查行政处罚案件)
	 * 
	 * @param record 食品药品稽查行政处罚案件 @return int 返回新增的数据笔数 @exception
	 */
	public int insertInspection(FdInspection record);

	/**
	 * 
	 * deleteInspectionsById(根据食品药品稽查行政处罚案件ID批量删除食品药品稽查行政处罚案件)
	 * 
	 * @param params Map对象，包含一个食品药品稽查行政处罚案件ID数组 @return int
	 * 返回数据删除的数据笔数 @exception
	 */
	public int deleteInspectionsById(Map<String, Object> params);

	/**
	 * 
	 * findInspectionById(根据食品药品稽查行政处罚案件ID查询食品药品稽查行政处罚案件)
	 * 
	 * @param id 食品药品稽查行政处罚案件ID @return FdInspection 食品药品稽查行政处罚案件 @exception
	 */
	public FdInspection findInspectionById(Long id);

	/**
	 * 
	 * updateInspectionById(根据食品药品稽查行政处罚案件ID更新食品药品稽查行政处罚案件)
	 * 
	 * @param record 食品药品稽查行政处罚案件 @return int 返回更新的数据笔数 @exception
	 */
	public int updateInspectionById(FdInspection record);

	/**
	 * 
	 * getInspectionCount(根据查询条件查询食品药品稽查行政处罚案件笔数)
	 * 
	 * @param record 食品药品稽查行政处罚案件 @return int 返回数据总笔数 @exception
	 */
	public int getInspectionCount(FdInspection record);

	/**
	 * 
	 * getInspectionList(根据查询条件查询食品药品稽查行政处罚案件列表)
	 * 
	 * @param params Map对象，包含FdInspection对象,当前页码以及每页数据笔数 @return
	 * List<FdInspection> 返回食品药品稽查行政处罚案件列表 @exception
	 */
	public List<FdInspection> getInspectionList(Map<String, Object> params);

	/**
	 * 
	 * getInspectionByName(根据食品药品稽查行政处罚案件名称查询食品药品稽查行政处罚案件)
	 * 
	 * @param record 食品药品稽查行政处罚案件对象 @return FdInspection
	 * 食品药品稽查行政处罚案件对象 @exception
	 */
	public FdInspection getInspectionByName(FdInspection record);

	/**
	 * 
	 * getInspectionCountByYear(根据立案年份统计案件数量) @param year @return
	 * List<FdInspection> @exception
	 */
	public List<FdInspection> getInspectionCountByYear(String year);
}