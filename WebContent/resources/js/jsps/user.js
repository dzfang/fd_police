/**
 * 用户基本列表画面初始化
 */
function userListInit() {
	var columns = [ [ {
		field : 'userId',
		checkbox : true,
	}, {
		field : "loginId",
		title : "用户ID",
		width : 100
	}, {
		field : "userName",
		title : "姓名",
		width : 100
	}, {
		field : "sexName",
		title : "性别",
		width : 50
	}, {
		field : "birthday",
		title : "出生日期",
		width : 120,
		formatter : dateFormatter
	}, {
		field : "telephone",
		title : "手机号码",
		width : 150
	}, {
		field : "email",
		title : "邮箱",
		width : 150
	}, {
		field : "roleName",
		title : "角色",
		width : 150
	}, {
		field : "operate",
		title : "操作",
		width : 150,
		formatter : operateUserFormatter
	} ] ];
	var toolbar = [ {
		text : '新增',
		iconCls : 'icon-add',
		id : 'btnAdd'
	}, {
		text : '删除',
		iconCls : 'icon-remove',
		id : 'btnDelete'
	}, {
		text : '重置密码',
		iconCls : 'icon-reload',
		id : 'btnResetPassword'
	} ]
	// 列表初始化
	dataGridInit(columns, toolbar);
	// 控制行数据是否可以被删除
	// gridCheckInit();
	// 查询
	$("#btnSearch").bind("click", findGridData);
	// 清空表单数据
	$("#btnClear").bind("click", userFormReset);
	// 添加会员
	$("#btnAdd").bind("click", openAddUserWindow);
	// 批量删除会员
	$("#btnDelete").bind("click", deleteUsers);
	// 批量重置会员密码
	$("#btnResetPassword").bind("click", resetPasswords);
	// 高级查询
	$("#btnMore").bind("click", openSearchWindow);
	// 默认加载数据
	findGridData();
	treeInit("roleId", "0");
}
/**
 * 菜单树初始化
 */
function treeInit(id, rootId) {
	var method = (rootId == "0") ? "getRoleByParentId" : "findRoleById";
	var url = basePath + '/role/' + method + '.do?id=' + rootId;
	var roleTree = $('#' + id);
	roleTree.combotree({
		url : url,
		onBeforeExpand : function(node) {
			// 点击根节点的时候发送请求去加载子节点
			roleTree.combotree("tree").tree("options").url = basePath
					+ '/role/getRoleByParentId.do?id=' + node.id;
		},
		onClick : function(node) {
			roleTree.combotree("setText", node.text);
		},
		onLoadSuccess : function(node, data) {
			var roleName = $("#" + id).attr("roleName");
			if (roleName != undefined) {
				roleTree.combotree("setText", roleName);
			}
		}
	});
}
 
/**
 * 用户保存画面初始化
 */
function userSaveInit() {
	// 添加用户
	$("#btnSave").bind("click", saveUser);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);
	if ($("#canChangeRole").val() == "true") {
		treeInit("popRoleId", $("#hidRoleId").val());
	}
}

/**
 * 密码修改页面初始化
 */
function userPwdInit() {
	// 修改密码
	$("#btnSave").bind("click", updatePassword);
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
		url : basePath + '/user/getUserList.do?pageIndex=' + pageIndex
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
		url : basePath + '/user/getUserList.do?pageIndex=' + pageIndex
				+ '&pageSize=' + pageSize,
		data : $("#queryForm").serialize(),
		success : function(data) {
			$('#dataList').datagrid('loadData', data.rows);
			var pager = $('#pagination');
			pager.pagination({
				pageNumber : pageIndex,
				total : data.total
			});
		},
		error : doError
	});
}

/**
 * 清空查询区域
 */
function userFormReset() {
	$("#loginId").textbox('setValue', '');
	$("#userName").textbox('setValue', '');
}
 
