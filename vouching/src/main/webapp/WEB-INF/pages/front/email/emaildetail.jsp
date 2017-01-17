<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@include file="../common/common.jsp"%>
<title>查看信件</title>
<script type="text/javascript" src="<%=bp%>/js/front/email/emaildetail.js"></script>
</head>

<body>
	<table width="100%" cellspacing="0" cellpadding="0">
		<caption>查看信件</caption>
		<tr>
			<td width="60" height="20" align="left" valign="middle">主题</td>
			<td><input id="subject" type="text" readonly style="background-color:#FFFFFF; border:1px solid #4c5e2a; width:459px; height:20px;" /></td>
		</tr>
		<tr><td height="10" colspan="2" align="left" valign="middle"></td></tr>
		<tr>
			<td align="left">发件人</td>
			<td><input id="sender" type="text" readonly style="background-color:#FFFFFF; border:1px solid #4c5e2a; width:459px; height:20px;" /></td>
		</tr>
		<tr><td height="10" colspan="2" align="left" valign="middle"></td></tr>
		<tr>
			<td align="left">发件人</td>
			<td><input id="receiver" type="text" readonly style="background-color:#FFFFFF; border:1px solid #4c5e2a; width:459px; height:20px;" /></td>
		</tr>
		<tr><td height="10" colspan="2" align="left" valign="middle"></td></tr>
		<tr>
			<td align="left">内容</td>
			<td><textarea id="content" readonly style="background-color:#FFFFFF; border:1px solid #4c5e2a; width:519px;height:200px; overflow:auto"></textarea></td>
		</tr>
	</table>
	<input type="hidden" id="token" value="${token}"/>
</body>
</html>
