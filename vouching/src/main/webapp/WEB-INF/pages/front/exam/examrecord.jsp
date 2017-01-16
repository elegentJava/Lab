<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp"%>
<link href="<%=bp%>/css/style2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=bp%>/js/front/exam/examrecord.js" ></script>
<title>查看历史成绩</title> 
</head>
<body>
	<table width="50%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="600" align="center" valign="top" class="left_td"></td>
			<td align="center" valign="top" class="right_td">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80" rowspan="3"><img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" /></td>
						<td>&nbsp;</td>
					</tr>
					<tr><td colspan="2" class="weizhi">&nbsp;<strong>首页 ->考试平台 ->查看历史成绩</strong></td></tr>
				</table>
				<table width="80%" border="0" cellspacing="1" cellpadding="0" class="content_table1">
					<tr><td colspan="3" class="teatbbai" align="left">历史成绩</td></tr>
					<tr class="teatb" style="color: #fff;" align="center">
						<td width="40%" align="center">考试名称</td>
						<td width="25%" align="center">提交时间</td>
						<td width="15%" align="center">分数</td>
					</tr>
					<tbody id="examRecordList"></tbody>
					<tfoot id="pager">
						<%@include file="../common/pager.jsp" %>
					</tfoot>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" id="token" value="${token}"/>
	<jsp:include page="../common/foot.jsp" />
</body>
</html>
