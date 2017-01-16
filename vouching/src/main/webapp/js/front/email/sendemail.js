$(function(){
	
	//装载班级信息
	var url = "/vouching/admin/loadClasses";
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
	var faildCallback = function(data){
		
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
	
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
		var url = "/vouching/email/sendEmail";
		var data = {
			token : $("#token").val(),
			subject : subject,
			content : content,
			receivers : receivers
		};
		var successCallback = function(data){
			
		};
		var faildCallback = function(data){
			
		};
		VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
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
	var faildCallback = function(data){
		
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
}

/**
 * 添加收件人事件
 */
function addReceiver(){
	$("option[name='receiver']").bind("click",function(){
		var selectedObj = $("option[name='receiver']:selected");
		var temp = "";
		var receivers = "";
		for (var i = 0; i < selectedObj.length; i++) {
			if (i != selectedObj.length - 1) {
				temp += $(selectedObj[i]).text() + ";";
				receivers += $(selectedObj[i]).attr("value") + ";";
			} else {
				temp += $(selectedObj[i]).text();
				receivers += $(selectedObj[i]).attr("value");
			}
		}
		$("#receiver").attr("value",temp);
		$("#receivers").attr("value",receivers);
	});
}