<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>商品id</th>
			<th>名称</th>
			<th>价格</th>
			<th>已售百分比</th>
		</tr>
		<c:forEach items="${list}" var="g">
			<tr>
				<td>${g.id}</td>
				<td>${g.name}</td>
				<td>${g.price}</td>
				<td>${g.recent}</td>
			</tr>
		</c:forEach>
		<tr>
			<td>
				<a href="list?pageNum=${pageNum==1?'1':pageNum-1}">上一页</a>
				<a href="list?pageNum=${pageNum==11?'1':pageNum+1}">下一页</a>
			</td>
		</tr>
	</table>
</body>
</html>