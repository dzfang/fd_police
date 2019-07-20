<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>从业企业信息</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/company.js"></script>
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
							<td class="kv-label">许可证号</td>
							<td class="kv-content"><input id="popLicense" name="license"
							class="easyui-validatebox textbox" value="${record.license }"
							data-options="required:true" maxlength="50"></td>
							<td class="kv-label">单位名称</td>
							<td class="kv-content"><input id="popCompanyName" name="companyName"
							class="easyui-validatebox textbox" value="${record.companyName }"
							data-options="required:true" maxlength="100"></td>
						</tr>
						<tr>
							<td class="kv-label">法人</td>
							<td class="kv-content"><input id="popLegalPerson" name="legalPerson"
							class="easyui-validatebox textbox" value="${record.legalPerson }"
							maxlength="20"></td>
							<td class="kv-label">负责人</td>
							<td class="kv-content"><input id="popDirector" name="director"
							class="easyui-validatebox textbox" value="${record.director }"
							maxlength="20"></td>
						</tr>
						<tr>
							<td class="kv-label">业主</td>
							<td class="kv-content"><input id="popOwner" name="owner"
							class="easyui-validatebox textbox" value="${record.owner }"
							maxlength="20"></td>
							<td class="kv-label">联系电话</td>
							<td class="kv-content"><input id="popPhone" name="phone"
							class="easyui-validatebox textbox" value="${record.phone }"
							maxlength="20"></td>
						</tr>
						<tr>
							<td class="kv-label">地址</td>
							<td class="kv-content" colspan="3"><input id="popAddress"
							name="address" class="easyui-validatebox textbox" style="width:592px;"
							value="${record.address }" maxlength="200"></td>
						</tr>
						<tr>
							<td class="kv-label">备注</td>
							<td class="kv-content" colspan="3"><input id="popRemark"
							name="remark" class="easyui-validatebox textbox" style="width:592px;"
							value="${record.remark }" maxlength="500"></td>
						</tr>
						<tr>
							<td class="kv-label">类别</td>
							<td class="kv-content"><cx:CXSelect id="popCompanyType" name="companyType"
								value="${record.companyType }" codeType="116"
								style="width: 211px;"></cx:CXSelect></td>
							<td class="kv-label">发证时间</td>
							<td class="kv-content"><input id="popIssueDate"
								name="issueDate"
								value="<fmt:formatDate value='${record.issueDate }' pattern='yyyy-MM-dd'/>"
								class="easyui-datebox" 
								style="width: 211px"></td>
						</tr>
						<tr>
							<td class="kv-label">委托代理人</td>
							<td class="kv-content"><input id="popAgent" name="agent"
							class="easyui-validatebox textbox" value="${record.agent }"
							maxlength="20"></td>
							<td class="kv-label">联系电话</td>
							<td class="kv-content"><input id="popAgentPhone" name="agentPhone"
								class="easyui-validatebox textbox" value="${record.agentPhone }" maxlength="20"></td>
						</tr>
						<tr>
							<td class="kv-label">备注</td>
							<td class="kv-content" colspan="3"><cx:CXSelect id="popHandleType" name="handleType"
								value="${record.handleType }" codeType="117"
								style="width: 211px;"></cx:CXSelect></td>
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
	<script type="text/javascript">companySaveInit();</script>
</body>
</html>
