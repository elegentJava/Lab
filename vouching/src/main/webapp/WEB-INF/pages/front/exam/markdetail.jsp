<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=bp%>/js/front/exam/markdetail.js" defer="defer"></script>
<title>批阅试卷</title>
<style>
.cc {
	border: 1px dashed #ccc;
	margin: 65px;
}

.cc td {
	border: none;
}

.sjdiv {
	margin-left: 30px;
	margin-top: 10px;
	font-family: "黑体";
}

#ztys {
	font-family: arial;
	font-size: 10pt
}
</style>
</head>
<body>
	<br/><br/>
	<center>
		<div class="sjdiv">
			<font style="font-size: 15px">
				<strong id="examName"></strong>&nbsp;&nbsp;&nbsp;&nbsp;
				<strong id="username"></strong>&nbsp;&nbsp;&nbsp;&nbsp;
				<strong id="className"></strong>
			</font>
		</div>
	</center>
	<br/>
	<table border="1" class="cc" cellspacing=0 style="margin-top: 10px" width="80%">
	
		<!-- 单选题 -->
		<tr>
			<td colspan="2">
				<div class="TypeTitle" style="background-color: #f4fbf8;font-family: 黑体; font-size: 20px;">
					<strong><font color="#417ac1" size="FONT-SIZE: 9pt">第1部分：单选<span id="radioScore"></span></font></strong>
				</div>
			</td>
		</tr>
		<tbody id="radiosList"></tbody>
		
		<!-- 名词解释 -->
		<tr style="background-color: #f4fbf8;">
			<td colspan="2">
				<div class="TypeTitle" style="background-color: #f4fbf8;font-family: 黑体; font-size: 20px;">
					<strong><font color="#417ac1" size="FONT-SIZE: 9pt">第2部分：名词解释<span id="phraseScore"></span></font></strong>
				</div>
			</td>
		</tr>
		<tbody id="phrasesList"></tbody>
		
		<!-- 填空 -->
		<tr style="background-color: #f4fbf8;">
			<td colspan="2">
				<div class="TypeTitle" style="background-color: #f4fbf8;font-family: 黑体; font-size: 20px;">
					<strong><font color="#417ac1" size="FONT-SIZE: 9pt">第3部分：填空<span id="blankScore"></span></font></strong>
				</div>
			</td>
		</tr>
		<tbody id="blanksList"></tbody>

		<!-- 句子翻译 -->
		<tr style="background-color: #f4fbf8;">
			<td colspan="2">
				<div class="TypeTitle" style="background-color: #f4fbf8;font-family: 黑体; font-size: 20px;">
					<strong><font color="#417ac1" size="FONT-SIZE: 9pt">第4部分：翻译<span id="translateScore"></span></font></strong>
				</div>
			</td>
		</tr>
		<tbody id="translatesList"></tbody>
		
		<!-- 完型填空 -->
		<tr style="background-color: #f4fbf8;">
			<td colspan="2">
				<div class="TypeTitle" style="background-color: #f4fbf8;font-family: 黑体; font-size: 20px;">
					<strong><font color="#417ac1" size="FONT-SIZE: 9pt">第5部分：完型<span id="clozeScore"></span></font></strong>
				</div>
			</td>
		</tr>
		<tbody id="clozesList"></tbody>
		
		<tr><td colspan="2"><center><input id="mark" type="button" value="批阅完成"/></center></td></tr>
	</table>
	<input type="hidden" id="token" value="${token}"/>
	<input type="hidden" id="userPaperId" value="${userPaperId}"/>
</body>
</html>
