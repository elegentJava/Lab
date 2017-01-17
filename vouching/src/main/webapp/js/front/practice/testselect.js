$(function(){
	
	//初始化装载数据
	var url = "/vouching/practice/loadTestSelect";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var chapters = data.detail.chapters;
		var categorys = data.detail.categorys;
		var levels = data.detail.levels;
		for (var i = 0; i < chapters.length; i++) {
			$("#chapters").append("<option value='" +chapters[i].chapterId+ "'>" +chapters[i].name+ "</option>");
		}
		for (var i = 0; i < categorys.length; i++) {
			$("#categorys").append("<option value='" +categorys[i].id+ "'>" +categorys[i].name+ "</option>");
		}
		for (var i = 0; i < levels.length; i++) {
			$("#levels").append("<option value='" +levels[i].id+ "'>" +levels[i].name+ "</option>");
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
	
	//生成试卷
	$("#startTest").bind("click",function(){
		var chapterId = $("#chapters").val();
		var categoryId = $("#categorys").val();
		var levelId = $("#levels").val();
		var count = $("#count").val();
		var token = $("#token").val();
		var url = "/vouching/practice/generateTest";
		var data = {
			chapterId : chapterId,
			categoryId : categoryId,
			levelId : levelId,
			count : count,
			token : token
		};
		var successCallback = function(){
			var url = "/vouching/forward/forwardStartTest?token="+token;
			VCUtils.common.util.simpleHref(url);
		}
		VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
	});
	
});