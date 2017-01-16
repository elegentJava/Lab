<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<%@include file="../common/common.jsp" %>
<script type="text/javascript" src="<%=bp%>/js/back/user/studentlist.js" ></script>
<script type="text/javascript" src="<%=bp%>/js/ajaxfileupload.js" ></script>
<title>学生信息列表</title>
</head>
<body>
	<div id="main" class="panel admin-panel">
		<div class="panel-head"><strong class="icon-reorder">学生信息列表</strong></div>
		<div class="padding border-bottom">
			<ul class="search">
				<li>
					<button id="checkall" class="button border-green"><span class="icon-check"></span> 全选</button>
					<button id="multiDelete" class="button border-red"><span class="icon-trash-o"></span> 批量删除</button>
					<a class="button border-yellow" href="/vouching/forward/forwardAddStudent?token=${token}"><span class="icon-plus-square-o"></span> 添加学生</a>
					<input id="userFile" name="userFile" type="file" class="button border-green"/>
					<button id="batchInsert" class="button border-green"><span class="icon-plus-square-o"></span> 批量导入</button>
					<a class="button border-yellow" href="/vouching/template/user_template.xlsx"><span class="icon-download"></span> 下载模板</a>
				</li>
			</ul>
		</div>
		<table class="table table-hover text-center">
			<tr>
				<th>&nbsp;</th>
				<th>账号(学号)</th>
				<th>姓名</th>
				<th>性别</th>
				<th>邮箱</th>
				<th>是否在线</th>
				<th>上次登录时间</th>
				<th>班级</th>
				<th>操作</th>
			</tr>
			<tbody id="studentList"></tbody>
			<tfoot id="pager">
				<%@include file="../common/pager.jsp" %>
			</tfoot>
		</table>
	</div>
	<input type="hidden" id="token" value="${token}"/>
</body>
</html>