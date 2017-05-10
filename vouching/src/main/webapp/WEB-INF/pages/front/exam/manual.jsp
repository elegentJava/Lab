<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp"%>
<link rel="stylesheet" href="<%=bp%>/js/lib/tree/treestyles.css" type="text/css"></link>
<script type="text/javascript" src="<%=bp%>/js/lib/tree/dtreebase.js"></script>
<script type="text/javascript" src="<%=bp%>/js/front/exam/manual.js"></script>
<title>手工组卷</title>
<style>
.cc {
	border: 1px dashed #ccc;
}

.cc td {
	border: none;
}

.sjdiv {
	margin-left: 30px;
	margin-top: 10px;
	font-family: "黑体";
}

.TypeTitle {
	background-color: #f4fbf8;
	BORDER-LEFT: #e0e0e0 1px solid;
	FONT-WEIGHT: bold;
	FONT-SIZE: 12pt;
	height: 30px;
	line-height: 30px;
}
</style>
</head>
<body>
	<div style="width: 100%; height: auto">
		<div style="width: 20%; height: auto; float: left;">
			<table><tr><td><div id="dtree" class="dtree"></div></td></tr></table>
			<br /><br />
			<table>
				<tr>
					<td>&nbsp;输入考试名称<br /><input name="name" id="name" /></td>
					<td><span id="errorMsg" style="color: red"></span></td>
				</tr>
				<tr><td>&nbsp;输入考试备注<br /><input name="bak" id="bak" /></td></tr>
				<tr><td><input type="button" id="saveExam" value="保存试卷并预览" /></td></tr>
			</table>
		</div>
		<div style="width: 80%; height: auto; float: left;">
			<table width="84%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px">
				<tr>
					<td height="600" align="left" valign="top" class="left_td" style="width:0px"></td>
					<td align="left" valign="top" class="right_td">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="80" rowspan="3">
									<img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" />
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" class="weizhi">&nbsp;首页 ->考试平台 ->手动组卷</td>
							</tr>
							<tr><td colspan="2">&nbsp;</td></tr>
						</table>
						<br />
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr id="types"></tr>
							<tr id="scores"></tr>
						</table>
						<br />
						<table cellpadding="0" cellspacing="0" class="juhuang12">
							<tr>
								<td align="right" style="font-size: 14px; font-weight: bold">
									<div style="font-size: 20pt">
										<font size="2px"> 请选择试题类型：</font> 
										<select id="source"></select> 
										<font size="2px">请选择难易程度：</font> 
										<select id="levels"></select>
									</div>
								</td>
							</tr>
						</table>
						<table border="1" style="margin-top: 10px;" cellpadding="0" cellspacing="0" width="100%" class="cc">
							<tbody id="questionList"></tbody>
							<tfoot id="pager">
								<%@include file="../common/pager.jsp" %>
							</tfoot>
						</table> <br/> <br/>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr><td height="20"></td></tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<input type="hidden" id="token" value="${token}"/>
	<input type="hidden" id="chapterIdHidden" value="1"/>
	<input type="hidden" id="sourceHidden" value="1"/>
	<input type="hidden" id="levelHidden" value="0"/>
</body>
</html>
