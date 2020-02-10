<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/js/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="/resources/css/index3.css">
<script type="text/javascript">

	function submit(){
		location = "selectPlans";
	}
	function update(id){
		location = "selectOnePlan?id="+id;
	}
</script>
</head>
<body>
	<table>
		<tr>
			<td></td>
			<td colspan="10">投资项目详情</td>
		</tr>
		<tr>
			<td>项目名称</td>
			<td>${plan.name}</td>
		</tr>
		<tr>
			<td>投资金额</td>
			<td>${plan.amount}元</td>
		</tr>
		<tr>
			<td>分管领导</td>
			<td>${plan.manager}</td>
		</tr>
		<tr>
			<td>所属部门</td>
			<td>${plan.department.name}</td>
		</tr>
		<tr>
			<td>项目说明</td>
			<td>${plan.content}</td>
		</tr>
	</table>
	<button onclick="submit()">返回主页</button>
	<button onclick="update(${plan.id})">更新数据</button>
</body>
</html>