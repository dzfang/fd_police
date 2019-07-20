package com.soft.manager.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.soft.common.domain.TMessage;
import com.soft.manager.service.TMessageService;
import com.soft.util.LoginInfoUtil;

@Controller
@RequestMapping("messages")
public class TMessageController extends BaseController {
	@Resource
	private TMessageService messageService;

	/**
	 * 
	 * messageList(站内信列表)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("messageList.do")
	public ModelAndView messageList() {
		ModelAndView modelAndView = new ModelAndView("messages/messageList");
		modelAndView.addObject("userId", LoginInfoUtil.getCurrentUser()
				.getUserId());
		return modelAndView;
	}

	/**
	 * 
	 * addMessage(新增站内信)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("addMessage.do")
	public String addMessage() {
		return "messages/messageSave";
	}

	/**
	 * 
	 * messageReciveList(收件箱)
	 * 
	 * @return String
	 * @exception
	 */
	@RequestMapping("messageReciveList.do")
	public String messageReciveList() {
		return "messages/messageRecive";
	}

	/**
	 * 
	 * getMessageList(查询站内信列表)
	 * 
	 * @param record
	 *            TMessage对象
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 * @return Map<String,Object> 返回一个Map对象，包含数据笔数及站内信列表
	 * @exception
	 */
	@RequestMapping("getMessageList.do")
	public @ResponseBody Map<String, Object> getMessageList(TMessage record,
			int pageIndex, int pageSize) {
		return messageService.getMessageList(record, pageIndex, pageSize);
	}

	/**
	 * 
	 * deleteMessagesById(根据站内信ID数组批量删除站内信)
	 * 
	 * @param idArray
	 *            站内信ID数组
	 * @return String 成功：DELETE_SUCCESS；失败：DELETE_FAILED
	 * @exception
	 */
	@RequestMapping("deleteMessagesById.do")
	public @ResponseBody String deleteMessagesById(
			@RequestParam(value = "idArray[]") Long[] idArray) {
		return messageService.deleteMessagesById(idArray);
	}

	/**
	 * 
	 * saveMessage(保存/更新站内信)
	 * 
	 * @param record
	 *            TMessage对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	@RequestMapping("saveMessage.do")
	public @ResponseBody String saveMessage(TMessage record) {
		String result = "";
		// 新增站内信
		if (null == record.getId()) {
			result = messageService.insertMessage(record);
		}
		// 更新站内信
		else {
			result = messageService.updateMessageById(record);
		}
		return result;
	}

	/**
	 * 
	 * getMessageById(根据站内信ID查询站内信)
	 * 
	 * @param id
	 *            站内信ID
	 * @return ModelAndView
	 * @exception
	 */
	@RequestMapping("getMessageById.do")
	public ModelAndView getMessageById(Long id) {
		TMessage record = messageService.findMessageById(id);
		ModelAndView modelAndView = new ModelAndView("equipType/typeSave");
		modelAndView.addObject("record", record);
		return modelAndView;
	}

	/**
	 * 
	 * updateMessagesAfterRead(处理后，批量更新消息状态)
	 * 
	 * @param idArray
	 *            一个站内信ID数组
	 * @return String 成功：OPERATE_SUCCESS；失败：OPERATE_SUCCESS
	 * @exception
	 */
	@RequestMapping("updateMessagesAfterRead.do")
	public @ResponseBody String updateMessagesAfterRead(
			@RequestParam(value = "idArray[]") Long[] idArray) {
		return messageService.updateMessagesAfterRead(idArray);
	}

	/**
	 * 
	 * updateMessagesByReciver(收件人删除收件箱中的消息)
	 * 
	 * @param idArray
	 *            一个站内信ID数组
	 * @return String 成功：OPERATE_SUCCESS；失败：OPERATE_SUCCESS
	 * @exception
	 */
	@RequestMapping("updateMessagesByReciver.do")
	public @ResponseBody String updateMessagesByReciver(
			@RequestParam(value = "idArray[]") Long[] idArray) {
		return messageService.updateMessagesByReciver(idArray);
	}
}
