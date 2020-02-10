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
	function fenye(pageNum){
		$("[name=pageNum]").val(pageNum);
		$("form").submit();
	}
	function add(){
		location = "toAdd";
	}
	function choose(own){
		$("[name=check]").each(function(){
			this.checked=own.checked;
		})
	}
	function del(){
		var ids = $("[name=check]:checked").map(function(){
			return this.value;
		}).get().join();
		$.post(
				"batchDelete",
				{ids:ids},
				function(flag){
					if(flag){
						alert("删除成功");
						location = "selectPlans";
					}else{
						alert("删除失败");
					}
			}
		)
	}
</script>
</head>
<body>
	<form action="selectPlans" method="post">
		<input type="hidden" name="pageNum">
		<input type="text" name="planName"> <input type="submit"
			value="查找">
	</form>
	<table border="1">
		<tr>
			<td><input type="checkbox" onclick="choose(this)"></td>
			<td>编号</td>
			<td>项目名称</td>
			<td>投资金额</td>
			<td>分管领导</td>
			<td>部门</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${list}" var="p">
			<tr>
				<td><input type="checkbox" name="check" value="${p.id}">
				</td>
				<td>${p.id}</td>
				<td>${p.name}</td>
				<td>${p.amount}</td>
				<td>${p.manager}</td>
				<td>${p.department.name}</td>
				<td><a href="selectOne?id=${p.id}">查看详情</a></td>
			</tr>
		</c:forEach>
	</table>
	页码：
	<c:forEach items="${pages}" var="page">
		<button onclick="fenye(${page})">${page}</button>
	</c:forEach>
	
	<button onclick="add()">新增项目</button>
	<button onclick="del()">批量删除项目</button>
</body>
</html>