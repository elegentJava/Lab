$(function(){
	
	//隐藏分页处理
	$("#activePager").hide();
	$("#inactivePager").hide();
	
	//初始化装载数据
	initLoadData("activeList", 1);
	initLoadData("inactiveList", 0);
	
	//修改试卷状态
	updateExamStatus("active", 1);
	updateExamStatus("inactive", 0);
	
	//预览事件
	previewExam();
	
	//删除事件
	deleteExam();
});

/**
 * 初始化装载数据
 * 
 * @param tbodyId
 * @param isActive
 */
function initLoadData(tbodyId,isActive){
	var loading = layer.load();
	var url = "/vouching/exam/loadExamSetting";
	var data = {
		token : $("#token").val(),
		pageNum : 1,
		isActive : isActive
	};
	var successCallback = function(data){
		$("#" + tbodyId).children().remove();
		var datas = null;
		if(isActive == 1){
			datas = data.detail.activeExams;
			if (datas != null && datas.length >= 1) {
				for (var i = 0; i < datas.length; i++) {
					$("#" + tbodyId).append("<tr id='" + datas[i].examId + "'></tr>");
					var tr = $("#" + tbodyId).children().eq(i);
					tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='inactive' href='javascript:;'>取消激活</a></td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].name + "</td>");
					tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='preview' target='_blank' href='/vouching/forward/forwardPreview?examId=" +datas[i].examId + "'>预览</a></td>");
					tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='delete' href='javascript:;'>删除</a></td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].bak + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].formatCreateDate + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].clas.className + "</td>");
				}
				VCUtils.common.pager.front.loadPage(data);
				$("#activePager").show();
				VCUtils.common.pager.front.registerEvent(loadActiveForPage);
			} else {
				$("#activePager").hide();
				$("#activeList").append("<tr><td colspan='7' class='teatbbai' align='left' style='font-family: 黑体;color:read;'>暂无激活的试卷信息！</td></tr>");
			}
		} else {
			datas = data.detail.inactiveExams;
			if (datas != null && datas.length >= 1) {
				for (var i = 0; i < datas.length; i++) {
					$("#" + tbodyId).append("<tr id='" + datas[i].examId + "'></tr>");
					var tr = $("#" + tbodyId).children().eq(i);
					tr.data("examId", datas[i].examId);
					tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='active' href='javascript:;'>激活</a></td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].name + "</td>");
					tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='preview' target='_blank' href='/vouching/forward/forwardPreview?examId=" +datas[i].examId + "'>预览</a></td>");
					tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='delete' href='javascript:;'>删除</a></td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].bak + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].formatCreateDate + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].clas.className + "</td>");
				}
				VCUtils.common.pager.front.loadPage1(data);
				$("#inactivePager").show();
				VCUtils.common.pager.front.registerEvent1(loadInactiveForPage);
			} else {
				$("#inactivePager").hide();
				$("#inactiveList").append("<tr><td colspan='7' class='teatbbai' align='left' style='font-family: 黑体;'>暂无待激活的试卷信息！</td></tr>");
			}
		}
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}

/**
 * 分页跳转装载数据
 * 
 * @param pageNum
 */
function loadActiveForPage(pageNum){
	var loading = layer.load();
	var url = "/vouching/exam/loadExamSetting";
	var data = {
		token : $("#token").val(),
		pageNum : pageNum,
		isActive : 1
	};
	var successCallback = function(data){
		$("#activeList").children().remove();
		datas = data.detail.activeExams;
		if (datas != null && datas.length >= 1) {
			for (var i = 0; i < datas.length; i++) {
				$("#activeList").append("<tr id='" + datas[i].examId + "'></tr>");
				var tr = $("#activeList").children().eq(i);
				tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='inactive' href='javascript:;'>取消激活</a></td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].name + "</td>");
				tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='preview' target='_blank' href='/vouching/forward/forwardPreview?examId=" +datas[i].examId + "'>预览</a></td>");
				tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='delete' href='javascript:;'>删除</a></td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].bak + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].formatCreateDate + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].clas.className + "</td>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#activePager").show();
			VCUtils.common.pager.front.registerEvent(loadActiveForPage);
		} else {
			$("#activePager").hide();
			$("#activeList").append("<tr><td colspan='7' class='teatbbai' align='left' style='font-family: 黑体;color:read;'>暂无激活的试卷信息！</td></tr>");
		}
		refreshForPage();
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}

/**
 * 分页跳转装载数据
 * 
 * @param pageNum
 */
function loadInactiveForPage(pageNum){
	var loading = layer.close();
	var url = "/vouching/exam/loadExamSetting";
	var data = {
		token : $("#token").val(),
		pageNum : pageNum,
		isActive : 0
	};
	var successCallback = function(data){
		$("#inactiveList").children().remove();
		datas = data.detail.inactiveExams;
		if (datas != null && datas.length >= 1) {
			for (var i = 0; i < datas.length; i++) {
				$("#inactiveList").append("<tr id='" + datas[i].examId + "'></tr>");
				var tr = $("#inactiveList").children().eq(i);
				tr.data("examId", datas[i].examId);
				tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='active' href='javascript:;'>激活</a></td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].name + "</td>");
				tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='preview' target='_blank' href='/vouching/forward/forwardPreview?examId=" +datas[i].examId + "'>预览</a></td>");
				tr.append("<td class='teatbbai' align='center'><a value='" + datas[i].examId + "' name='delete' href='javascript:;'>删除</a></td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].bak + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].formatCreateDate + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].clas.className + "</td>");
			}
			VCUtils.common.pager.front.loadPage1(data);
			$("#inactivePager").show();
			VCUtils.common.pager.front.registerEvent1(loadInactiveForPage);
		} else {
			$("#inactivePager").hide();
			$("#inactiveList").append("<tr><td colspan='7' class='teatbbai' align='left' style='font-family: 黑体;'>暂无待激活的试卷信息！</td></tr>");
		}
		refreshForPage();
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}

/**
 * 修改试卷状态
 * 
 * @param id
 * @param status
 */
function updateExamStatus(id,status){
	$("a[name='" +id+ "']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			var url = "/vouching/exam/updateExamStatus";
			var data = {
				token : $("#token").val(),
				examId : $(this).attr("value"),
				status : status
			};
			var successCallback = function(data){
				refresh();
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
		});
	});
}

/**
 * 删除试卷
 */
function deleteExam(){
	$("a[name='delete']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			var url = "/vouching/exam/deleteExam";
			var data = {
				token : $("#token").val(),
				examId : $(this).attr("value")
			};
			var successCallback = function(data){
				layer.msg("删除成功!");
				refresh();
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
		});
	});
}

/**
 * 预览试卷
 */
function previewExam(){
	$("a[name='preview']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			var url = $(this).attr("href");
			$(this).attr("href",url + "&token=" + $("#token").val());
		});
	});
}

/**
 * 刷新数据和事件
 */
function refresh(){
	initLoadData("activeList", 1);
	initLoadData("inactiveList", 0);
	updateExamStatus("active", 1);
	updateExamStatus("inactive", 0);
	deleteExam();
	previewExam();
}

/**
 * 分页刷新事件
 */
function refreshForPage(){
	updateExamStatus("active", 1);
	updateExamStatus("inactive", 0);
	deleteExam();
	previewExam();
}