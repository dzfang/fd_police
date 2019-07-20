package com.soft.manager.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.FdDocument;
import com.soft.manager.Constants;
import com.soft.manager.dao.FdDocumentDao;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

@Service
public class FdDocumentService {
	@Resource
	private FdDocumentDao documentDao;

	/**
	 * 
	 * insertDocument(新增文档信息)
	 * 
	 * @param record FdDocument对象 @return String
	 * 成功：SAVE_SUCCESS；失败：SAVE_FAILED @exception
	 */
	public int insertDocument(FdDocument record) {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		record.setCreateUser(userId);
		record.setCreateDate(createDate);
		record.setUpdateUser(userId);
		record.setUpdateDate(createDate);
		int result = documentDao.insertDocument(record);

		return result;
	}

	/**
	 * 
	 * deleteDocumentsById(根据文档信息ID批量删除文档信息)
	 * 
	 * @param params Map对象，包含一个文档信息ID数组 @return String
	 * 成功：DELETE_SUCCESS；失败：DELETE_FAILED @exception
	 */
	public String deleteDocumentsById(Long[] idArray) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		params.put("updateUser", LoginInfoUtil.getCurrentUser().getUserId());
		params.put("updateDate", new Date());
		int result = documentDao.deleteDocumentsById(params);
		return result > 0 ? Constants.DELETE_SUCCESS : Constants.DELETE_FAILED;
	}

	/**
	 * 
	 * findDocumentById(根据文档信息ID查询文档信息)
	 * 
	 * @param id 文档信息ID @return FdDocument 文档信息对象 @exception
	 */
	public FdDocument findDocumentById(Long id) {
		return documentDao.findDocumentById(id);
	}

	/**
	 * 
	 * getDocumentList(根据查询条件查询文档信息列表)
	 * 
	 * @param record FdDocument对象 @param pageIndex 当前页码 @param pageSize
	 * 每页数据笔数 @return List<FdDocument> 返回一个Map对象，包含数据笔数及文档信息列表 @exception
	 */
	public Map<String, Object> getDocumentList(FdDocument record, int pageIndex, int pageSize) {
		// 查询数据笔数
		int count = documentDao.getDocumentCount(record);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageIndex, pageSize, count);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("record", record);
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		// 查询当前页数据
		List<FdDocument> rows = documentDao.getDocumentList(params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", rows);
		return resultMap;
	}

}
