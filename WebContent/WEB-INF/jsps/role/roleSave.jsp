<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色维护</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/role.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div class="" style="padding: 5px;" region="center" border="false">
			<form id="saveForm">
				<table class="t1">
					<tr>
						<th>角色名称：</th>
						<td><input id="popRoleName" name="roleName"
							class="easyui-validatebox textbox"
							value="<c:out value='${roleModel.roleName }'/>"
							data-options="required:true" style="width: 150px;"></td>

						<th>角色描述：</th>
						<td><input id="popRemark" name="remark"
							class="easyui-textbox"
							value="<c:out value='${roleModel.remark }'/>"
							style="width: 150px;"></td>
					</tr>
				</table>
				<input type="hidden" id="popRoleId" name="roleId"
					value="${roleModel.roleId}"> <input type="hidden"
					id="menuIds" name="menuIds" value="${roleModel.menuIds}"> <input
					type="hidden" id="version" name="version"
					value="<fmt:formatDate value='${roleModel.version }' pattern="yyyy-MM-dd HH:mm:ss" />" />

			</form>

			<div id="p" class="easyui-panel" title="菜单列表" style="height: 320px;">
				<ul id="treeUl" class="easyui-tree" data-options="checkbox:true"></ul>
			</div>
		</div>
		<div region="south" style="text-align: right; padding: 5px;"
			border="false">
			<a href="javascript:void(0);" style="padding: 0px 10px;"
				id="btnClose" class="easyui-linkbutton c0"
				data-options="iconCls:'icon-cancel'">关&nbsp;&nbsp;闭</a>&nbsp;&nbsp;<a
				href="javascript:void(0);" style="padding: 0px 10px;" id="btnSave"
				class="easyui-linkbutton c0" data-options="iconCls:'icon-ok'">保&nbsp;&nbsp;存</a>
		</div>
	</div>
	<script type="text/javascript">
		roleSaveInit();
	</script>
</body>
</html>
