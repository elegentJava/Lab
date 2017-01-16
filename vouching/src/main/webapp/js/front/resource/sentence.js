var tree = new dTree("tree");
$(function(){
	
	//隐藏分页
	$("#pager").hide();
	
	//加载语句类型树
	var url = "/vouching/resource/loadSentenceTree";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data) {
		var categories = data.detail.categories;
		tree.add(0, -1, "语句查询系统", "");
		for (var i = 0; i < categories.length; i++) {
			tree.add(categories[i].scid, categories[i].fatherId, categories[i].name, categories[i].flag, categories[i].name);
		}
		document.getElementById("dtree").innerHTML = tree;
		//为每个类型绑定事件
		$("a[href='1']").each(function() {
			$(this).attr("href","javascript:;");
			$(this).unbind("click");
			$(this).bind("click", function() {
				categoryId = $(this).attr("id").slice($(this).attr("id").length - 1);
				loadQueryResultForCommon(categoryId, 0, 0);
			});
		});
	};
	VCUtils.common.ajax.commonAjax(url, true, data, successCallback, null, null);
	
	//装载语句类型和等级
	var url = "/vouching/resource/loadSentenceLevelAndType";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var levels = data.detail.levels;
		var types = data.detail.types;
		$("#levels").children().remove();
		$("#types").children().remove();
		for (var i = 0; i < levels.length; i++) {
			$("#levels").append("<option value='"+levels[i].id+"'>"+levels[i].name+"</option>");
		}
		for (var i = 0; i < types.length; i++) {
			$("#types").append("<option value='"+types[i].id+"'>"+types[i].name+"</option>");
		}
	};
	VCUtils.common.ajax.commonAjax(url, true, data, successCallback, null, null);
	
	//难度修改事件
	$("#levels").bind("change",function(){
		var level = $(this).val();
		loadQueryResultForCommon(null, level, null);
	});
	
	//语句类型改变事件
	$("#types").bind("change",function(){
		var type = $(this).val();
		loadQueryResultForCommon(null, null, type);
	});
	
});

/**
 * 装载分页查询结果
 * 
 * @param pageNum
 */
function loadQueryResultForPage(pageNum){
	var loading = layer.load();
	var url = "/vouching/resource/querySentence";
	var data = {
		token : $("#token").val(),
		categoryId : $("#categoryIdHidden").val(),
		level : $("#levelHidden").val(),
		type : $("#typeHidden").val(),
		pageNum : pageNum
	};
	var successCallback = function(data){
		var sentences = data.detail.sentences;
		$("#sentenceList").children().remove();
		if(sentences.length > 0){
			for (var i = 0; i < sentences.length; i++) {
				$("#sentenceList").append("<tr class='teatbbai'><td>" +sentences[i].chinese+ "</td></tr>");
				$("#sentenceList").append("<tr class='teatbbai'><td>" +sentences[i].english+ "</td></tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
		} else {
			$("#pager").hide();
			$("#sentenceList").append("<tr class='teatbbai'><td align='center'>未筛选到结果......</td></tr>");
		}
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}

/**
 * 装载结果
 * 
 * @param categoryId
 * @param level
 * @param type
 */
function loadQueryResultForCommon(categoryId,level,type){
	var loading = layer.load();
	if(categoryId == null){
		categoryId = $("#categoryIdHidden").val();
	}
	if(level == null){
		level = $("#levelHidden").val();
	}
	if(type == null){
		type = $("#typeHidden").val();
	}
	$("#categoryIdHidden").attr("value",categoryId);
	$("#levelHidden").attr("value",level);
	$("#typeHidden").attr("value",type);
	var url = "/vouching/resource/querySentence";
	var data = {
		token : $("#token").val(),
		categoryId : categoryId,
		level : level,
		type : type,
		pageNum : 1
	};
	var successCallback = function(data){
		var sentences = data.detail.sentences;
		$("#sentenceList").children().remove();
		if(sentences.length > 0){
			for (var i = 0; i < sentences.length; i++) {
				$("#sentenceList").append("<tr class='teatbbai'><td>" +sentences[i].chinese+ "</td></tr>");
				$("#sentenceList").append("<tr class='teatbbai'><td>" +sentences[i].english+ "</td></tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
		} else {
			$("#pager").hide();
			$("#sentenceList").append("<tr class='teatbbai'><td align='center'>未筛选到结果......</td></tr>");
		}
		VCUtils.common.pager.front.registerEvent(loadQueryResultForPage);
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}