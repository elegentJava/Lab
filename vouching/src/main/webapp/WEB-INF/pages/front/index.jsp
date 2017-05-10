<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="common/common.jsp" %>
<script type="text/javascript" src="<%=bp%>/js/front/index.js" ></script>
<title>国贸函电系统</title>
<style type="text/css">
.top {
	font-size: 12px;
	margin: 0px;
	padding: 0px;
	background: url(<%=bp%>/images/hd_01.jpg) repeat-x;
	background-color: #77B654;
}
</style>
</head>
<body>
	<div class="top">
		<c:set var="user" value="${requestScope.detail}"/>
		<table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="29" align="right" class="bai12">${user.name}，你好！&nbsp;角色：${user.roleName}&nbsp;&nbsp;
					<a id="logout" href="javascript:;" target="_parent">注销</a>&nbsp;| 
					<a href="/vouching/forward/forwardModifyPW?token=${requestScope.token}" target="menu">个人密码修改</a>&nbsp;|
					<a href="javascript:;">未读站内信：<span id="unreadCount"></span></a>
					<c:if test="${user.role == 1}">
						|
						<a href="javascript:;">互动平台积分：<span id="credit"></span></a> 
					</c:if>
				</td>
			</tr>
		</table>
		<table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="70" align="left"><img src="<%=bp%>/images/hd_05.jpg" width="304" height="53" /></td>
				<td id="nav" width="460">
					<td id="menu" width="460">
						<div>
							<ul id="menus">
								<li id="zypt" class='m_li'><a href="javascript:;">资源平台</a></li>
								<li id="alpt" class='m_li'><a href="javascript:;">案例平台</a></li>
								<li id="kspt" class='m_li'><a href="javascript:;">考试平台</a></li>
								<c:if test="${user.role == 1}">
									<li id="lxpt" class='m_li'><a href="javascript:;">练习平台</a></li>
									<li id="jjpt" class='m_li'><a href="javascript:;">竞技平台</a></li>
								</c:if>
								<li id="znx" class='m_li'><a href="javascript:;">站内短信</a></li>
							</ul>
						</div>
						<div style="height: 22px;">
							<ul class="smenu" style="height: 22px;">
								<li style="padding-left: 0px;" name="zypt" class='s_li'>
									<a href="/vouching/forward/forwardCorrespondence?token=${token}" target="menu">教学板块</a> | 
									<a href="/vouching/forward/forwardGlossary?token=${token}" target="menu">词汇查询</a> | 
									<a href="/vouching/forward/forwardSentence?token=${token}" target="menu">语句查询</a> |
									<a href="/vouching/forward/forwardUpload?token=${token}" target="menu">上传资源</a> |
									<a href="/vouching/forward/forwardDownload?token=${token}" target="menu">下载资源</a>
								</li>
								<li style="padding-left: 95px;" name="alpt" class='s_li'>
									<a href="/vouching/forward/forwardFlash?token=${token}" target="menu">Flash平台</a>
								</li>
								<li style="padding-left: 30px;" name="kspt" class='s_li'>
									<c:choose>
										<c:when test="${user.role == 2}">
											<a href="/vouching/forward/forwardManual?token=${token}" target="menu">手工组卷</a> | 
											<a href="/vouching/forward/forwardAuto?token=${token}" target="menu">自动组卷</a> | 
											<a href="/vouching/forward/forwardMarkPaper?token=${token}" target="menu">批改试卷</a> | 
											<a href="/vouching/forward/forwardExamSetting?token=${token}" target="menu">考试设置</a> | 
											<a href="/vouching/forward/forwardChapter?token=${token}" target="menu">章节设置</a> |
											<a href="/vouching/forward/forwardExamGrade?token=${token}" target="menu">查看成绩</a>
										</c:when>
										<c:otherwise>
											<a href="/vouching/forward/forwardJoinExam?token=${token}" target="menu">查看考试信息</a> | 
											<a href="/vouching/forward/forwardExamRecord?token=${token}" target="menu">查看历史成绩</a>
										</c:otherwise>
									</c:choose>
								</li>
								<c:if test="${user.role == 1}">
									<li style="padding-left: 230px;" name="lxpt" class='s_li'>
										<a href="/vouching/forward/forwardTestSelect?token=${token}" target="menu">学生练习</a>
										<a href="/vouching/forward/forwardShowRecord?token=${token}" target="menu">查看练习记录</a>
									</li>
								</c:if>
								<li style="padding-left: 285px;" name="jjpt" class='s_li'>
									<a href="/vouching/forward/forwardCompetition?token=${token}" target="menu">知识点竞技</a> | 
									<a href="/vouching/forward/forwardCompetitionRecord?token=${token}" target="menu">查看成绩</a>
								</li>
								<c:choose>
									<c:when test="${user.role == 1}">
										<li style="padding-left: 420px;" name="znx" class='s_li'>
											<a href="/vouching/forward/forwardEmailStation?token=${token}" target="menu">站内信</a>
										</li>
									</c:when>
									<c:otherwise>
										<li style="padding-left: 260px;" name="znx" class='s_li'>
											<a href="/vouching/forward/forwardEmailStation?token=${token}" target="menu">站内信</a>
										</li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</td>
				</td>
			</tr>
		</table>
		<input type="hidden" id="token" value="${token}"/>
	</div>
	<div>
		<iframe scrolling="auto" rameborder="0" name="menu" width="100%" height="100%" src="/vouching/forward/forwardFrontMain?token=${token}"></iframe>
	</div>
</body>
</html>
