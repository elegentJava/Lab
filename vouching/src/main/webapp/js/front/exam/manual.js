var tree = new dTree("tree");

$(function(){
	
	//隐藏分页项
	$("#pager").hide();
	
	//初始化加载数据
	initLoadData();
	
	//题目类型变更
	typeChange();
	
	//难度类型变更
	levelChange();
	
	//存卷并预览
	saveExam();
	
	
});

/**
 * 初始化装载数据
 */
function initLoadData(){
	var loading = layer.load();
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
			$(this).attr("href","javascript:;");
			$(this).unbind("click");
			$(this).bind("click",function(){
				var chapterId = $(this).attr("id").slice($(this).attr("id").length - 1);
				loadPageData(null, chapterId, 1, 0);
			});
		});
		/**
		 * 装载题目类型
		 */
		for (var i = 0; i < questionTypes.length; i++) {
			//装载上面的按钮
			$("#types").append("<td width='60px'>"+questionTypes[i].name+"</td>");
			$("#types").append("<td source='" +questionTypes[i].id+ "'>0</td>");
			$("td[source='"+questionTypes[i].id+"']").data("selected",new Array());
			$("#types").append("<td><input style='width:40px;' source='" +questionTypes[i].id+ "' name='clear' type='button' value='清除'/></td>");
			//装载选择框
			$("#source").append("<option value='"+questionTypes[i].id+"'>"+questionTypes[i].name+"</option>");
			//清除所有题目事件
			clear();
			var scoreId = questionTypes[i].tag + "Score";
			$("#scores").append("<td width='60px'>总分:</td><td></td><td><input style='width:40px;' id='"+scoreId+"'/></td>")
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
		VCUtils.common.pager.front.registerEvent(loadPageData);
		//添加题目事件
		addQuestion();
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, true, data, successCallback, null, loading);
}

/**
 * 添加题目
 */
function addQuestion(){
	$("input[name='addQuestion']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			var name = $(this).attr("name");
			var source = $("#sourceHidden").attr("value");
			var qid = $(this).attr("qid");
			var selected = getSelected(source);
			var count = $("td[source='" +source+ "']").text();
			var updatedCount = 0;
			if (name == "addQuestion") {
				selected.push(qid);
				//修改自身
				$(this).attr("name","clearSingle");
				$(this).attr("value","删除");
				$(this).css("color","red");
				//题目数修改
				updatedCount = parseInt(count) + 1;
			} else {
				//剔除数据
				for(var i = 0 ; i < selected.length ; i++){
					if (qid == selected[i]) {
						var temp = selected[i];
						selected[i] = selected[selected.length-1];
						selected[selected.length-1] = temp;
						break;
					}
				}
				selected.pop();
				//修改自身
				$(this).attr("name","addQuestion");
				$(this).attr("value","添加");
				$(this).css("color","black");
				//题目数修改
				updatedCount = parseInt(count) - 1;
			}
			$("td[source='" +source+ "']").text(updatedCount);
		});
	});
}

/**
 * 清除一个题目
 */
function clearSingle(){
	$("input[name='clearSingle']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			var name = $(this).attr("name");
			var source = $("#sourceHidden").attr("value");
			var qid = $(this).attr("qid");
			var selected = getSelected(source);
			var count = $("td[source='" +source+ "']").text();
			var updatedCount = 0;
			if (name == "addQuestion") {
				selected.push(qid);
				//修改自身
				$(this).attr("name","clearSingle");
				$(this).attr("value","删除");
				$(this).css("color","red");
				//题目数修改
				updatedCount = parseInt(count) + 1;
			} else {
				//剔除数据
				for(var i = 0 ; i < selected.length ; i++){
					if (qid == selected[i]) {
						var temp = selected[i];
						selected[i] = selected[selected.length-1];
						selected[selected.length-1] = temp;
						break;
					}
				}
				selected.pop();
				//修改自身
				$(this).attr("name","addQuestion");
				$(this).attr("value","添加");
				$(this).css("color","black");
				//题目数修改
				updatedCount = parseInt(count) - 1;
			}
			$("td[source='" +source+ "']").text(updatedCount);
		});
	});
}

/**
 * 清除所有题目
 */
