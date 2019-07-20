<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>食品药品稽查行政处罚案件维护</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/inspection.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div class="" region="center" style="padding: 5px;" border="false">
			<form id="saveForm" action="<%=contextPath%>/inspection/importExcel.do" method="post"
				enctype="multipart/form-data">
				<table class="t1">
					<tr>
						<th>文件路径：</th>
						<td><input id="file" name="file" class="easyui-filebox"
							style="width: 447px;height: 35px; line-height: 35px;"  /></td>
					</tr>
				</table>
			</form>
		</div>
		<div region="south" style="text-align: right; padding: 5px;"
			border="false">
			<a href="javascript:void(0);" style="padding: 0px 10px;"
				id="btnClose" class="easyui-linkbutton c0"
				data-options="iconCls:'icon-cancel'">关&nbsp;&nbsp;闭</a>&nbsp;&nbsp;<a
				href="javascript:void(0);" style="padding: 0px 10px;" id="btnSave" onclick="$('#saveForm').submit();"
				class="easyui-linkbutton c0" data-options="iconCls:'icon-ok'">导&nbsp;&nbsp;入</a>
		</div>
	</div>
	<script type="text/javascript">inspectionImportInit();</script>
</body>
</html>
