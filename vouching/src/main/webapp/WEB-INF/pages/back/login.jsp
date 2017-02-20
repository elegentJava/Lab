<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<%@include file="common/common.jsp" %>
<script type="text/javascript" src="<%=bp%>/js/lib/md5.js" ></script>
<script type="text/javascript" src="<%=bp%>/js/back/login.js"></script>
<title>登录</title>
</head>
<body>
	<div class="bg"></div>
	<div class="container">
		<div class="line bouncein">
			<div class="xs6 xm4 xs3-move xm4-move">
				<div style="height:150px;"></div>
				<div class="media media-y margin-big-bottom"></div>
				<form action="index.html" method="post">
					<div class="panel loginbox">
						<div class="text-center margin-big padding-big-top"><h1>后台管理中心</h1></div>
						<div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
							<div id="errorMsg" class="form-group"><div class="input-help"><ul><li style="color: red;"></li></ul></div></div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input id="account" type="text" class="input input-big" name="name" placeholder="登录账号" data-validate="required:请填写账号" /> 
									<span class="icon icon-user margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input id="password" type="password" class="input input-big" name="password" placeholder="登录密码" data-validate="required:请填写密码" /> 
									<span class="icon icon-key margin-small"></span>
								</div>
							</div>
						</div>
						<div style="padding:30px;">
							<input id="login" type="button" class="button button-block bg-main text-big input-big" value="登录">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>