$(function(){
	
	//装载页面信息
	var url = "/vouching/exam/loadAutoTest";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var questionTypes = data.detail.questionTypes;
		var levels = data.detail.levels;
		//装载题型
		for (var i = 0; i < questionTypes.length; i++) {
			$("#questionList").append("<tr><td width='120' align='left'>"+questionTypes[i].name+"</td><td align='left'><select id='"+questionTypes[i].tag+"'><option value='5'>5</option><option value='10'>10</option><option value='15'>15</option></select></td></tr>");
		}
		//装载难度
		for (var i = 0; i < levels.length; i++) {
			$("#levels").append("<option value='"+levels[i].id+"'>"+levels[i].name+"</option>");
		}
	};
	VCUtils.common.ajax.commonAjax(url, true, data, successCallback, null);
	
	//考试名称校验
	$("#name").bind("focusout",function(){
		examNameValidate();
	});
	$("#name").bind("focusin",function(){
		$("#errorMsg").text("");
	});
	
	//生成试卷
	$("#saveExam").bind("click",function(){
		if (examNameValidate()) {
			var url = "/vouching/exam/autoGenerateExam";
			var data = {
				token : $("#token").val(),
				name : $("#name").val(),
				bak : $("#bak").val(),
				radioCount : $("#radio").val(),
				blankCount : $("#blank").val(),
				clozeCount : $("#cloze").val(),
				phraseCount : $("#phrase").val(),
				translateCount : $("#translate").val(),
				level : $("#levels").val()
			};
			var successCallback = function(data){
				divAlert("试卷生成成功!");
			};
			var faildCallback = function(data){
				divAlert(data.errorCode);
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
		}
	});
	
});

/**
 * 考试名称校验
 * 
 * @returns {Boolean}
 */
function examNameValidate(){
	var name = $("#name").val();
	if (VCUtils.common.util.isNotNullOrBlank(name)) {
		$("#errorMsg").text("");
		return true;
	}
	$("#errorMsg").text("请输入考试名称!");
	return false;
}