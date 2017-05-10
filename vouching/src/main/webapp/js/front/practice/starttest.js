$(function(){
	
	//初始化装载页面
	var url = "/vouching/practice/loadStartTest";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var questions = data.detail.questions;
		var chapterId = data.detail.chapterId;
		$("#chapterIdHidden").attr("value",chapterId);
		if(questions != null){
			$("#title").text(questions[0].tagName);
			if(questions[0].tag == "radio"){
				for (var i = 0; i < questions.length; i++) {
					$("#questionList").append("<tr></tr>");
					var tr = $("#questionList").children().eq(6*i);
					tr.append("<td colspan='2' align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + questions[i].question + "</td>");
					for (var j = 0; j < questions[i].options.length; j++) {
						$("#questionList").append("<tr><td align='center' valign='top' width='100'></td><td align='left'><input name='answer"+i+"' value='"+(j+1)+"' type='radio'/>&nbsp;&nbsp;"+ questions[i].options[j] +"</td></tr>");
					}
					$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
				}
			} else {
				for (var i = 0; i < questions.length; i++) {
					$("#questionList").append("<tr></tr>");
					var tr = $("#questionList").children().eq(3*i);
					tr.append("<td colspan='2' align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + questions[i].question + "</td>");
					$("#questionList").append("<tr><td colspan='2' align='center'><input name='answer' size='200px'/></td></tr>");
					$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
				}
			}
		} else {
			$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
		}
	};
	var faildCallback = function(data){
		$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc;border-bottom: none;color:red'><h2>"+data.errorCode+"</h2></td></tr>");
		$("#showAnswer").hide();
	}
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback, null);
	
	//查看答案并计分
	$("#showAnswer").bind("click",function(){
		var answers = new Array();
		var answerObject = $("input[name='answer']");
		for (var i = 0; i < answerObject.length; i++) {
			answers[i] = $(answerObject[i]).val();
		}
		if(answers.length == 0){
			for (var i = 0; i < 5; i++) {
				answers[i] = $("input[name='answer"+i+"']:checked").attr("value");
			}
		}
		var url = "/vouching/practice/showAnswerAndScore";
		var data = {
			token : $("#token").val(),
			answers : answers,
			chapterId : $("#chapterIdHidden").attr("value")
		};
		var successCallback = function(data){
			var answers = data.detail.answers;
			var score = data.detail.score;
			var text = "";
			for (var i = 0; i < answers.length; i++) {
				text += (i+1)+"、"+answers[i]+"      ";
			}
			$("#answerText").text(text);
			$("#showAnswer").attr("value","最后得分为："+score+"分");
			$("#showAnswer").attr("disabled",true);
		};
		VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
	});
	
});