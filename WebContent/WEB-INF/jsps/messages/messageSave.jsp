<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>站内信</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/messages.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		messageSaveInit();
	});
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div class="" region="center" style="padding: 5px;" border="false">
			<form id="saveForm">
				<table class="t1">
					<tr>
						<th>消息内容：</th>
						<td colspan="3" style="padding-top: 5px; padding-bottom: 5px;">
							<textarea id="popMessageDesc" name="messageDesc"
								class="easyui-validatebox textbox" data-options="required:true"
								style="width: 447px; height: 150px; resize: none" maxlength="300">${record.messageDesc }</textarea>
						</td>

					</tr>
				</table>
				<input type="hidden" id="popId" name="id" value="${record.id }">
			</form>
		</div>
		<div region="south" style="text-align: right; padding: 5px;"
			border="false">
			<a href="javascript:void(0);" style="padding: 0px 10px;"
				id="btnClose" class="easyui-linkbutton c0"
				data-options="iconCls:'icon-cancel'">关&nbsp;&nbsp;闭</a>&nbsp;&nbsp;<a
				href="javascript:void(0);" style="padding: 0px 10px;" id="btnSave"
				class="easyui-linkbutton c0" data-options="iconCls:'icon-ok'">保&nbsp;&nbsp;存</a>
		</div>
	</div>
</body>
</html>
