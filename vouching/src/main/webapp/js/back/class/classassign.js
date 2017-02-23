$(function(){
	
	var token = $("#token").val();
	
	//装载教师班级
	loadTeacherClasses();
	
	//装载学生班级
	loadStudentClasses();
	
});

/**
 * 装载教师班级
 */
function loadTeacherClasses(){
	var url = "/vouching/admin/loadTeacherClasses";
	var data = {
	    token : $("#token").val()
	};
	var successCallback = function(data){
		var classes = data.detail.classes;
		$("#tclass").append("<option value='0' selected='selected'>请选择班级</option>");
		if(classes != null && classes.length > 0){
			for (var i = 0; i < classes.length; i++) {
				$("#sclass").append("<option value='"+classes[i].classId+"'>"+classes[i].className+"</option>");
			}
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback);
}

/**
 * 装载学生班级
 */
function loadStudentClasses(){
	var url = "/vouching/admin/loadClasses";
	var data = {
	    token : $("#token").val()
	};
	var successCallback = function(data){
		var classes = data.detail.classes;
		$("#sclass").append("<option value='0' selected='selected'>请选择班级</option>");
		if(classes != null && classes.length > 0){
			for (var i = 0; i < classes.length; i++) {
				$("#sclass").append("<option value='"+classes[i].classId+"'>"+classes[i].className+"</option>");
			}
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback); 
}