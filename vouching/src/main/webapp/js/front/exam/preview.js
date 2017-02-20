$(function(){
	
	//初始化装载数据
	initLoadData();
	
	//删除试题
	deleteQuestion();
	
	//查看答案
	showAnswer();
	
});

/**
 * 查看答案
 */
function showAnswer(){
	$("#showAnswer").unbind("click");
	$("#showAnswer").bind("click",function(){
		var flag = $(this).attr("name");
		if ("show" == flag) {
			$(this).attr("value","清除参考答案");
			$(this).attr("name","hidden");
			$("tr[name='answer']").each(function(){
				$(this).show();
			});
		} else if ("hidden" == flag) {
			$(this).attr("value","查看参考答案");
			$(this).attr("name","show");
			$("tr[name='answer']").each(function(){
				$(this).hide();
			});
		}
	});
}

/**
 * 删除试题
 */
function deleteQuestion(){
	$("a[name='delete']").each(function(){
		$(this).bind("click",function(){
			var id = $(this).attr("value");
			var categoryId = $(this).attr("flag");
			var url = "/vouching/exam/deleteQuestion";
			var data = {
				token : $("#token").val(),
				examId : $("#examId").val(),
				questionId : id,
				categoryId : categoryId
			};
			var successCallback = function(data){
				initLoadData();
				deleteQuestion();
				showAnswer();
			};
			var faildCallback = function(data){
				divAlert(data.errorCode);
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
		});
	});
}


/**
 * 初始化装载数据
 */
function initLoadData(){
	var loading = layer.load();
	var url = "/vouching/exam/loadPreview";
	data = {
		token : $("#token").val(),
		examId : $("#examId").val(),
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
		if (radios != null && radios.length > 0) {
			for (var i = 0; i < radios.length; i++) {
				$("#radiosList").append("<tr></tr>");
				var tr = $("#radiosList").children().eq(7*i);
				tr.append("<td align='center' valign='top' width='100'><a flag='1' value='" + radios[i].radioId + "' name='delete' href='javascript:;'>删除题目</a></td>");
				tr.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + radios[i].question + "</td>");
				for (var j = 0; j < radios[i].options.length; j++) {
					$("#radiosList").append("<tr><td align='center' valign='top' width='100'></td><td align='left'><input type='radio'/>&nbsp;&nbsp;"+ radios[i].options[j] +"</td></tr>");
				}
				$("#radiosList").append("<tr name='answer' style='display: none;background-color: #fefef7'><td align='center' valign='top' style='text-indent:10;color: red'>参考答案：</td><td><div style='color: red'>" +radios[i].answer+ "</div></td></tr>");
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
				tr.append("<td align='center' valign='top' width='100'><a flag='5' value='" + phrases[i].phraseId + "' name='delete' href='javascript:;'>删除题目</a></td>");
				tr.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + phrases[i].question + "</td>");
				$("#phrasesList").append("<tr><td></td><td align='left'><input size='30px'/></td></tr>");
				$("#phrasesList").append("<tr name='answer' style='display: none;background-color: #fefef7'><td align='center' valign='top' style='text-indent:10;color: red'>参考答案：</td><td><div style='color: red'>" +phrases[i].answer+ "</div></td></tr>");
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
				tr.append("<td align='center' valign='top' width='100'><a flag='2' value='" + blanks[i].blankId + "' name='delete' href='javascript:;'>删除题目</a></td>");
				tr.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + blanks[i].question + "</td>");
				$("#blanksList").append("<tr><td></td><td align='left'><input size='30px'/></td></tr>");
				$("#blanksList").append("<tr name='answer' style='display: none;background-color: #fefef7'><td align='center' valign='top' style='text-indent:10;color: red'>参考答案：</td><td><div style='color: red'>" +blanks[i].answer+ "</div></td></tr>");
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
				tr.append("<td align='center' valign='top' width='100'><a flag='4' value='" + translates[i].translateId + "' name='delete' href='javascript:;'>删除题目</a></td>");
				tr.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + translates[i].question + "</td>");
				$("#translatesList").append("<tr><td></td><td align='left'><input size='130px'/></td></tr>");
				$("#translatesList").append("<tr name='answer' style='display: none;background-color: #fefef7'><td align='center' valign='top' style='text-indent:10;color: red'>参考答案：</td><td><div style='color: red'>" +translates[i].answer+ "</div></td></tr>");
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
				tr.append("<td align='center' valign='top' width='100'><a flag='3' value='" + clozes[i].clozeId + "' name='delete' href='javascript:;'>删除题目</a></td>");
				tr.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + clozes[i].question + "</td>");
				$("#clozesList").append("<tr><td></td><td align='left'><input size='130px'/></td></tr>");
				$("#clozesList").append("<tr name='answer' style='display: none;background-color: #fefef7'><td align='center' valign='top' style='text-indent:10;color: red'>参考答案：</td><td><div style='color: red'>" +clozes[i].answer+ "</div></td></tr>");
				$("#clozesList").append("<tr><td colspan='2' style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#clozesList").append("<tr><td colspan='2'>该试卷暂未添加完型填空试题</td></tr>");
		}
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}