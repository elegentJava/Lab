$(function(){
	
	//全选事件
	VCUtils.common.util.selectAll("emailId");
	
	//隐藏分页
	$("#pager").hide();
	
	//装载收件箱
	var loading = layer.load();
	var url = "/vouching/email/loadEmailBox";
	var data = {
	    token : $("#token").val(),
	    emailType : 0,
	    pageNum : 1
	};
	var successCallback = function(data){
		var sendEmails = data.detail.sendEmails;
		$("#sendList").children().remove();
		if (sendEmails != null && sendEmails.length > 0) {
			for (var i = 0; i < sendEmails.length; i++) {
				$("#sendList").append("<tr></tr>");
				var tr = $("#sendList").children().eq(i);
				tr.append("<td align='center'><input type='checkbox' name='emailId' value='"+sendEmails[i].emailId+"'/></td>");
				tr.append("<td align='center'>"+sendEmails[i].subject+"</tr>");
				tr.append("<td align='center'><a name='detail' value='"+sendEmails[i].emailId+"' href='javascript:;'>"+sendEmails[i].content+"</a></td>");
				tr.append("<td align='center'>"+sendEmails[i].formatDate+"</tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.front.registerEvent(loadDataForPage);
		} else {
			$("#sendList").append("<tr><td colspan='5'>暂无您的站内信！</td></tr>");
			$("#pager").hide();
		}
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
	
	//查看详情
	showDetail();
	
	//删除事件
	$("#del").unbind("click");
	$("#del").bind("click",function(){
		var obj = $("input[name='emailId']:checked");
		if (obj.length != 0) {
			var emailIds = new Array();
			for (var i = 0; i< obj.length ; i++) {
				emailIds[i] = $(obj[i]).attr("value");
			}
			var url = "/vouching/email/batchDeleteEmail";
			var data = {
			    token : $("#token").val(),
			    emailIds : emailIds,
			    type : 0
			};
			var successCallback = function(data){
				loadDataForPage(1);
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
		} else {
			VCUtils.common.tip.errorAlert("请选择一个再删除!");
		}
	});
	
	//刷新事件
	$("#fresh").unbind("click");
	$("#fresh").bind("click",function(){
		loadDataForPage(1);
	});

});

/**
 * 查看详情
 */
function showDetail(){
	$("a[name='detail']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			window.parent._midframe.location.href = "/vouching/forward/forwardEmailDetail?token=" + $("#token").val()+"&type=0&emailId=" + $(this).attr("value");
		});
	});
}

/**
 * 分页装载数据
 * 
 * @param pageNum
 */
function loadDataForPage(pageNum){
	var loading = layer.load();
	var url = "/vouching/email/loadEmailBox";
	var data = {
	    token : $("#token").val(),
	    emailType : 0,
	    pageNum : pageNum
	};
	var successCallback = function(data){
		var sendEmails = data.detail.sendEmails;
		$("#sendList").children().remove();
		if (sendEmails != null && sendEmails.length > 0) {
			for (var i = 0; i < sendEmails.length; i++) {
				$("#sendList").append("<tr></tr>");
				var tr = $("#sendList").children().eq(i);
				tr.append("<td align='center'><input type='checkbox' name='emailId' value='"+sendEmails[i].emailId+"'/></td>");
				tr.append("<td align='center'>"+sendEmails[i].subject+"</tr>");
				tr.append("<td align='center'><a name='detail' value='"+sendEmails[i].emailId+"' href='javascript:;'>"+sendEmails[i].content+"</a></td>");
				tr.append("<td align='center'>"+sendEmails[i].formatDate+"</tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.front.registerEvent(loadDataForPage);
			showDetail();
		} else {
			$("#sendList").append("<tr><td colspan='5'>暂无您的站内信！</td></tr>");
			$("#pager").hide();
		}
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);

}