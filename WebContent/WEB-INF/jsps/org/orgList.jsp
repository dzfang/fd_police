<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>从业企业信息列表</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/org.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		organizationListInit();
	})
</script>
</head>
<body class="easyui-layout">
	<div class="minWidth" region="north" style="padding: 5px;"
		border="false">
		<form id="queryForm">
			<div class="conditions" style="padding: 5px;">
				<span class="con-span">机构代码 </span><input id="orgCode"
					name="orgCode" class="easyui-textbox"
					style="width: 166px; height: 35px; line-height: 35px;"> <span
					class="con-span">单位名称 </span><input id="orgName" name="orgName"
					class="easyui-textbox"
					style="width: 166px; height: 35px; line-height: 35px;"> <a
					id="btnSearch" href="#" class="easyui-linkbutton"
					iconCls="icon-search" data-options="selected:true">查询</a> <a
					id="btnClear" href="#" class="easyui-linkbutton"
					iconCls="icon-reload">重置</a> <a href="#" id="btnMore" 
					class="easyui-linkbutton more" iconCls="icon-more">更多</a>
			</div>
			<input type="hidden" id="license" name="license"/> <input type="hidden"
				id="orgType" name="orgType" /> <input type="hidden" id="qualified" name="qualified"/> <input
				type="hidden" id="qualifiedType" name="qualifiedType"/> <input type="hidden"
				id="corporationName" name="corporationName" />
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
