var tree = new dTree("tree");

$(function(){
	
	//隐藏分页项
	$("#pager").hide();
	
	//初始化加载数据
	initLoadData();
	
	//题目类型变更
	typeChange();
	
	//难度类型变更
	
	//清除所有题目
	
	//存卷
	
	//预览
	
});

/**
 * 初始化装载数据
 */
function initLoadData(){
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
		/**
		 * 装载章节树
		 */
		tree.add(0, -1, "章节列表","");
		for (var i = 0; i < chapters.length; i++) {
			tree.add(chapters[i].chapterId, 0, chapters[i].name, "1", chapters[i].name, "main");
		} 
		document.getElementById("dtree").innerHTML=tree;
		//为章节树添加点击事件
		$("a[href='1']").each(function(){
			$(this).unbind("click");
			$(this).bind("click",function(){
				var chapterId = $(this).attr("id").slice($(this).attr("id").length - 1);
				
			});
		});
		/**
		 * 装载题目类型
		 */
		for (var i = 0; i < questionTypes.length; i++) {
			//装载上面的按钮
			$("#types").append("<td width='60px'>"+questionTypes[i].name+"</td>");
			$("#types").append("<td source='" +questionTypes[i].id+ "'>0</td>");
			$("td[source='"+questionTypes[i].id+"']").data("selected","none");
			$("#types").append("<td><input source='" +questionTypes[i].id+ "' name='clear' type='button' value='清除'/></td>");
			//装载选择框
			$("#source").append("<option value='"+questionTypes[i].id+"'>"+questionTypes[i].name+"</option>");
		}
		/**
		 * 装载难易程度
		 */
		for (var i = 0; i < levels.length; i++) {
			$("#levels").append("<option value='"+levels[i].id+"'>"+levels[i].name+"</option>");
		}
		/**
		 * 装载默认的单选试题
		 */
		$("#questionList").children().remove();
		if(radios.length > 0){
			for (var i = 0,j = 1; i < radios.length; i++,j++) {
				$("#questionList").append("<tr><td align='left' valign='top' width='10%'><input qid='" +radios[i].id+ "' name='addQuestion' type='button' value='添加' /></td><td align='center' width='10%'>"+ j +"&nbsp;</td><td align='left' width='80%'>" +radios[i].question+ "</td></tr>");
				$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc; border-bottom: none;'></td></tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
		} else {
			$("#pager").hide();
			$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc; border-bottom: none;'>没有相关的题目!</td></tr>");
		}
		VCUtils.common.pager.front.registerEvent();
		//添加题目事件
		addQuestion();
	};
	VCUtils.common.ajax.commonAjax(url, true, data, successCallback, null);
}

/**
 * 添加题目
 */
function addQuestion(){
	$("input[name='addQuestion']").each(function(){
		$(this).unbind("change");
		$(this).bind("change",function(){
			var a = $("td[source='1']").data("selected");
			alert(a);
		});
	});
}

/**
 * 清除所有题目
 */
function clearQuestion(){
	
}

/**
 * 题目类型变更
 */
function typeChange(){
	$("#source").unbind("change");
	$("#source").bind("change",function(){
		var source = $(this).val();
		var radioSelected = $("td[source='" + source + "']").data("selected");
		alert(source);
	});
}

/**
 * 难度变更
 */
function levelChange(){
	
}

/**
 * 保存试卷
 */
function saveExam(){
	
}

/**
 * 预览试卷
 */
function previewExam(){
	
}

/**
 * 考试名称校验
 */
function nameValidate(){
	
}

/**
 * 装载分页数据
 */
function loadPageData(chapterId,source,level,pageNum){
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
		pageNum : pageNum
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
				$("#questionList").append("<tr><td align='left' valign='top' width='10%'><input qid='" +questions[i].id+ "' name='addQuestion' type='button' value='添加' /></td><td align='center' width='10%'>"+ j +"&nbsp;</td><td align='left' width='80%'>" +questions[i].question+ "</td></tr>");
				$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc; border-bottom: none;'></td></tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
		} else {
			//
			$("#pager").hide();
		}
		VCUtils.common.pager.front.registerEvent(queryQuestionsForPage);
		addQuestion();
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
}