package com.soft.manager.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.TMessage;
import com.soft.common.domain.UserInfo;
import com.soft.manager.Constants;
import com.soft.manager.dao.TMessageDao;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

@Service
public class TMessageService {
	@Resource
	private TMessageDao messageDao;

	/**
	 * 
	 * insertMessage(新增站内信)
	 * 
	 * @param record
	 *            TMessage对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	public String insertMessage(TMessage record) {
		// 获取当前登录用户的ID
		UserInfo userInfo = LoginInfoUtil.getCurrentUser();
		long userId = userInfo.getUserId();
		Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		record.setOrgId(userInfo.getOrgId());
		record.setProcessSign(false);
		record.setDeleteSign(false);
		record.setReciverDelete(false);
		record.setCreateUser(userId);
		record.setCreateDate(createDate);
		record.setUpdateUser(userId);
		record.setUpdateDate(createDate);
		int result = messageDao.insertMessage(record);

		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * deleteMessagesById(根据站内信ID批量删除站内信)
	 * 
	 * @param params
	 *            Map对象，包含一个站内信ID数组
	 * @return String 成功：DELETE_SUCCESS；失败：DELETE_FAILED
	 * @exception
	 */
	public String deleteMessagesById(Long[] idArray) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		int result = messageDao.deleteMessagesById(params);
		return result > 0 ? Constants.DELETE_SUCCESS : Constants.DELETE_FAILED;
	}

	/**
	 * 
	 * findMessageById(根据站内信ID查询站内信)
	 * 
	 * @param id
	 *            站内信ID
	 * @return TMessage 站内信对象
	 * @exception
	 */
	public TMessage findMessageById(Long id) {
		return messageDao.findMessageById(id);
	}

	/**
	 * 
	 * updateMessageById(根据站内信ID更新站内信)
	 * 
	 * @param record
	 *            站内信对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	public String updateMessageById(TMessage record) {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp updateDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		record.setUpdateUser(userId);
		record.setUpdateDate(updateDate);
		int result = messageDao.updateMessageById(record);

		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * getMessageList(根据查询条件查询站内信列表)
	 * 
	 * @param record
	 *            TMessage对象
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 * @return List<TMessage> 返回一个Map对象，包含数据笔数及站内信列表
	 * @exception
	 */
	public Map<String, Object> getMessageList(TMessage record, int pageIndex,
			int pageSize) {
		// 查询数据笔数
		int count = messageDao.getMessageCount(record);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageIndex, pageSize, count);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("record", record);
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		// 查询当前页数据
		List<TMessage> rows = messageDao.getMessageList(params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", rows);
		return resultMap;
	}

	/**
	 * 
	 * updateMessagesAfterRead(处理后，批量更新消息状态)
	 * 
	 * @param params
	 *            Map对象，包含一个站内信ID数组
	 * @return String 成功：OPERATE_SUCCESS；失败：OPERATE_SUCCESS
	 * @exception
	 */
	public String updateMessagesAfterRead(Long[] idArray) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		int result = messageDao.updateMessagesAfterRead(params);
		return result > 0 ? Constants.OPERATE_SUCCESS
				: Constants.OPERATE_FAILED;
	}

	/**
	 * 
	 * updateMessagesByReciver(收件人删除收件箱中的消息)
	 * 
	 * @param params
	 *            Map对象，包含一个站内信ID数组
	 * @return String 成功：DELETE_SUCCESS；失败：DELETE_FAILED
	 * @exception
	 */
	public String updateMessagesByReciver(Long[] idArray) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		int result = messageDao.updateMessagesByReciver(params);
		return result > 0 ? Constants.DELETE_SUCCESS : Constants.DELETE_FAILED;
	}
}
