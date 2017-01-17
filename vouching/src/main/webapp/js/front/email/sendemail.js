$(function(){
	
	//装载班级信息
	var url = "/vouching/email/loadClassList";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var classes = data.detail.classes;
		if(classes!=null){
			for (var i = 0; i < classes.length; i++) {
				$("#classList").append("<option value='"+classes[i].classId+"'>"+classes[i].className+"</option>");
			}
			loadReceiver(classes[0].classId);
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
	
	//班级选项改变事件
	$("#classList").bind("change",function(){
		var classId = $(this).val();
		loadReceiver(classId);
	});
	
	//发送邮件
	$("#send").unbind("click");
	$("#send").bind("click",function(){
		var subject = $("#subject").val();
		var content = $("#content").val();
		var receivers = $("#receivers").val();
		if (VCUtils.common.util.isNotNullOrBlank(subject)) {
			if (VCUtils.common.util.isNotNullOrBlank(content)) {
				if (VCUtils.common.util.isNotNullOrBlank(receivers)) {
					var url = "/vouching/email/sendEmail";
					var data = {
						token : $("#token").val(),
						subject : subject,
						content : content,
						receivers : receivers
					};
					var successCallback = function(data){
						layer.msg("发送成功!");
					};
					VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
				} else {
					VCUtils.common.tip.errorAlert("收件人不能为空!");
				}
			} else {
				VCUtils.common.tip.errorAlert("内容不能为空!");
			}
		} else {
			VCUtils.common.tip.errorAlert("主题不能为空!");
		}
	});
	
	
});

/**
 * 装载收件人
 * 
 * @param classId
 */
function loadReceiver(classId){
	var url = "/vouching/email/loadUsersInClass";
	var data = {
		token : $("#token").val(),
		classId : classId
	};
	var successCallback = function(data){
		var users = data.detail.users;
		$("#mutiClass").children().remove();
		if(users != null){
			for (var i = 0; i < users.length; i++) {
				$("#mutiClass").append("<option name='receiver' value='"+users[i].userId+"'>"+users[i].name+"</option>");
			}
		}
		addReceiver();
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
}

/**
 * 添加收件人事件
 */
function addReceiver(){
	$("option[name='receiver']").bind("click",function(){
		var selectedObj = $("option[name='receiver']:selected");
		var receivers = "";
		for (var i = 0; i < selectedObj.length; i++) {
			if (i != selectedObj.length - 1) {
				receivers += $(selectedObj[i]).attr("value") + ";";
			} else {
				receivers += $(selectedObj[i]).attr("value");
			}
		}
		$("#receivers").attr("value",receivers);
	});
}