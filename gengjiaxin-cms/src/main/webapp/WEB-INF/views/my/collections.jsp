<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 引入bootstrap样式 -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	function goPage(pageNum) {
		var url = "/user/selectCollections?pageNum=" + pageNum;
		$("#center").empty();
		$("#center").load(url);
	}
	function to(url){
		alert(url);
		location = url;
	}
</script>
</head>
<body>
	<div style="padding-top: 10px">
		<ul class="list-unstyled">
			<c:forEach items="${list}" var="a">
				<li class="media">
					<div class="media-body text-center">
						<h5 class="mt-0 mb-1">
							<a href="${a.url}" style="font-size: 15px"> ${a.text} </a>
						</h5>
						<fmt:formatDate value="${a.created}" pattern="yyyy-MM-dd" />
						<a href="/user/deleteCollection?id=${a.id}">删除</a>
					</div>
				</li>
				<hr>
			</c:forEach>
			<jsp:include page="/WEB-INF/views/public/page.jsp"></jsp:include>
		</ul>
	</div>
</body>
</html>