function clear(){
	$("input[name='clear']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			var source = $(this).attr("source");
			resetSelected(source);
			$("td[source='"+source+"']").text("0");
			loadPageData();
		});
	});
}

/**
 * 题目类型变更
 */
function typeChange(){
	$("#source").unbind("change");
	$("#source").bind("change",function(){
		var source = $(this).val();
		loadPageData(null, null, source, null);
	});
}

/**
 * 难度变更
 */
function levelChange(){
	$("#levels").unbind("change");
	$("#levels").bind("change",function(){
		var level = $(this).val();
		loadPageData(null, null, null, level);
	});
}

/**
 * 保存试卷
 */
function saveExam(){
	$("#saveExam").unbind("click");
	$("#saveExam").bind("click",function(){
		var examName = $("#name").val();
		var bak = $("#bak").val();
		var radios = getSelected(1);
		var blanks = getSelected(2);
		var clozes = getSelected(3);
		var translates = getSelected(4);
		var phrases = getSelected(5);
		var token = $("#token").val();
		if (examNameValidate()) {
			var url = "/vouching/exam/manualSaveExam";
			var data = {
				token : token,
				examName : examName,
				bak : bak,
				radios : radios,
				blanks : blanks,
				clozes : clozes,
				phrases : phrases,
				translates : translates,
				radioScore : $("#radioScore").val(),
				blankScore : $("#blankScore").val(),
				clozeScore : $("#clozeScore").val(),
				phraseScore : $("#phraseScore").val(),
				translateScore : $("#translateScore").val(),
			};
			var successCallback = function(data){
				layer.msg("试卷创建成功!");
				var url = "/vouching/forward/forwardPreview?examId=" + data.detail.examId + "&token=" + token;
				VCUtils.common.util.simpleHref(url);
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
		}
	});
}

/**
 * 考试名称校验
 */
function examNameValidate(){
	var name = $("#name").val();
	if (VCUtils.common.util.isNotNullOrBlank(name)) {
		var flag = true;
		var url = "/vouching/exam/validateExamName";
		var data = {
			token : $("#token").val(),
			name : name
		};
		var successCallback = function(data){
			$("#errorMsg").text("");
		};
		var faildCallback = function(data){
			$("#errorMsg").text(data.errorCode);
			flag = false;
		};
		VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback, null);
		return flag;
	} else {
		$("#errorMsg").text("请输入考试名称!");
		return false;
	}
}

/**
 * 装载分页数据
 */
function loadPageData(pageNum,chapterId,source,level){
	var loading = layer.load();
	if(chapterId == null){
		chapterId = $("#chapterIdHidden").val();
	}
	if(source == null){
		source = $("#sourceHidden").val();
	}
	if(level == null){
		level = $("#levelHidden").val();
	}
	if(pageNum == null){
		pageNum = 1;
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
				var selected = getSelected(source);
				var flag = true;
				for(var k = 0 ; k < selected.length ; k++){
					if (selected[k] == questions[i].id) {
						flag = false;
						$("#questionList").append("<tr><td align='left' valign='top' width='10%'><input style='color:red' qid='" +questions[i].id+ "' name='clearSingle' type='button' value='删除' /></td><td align='center' width='10%'>"+ j +"&nbsp;</td><td align='left' width='80%'>" +questions[i].question+ "</td></tr>");
						break;
					}
				}
				if (flag) {
					$("#questionList").append("<tr><td align='left' valign='top' width='10%'><input qid='" +questions[i].id+ "' name='addQuestion' type='button' value='添加' /></td><td align='center' width='10%'>"+ j +"&nbsp;</td><td align='left' width='80%'>" +questions[i].question+ "</td></tr>");
				}
				$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc; border-bottom: none;'></td></tr>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
		} else {
			$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc; border-bottom: none;'>暂无相关题目!</td></tr>");
			$("#pager").hide();
		}
		VCUtils.common.pager.front.registerEvent(loadPageData);
		addQuestion();
		clearSingle();
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}

/**
 * 获取已经选择的所有ID
 * 
 * @param source
 * @returns
 */
function getSelected(source) {
	return $("td[source='" + source + "']").data("selected");
}

/**
 * 重置选择的所有ID
 * 
 * @param source
 * @param data
 * @returns
 */
function resetSelected(source) {
	return $("td[source='" + source + "']").data("selected",new Array());
}