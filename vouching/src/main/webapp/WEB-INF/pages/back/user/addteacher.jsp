<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<%@include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=bp%>/js/back/user/addteacher.js"></script>
<title>添加教师信息</title>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-pencil-square-o"></span>添加教师信息</strong>
		</div>
		<div class="body-content form-x">
			<div class="form-group">
				<div class="label"><label>账号：</label></div>
				<div class="field">
					<input type="text" class="input w50" value="" id="account" data-validate="required:请输入账号" />
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label"><label>姓名：</label></div>
				<div class="field">
					<input type="text" class="input w50" value="" id="name" data-validate="required:请输入姓名" />
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label"><label>Email：</label></div>
				<div class="field">
					<input type="text" class="input w50" value="" id="email" data-validate="required:请输入Email" />
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label"><label>性别：</label></div>
				<div class="field" style="padding-top:8px;">
					男 <input name="sex" value="1" type="radio" checked="checked" /> 
					女 <input name="sex" value="0" type="radio" />
				</div>
			</div>
			<div class="form-group">
				<div class="label"><label>班级：</label></div>
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
					<button id="add" class="button bg-main icon-check-square-o">马上添加</button>
					<div></div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="token" value="${token}"/>
</body>
</html>