/**
 * 格式化操作列
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function operateUserFormatter(value, row, index) {
	var result = "";

	if (canEditData(row.roleId)) {
		if (canEdit == "true") {
			result += "<a href='javascript:void(0);' class='l-btn-selected btn-sm' onclick='openEditUserWindow(\""
					+ row.userId + "\")'>编辑</a>&nbsp;&nbsp;";
		} else {
			result += "<a href='javascript:void(0);' class='l-btn-selected btn-sm'  style='color:#ccc'>编辑</a>&nbsp;&nbsp;";
		}
		if (canDelete == "true") {
			result += "<a href='javascript:void(0);' class='l-btn-danger btn-sm' onclick='deleteUser(\""
					+ row.userId + "\",\"" + row.loginId
					+ "\")'>删除</a>&nbsp;&nbsp;";
		} else {
			result += "<a href='javascript:void(0);' class='l-btn-danger btn-sm' style='color:#ccc'>删除</a>&nbsp;&nbsp;";
		}
	} else {
		result += "<a href='javascript:void(0);' class='l-btn-selected btn-sm' style='color:#ccc'>编辑</a>&nbsp;&nbsp;";
		result += "<a href='javascript:void(0);' class='l-btn-danger btn-sm' style='color:#ccc'>删除</a>&nbsp;&nbsp;";
	}

	return result;
}

/**
 * 当前角色是否可编辑某行数据
 * 
 * @param roleId
 * @returns
 */
function canEditData(roleId) {
	var roleIdString = $("#hidChildRoleIdString").val();
	if (roleIdString == undefined || roleId == undefined) {
		return false;
	}
	var roleIdArray = roleIdString.split(",");
	return (roleId = 2 || roleId == 3 || roleIdArray.indexOf(roleId) >= 0) ? true
			: false;
}
/**
 * 用户基本添加画面初始化
 */
function openAddUserWindow() {
	var href = basePath + '/user/addUser.do?orgIds=' + $("#orgIds").val();
	openWindow("新增用户基本信息", href, 750, 315);
}
 
/**
 * 打开用户基本编辑画面
 * 
 * @param userId
 */
function openEditUserWindow(userId) {
	var href = basePath + '/user/updateUser.do?userId=' + userId
			+ '&orgIds=' + $("#orgIds").val();
	openWindow("编辑用户基本信息", href, 750, 315);
}

function openSearchWindow(){
	var href = basePath + '/user/userSearch.do';
	openWindow("高级查询", href, 750, 315);
}
 
/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 批量删除用户
 */
