<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.title_tr td{
		color : #5CBA2C;
	}
</style>
</head>
<body>

 

	<font color="red" style="margin-left: 10%">操作用户：${sessionScope.username}</font>
	<center>
		<h2>用户管理界面</h2>
		<hr width="80%">
		<table width="80%" height="30%">
			<tr class="title_tr">
				<td>用户编号</td>
				<td>用户名</td>
				<td>密码</td>
				<td>电话</td>
				<td>邮箱</td>
				<td>权限</td>
				<td>编辑</td>
			</tr>
			
			<c:forEach var="mt" items="${pf.list }">
					<tr>
						<td>${mt.id }</td>
						<td>${mt.username }</td>
						<td>${mt.password }</td>
						<td>${mt.tel }</td>
						<td>${mt.email }</td>
						<td>${mt.role.rolename }</td>
						<td><a style="text-decoration: NONE " href="updateuser?id=${mt.id }&pageNum=${pf.pageNum }">修改</a>
						 	<a style="text-decoration: NONE " href="deleteuser?id=${mt.id }">删除</a>
						 </td>
					</tr>
			</c:forEach>
			
		</table>
		
	</center>
	<a style="margin-left: 10%;text-decoration: NONE "  href="adduser" >新增用户</a>
	<div id="fenye" align="center">
		第 ${pf.pageNum } 页 共 ${pf.pages } 页 一共 ${pf.total } 条记录 <a
			href="listAllUser?currentPage=${pf.firstPage }" style="text-decoration: NONE ">首页</a> <a
			href="listAllUser?currentPage=${pf.prePage }" style="text-decoration: NONE ">上一页 </a> <a
			href="listAllUser?currentPage=${pf.nextPage }" style="text-decoration: NONE ">下一页</a> <a
			href="listAllUser?currentPage=${pf.lastPage }" style="text-decoration: NONE ">尾页</a>
	</div>
	
</body>
</html>