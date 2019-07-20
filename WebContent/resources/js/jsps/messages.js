/**
 * 站内信列表画面初始化
 */
function messageListInit() {
	// 列表初始化
	dataGridInit();
	// 查询
	$("#btnSearch").bind("click", findGridData);
	// 清空表单数据
	$("#btnClear").bind("click", messageFormReset);
	// 添加站内信
	$("#btnAdd").bind("click", openAddMessageWindow);
	// 批量删除站内信
	$("#btnDelete").bind("click", deleteMessages);
	// 默认加载数据
	findGridData();
}
/**
 * 收件箱初始化
 */
function messageReciveListInit() {
	// 列表初始化
	dataGridInit();
	// 查询
	$("#btnSearch").bind("click", findGridData);
	// 清空表单数据
	$("#btnClear").bind("click", reciverFormReset);

	// 添加站内信
	$("#btnRead").bind("click", readMessages);
	// 批量删除站内信
	$("#btnDelete").bind("click", reciverDeleteMessages);

	// 默认加载数据
	findGridData();
}
/**
 * 站内信保存画面初始化
 */
function messageSaveInit() {
	// 添加站内信
	$("#btnSave").bind("click", saveMessage);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);
}

/**
 * 加载数据
 * 
 * @param pageIndex
 * @param pageSize
 */
function loadData(pageIndex, pageSize) {
	$.ajax({
		type : "POST",
		url : basePath + '/messages/getMessageList.do?pageIndex=' + pageIndex
				+ '&pageSize=' + pageSize,
		data : $("#queryForm").serialize(),
		beforeSend : onBeforeSend,
		success : function(data) {
			$('#dataList').datagrid('loadData', data.rows);
			var pager = $("#dataList").datagrid('getPager');
			pager.pagination({
				pageNumber : pageIndex,
				total : data.total
			});
			$("input:checkbox").css("border", "none");
		},
		error : doError,
		complete : onComplete
	});
}
/**
 * 重新加载数据，不带进度条
 * 
 * @param pageIndex
 * @param pageSize
 */
function reloadData(pageIndex, pageSize) {
	$.ajax({
		type : "POST",
		url : basePath + '/messages/getMessageList.do?pageIndex=' + pageIndex
				+ '&pageSize=' + pageSize,
		data : $("#queryForm").serialize(),
		success : function(data) {
			$('#dataList').datagrid('loadData', data.rows);
			var pager = $("#dataList").datagrid('getPager');
			pager.pagination({
				pageNumber : pageIndex,
				total : data.total
			});
			$("input:checkbox").css("border", "none");
		},
		error : doError
	});
}

/**
 * 清空发件箱查询区域
 */
function messageFormReset() {
	$("#processSign").combobox('setValue', '');
	$("#createDate").datebox('setValue', '');
}
/**
 * 清空收件箱查询区域
 */
function reciverFormReset() {
	combogridClear("createUser");
	$("#processSign").combobox('setValue', '');
	$("#createDate").datebox('setValue', '');
}
/**
 * 格式化操作列
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function operateFormatter(value, row, index) {
	var result = "";
	if (canEdit == "true") {
		result += "<a href='javascript:void(0);' onclick='openEditMessageWindow(\""
				+ row.id + "\")'>编辑</a>&nbsp;&nbsp;";
	}
	if (canDelete == "true") {
		result += "<a href='javascript:void(0);' onclick='deleteMessage(\""
				+ row.id + "\")'>删除</a>&nbsp;&nbsp;";
	}
	if (result == "") {
		$("#dataList").datagrid('hideColumn', 'operate');
	}
	return result;
}

/**
 * 站内信添加画面初始化
 */
function openAddMessageWindow() {
	var href = basePath + '/messages/addMessage.do';
	openWindow("新增站内信", href, 620, 260);
}

/**
 * 打开站内信编辑画面
 * 
 * @param messageId
 */
function openEditMessageWindow(id) {
	var href = basePath + '/messages/getMessageById.do?id=' + id;
	openWindow("编辑站内信", href, 620, 260);
}

/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 批量删除站内信
 */
function deleteMessages() {
	var id = "";
	$($('#dataList').datagrid('getSelections')).each(function() {
		id += "" + $(this).attr("id") + ",";
	});
	if (id.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_DELETE_DATA, "info");
		return;
	}
	$.messager.confirm("提示信息", message.DELETE_MESSAGE_CONFIRM, function(r) {
		if (r) {
			var param = {
				idArray : [ id.substring(0, id.length - 1) ]
			};
			doDelete(param);
		}
	});
}
/**
 * 执行删除操作
 */
