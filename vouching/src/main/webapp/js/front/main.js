$(function(){
	
	//装载未读站内信
	loadUnreadEmail();
	
	//邮件刷新
	$("#emailFreash").unbind("click");
	$("#emailFreash").bind("click",function(){
		loadUnreadEmail();
	});
	
	//查看邮件详情
	showDetail();
	
});

/**
 * 查看详情
 */
function showDetail(){
	$("a[name='detail']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			window.parent.menu.location.href = "/vouching/forward/forwardEmailDetail?token=" + $("#token").val()+"&type=1&emailId=" + $(this).attr("value");
		});
	});
}

/**
 * 装载未读站内信
 */
function loadUnreadEmail(){
	$("#more").hide();
	var url = "/vouching/email/loadUnreadEmailsForMain";
	var data = {
	    token : $("#token").val(),
	    pageNum : 1
	};
	var successCallback = function(data){
		var emails = data.detail.emails;
		$("#emailList").children().remove();
		if (emails != null && emails.length > 0) {
			for (var i = 0; i < emails.length; i++) {
				$("#emailList").append("<tr class='teatbbai' align='center' title='点击内容可查看信件详情' style='cursor: hand;'></tr>");
				var tr = $("#emailList").children().eq(i);
				tr.append("<td align='center'>"+emails[i].sendName+"</tr>");
				tr.append("<td align='center'>"+emails[i].subject+"</tr>");
				tr.append("<td align='center'><a name='detail' value='"+emails[i].emailId+"' href='javascript:;'>"+emails[i].content+"</a></td>");
				tr.append("<td align='center'>"+emails[i].formatDate+"</tr>");
			}
			if (emails.length < 5) {
				$("#more").hide();
			} else {
				$("#more").show();
			}
		} else {
			$("#emailList").append("<tr><td colspan='5'>暂无您的站内信！</td></tr>");
		}
		showDetail();
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
}