<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
<script type="text/javascript">
	//模糊查询
	function check() {
		var text = $("[name='text']").val();
		var url = "/link/selects?text=" + text;
		$("#content-wrapper").load(url);
	}

	//分页方法
	function goPage(pageNum) {
		var url = "/link/selects?pageNum=" + pageNum + "&"
				+ $("#f1").serialize();
		$("#content-wrapper").load(url);
	}
	
	//添加
	function add(){
		location="/link/toAdd";
	}
	
	//修改
	function upd(id){
		location="/link/toUpdate?id="+id;
	}
	
	//删除
	function del(id){
		location="/link/deleteLink?id="+id;
	}
</script>
</head>
<body>
	<div style="padding: 0 10px 0 10px">
		<div>
			<form id="f1">
				链接标题：<input type="text" name="text" value="${link.text}" />
				<button type="button" class="btn btn-success" onclick="check()">搜索</button>
			</form>
			<button onclick="add()">添加友情链接</button>
		</div>
		<br>
		<table class="table table-bordered">
			<tr>
				<td>编号</td>
				<td>链接名称</td>
				<td>地址</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${links}" var="l" varStatus="count">
				<tr>
					<td>${l.id}</td>
					<td>${l.text}</td>
					<td>${l.url}</td>
					<td>
						<button onclick="upd(${l.id})">修改</button>
						<button onclick="del(${l.id})">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/views/public/page.jsp"></jsp:include>

	</div>
</body>
</html>
