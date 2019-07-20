<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${webSite.logoTitle}</title>
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
<link rel="stylesheet" href="<%=contextPath%>/resources/css/style.css"
	type="text/css" media="screen" />
<script type="text/javascript"
	src="<%=contextPath%>/resources/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/jquery.cookie.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/message.js"></script>

</head>

<body
	style="background-color: #1c77ac; background-image: url(images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">
	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
	<div class="logintop">
		<span>欢迎登录后台管理界面平台</span>
		<ul>
			<!-- <li><a href="#">回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li> -->
		</ul>
	</div>
	<div class="loginbody">
		<span class="systemlogo">西安市公安局<br/> ${webSite.logoTitle }</span>

		<form id="loginForm" method="post">
			<div class="loginbox">
				<ul>
					<li><input class="loginuser" type="text" name="employeeCode"
						id="j_username" tabindex="1" /></li>
					<li><input class="loginpwd" type="password" name="password"
						id="j_password" tabindex="2" /></li>
					<li><input name="loginbtn" type="button" class="loginbtn"
						value="登录" onclick="javascript:doLogin()" /><input
						id="cxdc_rmb_user" name="0" type="checkbox"
						style="margin-left: 20px;" />记住用户名</li>
					<li style="margin-top: -15px;"><p
							style="color: red; font-weight: 700;" id="message"></p></li>
				</ul>
			</div>
		</form>
	</div>
	<!-- <div class="loginbm">CopyRight © 2015 西安市第四十七中</div> -->

	<script type="text/javascript">
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});

		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		});

		$(document).ready(function() {
			if ($.cookie("cxdc_rmb_user") == "true") {
				$("#cxdc_rmb_user").attr("checked", true);
				$("#j_username").val($.cookie("cxdc_user"));
			}

			$("#j_username").bind('keypress', function(e) {
				if (e.keyCode == 13) {
					$("#j_password").focus();
					$("#j_password").select();
					return false;
				}
			});

			$("#j_password").bind('keypress', function(e) {
				if (e.keyCode == 13) {
					doLogin();
				}
			});

			$("#j_username").focus();
			$("#j_username").select();

		});

		function doLogin() {
			var str_username = $("#j_username").val();
			var str_password = $("#j_password").val();
			if ($.trim(str_username) == "") {
				$("#message").html(message.PLS_INPUT_LOGINID);
				return;
			} else if ($.trim(str_password) == "") {
				$("#message").html(message.PLS_INPUT_PASSWORD);
				return;
			}
			if ($("[id='cxdc_rmb_user']:checked").val() == 'on') {
				$.cookie("cxdc_rmb_user", "true", {
					expires : 7
				});
				$.cookie("cxdc_user", str_username, {
					expires : 7
				});
			} else {
				$.removeCookie('cxdc_rmb_user');
				$.removeCookie('cxdc_user');
			}
			var loginId = escape(str_username);
			var password = escape(str_password);
			var basePath = "${pageContext.request.contextPath}";
			$
					.ajax({
						type : 'POST',
						global : false,
						url : basePath + '/admin/doLogin.do',
						data : {
							'loginId' : loginId,
							'password' : password
						},

						success : function(data) {
							if (data != null) {
								if (data == "LOGIN_SUCCESS") {
									window.location.href = basePath
											+ '/index.do';
								} else {
									$("#message").html(message.byId(data));
								}
							}
						},
						error : function(err, state) {
							$("#message").html(message.SYSTEM_ERROR);
						}
					});
		}
	</script>
</body>
</html>