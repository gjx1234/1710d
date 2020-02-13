<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 引入bootstrap样式 -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/sb-admin.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/index.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function look(id){
		var url = "indexs/select?id="+id;
		location=url;
	}
</script>
</head>
<body>
	<div class="container-fluid">

		<!-- top-->
		<div class="row">
			<div class="col-md-12"
				style="background-color: #222222; height: 40px;">
				<jsp:include page="/WEB-INF/views/index/top.jsp"></jsp:include>
			</div>
		</div>


		<!--middle  -->
		<div class="row" style="margin-top: 10px">
			<!--middle_left -->
			<div class="col-md-2">

				<ul style="margin-top: 8px">

					<li class="channel-item ${article.channel_id==null?'active':'' }"><a
						href="/index" class="channel">热门</a></li>

					<c:forEach items="${channelList }" var="c">

						<li class="channel-item" ${article.channel_id==c.id?'active':'' }><a
							href="/index?channel_id=${c.id}" class="channel">${c.name }</a></li>


					</c:forEach>
				</ul>
			</div>

			<!--  middle-->
			<div class="col-md-7 split" id="article">
				<!-- 第一次进入 没有选择栏目  默认显示轮播图和热门文章 -->
				<c:if test="${article.channel_id==null}">

					<!--  轮播图-->
					<div id="carouselExampleCaptions" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleCaptions" data-slide-to="0"
								class="active"></li>
							<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
							<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner">
							<c:forEach items="${slideList }" var="s" varStatus="i">

								<div class="carousel-item ${i.index==0?'active':'' }">
									<img src="/pic/${s.picture}" class="d-block w-100" alt="..."
										width="280px" height="200px">
									<div class="carousel-caption d-none d-md-block">
										<h5>${s.title }</h5>

									</div>
								</div>
							</c:forEach>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleCaptions"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
							role="button" data-slide="next"> <span
							class="carousel-control-next-icon" aria-hidden="true"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>

					<!--热门文章  -->
					<div id="hotArticle">

						<ul class="list-unstyled">
							<c:forEach items="${articleList }" var="a">
								<li class="media"><img src="/pic/${a.picture }"
									class="mr-3" alt="..." width="160px" height="100px">
									<div class="media-body text-center">
										<h5 class="mt-0 mb-1 ">
											<a href="javascript:look(${a.id})" style="font-size: 15px;"
												data-target="#exampleModal">${a.title }</a>
										</h5>
										<br> ${a.user.username }&nbsp;&nbsp;&nbsp;
										<fmt:formatDate value="${a.created}" pattern="yyyy-MM-dd" />
									</div></li>
								<hr>
							</c:forEach>
						</ul>
					</div>
					<ul>
							<li style="float:left;margin:8px;margin-right:10px">友情链接</li>
							<c:forEach items="${links}" var="link">
								<li style="float:left;margin:8px;margin-right:10px"><a href="${link.url}">${link.text}</a></li>
							</c:forEach>
						</ul>
				</c:if>
				<!-- 选择了栏目  只显示栏目下的 文章 和分类-->
				<c:if test="${article.channel_id!=null}">
					<!-- 栏目下分类菜单 -->
					<div class="subchannel">
						<ul class="sub-list" style="width: 660px;">
							<li
								class="sub-item ${article.category_id==null?'sub-selected':''}"><a
								href="/index?channel_id=${article.channel_id}">全部</a></li>
							<c:forEach items="${categorys}" var="category">
								<li
									class="sub-item ${article.category_id==category.id?'sub-selected':'' }"><a
									href="/index?channel_id=${article.channel_id }&category_id=${category.id}">${category.name }</a></li>
							</c:forEach>

						</ul>
					</div>
					<hr>

					<div id="article">
						<ul class="list-unstyled">
							<c:forEach items="${info.list }" var="a">
								<li class="media"><img src="/pic/${a.picture }"
									class="mr-3" alt="..." width="160px" height="100px">
									<div class="media-body text-center">
										<h5 class="mt-0 mb-1 ">
											<a href="/indexs/select?id=${a.id }" onclick="look(${a.id})"
												style="font-size: 15px;" data-target="#exampleModal">${a.title }</a>
										</h5>
										<br> ${a.user.username }&nbsp;&nbsp;&nbsp;
										<fmt:formatDate value="${a.created }" pattern="yyyy-MM-dd" />
									</div></li>
								<hr>
							</c:forEach>
						</ul>
					</div>
				</c:if>


			</div>
			<!-- middle_right-->
			<div class="col-md-3 split min_h_500">
				<div class="card" style="width: 18rem;">
					<div class="card-header">最新文章</div>
					<ul class="list-group list-group-flush">
						<c:forEach items="${newArcitles}" var="a">
							<li class="list-group-item"><a
								href="javascript:look(${a.id})">${a.title}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>

	</div>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel"></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" name="id" class="btn btn-success"
						data-dismiss="modal" onclick="changeStatus(1,this)">通过</button>
					<button type="button" name="id" class="btn btn-primary"
						onclick="changeStatus(-1,this)">不通过</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>