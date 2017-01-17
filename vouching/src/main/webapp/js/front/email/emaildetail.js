$(function(){
	
	//装载信息
	var url = "/vouching/email/loadEmailDetail";
	var data = {
	    token : $("#token").val(),
	};
	var successCallback = function(data){
		var email = data.detail.email;
		if (email != null) {
			$("#subject").attr("value",email.subject);
			$("#sender").attr("value",email.sendName);
			$("#receiver").attr("value",email.receiveName);
			$("#content").text(email.content);
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);

});