<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
input {
	width: 200px;
	height: 30px;
}
select{
	width: 200px;
	height: 30px;
}
</style>
</head>
<body>
	<form action="startAddUser" method="post">
		<center>
			<h2>新增用户</h2>
			<table>
				<tr>
					<td>编号</td>
					<td><input type="text" value="${maxId }" readonly="readonly" name="id"></td>
				</tr>
				<tr>
					<td>用户名</td>
					<td><input type="text" value="" name="username"></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="text" value="" name="password"></td>
				</tr>
				<tr>
					<td>手机号</td>
					<td><input type="text" value="" name="tel"></td>
				</tr>
				<tr>
					<td>邮箱</td>
					<td><input type="text" value="" name="email"></td>
				</tr>
				<tr>
					<td>权限</td>
					<td>
						<select name="roleId">
							<option value="1" >管理员</option>
							<option value="2">普通会员</option>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="提交"></td>
				</tr>
			</table>


		</center>


	</form>
</body>
</html>