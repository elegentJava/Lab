<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/common.jsp"%>
<link rel="stylesheet" href="<%=bp%>/css/front/style2.css" type="text/css"></link>
<link rel="stylesheet" href="<%=bp%>/js/lib/tree/treestyles.css" type="text/css"></link>
<script type="text/javascript" src="<%=bp%>/js/lib/tree/dtreebase.js"></script>
<script type="text/javascript" src="<%=bp%>/js/front/resource/sentence.js"></script>
<title>语句查询</title>
</head>

<body>
	<div style="width: 100%; height: auto">
		<div style="width: 16%; height: auto; float: left; margin-left: 20px; margin-top: 30px">
			<div id="dtree" class="dtree"></div>
		</div>
		<div style="width: 80%; height: auto; float: left;">
			<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top: 10px;position: relative;">
				<tr>
					<td align="center" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="80" rowspan="2"><img src="<%=bp%>/images/pic_user1.jpg" width="80" height="70" /></td>
								<td class="weizhi">&nbsp;<strong>首页 ->资源平台 ->语句查询</strong></td>
							</tr>
							<tr><td class="weizhi_under">&nbsp;</td></tr>
						</table>
						<table cellpadding="0" cellspacing="0" class="juhuang12" border="0">
							<tr>
								<td align="center" style="font-size: 14px; font-weight: bold">
									<table border="0" width="90%">
										<tr align="right">
											<td>请选择语句类型： <select id="types"></select>
												请选择难度类型： <select id="levels"></select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tbody id="sentenceList"></tbody>
							<tfoot id="pager" style="display: block;">
								<%@include file="../common/pager.jsp"%>
							</tfoot>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<input type="hidden" id="token" value="${token}" />
	<input type="hidden" id="categoryIdHidden" value="8" />
	<input type="hidden" id="levelHidden" value="0" />
	<input type="hidden" id="typeHidden" value="0" />
</body>
</html>
