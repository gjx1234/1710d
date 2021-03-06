<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 引入bootstrap样式 -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$("#link a").click(function() {
			var url = $(this).attr("data");
			$("#center").empty();
			$("#center").load(url);
			//把action样式去除
			$("#link a").attr("class", "list-group-item");
			$(this).attr("class", "list-group-item active")
		})
	})
	function look(id) {
		id = id;
		//根据id  查询文章
		$.post("/article/select", {
			id : id
		}, function(obj) {
			//把值给谁?
			$("#exampleModalLabel").html(obj.title)
			$(".modal-body").html(obj.content);
			$("[name='id']").val(obj.id);
		});
	}
</script>
<body>
	<div class="container">
		<!-- 头 -->
		<div class="row">
			<div class="col-md-12" style="background-color: #3975c6;">
				<img alt="" src="/resource/images/logo.jpg" style="width: 70px"
					class=""> <font style="font-size: 30px; color: pink">CMS个人中心</font>
				<a href="/index" style="color: red">返回主页</a>
			</div>
		</div>

		<!-- 下面 -->
		<div class="row">
			<!-- left.jsp -->
			<div id="link" class="col-md-2"
				style="background-color: #ccc; height: 500px; padding-top: 10px">
				<nav class="nav flex-column">
					<a class="list-group-item active" href="#"
						data="/article/selectArticle">查看文章</a> <a class="list-group-item "
						href="javascript:void(0)" data="/article/toAdd">发布文章</a>
						<a class="list-group-item "	href="javascript:void(0)" data="/article/toAddPicture">发布图片</a> <a
						class="list-group-item " href="#" data="/user/selectOne">资料管理</a>
					<a class="list-group-item " href="#" data="/user/selectCollections">个人收藏</a>
					<a class="list-group-item " href="#" data="/article/addArticle">其他管理</a>

				</nav>
			</div>
			<!--中间区域显示内容  -->
			<div class="col-md-10" id="center">
				<!-- 引入详情页面 -->
				<jsp:include page="/WEB-INF/views/my/article.jsp"></jsp:include>

				<!-- 引入富文本编辑器 -->

				<div style="display: none">
					<jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
				</div>
			</div>
		</div>




	</div>


</body>
</html>