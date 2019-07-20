<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${webSite.logoTitle }</title>
<%
	String contextPath = request.getContextPath();
%>
<link rel="icon" href="<%=contextPath%>/resources/images/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="<%=contextPath%>/resources/images/favicon.ico"
	type="image/x-icon" />
<link rel="bookmark"
	href="<%=contextPath%>/resources/images/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/css/default.css" />

<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/default/easyui.css" />

<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/color.css" />
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/icon.css" />

<script type="text/javascript"
	src="<%=contextPath%>/resources/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/easyui/extend.validate.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/common.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/index.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		$("#defaultFrame").attr("src",
				"${pageContext.request.contextPath}/admin/default.do");
	});
	function setFrameContent(title, src) {
		addTab(title,src);
	}
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" border="false"
		style="overflow: hidden; height: 60px; background: url(<%=contextPath%>/resources/images/top.jpg) #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
		<span style="float: right; font-weight:700; padding-right: 20px; margin-top: 10px;"
			class="head">欢迎 <font style="color: #FFA700;">&nbsp;${sessionScope.loginInfo.userName}&nbsp;</font>
			<a href="javascript:void(0)" id="loginOut" onclick="logout()">安全退出</a></span>
		<span
			style="padding-left: 10px; margin-top: 5px; font-size: 30px; font-weigth:700; float: left;"><img
			src="<%=contextPath%>/resources/images/logo_police.png" width="50px"
			height="48px" style="margin-bottom:12px;" align="absmiddle" />西安市公安局${webSite.logoTitle }</span>

	</div>
	<!-- <div region="south" style="height: 30px; background: #F8F8F8;">
		<div class="footer">CopyRight © 2015 西安市第四十七中</div>
	</div> -->
	<div region="west" hide="true" title="导航菜单" style="width: 190px;"
		id="west">
		<div id='wnav' class="easyui-accordion" fit="true" border="false">

			<c:forEach var="parentMenu" items="${menuInfo.children}"
				varStatus="loop">
				<div style="width: 163px; height:;" class="panel">
					<div style="width: 153px; height: 15px;"
						class="panel-header accordion-header accordion-header-selected">
						<div class="panel-title panel-with-icon">${parentMenu.menuName}</div>
						<!-- <div class="panel-icon icon icon-sys"></div> -->
						<div class="panel-icon"></div>
						<div class="panel-tool">
							<div class="panel-tool-collapse"></div>
						</div>
					</div>
					<div style="width: 100%; height: 100%; display: block;"
						class="accordion-body" title="">
						<ul>
							<c:forEach var="childMenu" items="${parentMenu.children}"
								varStatus="loop">
								<li><div>
										<a href="javascript:void(0)"
											rel='<%=contextPath%>${childMenu.menuUrl}'
											title='${childMenu.menuName}'><span class="icon icon-nav">&nbsp;</span><span
											class="nav">${childMenu.menuName}</span></a>
									</div></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用"
				style="overflow: hidden; background-color: rgb(242, 242, 242);"
				id="home">

				<iframe id="defaultFrame" width="100%" height="100%" frameborder="0"
					scrolling="auto"></iframe>

			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>

</body>
</html>