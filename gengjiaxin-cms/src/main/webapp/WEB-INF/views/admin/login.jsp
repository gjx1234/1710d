<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="/resource/css/index.css">
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.css" />
<script type="text/javascript">
	function login() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == null || password == null || username == ''
				|| password == '') {
			$("#alert").html("用户名和密码不能为空");
			$("#alert").show();
			return;
		}

		$.post("/user/login", $("#loginForm").serialize(), function(cju) {
			if (cju.msg == 'true') {
				$("#alert").hide();
				location.href = "/index";
			} else {
				if(cju.msg == '管理员账号'){
					location.href="/admin";
				}else{
					$("#alert").html(cju.msg);
					$("#alert").show();
				}
			}
		}, "json");

	}
	var msg = '${msg}';
	if (msg != null && msg != '') {
		alert(msg);
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/index/top.jsp"></jsp:include>
	<div class="container-fulid" style="margin-top: 6px">
		<div class="row offset-4" style="margin-top: 180px">
			<div class="col-5">
				<h1>欢迎回来</h1>
				<div class="alert alet-success" id="alert" style="display: none"></div>
				<form id="loginForm">
					<div class="form-group">
						<input type="email" class="form-control" id="username"
							name="username" placeholder="请输入用户名">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="请输入密码"
							id="password" name="password">
					</div>

					<div class="row">
						<div class="col-4">
							<button type="button" class="btn btn-primary" onclick="login();">登录</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>