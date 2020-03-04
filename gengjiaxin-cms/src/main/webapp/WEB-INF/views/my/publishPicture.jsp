<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="article/addPictures" enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<td>
					<span>写入描述</span>
					<textarea name="desc1" placeholder="请写入具体描述"></textarea>
					<input type="file" name="file1">
				</td>
			</tr>
			<tr>
				<td>
					<span>写入描述</span>
					<textarea name="desc2" placeholder="请写入具体描述"></textarea>
					<input type="file" name="file2">
				</td>
			</tr>
			<tr>
				<td>
					<span>写入描述</span>
					<textarea name="desc3" placeholder="请写入具体描述"></textarea>
					<input type="file" name="file3">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="提交">
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>