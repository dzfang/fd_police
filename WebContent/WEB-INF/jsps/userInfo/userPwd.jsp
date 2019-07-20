<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://cxtag.bm.com" prefix="cx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码修改</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/user.js"></script>

<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		userPwdInit();
	})
</script>
</head>

<body>
	<div class="" style="padding: 5px;">
		<form id="saveForm">
			<table class="kv-table">
				<tr>
					<td class="kv-label">新密码 </td>
					<td class="kv-content"><input id="password" name="password" type="password"
						value="${userModel.password }" class="easyui-validatebox textbox"
						data-options="required:true,validType:'password'"
						maxlength="6"></td>
				</tr>
				<tr>
					<td class="kv-label">确认密码 </td>
					<td class="kv-content"><input id="confirm" name="confirm" type="password"
						value="${userModel.confirm }" class="easyui-validatebox textbox"
						data-options="required:true" validType="equalTo['#password']"
						maxlength="6"></td>
				</tr>

			</table>
			<input type="hidden" id="userId" name="userId"
				value="${userModel.userId }">
		</form>
	</div>
	<div id="toolbar" style="text-align: center; padding: 5px;">
		<a href="javascript:void(0);" style="padding: 0px 10px;" id="btnSave"
			class="easyui-linkbutton c0" data-options="iconCls:'icon-ok'">保&nbsp;&nbsp;存</a>
	</div>
</body>
</html>
