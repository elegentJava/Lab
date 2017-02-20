$(function(){
	
	//隐藏分页处理
	$("#pager").hide();
	
	//装载试卷信息
	loadExamRecord(1);
	
});

/**
 * 装载历史成绩信息
 * 
 * @param pageNum
 */
function loadExamRecord(pageNum){
	var loading = layer.load();
	var url = "/vouching/exam/loadExamRecord";
	var data = {
		token : $("#token").val(),
		pageNum : pageNum,
	};
	var successCallback = function(data){
		$("#examRecordList").children().remove();
		var datas = data.detail.examRecords;
		if (datas != null && datas.length >= 1) {
			for (var i = 0; i < datas.length; i++) {
				$("#examRecordList").append("<tr></tr>");
				var tr = $("#examRecordList").children().eq(i);
				tr.append("<td class='teatbbai' align='center'>" + datas[i].exam.name + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].formatAnswerDate + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].score + "</td>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.front.registerEvent(loadExamRecord);
		} else {
			$("#pager").hide();
			$("#examRecordList").append("<tr><td colspan='3' class='teatbbai' align='left' style='font-family: 黑体;color:read;'>暂无成绩信息！</td></tr>");
		}
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}

