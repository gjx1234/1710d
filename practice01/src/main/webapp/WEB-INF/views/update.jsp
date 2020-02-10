<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/js/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="/resources/css/index3.css">
<script type="text/javascript">
	var content = '${plan.content}';
	$("[name=content]").text(content);
</script>
</head>
<body>
	<form action="update" method="post">
		<table>
			<tr>
				<td></td>
				<td colspan="10">投资项目详情</td>
			</tr>
			<tr>
				<td>项目名称</td>
				<td><input type="hidden" value="${plan.id}" name="id">
					<input name="name" type="text" value="${plan.name}"></td>
			</tr>
			<tr>
				<td>投资金额</td>
				<td><input name="amount" type="text" value="${plan.amount}">元</td>
			</tr>
			<tr>
				<td>分管领导</td>
				<td><input name="manager" type="text" value="${plan.manager}"></td>
			</tr>
			<tr>
				<td>所属部门</td>
				<td><select name="dept_id">
						<option value="-1">---请选择---</option>
						<c:forEach items="${departments}" var="d">
							<option value="${d.id}" ${plan.department.id==d.id?'selected':''}>${d.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>项目说明</td>
				<td>
				<textarea rows="10" cols="30" name="content" value="${plan.content}"></textarea>
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