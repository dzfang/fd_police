<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/role.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		roleListInit();
	})
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div class="minWidth" style="padding: 5px;" region="north" border="false">
			<form id="queryForm">
				<table class="t1">
					<tr>
						<th>角色名称：</th>
						<td><input id="roleName" name="roleName"
							class="easyui-textbox" style="width: 150px;"></td>
					</tr>
				</table>
				<table style="width: 100%">
					<tr>
						<td><div align="right" class="search_btn_div">
								<a id="btnSearch" href="javascript:void(0);"
									class="easyui-linkbutton c0"
									data-options="iconCls:'icon-search'"
									style="padding-right: 5px;">查&nbsp;&nbsp;询</a> <a id="btnClear"
									href="javascript:void(0);"
									style="margin-left: 5px; margin-right: 5px; padding-right: 5px;"
									class="easyui-linkbutton c0"
									data-options="iconCls:'icon-clear'">清&nbsp;&nbsp;空</a>
							</div></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="minWidth" style="padding: 0 5px;" region="center" border="false">
			<table id="dataList" width="100%" height="100%"
				class="easyui-datagrid"
				data-options="rownumbers:true,singleSelect:false,toolbar:'#toolbar',checkbox:true">
				<thead>
					<tr>
						<th field="roleId" width="0%" checkbox="true"></th>
						<th field="roleName" width="30%">角色名称</th>
						<th field="remark" width="55%">角色描述</th>
						<th field="operate" width="15%"
							data-options="align:'center',field:'loginId',formatter:operateFormatter">操作</th>
					</tr>
				</thead>
			</table>
			<div id="toolbar">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td><a href="javascript:void(0);" id="btnAdd"
							class="easyui-linkbutton btn-separator" iconcls="icon-add"
							plain="true">新增</a></td>
						<td>
							<div class="datagrid-btn-separator"></div>
						</td>
						<td><a href="javascript:void(0);" id="btnDelete"
							class="easyui-linkbutton btn-separator" iconcls="icon-remove"
							plain="true">删除</a></td>
					</tr>
				</table>
			</div>
		</div>

		<div class="minWidth" region="south" border="false"
			style="height: 33px; padding: 0px 5px;">
			<div class="easyui-panel"
				style="background-color: rgb(244, 244, 244);width: 100%;">
				<div class="easyui-pagination" id="pagination"></div>
			</div>
		</div>
	</div>
</body>
</html>
