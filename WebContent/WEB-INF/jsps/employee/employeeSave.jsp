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
								class="easyui-validatebox textbox" value="${record.name }"
								data-options="required:true" maxlength="20"></td>
							<td class="kv-label">身份证号</td>
							<td class="kv-content"><input id="popIdCard" name="idCard"
								class="easyui-validatebox textbox" value="${record.idCard }"
								data-options="required:true" maxlength="18"></td>
						</tr>
						<tr>
							<td class="kv-label">性别</td>
							<td class="kv-content"><cx:CXSelect id="popGender"
									name="gender" required="true" value="${record.gender }"
									codeType="002" style="width: 211px;"></cx:CXSelect></td>
							<td class="kv-label">出生日期</td>
							<td class="kv-content"><input id="popBirthday"
								name="birthday" class="easyui-datebox" style="width: 211px;"
								value="<fmt:formatDate value='${record.birthday }' pattern='yyyy-MM-dd'/>"
								data-options="required:true" maxlength="50"></td>
						</tr>
						<tr>
							<td class="kv-label">籍贯</td>
							<td class="kv-content"><input id="popBirthPlace"
								name="birthPlace" class="easyui-validatebox textbox"
								value="${record.birthPlace }" maxlength="50"></td>
							<td class="kv-label">民族</td>
							<td class="kv-content"><cx:CXSelect id="popNation"
									name="nation" value="${record.nation }" codeType="114"
									style="width: 211px;"></cx:CXSelect></td>
						</tr>
						<tr>
							<td class="kv-label">毕业院校</td>
							<td class="kv-content"><input id="popSchool" name="school"
								class="easyui-validatebox textbox" value="${record.school }"
								maxlength="50"></td>
							<td class="kv-label">专业</td>
							<td class="kv-content"><input id="popMajor" name="major"
								class="easyui-validatebox textbox" value="${record.major }"
								maxlength="50"></td>
						</tr>
						<tr>
							<td class="kv-label">学历</td>
							<td class="kv-content"><cx:CXSelect id="popDegree"
									name="degree" required="true" value="${record.degree }"
									codeType="115" style="width: 211px;"></cx:CXSelect></td>
							<td class="kv-label">职称</td>
							<td class="kv-content"><input id="popPosition"
								name="position" class="easyui-validatebox textbox"
								value="${record.position }" maxlength="50"></td>
						</tr>
						<tr>
							<td class="kv-label">住址</td>
							<td class="kv-content"><input id="popAddress" name="address"
								class="easyui-validatebox textbox" value="${record.address }"
								data-options="required:true" maxlength="100"></td>
							<td class="kv-label">联系电话</td>
							<td class="kv-content"><input id="popTelephone"
								name="telephone" class="easyui-validatebox textbox"
								value="${record.telephone }" data-options="required:true"
								maxlength="20"></td>
						</tr>
						<tr>
							<td class="kv-label">机构代码</td>
							<td class="kv-content"><input id="popOrgCode" name="orgCode"
								class="easyui-validatebox textbox" value="${record.orgCode }"
								maxlength="50"></td>
							<td class="kv-label">单位名称</td>
							<td class="kv-content"><input id="popOrgName" name="orgName"
								class="easyui-validatebox textbox" value="${record.orgName }"
								data-options="required:true"
								maxlength="100"></td>
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
	<script type="text/javascript">
		employeeSaveInit();
	</script>
</body>
</html>
