<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=bp%>/js/front/practice/testselect.js"></script>
<title>练习平台</title>
</head>

<body>
	<table width="50%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top: 12px">
		<tr>
			<td height="600" align="center" valign="top" class="left_td"> &nbsp;</td>
			<td align="center" valign="top" class="right_td"
				style="background: url(<%=bp%>/images/img9.jpg) no-repeat right bottom;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80" rowspan="3"><img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" /></td>
						<td>&nbsp;</td>
					</tr>
					<tr><td colspan="2" class="weizhi">&nbsp;<strong>首页->练习平台 ->学生练习</strong></td></tr>
					<tr><td colspan="2">&nbsp;</td></tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr><td height="30">&nbsp;</td></tr>
					<tr>
						<td height="55" align="center">
							<table width="60%" border="0" cellspacing="2" cellpadding="2">
								<tr>
									<td align="left"><strong>选择章节</strong></td>
									<td align="left"><select id="chapters"></select></td>
								</tr>
								<tr>
									<td align="left"><strong>选择题型</strong></td>
									<td align="left"><select id="categorys"></select></td>
								</tr>
								<tr>
									<td align="left"><strong>选择题目难度</strong></td>
									<td align="left"><select id="levels"></select></td>
								</tr>
								<tr>
									<td align="left"><strong>选择题目数量</strong></td>
									<td align="left">
										<select id="count">
											<option value="5">5</option>
											<option value="10">10</option>
											<option value="15">15</option>
										</select>
									</td>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr>
									<td></td>
									<td align="left"><input id="startTest" type="button" value="开始练习" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" id="token" value="${token}"/>
	<jsp:include page="../common/foot.jsp" />
</body>
</html>