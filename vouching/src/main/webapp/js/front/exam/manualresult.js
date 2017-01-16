$(function(){
	
	//装载页面数据
	var url = "/vouching/exam/loadManualResult";
	var data = {
		token : $("#token").val(),
		chapterId : 1
	};
	var successCallback = function(data){
		var questionTypes = data.detail.questionTypes;
		var levels = data.detail.levels;
		
		//装载问题类型
		for (var i = 0; i < questionTypes.length; i++) {
			//装载上面的按钮
			$("#types").append("<td width='60px'>"+questionTypes[i].name+"</td>");
			$("#types").append("<td id=''>0</td>");
			$("#types").append("<td><input id='clear' type='button' value='清除'/></td>");
			//装载选择框
			$("#source").append("<option value='"+questionTypes[i].id+"'>"+questionTypes[i].name+"</option>");
		}
		//装载难易程度
		for (var i = 0; i < levels.length; i++) {
			$("#levels").append("<option value='"+levels[i].id+"'>"+levels[i].name+"</option>");
		}
		//装载试题
		
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null);
	
	
});