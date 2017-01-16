$(function(){
	
	$("#errorMsg").hide();
	
	$("#login").bind("click",function(){
		var account = $("#account").val();
		var password = $("#password").val();
		if(VCUtils.common.util.isNotNullOrBlank(account) && VCUtils.common.util.isNotNullOrBlank(password)){
			$("#password").text(hex_md5(password));
			var url = "/vouching/user/adminLogin";
			var data = {
				account : account,
				password : hex_md5(password)
			};
			var successCallback = function(data){
				var url = "/vouching/forward/forwardBackIndex?token="+data.detail.token;
				VCUtils.common.util.simpleHref(url);	
			};
			var faildCallback = function(data){
				$("#errorMsg>div>ul>li").text(data.errorCode);
				$("#errorMsg").show();
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
		}
	});
	
});