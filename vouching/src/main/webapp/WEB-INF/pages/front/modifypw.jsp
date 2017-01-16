<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="common/common.jsp" %>
<script type="text/javascript" src="<%=bp%>/js/lib/md5.js" ></script>
<script type="text/javascript" src="<%=bp%>/js/front/modifypw.js"></script>
<title>修改密码</title>
</head>

<body>
	<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" background="<%=bp%>/images/bak_teacher.png">
		<tr>
			<td width="100%">
				<div align="center">
					<center>
						<table border="0" cellpadding="0" cellspacing="0"
							style="border-collapse: collapse" bordercolor="#111111"
							width="960" height="80">
							<tr>
								<td width="21" rowspan="2" valign="top"><img border="0"
									src="<%=bp%>/images/sdw_left_tea.png" width="21" /></td>
								<td width="20" height="80" valign="bottom"></td>
								<td width="420" valign="bottom"><img border="0"
									src="<%=bp%>/images/tle_xiugaimima1.png" width="128"
									height="33" /></td>
								<td width="20" valign="bottom">&nbsp;</td>
								<td width="21" rowspan="2" valign="top"><img border="0"
									src="<%=bp%>/images/sdw_right_tea.png" width="21" /></td>
								<td width="458">&nbsp;</td>
							</tr>
							<tr>
								<td width="20" valign="top"></td>
								<td width="420" valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										style="border:1px solid #277706">
										<tr>
											<td height="130" valign="top">
												<div align="center">
													<center>
														<table border="0" cellpadding="0" cellspacing="0"
															style=" font-size:12px;border-collapse: collapse"
															bordercolor="#111111" width="60%" align="center">
															<tr>
																<td height="20" colspan="2">&nbsp;</td>
															</tr>
															<tr height="25">
																<td>输入原密码：<input type="password" name="oldPassword"
																	id="oldPassword" style='height:20px ; width:150px'
																	size="20" /></td>
																<td><span id="errorMsg" style="color: red;"></span></td>
															</tr>
															<tr height="25">
																<td>输入新密码：<input type="password" name="newPassword"
																	id="newPassword" style='height:20px ; width:150px'
																	size="20" /></td>
															</tr>
															<tr height="25">
																<td>确认新密码：<input type="password"
																	name="confirmPassword" id="confirmPassword"
																	style='height:20px ; width:150px' size="20" /></td>
															</tr>
															<tr>
																<td align="center"><input type="submit" id="modify"
																	value="确定并修改密码" /></td>
															</tr>
														</table>
													</center>
												</div>
											</td>
										</tr>
									</table>
								</td>
								<td width="20" valign="top"></td>
								<td width="458" valign="top">
									<table border="0" cellpadding="0" cellspacing="0"
										style="border-collapse: collapse" bordercolor="#111111"
										width="100%">
										<tr>
											<td width="100%" height="50">&nbsp;</td>
										</tr>
										<tr>
											<td width="100%" height="68" valign="bottom" align="right">
												<p>
													<font color="#333333"> <img border="0"
														src="<%=bp%>/images/pic_coinfo1.png" width="440"
														height="348" /></font>
												</p>
											</td>
										</tr>
										<tr>
											<td width="100%"></td>
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
</body>
</html>