$(function(){
	
	//装载章节信息
	var url = "/vouching/exam/loadChapters";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var activeChapter = data.detail.activeChapters;
		var inactiveChapter = data.detail.inactiveChapters;
		if (activeChapter != null && activeChapter.length >= 1) {
			for (var i = 0; i < activeChapter.length; i++) {
				$("#activeList").append("<tr id='"+activeChapter[i].chapterId+"' class='teatbbai' align='center'><td><a chapterName='"+activeChapter[i].name+"' value='"+activeChapter[i].chapterId+"' name='inactive' href='javascript:;'>取消激活</a></td><td>"+activeChapter[i].name+"</td></tr>");
			}
		} else {
			$("#activeList").append("<tr><td>暂无已经激活的章节信息!</td></tr>");
		}
		if (inactiveChapter != null && inactiveChapter.length >= 1) {
			for (var i = 0; i < inactiveChapter.length; i++) {
				$("#inactiveList").append("<tr id='"+inactiveChapter[i].chapterId+"' class='teatbbai' align='center'><td><a chapterName='"+inactiveChapter[i].name+"' value='"+inactiveChapter[i].chapterId+"' name='active' href='javascript:;'>激活</a></td><td>"+inactiveChapter[i].name+"</td></tr>");
			}
		} else {
			$("#inactiveList").append("<tr><td>暂无未激活的章节信息!</td></tr>");
		}
	};
	var failedCallback = function(data){
		divAlert(data.errorCode);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, failedCallback);
	
	//取消激活
	inactiveChapter();
	
	//激活
	activeChapter();
	
});

/**
 * 激活章节
 */
function activeChapter(){
	$("a[name='active']").each(function(){
		$(this).bind("click",function(){
			var id = $(this).attr("value");
			var chapterName = $(this).attr("chapterName");
			var url = "/vouching/exam/updateChapterStatus";
			var data = {
				token : $("#token").val(),
				chapterId : id,
				status : 1
			};
			var successCallback = function(data){
				$("#"+id).remove();
				$("#activeList").append("<tr id='"+id+"' class='teatbbai' align='center'><td><a chapterName='"+chapterName+"' value='"+id+"' name='inactive' href='javascript:;'>取消激活</a></td><td>"+chapterName+"</td></tr>");
				inactiveChapter();
			};
			var faildCallback = function(data){
				divAlert(data.errorCode);
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
		});
	});
}

/**
 * 取消激活章节
 */
function inactiveChapter(){
	$("a[name='inactive']").each(function(){
		$(this).bind("click",function(){
			var id = $(this).attr("value");
			var chapterName = $(this).attr("chapterName");
			var url = "/vouching/exam/updateChapterStatus";
			var data = {
				token : $("#token").val(),
				chapterId : id,
				status : 0
			};
			var successCallback = function(data){
				$("#"+id).remove();
				$("#inactiveList").append("<tr id='"+id+"' class='teatbbai' align='center'><td><a chapterName='"+chapterName+"' value='"+id+"' name='active' href='javascript:;'>激活</a></td><td>"+chapterName+"</td></tr>");
				activeChapter();
			};
			var faildCallback = function(data){
				divAlert(data.errorCode);
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
		});
	});
}