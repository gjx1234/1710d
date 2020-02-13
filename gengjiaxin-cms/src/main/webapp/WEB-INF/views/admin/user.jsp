<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
<script type="text/javascript">
	function check(){
		var username = $("[name='username']").val();
		var url = "/user/selects?username="+username;
		$("#content-wrapper").load(url);
	}
	
	//改变用户状态
	function locked(id,obj){
		//获得当前用户的状态
		var locked=$(obj).text()=="正常"?0:1;
		//调用方法区修改
		$.post(
			"/user/update",
			{id:id,locked:locked},
			function(msg){
				$(obj).text(locked==0?"禁用":"正常");
				$(obj).attr("class",locked==0?"btn btn-success":"btn btn-secondary");
			}
		);
		
	}
	
	//分页方法
	function goPage(pageNum){
		var url="/user/selects?pageNum="+pageNum+"&"+$("#f1").serialize();
		$("#content-wrapper").load(url);
	}
</script>
</head>
<body>
	<div style="padding: 0 10px 0 10px">
		<div>
			<form id="f1">
				姓名：<input type="text" name="username" value="${username}">
				<button type="button" class="btn btn-success" onclick="check()">搜索</button>
			</form>
		</div>
		<br>
		<table class="table table_bordered">
			<tr>
				<td>序号</td>
				<td>用户名</td>
				<td>昵称</td>
				<td>性别</td>
				<td>生日</td>
				<td>注册日期</td>
				<td>修改日期</td>
				<td>状态</td>
			</tr>
			<c:forEach items="${list}" var="u" varStatus="count">
				<tr>
					<td>${count.index+info.startRow}</td>
					<td>${u.username}</td>
					<td>${u.nickname}</td>
					<td>${u.gender.displayName}</td>
					<td>
						<fmt:formatDate value="${u.birthday}" pattern="yyyy-MM-dd" />
					</td>
					<td><fmt:formatDate value="${u.created}" pattern="yyyy-MM-dd" />
					</td>
					<td><fmt:formatDate value="${u.updated}" pattern="yyyy-MM-dd" />
					</td>
					<td><c:if test="${u.locked==0}">
							<button type="button" class="btn btn-success"
								onclick="locked(${u.id},this)">禁用</button>
						</c:if> <c:if test="${u.locked==1}">
							<button type="button" class="btn btn-success"
								onclick="locked(${u.id},this)">正常</button>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/views/public/page.jsp"></jsp:include>
	</div>
</body>
</html>