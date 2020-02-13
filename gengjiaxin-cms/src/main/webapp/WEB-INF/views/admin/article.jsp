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
	function check(){
	var title = $("[name='title']").val();
	var status = $("[name='status']").val();
	var url = "/article/selectsByAdmin?title="+title+"&status="+status;
	$("#content-wrapper").load(url);
}

//分页方法
	function goPage(pageNum){
		var url="/article/selectsByAdmin?pageNum="+pageNum+"&"+$("#f1").serialize();
		$("#content-wrapper").load(url);
	}
	
//查看详情
function look(id){
	$.post(
		"/article/select",
		{id:id},
		function(obj){
			$("#exampleModalLabel").html(obj.title);	
			$(".modal-body").html(obj.content);
			$("[name='id']").val(obj.id);
		}
	);
}

//审核
function changeStatus(status,obj){
	var id=$(obj).val();
	//获得谁要修改为那种状态
	 $.post("/article/updateArcitle",{id:id,status:status},function(msg){
		if(msg){
			alert("审核成功");
			$("#content-wrapper").load("/article/selectsByAdmin");
		}else{
			alert("审核失败");
		}
	}) 
}
</script>
</head>
<body>
	<div style="padding: 0 10px 0 10px">
		<div>
			<form id="f1">
				文章标题：<input type="text" name="title" value="${article.title}" />
				状态：<select name="status">
					<option value="-2">请选择</option>
					<option value="0" ${article.status==0?'selected':''}>待审</option>
					<option value="1" ${article.status==1?'selected':''}>审核通过</option>
					<option value="-1" ${article.status==-1?'selected':''}>审核未通过</option>
				</select>
				<button type="button" class="btn btn-success" onclick="check()">搜索</button>
			</form>

		</div>
		<br>
		<table class="table table-bordered">
			<tr>
				<td>编号</td>
				<td>标题</td>
				<td>图片</td>
				<td>作者</td>
				<td>点击量</td>
				<td>是否热门</td>
				<td>是否审核通过</td>
				<td>创建时间</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${list}" var="a" varStatus="count">
				<tr>
					<td>${a.id}</td>
					<td>${a.title}</td>
					<td><img src="/pic/${a.picture}" width="50px" height="50px">
					</td>
					<td>${a.user.username}</td>
					<td>${a.hits}</td>
					<td><c:if test="${a.hot==0}">普通</c:if> <c:if
							test="${a.hot==1}">热门</c:if></td>
					<td>${a.status==0?'待审':a.status==1?'审核通过':'审核不通过'}</td>
					<td><fmt:formatDate value="${a.created}" pattern="yyyy-MM-dd"/></td>
					<td>
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#exampleModal" onclick="look(${a.id})">详情</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/views/public/page.jsp"></jsp:include>

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
	</div>
</body>
</html>
