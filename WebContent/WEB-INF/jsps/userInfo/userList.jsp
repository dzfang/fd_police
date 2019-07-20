<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师列表</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/user.js"></script>
<script>
	$(function() {
		userListInit();
	})
</script>
</head>
<body class="easyui-layout">
	<div class="minWidth" region="north" style="padding: 5px;"
		border="false">
		<form id="queryForm">
			<input type="hidden" id="userType" name="userType"
				value="${userType }">
			<div class="conditions" style="padding: 5px;">
				<span class="con-span">用户ID </span><input class="easyui-textbox"
					type="text" name="loginId" id="loginId"
					style="width: 166px; height: 35px; line-height: 35px;"></input> <span
					class="con-span">姓名 </span><input class="easyui-textbox"
					type="text" name="userName" id="userName"
					style="width: 166px; height: 35px; line-height: 35px;"></input> <a
					id="btnSearch" href="#" class="easyui-linkbutton"
					iconCls="icon-search" data-options="selected:true">查询</a> <a
					id="btnClear" href="#" class="easyui-linkbutton"
					iconCls="icon-reload">重置</a>
			</div>
			<input type="hidden" id="orgIds" name="orgIds" value="${orgIds }">
		</form>
	</div>
	<div class="minWidth" style="padding: 1px;" region="center"
		border="false">
		<table id="dataList" style="height:100%;"></table>
	</div>
	<div class="minWidth" region="south" style="padding: 0px;"
		border="false">
		<div class="easyui-pagination" id="pagination"></div>
	</div>
	
	<input type="hidden" id="hidRoleId"
		value="${sessionScope.loginInfo.roleId}" />
	<input type="hidden" id="hidChildRoleIdString"
		value="${sessionScope.loginInfo.childRoleIdString}" />
	<script type="text/javascript">
		var canEdit = "${cx:permission(action, 'edit')}";
		var canDelete = "${cx:permission(action, 'delete')}";
		var basePath = "${pageContext.request.contextPath}";
	</script>
</body>
</html>
