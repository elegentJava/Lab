<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp"%>
<link href="<%=bp%>/css/front/style2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=bp%>/js/front/competition/competitionrecord.js"></script>
<title>竞技记录</title>
</head>
<body>
	<table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center" valign="top" class="right_td">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80" rowspan="2">
							<img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" />
						</td>
						<td class="weizhi"><strong> 首页 -> 竞技平台 -> 查看成绩</strong></td>
					</tr>
					<tr><td class="weizhi_under">&nbsp;</td></tr>
				</table>
				<table width="519" border="0" cellspacing="1" cellpadding="0" class="content_table1">
					<thead>
						<tr class="teatb" style="color: #fff;" height="25px" align="center">
							<td width="50%" ><strong>竞技时间</strong></td>
							<td width="50%" ><strong>答对题目</strong></td>
						</tr>
					</thead>
					<tbody id="recordList"></tbody>
					<tfoot id="pager">
						<%@include file="../common/pager.jsp"%>
					</tfoot>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" id="token" value="${token}"/>
</body>
</html>


