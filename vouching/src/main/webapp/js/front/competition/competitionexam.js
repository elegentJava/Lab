$(function(){
	
	//装载竞技试题
	var url = "/vouching/competition/loadCompetitionExam";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var questions = data.detail.questions;
		for (var i = 0; i < questions.length; i++) {
			$("#questionList").append("<tr></tr>");
			var tr = $("#questionList").children().eq(6*i);
			tr.append("<td colspan='2' align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + questions[i].question + "</td>");
			for (var j = 0; j < questions[i].options.length; j++) {
				$("#questionList").append("<tr><td align='center' valign='top' width='100'></td><td align='left'><input name='answer"+i+"' value='"+(j+1)+"' type='radio'/>&nbsp;&nbsp;"+ questions[i].options[j] +"</td></tr>");
			}
			$("#questionList").append("<tr><td colspan='2' style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
	
	//查看答案并提交
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
		var url = "/vouching/competition/showAnswer";
		var data = {
			token : $("#token").val(),
			answers : answers,
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