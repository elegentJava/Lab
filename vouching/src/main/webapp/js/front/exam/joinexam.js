$(function(){
	
	//隐藏分页处理
	$("#joinedPager").hide();
	$("#unjoinedPager").hide();
	
	//装载试卷信息
	initLoadData("unjoinedList",0);
	initLoadData("joinedList",1);
	
	//参加考试事件
	joinExam();
	
});

/**
 * 根据试卷状态装载试卷信息
 * 
 * @param joinStatus
 */
function initLoadData(tbodyId,joinStatus){
	var url = "/vouching/exam/loadJoinExam";
	var data = {
		token : $("#token").val(),
		pageNum : 1,
		joinStatus : joinStatus
	};
	var successCallback = function(data){
		$("#" + tbodyId).children().remove();
		var datas = null;
		if(joinStatus == 1){
			datas = data.detail.joinedExams;
			if (datas != null && datas.length >= 1) {
				for (var i = 0; i < datas.length; i++) {
					$("#" + tbodyId).append("<tr id='" + datas[i].examId + "'></tr>");
					var tr = $("#" + tbodyId).children().eq(i);
					tr.append("<td class='teatbbai' align='center'>" + datas[i].name + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].bak + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].formatCreateDate + "</td>");
				}
				VCUtils.common.pager.front.loadPage(data);
				$("#joinedPager").show();
				VCUtils.common.pager.front.registerEvent(loadJoinedForPage);
			} else {
				$("#joinedPager").hide();
				$("#" + tbodyId).append("<tr><td colspan='3' class='teatbbai' align='left' style='font-family: 黑体;color:read;'>暂无已经参加的试卷信息！</td></tr>");
			}
		} else {
			datas = data.detail.unjoinedExams;
			if (datas != null && datas.length >= 1) {
				for (var i = 0; i < datas.length; i++) {
					$("#" + tbodyId).append("<tr id='" + datas[i].examId + "'></tr>");
					var tr = $("#" + tbodyId).children().eq(i);
					tr.append("<td class='teatbbai' align='center'>" + datas[i].name + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].bak + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].formatCreateDate + "</td>");
					tr.append("<td class='teatbbai' align='center'><a name='joinExam' examId='"+datas[i].examId+"' href='javascript:;'>参加考试</a></td>");
				}
				VCUtils.common.pager.front.loadPage1(data);
				$("#unjoinedPager").show();
				VCUtils.common.pager.front.registerEvent1(loadUnjoinedForPage);
			} else {
				$("#unjoinedPager").hide();
				$("#" + tbodyId).append("<tr><td colspan='4' class='teatbbai' align='left' style='font-family: 黑体;'>暂无尚未参加的试卷信息！</td></tr>");
			}
		}
	};
	var failedCallback = function(data){
		divAlert(data.errorCode);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, failedCallback);
}

/**
 * 装载已经参加过的考试信息
 * 
 * @param pageNum
 */
function loadJoinedForPage(pageNum){
	var url = "/vouching/exam/loadJoinExam";
	var data = {
		token : $("#token").val(),
		pageNum : pageNum,
		joinStatus : 1
	};
	var successCallback = function(data){
		$("#joinedList").children().remove();
		datas = data.detail.joinedExams;
		if (datas != null && datas.length >= 1) {
			for (var i = 0; i < datas.length; i++) {
				$("#joinedList").append("<tr id='" + datas[i].examId + "'></tr>");
				var tr = $("#joinedList").children().eq(i);
				tr.append("<td class='teatbbai' align='center'>" + datas[i].name + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].bak + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].formatCreateDate + "</td>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#joinedPager").show();
			VCUtils.common.pager.front.registerEvent(loadJoinedForPage);
		} else {
			$("#joinedPager").hide();
			$("#joinedList").append("<tr><td colspan='3' class='teatbbai' align='left' style='font-family: 黑体;color:read;'>暂无激活的试卷信息！</td></tr>");
		}
	};
	var failedCallback = function(data){
		divAlert(data.errorCode);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, failedCallback);
}

/**
 * 装载已经参加过的考试信息
 * 
 * @param pageNum
 */
function loadUnjoinedForPage(pageNum){
	var url = "/vouching/exam/loadJoinExam";
	var data = {
		token : $("#token").val(),
		pageNum : pageNum,
		joinStatus : 0
	};
	var successCallback = function(data){
		$("#unjoinedList").children().remove();
		datas = data.detail.unjoinedExams;
		if (datas != null && datas.length >= 1) {
			for (var i = 0; i < datas.length; i++) {
				$("#unjoinedList").append("<tr id='" + datas[i].examId + "'></tr>");
				var tr = $("#unjoinedList").children().eq(i);
				tr.append("<td class='teatbbai' align='center'>" + datas[i].name + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].bak + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].formatCreateDate + "</td>");
				tr.append("<td class='teatbbai' align='center'><a target='_blank' name='joinExam' examId='"+datas[i].examId+"' href='javascript:;'>参加考试</a></td>");
			}
			VCUtils.common.pager.front.loadPage1(data);
			$("#unjoinedPager").show();
			VCUtils.common.pager.front.registerEvent1(loadUnjoinedForPage);
		} else {
			$("#unjoinedPager").hide();
			$("#unjoinedList").append("<tr><td colspan='4' class='teatbbai' align='left' style='font-family: 黑体;color:read;'>暂无激活的试卷信息！</td></tr>");
		}
		joinExam();
	};
	var failedCallback = function(data){
		divAlert(data.errorCode);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, failedCallback);
}

/**
 * 绑定参加考试事件
 */
function joinExam(){
	
	$("a[name='joinExam']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			token = $("#token").val();
			var examId = $(this).attr("examId");
			var url = "/vouching/exam/startExam";
			var data = {
				token : token,
				examId : examId
			};
			var successCallback = function(data){
				var url = "/vouching/forward/forwardStartExam?token="+token;
				VCUtils.common.util.simpleHref(url);
			};
			var failedCallback = function(data){
				divAlert(data.errorCode);
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, failedCallback);
		});
	});
}

