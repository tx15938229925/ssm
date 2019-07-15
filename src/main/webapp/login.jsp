<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	input{
		width: 200px;
		height: 40px;
	}
</style>
</head>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" language="javascript">
	function checkForm() {
		var username = checkTeaMess();

		return username;
	}

	function checkPassword() {
		var teaPassword = $("#signin-password").val();

		return true;
	}

	function checkTeaMess() {
		var regPhone = /^1[34578]\d{9}$/;/* 手机号验证，11位手机号 */
		var regName = /^[\u4e00-\u9fff\w]{1,7}$/;
		var regEmai = /^[1-9a-zA-Z_]\w*@[a-zA-Z0-9]+(\.[a-zA-Z]{2,})+$/;
		var regCardId = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;

		/* 获取教师登陆信息 */
		var username = $("#signin-mess").val();//获取输入登陆信息

		/* 判断用户输入是否为邮箱，是则将name属性值改为邮箱 */
		if (regEmai.test(username)) {
			$("#signin-mess").attr("name", "email");
		}

		/* 判断用户输入是否为邮箱，是则将name属性值改为邮箱 */
		if (regPhone.test(username)) {
			$("#signin-mess").attr("name", "tel");
		}
		if (regCardId.test(username)) {
			$("#signin-mess").attr("name", "cardId");
		}
	}

	$(document.body).ready(function() {
		//首次获取验证码
		$("#imgVerify").attr("src", "getVerify?" + Math.random());
	});
	//获取验证码
	function getVerify() {
		var src1 = document.getElementById('imgVerify')
		src1.src = "getVerify?" + Math.random();
	}
	//校验验证码
	function checkSum() {
		var html1 = document.getElementById('loginingForm');
		var inputStr = $("#checks").val();
		if (inputStr != null && inputStr != "" && inputStr != "验证码:") {
			inputStr = inputStr.toUpperCase();//将输入的字母全部转换成大写
			html1.action = "login?inputStr=" + inputStr;//提交表单
		} else {
			alert("验证码不能为空!")
		}

	}
</script>
<body>
	<center>

		<form action="loginVerify" method="post">
			<table>
				<tr>
					<td>
						<h1 align="center">登录页面</h1>
					</td>
				</tr>
				<tr>
					<td><input type="text" name="username" placeholder="用户名" />

					</td>
				</tr>
				<tr>
					<td><input type="text" name="password" placeholder="密码" /></td>
				</tr>
				<tr>
				<tr>
					<td><input type="submit" value="登陆" /></td>
				</tr>
			</table>

		</form>
	</center>
</body>
</html>