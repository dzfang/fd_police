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
	src="<%=contextPath%>/resources/js/jsps/investigation.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
</script>
<style type="text/css">
#dg {
	color: #000;
}
</style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div class="" region="center" style="padding: 5px;" border="false">
			<form id="saveForm">
				<table class="kv-table">
					<tr>
						<td class="kv-label">案卷编号：</td>
						<td class="kv-content"><input id="popCaseCode"
							name="caseCode" value="<c:out value='${record.caseCode }'/>"
							class="easyui-validatebox textbox" data-options="required:true"
							style="width: 150px;" maxlength="20"></td>
						<td class="kv-label">案件类别：</td>
						<td class="kv-content"><cx:CXSelect id="popCaseType"
								name="caseType" required="true" codeType="100" readonly="true"
								value="${record.caseType }" style="width: 161px;"></cx:CXSelect></td>
						<td class="kv-label">案件名称：</td>
						<td class="kv-content"><input id="popCaseName"
							name="caseName" value="<c:out value='${record.caseName }'/>"
							class="easyui-validatebox textbox" data-options="required:true"
							style="width: 150px;" maxlength="100"></td>
					</tr>
					<tr>
						<td class="kv-label">案件来源：</td>
						<td class="kv-content"><cx:CXSelect id="popCaseSource"
								name="caseSource" codeType="102" value="${record.caseSource }"
								style="width: 161px;"></cx:CXSelect></td>

						<td class="kv-label">承办人：</td>
						<td class="kv-content"><input id="popUndertakePerson"
							name="undertakePerson"
							value="<c:out value='${record.undertakePerson }'/>"
							class="easyui-validatebox textbox" style="width: 150px;"
							maxlength="20"></td>

						<td class="kv-label">承办单位：</td>
						<td class="kv-content"><input id="popUndertakeOrg"
							name="undertakeOrg"
							value="<c:out value='${record.undertakeOrg }'/>"
							class="easyui-validatebox textbox" style="width: 150px;"
							maxlength="20"></td>
					</tr>
					<tr>
						<td class="kv-label">涉案物品：</td>
						<td class="kv-content"><cx:CXSelect id="popRelatedThings"
								name="relatedThings" codeType="101" readonly="true"
								value="${record.relatedThings }" style="width: 161px;"></cx:CXSelect>
						</td>
						<td class="kv-label">涉案价值：</td>
						<td class="kv-content"><input id="popRelatedValue"
							name="relatedValue" data-options="validType:'money',"
							value="<c:out value='${record.relatedValue }'/>"
							class="easyui-validatebox textbox"
							style="width: 150px; text-align: right;" maxlength="20"></td>
						<td class="kv-label">受案时间：</td>
						<td class="kv-content"><input id="popCaseTime"
							name="caseTime"
							value="<fmt:formatDate value='${record.caseTime }' pattern='yyyy-MM-dd'/>"
							class="easyui-datebox" data-options="editable:false"
							style="width: 161px"></td>
					</tr>
					<tr>
						<td class="kv-label">发案地域：</td>
						<td class="kv-content"><cx:CXSelect id="popCaseArea"
								name="caseArea" codeType="103" value="${record.caseArea }"
								style="width:161px;"></cx:CXSelect></td>
						<td class="kv-label">发案处所：</td>
						<td class="kv-content"><cx:CXSelect id="popCasePlace"
								name="casePlace" codeType="104" value="${record.casePlace }"
								style="width:161px;"></cx:CXSelect></td>
						<td class="kv-label">发案地址：</td>
						<td class="kv-content"><input id="popCaseAddress"
							name="caseAddress"
							value="<c:out value='${record.caseAddress }'/>"
							class="easyui-validatebox textbox" style="width: 150px;"
							maxlength="100"></td>
					</tr>
					<tr>
						<td class="kv-label">违法事实：</td>
						<td class="kv-content" colspan="5"
							style="padding-top: 5px; padding-bottom: 5px;"><textarea
								id="popIllegalFacts" name="illegalFacts"
								class="easyui-validatebox textbox"
								style="width: 823px; height: 50px; resize: none"
								maxlength="1000">${record.illegalFacts }</textarea></td>
					</tr>
					<tr>
						<td class="kv-label">立案时间：</td>
						<td class="kv-content"><input id="popFilingTime"
							name="filingTime"
							value="<fmt:formatDate value='${record.filingTime }' pattern='yyyy-MM-dd'/>"
							class="easyui-datebox" data-options="required:true"
							style="width: 161px"></td>
						<td class="kv-label">刑事拘留开始日期：</td>
						<td class="kv-content"><input id="popCrimnalStart"
							name="crimnalStart"
							value="<fmt:formatDate value='${record.crimnalStart }' pattern='yyyy-MM-dd'/>"
							class="easyui-datebox" style="width: 161px"></td>
						<td class="kv-label">结束日期：</td>
						<td class="kv-content"><input id="popCrimnalEnd"
							name="crimnalEnd"
							value="<fmt:formatDate value='${record.crimnalEnd }' pattern='yyyy-MM-dd'/>"
							class="easyui-datebox" style="width: 161px"></td>

					</tr>
					<tr>
						<td class="kv-label">监视居住处所：</td>
						<td class="kv-content"><input id="popMonitorPlace"
							name="monitorPlace"
							value="<c:out value='${record.monitorPlace }'/>"
							class="easyui-validatebox textbox" style="width: 150px;"
							maxlength="100"></td>
						<td class="kv-label">监视居住开始日期：</td>
						<td class="kv-content"><input id="popMonitorStart"
							name="monitorStart"
							value="<fmt:formatDate value='${record.monitorStart }' pattern='yyyy-MM-dd'/>"
							class="easyui-datebox" style="width: 161px;" maxlength="200"></td>
						<td class="kv-label">结束日期：</td>
						<td class="kv-content" colspan="3"><input id="popMonitorEnd"
							name="monitorEnd"
							value="<fmt:formatDate value='${record.monitorEnd }' pattern='yyyy-MM-dd'/>"
							class="easyui-datebox" style="width: 161px;" maxlength="200"></td>
					</tr>
					<tr>
						<td class="kv-label">取保候审日期：</td>
						<td class="kv-content"><input id="popBailTime"
							name="bailTime"
							value="<fmt:formatDate value='${record.bailTime }' pattern='yyyy-MM-dd'/>"
							class="easyui-datebox" style="width: 161px;" maxlength="200"></td>
						<td class="kv-label">逮捕日期：</td>
						<td class="kv-content" colspan="3"><input id="popArrestDate"
							name="arrestDate"
							value="<fmt:formatDate value='${record.arrestDate }' pattern='yyyy-MM-dd'/>"
							class="easyui-datebox" style="width: 161px;" maxlength="200"></td>
					</tr>
					<tr>
						<td class="kv-content" id="fb" colspan="6"
							style="padding: 1px; height: 205px">
							<table id="dg" class="easyui-datagrid" title="涉案人员信息"
								data-options="rownumbers:true,singleSelect:false,fit:true">
								<thead>
									<tr>
										<th data-options="field:'id',width:'5%',checkbox:true">
										</td>
										<th
											data-options="field:'name',width:'10%',editor:{type:'textbox' ,options:{required:true,validType:'maxlength[20]'}}">姓名
										
										</td>
										<th
											data-options="field:'idCard',width:'18%',align:'left',editor:{type:'textbox',options:{required:true,validType:'maxlength[18]'}}">身份证
										
										</td>
										<th
											data-options="field:'birthday',width:'12%',editor:{type:'datebox',options:{required:true,formatter:dateFormatter}}">出生日期
										
										</td>
										<th
											data-options="field:'age',width:'7%',align:'right',editor:{type:'numberbox',options:{required:true,validType:'maxlength[3]'}}">年龄
										
										</td>
										<th
											data-options="field:'gender',width:'7%',editor:{type:'combobox',options:{panelHeight:'auto',
											valueField:'code1',textField:'value',method:'get',url:'<%=contextPath%>/codeData/getCodeDataList.do?type=002',required:true}}">性别
										
										</td>
										<th
											data-options="field:'address',width:'20%',editor:{type:'textbox',options:{validType:'maxlength[100]'}}">家庭住址
										
										</td>
										<th
											data-options="field:'relatedKind',width:'10%',editor:{type:'combobox',options:{panelHeight:'auto',
											valueField:'code1',textField:'value',method:'get',url:'<%=contextPath%>/codeData/getCodeDataList.do?type=111',required:true}}">与案件关系
										
										</td>
										<th
											data-options="field:'pledgeType',width:'10%',editor:{type:'combobox',options:{panelHeight:'auto',
											valueField:'code1',textField:'value',method:'get',url:'<%=contextPath%>/codeData/getCodeDataList.do?type=105',required:true}}">保证形式
										
										</td>
									</tr>
								</thead>
							</table> <!-- <div id="toolbar1">
								<a href="javascript:void(0);" id="btnAddRow"
									class="easyui-linkbutton btn-separator"
									data-options="iconCls:'icon-add',plain:'true'">添加</a>&nbsp;&nbsp;<a
									href="javascript:void(0);" id="btnDeleteRow"
									class="easyui-linkbutton btn-separator"
									data-options="iconCls:'icon-remove',plain:'true'">删除</a>
							</div> -->
						</td>
					</tr>
					<tr>
						<td class="kv-label">涉案单位：</td>
						<td class="kv-content"><input id="popRelateOrg"
							name="relatedOrg" value="<c:out value='${record.relatedOrg }'/>"
							class="easyui-validatebox textbox" style="width: 150px;"
							maxlength="100"></td>
						<td class="kv-label">延长羁押：</td>
						<td class="kv-content"><c:choose>
								<c:when test="${record.extendDetention}">
									<input id="popExtendDetention1" type="radio" checked="checked"
										name="extendDetention" class="easyui-validatebox" value="1" />
									<label>是</label>
									<input id="popExtendDetention2" type="radio"
										name="extendDetention" class="easyui-validatebox" value="0" />
									<label>否</label>
								</c:when>
								<c:otherwise>
									<input id="popExtendDetention1" type="radio"
										name="extendDetention" class="easyui-validatebox" value="1" />
									<label>是</label>
									<input id="popExtendDetention2" type="radio" checked="checked"
										name="extendDetention" class="easyui-validatebox" value="0" />
									<label>否</label>
								</c:otherwise>
							</c:choose></td>
						<td class="kv-label">判罚刑期：</td>
						<td class="kv-content"><input id="popTerm" name="term"
							value="<c:out value='${record.term }'/>"
							class="easyui-validatebox textbox" style="width: 150px;"
							maxlength="100"></td>
					</tr>
					<tr>
						<td class="kv-label">罚金：</td>
						<td class="kv-content"><input id="popFine" name="fine"
							data-options="validType:'money'"
							value="<c:out value='${record.fine }'/>"
							class="easyui-validatebox textbox"
							style="width: 150px; text-align: right;" maxlength="20"></td>
						<td class="kv-label">免于起诉：</td>
						<td class="kv-content"><c:choose>
								<c:when test="${record.prosecution}">
									<input id="popProsecution1" type="radio" checked="checked"
										name="prosecution" class="easyui-validatebox" value="1" />
									<label>是</label>
									<input id="popProsecution2" type="radio" name="prosecution"
										class="easyui-validatebox" value="0" />
									<label>否</label>
								</c:when>
								<c:otherwise>
									<input id="popProsecution1" type="radio" name="prosecution"
										class="easyui-validatebox" value="1" />
									<label>是</label>
									<input id="popProsecution2" type="radio" checked="checked"
										name="prosecution" class="easyui-validatebox" value="0" />
									<label>否</label>
								</c:otherwise>
							</c:choose></td>
						<td class="kv-label">撤销日期：</td>
						<td class="kv-content"><input id="popPevocationDate"
							name="pevocationDate"
							value="<fmt:formatDate value='${record.pevocationDate }' pattern='yyyy-MM-dd'/>"
							class="easyui-datebox" style="width: 161px;" maxlength="200"></td>
					</tr>
				</table>
				<input type="hidden" id="popId" name="id" value="${record.id }">
				<input type="hidden" id="personJson" name="personJson"> <input
					type="hidden" id="popVersion" name="version"
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
		investigationSaveInit();
	</script>
</body>
</html>
