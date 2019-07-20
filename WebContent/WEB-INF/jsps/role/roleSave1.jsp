<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色维护</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/role1.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div region="center" style="padding: 5px;" border="false">
			<form id="saveForm">
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">角色名称</td>
							<td class="kv-content"><input id="popRoleName"
								name="roleName" value="<c:out value='${record.roleName }'/>"
								class=" easyui-validatebox textbox" data-options="required:true"
								maxlength="20"></td>
						</tr>
					</tbody>
				</table>
				<input id="popRoleId" name="roleId" type="hidden"
					value="${record.roleId}" /> <input id="popParentId"
					name="parentId" type="hidden" value="${record.parentId}" /> <input
					type="hidden" id="version" name="version"
					value="<fmt:formatDate value='${record.version }' pattern="yyyy-MM-dd HH:mm:ss" />" />
			</form>
		</div>
		<div region="south" style="text-align: right; padding: 5px;"
			border="false">
			<a href="javascript:void(0);" style="padding: 0px 10px;"
				id="btnCloseRole" class="easyui-linkbutton c0"
				data-options="iconCls:'icon-cancel'">关&nbsp;&nbsp;闭</a>&nbsp;&nbsp;<a
				href="javascript:void(0);" style="padding: 0px 10px;"
				id="btnSaveRole" class="easyui-linkbutton c0"
				data-options="iconCls:'icon-ok'">保&nbsp;&nbsp;存</a>
		</div>
	</div>
	<script type="text/javascript">
		roleSaveInit();
	</script>
</body>
</html>
