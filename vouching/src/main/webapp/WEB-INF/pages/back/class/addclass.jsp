<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<%@include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=bp%>/js/back/class/addclass.js"></script>
<title>添加班级信息</title>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-pencil-square-o"></span>添加班级信息</strong>
		</div>
		<div class="body-content form-x">
			<div class="form-group">
				<div class="label"><label>名称：</label></div>
				<div class="field">
					<input type="text" class="input w50" value="" id="className" data-validate="required:请输入名称" />
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label"><label>备注：</label></div>
				<div class="field">
					<input type="text" class="input w50" id="bak"/>
				</div>
			</div>
			<div class="form-group">
				<div class="label"><label>状态：</label></div>
				<div class="field" style="padding-top:8px;">
					激活<input name="status" value="1" type="radio" checked="checked" /> 
					不激活 <input name="status" value="0" type="radio" />
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