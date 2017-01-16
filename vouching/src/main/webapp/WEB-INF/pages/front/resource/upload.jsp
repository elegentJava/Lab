<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="../common/common.jsp" %>
<script type="text/javascript" src="<%=bp%>/js/lib/ajaxfileupload.js" ></script>
<script type="text/javascript" src="<%=bp%>/js/front/resource/upload.js" ></script>
<title>上传文件</title>
</head>
  
 <body>
	<div style="margin-left: 20%;margin-top: 20px;">
		<span style="color: red;font: bold;font-size: 15px;">请先在下载页面下载对应的资源进行填写后在上传文件!</span>	
		<div id="uploadList"></div>
	</div> 
	<input type="hidden" id="token" value="${token}">
</body>
</html>
