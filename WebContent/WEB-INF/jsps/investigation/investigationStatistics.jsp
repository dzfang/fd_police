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
<script type="text/javascript"
	src="<%=contextPath%>/resources/highcharts/highcharts.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/highcharts/modules/exporting.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		investigationStatisticsInit();
	})
</script>
</head>
<body class="easyui-layout">
	<div class="" region="center" style="padding: 5px;" border="false">
		<div style="width: 95%">
			<center>
				<label>请选择年份 </label><input class="easyui-combobox" name="language" id="filingYear" style="width: 80px;"/>
			</center>
		</div>
		<div id="monthStatistics" style="width: 95%"></div>
		<div>
			<div id="foodDrugPieChart" style="width: 95%"></div>
			<div id=""></div>
		</div>
	</div>
</body>
</html>
