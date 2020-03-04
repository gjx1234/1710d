<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resource/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function upd(){
		var user =$("form").serialize();
		alert(user);
		$.post(
			"/user/updateUser",user,function(msg){
				if(msg){
					alert("修改成功");
					var url = window.location.href;
					alert(url);
				}else{
					alert("修改失败");
					location="/my";
				}
			}
		)
	}
</script>
</head>
<body>

	<h2>完善个人信息</h2>
	<form>
		<div class="form-group">
			<label for="exampleFormControlInput1">用户名</label> <input
				type="hidden" value="${user.id}" name="id"> <input
				type="text" class="form-control" id="exampleFormControlInput1"
				value="${user.username}" name="username">
		</div>
		<c:if test="${user.nickname==null}">
			<div class="form-group">
				<label for="exampleFormControlInput1">昵称</label> <input type="text"
					class="form-control" id="exampleFormControlInput1" name="nickname"
					placeholder="请输入昵称">
			</div>
		</c:if>
		<c:if test="${user.nickname!=null}">
			<div class="form-group">
				<label for="exampleFormControlInput1">昵称</label> <input type="text"
					class="form-control" id="exampleFormControlInput1" name="nickname"
					value="${user.nickname}">
			</div>
		</c:if>

		<c:if test="${user.birthday==null}">
			<div class="form-group">
				<label for="exampleFormControlInput1">出生日期</label> <input type="text"
					class="form-control" id="exampleFormControlInput1" name="birthday"
					placeholder="请输入出生日期" onclick="WdatePicker()">
			</div>
		</c:if>
		<c:if test="${user.birthday!=null}">
			<div class="form-group">
				<label for="exampleFormControlInput1">出生日期</label> <input type="text"
					class="form-control" id="exampleFormControlInput1" name="birthday"
					value='<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />' onclick="WdatePicker()">
					
			</div>
		</c:if>
		<div class="form-group">
			<label for="exampleFormControlInput1">性别</label> 
			<input type="radio" name="gender" value="男" ${user.gender.displayName=='男'?'checked':''}>男
			<input type="radio" name="gender" value="女" ${user.gender.displayName=='女'?'checked':''}>女
		</div>
		<div class="form-group">
			<button
				type="button" class="form-control" id="exampleFormControlInput1"
				onclick="upd()">完善信息</button>
		</div>
	</form>
</body>
</html>
