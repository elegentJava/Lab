<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="../common/common.jsp" %>
<link rel="stylesheet" href="<%=bp%>/css/front/style2.css" type="text/css"></link>
<link rel="stylesheet" href="<%=bp%>/js/lib/tree/treestyles.css" type="text/css"></link>
<script type="text/javascript" src="<%=bp%>/js/lib/tree/dtreebase.js"></script>
<script type="text/javascript" src="<%=bp%>/js/front/resource/correspondence.js"></script>
<title>资源平台-教学模块</title>
</head>
<body>
<div style="width: 100%; height: auto">
		<div style="width: 16%; height: auto; float: left; margin-left: 20px; margin-top: 30px">
			<div id="dtree" class="dtree"></div>
		</div>
		<div style="width: 80%; height: auto; float: left;">
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px;position: relative;">
				<tr>
					<td align="center" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="80" rowspan="2"><img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" /></td>
								<td class="weizhi">&nbsp;<strong>首页 ->资源平台 ->教学板块</strong></td>
							</tr>
							<tr><td class="weizhi_under">&nbsp;</td></tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin: 10px auto;table-layout: fixed;">
							<tr height="30">
								<td align="left" valign="top" class="t2" width="15%"><strong>
										<a href="javascript:;" id="sendHD">发送函电</a>
								</strong></td>
								<td width="84%"></td>
							</tr>
							<tr>
								<td>正文:</td>
								<td align="left" style="word-wrap:break-word;" id="english"></td>
							</tr>
							<tr>
								<td></td>
								<td><hr /> <br /></td>
							</tr>
							<tr>
								<td>可替换语句:</td>
								<td id="replace"></td>
							</tr>
							<tr>
								<td></td>
								<td><hr /> <br /></td>
							</tr>
							<tr>
								<td>翻译:</td>
								<td align="left" id="translate"></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div id="showdiv" style="display: "></div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<input type="hidden" id="token" value="${token}" />
	<input type="hidden" id="categoryId" value="26" />
</body>
</html>
