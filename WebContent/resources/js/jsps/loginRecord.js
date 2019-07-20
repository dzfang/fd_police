 
/**
 * 后台用户登录记录列表页面初始化
 */
function loginRecordListInit(){
	var columns = [ [ {
		field : "loginId",
		title : "用户ID",
		width : 200
	}, {
		field : "userName",
		title : "姓名",
		width : 200
	},{
		field : "loginDate",
		title : "登录时间",
		width : 250,
		formatter:timeFormatter
	} ] ];
	// 列表初始化
	dataGridInit(columns);
	// 查询
	$("#btnSearch").bind("click", findGridData);
	// 清空表单数据
	$("#btnClear").bind("click", formReset);
	// 默认加载数据
	findGridData();
}

/**
 * 清空查询区域
 */
function formReset() {
	$("#loginId").textbox('setValue', '');
	$("#userName").textbox('setValue', '');
	$("#startDate").textbox('setValue', '');
	$("#endDate").textbox('setValue', '');

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
		url : basePath + '/loginRecord/getLoginRecordList.do?pageIndex=' + pageIndex
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