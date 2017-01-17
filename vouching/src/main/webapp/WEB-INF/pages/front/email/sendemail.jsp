<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp"%>
<title>收件箱</title>
<link rel="stylesheet" href="<%=bp%>/css/front/style2.css" type="text/css"></link>
<script type="text/javascript" src="<%=bp%>/js/front/email/sendemail.js"></script>
</head>

<body topmargin="0" leftmargin="0">
	<form name="frm" id="frm" action="#" method="post">
		<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="100%" height="400">
			<tr>
				<td width="100%" bgcolor="#1D6300" height="25px">
					<p style="margin-left: 20">
						<font color="#FFFFFF"> <span style="font-size: 9pt; font-weight: 700">&nbsp;&nbsp;站内短信发送</span></font>
					</p>
				</td>
			</tr>
			<tr>
				<td width="100%" valign="top">
					<table cellpadding="0" style="border-collapse:collapse;border:1px solid #004A6E" cellspacing="0" width="100%" border="0">
						<tr>
							<td width="10%" align="right">
								<p style="margin-right: 5">
									<span style="font-size: 9pt">站内短信标题</span>
								</p>
							</td>
							<td width="80%"><input type="text" id="subject" name="subject" value="" size="80" /></td>
						</tr>
						<tr>
							<td width="10%" align="right"><span style="font-size: 9pt">短信收件人</span></td>
							<td width="80%">
								<input type="text" id="receivers" value="" size="80" /> 
								<select id="classList" style="width: 120px"></select>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right"><span style="font-size: 9pt">站内短信内容</span></td>
							<td width="80%" valign="top">
								<div id="globalDiv" style="background: red;">
									<div id="areadate" style="float: left;">
										<textarea rows="13" name="content" id="content" cols="82" style="width: 540; font-family: 宋体; font-size: 9pt; height: 220; border: 1px solid #CCCCCC;resize:none;"></textarea>
									</div>
									<div id="stuDiv" style="float: left;margin-left: 5px;height: 220px">
										<select id="mutiClass" size="12" multiple="multiple" style="WIDTH: 120px;margin-top: 2px;height: 185px" >

										</select>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="button" value="发  送" id="send" style="font-size: 9pt; border: 1px solid #CCCCCC; background-color: #FFFFFF" />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="font-family: 黑体;font-size: 12px;">
								&nbsp;&nbsp;
								<font color="red">*</font>&nbsp;选择全部学生即为给该班级发送； 
								<font color="red">*</font>&nbsp;Shift+单击 或 Ctrl+单击 可选择多个；
							</td>
						</tr>
					</table>
				</td>
		</table>
	</form>
	<input type="hidden" id="token" value="${token}"/>
</body>
</html>
