<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>食品药品稽查行政处罚案件列表</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/investigation.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		investigationListInit();
	})
</script>
</head>
<body class="easyui-layout">
	<div class="minWidth" region="north" style="padding: 5px;"
		border="false">
		<form id="queryForm">
			<div class="conditions" style="padding: 5px;">
				<span class="con-span">案卷编号 </span><input id="caseCode"
					name="caseCode" class="easyui-textbox"
					style="width: 166px; height: 35px; line-height: 35px;"> <span
					class="con-span">案件名称 </span><input id="caseName" name="caseName"
					class="easyui-textbox"
					style="width: 166px; height: 35px; line-height: 35px;"> <a
					id="btnSearch" href="#" class="easyui-linkbutton"
					iconCls="icon-search" data-options="selected:true">查询</a> <a
					id="btnClear" href="#" class="easyui-linkbutton"
					iconCls="icon-reload">重置</a> <a href="#"
					class="easyui-linkbutton more" iconCls="icon-more"
					id="btnMore">更多</a> <a
					href="<%=contextPath%>/investigation/downloadTemplate.do"
					id="btnDownLoad" style="display: none;">下载模板</a>
			</div>
			<input type="hidden" id="caseType" name="caseType" value="${record.caseType}" /> <input
				type="hidden" id="relatedThings" name="relatedThings" value="${record.relatedThings}" /> <input
				type="hidden" id="caseArea" name="caseArea" /> <input type="hidden"
				id="casePlace" name="casePlace" /><input type="hidden"
				id="undertakeOrg" name="undertakeOrg" /><input type="hidden"
				id="filingTime" name="filingTime" /><input type="hidden"
				id="closeCaseTime" name="closeCaseTime" />
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
