<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<link type="stylesheet" href="css/index.css">
<script type="text/javascript">
	function goPage(pageNum){
		location="index?pageNum="+pageNum+"&"+$("#form").serialize();
	}
	
	function pages(){
		var pageNum = $("#page").val();
		location="index?pageNum="+pageNum+"&"+$("#form").serialize();
	}
	
	function check(id){
		location="checkOne?id="+id;
	}
	function addOne(){
		location="addOne";
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<button onclick="addOne()">添加</button>
	<form id="form" action="index" method="post">
		类型：
		<select name="tid">
			<option value="0">请选择</option>
			<c:forEach items="${types}" var="t">
				<option value="${t.id}" ${t.id==con.tid?'selected':''}>${t.name}</option>
			</c:forEach>
		</select> 
		视频日期:<input type="text" value='<fmt:formatDate value="${con.date1}" pattern="yyyy-MM-dd"/>' name="date1"> 
		--<input type="text" value='<fmt:formatDate value="${con.date2}" pattern="yyyy-MM-dd"/>' name="date2"> 
		标题：<input type="text" value="${con.title}" name="title">
		<input type="submit" value="搜索">
	</form>

	<table>
		<tr>
			<th>标题</th>
			<th>文件</th>
			<th>日期</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list}" var="d">
			<tr>
				<td>${d.title}</td>
				<td>
					<video src="d:pic/${d.video}"></video>
				</td>
				<td>
					<fmt:formatDate value="${d.date}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<button onclick="check(${d.id})">评测</button>
					<button onclick="check(${d.id})">修改</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	共${info.total}项  每页${info.pageSize}项  当前第${info.pageNum}页 共${info.pages}页 
	<button onclick="goPage(${info.pageNum==1?1:info.pageNum-1})">上一页</button>
	<button onclick="goPage(${info.pageNum==info.pages?info.pages:info.pageNum+1})">下一页</button>
	<button onclick="goPage(${info.pages})">尾页</button>
	<br>
	第<input id="page" type="text" name="pageNum" value="${info.pageNum}">页
	<button onclick="pages()">跳转</button>
</body>
</html>