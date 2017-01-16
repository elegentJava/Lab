$(function(){
	
	//装载班级信息
	var url = "/vouching/email/loadClasses";
	var data = {
		token : $("#token").val()
	};
	successCallback = function(data){
		var classes = data.detail.classes;
		$("#classes").children().remove();
		$("#classes").append("<option value='0'>请选择班级</option>");
		for (var i = 0; i < classes.length; i++) {
			$("#classes").append("<option value='"+classes[i].classId+"'>"+classes[i].name+"</option>");
		}
	};
	VCUtils.common.ajax.commonAjax(url, true, data, successCallback, null);
	
});