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

<%-- <link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/default/easyui.css" />

<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/color.css" />
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/resources/easyui/thems/icon.css" />
	 --%>
<link href="<%=contextPath%>/resources/uimaker/css1/base.css"
	rel="stylesheet">
<link href="<%=contextPath%>/resources/uimaker/css1/platform.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=contextPath%>/resources/uimaker/easyui.css">

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
<script type="text/javascript"
	src="<%=contextPath%>/resources/uimaker/js/main.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
	$(function() {
		$("#defaultFrame").attr("src",
				"${pageContext.request.contextPath}/admin/default.do");
	});
	function setFrameContent(title, src) {
		addTab(title, src);
	}
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div class="container">
		<div id="pf-hd">
			<div class="pf-logo" style="border-right: 0px; padding-top: 10px;">
				<%-- <img
					src="<%=contextPath%>/resources/uimaker/images/main/main_logo.png"
					alt="logo"> --%>
				<img src="<%=contextPath%>/resources/images/logo2.png" alt="logo">
			</div>

			<!-- <div class="pf-nav-wrap">
				<div class="pf-nav-ww">
					<ul class="pf-nav">
						<li class="pf-nav-item home current" data-menu="sys-manage">
							<a href="javascript:;"> <span class="iconfont">&#xe63f;</span>
								<span class="pf-nav-title">系统管理</span>
						</a>
						</li>
						<li class="pf-nav-item project" data-menu="org-manage"><a
							href="javascript:;"> <span class="iconfont">&#xe60d;</span> <span
								class="pf-nav-title">组织管理</span>
						</a></li>
						<li class="pf-nav-item static" data-menu="main-data"><a
							href="javascript:;"> <span class="iconfont">&#xe61e;</span> <span
								class="pf-nav-title">主数据</span>
						</a></li>
						<li class="pf-nav-item manger" data-menu="supplier-mange"><a
							href="javascript:;"> <span class="iconfont">&#xe620;</span> <span
								class="pf-nav-title">供应商管理</span>
						</a></li>

						<li class="pf-nav-item manger" data-menu="supplier-dev"><a
							href="javascript:;"> <span class="iconfont">&#xe625;</span> <span
								class="pf-nav-title">供应商开发</span>
						</a></li>

						<li class="pf-nav-item manger" data-menu="pur-source"><a
							href="javascript:;"> <span class="iconfont">&#xe64b;</span> <span
								class="pf-nav-title">采购寻源</span>
						</a></li>

						<li class="pf-nav-item manger" data-menu="contract-mange"><a
							href="javascript:;"> <span class="iconfont">&#xe64c;</span> <span
								class="pf-nav-title">合同管理</span>
						</a></li>


						<li class="pf-nav-item manger" data-menu="pur-source"><a
							href="javascript:;"> <span class="iconfont">&#xe623;</span> <span
								class="pf-nav-title">示例示例</span>
						</a></li>

						<li class="pf-nav-item manger" data-menu="contract-mange"><a
							href="javascript:;"> <span class="iconfont">&#xe646;</span> <span
								class="pf-nav-title">示例示例示例示例示例</span>
						</a></li>
						<li class="pf-nav-item manger" data-menu="pur-source"><a
							href="javascript:;"> <span class="iconfont">&#xe623;</span> <span
								class="pf-nav-title">采购寻源</span>
						</a></li>

						<li class="pf-nav-item manger" data-menu="contract-mange"><a
							href="javascript:;"> <span class="iconfont">&#xe646;</span> <span
								class="pf-nav-title">合同管理</span>
						</a></li>


						<li class="pf-nav-item manger" data-menu="pur-source"><a
							href="javascript:;"> <span class="iconfont">&#xe623;</span> <span
								class="pf-nav-title">示例示例</span>
						</a></li>

						<li class="pf-nav-item manger" data-menu="contract-mange"><a
							href="javascript:;"> <span class="iconfont">&#xe646;</span> <span
								class="pf-nav-title">示例示例示例示例示例示例</span>
						</a></li>

						<li class="pf-nav-item manger" data-menu="pur-source"><a
							href="javascript:;"> <span class="iconfont">&#xe623;</span> <span
								class="pf-nav-title">采购寻源</span>
						</a></li>

						<li class="pf-nav-item manger" data-menu="contract-mange"><a
							href="javascript:;"> <span class="iconfont">&#xe646;</span> <span
								class="pf-nav-title">合同管理</span>
						</a></li>
					</ul>
				</div>


				<a href="javascript:;" class="pf-nav-prev disabled iconfont">&#xe606;</a>
				<a href="javascript:;" class="pf-nav-next iconfont">&#xe607;</a>
			</div> -->



			<div class="pf-user">
				<div class="pf-user-photo">
					<img src="<%=contextPath%>/resources/images/user.png" alt="">
				</div>
				<h4 class="pf-user-name ellipsis">${sessionScope.loginInfo.userName}</h4>
				<i class="iconfont xiala">&#xe607;</i>

				<div class="pf-user-panel">
					<ul class="pf-user-opt">
						<li><a href="javascript:;"> <i class="iconfont">&#xe60d;</i>
								<span class="pf-opt-name">用户信息</span>
						</a></li>
						<li class="pf-modify-pwd"><a href="javascript:void(0)"
							onclick='setFrameContent("修改密码","<%=contextPath%>/user/updatePasswordInit.do");'>
								<i class="iconfont">&#xe634;</i> <span class="pf-opt-name">修改密码</span>
						</a></li>
						<li class="pf-logout"><a href="javascript:void(0)"
							id="loginOut" onclick="logout()"> <i class="iconfont">&#xe60e;</i>
								<span class="pf-opt-name">退出</span>
						</a></li>
					</ul>
				</div>
			</div>

		</div>

		<div id="pf-bd">
			<div id="pf-sider">
				<h2 class="pf-model-name">
					<span class="iconfont">&#xe64a;</span> <span class="pf-name">功能列表</span>
					<span class="toggle-icon"></span>
				</h2>

				<ul class="sider-nav" id='wnav'>
					<c:forEach var="parentMenu" items="${menuInfo.children}"
						varStatus="loop">
						<li><a href="javascript:;"> <span
								class="iconfont sider-nav-icon">&#xe620;</span> <span
								class="sider-nav-title">${parentMenu.menuName}</span> <i
								class="iconfont">&#xe642;</i>
						</a>
							<ul class="sider-nav-s">
								<c:forEach var="childMenu" items="${parentMenu.children}"
									varStatus="loop">
									<li><a href="javascript:void(0)"
										rel='<%=contextPath%>${childMenu.menuUrl}'
										title='${childMenu.menuName}'>${childMenu.menuName}</a></li>
								</c:forEach>
							</ul></li>
					</c:forEach>
				</ul>
			</div>

			<div id="pf-page">
				<div id="tabs" class="easyui-tabs1" data-options="fit:true"
					style="width: 100%;">
					<div title="首页" style="padding: 10px 5px 5px 10px;">
						<!-- <iframe class="page-iframe" src="workbench.html" frameborder="no"
							border="no" height="100%" width="100%" scrolling="auto"></iframe> -->
						<iframe id="defaultFrame" width="100%" height="100%"
							frameborder="0" scrolling="auto"></iframe>

					</div>
					<!-- <div title="采购组织" style="padding: 10px 5px 5px 10px;"
						data-options="closable:true">
						<iframe class="page-iframe" src="index.html" frameborder="no"
							border="no" height="100%" width="100%" scrolling="auto"></iframe>
					</div>
					<div title="基本信息" data-options="closable:true"
						style="padding: 10px 5px 5px 10px;">
						<iframe class="page-iframe" src="basic_info.html" frameborder="no"
							border="no" height="100%" width="100%" scrolling="auto"></iframe>
					</div>
					<div title="供应商" data-options="closable:true"
						style="padding: 10px 5px 5px 10px;">
						<iframe class="page-iframe" src="providers.html" frameborder="no"
							border="no" height="100%" width="100%" scrolling="auto"></iframe>
					</div>
					<div title="业务流程" data-options="closable:true"
						style="padding: 10px 5px 5px 10px;">
						<iframe class="page-iframe" src="process.html" frameborder="no"
							border="no" height="100%" width="100%" scrolling="auto"></iframe>
					</div>
					<div title="表单管理" data-options="closable:true"
						style="padding: 10px 5px 5px 10px;">
						<iframe class="page-iframe" src="providers1.html" frameborder="no"
							border="no" height="100%" width="100%" scrolling="auto"></iframe>
					</div> -->
				</div>
			</div>
		</div>

		<div id="pf-ft">
			<div class="system-name">
				<i class="iconfont">&#xe6fe;</i> <span>西安市公安局食品药品犯罪侦查信息管理系统&nbsp;v1.0</span>
			</div>
			<div class="copyright-name">
				<span>CopyRight&nbsp;2019&nbsp;&nbsp;版权所有</span> <i class="iconfont">&#xe6ff;</i>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('.easyui-tabs1').tabs({
			tabHeight : 44,
			onSelect : onSelect
		})
		function onSelect(title, index) {
			/* var currentTab = $('.easyui-tabs1').tabs("getSelected");
			if (currentTab.find("iframe") && currentTab.find("iframe").size()) {
				currentTab.find("iframe").attr("src",
						currentTab.find("iframe").attr("src"));
			} */
		}

		$(window).resize(function() {
			$('#tabs').tabs('resize', {
				width : $("#tabs").parent().width(),
				height : "auto"
			});
		});

		var page = 0, pages = ($('.pf-nav').height() / 70) - 1;

		if (pages === 0) {
			$('.pf-nav-prev,.pf-nav-next').hide();
		}
		$(document).on('click', '.pf-nav-prev,.pf-nav-next', function() {

			if ($(this).hasClass('disabled'))
				return;
			if ($(this).hasClass('pf-nav-next')) {
				page++;
				$('.pf-nav').stop().animate({
					'margin-top' : -70 * page
				}, 200);
				if (page == pages) {
					$(this).addClass('disabled');
					$('.pf-nav-prev').removeClass('disabled');
				} else {
					$('.pf-nav-prev').removeClass('disabled');
				}

			} else {
				page--;
				$('.pf-nav').stop().animate({
					'margin-top' : -70 * page
				}, 200);
				if (page == 0) {
					$(this).addClass('disabled');
					$('.pf-nav-next').removeClass('disabled');
				} else {
					$('.pf-nav-next').removeClass('disabled');
				}

			}
		})
	</script>
</body>
</html>