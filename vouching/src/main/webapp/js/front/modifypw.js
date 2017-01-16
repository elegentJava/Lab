$(function(){
	
	//修改密码
	$("#modify").bind("click",function(){
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		var confirmPassword = $("#confirmPassword").val();
		if (VCUtils.common.util.isNotNullOrBlank(oldPassword)
				&& VCUtils.common.util.isNotNullOrBlank(newPassword)
				&& VCUtils.common.util.isNotNullOrBlank(confirmPassword)) {
			if (newPassword == confirmPassword) {
				var url = "/vouching/user/modifyPW";
				var data = {
					token : $("#token").val(),
					oldPassword : hex_md5(oldPassword),
					newPassword : hex_md5(newPassword),
					confirmPassword : hex_md5(confirmPassword)
				};
				var successCallback = function(data){
					layer.msg("密码修改成功!");
				};
				VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
			} else {
				VCUtils.common.tip.errorAlert("两次输入的密码不一致!");
			}
		} else {
			VCUtils.common.tip.errorAlert("输入密码不能为空!");
		}
	});
});