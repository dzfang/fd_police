<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色维护</title>
<%@include file="../script.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/role1.js"></script>
<style>
* {
	margin: 0;
	border: 0;
	padding: 0;
}

#nav ul {
	list-style: none;
	margin-left: 5px;
}

#nav li {
	display: block;
	line-height: 30px;
	height: 30px;
	width: 120px;
	overflow: hidden;
	text-overflow: ellipsis;
	float: left;
}

#nav a {
	color: #fff;
	text-decoration: none;
	padding: 20px 20px;
}

#nav a:hover {
	background-color: #060;
}
</style>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		roleAuthSaveInit();
	})
</script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div class="minWidth" data-options="region:'center'" border="false"
			style="padding: 5px;">
			<table class="t1" id="nav" style="width: 100%">

				<c:forEach var="menu" items="${menuList}">
					<c:if test="${menu.parentId==null||menu.parentId=='' }">
						<tr>
							<th colspan="2"
								style="height: 35px; background-color: #1da02b;padding-left:10px;color:#fff"><input
								type="checkbox" menuId="${menu.menuId}" actionId="" dataId=""
								onclick="checkFunc(this)" ${menu.isChecked=='1'?'checked':''} />${menu.menuName}</th>
						</tr>
						<c:forEach var="item" items="${menuList}">
							<c:if test="${item.parentId==menu.menuId }">
								<tr>
									<c:choose>
										<c:when
											test="${item.isPersonal=='0' && item.isOrganization=='0' && item.actionList.size()==0 }">
											<td style="text-indent: 2em; height: 30px; padding: 10px;"
												colspan="2"><input type="checkbox"
												menuId="${item.menuId}" actionId="" dataId=""
												parentId="${menu.menuId }" onclick="checkItem(this)"
												${item.isChecked=='1'?'checked':''} />${item.menuName}</td>
										</c:when>
										<c:otherwise>
											<td style="text-indent: 2em; height: 30px; padding: 10px;"><input
												type="checkbox" menuId="${item.menuId}" actionId=""
												parentId="${menu.menuId }" onclick="checkItem(this)"
												dataId="" ${item.isChecked=='1'?'checked':''} />${item.menuName}</td>
											<td>
												<table style="border: 0px; table-layout: fixed;">
													<c:if
														test="${item.resourceList!=null && item.resourceList.size()>0}">
														<tr>
															<td style="border: 0px;">数据权限：</td>
															<td
																style="border: 0px; white-space: normal; word-wrap: break-word; padding: 10px 0px;">

																<ul>
																	<c:forEach var="resource" items="${item.resourceList}">
																		<li><input type="checkbox" onclick="checkDataOrAction(this)"
																			menuId="${item.menuId}" actionId=""
																			dataId="${resource.dataId}"
																			${resource.isChecked=='1'?'checked':''} />${resource.dataName }</li>
																	</c:forEach>
																</ul>
															</td>
														</tr>
													</c:if>
													<c:if
														test="${item.actionList!=null && item.actionList.size()>0}">
														<tr>
															<td style="border: 0px;">操作权限：</td>
															<td
																style="border: 0px; white-space: normal; word-wrap: break-word; padding: 10px 0px;"><ul>
																	<c:forEach var="action" items="${item.actionList}">
																		<li><input type="checkbox" onclick="checkDataOrAction(this)"
																			menuId="${item.menuId}" actionId="${action.actionId}"
																			dataId="" ${action.isChecked=='1'?'checked':''} />${action.actionName }</li>
																	</c:forEach>
																</ul></td>
														</tr>
													</c:if>
												</table>
											</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</table>
			<input type="hidden" id="roleId" value="${roleId }" />
		</div>
		<div class="minWidth" region="south" border="false"
			style="height: 40px;">
			<table cellspacing="0" cellpadding="0">
				<tr>
					<td><a href="javascript:void(0);" id="btnCheckAll"
						class="easyui-linkbutton btn-separator" iconcls="icon-add"
						plain="true">全选</a></td>
					<td><a href="javascript:void(0);" id="btnUnCheckAll"
						class="easyui-linkbutton btn-separator" iconcls="icon-remove"
						plain="true">全不选</a></td>
					<td><a href="javascript:void(0);" id="btnSaveAuth"
						class="easyui-linkbutton btn-separator" iconcls="icon-save"
						plain="true">保存权限</a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
