package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.TMessage;

public interface TMessageDao {

	/**
	 * 
	 * insertMessage(新增站内信)
	 * 
	 * @param record
	 *            站内信
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertMessage(TMessage record);

	/**
	 * 
	 * deleteMessagesById(根据站内信ID批量删除站内信)
	 * 
	 * @param params
	 *            Map对象，包含一个站内信ID数组
	 * @return int 返回数据删除的数据笔数
	 * @exception
	 */
	public int deleteMessagesById(Map<String, Object> params);

	/**
	 * 
	 * findMessageById(根据站内信ID查询站内信)
	 * 
	 * @param id
	 *            站内信ID
	 * @return TMessage 站内信
	 * @exception
	 */
	public TMessage findMessageById(Long id);

	/**
	 * 
	 * updateMessageById(根据站内信ID更新站内信)
	 * 
	 * @param record
	 *            站内信
	 * @return int 返回更新的数据笔数
	 * @exception
	 */
	public int updateMessageById(TMessage record);

	/**
	 * 
	 * getMessageCount(根据查询条件查询站内信笔数)
	 * 
	 * @param record
	 *            站内信
	 * @return int 返回数据总笔数
	 * @exception
	 */
	public int getMessageCount(TMessage record);

	/**
	 * 
	 * getMessageList(根据查询条件查询站内信列表)
	 * 
	 * @param params
	 *            Map对象，包含TMessage对象,当前页码以及每页数据笔数
	 * @return List<TMessage> 返回站内信列表
	 * @exception
	 */
	public List<TMessage> getMessageList(Map<String, Object> params);

	/**
	 * 
	 * updateMessagesAfterRead(处理后，批量更新消息状态)
	 * 
	 * @param params
	 *            Map对象，包含一个站内信ID数组
	 * @return int 返回数据删除的数据笔数
	 * @exception
	 */
	public int updateMessagesAfterRead(Map<String, Object> params);
	
	/**
	 * 
	 * updateMessagesByReciver(收件人删除收件箱中的消息)
	 * 
	 * @param params
	 *            Map对象，包含一个站内信ID数组
	 * @return int 返回数据删除的数据笔数
	 * @exception
	 */
	public int updateMessagesByReciver(Map<String, Object> params);
}