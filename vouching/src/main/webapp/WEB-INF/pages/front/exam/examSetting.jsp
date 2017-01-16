<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp"%>
<link href="<%=bp%>/css/style2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=bp%>/js/front/exam/examsetting.js"></script>
<title>考试设置</title>
</head>

<body>
	<table width="50%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px">
		<tr>
			<td height="600" align="center" valign="top" class="left_td"></td>
			<td align="center" valign="top" class="right_td">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80" rowspan="3"><img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" /></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" class="weizhi">&nbsp;<strong>首页 ->考试平台 ->考试设置</strong></td>
					</tr>
					<tr><td colspan="2">&nbsp;</td></tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="20"></td></tr></table>
					<table width="90%" border="0" cellspacing="1" cellpadding="0" class="content_table1">
						<thead>
							<tr><td colspan="7" class="teatbbai" align="left">&nbsp;激活试卷列表</td></tr>
							<tr class="teatb" style="color: #fff;" height="25px" align="center">
								<td width="15%" align="center">点击改变激活状态</td>
								<td width="20%" align="center">试卷名</td>
								<td width="6%" align="center">预览</td>
								<td width="6%" align="center">删除</td>
								<td width="20%" align="center">备注</td>
								<td width="13%" align="center">提交时间</td>
								<td width="10%" align="center">班级</td>
							</tr>
						</thead>
						<tbody id="activeList"></tbody>
						<tfoot id="activePager">
							<%@include file="../common/pager.jsp" %>
						</tfoot>
					</table>
					<table width="90%" border="0" cellspacing="1" cellpadding="0" class="content_table1">
						<thead>
							<tr><td colspan="7" class="teatbbai" align="left">&nbsp;待激活试卷列表</td></tr>
							<tr class="teatb" style="color: #fff;" height="25px" align="center">
								<td width="15%" align="center">点击改变激活状态</td>
								<td width="20%" align="center">试卷名</td>
								<td width="6%" align="center">预览</td>
								<td width="6%" align="center">删除</td>
								<td width="20%" align="center">备注</td>
								<td width="13%" align="center">提交时间</td>
								<td width="10%" align="center">班级</td>
							</tr>
						</thead>
						<tbody id="inactiveList"></tbody>
						<tfoot id="inactivePager">
							<%@include file="../common/pager1.jsp" %>
						</tfoot>
					</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td height="20"></td></tr></table>
			</td>
		</tr>
	</table>
	<input type="hidden" id="token" value="${token}"/>
	<jsp:include page="../common/foot.jsp" />
</body>
</html>
