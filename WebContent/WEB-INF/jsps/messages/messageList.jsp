<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>站内信列表</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/messages.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		messageListInit();
	})
</script>
</head>
<body class="easyui-layout">
	<div class="minWidth" style="padding: 5px;" region="north"
		border="false">
		<form id="queryForm">
			<table class="t1">
				<tr>
					<th>发送人：</th>
					<td><input id="popUserName" name="userName"
						readonly="readonly" value="${sessionScope.loginInfo.userName}"
						class="easyui-validatebox textbox"
						data-options="disabled:true,editable:false"
						style="width: 150px; border: none;"> <input type="hidden"
						id="createId" name="createId"
						value="${sessionScope.loginInfo.userId}"></td>

					<th>处理状态：</th>
					<td><cx:CXSelect id="processSign" name="processSign"
							codeType="020" style="width:150px"></cx:CXSelect></td>
					<th>发送日期：</th>
					<td><input id="createDate" name="createDate"
						class="easyui-datebox" style="width: 150px;"></td>
				</tr>
			</table>
			<table style="width: 100%">
				<tr>
					<td><div align="right" class="search_btn_div">
							<a id="btnSearch" href="javascript:void(0);"
								class="easyui-linkbutton c0"
								data-options="iconCls:'icon-search'" style="padding-right: 5px;">查&nbsp;&nbsp;询</a>
							<a id="btnClear" href="javascript:void(0);"
								style="margin-left: 5px; margin-right: 5px; padding-right: 5px;"
								class="easyui-linkbutton c0" data-options="iconCls:'icon-clear'">清&nbsp;&nbsp;空</a>
						</div></td>
				</tr>
			</table>

			<input type="hidden" id="createUser" name="createUser"
				value="${userId }"> <input type="hidden" id="orgIds"
				name="orgIds" value="${orgIds }">
		</form>
	</div>
	<div class="minWidth" style="padding: 0 5px;" region="center"
		border="false">
		<table id="dataList" style="width: auto" height="100%"
			class="easyui-datagrid"
			data-options="rownumbers:true,singleSelect:false,toolbar:'#toolbar',checkbox:true">
			<thead>
				<tr>
					<th field="id" width="0%" checkbox="true"></th>
					<th field="userName" width="10%">发送人</th>
					<th field="orgName" width="10%">所属实验室</th>
					<th field="messageDesc" width="45%">消息内容</th>
					<th field="createDate" width="10%"
						data-options="align:'center',formatter:timeFormatter">发送时间</th>
					<th field="processStatus" width="10%" data-options="align:'center'">处理状态</th>
					<th field="operate" width="15%"
						data-options="align:'center',formatter:operateFormatter">操作</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<c:if test="${cx:permission(action, 'add')}">
				<a href="javascript:void(0);" id="btnAdd"
					class="easyui-linkbutton btn-separator" iconcls="icon-add"
					plain="true">新增</a>
			</c:if>
			<c:if test="${cx:permission(action, 'delete')}">
				<a href="javascript:void(0);" id="btnDelete"
					class="easyui-linkbutton btn-separator" iconcls="icon-remove"
					plain="true">删除</a>
			</c:if>
		</div>
	</div>

	<div class="minWidth" region="south" border="false"
		style="height: 33px; padding: 0px 5px;">
		<div class="easyui-panel"
			style="background-color: rgb(244, 244, 244); width: 100%;">
			<div class="easyui-pagination" id="pagination"></div>
		</div>
	</div>
	<script type="text/javascript">
		var canEdit = "${cx:permission(action, 'edit')}";
		var canDelete = "${cx:permission(action, 'delete')}";
	</script>
</body>
</html>
