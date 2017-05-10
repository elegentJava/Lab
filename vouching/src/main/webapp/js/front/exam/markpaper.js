$(function(){
	
	//隐藏分页
	$("#pager").hide();
	
	//装载数据
	loadPageData(1);
	
});

/**
 * 装在分页数据
 * 
 * @param pageNum
 * @returns
 */
function loadPageData(pageNum){
	var loading = layer.load();
	var url = "/vouching/exam/loadMarkPaper";
	var token = $("#token").val();
	var data = {
		token : token,
		pageNum : pageNum
	};
	var successCallback = function(data){
		var datas = data.detail.up;
		$("#markPaperList").children().remove();
		if (datas != null && datas.length >= 1) {
			for (var i = 0; i < datas.length; i++) {
				var url = "/vouching/forward/forwardMarkDetail?token="+token+"&userPaperId="+datas[i].userPaperId;
				$("#markPaperList").append("<tr></tr>");
				var tr = $("#markPaperList").children().eq(i);
				tr.append("<td class='teatbbai' align='center'>" + datas[i].exam.name + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].formatAnswerDate + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].user.name + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].user.clas.className + "</td>");
				tr.append("<td class='teatbbai' align='center'><a name='mark' href='"+url+"'>马上批改</a></td>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.front.registerEvent(loadPageData);
		} else {
			$("#pager").hide();
			$("#markPaperList").append("<tr><td colspan='5' class='teatbbai' align='left' style='font-family: 黑体;color:read;'>暂无可批改试卷信息！</td></tr>");
		}
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}