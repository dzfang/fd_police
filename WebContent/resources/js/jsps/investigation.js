/**
 * 食品药品犯罪侦查案件列表画面初始化
 */
function investigationListInit() {
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : "caseCode",
		title : "案卷编号",
		width : 130
	}, {
		field : "caseTypeName",
		title : "案件类别",
		width : 90
	}, {
		field : "caseName",
		title : "案件名称",
		width : 150
	}, {
		field : "filingTime",
		title : "立案时间",
		width : 80,
		formatter : dateFormatter
	}, {
		field : "undertakeOrg",
		title : "承办单位",
		width : 120
	}, {
		field : "relatedThingsName",
		title : "涉案物品",
		width : 120
	}, {
		field : "caseAddress",
		title : "发案地址",
		width : 90
	}, {
		field : "caseAreaName",
		title : "发案地域",
		width : 100
	}, {
		field : "casePlaceName",
		title : "发案处所",
		width : 100
	}, {
		field : "undertakePerson",
		title : "承办人",
		width : 80
	}, {
		field : "relatedValue",
		title : "涉案价值（元）",
		align : "right",
		width : 90
	}, {
		field : "caseTime",
		title : "受案时间",
		width : 80,
		formatter : dateFormatter
	}, {
		field : "caseSourceName",
		title : "案件来源",
		width : 120
	}, {
		field : "illegalFacts",
		title : "违法事实",
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
	$("#btnClear").bind("click", investigationFormReset);
	// 添加食品药品犯罪侦查案件
	$("#btnAdd").bind("click", openAddInvestigationWindow);
	// 批量删除食品药品犯罪侦查案件
	$("#btnDelete").bind("click", deleteInvestigations);
	// 添加食品药品犯罪侦查案件
	$("#btnImport").bind("click", openImportInvestigationWindow);
	// 高级查询
	$("#btnMore").bind("click", openSearchWindow);
	// 默认加载数据
	findGridData();
}

/**
 * 食品药品犯罪侦查案件保存画面初始化
 */
function investigationSaveInit() {
	$("#dg").datagrid({
		toolbar : [ {
			text : '新增',
			iconCls : 'icon-add',
			id : 'btnAddRow',
			handler : function() {
				addGridRow();
			}
		}, {
			text : '删除',
			iconCls : 'icon-remove',
			id : 'btnDeleteRow',
			handler : function() {
				deleteRow();
			}
		} ]
	});
	// 添加食品药品犯罪侦查案件
	$("#btnSave").bind("click", saveInvestigation);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);
	/*
	 * // 添加行 $("#btnAddRow").bind("click", addGridRow); // 删除行
	 * $("#btnDeleteRow").bind("click", deleteRow);
	 */

	queryRelatedPerson();
}

/**
 * 高级查询
 */
function investigationSearchInit() {
	// 查询食品药品稽查行政处罚案件
	$("#btnPopSearch").bind("click", queryInspection);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);
}

function addGridRow() {
	$('#dg').datagrid('appendRow', {
		name : '',
		idCard : '',
		birthday : '',
		age : '',
		gender : '',
		address : '',
		relatedKind : '',
		pledgeType : ''
	});
	var editIndex = $('#dg').datagrid('getRows').length - 1;
	var h = parseInt($("#fb").css("height")) + 35;
	$("#fb").css("height", h + "px");
	$('#dg').datagrid('resize', {
		height : h
	});
	$('#dg').datagrid('beginEdit', editIndex);
	var editors = $('#dg').datagrid('getEditors', editIndex);
	var idCard = editors[1];
	editors[4].target.combobox({
		editable : false
	});
	editors[6].target.combobox({
		editable : false
	});
	editors[7].target.combobox({
		editable : false
	});
	idCard.target.textbox({
		onChange : function(newValue, oldValue) {
			if ($.trim(newValue).length < 18) {
				return;
			}
			if (IdCardValid(newValue)) {
				var sex = newValue.substr(16);
				if (sex % 2 == 0) {
					editors[4].target.combobox('setValue', '002');
				} else {
					editors[4].target.combobox('setValue', '001');
				}
				var birthday = newValue.substr(6, 8);
				var day = birthday.substr(0, 4) + "-" + birthday.substr(4, 2)
						+ "-" + birthday.substr(7, 2);
				editors[2].target.datebox('setValue', day);
				var age = getAge(day);
				editors[3].target.textbox('setValue', age);
			} else {
				$.messager.alert('提示信息', message.ID_CARD_ERROR, "info");
			}
		}
	});
}
function deleteRow() {
	var rows = $('#dg').datagrid('getSelections');
	var h = parseInt($("#fb").css("height"));
	for (var i = 0; i < rows.length; i++) {
		var rowIndex = $('#dg').datagrid('getRowIndex', rows[i]);
		$('#dg').datagrid('deleteRow', rowIndex);
		h = parseInt($("#fb").css("height")) - 35;
		$("#fb").css("height", h + "px");
	}

}

