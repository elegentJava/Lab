$(function(){
	
	//装载章节信息
	loadChapter();
	
	//取消激活
	inactiveChapter();
	
	//激活
	activeChapter();
	
});

/**
 * 装载章节信息
 */
function loadChapter(){
	var url = "/vouching/exam/loadChapters";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		$("#activeList").children().remove();
		$("#inactiveList").children().remove();
		var activeChapter = data.detail.activeChapters;
		var inactiveChapter = data.detail.inactiveChapters;
		if (activeChapter != null && activeChapter.length >= 1) {
			for (var i = 0; i < activeChapter.length; i++) {
				$("#activeList").append("<tr id='"+activeChapter[i].chapterId+"' class='teatbbai' align='center'><td><a chapterName='"+activeChapter[i].name+"' value='"+activeChapter[i].chapterId+"' name='inactive' href='javascript:;'>取消激活</a></td><td>"+activeChapter[i].name+"</td></tr>");
			}
		} else {
			$("#activeList").append("<tr class='teatbbai'><td colspan='2'>暂无已经激活的章节信息!</td></tr>");
		}
		if (inactiveChapter != null && inactiveChapter.length >= 1) {
			for (var i = 0; i < inactiveChapter.length; i++) {
				$("#inactiveList").append("<tr id='"+inactiveChapter[i].chapterId+"' class='teatbbai' align='center'><td><a chapterName='"+inactiveChapter[i].name+"' value='"+inactiveChapter[i].chapterId+"' name='active' href='javascript:;'>激活</a></td><td>"+inactiveChapter[i].name+"</td></tr>");
			}
		} else {
			$("#inactiveList").append("<tr class='teatbbai'><td colspan='2'>暂无未激活的章节信息!</td></tr>");
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
}

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
				refresh();
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
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
				refresh();
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
		});
	});
}

/**
 * 刷新
 */
function refresh(){
	loadChapter();
	activeChapter();
	inactiveChapter();
}