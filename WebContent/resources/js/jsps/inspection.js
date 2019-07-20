/**
 * 食品药品稽查行政处罚案件列表画面初始化
 */
function inspectionListInit() {
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : "caseCode",
		title : "案卷编号",
		width : 70
	}, {
		field : "caseTypeName",
		title : "案件类别",
		width : 70
	}, {
		field : "caseName",
		title : "案件名称",
		width : 200
	}, {
		field : "relatedThingsName",
		title : "涉案物品",
		width : 70
	}, {
		field : "caseAttrName",
		title : "案件属性",
		width : 90
	}, {
		field : "caseLinkName",
		title : "案件环节",
		width : 100
	}, {
		field : "filingTime",
		title : "立案时间",
		width : 80,
		formatter : dateFormatter
	}, {
		field : "closeCaseTime",
		title : "结案时间",
		width : 80,
		formatter : dateFormatter
	}, {
		field : "punishDoc",
		title : "文书号",
		width : 100
	}, {
		field : "execOrgName",
		title : "处罚单位",
		width : 150
	}, {
		field : "orgName",
		title : "违法企业",
		width : 200
	}, {
		field : "caseAmount",
		title : "案件总值（元）",
		align : "right",
		width : 90
	}, {
		field : "caseSourceName",
		title : "案件来源",
		width : 100
	}, {
		field : "illegalFacts",
		title : "违法事实",
		width : 300
	}, {
		field : "punishKind",
		title : "处罚依据",
		width : 300
	}, {
		field : "punishExec",
		title : "履行方式和期限",
		width : 300
	}, {
		field : "punishDate",
		title : "处罚日期",
		width : 80,
		formatter : dateFormatter
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
	$("#btnClear").bind("click", inspectionFormReset);
	// 添加食品药品稽查行政处罚案件
	$("#btnAdd").bind("click", openAddInspectionWindow);
	// 批量删除食品药品稽查行政处罚案件
	$("#btnDelete").bind("click", deleteInspections);
	// 添加食品药品稽查行政处罚案件
	$("#btnImport").bind("click", openImportInspectionWindow);
	// 高级查询
	$("#btnMore").bind("click", openSearchWindow);
	// 默认加载数据
	findGridData();
}

/**
 * 食品药品稽查行政处罚案件保存画面初始化
 */
function inspectionSaveInit() {
	// 添加食品药品稽查行政处罚案件
	$("#btnSave").bind("click", saveInspection);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);
}

/**
 * 高级查询
 */
function inspectionSearchInit() {
	// 查询食品药品稽查行政处罚案件
	$("#btnPopSearch").bind("click", queryInspection);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);
}

/**
 * 食品药品稽查行政处罚案件保存画面初始化
 */
