var tree = new dTree("tree");
$(function(){
	
	//隐藏分页项
	$("#pager").hide();
	
	//装载页面
	var url = "/vouching/exam/loadManual";
	var data = {
		token : $("#token").val(),
	};
	var successCallback = function(data){
		var chapters = data.detail.chapters;
		var questionTypes = data.detail.questionTypes;
		var levels = data.detail.levels;
		var radios = data.detail.radios;
		//装载章节树
		tree.add(0, -1, "章节列表","");
		for (var i = 0; i < chapters.length; i++) {
			tree.add(chapters[i].chapterId, 0, chapters[i].name, "1", chapters[i].name, "main");
		} 
		document.getElementById("dtree").innerHTML=tree;
		//装载题目类型
		for (var i = 0; i < questionTypes.length; i++) {
			//装载上面的按钮
			$("#types").append("<td width='60px'>"+questionTypes[i].name+"</td>");
			$("#types").append("<td source='" +questionTypes[i].id+ "'>0</td>");
			$("#types").append("<td><input source='" +questionTypes[i].id+ "' name='clear' type='button' value='清除'/></td>");
			//装载选择框
			$("#source").append("<option value='"+questionTypes[i].id+"'>"+questionTypes[i].name+"</option>");
		}
		//装载难易程度
		for (var i = 0; i < levels.length; i++) {
			$("#levels").append("<option value='"+levels[i].id+"'>"+levels[i].name+"</option>");
		}
		//装载默认的单选试题
		$("#questionList").children().remove();
		if(radios.length > 0){
			for (var i = 0,j = 1; i < radios.length; i++,j++) {
				$("#questionList").append("<tr><td align='left' valign='top' width='10%'><input qid='" +radios[i].id+ "' name='addQuestion' type='button' value='添加到试卷' /></td><td align='center' width='10%'>"+ j +"&nbsp;</td><td align='left' width='80%'>" +radios[i].question+ "</td></tr>");
				$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc; border-bottom: none;'></td></tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
		} else {
			//
			$("#pager").hide();
		}
		VCUtils.common.pager.front.registerEvent(queryQuestionsForPage);
		bindClickForAddQuestion();
	};
	VCUtils.common.ajax.commonAjax(url, true, data, successCallback, null);
	
	//为题目类型绑定事件
	$("#source").bind("change",function(){
		var source = $(this).val();
		queryQuestionsForCommon(null, source, null);
	});
	
	//为难易程度绑定事件
	$("#levels").bind("change",function(){
		var level = $(this).val();
		queryQuestionsForCommon(null, null, level);
	});
	
	//提交试卷
	
	
});

/**
 * 清除题目
 */
function clearQuestion(){
	$("input[name='clear']").each(function(){
		$(this).bind("click",function(){
			var source = $(this).attr("source");
			//题目数修改
			var count = $("td[source='" +source+ "']").text();
			if(count != "0"){
				var updatedCount = parseInt(count) - 1;
				$("td[source='" +source+ "']").text(updatedCount);
			}
			//数据填充
		});
	});
}

/**
 * 为添加试题绑定事件
 */
function bindClickForAddQuestion(){
	$("input[name='addQuestion']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			var qid = $(this).attr("qid");
			var source = $("#sourceHidden").val();
			//按钮失效
			$(this).attr("value","该题目已经添加");
			$(this).css("color","red");
			$(this).attr("disabled",true);
			//题目数修改
			var count = $("td[source='" +source+ "']").text();
			var updatedCount = parseInt(count) + 1;
			$("td[source='" +source+ "']").text(updatedCount);
			//数据填充
			
		});
	});
}

/**
 * 普通数据装载
 * 
 * @param chapterId
 * @param source
 * @param level
 */
function queryQuestionsForCommon(chapterId,source,level){
	if(chapterId == null){
		chapterId = $("#chapterIdHidden").val();
	}
	if(source == null){
		source = $("#sourceHidden").val();
	}
	if(level == null){
		level = $("#levelHidden").val();
	}
	var url = "/vouching/exam/queryQuestions";
	var data = {
		token : $("#token").val(),
		chapterId : chapterId,
		source : source,
		level : level,
		pageNum : 1
	};
	var successCallback = function(data){
		$("#chapterIdHidden").attr("value",chapterId);
		$("#sourceHidden").attr("value",source);
		$("#levelHidden").attr("value",level);
		var questions = data.detail.questions;
		//装载数据
		$("#questionList").children().remove();
		if(questions.length > 0){
			for (var i = 0,j = 1; i < questions.length; i++,j++) {
				$("#questionList").append("<tr><td align='left' valign='top' width='10%'><input qid='" +questions[i].id+ "' name='addQuestion' type='button' value='添加到试卷' /></td><td align='center' width='10%'>"+ j +"&nbsp;</td><td align='left' width='80%'>" +questions[i].question+ "</td></tr>");
				$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc; border-bottom: none;'></td></tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
		} else {
			//
			$("#pager").hide();
		}
		VCUtils.common.pager.front.registerEvent(queryQuestionsForPage);
		bindClickForAddQuestion();
	};
	var faildCallback = function(data){
		divAlert("题目查询失败!");
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
}

/**
 * 分页专门装载
 * 
 * @param pageNum
 */
function queryQuestionsForPage(pageNum){
	var url = "/vouching/exam/queryQuestions";
	var data = {
		token : $("#token").val(),
		chapterId : $("#chapterIdHidden").val(),
		source : $("#sourceHidden").val(),
		level : $("#levelHidden").val(),
		pageNum : pageNum
	};
	var successCallback = function(data){
		var questions = data.detail.questions;
		//装载数据
		$("#questionList").children().remove();
		if(questions.length > 0){
			for (var i = 0,j = 1; i < questions.length; i++,j++) {
				$("#questionList").append("<tr><td align='left' valign='top' width='10%'><input qid='" +questions[i].id+ "' name='addQuestion' type='button' value='添加到试卷' /></td><td align='center' width='10%'>"+ j +"&nbsp;</td><td align='left' width='80%'>" +questions[i].question+ "</td></tr>");
				$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc; border-bottom: none;'></td></tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
		} else {
			//
			$("#pager").hide();
		}
		VCUtils.common.pager.front.registerEvent(queryQuestionsForPage);
		bindClickForAddQuestion();
	};
	var faildCallback = function(data){
		divAlert("题目查询失败!");
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
};