function queryRelatedPerson() {
	var caseId = $("#popId").val();
	if (caseId == "") {
		return;
	}
	$.ajax({
		type : "POST",
		url : basePath + '/investigation/queryRelatedPerson.do?caseId='
				+ caseId,
		success : function(data) {
			if (data != null) {
				for (var i = 0; i < data.rows.length; i++) {
					var r = data.rows[i];
					$('#dg').datagrid('appendRow', {
						name : r.name,
						idCard : r.idCard,
						birthday : dateFormatter(r.birthday),
						age : r.age,
						gender : r.gender,
						address : r.address,
						relatedKind : r.relatedKind,
						pledgeType : r.pledgeType
					});
					var editIndex = $('#dg').datagrid('getRows').length - 1;
					var h = parseInt($("#fb").css("height")) + 25;
					$("#fb").css("height", h + "px");
					$('#dg').datagrid('resize', {
						height : h
					});
					$('#dg').datagrid('beginEdit', editIndex);
					var editors = $('#dg').datagrid('getEditors', editIndex);
					editors[4].target.combobox({
						editable : false
					});
					editors[4].target.combobox("setValue", r.gender);
					editors[6].target.combobox({
						editable : false
					});
					editors[6].target.combobox("setValue", r.relatedKind);
					editors[7].target.combobox({
						editable : false
					});
					editors[7].target.combobox("setValue", r.pledgeType);
				}
			}
		},
		error : doError
	});

}
/**
 * 食品药品犯罪侦查案件保存画面初始化
 */
function investigationImportInit() {
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
		url : basePath + '/investigation/getInvestigationList.do?pageIndex='
				+ pageIndex + '&pageSize=' + pageSize,
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
		url : basePath + '/investigation/getInvestigationList.do?pageIndex='
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
function investigationFormReset() {
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
	$("#caseArea").val('');
	$("#casePlace").val('');
	$("#undertakeOrg").val('');
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
		result += "<a href='javascript:void(0);' class='l-btn-selected btn-sm' onclick='openEditInvestigationWindow(\""
				+ row.id + "\")'>编辑</a>&nbsp;&nbsp;";
	}
	if (canDelete == "true") {
		result += "<a href='javascript:void(0);' class='l-btn-danger btn-sm' onclick='deleteInvestigation(\""
				+ row.id + "\")'>删除</a>&nbsp;&nbsp;";
	}
	if (result == "") {
		$("#dataList").datagrid('hideColumn', 'operate');
	}
	return result;
}

/**
 * 食品药品犯罪侦查案件添加画面初始化
 */
function openAddInvestigationWindow() {
	var relatedThings = $("#relatedThings").val();
	var caseType = $("#caseType").val();
	var href = basePath + '/investigation/addInvestigation.do?relatedThings='
			+ relatedThings + '&caseType=' + caseType;
	openWindow("新增食品药品犯罪侦查案件", href, 1024, 550);
}

/**
 * 打开食品药品犯罪侦查案件编辑画面
 */
function openEditInvestigationWindow(id) {
	var href = basePath + '/investigation/getInvestigationById.do?id=' + id;
	openWindow("编辑食品药品犯罪侦查案件", href, 1024, 550);
}

/**
 * 打开高级查询画面
 */
function openSearchWindow() {
	var href = basePath + '/investigation/investigationSearch.do';
	openWindow("高级查询", href, 1024, 265);
}

/**
 * 打开导入画面
 */
function openImportInvestigationWindow() {
	var href = basePath + '/investigation/importInit.do';
	openWindow("导入食品药品犯罪侦查案件", href, 630, 140);
}
/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 批量删除食品药品犯罪侦查案件
 */
function deleteInvestigations() {
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
		url : basePath + '/investigation/deleteInvestigationsById.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除食品药品犯罪侦查案件
 * 
 * @param id
 */
function deleteInvestigation(id) {
	var msg = message.DELETE_EQUIPMENT_CONFIRM;
	$.messager.confirm("提示信息", message.DELETE_CONFIRM, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/investigation/deleteInvestigationsById.do',
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
 * 保存食品药品犯罪侦查案件
 */
function saveInvestigation() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	var rows = $('#dg').datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
	}
	var array = new Array()
	for (var i = 0; i < rows.length; i++) {
		var item = new Object();
		item.name = rows[i].name, item.idCard = rows[i].idCard,
				item.birthday = rows[i].birthday, item.age = rows[i].age,
				item.gender = rows[i].gender, item.address = rows[i].address,
				item.relatedKind = rows[i].relatedKind,
				item.pledgeType = rows[i].pledgeType
		array.push(item);
	}
	$("#personJson").val(JSON.stringify(array));
	$.ajax({
		type : "POST",
		url : basePath + '/investigation/saveInvestigation.do',
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

function investigationStatisticsInit() {
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
		url : basePath + '/investigation/getInvestigationCountByYear.do?year='
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
					text : filingYear + '年度每月食品药品犯罪侦查案件统计'
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
	//var caseType = $("#popCaseType").combobox('getValue');
	var caseName = $("#popCaseName").val();
	// var relatedThings = $("#popRelatedThings").combobox('getValue');
	var caseArea = $("#popCaseArea").combobox('getValue');
	var casePlace = $("#popCasePlace").combobox('getValue');
	var undertakeOrg = $("#popUndertakeOrg").val();
	var filingTime = $("#popFilingTime").datebox('getValue');
	var closeCaseTime = $("#popCloseCaseTime").datebox('getValue');

	$("#caseCode").textbox("setValue", caseCode);
	$("#caseName").textbox("setValue", caseName);
	//$("#caseType").val(caseType);
	// $("#relatedThings").val(relatedThings);
	$("#caseArea").val(caseArea);
	$("#casePlace").val(casePlace);
	$("#undertakeOrg").val(undertakeOrg);
	$("#filingTime").val(filingTime);
	$("#closeCaseTime").val(closeCaseTime);
	closeWindow();
	findGridData();
}