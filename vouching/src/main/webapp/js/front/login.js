$(function(){
	
	$("#errorMsg").hide();
	
	$("#login").bind("click",function(){
		var account = $("#account").val();
		var password = $("#password").val();
		if (VCUtils.common.util.isNotNullOrBlank(account) 
				&& VCUtils.common.util.isNotNullOrBlank(password)) {
			$("#password").text(hex_md5(password));
			var url = "/vouching/user/login";
			var data = {
				flag : true,
				account : account,
				password : hex_md5(password)
			};
			var successCallback = function(data){
				var url = "/vouching/forward/forwardFrontIndex?token="+data.detail.token;
				VCUtils.common.util.simpleHref(url);	
			};
			var faildCallback = function(data){
				$("#errorMsg").text(data.errorCode);
				$("#errorMsg").show();
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback, null);
		} else {
			$("#errorMsg").text("账号或密码不能为空!");
			$("#errorMsg").show();
		}
	});
	
});