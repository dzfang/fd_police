<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/role1.js"></script>

<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		roleList1Init();
	})
</script>
</head>
<body>
	<div class="easyui-layout" style="margin: 5px;" fit="true">
		<div data-options="region:'east',split:false" style="width: 5px;"
			border="false">&nbsp;</div>

		<div data-options="region:'west',split:true" title="角色列表"
			style="width: 270px;">
			<div class="easyui-panel" style="margin: 5px; width: 260px;"
				border="false">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td><a href="javascript:void(0);" id="btnAddRole"
							class="easyui-linkbutton btn-separator" iconcls="icon-add"
							plain="true">新增</a></td>
						<td><a href="javascript:void(0);" id="btnEditRole"
							class="easyui-linkbutton btn-separator" iconcls="icon-edit"
							plain="true">编辑</a></td>
						<td><a href="javascript:void(0);" id="btnDeleteRole"
							class="easyui-linkbutton btn-separator" iconcls="icon-remove"
							plain="true">删除</a></td>
					</tr>
				</table>
			</div>
			<ul id="treeUl" class="easyui-tree" data-options="checkbox:false"></ul>
			<input type="hidden" id="roleId" value="${sessionScope.loginInfo.roleId}"/>
		</div>

		<div data-options="region:'center',title:'功能列表'" style="overflow:hidden;">
			<iframe id="funcFrame" width="100%" height="100%" frameborder="0"
				scrolling="auto" ></iframe>
		</div>
	</div>
</body>
</html>
