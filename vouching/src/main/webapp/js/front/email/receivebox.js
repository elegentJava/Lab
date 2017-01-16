$(function(){
	
	//
	$("#pager").hide();
	
	//装载收件箱
	var url = "/vouching/email/loadEmailBox";
	var data = {
	    token : $("#token").val(),
	    emailType : 1,
	    pageNum : 1
	};
	var successCallback = function(data){
		var receiveEmails = data.detail.receiveEmails;
		$("#receiveList").children().remove();
		if (receiveEmails != null && receiveEmails.length > 0) {
			for (var i = 0; i < receiveEmails.length; i++) {
				$("#receiveList").append("<tr></tr>");
				var tr = $("#receiveList").children().eq(i);
				tr.append("<td align='center'><input type='checkbox' value='"+receiveEmails[i].emailId+"'/></td>");
				tr.append("<td align='center'>"+receiveEmails[i].isReadName+"</tr>");
				tr.append("<td align='center'>"+receiveEmails[i].subject+"</tr>");
				tr.append("<td align='center'><a name='detail' href='javascript:;'>"+receiveEmails[i].content+"</a></td>");
				tr.append("<td align='center'>"+receiveEmails[i].formatDate+"</tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.front.registerEvent(loadDataForPage);
		} else {
			$("#receiveList").append("<tr><td colspan='5'>暂无您的站内信！</td></tr>");
			$("#pager").hide();
		}
	};
	var faildCallback = function(data){
		if ("USERINTEXAM" == data.errorCode) {
			var url = "/vouching/forward/forwardFrontIndex?token="+$("#token").val();
			VCUtils.common.util.simpleHref(url);
		} else {
			divAlert(data.errorCode);
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);

});

function loadDataForPage(pageNum){
	var url = "/vouching/email/loadEmailBox";
	var data = {
	    token : $("#token").val(),
	    emailType : 1,
	    pageNum : pageNum
	};
	var successCallback = function(data){
		var receiveEmails = data.detail.receiveEmails;
		$("#receiveList").children().remove();
		if (receiveEmails != null && receiveEmails.length > 0) {
			for (var i = 0; i < receiveEmails.length; i++) {
				$("#receiveList").append("<tr></tr>");
				var tr = $("#receiveList").children().eq(i);
				tr.append("<td align='center'><input type='checkbox' value='"+receiveEmails[i].emailId+"'/></td>");
				tr.append("<td align='center'>"+receiveEmails[i].isReadName+"</tr>");
				tr.append("<td align='center'>"+receiveEmails[i].subject+"</tr>");
				tr.append("<td align='center'><a name='detail' href='javascript:;'>"+receiveEmails[i].content+"</a></td>");
				tr.append("<td align='center'>"+receiveEmails[i].formatDate+"</tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.front.registerEvent(loadDataForPage);
		} else {
			$("#receiveList").append("<tr><td colspan='5'>暂无您的站内信！</td></tr>");
			$("#pager").hide();
		}
	};
	var faildCallback = function(data){
	    divAlert(data.errorCode);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);

}