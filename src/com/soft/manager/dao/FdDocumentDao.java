package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.FdDocument;

public interface FdDocumentDao {

	/**
	 * 
	 * insertDocument(新增文档信息)
	 * 
	 * @param record
	 *            文档信息
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertDocument(FdDocument record);

	/**
	 * 
	 * deleteDocumentsById(根据文档信息ID批量删除文档信息)
	 * 
	 * @param params
	 *            Map对象，包含一个文档信息ID数组
	 * @return int 返回数据删除的数据笔数
	 * @exception
	 */
	public int deleteDocumentsById(Map<String, Object> params);

	/**
	 * 
	 * findDocumentById(根据文档信息ID查询文档信息)
	 * 
	 * @param id
	 *            文档信息ID
	 * @return FdDocument 文档信息
	 * @exception
	 */
	public FdDocument findDocumentById(Long id);

	/**
	 * 
	 * getDocumentCount(根据查询条件查询文档信息笔数)
	 * 
	 * @param record
	 *            文档信息
	 * @return int 返回数据总笔数
	 * @exception
	 */
	public int getDocumentCount(FdDocument record);

	/**
	 * 
	 * getDocumentList(根据查询条件查询文档信息列表)
	 * 
	 * @param params
	 *            Map对象，包含FdDocument对象,当前页码以及每页数据笔数
	 * @return List<FdDocument> 返回文档信息列表
	 * @exception
	 */
	public List<FdDocument> getDocumentList(Map<String, Object> params);
 
}