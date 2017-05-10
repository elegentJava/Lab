<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp" %>
<script type="text/javascript" src="<%=bp%>/js/lib/jquery-timer.js" ></script>
<script type="text/javascript" src="<%=bp%>/js/front/competition/competition.js" ></script>
<title>知识点竞技</title>
</head>

<body>
	<div>
	<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px">
		<tr>
			<td height="600" align="center" valign="top" class="left_td">
				<table width="230" border="0" cellpadding="0" cellspacing="0" class="left_title">
					<tr><td>竞技积分榜</td></tr>
				</table>
				<table width="230" border="0" cellspacing="1" cellpadding="0" class="content_table1">
					<thead>
						<tr>
							<td class="bai12">用户名</td>
							<td class="bai12">积分</td>
							<td class="bai12">状态</td>
						</tr>
					</thead>
					<tbody id="rankList"></tbody>
				</table>
			</td>
			<td align="center" valign="top" class="right_td">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80" rowspan="3"><img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" /></td>
						<td>&nbsp;</td>
					</tr>
					<tr><td colspan="2" class="weizhi">&nbsp;<strong>首页 ->互动平台 ->知识点竞技</strong></td></tr>
					<tr><td colspan="2">&nbsp;</td></tr>
				</table>
				<table width="80%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td height="120" align="center" valign="middle">
							<h3>匹配规则：</h3>
							<h4>1.五人一组进行匹配</h4>
							<h4>2.匹配过程中只能通过注销帐号与点击匹配页面的关闭按钮取消匹配</h4>
							<h4>3.匹配成功后，由系统随机生成15道题目进行答题</h4>
							<h4>4.答题过程中如果退出，积分为0</h4>
						</td>
					</tr>
					<tr>
						<td height="120" align="center" valign="middle">
							<button id="match">开始匹配</button>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" id="token" value="${token}"/>
	<jsp:include page="../common/foot.jsp" />
	</div>
</body>
</html>