function inspectionImportInit() {
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
		url : basePath + '/inspection/getInspectionList.do?pageIndex='
				+ pageIndex + '&pageSize=' + pageSize,
		data : $("#queryForm").serialize(),
		beforeSend : onBeforeSend,
		success : function(data) {
			$('#dataList').datagrid('loadData', data.rows);
			// var pager = $("#dataList").datagrid('getPager');
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
		url : basePath + '/inspection/getInspectionList.do?pageIndex='
				+ pageIndex + '&pageSize=' + pageSize,
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
function inspectionFormReset() {
	$("#caseCode").textbox('setValue', '');
	$("#caseName").textbox('setValue', '');
	// 清空高级查询
	searchClear();
}
/**
 * 清空高级查询
 */
function searchClear() {
	// $("#caseType").val('');
	// $("#relatedThings").val('');
	$("#caseAttr").val('');
	$("#caseLink").val('');
	$("#punishDoc").val('');
	$("#filingTime").val('');
	$("#closeCaseTime").val('');
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
		result += "<a href='javascript:void(0);' class='l-btn-selected btn-sm' onclick='openEditInspectionWindow(\""
				+ row.id + "\")'>编辑</a>&nbsp;&nbsp;";
	}
	if (canDelete == "true") {
		result += "<a href='javascript:void(0);' class='l-btn-danger btn-sm' onclick='deleteInspection(\""
				+ row.id + "\")'>删除</a>&nbsp;&nbsp;";
	}
	if (result == "") {
		$("#dataList").datagrid('hideColumn', 'operate');
	}
	return result;
}

/**
 * 食品药品稽查行政处罚案件添加画面初始化
 */
function openAddInspectionWindow() {
	var relatedThings = $("#relatedThings").val();
	var caseType = $("#caseType").val();
	var href = basePath + '/inspection/addInspection.do?relatedThings='
			+ relatedThings + '&caseType=' + caseType;
	openWindow("新增食品药品稽查行政处罚案件", href, 1024, 550);
}

/**
 * 打开食品药品稽查行政处罚案件编辑画面
 * 
 * @param inspectionId
 */
function openEditInspectionWindow(id) {
	var href = basePath + '/inspection/getInspectionById.do?id=' + id;
	openWindow("编辑食品药品稽查行政处罚案件", href, 1024, 550);
}
/**
 * 打开高级查询画面
 * 
 * @param organizationId
 */
function openSearchWindow() {
	var href = basePath + '/inspection/inspectionSearch.do';
	openWindow("高级查询", href, 1024, 265);
}
/**
 * 打开导入画面
 * 
 * @param organizationId
 */
function openImportInspectionWindow() {
	var href = basePath + '/inspection/importInit.do';
	openWindow("导入食品药品稽查行政处罚案件", href, 630, 140);
}
/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 批量删除食品药品稽查行政处罚案件
 */
function deleteInspections() {
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
				idArray : [ id.substring(0, id.length - 1) ],
				reason : r
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
		url : basePath + '/inspection/deleteInspectionsById.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除食品药品稽查行政处罚案件
 * 
 * @param id
 */
function deleteInspection(id) {
	var msg = message.DELETE_EQUIPMENT_CONFIRM;
	$.messager.confirm("提示信息", message.DELETE_CONFIRM, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/inspection/deleteInspectionsById.do',
				data : {
					idArray : [ id ],
					reason : r
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
 * 保存食品药品稽查行政处罚案件
 */
function saveInspection() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : basePath + '/inspection/saveInspection.do',
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

function inspectionStatisticsInit() {
	$('#filingYear').combobox({
		url : basePath + '/codeData/getConstantDataByType.do?type=A001',
		valueField : 'code1',
		textField : 'value',
		editable : false,
		onSelect : function(record) {
			changeFilingYear();
		},
		onLoadSuccess : function() {
			changeFilingYear();
		}
	});
}

function changeFilingYear() {
	var filingYear = $("#filingYear").combobox("getValue");
	$.ajax({
		type : "POST",
		url : basePath + '/inspection/getInspectionCountByYear.do?year='
				+ filingYear,
		success : function(data) {
			var foodData = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
			var drugData = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
			if (data != null) {
				for (var i = 0; i < data.rows.length; i++) {
					var r = data.rows[i];
					if (r.relatedThings == "001") {
						foodData[r.filingMonth - 1] = r.filingCount;
					} else {
						drugData[r.filingMonth - 1] = r.filingCount;
					}
				}
			}
			loadMonthStatistics(filingYear, foodData, drugData);
			loadFoodDrugPieChart(filingYear, foodData, drugData);
		},
		error : doError
	});

}
function loadMonthStatistics(filingYear, foodData, drugData) {
	$('#monthStatistics').highcharts(
			{
				chart : {
					type : 'column'
				},
				title : {
					text : filingYear + '年度每月食品药品稽查行政处罚案件统计'
				},
				subtitle : {
					text : '按月度，分案件类型统计'
				},
				xAxis : {
					title : {
						text : '月份'
					},
					categories : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月',
							'8月', '9月', '10月', '11月', '12月' ],
					crosshair : true
				},
				yAxis : {
					title : {
						text : '数量'
					},
					min : 0
				},
				series : [ {
					name : '食品类',
					data : foodData
				}, {
					name : '药品类',
					data : drugData
				} ]
			});

}

function loadFoodDrugPieChart(filingYear, foodData, drugData) {
	var foodCount = 0;
	var drugCount = 0;
	for (var i = 0; i < foodData.length; i++) {
		foodCount += foodData[i];
	}
	for (var i = 0; i < drugData.length; i++) {
		drugCount += drugData[i];
	}

	var chart = {
		plotBackgroundColor : null,
		plotBorderWidth : null,
		plotShadow : false
	};
	var title = {
		text : filingYear + '食品药品案件占有比例'
	};
	// 鼠标移动时显示的信息
	var tooltip = {
		pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
	};
	// 圆饼图旁边的信息
	var plotOptions = {
		pie : {
			allowPointSelect : true,
			cursor : 'pointer',
			dataLabels : {
				enabled : true,
				format : '<b>{point.name}%</b>: {point.percentage:.1f} %',
				style : {
					color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
							|| 'black'
				}
			}
		}
	};
	var series = [ {
		type : 'pie',
		name : '食品药品案件占比',
		data : [ [ '食品类', foodCount ], [ '药品类', drugCount ] ]
	} ];

	var json = {};
	json.chart = chart;
	json.title = title;
	json.tooltip = tooltip;
	json.series = series;
	json.plotOptions = plotOptions;
	$('#foodDrugPieChart').highcharts(json);
};
/**
 * 高级查询
 */
function queryInspection() {
	var caseCode = $("#popCaseCode").val();
	//var caseType = $("#popCaseType").val();
	var caseName = $("#popCaseName").val();
	// var relatedThings = $("#popRelatedThings").combobox('getValue');
	var caseAttr = $("#popCaseAttr").combobox('getValue');
	var caseLink = $("#popCaseLink").combobox('getValue');
	var punishDoc = $("#popPunishDoc").val();
	var filingTime = $("#popFilingTime").datebox('getValue');
	var closeCaseTime = $("#popCloseCaseTime").datebox('getValue');

	$("#caseCode").textbox("setValue", caseCode);
	$("#caseName").textbox("setValue", caseName);
	//$("#caseType").val(caseType);
	// $("#relatedThings").val(relatedThings);
	$("#caseAttr").val(caseAttr);
	$("#caseLink").val(caseLink);
	$("#punishDoc").val(punishDoc);
	$("#filingTime").val(filingTime);
	$("#closeCaseTime").val(closeCaseTime);
	closeWindow();
	findGridData();
}