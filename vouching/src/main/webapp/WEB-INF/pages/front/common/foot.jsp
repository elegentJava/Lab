<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String bp = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面底部</title>
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
		<tr>
			<td width="100%" colspan="2" height="10" bgcolor="#256E00">
				<img border="0" src="<%=bp%>/images/pixel.gif" width="1" height="1">
			</td>
		</tr>
		<tr>
			<td width="40%" height="30">
				<p align="right">
					<img border="0" src="<%=bp%>/images/logo_hep_teacher.png" width="20" height="19">
				</p>
			</td>
			<td width="60%">
				<p style="margin-left: 3">
					<font color="#256E00"> 
						<span style="font-size: 9pt">版权所有：高等教育出版社高等教育电子音像出版社</span>
					</font>
			</td>
		</tr>
	</table>
	<br>
</body>
</html>