<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<%@include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=bp%>/js/back/class/classassign.js"></script>
<title>班级指派</title>
</head>
<body>
	<div class="panel admin-panel">
		
		<div class="panel-head">
			<strong><span class="icon-pencil-square-o"></span>指派教师</strong>
		</div>
		<div class="body-content form-x">
			<div class="form-group">
				<div class="label"><label>班级：</label></div>
				<div class="field w50">
					<select id="clas" class="input"></select>
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label"><label>教师：</label></div>
				<div class="field">
					<select id="clas" class="input w50"></select>
					<div class="tips"></div>
				</div>
			</div>
			<div id="errorMsg" class="form-group">
				<div class="label"><label></label></div>
				<div class="input-help"><ul><li style="color: red;"></li></ul></div>
			</div>
			<div class="form-group">
				<div class="label"><label></label></div>
				<div class="field">
					<button id="add" class="button bg-main icon-check-square-o">指派</button>
					<div></div>
				</div>
			</div>
		</div>
		
		<div class="panel-head">
			<strong><span class="icon-pencil-square-o"></span>指派学生</strong>
		</div>
		<div class="body-content form-x">
			<div class="form-group">
				<div class="label"><label>班级：</label></div>
				<div class="field">
					<select id="clas" class="input w50"></select>
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label"><label>学生：</label></div>
				<div class="field">
					<select id="clas" class="input w50"></select>
					<div class="tips"></div>
				</div>
			</div>
			<div id="errorMsg" class="form-group">
				<div class="label"><label></label></div>
				<div class="input-help"><ul><li style="color: red;"></li></ul></div>
			</div>
			<div class="form-group">
				<div class="label"><label></label></div>
				<div class="field">
					<button id="add" class="button bg-main icon-check-square-o">指派</button>
					<div></div>
				</div>
			</div>
		</div>
		
	</div>
	<input type="hidden" id="token" value="${token}"/>
</body>
</html>