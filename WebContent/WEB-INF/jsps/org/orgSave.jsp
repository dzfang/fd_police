<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>从业企业信息</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/org.js"></script>
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
							<td class="kv-label">机构代码</td>
							<td class="kv-content"><input id="popOrgCode" name="orgCode"
							class="easyui-validatebox textbox" value="${record.orgCode }"
							data-options="required:true" maxlength="50"></td>
							<td class="kv-label">单位名称</td>
							<td class="kv-content"><input id="popOrgName" name="orgName"
							class="easyui-validatebox textbox" value="${record.orgName }"
							data-options="required:true" maxlength="50"></td>
						</tr>
						<tr>
							<td class="kv-label">营业执照</td>
							<td class="kv-content"><input id="popLicense" name="license"
							class="easyui-validatebox textbox" value="${record.license }"
							data-options="required:true" maxlength="50"></td>
							<td class="kv-label">单位类型</td>
							<td class="kv-content"><cx:CXSelect id="popOrgType" name="orgType"
								value="${record.orgType }" codeType="112" style="width: 211px;"></cx:CXSelect></td>
						</tr>
						<tr>
							<td class="kv-label">从业资质</td>
							<td class="kv-content"><input id="popQualified" name="qualified"
							class="easyui-validatebox textbox" value="${record.qualified }"
							data-options="required:true" maxlength="50"></td>
							<td class="kv-label">资质类别</td>
							<td class="kv-content"><cx:CXSelect id="popQualifiedType" name="qualifiedType"
								value="${record.qualifiedType }" codeType="113"
								style="width: 211px;"></cx:CXSelect></td>
						</tr>
						<tr>
							<td class="kv-label">法人代表</td>
							<td class="kv-content" colspan="3"><input id="popCorporationName"
							name="corporationName" class="easyui-validatebox textbox"
							value="${record.corporationName }"
							data-options="required:true" maxlength="50"></td>
						</tr>
					</tbody>
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
	<script type="text/javascript">organizationSaveInit();</script>
</body>
</html>
