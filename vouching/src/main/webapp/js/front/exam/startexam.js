$(function(){
	
	//装载试卷信息
	var url = "/vouching/exam/loadStartExam";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var radios = data.detail.radios;
		var blanks = data.detail.blanks;
		var phrases = data.detail.phrases;
		var clozes = data.detail.clozes;
		var translates = data.detail.translates;
		
		//装载试卷概要内容
		$("#examName").text("试卷名称："+data.detail.examName);
		$("#bak").text("备注："+data.detail.bak);
		
		//装载单选题
		$("#radiosList").children().remove();
		if (radios != null && radios.length > 1) {
			for (var i = 0; i < radios.length; i++) {
				$("#radiosList").append("<tr></tr>");
				var tr = $("#radiosList").children().eq(6*i);
				tr.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + radios[i].question + "</td>");
				for (var j = 0; j < radios[i].options.length; j++) {
					if (j != 0) {
						$("#radiosList").append("<tr><td align='left'><input value='"+(j+1)+"' name='radios"+i+"' type='radio'/>&nbsp;&nbsp;"+ radios[i].options[j] +"</td></tr>");
					} else {
						$("#radiosList").append("<tr><td align='left'><input value='"+(j+1)+"' checked='checked' name='radios"+i+"' type='radio'/>&nbsp;&nbsp;"+ radios[i].options[j] +"</td></tr>");
					}
				}
				$("#radiosList").append("<tr><td style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#radiosList").append("<tr><td>该试卷暂未添加单项选择试题</td></tr>");
		}
		
		//装载名词解释题
		$("#phrasesList").children().remove();
		if (phrases != null && phrases.length > 1) {
			for (var i = 0; i < phrases.length; i++) {
				$("#phrasesList").append("<tr></tr>");
				var tr = $("#phrasesList").children().eq(3*i);
				tr.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + phrases[i].question + "</td>");
				$("#phrasesList").append("<tr><td align='left'><input size='30px'/></td></tr>");
				$("#phrasesList").append("<tr><td style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#phrasesList").append("<tr><td>该试卷暂未添加名词翻译试题</td></tr>");
		}
		
		//装载填空题
		$("#blanksList").children().remove();
		if (blanks != null && blanks.length > 1) {
			for (var i = 0; i < blanks.length; i++) {
				$("#blanksList").append("<tr></tr>");
				var tr = $("#blanksList").children().eq(3*i);
				tr.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + blanks[i].question + "</td>");
				$("#blanksList").append("<tr><td align='left'><input size='30px'/></td></tr>");
				$("#blanksList").append("<tr><td style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#blanksList").append("<tr><td>该试卷暂未添加名词填空试题</td></tr>");
		}
		
		//装载语句翻译题
		$("#translatesList").children().remove();
		if (translates != null && translates.length > 1) {
			for (var i = 0; i < translates.length; i++) {
				$("#translatesList").append("<tr></tr>");
				var tr = $("#translatesList").children().eq(3*i);
				tr.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + translates[i].question + "</td>");
				$("#translatesList").append("<tr><td align='left'><input size='130px'/></td></tr>");
				$("#translatesList").append("<tr><td style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#translatesList").append("<tr><td>该试卷暂未添加语句翻译试题</td></tr>");
		}
		
		//装载完型填空题
		$("#clozesList").children().remove();
		if (clozes != null && clozes.length > 1) {
			for (var i = 0; i < clozes.length; i++) {
				$("#clozesList").append("<tr></tr>");
				var tr = $("#clozesList").children().eq(3*i);
				tr.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + clozes[i].question + "</td>");
				$("#clozesList").append("<tr><td align='left'><input size='130px'/></td></tr>");
				$("#clozesList").append("<tr><td style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#clozesList").append("<tr><td>该试卷暂未添加完型填空试题</td></tr>");
		}
		
		//备份ExamId
		$("#examId").attr("value",data.detail.examId);
	};
	var faildCallback = function(data){
		divAlert(data.errorCode);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
	
	//提交试卷
	$("#submitExam").bind("click",function(){
		var radiosObj = $("#radiosList").find("input:checked");
		var phrasesObj = $("#phrasesList").find("input");
		var clozesObj = $("#clozesList").find("input");
		var blanksObj = $("#blanksList").find("input");
		var translatesObj = $("#translatesList").find("input");
		var radioAnswers = new Array();
		var phraseAnswers = new Array();
		var clozeAnswers = new Array();
		var blankAnswers = new Array();
		var translateAnswers = new Array();
		for (var i = 0; i < radiosObj.length; i++) {
			radioAnswers[i] = $(radiosObj[i]).val();
		}
		for (var i = 0; i < phrasesObj.length; i++) {
			phraseAnswers[i] = $(phrasesObj[i]).val();
		}
		for (var i = 0; i < clozesObj.length; i++) {
			clozeAnswers[i] = $(clozesObj[i]).val();
		}
		for (var i = 0; i < blanksObj.length; i++) {
			blankAnswers[i] = $(blanksObj[i]).val();
		}
		for (var i = 0; i < translatesObj.length; i++) {
			translateAnswers[i] = $(translatesObj[i]).val();
		}
		var url = "/vouching/exam/saveUserExam";
		var data = {
		    token : $("#token").val(),
		    examId : $("#examId").val(),
		    radioAnswers : radioAnswers,
		    phraseAnswers : phraseAnswers,
		    clozeAnswers : clozeAnswers,
		    blankAnswers : blankAnswers,
		    translateAnswers : translateAnswers
		};
		var successCallback = function(data){
			divAlert("试卷保存成功!");
		};
		var faildCallback = function(data){
		    divAlert(data.errorCode);
		};
		VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
	});
	
});