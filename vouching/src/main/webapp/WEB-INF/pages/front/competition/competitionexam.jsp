<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=bp%>/js/front/competition/competitionexam.js"></script>
<title>竞技试题</title>
<style type="text/css">
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
	<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="600" align="center" valign="top" class="left_td">&nbsp;</td>
			<td align="center" valign="top" class="right_td">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80" rowspan="3"><img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" /></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" class="weizhi">&nbsp;<strong>首页 ->竞技平台 -> 开始答题</strong></td>
					</tr>
					<tr><td colspan="2">&nbsp;</td></tr>
				</table>
				<table width="100%" border="0" class="cc" align="right" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td align="left" valign="top" colspan="3">
										<div class="TypeTitle" style="background-color: #f4fbf8; font-family: 黑体;width: 100%">
											<strong id="title" style="float:left; color:#417ac1; font-size: 12pt"></strong>
										</div>
									</td>
								</tr>
								<tbody id="questionList"></tbody>
								<tr><td colspan="2" id="answerText" style="color: red"></td></tr>
								<tfoot><tr><td colspan='2' align="center" class="t2"><strong><input id="showAnswer" type="button" value="查看答案并计分"/>&nbsp;&nbsp;</strong></td></tr></tfoot>	
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<input type="hidden" id="chapterIdHidden" value="1"/>
	<input type="hidden" id="token" value="${token}"/>
	<jsp:include page="../common/foot.jsp" />
</body>
</html>