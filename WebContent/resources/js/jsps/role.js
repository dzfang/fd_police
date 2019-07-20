/**
 * 用户列表画面初始化
 */
function roleListInit() {
	// 列表初始化
	dataGridInit();
	// 查询
	$("#btnSearch").bind("click", findGridData);
	// 清空表单数据
	$("#btnClear").bind("click", formReset);
	// 打开添加角色窗口
	$("#btnAdd").bind("click", openRoleAddWindow);
	// 批量删除角色
	$("#btnDelete").bind("click", deleteRoles);
	// 默认加载数据
	findGridData();
}
/**
 * 添加画面初始化
 */
function roleSaveInit() {
	// 保存角色菜单信息
	$("#btnSave").bind("click", saveRole);
	// 关闭画面
	$("#btnClose").bind("click", closePopupWindow);
	var roleId = null;
	if ($("#popRoleId").val() != "") {
		roleId = $("#popRoleId").val();
	}
	// 设定APP管理权限checkbox的状态
	var appFlag = null;
	if ($("#hidAppFlag").val() != "") {
		$("#appFlag").prop("checked", true);
	}
	// 菜单树初始化
	treeInit(roleId);
}
/**
 * 加载数据
 * 
 * @param pageIndex
 *            当前页码
 * @param pageSize
 *            每页分页笔数
 */
function loadData(pageIndex, pageSize) {
	$.ajax({
		type : "POST",
		url : basePath + '/role/getRoleList.do?pageIndex=' + pageIndex
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
 * 重新加载数据
 * 
 * @param pageIndex
 *            当前页码
 * @param pageSize
 *            每页分页笔数
 */
function reloadData(pageIndex, pageSize) {
	$.ajax({
		type : "POST",
		url : basePath + '/role/getRoleList.do?pageIndex=' + pageIndex
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
 * 清空查询区域
 */
function formReset() {
	$("#roleName").textbox('setValue', '');
}

/**
 * 格式化操作列
 * 
 * @param value
 *            当前单元格绑定的值
 * @param row
 *            当前行对象
 * @param index
 *            当前行下标
 * @returns {String} 格式化后的字符串
 */
function operateFormatter(value, row, index) {
	var edit = "<a href='javascript:void(0);' onclick='openRoleEditWindow(\""
			+ row.roleId + "\")'>编辑</a>&nbsp;&nbsp;";
	var del = "<a href='javascript:void(0);' onclick='deleteRole(\""
			+ row.roleId + "\")'>删除</a>&nbsp;&nbsp;";

	return edit + del;
}
/**
 * APP管理权限列格式化
 * 
 * @param value
 *            当前单元格绑定的值
 * @param row
 *            当前行对象
 * @param index
 *            当前行下标
 * @returns {String} 格式化后的字符串
 */
function appFlagFormatter(value, row, index) {
	if (isNull(value) == "") {
		return "无";
	}
	return "有";
}
/**
 * 打开新增画面
 */
function openRoleAddWindow() {
	var href = basePath + '/role/addInit.do';
	openWindow("新增角色", href, 620, 450);
}

/**
 * 打开编辑画面
 * 
 * @param roleId
 *            角色ID
 */
function openRoleEditWindow(roleId) {
	var href = basePath + '/role/updateInit.do?roleId=' + roleId;
	openWindow("编辑角色", href, 620, 450);
}

/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 批量删除角色信息
 */
function deleteRoles() {
	var roleId = "";
	$($('#dataList').datagrid('getSelections')).each(function() {
		roleId += "" + $(this).attr("roleId") + ",";
	});
	if (roleId.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_DELETE_DATA);
		return;
	}
	$.messager.confirm("提示信息", message.BATCH_DELETE_ROLE_CONFIRM, function(r) {
		if (r) {
			var param = {
				idArray : [ roleId.substring(0, roleId.length - 1) ]
			};
			doDelete(param);
		}
	});
}
/**
 * 执行批量删除
 * 
 * @param param
 *            角色ID组成的字符串，以,分割
 */
function doDelete(param) {
	$.ajax({
		type : "POST",
		url : basePath + '/role/deleteRolesById.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除角色
 * 
 * @param roleId
 *            角色ID
 */
function deleteRole(roleId) {
	var msg = message.DELETE_ROLE_CONFIRM;
	$.messager.confirm("提示信息", msg, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/role/deleteRoleById.do',
				data : {
					'roleId' : roleId
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
 * 保存角色信息
 */
function saveRole() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	var nodes = $('#treeUl').tree('getChecked');
	var menuId = '';
	for (var i = 0; i < nodes.length; i++) {
		if (menuId != '') {
			menuId += ',';
		}
		menuId += nodes[i].id;
	}
	if (menuId.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_MENU);
		return;
	}
	$("#menuIds").val(menuId);
	$.ajax({
		type : "POST",
		url : basePath + '/role/saveRole.do',
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
 * 重置表单数据
 */
function resetSaveForm() {
	$("#popRoleName").val("");
	$("#popRemark").val("");
	/*
	 * $("#popUserSex").combobox("setValue", "001"); $("#popTelephone").val("");
	 * $("#popEmail").val(""); $("#popUserStatus").combobox("setValue", "001");
	 * $("#popAddress").val("");
	 */
}
/**
 * 菜单树初始化
 */
function treeInit(roleId) {
	var url = basePath + '/role/getMenuInfo.do';
	if (roleId != null) {
		url += "?roleId=" + roleId;
	}
	$('#treeUl').tree({
		checkbox : true,
		url : url,
		onLoadSuccess : function() {
		}
	});
}
