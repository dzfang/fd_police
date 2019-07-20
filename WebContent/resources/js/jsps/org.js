/**
 * 从业企业信息列表画面初始化
 */
function organizationListInit() {
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : "orgCode",
		title : "机构代码",
		width : 130
	}, {
		field : "orgName",
		title : "单位名称",
		width : 150
	}, {
		field : "license",
		title : "营业执照",
		width : 150
	}, {
		field : "orgTypeName",
		title : "单位类型",
		width : 120
	}, {
		field : "qualified",
		title : "从业资质",
		width : 150
	}, {
		field : "qualifiedTypeName",
		title : "资质类别",
		width : 120
	}, {
		field : "corporationName",
		title : "法人代表",
		width : 100
	}, {
		field : "operate",
		title : "操作",
		width : 120,
		formatter : operateFormatter
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
	dataGridInit(columns, toolbar);
	// 查询
	$("#btnSearch").bind("click", function() {
		searchClear();
		findGridData()
	});
	// 清空表单数据
	$("#btnClear").bind("click", organizationFormReset);
	// 添加从业企业信息
	$("#btnAdd").bind("click", openAddOrganizationWindow);
	// 批量删除从业企业信息
	$("#btnDelete").bind("click", deleteOrganizations);
	// 高级查询
	$("#btnMore").bind("click", openSearchWindow);
	// 默认加载数据
	findGridData();
}

/**
 * 从业企业信息保存画面初始化
 */
function organizationSaveInit() {
	// 添加从业企业信息
	$("#btnSave").bind("click", saveOrganization);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);

}
/**
 * 高级查询
 */
function organizationSearchInit() {
	// 查询从业企业信息
	$("#btnPopSearch").bind("click", queryOrganization);
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
		url : basePath + '/org/getOrganizationList.do?pageIndex=' + pageIndex
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
		url : basePath + '/org/getOrganizationList.do?pageIndex=' + pageIndex
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
function organizationFormReset() {
	$("#orgCode").textbox('setValue', '');
	$("#orgName").textbox('setValue', '');
	searchClear();
}
/**
 * 清空高级查询
 */
function searchClear() {
	$("#license").val('');
	$("#orgType").val('');
	$("#qualified").val('');
	$("#qualifiedType").val('');
	$("#corporationName").val('');
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
		result += "<a href='javascript:void(0);' class='l-btn-selected btn-sm' onclick='openEditOrganizationWindow(\""
				+ row.id + "\")'>编辑</a>&nbsp;&nbsp;";
	}
	if (canDelete == "true") {
		result += "<a href='javascript:void(0);' class='l-btn-danger btn-sm' onclick='deleteOrganization(\""
				+ row.id + "\")'>删除</a>&nbsp;&nbsp;";
	}
	if (result == "") {
		$("#dataList").datagrid('hideColumn', 'operate');
	}
	return result;
}

/**
 * 从业企业信息添加画面初始化
 */
function openAddOrganizationWindow() {
	var href = basePath + '/org/addOrg.do';
	openWindow("新增从业企业信息", href, 800, 315);
}

/**
 * 打开从业企业信息编辑画面
 * 
 * @param organizationId
 */
function openEditOrganizationWindow(id) {
	var href = basePath + '/org/getOrganizationById.do?id=' + id;
	openWindow("编辑从业企业信息", href, 800, 315);
}

/**
 * 打开高级查询画面
 * 
 * @param organizationId
 */
function openSearchWindow() {
	var href = basePath + '/org/orgSearch.do';
	openWindow("高级查询", href, 800, 315);
}
/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 批量删除从业企业信息
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
		url : basePath + '/org/deleteOrganizationsById.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除从业企业信息
 * 
 * @param id
 */
function deleteOrganization(id) {
	$.messager.confirm("提示信息", message.DELETE_CONFIRM, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/org/deleteOrganizationsById.do',
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
 * 保存从业企业信息
 */
function saveOrganization() {
	if (!$("#saveForm").form("validate")) {
		return;
	}

	$.ajax({
		type : "POST",
		url : basePath + '/org/saveOrganization.do',
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
 * 查询从业企业信息
 */
function queryOrganization() {
	var orgCode = $("#popOrgCode").val();
	var orgName = $("#popOrgName").val();
	var license = $("#popLicense").val();
	var orgType = $("#popOrgType").combobox('getValue');
	var qualified = $("#popQualified").val();
	var qualifiedType = $("#popQualifiedType").combobox('getValue');
	var corporationName = $("#popCorporationName").val();

	$("#orgCode").textbox("setValue", orgCode);
	$("#orgName").textbox("setValue", orgName);
	$("#license").val(license);
	$("#orgType").val(orgType);
	$("#qualified").val(qualified);
	$("#qualifiedType").val(qualifiedType);
	$("#corporationName").val(corporationName);
	closeWindow();
	findGridData();
}