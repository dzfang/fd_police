/**
 * 
 */
function roleList1Init() {
	// 初始化树
	treeInit();
	// 列表初始化
	// dataGridInit();
	// 添加类型
	$("#btnAddRole").bind("click", openRoleAddWindow);
	// 编辑
	$("#btnEditRole").bind("click", openRoleEditWindow);
	// 删除类别信息
	$("#btnDeleteRole").bind("click", deleteRole);
	// 默认加载数据
	// findGridData();

}

function roleSaveInit() {
	$("#btnSaveRole").bind("click", saveRole);
	$("#btnCloseRole").bind("click", closePopupWindow);
}

function roleAuthSaveInit() {
	$("#btnSaveAuth").bind("click", saveRoleAuth);
	$("#btnCheckAll").bind("click", checkAll);
	$("#btnUnCheckAll").bind("click", uncheckAll);
 
}

function checkAll() {
	$("input[type='checkbox']").prop("checked", true);
}

function uncheckAll() {
	$("input[type='checkbox']").prop("checked", false);
}
/**
 * 功能checkbox选中或不选中
 * 
 * @param obj
 */
function checkFunc(obj) {
	var menuId = $(obj).attr("menuId");
	var checked = $(obj).prop("checked");
	$("input[type='checkbox']").each(function() {
		var parentId = $(this).attr("parentId")
		if (menuId != undefined && menuId == parentId) {
			$(this).prop("checked", checked);
			checkItem(this);
		}
	});
}

/**
 * 菜单checkbox选中或不选中
 * 
 * @param obj
 */
function checkItem(obj) {
	var checked = $(obj).prop("checked");
	var parentId = $(obj).attr("parentId");
	var itemId = $(obj).attr("menuId");
	if (checked) {
		$("input[type='checkbox']").each(function() {
			var menuId = $(this).attr("menuId")
			if (menuId != undefined && menuId == parentId) {
				$(this).prop("checked", true);
			}
		});
		// 勾选数据、按钮操作权限
		dataAndActionChecked(itemId, true);

	} else {
		var flag = false
		$("input[type='checkbox']").each(function() {
			var id = $(this).attr("parentId")
			if (id != undefined && id == parentId && $(this).prop("checked")) {
				flag = true;
				return false;
			}
		});
		$("input[type='checkbox']").each(function() {
			var menuId = $(this).attr("menuId")
			if (menuId != undefined && menuId == parentId) {
				$(this).prop("checked", flag);
			}
		});
		// 不勾选数据、按钮操作权限
		dataAndActionChecked(itemId, false);
	}
}

function dataAndActionChecked(itemId, checked) {
	// 勾选数据、按钮操作权限
	$("input[type='checkbox']").each(
			function() {
				var menuId = $(this).attr("menuId")
				var actionId = $(this).attr("actionId")
				var dataId = $(this).attr("dataId")
				if (menuId != undefined && menuId == itemId
						&& (actionId != "" || dataId != "")) {
					$(this).prop("checked", checked);
				}
			});
}

function checkDataOrAction(obj) {
	var daMenuId = $(obj).attr("menuId");
	var checked = $(obj).prop("checked");
	if (checked) {
		$("input[type='checkbox']").each(
				function() {
					var menuId = $(this).attr("menuId")
					var parentId = $(this).attr("parentId")
					if (menuId != undefined && menuId == daMenuId
							&& parentId != undefined && parentId != "") {
						$(this).prop("checked", true);
					}
				});
	}
}
/**
 * 菜单树初始化
 */
