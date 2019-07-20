/**
 * 文档列表画面初始化
 */
function documentListInit() {
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : "fileName",
		title : "文件名称",
		width : 400
	}, {
		field : "fileSize",
		title : "文件大小",
		width : 150,
		formatter : byteFormatter
	}, {
		field : "createDate",
		title : "上传时间",
		width : 150,
		formatter : timeFormatter
	}, {
		field : "userName",
		title : "上传人",
		width : 120
	} ] ];
	var toolbar = [ {
		text : '新增',
		iconCls : 'icon-add',
		id : 'btnAdd'
	}, {
		text : '删除',
		iconCls : 'icon-remove',
		id : 'btnDelete'
	} ]
	// 列表初始化
	dataGridInit(columns, undefined);
	// // 查询
	// $("#btnSearch").bind("click", function() {
	// searchClear();
	// findGridData()
	// });
	// // 清空表单数据
	// $("#btnClear").bind("click", documentanizationFormReset);
	// // 添加文档信息
	// $("#btnAdd").bind("click", openAddDocumentWindow);
	// // 批量删除文档信息
	// $("#btnDelete").bind("click", deleteDocuments);
	// // 高级查询
	// $("#btnMore").bind("click", openSearchWindow);
	// 默认加载数据
	findGridData();
}

/**
 * 文档信息保存画面初始化
 */
function documentanizationSaveInit() {
	// 添加文档信息
	$("#btnSave").bind("click", saveDocument);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);

}
/**
 * 高级查询
 */
function documentanizationSearchInit() {
	// 查询文档信息
	$("#btnPopSearch").bind("click", queryDocument);
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
		url : basePath + '/document/getDocumentList.do?pageIndex=' + pageIndex
				+ '&pageSize=' + pageSize,
		data : $("#queryForm").serialize(),
		beforeSend : onBeforeSend,
		success : function(data) {
			$('#dataList').datagrid('loadData', data.rows);
			var pager = $('#pagination');
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
		url : basePath + '/document/getDocumentList.do?pageIndex=' + pageIndex
				+ '&pageSize=' + pageSize,
		data : $("#queryForm").serialize(),
		success : function(data) {
			$('#dataList').datagrid('loadData', data.rows);
			var pager = $('#pagination');
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
 * 清空查询区域
 */
function documentanizationFormReset() {
	$("#documentCode").textbox('setValue', '');
	$("#documentName").textbox('setValue', '');
	searchClear();
}
/**
 * 清空高级查询
 */
function searchClear() {
	$("#license").val('');
	$("#documentType").val('');
	$("#qualified").val('');
	$("#qualifiedType").val('');
	$("#corporationName").val('');
}

function byteFormatter(value, row, index) {
	if (value < 1024) {
		return value + "字节";
	} else if (value > 1024 && value < 1024 * 1024) {
		return (Number(value) / 1024).toFixed(2) + "KB";
	} else {
		return (Number(value) / (1024 * 1024)).toFixed(2) + "MB";
	}
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
		result += "<a href='javascript:void(0);' class='l-btn-selected btn-sm' onclick='openEditDocumentWindow(\""
				+ row.id + "\")'>编辑</a>&nbsp;&nbsp;";
	}
	if (canDelete == "true") {
		result += "<a href='javascript:void(0);' class='l-btn-danger btn-sm' onclick='deleteDocument(\""
				+ row.id + "\")'>删除</a>&nbsp;&nbsp;";
	}
	if (result == "") {
		$("#dataList").datagrid('hideColumn', 'operate');
	}
	return result;
}

/**
 * 文档信息添加画面初始化
 */
function openAddDocumentWindow() {
	var href = basePath + '/document/addOrg.do';
	openWindow("新增文档信息", href, 800, 315);
}

/**
 * 打开文档信息编辑画面
 * 
 * @param documentanizationId
 */
function openEditDocumentWindow(id) {
	var href = basePath + '/document/getDocumentById.do?id=' + id;
	openWindow("编辑文档信息", href, 800, 315);
}

/**
 * 打开高级查询画面
 * 
 * @param documentanizationId
 */
function openSearchWindow() {
	var href = basePath + '/document/documentSearch.do';
	openWindow("高级查询", href, 800, 315);
}
/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 批量删除文档信息
 */
function deleteDocuments() {
	var id = "";
	$($('#dataList').datagrid('getSelections')).each(function() {
		id += "" + $(this).attr("id") + ",";
	});
	if (id.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_DELETE_DATA, "info");
		return;
	}
	$.messager.confirm("提示信息", message.DELETE_CONFIRM, function(r) {
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
		url : basePath + '/document/deleteDocumentsById.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除文档信息
 * 
 * @param id
 */
function deleteDocument(id) {
	$.messager.confirm("提示信息", message.DELETE_CONFIRM, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/document/deleteDocumentsById.do',
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
 * 保存文档信息
 */
function saveDocument() {
	if (!$("#saveForm").form("validate")) {
		return;
	}

	$.ajax({
		type : "POST",
		url : basePath + '/document/saveDocument.do',
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
 * 查询文档信息
 */
function queryDocument() {
	var documentCode = $("#popOrgCode").val();
	var documentName = $("#popOrgName").val();
	var license = $("#popLicense").val();
	var documentType = $("#popOrgType").combobox('getValue');
	var qualified = $("#popQualified").val();
	var qualifiedType = $("#popQualifiedType").combobox('getValue');
	var corporationName = $("#popCorporationName").val();

	$("#documentCode").textbox("setValue", documentCode);
	$("#documentName").textbox("setValue", documentName);
	$("#license").val(license);
	$("#documentType").val(documentType);
	$("#qualified").val(qualified);
	$("#qualifiedType").val(qualifiedType);
	$("#corporationName").val(corporationName);
	closeWindow();
	findGridData();
}