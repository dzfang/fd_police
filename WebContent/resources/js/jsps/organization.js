/**
 * 部门信息列表画面初始化
 */
function organizationListInit() {
	// 列表初始化
	dataGridInit();
	// 查询
	$("#btnSearch").bind("click", findGridData);
	// 清空表单数据
	$("#btnClear").bind("click", organizationFormReset);
	// 添加部门信息
	$("#btnAdd").bind("click", openAddOrganizationWindow);
	// 批量删除部门信息
	$("#btnDelete").bind("click", deleteOrganizations);
	// 默认加载数据
	findGridData();
}

/**
 * 部门信息保存画面初始化
 */
function organizationSaveInit() {
	// 添加部门信息
	$("#btnSave").bind("click", saveOrganization);
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
		url : basePath + '/organization/getOrganizationList.do?pageIndex='
				+ pageIndex + '&pageSize=' + pageSize,
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
		url : basePath + '/organization/getOrganizationList.do?pageIndex='
				+ pageIndex + '&pageSize=' + pageSize,
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
 * 清空查询区域
 */
function organizationFormReset() {
	$("#orgName").textbox('setValue', '');
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
		result += "<a href='javascript:void(0);' onclick='openEditOrganizationWindow(\""
				+ row.id + "\")'>编辑</a>&nbsp;&nbsp;";
	}
	if (canDelete == "true") {
		result += "<a href='javascript:void(0);' onclick='deleteOrganization(\""
				+ row.id + "\")'>删除</a>&nbsp;&nbsp;";
	}
	if (result == "") {
		$("#dataList").datagrid('hideColumn', 'operate');
	}
	return result;
}

/**
 * 部门信息添加画面初始化
 */
function openAddOrganizationWindow() {
	var href = basePath + '/organization/addOrg.do';
	openWindow("新增部门信息", href, 320, 125);
}

/**
 * 打开部门信息编辑画面
 * 
 * @param organizationId
 */
function openEditOrganizationWindow(id) {
	var href = basePath + '/organization/getOrganizationById.do?id=' + id;
	openWindow("编辑部门信息", href, 320, 125);
}

/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 批量删除部门信息
 */
function deleteOrganizations() {
	var id = "";
	$($('#dataList').datagrid('getSelections')).each(function() {
		id += "" + $(this).attr("id") + ",";
	});
	if (id.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_DELETE_DATA, "info");
		return;
	}
	$.messager.confirm("提示信息", message.DELETE_ORG_CONFIRM, function(r) {
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
		url : basePath + '/organization/deleteOrganizationsById.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除部门信息
 * 
 * @param id
 */
function deleteOrganization(id) {
	$.messager.confirm("提示信息", message.DELETE_ORG_CONFIRM, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/organization/deleteOrganizationsById.do',
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
 * 保存部门信息
 */
function saveOrganization() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	 
	$.ajax({
		type : "POST",
		url : basePath + '/organization/saveOrganization.do',
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
 