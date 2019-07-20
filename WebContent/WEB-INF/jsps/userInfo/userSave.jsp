<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户基本信息维护</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/user.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div class="" region="center" style="padding: 5px;" border="false">
			<form id="saveForm">
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">用户名</td>
							<td class="kv-content"><input id="popLoginId" name="loginId"
								value="<c:out value='${userModel.loginId }'/>"
								class="easyui-validatebox textbox"
								data-options="required:true,validType:'loginId'" maxlength="20"></td>
							<td class="kv-label">姓名</td>
							<td class="kv-content"><input id="popUserName"
								name="userName" value="<c:out value='${userModel.userName }'/>"
								class="easyui-validatebox textbox" data-options="required:true"
								maxlength="20"></td>
						</tr>
						<tr>
							<td class="kv-label">性别</td>
							<td class="kv-content"><cx:CXSelect id="popUserSex"
									style="width:211px;" name="userSex" codeType="002"
									value="${userModel.userSex }" required="true"></cx:CXSelect></td>
							<td class="kv-label">出生日期</td>
							<td class="kv-content"><input id="popBirthday"
								name="birthday" class="easyui-datebox" style="width: 211px;"
								data-options="editable:false" value="${userModel.birthday }"></td>
						</tr>
						<tr>
							<td class="kv-label">手机号</td>
							<td class="kv-content"><input id="popTelephone"
								name="telephone"
								value="<c:out value='${userModel.telephone }'/>"
								class="easyui-validatebox textbox"
								data-options="validType:'mobile'" maxlength="11"></td>
							<td class="kv-label">邮箱</td>
							<td class="kv-content"><input id="popEmail" name="email"
								value="<c:out value='${userModel.email }'/>"
								class="easyui-validatebox textbox"
								data-options="validType:'email'" maxlength="50"></td>
						</tr>
						<tr>
							<td class="kv-label">角色</td>
							<td class="kv-content" colspan="3"><c:choose>
									<c:when test="${canChangeRole }">
										<input class="easyui-combotree" id="popRoleId" name="roleId"
											value="${userModel.roleId }" style="width: 211px;"
											roleName="${userModel.roleName }"
											data-options="required:true">
									</c:when>
									<c:otherwise>${userModel.roleName }
									<input type="hidden" id="popRoleId" name="roleId"
											value="${userModel.roleId }">

									</c:otherwise>
								</c:choose> <input type="hidden" id="canChangeRole"
								value="${canChangeRole }"></td>
						</tr>
					</tbody>
				</table>

				<input type="hidden" id="popUserId" name="userId"
					value="${userModel.userId }"> <input type="hidden"
					id="popUserType" name="userType" value="${userModel.userType }">
				<input type="hidden" id="roleId"
					value="${sessionScope.loginInfo.roleId}" />
			</form>
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
		userSaveInit();
	</script>
</body>
</html>
