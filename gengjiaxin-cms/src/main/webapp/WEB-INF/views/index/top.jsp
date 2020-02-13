<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<title>Insert title here</title>
</head>
<body>
	<div class="container-fulid">
		<div style="padding-left:1000px">
			<!-- login register -->
			<ul class="nav">
				<!-- if not login--show login or register -->
				<c:if test="${sessionScope.user==null}">
					<li class="nav-item"><a class="nav-link" href="/user/register">注册</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/login">登录</a></li>
				</c:if>
				<c:if test="${sessionScope.user!=null}">
					<li class="nav-item" style="color:red;padding-top:5px">欢迎 ${sessionScope.user.username}</li>
					<li class="nav-item"><a class="nav-link" href="/my">个人主页</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/logout">退出</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>