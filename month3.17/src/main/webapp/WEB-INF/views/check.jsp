<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>试驾测评</h2>
	<form action="check" method="post">
		<table>
			<tr>
				<td>视频日期</td>
				<td><input type="text"
					value='<fmt:formatDate value="${driver.date}" pattern="yyyy-MM-dd"/>'
					name="date"></td>
			</tr>
			<tr>
				<td>类型</td>
				<td><select name="type.id">
						<option value="0">请选择</option>
						<c:forEach items="${types}" var="t">
							<option value="${t.id}" ${t.id==driver.type.id?'selected':''}>${t.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>标题</td>
				<td>
					<input type="text" name="title" value="${driver.title}">
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="提交">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>