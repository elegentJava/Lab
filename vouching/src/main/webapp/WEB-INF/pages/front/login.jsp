<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="common/common.jsp" %>
<script type="text/javascript" src="<%=bp%>/js/lib/md5.js" ></script>
<script type="text/javascript" src="<%=bp%>/js/front/login.js"></script>
<title>登录页面</title>

</head>

<body style="background: url(<%=bp%>/images/bg2.jpg) repeat-x top; background-color: #FFFFFF">
	<table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="251" valign="middle" style="padding-left: 70px">
				<img src="<%=bp%>/images/img7.jpg" width="599" height="169" />
			</td>
		</tr>
		<tr>
			<td style="background: url(<%=bp%>/images/img8.jpg) no-repeat right bottom; padding-top: 30px; font-size: 14px; color: #004a6e" height="400" align="center" valign="top">
				<form action="/vouching/user/loginValidate" id="loginForm" style="line-height: 31px;" method="post" onsubmit="return false">
					<table style="padding: 2px; margin-left: 320px" width="70%" height="90" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td width="10%" align="center">
								<span style="font-size: 1em">用户名：</span>
							</td>
							<td width="80%" align="left">
								<input name="account" id="account" style="border: 1px solid #CCCCCC; height: 18; width: 140px; border-radius: 6px;" type="text" size="15"/> 
								<span id="errorMsg" style="color: red; font-size: 1em"></span>
							</td>
						</tr>
						<tr>
							<td align="center" width="10%">
								<span style="font-size: 1em">密&nbsp;码：</span>
							</td>
							<td width="80%" align="left">
								<input type="password" style="border: 1px solid #CCCCCC; height: 18; width: 140px; border-radius: 6px;" name="password" id="password" size="15"/>
							</td>
						</tr>
						<tr>
							<td width="10%"></td>
							<td width="70%" align="left">
								<input id="login" style="padding: 3px 5px; font-family:微软雅黑;"  type="submit" value="登陆"/>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
	<jsp:include page="common/foot.jsp" />
</body>
</html>