function deleteUsers() {
	var userId = "";
	$($('#dataList').datagrid('getSelections')).each(function() {
		if (canEditData($(this).attr("roleId"))) {
			userId += "" + $(this).attr("userId") + ",";
		}
	});
	if (userId.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_DELETE_DATA);
		return;
	}
	$.messager.confirm("提示信息", message.BATCH_DELETE_USER_CONFIRM, function(r) {
		if (r) {
			var param = {
				idArray : [ userId.substring(0, userId.length - 1) ]
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
		url : basePath + '/user/deleteUsersById.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除会员
 * 
 * @param userId
 */
function deleteUser(userId, loginId) {
	var msg = message.DELETE_USER_CONFIRM;
	$.messager.confirm("提示信息", msg, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/user/deleteUserById.do',
				data : {
					'userId' : userId
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
 * 保存用户
 */
function saveUser() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : basePath + '/user/saveUser.do',
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
	$("#popLoginId").val('');
	$("#popUserName").val('');
	$("#popIdCard").val('');
	$("#popTelephone").val('');
	$("#popEmail").val('');
	$("#popAddress").val("");
	resetCXSelect("popUserStatus");
	resetCXSelect("popRoomCode");
	resetCXSelect("popUserSex");
	$("#popBirthday").datebox("setValue", "");
	var data = $('#popRoleId').combobox('getData');
	if (data != null && data.length > 0) {
		$('#popRoleId').combobox('setValue', data[0].roleId);
	}
}
/**
 * 修改密码
 */
function updatePassword() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : basePath + '/user/updatePassword.do',
		data : $("#saveForm").serialize(),

		success : function(data) {
			if (data != null) {
				$.messager.alert('提示信息', message.byId(data), "info");
			}
		},
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}
function gridCheckInit() {
	$('#dataList').datagrid({
		onLoadSuccess : function(data) {
			// 数据加载完成后，绑定每行checkbox的click事件
			if (data.rows.length > 0) {
				bindAfterLoadSuccess(data);
			}
		},
		onCheckAll : function(rowData) {
			var panel = $('#dataList').datagrid('getPanel');
			var rows = panel.find('tr[datagrid-row-index]');
			var allChecked = false;
			var selector = "div.datagrid-cell-check input[type=checkbox]";
			rows.find(selector).each(function() {
				var gridRow = $(this).parent().parent().parent();
				var index = gridRow.attr('datagrid-row-index');
				// 根据状态判断checkbox是否可用
				if (!canEditData(rowData[index].roleId)) {
					$(this).prop('checked', false);
				}
			});
		},
		onClickRow : function(rowIndex, rowData) {
			var panel = $('#dataList').datagrid('getPanel');
			var rows = panel.find('tr[datagrid-row-index]');
			var allChecked = false;
			var selector = "div.datagrid-cell-check input[type=checkbox]";
			var canSelectedCount = 0;
			rows.find(selector).each(function() {
				if (!$(this).prop("disabled")) {
					canSelectedCount++;
					if (!$(this).prop('checked')) {
						allChecked = false;
					}
				} else {
					var gridRow = $(this).parent().parent().parent();
					var index = gridRow.attr('datagrid-row-index');
					// 根据状态判断checkbox是否可用
					if (!canEditData(rowData.roleId)) {
						$(this).prop('checked', false);
					}
				}
			});
		}
	});
}
/**
 * 数据加载完成后，绑定每行checkbox的click事件
 */
function bindAfterLoadSuccess(data) {
	var panel = $('#dataList').datagrid('getPanel');
	var rows = panel.find('tr[datagrid-row-index]');
	var selector = "div.datagrid-cell-check input[type=checkbox]";
	rows.find(selector).each(function() {
		var gridRow = $(this).parent().parent().parent();
		var index = gridRow.attr('datagrid-row-index');
		// 根据状态判断checkbox是否可用
		if (!canEditData(data.rows[index].roleId)) {
			$(this).prop("disabled", true);
		}
		$(this).unbind().bind('click', function(e) {
			var allChecked = true;
			rows.find(selector).each(function() {
				var gridRow = $(this).parent().parent().parent();
				var index = gridRow.attr('datagrid-row-index');
				if (!$(this).prop("disabled")) {
					if ($(this).prop('checked')) {
						$('#dataList').datagrid('selectRow', index);
					} else {
						allChecked = false;
						$('#dataList').datagrid('unselectRow', index);
					}
				}
			});
			var checkbox = $(".datagrid-header-check input[type=checkbox]");
			checkbox.prop("checked", allChecked);
		});
	});
}

/**
 * 批量重置用户密码
 */
function resetPasswords() {
	var userId = "";
	$($('#dataList').datagrid('getSelections')).each(function() {
		userId += "" + $(this).attr("userId") + ",";
	});
	if (userId.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_RESET_DATA);
		return;
	}
	$.messager.confirm("提示信息", message.BATCH_RESET_USER_CONFIRM, function(r) {
		if (r) {
			var param = {
				idArray : [ userId.substring(0, userId.length - 1) ]
			};
			doReset(param);
		}
	});
}
/**
 * 执行重置用户密码操作
 * 
 * @param param
 *            用户ID拼接的字符串，以,分割
 */
function doReset(param) {
	$.ajax({
		type : "POST",
		url : basePath + '/user/updatePasswordById.do',
		data : param,
		success : function(data) {
			if (data != null) {
				if (data == "RESET_SUCCESS") {
					$.messager.alert('提示信息', message.byId(data), "info");
					reload();
				} else {
					$.messager.alert("提示信息", message.byId(data), "warning");
				}
			}
		},
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}
 
function doRegister() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : basePath + '/user/doRegister.do',
		data : $("#saveForm").serialize(),

		success : function(data) {
			if (data != null) {
				if (data == "SAVE_SUCCESS") {
					var href = basePath + '/user/registerSuccess.do';
					window.location.href = href;
				} else {
					$("#loginIdMsg").html(message.byId(data))
				}
			}
		},
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

function userSearchInit(){
	// 添加用户
	$("#btnSave").bind("click", doSearch);
}

function doSearch(){
	var sonText = $("#popLoginId").val();
	$("#loginId").textbox("setValue",sonText);
	closeWindow();
	findGridData();
}