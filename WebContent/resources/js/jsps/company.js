/**
 * 从业企业信息列表画面初始化
 */
function companyListInit() {
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : "license",
		title : "许可证号",
		width : 130
	}, {
		field : "companyName",
		title : "单位名称",
		width : 250
	}, {
		field : "legalPerson",
		title : "法人",
		width : 90
	}, {
		field : "director",
		title : "负责人",
		width : 90
	}, {
		field : "owner",
		title : "业主",
		width : 90
	}, {
		field : "phone",
		title : "联系电话",
		width : 100
	}, {
		field : "address",
		title : "地址",
		width : 250
	}, {
		field : "companyTypeName",
		title : "类型",
		width : 100
	}, {
		field : "remark",
		title : "备注",
		width : 250
	}, {
		field : "issueDate",
		title : "发证时间",
		width : 100,
		formatter : dateFormatter
	}, {
		field : "agent",
		title : "委托代理人",
		width : 100
	}, {
		field : "agentPhone",
		title : "联系电话",
		width : 100
	}, {
		field : "handleTypeName",
		title : "备注",
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
	}, {
		text : '导入',
		iconCls : 'icon-edit',
		id : 'btnImport'
	}, {
		text : '下载模板',
		iconCls : 'icon-reload',
		handler : function() {
			document.getElementById("btnDownLoad").click();
		}
	}  ]
	// 列表初始化
	dataGridInit(columns, toolbar);
	// 查询
	$("#btnSearch").bind("click", function() {
		searchClear();
		findGridData()
	});
	// 清空表单数据
	$("#btnClear").bind("click", companyFormReset);
	// 添加从业企业信息
	$("#btnAdd").bind("click", openAddCompanyWindow);
	// 批量删除从业企业信息
	$("#btnDelete").bind("click", deleteCompanys);
	// 打开Excel导入画面
	$("#btnImport").bind("click", openImportCompanyWindow);
	// 高级查询
	// $("#btnMore").bind("click", openSearchWindow);
	// 默认加载数据
	findGridData();
}

/**
 * 从业企业信息保存画面初始化
 */
function companySaveInit() {
	// 添加从业企业信息
	$("#btnSave").bind("click", saveCompany);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);

}
 
/**
 * 从业企业导入画面初始化
 */
function companyImportInit() {
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
		url : basePath + '/company/getCompanyList.do?pageIndex=' + pageIndex
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
		url : basePath + '/company/getCompanyList.do?pageIndex=' + pageIndex
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
function companyFormReset() {
	$("#handleYear").combobox('setValue', '');
	$("#handleType").combobox('setValue', '');
	$("#companyName").textbox('setValue', '');
	searchClear();
}
/**
 * 清空高级查询
 */
function searchClear() {
	 
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
		result += "<a href='javascript:void(0);' class='l-btn-selected btn-sm' onclick='openEditCompanyWindow(\""
				+ row.id + "\")'>编辑</a>&nbsp;&nbsp;";
	}
	if (canDelete == "true") {
		result += "<a href='javascript:void(0);' class='l-btn-danger btn-sm' onclick='deleteCompany(\""
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
function openAddCompanyWindow() {
	var href = basePath + '/company/addCompany.do';
	openWindow("新增从业企业信息", href, 800, 505);
}

/**
 * 打开从业企业信息编辑画面
 * 
 * @param companyId
 */
function openEditCompanyWindow(id) {
	var href = basePath + '/company/getCompanyById.do?id=' + id;
	openWindow("编辑从业企业信息", href, 800, 505);
}

/**
 * 打开高级查询画面
 * 
 * @param companyId
 */
function openSearchWindow() {
	var href = basePath + '/company/companySearch.do';
	openWindow("高级查询", href, 800, 315);
}

/**
 * 打开导入画面
 * 
 * @param organizationId
 */
function openImportCompanyWindow() {
	var href = basePath + '/company/importInit.do';
	openWindow("导入从业单位信息", href, 630, 140);
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
function deleteCompanys() {
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
		url : basePath + '/company/deleteCompanysById.do',
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
function deleteCompany(id) {
	$.messager.confirm("提示信息", message.DELETE_CONFIRM, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/company/deleteCompanysById.do',
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
function saveCompany() {
	if (!$("#saveForm").form("validate")) {
		return;
	}

	$.ajax({
		type : "POST",
		url : basePath + '/company/saveCompany.do',
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
