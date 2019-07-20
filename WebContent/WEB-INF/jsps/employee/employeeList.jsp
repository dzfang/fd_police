<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>从业人员信息列表</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/employee.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		employeeListInit();
	})
</script>

</head>
<body class="easyui-layout" style="font-size: 14px;">
	<div class="minWidth" region="north" style="padding: 5px;"
		border="false">
		<form id="queryForm">
			<div class="conditions" style="padding: 5px;">
				<span class="con-span">姓名 </span><input id="name" name="name"
					class="easyui-textbox"
					style="width: 166px; height: 35px; line-height: 35px;"> <span
					class="con-span">身份证号 </span><input id="idCard" name="idCard"
					class="easyui-textbox"
					style="width: 166px; height: 35px; line-height: 35px;"> <a
					id="btnSearch" href="#" class="easyui-linkbutton"
					iconCls="icon-search" data-options="selected:true">查询</a> <a
					id="btnClear" href="#" class="easyui-linkbutton"
					iconCls="icon-reload">重置</a> <a href="#"
					class="easyui-linkbutton more" iconCls="icon-more" id="btnMore">更多</a>
				<a href="<%=contextPath%>/employee/downloadTemplate.do"
					id="btnDownLoad" style="display: none;">下载模板</a>
			</div>
			<input type="hidden" id="major" name="major" /> <input
				type="hidden" id="degree" name="degree" /> <input type="hidden"
				id="orgCode" name="orgCode" /> <input type="hidden"
				id="orgName" name="orgName" />
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
	
	<script type="text/javascript">
		var canEdit = "${cx:permission(action, 'edit')}";
		var canDelete = "${cx:permission(action, 'delete')}";
	</script>
</body>
</html>
