<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String bp = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>

<link href="<%=bp%>/css/back/admin.css" rel="stylesheet" type="text/css" />
<link href="<%=bp%>/css/back/pintuer.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=bp%>/js/lib/jquery.js"></script>
<script type="text/javascript" src="<%=bp%>/js/lib/layer/layer.js"></script>
<script type="text/javascript" src="<%=bp%>/js/VCUtils.js"></script>
<script type="text/javascript" src="<%=bp%>/js/back/pintuer.js"></script>

<c:set var="token" value="${requestScope.token}"/>

