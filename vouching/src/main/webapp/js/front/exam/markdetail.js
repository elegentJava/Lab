$(function(){
	
	var loading = layer.load();
	var url = "/vouching/exam/loadMarkDetail";
	data = {
		token : $("#token").val(),
		userPaperId : $("#userPaperId").val(),
	};
	var successCallback = function(data) {
		var radios = data.detail.radios;
		var blanks = data.detail.blanks;
		var phrases = data.detail.phrases;
		var clozes = data.detail.clozes;
		var translates = data.detail.translates;
		var radioAnswers = data.detail.radioAnswers;
		var blankAnswers = data.detail.blankAnswers;
		var phraseAnswers = data.detail.phraseAnswers;
		var translateAnswers = data.detail.translateAnswers;
		var clozeAnswers = data.detail.clozeAnswers;
		
		//装载试卷概要内容
		$("#examName").text("试卷名称："+data.detail.examName);
		$("#username").text("学生姓名："+data.detail.username);
		$("#className").text("班级："+data.detail.className);
		$("#radioScore").text("("+data.detail.radioScore+"分)");
		$("#blankScore").text("("+data.detail.blankScore+"分)");
		$("#phraseScore").text("("+data.detail.phraseScore+"分)");
		$("#translateScore").text("("+data.detail.translateScore+"分)");
		$("#clozeScore").text("("+data.detail.clozeScore+"分)");
		
		//装载单选题
		$("#radiosList").children().remove();
		if (radios != null && radios.length > 0) {
			for (var i = 0,k = 0; i < radios.length; i++,k++) {
				$("#radiosList").append("<tr></tr>");
				var tr = $("#radiosList").children().eq(7*i);
				tr.append("<td align='center' valign='top' width='100'></td>");
				tr.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + radios[i].question + "</td>");
				for (var j = 0; j < radios[i].options.length; j++) {
					if (j + 1 != radioAnswers[k]) {
						$("#radiosList").append("<tr><td align='center' valign='top' width='100'></td><td align='left'><input type='radio'/>&nbsp;&nbsp;"+ radios[i].options[j] +"</td></tr>");
					} else {
						$("#radiosList").append("<tr><td align='center' valign='top' width='100'></td><td align='left'><input checked='checked' type='radio'/>&nbsp;&nbsp;"+ radios[i].options[j] +"</td></tr>");
					}
				}
				$("#radiosList").append("<tr name='answer' style='background-color: #fefef7'><td></td><td align='left' valign='top' style='text-indent:10;color: red'><h4>参考答案："+radios[i].answer+"<h4/></td></tr>");
				$("#radiosList").append("<tr><td colspan='2' style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#radiosList").append("<tr><td colspan='2'>该试卷暂未添加单项选择试题</td></tr>");
		}
		
		//装载名词解释题
		$("#phrasesList").children().remove();
		if (phrases != null && phrases.length > 0) {
			for (var i = 0; i < phrases.length; i++) {
				$("#phrasesList").append("<tr></tr>");
				var tr = $("#phrasesList").children().eq(4*i);
				tr.append("<td></td><td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + phrases[i].question + "</td>");
				$("#phrasesList").append("<tr><td>分数:&nbsp;&nbsp;<input name='score' style='width:40px'/></td><td align='left'><input value='"+phraseAnswers[i]+"' size='30px'/></td></tr>");
				$("#phrasesList").append("<tr name='answer' style='background-color: #fefef7'><td></td><td align='left' valign='top' style='text-indent:10;color: red'><h4>参考答案："+phrases[i].answer+"<h4/></td></tr>");
				$("#phrasesList").append("<tr><td colspan='2' style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#phrasesList").append("<tr><td colspan='2'>该试卷暂未添加名词翻译试题</td></tr>");
		}
		
		//装载填空题
		$("#blanksList").children().remove();
		if (blanks != null && blanks.length > 0) {
			for (var i = 0; i < blanks.length; i++) {
				$("#blanksList").append("<tr></tr>");
				var tr = $("#blanksList").children().eq(4*i);
				tr.append("<td></td><td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + blanks[i].question + "</td>");
				$("#blanksList").append("<tr><td>分数:&nbsp;&nbsp;<input name='score' style='width:40px'/></td><td align='left'><input value='"+blankAnswers[i]+"' size='30px'/></td></tr>");
				$("#blanksList").append("<tr name='answer' style='background-color: #fefef7'><td></td><td align='left' valign='top' style='text-indent:10;color: red'><h4>参考答案："+blanks[i].answer+"<h4/></td></tr>");
				$("#blanksList").append("<tr><td colspan='2' style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#blanksList").append("<tr><td colspan='2'>该试卷暂未添加名词填空试题</td></tr>");
		}
		
		//装载语句翻译题
		$("#translatesList").children().remove();
		if (translates != null && translates.length > 0) {
			for (var i = 0; i < translates.length; i++) {
				$("#translatesList").append("<tr></tr>");
				var tr = $("#translatesList").children().eq(4*i);
				tr.append("<td></td><td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + translates[i].question + "</td>");
				$("#translatesList").append("<tr><td>分数:&nbsp;&nbsp;<input name='score' style='width:40px'/></td><td align='left'><input value='"+translateAnswers[i]+"' size='130px'/></td></tr>");
				$("#translatesList").append("<tr name='answer' style='background-color: #fefef7'><td></td><td align='left' valign='top' style='text-indent:10;color: red'><h4>参考答案："+translates[i].answer+"<h4/></td></tr>");
				$("#translatesList").append("<tr><td colspan='2' style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#translatesList").append("<tr><td colspan='2'>该试卷暂未添加语句翻译试题</td></tr>");
		}
		
		//装载完型填空题
		$("#clozesList").children().remove();
		if (clozes != null && clozes.length > 0) {
			for (var i = 0; i < clozes.length; i++) {
				$("#clozesList").append("<tr></tr>");
				var tr = $("#clozesList").children().eq(4*i);
				tr.append("<td></td><td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + clozes[i].question + "</td>");
				$("#clozesList").append("<tr><td>分数:&nbsp;&nbsp;<input name='score' style='width:40px'/></td><td align='left'><input value='"+clozeAnswers[i]+"' size='130px'/></td></tr>");
				$("#clozesList").append("<tr name='answer' style='background-color: #fefef7'><td></td><td align='left' valign='top' style='text-indent:10;color: red'><h4>参考答案："+clozes[i].answer+"<h4/></td></tr>");
				$("#clozesList").append("<tr><td colspan='2' style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#clozesList").append("<tr><td colspan='2'>该试卷暂未添加完型填空试题</td></tr>");
		}
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
	
	//批阅试卷
	$("#mark").bind("click",function(){
		var blankObj = $("#blanksList>tr>td>input[name='score']");
		var blankScores = new Array();
		for (var i = 0; i < blankObj.length; i++) {
			blankScores[i] = $(blankObj[i]).val();
		}
		var phraseObj = $("#phrasesList>tr>td>input[name='score']");
		var phraseScores = new Array();
		for (var i = 0; i < phraseObj.length; i++) {
			phraseScores[i] = $(phraseObj[i]).val();
		}
		var clozeObj = $("#clozesList>tr>td>input[name='score']");
		var clozeScores = new Array();
		for (var i = 0; i < clozeObj.length; i++) {
			clozeScores[i] = $(clozeObj[i]).val();
		}
		var translateObj = $("#translatesList>tr>td>input[name='score']");
		var translateScores = new Array();
		for (var i = 0; i < translateObj.length; i++) {
			translateScores[i] = $(translateObj[i]).val();
		}
		var url = "/vouching/exam/markPaper";
		var data = {
			token : $("#token").val(),
			userPaperId : $("#userPaperId").val(),
			blankScores : blankScores,
			phraseScores : phraseScores,
			clozeScores : clozeScores,
			translateScores :translateScores
		};
		var successCallback = function(data){
			layer.msg("批改成功!");
			var url = "/vouching/forward/forwardMarkPaper?token="+$("#token").val();
			VCUtils.common.util.simpleHref(url);
		};
		VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
	});
	
});