function doDelete(param) {
	$.ajax({
		type : "POST",
		url : basePath + '/messages/deleteMessagesById.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除站内信
 * 
 * @param id
 */
function deleteMessage(id) {
	var msg = message.DELETE_MESSAGE_CONFIRM;
	$.messager.confirm("提示信息", msg, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/messages/deleteMessagesById.do',
				data : {
					idArray : [ id ]
				},
				success : afterDelete,
				error : doError,
				beforeSend : onBeforeSend,
				complete : onComplete
			});
		}
	});
}

/**
 * 保存站内信
 */
function saveMessage() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : basePath + '/messages/saveMessage.do',
		data : $("#saveForm").serialize(),

		success : function(data) {
			if (data != null) {
				$.messager.alert("提示信息", message.byId(data), "info");
				if (data == "SAVE_SUCCESS") {
					closePopupWindow();
					reload();
				}
			}
		},
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 收件箱操作列格式化
 */
function reciveFormatter(value, row, index) {
	var result = "";
	if (canRead == "true") {
		result += "<a href='javascript:void(0);' onclick='readMessage(\""
				+ row.id + "\")'>处理</a>&nbsp;&nbsp;";
	}
	if (canDelete == "true") {
		result += "<a href='javascript:void(0);' onclick='reciverDeleteMessage(\""
				+ row.id + "\")'>删除</a>&nbsp;&nbsp;";
	}
	if (result == "") {
		$("#dataList").datagrid('hideColumn', 'operate');
	}
	return result;
}

/**
 * 批量删除站内信
 */
function readMessages() {
	var id = "";
	$($('#dataList').datagrid('getSelections')).each(function() {
		id += "" + $(this).attr("id") + ",";
	});
	if (id.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_DELETE_DATA, "info");
		return;
	}
	// 重置Confrim窗体的按钮文本
	$.messager.confirm("提示信息", message.READ_MESSAGE_CONFIRM, function(r) {
		if (r) {
			var param = {
				idArray : [ id.substring(0, id.length - 1) ]
			};
			doDeleteAfterRead(param);
		}
	});
}
/**
 * 执行删除操作
 */
function doDeleteAfterRead(param) {
	$.ajax({
		type : "POST",
		url : basePath + '/messages/updateMessagesAfterRead.do',
		data : param,
		success : function(data) {
			if (data != null) {
				$.messager.alert("提示信息", message.byId(data), "info");
				if (data == "OPERATE_SUCCESS") {
					reload();
				}
			}
		},
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除站内信
 * 
 * @param id
 */
function readMessage(id) {
	var msg = message.READ_MESSAGE_CONFIRM;
	$.messager.confirm("提示信息", msg, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/messages/updateMessagesAfterRead.do',
				data : {
					idArray : [ id ]
				},
				success : function(data) {
					if (data != null) {
						$.messager.alert("提示信息", message.byId(data), "info");
						if (data == "OPERATE_SUCCESS") {
							reload();
						}
					}
				},
				error : doError,
				beforeSend : onBeforeSend,
				complete : onComplete
			});
		}
	});
}

/**
 * 收件人删除收件箱内的消息
 */
function reciverDeleteMessages() {
	var id = "";
	$($('#dataList').datagrid('getSelections')).each(function() {
		id += "" + $(this).attr("id") + ",";
	});
	if (id.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_DELETE_DATA, "info");
		return;
	}
	$.messager.confirm("提示信息", message.DELETE_MESSAGE_CONFIRM, function(r) {
		if (r) {
			var param = {
				idArray : [ id.substring(0, id.length - 1) ]
			};
			doReciverDelete(param);
		}
	});
}
/**
 * 执行删除操作
 */
function doReciverDelete(param) {
	$.ajax({
		type : "POST",
		url : basePath + '/messages/updateMessagesByReciver.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 收件人删除收件箱内的消息
 * 
 * @param id
 */
function reciverDeleteMessage(id) {
	var msg = message.DELETE_MESSAGE_CONFIRM;
	$.messager.confirm("提示信息", msg, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/messages/updateMessagesByReciver.do',
				data : {
					idArray : [ id ]
				},
				success : afterDelete,
				error : doError,
				beforeSend : onBeforeSend,
				complete : onComplete
			});
		}
	});
}
