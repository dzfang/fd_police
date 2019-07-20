<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://cxtag.bm.com" prefix="cx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档搜索</title>
<%@include file="../script.jsp"%>
<style>
.container {
	width: 100%;
	height: 80px;
	text-align: center;
}

.center {
	display: inline-block;
	border: 0px solid #fff;
	padding-top: 200px;
}

.center {
	_display: inline;
} /*针对ie6 hack*/
</style>
<script>
	var basePath = "${pageContext.request.contextPath}";
	function doSearch(){
		var keywords = $('#keywords').val();
		if($.trim(keywords)!=""){
			$("#queryForm").submit();
		}
	}
</script>
</head>

<body class="easyui-layout">
	<div class="minWidth container" style="padding: 5px;" region="center">
		<div class="center">
		<form id="queryForm" action="<%=contextPath%>/document/searchFile.do" method="post">
			<table>
				<tr>
					<td style="height: 40px;"><input class="easyui-textbox" name="keywords" id="keywords"
						style="width: 400px;" /></td>
					<td><a id="btnSearch" href="#" class="easyui-linkbutton" onclick="doSearch();"
						 data-options="selected:true">搜索一下</a></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>
