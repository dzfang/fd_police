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
								name="caseCode" value="<c:out value='${record.caseCode }'/>"
								class="easyui-validatebox textbox" data-options="required:true"
								style="width: 150px;" maxlength="20"></td>
							<td class="kv-label">案件类别</td>
							<td class="kv-content"><cx:CXSelect id="popCaseType" readonly="true"
									name="caseType" required="true" codeType="100"
									value="${record.caseType }" style="width: 161px;"></cx:CXSelect></td>
							<td class="kv-label">案件名称${caseName }</td>
							<td class="kv-content"><input id="popCaseName"
								name="caseName" value="<c:out value='${record.caseName }'/>"
								class="easyui-validatebox textbox" data-options="required:true"
								style="width: 150px;" maxlength="100"></td>
						</tr>
						<tr>
							<td class="kv-label">涉案物品</td>
							<td class="kv-content"><cx:CXSelect id="popRelatedThings" readonly="true"
									name="relatedThings" required="true" codeType="101" 
									value="${record.relatedThings }" style="width: 161px;"></cx:CXSelect>
									</td>
							<td class="kv-label">案件属性</td>
							<td class="kv-content"><cx:CXSelect id="popCaseAttr"
									name="caseAttr" required="true" codeType="108"
									value="${record.caseAttr }" style="width: 161px;"></cx:CXSelect></td>
							<td class="kv-label">发案环节</td>
							<td class="kv-content"><cx:CXSelect id="popCaseLink"
									name="caseLink" required="true" codeType="109"
									value="${record.caseLink }" style="width:161px;"></cx:CXSelect></td>
						</tr>
						<tr>
							<td class="kv-label">文书号</td>
							<td class="kv-content"><input id="popPunishDoc"
								name="punishDoc" value="<c:out value='${record.punishDoc }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="50"></td>
							<td class="kv-label">处罚单位</td>
							<td class="kv-content"><cx:CXSelect id="popExecOrg"
									name="execOrg" required="true" codeType="106"
									value="${record.execOrg }" style="width:161px;"></cx:CXSelect></td>
							<td class="kv-label">立案时间</td>
							<td class="kv-content"><input id="popFilingTime"
								name="filingTime"
								value="<fmt:formatDate value='${record.filingTime }' pattern='yyyy-MM-dd'/>"
								class="easyui-datebox" 
								style="width: 161px"></td>
						</tr>
						<tr>
							<td class="kv-label">结案时间</td>
							<td class="kv-content"><input id="popCloseCaseTime"
								name="closeCaseTime"
								value="<fmt:formatDate value='${record.closeCaseTime }' pattern='yyyy-MM-dd'/>"
								class="easyui-datebox" style="width: 161px"></td>
							<td class="kv-label">违法企业</td>
							<td class="kv-content"><input id="popOrgName" name="orgName"
								value="<c:out value='${record.orgName }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="50"></td>
							<td class="kv-label">企业代码</td>
							<td class="kv-content"><input id="popOrgCode" name="orgCode"
								value="<c:out value='${record.orgCode }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="50"></td>
						</tr>
						<tr>
							<td class="kv-label">法人姓名</td>
							<td class="kv-content"><input id="popCorporationName"
								name="corporationName"
								value="<c:out value='${record.corporationName }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="50"></td>
							<td class="kv-label">企业地址</td>
							<td class="kv-content"><input id="popOrgAddress"
								name="orgAddress" value="<c:out value='${record.orgAddress }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="100"></td>
							<td class="kv-label">履行方式</td>
							<td class="kv-content"><input id="popPunishExec"
								name="punishExec" value="<c:out value='${record.punishExec }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="200"></td>
						</tr>
						<tr>
							<td class="kv-label">主要负责人</td>
							<td class="kv-content"><input id="popLeader" name="leader"
								value="<c:out value='${record.leader }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="50"></td>
							<td class="kv-label">涉税信息</td>
							<td class="kv-content" colspan="3"><input
								id="popTaxInfo" name="taxInfo"
								value="<c:out value='${record.taxInfo }'/>"
								class="easyui-validatebox textbox" style="width: 484px;"
								maxlength="500"></td>
						</tr>
						<tr>
							<td class="kv-label">处罚日期</td>
							<td class="kv-content"><input id="popPunishDate"
								name="punishDate"
								value="<fmt:formatDate value='${record.punishDate }' pattern='yyyy-MM-dd'/>"
								class="easyui-datebox" style="width: 161px"></td>
							<td class="kv-label">没收价值</td>
							<td class="kv-content"><input id="popConfiscateValue"
								name="confiscateValue" data-options="validType:'money'"
								value="<c:out value='${record.confiscateValue }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="20"></td>
							<td class="kv-label">案件总值</td>
							<td class="kv-content"><input id="popCaseAmount"
								name="caseAmount" data-options="validType:'money'"
								value="<c:out value='${record.caseAmount }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="20"></td>
						</tr>
						<tr>
							<td class="kv-label">销毁价值</td>
							<td class="kv-content"><input id="popDestroyValue"
								name="destroyValue" data-options="validType:'money'"
								value="<c:out value='${record.destroyValue }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="20"></td>
							<td class="kv-label">罚款金额</td>
							<td class="kv-content"><input id="popFine" name="fine"
								data-options="validType:'money'"
								value="<c:out value='${record.fine }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="20"></td>
							<td class="kv-label">票号</td>
							<td class="kv-content"><input id="popBillNo" name="billNo"
								value="<c:out value='${record.billNo }'/>"
								class="easyui-validatebox textbox" style="width: 150px;"
								maxlength="20"></td>
						</tr>
						<tr>
							<td class="kv-label">缴款日期</td>
							<td class="kv-content"><input id="popPaymentTime"
								name="paymentTime"
								value="<fmt:formatDate value='${record.paymentTime }' pattern='yyyy-MM-dd'/>"
								class="easyui-datebox" style="width: 161px"></td>
							<td class="kv-label">案件来源</td>
							<td class="kv-content"><cx:CXSelect id="popCaseSource"
									name="caseSource" codeType="102" value="${record.caseSource }"
									style="width: 161px;"></cx:CXSelect></td>
							<td class="kv-label">来源日期</td>
							<td class="kv-content"><input id="popCaseSourceTime"
								name="caseSourceTime"
								value="<fmt:formatDate value='${record.caseSourceTime }' pattern='yyyy-MM-dd'/>"
								class="easyui-datebox" style="width: 161px"></td>
						</tr>
						<tr>
							<td class="kv-label">违法事实</td>
							<td class="kv-content" colspan="5"
								style="padding-top: 5px; padding-bottom: 5px;"><textarea
									id="popIllegalFacts" name="illegalFacts"
									class="easyui-validatebox textbox"
									style="width: 823px; height: 50px; resize: none"
									maxlength="1000">${record.illegalFacts }</textarea></td>

						</tr>
						<tr>
							<td class="kv-label">处罚依据</td>
							<td class="kv-content" colspan="5"
								style="padding-top: 5px; padding-bottom: 5px;"><textarea
									id="popPunishKind" name="punishKind"
									class="easyui-validatebox textbox"
									style="width: 823px; height: 50px; resize: none"
									maxlength="200">${record.punishKind }</textarea></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" id="popId" name="id" value="${record.id }">
				<input type="hidden" id="popVersion" name="version"
					value="<fmt:formatDate value='${record.version }' pattern='yyyy-MM-dd HH:mm:ss'/>">
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
		inspectionSaveInit();
	</script>
</body>
</html>
