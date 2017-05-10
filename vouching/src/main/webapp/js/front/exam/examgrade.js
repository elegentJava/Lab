$(function() {
	
	$("#pager").hide();

	// 装载考试信息
	loadExamData();

	// 装载成绩信息
	loadGradeData(1);
	
	//考试信息改变事件
	examChange();

});

/**
 * 装载考试信息
 * 
 * @returns
 */
function loadExamData() {
	var url = "/vouching/exam/loadGradeExam";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var exams = data.detail.exams;
		if(exams != null && exams.length >0){
			var select = $("#exams>select");
			for (var i = 0; i < exams.length; i++) {
				$(select).append("<option value='"+exams[i].examId+"'>"+exams[i].name+"</option>");
			}
			$("#examId").attr("value",exams[0].examId);
		} else {
			$("#exams").remove();
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
}

/**
 * 装载成绩信息
 * 
 * @returns
 */
function loadGradeData(pageNum) {
	var loading = layer.load();
	var url = "/vouching/exam/loadExamGrade";
	var data = {
		token : $("#token").val(),
		examId : $("#examId").val(),
		pageNum : pageNum,
	};
	var successCallback = function(data){
		$("#examGradeList").children().remove();
		var datas = data.detail.grades;
		if (datas != null && datas.length >= 1) {
			for (var i = 0; i < datas.length; i++) {
				$("#examGradeList").append("<tr></tr>");
				var tr = $("#examGradeList").children().eq(i);
				tr.append("<td class='teatbbai' align='center'>" + datas[i].user.name + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].exam.name + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].formatAnswerDate + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].score + "</td>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.front.registerEvent(loadGradeData);
		} else {
			$("#pager").hide();
			$("#examGradeList").append("<tr><td colspan='4' class='teatbbai' align='left' style='font-family: 黑体;color:read;'>暂无成绩信息！</td></tr>");
		}
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}

/**
 * 考试信息改变事件
 * 
 * @returns
 */
function examChange() {
	$("#exams>select").bind("change", function() {
		var examId = $(this).val();
		$("#examId").attr("value",examId);
		loadGradeData(1);
	});
}