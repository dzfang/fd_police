<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://cxtag.bm.com" prefix="cx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档上传</title>
<%@include file="../script.jsp"%>

<script>
	var basePath = "${pageContext.request.contextPath}";
	function doUpload() {
		var fileInput = $('#file').filebox('getValue');
		if (fileInput != "") {
			$("#saveForm").submit();
		} else {
			$.messager.alert('提示信息', message.PLS_SELECT_FILE, "info");
		}
	}
</script>
</head>

<body>
	<div class="" style="padding: 5px;">
		<form id="saveForm" action="<%=contextPath%>/document/uploadFile.do"
			method="post" enctype="multipart/form-data">
			<table class="kv-table">
				<tr>
					<td class="kv-label">文件</td>
					<td class="kv-content"><input id="file" name="file"
						style="width: 400px;" class="easyui-filebox"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="toolbar" style="text-align: center; padding: 5px;">
		<a href="javascript:void(0);" style="padding: 0px 10px;" id="btnSave"
			onclick="doUpload();" class="easyui-linkbutton c0"
			data-options="iconCls:'icon-ok'">上&nbsp;&nbsp;传</a>
	</div>
</body>
</html>
