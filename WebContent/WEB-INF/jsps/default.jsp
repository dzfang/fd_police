<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>首页</title>
<%
	String contextPath = request.getContextPath();
%>

<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/css/default.css" />
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/css/common.css" />

<script type="text/javascript"
	src="<%=contextPath%>/resources/js/common.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/gray/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/color.css" />
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/icon.css" />

<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/uploadify/uploadify.css" />

<script type="text/javascript"
	src="<%=contextPath%>/resources/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/easyui/extend.validate.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/message.js"></script>

<script>
	var basePath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div class="admincenter" style="padding-top: 10px;">
		<div class="admintitle">
			<div class="sj">
				<img src="<%=contextPath%>/resources/images/zao.gif" width="34"
					height="25" />
			</div>
			<strong>个人信息管理</strong>
		</div>
	</div>
	<div class="main_main">
		<div class="sj">
			<img src="<%=contextPath%>/resources/images/icon5.gif" width="22"
				height="20" />
		</div>
		<c:choose>
			<c:when test="${sessionScope.loginInfo.loginTime==null}">
				<span class="main_time">这是您第一次登录本系统，欢迎您使用！</span>
			</c:when>
			<c:otherwise>
				<span class="main_time">您最后一次登录的时间：<fmt:formatDate
						value="${sessionScope.loginInfo.loginTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /> (不是您登录的？<a
					href="javascript:void(0);"
					onclick='window.parent.setFrameContent("修改密码","<%=contextPath%>/user/updatePasswordInit.do");'>请点这里</a>)
				</span>
			</c:otherwise>
		</c:choose>


	</div>
	<div class="main_bottomzi">
		<ul>
			<li>您可以快速修改个人密码</li>
			<li>
			<li><a href="javascript:void(0);"
				onclick='window.parent.setFrameContent("修改密码","<%=contextPath%>/user/updatePasswordInit.do");'>
					修改密码</a></li>
		</ul>
		<ul>
			<li>您可以快速查看登录日志</li>
			<li><a href="javascript:void(0);"
				onclick='window.parent.setFrameContent("登录日志","<%=contextPath%>/loginRecord/recordList.do");'>
					登录日志</a></li>
		</ul>
		<c:if test="${sessionScope.loginInfo.userType=='001'}">
			<ul>
				<li>您可以快速查看行政案件年度统计&nbsp;&nbsp;</li>
				<li><a href="javascript:void(0);"
					onclick='window.parent.setFrameContent("行政案件统计","<%=contextPath%>/inspection/statisticsInit.do");'>
						行政案件统计</a></li>
			</ul>
		</c:if>
			<ul>
				<li>您可以快速查看刑事案件年度统计&nbsp;&nbsp;</li>
				<li><a href="javascript:void(0);"
					onclick='window.parent.setFrameContent("刑事案件统计","<%=contextPath%>/investigation/statisticsInit.do");'>
						刑事案件统计</a></li>
			</ul>
	</div>
	<%-- <div class="admintitle adminxia" style="margin-top: 10px">
		<div class="sj" style="margin-right: 20px;">
			<img src="<%=contextPath%>/resources/images/icon6.gif" width="21"
				height="28" />
		</div>
		<strong>待处理信息</strong>
	</div>
	<div class="main_bottomzi">
		<ul>
			<li>您可以快速修改个人密码</li>
			<li>
			<li><a href="javascript:void(0);"
				onclick='window.parent.setFrameContent("修改密码","<%=contextPath%>/user/updatePasswordInit.do");'>
					修改密码</a></li>
		</ul>
		<ul>
			<li>您可以快速查看登录日志</li>
			<li><a href="javascript:void(0);"
				onclick='window.parent.setFrameContent("登录日志","<%=contextPath%>/loginRecord/recordList.do");'>
					登录日志</a></li>
		</ul>
	</div> --%>


	<div class="admintitle">
		<div class="sj" style="margin-right: 20px;">
			<img src="<%=contextPath%>/resources/images/icon_3.gif" width="21"
				height="28" />
		</div>
		<strong>软件信息</strong>
	</div>
	<div class="main_bottomzi ">
		<ul>
			<li>软件版本：</li>
			<li>V1.0</li>
		</ul>
		<ul>
			<li>开发平台：</li>
			<li>MyBatis+Spring3.0+easyUI</li>
		</ul>
		<ul>
			<li>数据库：</li>
			<li>MySql5.6</li>
		</ul>
		<ul>
			<li>WEB服务器：</li>
			<li>Tomcat7.0</li>
		</ul>
	</div>



</body>
</html>