/**
 * 
 */
function codeListInit() {

	// 初始化树
	treeInit();
	// 列表初始化
	var columns = [ [ {
		field : 'type,code1',
		checkbox : true,
	}, {
		field : "code1",
		title : "字典编码",
		width : 100
	}, {
		field : "value",
		title : "字典标签",
		width : 180
	}, {
		field : "extendValue",
		title : "扩展值",
		width : 120
	}, {
		field : "remark",
		title : "备注",
		width : 150
	}, {
		field : "operate",
		title : "操作",
		width : 150,
		formatter : operateFormatter
	} ] ];
	var toolbar = [ {
		text : '新增',
		iconCls : 'icon-add',
		id : 'btnAddd'
	}, {
		text : '删除',
		iconCls : 'icon-remove',
		id : 'btnDeletes'
	} ]
	// 列表初始化
	dataGridInit(columns, toolbar);
	// 添加类型
	$("#btnAddd").bind("click", openCodeDateAddWindow);
	// 添加类型
	$("#btnAdd").bind("click", openCodeTypeAddWindow);
	// 编辑
	$("#btnEdit").bind("click", openCodeTypeEditWindow);
	// 删除类别信息
	$("#btnDelete").bind("click", deleteCodeType);
	// 删除码表信息
	$("#btnDeletes").bind("click", deleteCodeDates);
	// 默认加载数据
	findGridData();

}
/**
 * 菜单树初始化
 */
function treeInit() {
	var url = basePath + '/codeType/getAllCodeTypes.do';
	$('#treeUl').tree({
		checkbox : false,
		url : url,
		onLoadSuccess : function() {
		},
		onClick : function(node) {
			// 判断是否是叶子节点
			if ($('#treeUl').tree('isLeaf', node.target)) {
				$("#type").val(node.id);
				findGridData();
			} else {
				$("#type").val("");
				findGridData();
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
 * 类型新增画面初始化
 */
function openCodeTypeAddWindow() {
	var href = basePath + '/codeType/codeTypeInit.do';
	openWindow("新增字典", href, 400, 220);
}
/**
 * 码表新增画面初始化
 */
function openCodeDateAddWindow() {
	var href = basePath + '/codeData/addInit.do';
	openWindow("新增标签", href, 500, 360);
}
/**
 * 打开编辑画面
 * 
 * @param type
 */
function openCodeTypeEditWindow(type) {
	var type = $("#treeUl").tree("getSelected");
	if (type != null) {
		var href = basePath + '/codeType/updateInit.do?type=' + type.id;
		openWindow("编辑字典", href, 400, 220);
	}

}
/**
 * 类型新增画面保存和关闭
 */
function codeTypeSaveInit() {
	// 初始化类别名称下拉列表
	comboboxTypeInit();
	// 保存添加的码表信息
	$("#btnSaves").bind("click", saveCodeData);
	// 保存添加的类型
	$("#btnSave").bind("click", saveCodeType);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);
	// 使IE浏览器下，回车可以换行
	$("textarea").focus(function() {
		$(this).css("white-space", "pre-wrap");
	})
}
/**
 * 保存添加的类型信息
 */
function saveCodeType() {
	if (!$("#saveForm").form("validate")) {
		return;
	}

	$.ajax({
		type : "POST",
		url : basePath + '/codeType/saveCodeType.do',
		data : $("#saveForm").serialize(),

		success : function(data) {
			if (data != null) {
				$.messager.alert("提示信息", message.byId(data), "info");
				if (data == "SAVE_SUCCESS") {
					// 编辑完成，直接关闭
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
 * 保存添加的码表信息
 */
function saveCodeData() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : basePath + '/codeData/saveCodeData.do',
		data : $("#saveForm").serialize(),

		success : function(data) {
			if (data != null) {
				$.messager.alert("提示信息", message.byId(data), "info");
				if (data == "SAVE_SUCCESS") {
					// 编辑完成，直接关闭
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
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 删除类别
 * 
 * @param type
 */
function deleteCodeType() {
	var type = $("#treeUl").tree("getSelected");
	if (type != null) {
		var msg = message.DELETE_INFORMATION_CONFIRM;

		$.messager.confirm("提示信息", msg, function(r) {
			if (r) {
				$.ajax({
					type : "POST",
					url : basePath + '/codeType/deleteCodeTypeById.do',
					data : {
						'type' : type.id
					},
					success : function(data) {
						if (data != null) {
							$.messager
									.alert("提示信息", message.byId(data), "info");
							if (data == "DELETE_SUCCESS") {
								// 编辑完成，直接关闭
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
}
/**
 * 批量删除码表
 */
function deleteCodeDates() {
	var type = "";
	var code = "";
	$($('#dataList').datagrid('getSelections')).each(function() {

		type += "" + $(this).attr("type") + ",";
		code += "" + $(this).attr("code1") + ",";

	});
	if (type.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_DELETE_DATA);
		return;
	}
	$.messager.confirm("提示信息", message.BATCH_DELETE_CODE_CONFIRM, function(r) {
		if (r) {
			var param = {
				typeArray : [ type.substring(0, type.length - 1) ],
				codeArray : [ code.substring(0, code.length - 1) ]
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
		url : basePath + '/codeData/deleteCodeDataByTypes.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}
/**
 * 格式化操作
 */
function operateFormatter(value, row, index) {
	var edit = "<a href='javascript:void(0);' class='l-btn-selected btn-sm' onclick='openCodeDataEditWindow(\""
			+ row.type + "\",\"" + row.code1 + "\")'>修改</a>&nbsp;&nbsp;";
	var del = "<a href='javascript:void(0);' class='l-btn-danger btn-sm' onclick='deleteCodedata(\""
			+ row.type + "\",\"" + row.code1 + "\")'>删除</a>&nbsp;&nbsp;";
	return edit + del;
}
/**
 * 删除会员
 * 
 * @param type
 */
function deleteCodedata(type, code1) {
	var msg = message.DELETE_INFORMATION_CONFIRM;
	$.messager.confirm("提示信息", msg, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/codeData/deleteCodeDataByType.do',
				data : {
					'type' : type,
					'code1' : code1
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
 * 打开修改画面
 * 
 * @param type
 */
function openCodeDataEditWindow(type, code1) {
	var href = basePath + '/codeData/updateInit.do?type=' + type + '&code1='
			+ code1;
	openWindow("编辑类别", href, 500, 360);
}
/**
 * 类型下拉列表
 */
function comboboxTypeInit() {
	var combobox = $("#popType");
	combobox.combobox({
		url : basePath + '/codeType/getAllCodeType.do',
		valueField : 'type',
		textField : 'text',
		onLoadSuccess : function() {
			var value = $("#hidTypeId").val();
			if (value != "") {
				combobox.combobox('setValue', value);
			}
			// 设置下拉列表空白项高度
			$(".combobox-item").height("16px");
		}
	});
}

// }
// }});
