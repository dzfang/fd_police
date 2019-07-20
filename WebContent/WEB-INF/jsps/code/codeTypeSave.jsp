<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/code.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div region="center" style="padding: 5px;" border="false">
			<form id="saveForm">
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">字典主键</td>
							<td class="kv-content"><input id="popTypeName" name="type"
								value="<c:out value='${codeTypeModel.type }'/>"
								class=" easyui-validatebox textbox" data-options="required:true"
								maxlength="3"></td>
						</tr>
						<tr>
							<td class="kv-label">字典名称</td>
							<td class="kv-content"><input id="popText" name="text"
								value="<c:out value='${codeTypeModel.text }'/>"
								class="easyui-validatebox textbox" data-options="required:true"
								maxlength="50"></td>
						</tr>
					</tbody>
				</table>
				<input id="popOperate" name="operate" type="hidden"
					value="${operate}" />
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
	<script type="text/javascript">
		codeTypeSaveInit();
	</script>
</body>
</html>
