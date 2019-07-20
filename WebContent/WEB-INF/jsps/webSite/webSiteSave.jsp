<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站信息</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/webSite.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		webSiteSaveInit();
	});
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div class="" region="center" style="padding: 5px;" border="false">
			<form id="saveForm">
				<table class="t1">
					<tr>
						<th>网站名称&nbsp;</th>
						<td><input id="logoTitle" name="logoTitle"
							value="<c:out value='${record.logoTitle }'/>"
							class="easyui-validatebox textbox" data-options="required:true"
							style="width: 250px;" maxlength="20"></td>
					</tr>
				</table>
				<div style="text-align: center;">
					<a href="javascript:void(0);" style="padding: 0px 10px;"
						id="btnSave" class="easyui-linkbutton c0"
						data-options="iconCls:'icon-ok'">保&nbsp;&nbsp;存</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
