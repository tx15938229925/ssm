<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
	#left_01{
		background-color: gray;
		margin-top: 10px;
	}
	body{
		background-image: url("../images/1.jpg");
	}
</style>
</head>

<body>
	<!-- 标题 -->
	<iframe height=100px width=1500px frameborder="0" src="biaoti" >
		
	</iframe>
	
	
	

	

	
	
	<div style="float: left; width: 200px; height: 320px" id="left_01" align="center">
		<p>
			欢迎 <font color="red">${sessionScope.username}</font> 登录！
		</p>

		<%-- <c:forEach items="${menulst }" var="menu">
			<a href="${menu.url }" style="text-decoration: NONE " target="main"><c:out value="${menu.menuname }"></c:out></a>
				<br>
				<br>
		</c:forEach> --%>
		<br><br>
		<a style="text-decoration: NONE; color: black " href="aaa.jsp" target="main">首页</a>
		<br><br><br>
		<a style="text-decoration: NONE; color: black " href="listAllUser" target="main">用户管理</a>
		<br><br><br>
		<a style="text-decoration: NONE; color: black " href="2.jsp" target="main">权限管理</a>
		<br><br><br>
		<hr>
		<a style="text-decoration: NONE; color: black " href="logout">退出登录</a>
	</div>
	
	<!-- 主框 -->
	<iframe id="iframe_id" height=500px width=1100px scrolling="yes"
		src="aaa.jsp" name="main" frameborder="0"> 
	</iframe>
</body>
</html>