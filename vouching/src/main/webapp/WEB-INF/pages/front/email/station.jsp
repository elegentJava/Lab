<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp" %>
<script type="text/javascript" src="<%=bp%>/js/lib/tree/dtreebase.js"></script>
<link rel="stylesheet" href="<%=bp%>/js/lib/tree/treestyles.css" type="text/css"></link>
<title>信息管理中心 </title>
</head>

<body>
<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" background="<%=bp%>/images/bak_teacher.png">
	<tr>
		<td width="100%">
		    <div align="center">
		    	<center>
			        <table border="0" cellpadding="0" cellspacing="0" width="960" style="border-collapse:collapse;">
				        <tr>
				        	<td width="21" rowspan="2" valign="top"><img border="0" src="<%=bp%>/images/sdw_left_tea.png" width="21"/></td>
				        	<td width="20" valign="bottom">&nbsp;</td>
				        	<td width="878" height="80" align="left" valign="bottom"><img border="0" src="<%=bp%>/images/handianguanli.jpg"/></td>
				        	<td width="20">&nbsp;</td>
				        	<td width="21" rowspan="2" valign="top"><img border="0" src="<%=bp%>/images/sdw_right_tea.png" width="21"/></td>
				        </tr>
						<tr>
				        	<td colspan="3">
				            <table border="0" cellpadding="0" align="center" cellspacing="0" width="878">
				            	<tr>
				                	<td width="129" height="154" align="center" rowspan="2" valign="top">
				                  		<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
					                    	<tr>
					                      		<td width="100%"><img border="0" src="<%=bp%>/images/pic_user1.jpg" width="129" height="134"/></td>
					                    	</tr>
					                    	<tr>
					                      		<td width="100%" bgcolor="#FFFFFF">
					                        		<script type="text/javascript">
														var tree = new dTree("tree");
														var timestr=new Date().getTime();
														var ty =1;
														tree.add(0,-1,'函电中心'); 
														tree.add(1,0,'站内信');   
														tree.add(4,1,'写短信','/vouching/forward/forwardSendEmail?token=${token}','','_midframe','<%=bp%>/images/folder.gif','<%=bp%>/images/folder.gif');   
														tree.add(5,1,'收信箱','/vouching/forward/forwardReceiveBox?token=${token}','','_midframe','<%=bp%>/images/folder.gif','<%=bp%>/images/folder.gif');   
														tree.add(6,1,'发件箱','/vouching/forward/forwardSendBox?token=${token}','','_midframe','<%=bp%>/images/folder.gif','<%=bp%>/images/folder.gif');    
														document.write(tree);
					  	                    		</script>                        
					  	                    	</td>
					                    	</tr>
				                    	</table>
				                    </td>
								  	<td width="25" height="320" align="center"></td>
				                  	<td width="746"  rowspan="2" valign="top">
					                    <iframe name="_midframe" id="_midframe" marginwidth="1" marginheight="1" height="400" width="746" border="0" frameborder="0" allowtransparency="true" src="/vouching/forward/forwardReceiveBox?token=${token}" scrolling="no">
											浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
										</iframe>
									</td>
				               	</tr>
				            </table>
				          </td>
			          </tr>
			      </table>
		      </center>
		    </div>
	    </td>
	  </tr>
</table>
<input type="hidden" id="token" value="${token}"/>
<jsp:include page="../common/foot.jsp" />
</body>
</html>