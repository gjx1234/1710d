<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/sb-admin.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/index.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function look(id) {
		var url = "/indexs/select?id=" + id;
		location = url;
	}
	
	function goPage(pageNum) {
		location = "/indexs/select?pageNum=" + pageNum + "&&id=" + ${article.id};
	}
</script>
</head>
<body>
	<div class="container-fluid">

		<!-- top-->
		<div class="row">
			<div class="col-md-12"
				style="background-color: #222222; height: 40px;">
				<jsp:include page="/WEB-INF/views/index/comments/topContent.jsp"></jsp:include>
			</div>
		</div>
		<center>
			<div style="float: left">

				<!-- 文章 -->
				<div>
					<h2>${article.title}</h2>
					<span>${article.user.username} &nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate
							value="${article.created}" pattern="yyyy-MM-dd" /></span>
				</div>

				<div>
					<form method="post" action="/user/advice">
						<input type="hidden" value="${article.user.id}" name="user.id">
						<input type="hidden" value="${article.id}" name="article.id">
						<textarea style="width: 800px" name="content"
							placeholder="请输入你的评论..."></textarea>
						<input type="submit" value="发布">
					</form>
				</div>


				<!-- 评论 -->
				<div style="width: 1000px">
					<c:if test="${contents.size()!=0}">
						<div>
							<c:forEach items="${contents}" var="c">
								<ul class="list-group list-group-flush">
									<li class="list-group-item">${c.user.username}&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate
											value="${c.created}" pattern="yyyy-MM-dd" /> <br>${c.content}
									</li>
								</ul>
							</c:forEach>
							<jsp:include page="/WEB-INF/views/public/page.jsp"></jsp:include>
							共${info.total} 条评论
						</div>
					</c:if>
					<c:if test="${contents.size()==0}">
						<span style="color: gray">暂无评论，快来抢沙发吧！</span>
					</c:if>
				</div>
			</div>


			<!-- 相关文章 -->

			<div class="col-md-3 split min_h_500" style="float: left;margin-left:350px">
				<div class="card" style="width: 18rem;">
					<div class="card-header">相关文章</div>
					<ul class="list-group list-group-flush">
						<c:forEach items="${articles}" var="a">
							<li class="list-group-item"><a
								href="javascript:look(${a.id})">${a.title}&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate
										value="${a.created}" pattern="yyyy-MM-dd" /></a> <br>${a.content}
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</center>


	</div>
</body>
</html>