<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Mobile-Worker</title>
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
<link rel="stylesheet" href="<%=contextPath%>/resources/css/common.css"
	type="text/css" media="screen" />
<script type="text/javascript"
	src="<%=contextPath%>/resources/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/jquery.cookie.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/message.js"></script>

</head>
<body id="loginbg">
	<div id="login-wrapper">
		<span class="system-title">用户登录</span>
		<p style="margin-top: -18px; height: 20px; padding: 0px; color: red;"
			id="message"></p>
		<form id="loginForm" action="login.do" method="post">
			<table class="form_tab">
				<tr>
					<td class="td-right">用户名</td>
					<td><input class="input_200" type="text" name="employeeCode"
						id="j_username" tabindex="1" /></td>
				</tr>
				<tr>
					<td class="td-right">密码</td>
					<td><input class="input_200" type="password" name="password"
						id="j_password" tabindex="2" /></td>
				</tr>
				<tr>
					<td colspan="2"><input class="order" type="checkbox"
						id="cxdc_rmb_user" name="0" id="0" />记住用户名</td>
				</tr>
			</table>
		</form>
		<span class="login_btn"><a class="chengxiang_btn" type="submit"
			onclick="javascript:submit()">登&nbsp;录</a></span>
	</div>
	<script type="text/javascript">
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
					submit();
				}
			});

			$("#j_username").focus();
			$("#j_username").select();

		});

		function submit() {
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
			$.ajax({
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
							window.location.href = basePath + '/index.do';
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