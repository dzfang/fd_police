<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<form id="saveForm">
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">案卷编号</td>
							<td class="kv-content"><input id="popCaseCode"
								name="caseCode" class="easyui-validatebox textbox"
								style="width: 150px;"></td>
							<%-- <td class="kv-label">案件类型</td>
							<td class="kv-content"><cx:CXSelect id="popCaseType"
									name="caseType" required="true" codeType="100"
									style="width: 161px;"></cx:CXSelect></td> --%>
									
							<td class="kv-label">案件名称</td>
							<td class="kv-content" colspan="3"><input id="popCaseName"
								name="caseName" class="easyui-validatebox textbox"
								style="width: 487px;"></td>
						</tr>
						<tr>
							<%-- <td class="kv-label">涉案物品</td>
							<td class="kv-content"><cx:CXSelect id="popRelatedThings"
									name="relatedThings" codeType="101" style="width: 161px;"></cx:CXSelect></td> --%>
							<td class="kv-label">案件属性</td>
							<td class="kv-content"><cx:CXSelect id="popCaseAttr"
									name="caseAttr" codeType="108" style="width: 161px;"></cx:CXSelect></td>
							<td class="kv-label">发案环节</td>
							<td class="kv-content"><cx:CXSelect id="popCaseLink"
									name="caseLink" codeType="109" style="width:161px;"></cx:CXSelect></td>
							<td class="kv-label">文书号</td>
							<td class="kv-content"><input id="popPunishDoc"
								name="punishDoc" class="easyui-validatebox textbox"
								style="width: 150px;"></td>
						</tr>
						<tr>
							<td class="kv-label">立案时间</td>
							<td class="kv-content"><input id="popFilingTime"
								name="filingTime" class="easyui-datebox" style="width: 161px"></td>
							<td class="kv-label">结案时间</td>
							<td class="kv-content" colspan="3"><input id="popCloseCaseTime"
								name="closeCaseTime" class="easyui-datebox" style="width: 161px"></td>
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
				href="javascript:void(0);" style="padding: 0px 10px;"
				id="btnPopSearch" class="easyui-linkbutton c0"
				data-options="iconCls:'icon-search'">查&nbsp;&nbsp;询</a>
		</div>
	</div>
	<script type="text/javascript">
		inspectionSearchInit();
	</script>
</body>
</html>
