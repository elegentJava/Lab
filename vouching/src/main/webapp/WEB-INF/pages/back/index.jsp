<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<%@include file="common/common.jsp" %>
<script type="text/javascript" src="<%=bp%>/js/back/index.js"></script>
<title>后台管理中心</title>
</head>
<body style="background-color:#f2f9fd;">
	<div class="header bg-main">
		<div class="logo margin-big-left fadein-top">
			<h1>
				<img src="<%=bp%>/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心
			</h1>
		</div>
		<div class="head-l">
			<a class="button button-little bg-green" href="/vouching/forward/login" target="_blank"><span class="icon-home"></span>前台首页</a> &nbsp;&nbsp;
			<a id="logout" class="button button-little bg-red" href="javascript:;"><span class="icon-power-off"></span> 退出登录</a>
		</div>
	</div>
	<div class="leftnav">
		<div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
		<h2><span class="icon-user"></span>用户管理</h2>
		<ul style="display:block">
			<li><a href="/vouching/forward/forwardStudentList?token=${token}" target="right"><span class="icon-caret-right"></span>学生管理</a></li>
			<li><a href="/vouching/forward/forwardTeacherList?token=${token}" target="right"><span class="icon-caret-right"></span>教师管理</a></li>
			<li><a href="/vouching/forward/forwardClassList?token=${token}" target="right"><span class="icon-caret-right"></span>班级管理</a></li>
		</ul>
		<h2><span class="icon-user"></span>资源管理</h2>
		<ul style="display:block">
			<li><a href="/vouching/forward/forwardChapterList?token=${token}" target="right"><span class="icon-caret-right"></span>章节管理</a></li>
			<li><a href="/vouching/forward/forwardGlossaryList?token=${token}" target="right"><span class="icon-caret-right"></span>词汇管理</a></li>
			<li><a href="#" target="right"><span class="icon-caret-right"></span>语句管理</a></li>
			<li><a href="#" target="right"><span class="icon-caret-right"></span>函电管理</a></li>
		</ul>
		<h2><span class="icon-user"></span>杂项管理</h2>
		<ul style="display:block">
			<li><a href="#" target="right"><span class="icon-caret-right"></span>试卷清理</a></li>
			<li><a href="#" target="right"><span class="icon-caret-right"></span>邮件清理</a></li>
		</ul>
		<h2><span class="icon-user"></span>个性管理</h2>
		<ul style="display:block">
			<li><a href="#" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
			<li><a href="#" target="right"><span class="icon-caret-right"></span>个性化设置</a></li>
		</ul>
	</div>
	<ul class="bread">
		<li><a href="javascript:;" target="right" class="icon-home">首页</a></li>
		<li><a href="javascript:;" id="a_leader_txt">网站信息</a></li>
	</ul>
	<div class="admin">
		<iframe scrolling="auto" rameborder="0" name="right" width="100%" height="100%"></iframe>
	</div>
	<input type="hidden" id="token" value="${token}"/>
</body>
</html>