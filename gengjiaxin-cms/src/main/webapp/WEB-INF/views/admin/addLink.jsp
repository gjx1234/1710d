<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
<!-- 引入bootstrap样式 -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/sb-admin.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
</head>
<body>
	<div style="padding: 0 10px 0 10px">
		<form action="/link/addLink" method="post">
			<table class="table table-bordered">
				<tr>
					<td>名称</td>
					<td>
						<input type="text" name="text">
					</td>
				</tr>
				<tr>
					<td>链接地址</td>
					<td>
						<input type="text" name="url">
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
	</div>
</body>
</html>