function treeInit() {
	var url = basePath + '/role/findRoleById.do?id=' + $("#roleId").val();

	$('#treeUl').tree(
			{
				checkbox : false,
				url : url,
				onBeforeExpand : function(node) {
					// 点击根节点的时候发送请求去加载子节点
					$("#treeUl").tree('options').url = basePath
							+ '/role/getRoleByParentId.do?id=' + node.id;
				},
				onClick : function(node) {
					var pn = $('#treeUl').tree('getParent', node.target);
					var src = basePath + '/role/funcList.do?id=' + node.id;
					;
					// if (pn == null) {
					// src = basePath + '/role/funcList.do?id=' + node.id;
					// } else {
					// src = basePath + '/role/funcList.do?id=' + node.id
					// + "&parentId=" + pn.id;
					// }
					$("#funcFrame").attr("src", src);
				},
				onLoadSuccess : function(node, data) {
					if (data.length > 0) {
						var nd = $('#treeUl').tree('find', data[0].id);
						$('#treeUl').tree('select', nd.target);
						$("#funcFrame").attr("src",
								basePath + '/role/funcList.do?id=' + nd.id);
					}

				}
			});
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
		url : basePath + '/codeData/getCodeList.do?pageIndex=' + pageIndex
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
 * 重新加载数据，将查询时的进度条去掉。重新加载数据时，由于上一步操作已经弹出了进度条，此处再弹进度条时，会引起脚本错误
 * 
 * @param pageIndex
 *            当前页码
 * @param pageSize
 *            每页分页笔数
 */
function reloadData(pageIndex, pageSize) {
	$.ajax({
		type : "POST",
		url : basePath + '/codeData/getCodeList.do?pageIndex=' + pageIndex
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
 * 打开角色新增画面
 */
function openRoleAddWindow() {
	var href = basePath + '/role/addRole.do';
	openWindow("新增角色", href, 400, 170);
}

/**
 * 打开编辑画面
 * 
 * @param type
 */
function openRoleEditWindow() {
	var node = $("#treeUl").tree("getSelected");
	if (node) {
		var href = basePath + '/role/updateRole.do?roleId=' + node.id;
		openWindow("编辑角色", href, 400, 170);
	} else {
		$.messager.alert("提示信息", "请选择要编辑的数据！", "info");
	}

}

/**
 * 保存角色信息
 */
function saveRole() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : basePath + '/role/saveRole.do',
		data : $("#saveForm").serialize(),

		success : function(data) {
			if (data != null) {
				$.messager.alert("提示信息", message.byId(data), "info");
				if (data == "SAVE_SUCCESS") {
					closePopupWindow();
					treeInit();
				}
			}
		},
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}
/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

function resetSaveForm() {
	$("#popRoleName").val("");
}
/**
 * 删除角色
 * 
 */
function deleteRole() {
	var node = $("#treeUl").tree("getSelected");
	if (node == null) {
		$.messager.alert("提示信息", message.PLS_SELECT_DELETE_DATA, "info");
		return;
	}
	var parentNode = $('#treeUl').tree('getParent', node.target);
	if (parentNode == null) {
		$.messager.alert("提示信息", message.NORIGHT_DELETE_ROLE, "warning");
		return;
	}
	var msg = message.DELETE_ROLE_CONFIRM;
	$.messager.confirm("提示信息", msg, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/role/deleteRoleById.do',
				data : {
					'roleId' : node.id
				},
				success : function(data) {
					if (data != null) {
						$.messager.alert("提示信息", message.byId(data), "info");
						if (data == "DELETE_SUCCESS") {
							closePopupWindow();
							treeInit();
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

function saveRoleAuth() {
	var authString = "";
	$("input[type='checkbox']").each(
			function() {
				if ($(this).prop("checked")) {
					if (authString.length > 0) {
						authString += ";";
					}
					authString += $(this).attr("menuId") + ","
							+ $(this).attr("actionId") + ","
							+ $(this).attr("dataId");
				}
			});
	if (authString.length == 0) {
		$.messager.alert("提示信息", "请勾选权限信息！", "info");
		return;
	}
	$.ajax({
		type : "POST",
		url : basePath + '/role/saveRoleAuth.do',
		data : {
			'roleId' : $("#roleId").val(),
			'authString' : authString
		},

		success : function(data) {
			if (data != null) {
				if (data == "SAVE_SUCCESS") {
					$.messager.alert("提示信息", message.byId(data), "info");
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
