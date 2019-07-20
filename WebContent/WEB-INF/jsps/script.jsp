<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://cxtag.bm.com" prefix="cx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<%
	String contextPath = request.getContextPath();
%>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/css/comm.css" />

<%--  <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/gray/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/color.css" />
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/icon.css" />  --%>

<%-- <link href="<%=contextPath%>/resources/uimaker/css1/platform.css"
    rel="stylesheet"> --%>
<link rel="stylesheet"
    href="<%=contextPath%>/resources/uimaker/easyui.css">
<link rel="stylesheet"
    href="<%=contextPath%>/resources/uimaker/icon.css">
<link href="<%=contextPath%>/resources/uimaker/css1/base.css"
    rel="stylesheet">        
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/uploadify/uploadify.css" />

<script type="text/javascript"
	src="<%=contextPath%>/resources/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/uimaker/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/easyui/extend.validate.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/uimaker/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/message.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/common.js"></script>

<script type="text/javascript"
	src="<%=contextPath%>/resources/uploadify/jquery.uploadify.js"></script>