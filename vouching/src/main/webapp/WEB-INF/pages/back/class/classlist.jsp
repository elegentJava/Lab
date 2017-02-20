<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<%@include file="../common/common.jsp" %>
<script type="text/javascript" src="<%=bp%>/js/back/class/classlist.js" ></script>
<title>班级信息列表</title>
</head>
<body>
	<div id="main" class="panel admin-panel">
		<div class="panel-head"><strong class="icon-reorder">班级信息列表</strong></div>
		<div class="padding border-bottom">
			<ul class="search">
				<li>
					<button id="checkall" class="button border-green"><span class="icon-check"></span> 全选</button>
					<button id="multiDelete" class="button border-red"><span class="icon-trash-o"></span> 批量删除</button>
					<a class="button border-yellow" href="/vouching/forward/forwardAddClass?token=${token}"><span class="icon-plus-square-o"></span> 添加班级</a>
					<a class="button border-yellow" href="/vouching/forward/forwardClassAssign?token=${token}"><span class="icon-plus-square-o"></span> 指派</a>
				</li>
			</ul>
		</div>
		<table class="table table-hover text-center">
			<tr>
				<th>&nbsp;&nbsp;&nbsp;</th>
				<th>班级名称</th>
				<th>备注</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			<tbody id="classList"></tbody>
			<tfoot id="pager">
				<%@include file="../common/pager.jsp" %>
			</tfoot>
		</table>
	</div>
	<input type="hidden" id="token" value="${token}"/>
</body>
</html>