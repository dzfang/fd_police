<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://cxtag.bm.com" prefix="cx"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登陆记录</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/loginRecord.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		loginRecordListInit();
	})
</script>
</head>
<body class="easyui-layout">
	<div class="minWidth" region="north" style="padding: 5px;"
		border="false">
		<form id="queryForm">
			<div class="conditions" style="padding: 5px;">
				<span class="con-span">登录时间 </span><input id="startDate" name="startDate"
						class="easyui-datebox" data-options="editable:false"
						style="width: 150px">--&nbsp;&nbsp;<input id="endDate"
						name="endDate" class="easyui-datebox"
						data-options="editable:false" style="width: 150px"> <a
					id="btnSearch" href="#" class="easyui-linkbutton"
					iconCls="icon-search" data-options="selected:true">查询</a> <a
					id="btnClear" href="#" class="easyui-linkbutton"
					iconCls="icon-reload">重置</a>
			</div>
			<input type="hidden" id="userId" name="userId" value="${createUser }">
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
</body>
</html>
