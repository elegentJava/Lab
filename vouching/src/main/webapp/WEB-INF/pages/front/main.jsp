<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统首页</title>
<%@include file="common/common.jsp" %>
<link href="<%=bp%>/css/front/style2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=bp%>/js/front/main.js"></script>
</head>

<body>
	<table width="70%" border="0" align="center" cellpadding="0"
		cellspacing="0" style="margin-top: 10px">
		<tr>
			<td align="center" valign="top" class="left_td">&nbsp;</td>
			<td align="center" valign="top" class="right_td">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80" rowspan="3"><img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" /></td>
						<td>&nbsp;</td>
					</tr>
					<tr><td colspan="2" class="weizhi">&nbsp;<strong>欢迎使用国际贸易函电实训系统</strong></td></tr>
					<tr><td colspan="2">&nbsp;</td></tr>
				</table>
			</td>
		</tr>
	</table>
	<table width="70%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px;min-width:1000px" height="500">
		<tr height="258">
			<td style="vertical-align:top;" width="50%" align="center">
				<div align="left">
					<table width='100%' border="0" cellspacing="1" cellpadding="0" style="margin-top: 0px;table-layout:fixed">
						<thead>
							<tr><td colspan="4" class="teatbbai" align="left"><a style="float:right" id="emailFreash" href="javascript:;">刷新&nbsp;</a>&nbsp;未读站内短信</td></tr>
							<tr class="teatb" style="color: #fff;" height="25px" align="center">
								<td align="center" width="7%">发件人</td>
								<td align="center" width="35%">信件标题</td>
								<td align="center" width="45%">信件内容</td>
								<td align="center" width="12%">信件日期</td>
							</tr>
						</thead>
						<tbody id="emailList"></tbody>
						<tfoot id="more">
							<tr class="teatbbai" align="right"><td colspan="4"><a href="/vouching/forward/forwardEmailStation?token=${token}">更多站内信</a></td></tr>
						</tfoot>
					</table>
				</div>
			</td>
			<td style="background:url(<%=bp%>/images/img6.jpg) no-repeat right bottom;">
			</td>
		</tr>
	</table>
	<input type="hidden" id="token" value="${token}"/>
</body>
</html>