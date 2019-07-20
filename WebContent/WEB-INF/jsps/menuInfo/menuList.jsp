<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://cxtag.bm.com" prefix="cx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/main.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	/**
	 * 页面加载初始化
	 */
	$(function() {
		menuListInit();
	})
	/**
	 * 用户列表画面初始化
	 */
	function menuListInit() {
		$('#popupDataList').datagrid({
			//选择checkbox则选择行
			checkOnSelect : true,
			//选择行则选择checkbox
			selectOnCheck : true,
			//当表格成功加载时执行          
			onLoadSuccess : function(row) {
				var rowData = row.rows;
				//遍历JSON
				$.each(rowData, function(idx, val) {
					if (isNull(val.userId) != '') {
						//如果数据行为已选中则选中改行
						$("#popupDataList").datagrid("selectRow", idx);
					}
				});
			}
		});

		// 关闭
		$("#btnPopupClose").bind("click", popupClose);
		// 确定
		$("#btnPopupConfirm").bind("click", menuSelectedConfirm);
		// 默认加载数据
		loadPopupData();

	}

	/**
	 * 加载APP用户数据
	 * 
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 */
	function loadPopupData() {
		var roleId = $("#roleId").val();
		$.ajax({
			type : "POST",
			url : basePath + '/admin/getSubmenuByRoleId.do',
			data : {
				'roleId' : roleId
			},
			beforeSend : onBeforeSend,
			success : function(data) {
				$('#popupDataList').datagrid('loadData', data.rows);
				$("input:checkbox").css("border", "none");
			},
			error : doError,
			complete : onComplete
		});
	}
	/**
	 * 将选择的数据带回到父画面
	 */
	function menuSelectedConfirm() {
		var row = $('#popupDataList').datagrid('getSelections');
		var menuId = "";
		$($('#popupDataList').datagrid('getSelections')).each(function() {
			if (menuId != "") {
				menuId += ",";
			}
			menuId += $(this).attr("menuId");
		});

		$
				.ajax({
					type : "POST",
					url : basePath + '/admin/insertPersonalMenu.do',
					data : {
						idArray : [ menuId ]
					},
					success : function(data) {
						if (data != null) {
							if (data == "SAVE_SUCCESS") {

								/* $.messager.alert('提示信息', message.byId(data),
										"info"); */

								setDefaultContent();
								popupClose();
							} else {
								$.messager.alert("提示信息", message.byId(data),
										"warning");
							}
						}
					},
					error : doError,
					beforeSend : onBeforeSend,
					complete : onComplete
				});
	}
	/**
	 * 关闭弹出画面
	 */
	function popupClose() {
		parent.closeWindow();
	}

	function idFormatter(value, row, index) {
		return row.menuId;
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div class="" style="padding: 5px;" region="center" border="false">
			<table id="popupDataList" width="100%" height="100%"
				class="easyui-datagrid" style="border-color: red"
				data-options="rownumbers:true,singleSelect:false">
				<thead>
					<tr>
						<th field="menuId" checkbox="true"></th>
						<th field="id" width="100" data-options="formatter:idFormatter">菜单ID</th>
						<th field="menuName" width="282">菜单名称</th>
					</tr>
				</thead>
			</table>
		</div>
		<div region="south" border="false"
			style="height: 40px; padding: 0px 5px;">
			<div align="center" class="search_btn_div">
				<a id="btnPopupClose" href="javascript:void(0);"
					style="padding: 0 10px" class="easyui-linkbutton c8">关&nbsp;&nbsp;闭</a>&nbsp;&nbsp;<a
					id="btnPopupConfirm" href="javascript:void(0);"
					style="padding: 0 10px; margin-left: 5px; margin-right: 5px;"
					class="easyui-linkbutton c8">确&nbsp;&nbsp;定</a>
			</div>
		</div>
	</div>
	<input type="hidden" id="roleId" name="roleId" value="${roleId }">
</body>
</html>
