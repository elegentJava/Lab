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
			layer.open({
				  type: 1 ,
				  area: ['430px', '250px'] ,
				  shade: 0.6 ,
				  anim: 3 ,
				  closeBtn: 0,
				  title : '结算中,请等待...' ,
				  content: '<div style="padding:10px;"><div style="text-align: center;"><h3>结算中....</h3><h3>答对<span style="color:red" id="score"></span>道题</h3></div><div style="text-align: center;"><h3>题目答案为:<span id="answers"></span></h3></div><div style="text-align: center;"><h3>最后获得积分为:<span style="color:red" id="credit"></span></h3></div><div style="text-align: center;"></div></div>',
				  success : function(){
					  $(this).everyTime('1s','validateSettlement',function(){
						  var url = "/vouching/competition/creditHandle";
						  var data = {
							  token : $("#token").val()
						  };
						  var successCallback = function(data){
							  $(this).stopTime("validateSettlement");
							  $("#credit").text(data.detail.credit);
							  $(this).oneTime('2s',function(){//结算成功后，2s后跳转
								  var url = "/vouching/forward/forwardCompetition?token=" + $("#token").val();
								  VCUtils.common.util.simpleHref(url);
							  });
						  };
						  var faildCallback = function(data){
							  //nope
						  }
						  VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback, null);
						},0,true);
				  }
				});
			var answers = data.detail.answers;
			var score = data.detail.score;
			$("#score").text(score);
			$("#answers").text(answers);
		};
		VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
	});
	
});