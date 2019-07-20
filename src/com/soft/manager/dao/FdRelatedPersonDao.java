package com.soft.manager.dao;

import java.util.List;

import com.soft.common.domain.FdRelatedPerson;

public interface FdRelatedPersonDao {

	/**
	 * 
	 * insertRelatedPerson(新增案件相关人员)
	 * 
	 * @param record
	 *            案件相关人员 @return int 返回新增的数据笔数 @exception
	 */
	public int insertRelatedPerson(FdRelatedPerson record);

	/**
	 * 
	 * deleteRelatedPersonsById(根据案件相关人员ID批量删除案件相关人员)
	 * 
	 * @param record
	 *            案件Id @return int 返回数据删除的数据笔数 @exception
	 */
	public int deleteRelatedPersonByCaseId(Long caseId);

	/**
	 * 
	 * getRelatedPersonList(根据查询条件查询案件相关人员列表)
	 * 
	 * @param params
	 *            案件Id @return List<FdRelatedPerson> 返回案件相关人员列表 @exception
	 */
	public List<FdRelatedPerson> getRelatedPersonByCaseId(Long caseId);

}