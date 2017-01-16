<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=bp%>/js/front/exam/auto.js" defer="defer"></script>
<title>无标题文档</title>
</head>

<body>
	<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px">
		<tr>
			<td height="600" align="center" valign="top" class="left_td">&nbsp;</td>
			<td align="center" valign="top" class="right_td" style="background: url(<%=bp%>/images/img9.jpg) no-repeat right bottom;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80" rowspan="3">
							<img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" /></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" class="weizhi">&nbsp;
							<strong>首页 ->考试平台 ->自动试卷</strong></td>
					</tr>
					<tr><td colspan="2">&nbsp;</td></tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr><td height="30">&nbsp;</td></tr>
					<tr>
						<td>
							<table border="0" width="50%">
								<tbody id="questionList"></tbody>
								<tr>
									<td align="left">试卷名称</td>
									<td align="left"><input name="name" id="name" /></td>
									<td><span id="errorMsg" style="color: red"></span></td>
								</tr>
								<tr>
									<td align="left">考试备注</td>
									<td align="left"><input name="bak" id="bak" /></td>
								</tr>
								<tr>
									<td align="left">难易程度</td>
									<td align="left"><select id="levels"></select></td>
								</tr>
								<tr>
									<td></td>
									<td align="left"><input id="saveExam" type="button" value="保存试卷并预览"/></td>
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
