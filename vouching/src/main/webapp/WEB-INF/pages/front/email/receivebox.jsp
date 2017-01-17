<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp" %>
<script type="text/javascript" src="<%=bp%>/js/front/email/receivebox.js"></script>
<title>站内信</title>
<style type="text/css">
td {
	overflow: hidden;
	text-overflow: ellipsis;
	word-break: keep-all;
	white-space: nowrap;
}
</style>
</head>
<body style="margin:0px;" scroll="yes">
	<div id="msgdiv" style="border: 1px solid #3765ac;;display: none;position:absolute;filter: alpha(opacity=85); " onclick="this.style.display='none';"></div>
	<form action="message_person.do" id="frm" method="post" name="frm">
    	<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" height="400">
			<tr>	
				<td width="50%" bgcolor="#1D6300" height="30">
			    	<p style="margin-left: 20"><font color="#FFFFFF"><span style="font-size: 9pt; font-weight: 700">&nbsp;&nbsp;收件箱</span></font></p>
		     	</td>
				 <td width="50%" height="30" align="right" bgcolor="#1D6300">
				     <img src="<%=bp %>/images/mes_btn1.jpg" alt="" width="166" height="35" border="0" usemap="#Map2"/>
				 </td>
		  	</tr>
  			<tr>
		    	<td width="100%" valign="top" height="316" colspan="2">
		        	<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;font-size:12px;table-layout:fixed;" bordercolor="#1D6300" width="100%">
		            	<tr>
			            	<td width="4%" height="24" bgcolor="#77B654" align="center">
			            		<input id="checkall" type="checkbox"/>
			            	</td>
			            	<td width="6%" height="24" bgcolor="#77B654" align="center">
			            		<p style="margin-left: 5; margin-right: 2; margin-top: 2; margin-bottom: 2">
		                    		<font color="#FFFFFF"><span style="font-size: 9pt">状态</span></font>
		                    	</p>
		               		</td>
			                <td width="25%" height="24" bgcolor="#77B654" align="center">
			                	<p style="margin-left: 5; margin-right: 2; margin-top: 2; margin-bottom: 2">
			                    	<font color="#FFFFFF"><span style="font-size: 9pt">函电主题</span></font>
			                	</p>
			                </td>
			                <td width="45%" height="24" bgcolor="#77B654" align="center">
			                	<p style="margin-left: 5; margin-right: 2; margin-top: 2; margin-bottom: 2">
			                    	<font color="#FFFFFF"><span style="font-size: 9pt">函电内容</span></font>
			                    </p>
			                </td>
			                <td width="20%"  height="24" bgcolor="#77B654" align="center" width="20%">
			                	<p style="margin-left: 5; margin-right: 2; margin-top: 2; margin-bottom: 2">
			                    	<font color="#FFFFFF"><span style="font-size: 9pt">发送时间</span></font>
			                    </p>
			                </td>
		             	</tr>
		             	<tbody id="receiveList"></tbody>
		             	<tfoot id="pager">
		             		<%@include file="../common/pager.jsp" %>
		             	</tfoot>
		        	</table>
		     	</td>
			</tr>
		</table>
		<!-- 热点击 -->
		<map name="Map2" id="Map2">
			<area shape="rect" coords="3,5,82,30" href="javascript:;" alt="" id="del"/>
			<area shape="rect" coords="86,4,159,31" href="javascript:;" alt="" id="fresh" />
		</map>
	</form>
	<input type="hidden" id="token" value="${token}"/>
</body>
</html>