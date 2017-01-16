<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="../common/common.jsp" %>
<link href="<%=bp%>/css/front/style2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=bp%>/js/front//resource/download.js"></script>
<title>下载文件</title>
</head>
  
 <body>
	<table width="50%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="600" align="center" valign="top" class="left_td"></td>
			<td align="center" valign="top" class="right_td">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80" rowspan="3">
							<img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" /></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" class="weizhi">&nbsp; 
							<strong>首页 ->考试平台 ->章节设置</strong>
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				<table width="90%" border="0" cellspacing="0" cellpadding="0">
					<tr><td height="20"></td></tr>
				</table>
				<table width="90%" border="0" cellspacing="1" cellpadding="0" class="content_table1">
					<thead>
						<tr><td colspan="2" class="teatbbai" align="left">&nbsp;资源列表</td></tr>
						<tr class="teatb" style="color: #fff;" height="25px" align="center">
							<td width="191" align="center">资源名称</td>
							<td width="136" align="center">操作</td>
						</tr>
					</thead>
					<tbody id="downloadList"></tbody>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr><td height="20"></td></tr>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" id="token" value="${token}"/>
	<jsp:include page="../common/foot.jsp" />
</body>
</html>
