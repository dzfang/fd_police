<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/code.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		codeListInit();
	})
</script>
</head>
<body>
	<div class="easyui-layout" style="margin: 5px;" fit="true">
		<div data-options="region:'east',split:false" style="width: 10px;"
			border="false">&nbsp;</div>

		<div data-options="region:'west',split:true" title="字典管理"
			style="width: 270px;">
			<div class="easyui-panel" style="margin: 5px; width: 250px;"
				border="false">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td><a href="javascript:void(0);" id="btnAdd"
							class="easyui-linkbutton" iconcls="icon-add" plain="true"
							style="padding: 0 10px;">新增</a></td>
						<td><a href="javascript:void(0);" id="btnEdit"
							class="easyui-linkbutton" iconcls="icon-edit" plain="true"
							style="padding: 0 10px;">编辑</a></td>
						<td><a href="javascript:void(0);" id="btnDelete"
							class="easyui-linkbutton" iconcls="icon-remove" plain="true"
							style="padding: 0 10px;">删除</a></td>
					</tr>
				</table>
			</div>
			<ul id="treeUl" class="easyui-tree" data-options="checkbox:false"></ul>
		</div>

		<div data-options="region:'center',title:'字典标签'">
			<div class="easyui-layout" fit="true">
				<div class="minWidth" style="padding: 1px;" data-options="region:'center'" border="false">
					<form id="queryForm">
						<input type="hidden" id="type" name="type">
					</form>
					<table id="dataList" style="height: 100%;"></table>
				</div>
				<div class="minWidth" region="south" style="padding: 0px;"
					border="false">
					<div class="easyui-pagination" id="pagination"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
