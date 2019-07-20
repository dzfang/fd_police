/**
 * 从业人员信息列表画面初始化
 */
function employeeListInit() {
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : "idCard",
		title : "身份证号",
		width : 150
	}, {
		field : "name",
		title : "姓名",
		width : 70
	}, {
		field : "genderName",
		title : "性别",
		width : 50
	}, {
		field : "birthday",
		title : "出生日期",
		width : 80,
		formatter : dateFormatter
	}, {
		field : "birthPlace",
		title : "籍贯",
		width : 120
	}, {
		field : "nationName",
		title : "民族",
		width : 50
	}, {
		field : "school",
		title : "毕业院校",
		width : 150
	}, {
		field : "major",
		title : "专业",
		width : 150
	}, {
		field : "degreeName",
		title : "学历",
		width : 50
	}, {
		field : "position",
		title : "职称",
		width : 80
	}, {
		field : "address",
		title : "住址",
		width : 150
	}, {
		field : "telephone",
		title : "联系电话",
		width : 100
	}, {
		field : "orgCode",
		title : "机构代码",
		width : 100
	}, {
		field : "orgName",
		title : "单位名称",
		width : 150
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
	} ]
	// 列表初始化
	dataGridInit(columns, toolbar);
	// 查询
	$("#btnSearch").bind("click", function() {
		searchClear();
		findGridData()
	});
	// 清空表单数据
	$("#btnClear").bind("click", employeeFormReset);
	// 添加从业人员信息
	$("#btnAdd").bind("click", openAddEmployeeWindow);
	// 批量删除从业人员信息
	$("#btnDelete").bind("click", deleteEmployees);
	// 添加从业人员信息
	$("#btnImport").bind("click", openImportWindow);
	// 高级查询
	$("#btnMore").bind("click", openSearchWindow);
	// 默认加载数据
	findGridData();
}

/**
 * 从业人员信息保存画面初始化
 */
function employeeSaveInit() {
	// 添加从业人员信息
	$("#btnSave").bind("click", saveEmployee);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);

}

/**
 * 高级查询
 */
function employeeSearchInit() {
	// 查询从业企业信息
	$("#btnPopSearch").bind("click", queryEmployee);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);
}

/**
 * 从业人员信息导入画面初始化
 */
function employeeImportInit() {
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
		url : basePath + '/employee/getEmployeeList.do?pageIndex=' + pageIndex
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
		url : basePath + '/employee/getEmployeeList.do?pageIndex=' + pageIndex
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
function employeeFormReset() {
	$("#name").textbox('setValue', '');
	$("#idCard").textbox('setValue', '');
	searchClear();
}
/**
 * 清空高级查询
 */
function searchClear() {
	$("#major").val('');
	$("#degree").val('');
	$("#orgCode").val('');
	$("#orgName").val('');
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
		result += "<a href='javascript:void(0);' class='l-btn-selected btn-sm' onclick='openEditEmployeeWindow(\""
				+ row.id + "\")'>编辑</a>&nbsp;&nbsp;";
	}
	if (canDelete == "true") {
		result += "<a href='javascript:void(0);' class='l-btn-danger btn-sm' onclick='deleteEmployee(\""
				+ row.id + "\")'>删除</a>&nbsp;&nbsp;";
	}
	if (result == "") {
		$("#dataList").datagrid('hideColumn', 'operate');
	}
	return result;
}

/**
 * 从业人员信息添加画面初始化
 */
function openAddEmployeeWindow() {
	var href = basePath + '/employee/addEmployee.do';
	openWindow("新增从业人员信息", href, 800, 460);
}

/**
 * 打开从业人员信息编辑画面
 * 
 * @param employeeId
 */
function openEditEmployeeWindow(id) {
	var href = basePath + '/employee/getEmployeeById.do?id=' + id;
	openWindow("编辑从业人员信息", href, 800, 460);
}
/**
 * 打开高级查询画面
 * 
 * @param organizationId
 */
function openSearchWindow() {
	var href = basePath + '/employee/employeeSearch.do';
	openWindow("高级查询", href, 800, 265);
}

function openImportWindow() {
	var href = basePath + '/employee/importInit.do';
	openWindow("导入从业人员信息", href, 630, 140);
}
/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 批量删除从业人员信息
 */
function deleteEmployees() {
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
		url : basePath + '/employee/deleteEmployeesById.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除从业人员信息
 * 
 * @param id
 */
function deleteEmployee(id) {
	$.messager.confirm("提示信息", message.DELETE_CONFIRM, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/employee/deleteEmployeesById.do',
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
 * 保存从业人员信息
 */
function saveEmployee() {
	if (!$("#saveForm").form("validate")) {
		return;
	}

	$.ajax({
		type : "POST",
		url : basePath + '/employee/saveEmployee.do',
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

function queryEmployee(){
	var name = $("#popName").val();
	var idCard = $("#popIdCard").val();
	var major = $("#popMajor").val();
	var degree = $("#popDegree").combobox('getValue');
	var orgCode = $("#popOrgCode").val();
	var orgName = $("#popOrgName").val();

	$("#name").textbox("setValue", name);
	$("#idCard").textbox("setValue", idCard);
	$("#major").val(major);
	$("#degree").val(degree);
	$("#orgCode").val(orgCode);
	$("#orgName").val(orgName);
	closeWindow();
	findGridData();
}