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
				tr.append("<td align='center'><input type='checkbox' name='emailId' value='"+receiveEmails[i].emailId+"'/></td>");
				if (receiveEmails[i].isRead == 0) {
					tr.append("<td align='center' ><strong>"+receiveEmails[i].isReadName+"</strong></td>");
				} else {
					tr.append("<td align='center'>"+receiveEmails[i].isReadName+"</td>");
				}
				tr.append("<td align='center'>"+receiveEmails[i].subject+"</td>");
				tr.append("<td align='center'><a name='detail' value='"+receiveEmails[i].emailId+"' href='javascript:;'>"+receiveEmails[i].content+"</a></td>");
				tr.append("<td align='center'>"+receiveEmails[i].formatDate+"</td>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.front.registerEvent(loadDataForPage);
		} else {
			$("#receiveList").append("<tr><td colspan='5'>暂无您的站内信！</td></tr>");
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
			    type : 1
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
			window.parent._midframe.location.href = "/vouching/forward/forwardEmailDetail?token=" + $("#token").val()+"&type=1&emailId=" + $(this).attr("value");
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
				tr.append("<td align='center'><input type='checkbox' name='emailId' value='"+receiveEmails[i].emailId+"'/></td>");
				if (receiveEmails[i].isRead == 0) {
					tr.append("<td align='center' ><strong>"+receiveEmails[i].isReadName+"</strong></td>");
				} else {
					tr.append("<td align='center'>"+receiveEmails[i].isReadName+"</td>");
				}
				tr.append("<td align='center'>"+receiveEmails[i].subject+"</td>");
				tr.append("<td align='center'><a name='detail' value='"+receiveEmails[i].emailId+"' href='javascript:;'>"+receiveEmails[i].content+"</a></td>");
				tr.append("<td align='center'>"+receiveEmails[i].formatDate+"</td>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.front.registerEvent(loadDataForPage);
			showDetail();
		} else {
			$("#receiveList").append("<tr><td colspan='5'>暂无您的站内信！</td></tr>");
			$("#pager").hide();
		}
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);

}