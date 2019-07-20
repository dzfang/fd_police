<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>从业企业信息</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/employee.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div class="" region="center" style="padding: 5px;" border="false">
			<form id="saveForm">
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">姓名</td>
							<td class="kv-content"><input id="popName" name="name"
								class="easyui-validatebox textbox"></td>
							<td class="kv-label">身份证号</td>
							<td class="kv-content"><input id="popIdCard" name="idCard"
								class="easyui-validatebox textbox"></td>
						</tr>
						<tr>
							<td class="kv-label">专业</td>
							<td class="kv-content"><input id="popMajor" name="major"
								class="easyui-validatebox textbox"></td>
							<td class="kv-label">学历</td>
							<td class="kv-content"><cx:CXSelect id="popDegree"
									name="degree" codeType="115" style="width: 211px;"></cx:CXSelect></td>
						</tr>
						<tr>
							<td class="kv-label">机构代码</td>
							<td class="kv-content"><input id="popOrgCode" name="orgCode"
								class="easyui-validatebox textbox"></td>
							<td class="kv-label">单位名称</td>
							<td class="kv-content"><input id="popOrgName" name="orgName"
								class="easyui-validatebox textbox"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div region="south" style="text-align: right; padding: 5px;"
			border="false">
			<a href="javascript:void(0);" style="padding: 0px 10px;"
				id="btnClose" class="easyui-linkbutton c0"
				data-options="iconCls:'icon-cancel'">关&nbsp;&nbsp;闭</a>&nbsp;&nbsp;<a
				href="javascript:void(0);" style="padding: 0px 10px;" id="btnPopSearch"
				class="easyui-linkbutton c0" data-options="iconCls:'icon-search'">查&nbsp;&nbsp;询</a>
		</div>
	</div>
	<script type="text/javascript">
		employeeSearchInit();
	</script>
</body>
</html>
