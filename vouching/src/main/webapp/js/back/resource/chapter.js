$(function(){
	
	//隐藏分页信息
	$("#pager").hide();
	
	//全选事件
	VCUtils.common.util.selectAll("chapterId");
	
	//初始化加载数据
	loadData(1);
	
	//批量处理事件
	
	//单个处理事件
	singleDeal();
	
});

/**
 * 加载数据
 * 
 * @param pageNum
 */
function loadData(pageNum){
	var loading = layer.load();
	var url = "/vouching/admin/loadResourceList";
	var data = {
	    token : $("#token").val(),
	    pageNum : pageNum,
	    type : 1,
	};
	var successCallback = function(data){
		var chapters = data.detail.resources;
		$("#chapterList").children().remove();
		if (chapters != null && chapters.length > 0) {
			for (var i = 0; i < chapters.length; i++) {
				$("#chapterList").append("<tr></tr>");
				var tr = $("#chapterList").children().eq(i);
				if(chapters[i].flag == 0){
					tr.append("<td><input type='checkbox' name='chapterId' value='"+chapters[i].trid+"' /></td>");
				} else {
					tr.append("<td>  </td>");
				}
				tr.append("<td>"+chapters[i].user.name+"</td>");
				tr.append("<td>"+chapters[i].formatDate+"</td>");
				tr.append("<td>"+chapters[i].flagName+"</td>");
				tr.append("<td><div class='button-group'></div><td>");
				if(chapters[i].flag == 0){
					$(tr).find(">td>div").append("<button chapterId='"+chapters[i].trid+"' class='button border-red' name='deal'><span class='con-info-circle'></span>处理</button>");
				}
			}
			VCUtils.common.pager.back.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.back.registerEvent(loadData);
		} else {
			$("#pager").hide();
			$("#chapterList").append("<tr><td colspan='4'>暂无未处理的章节信息！</td></tr>");
		}
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null);
}

/**
 * 单个处理事件
 */
function singleDeal(){
	$("button[name='deal']").each(function(){
		trid = $(this).attr("chapterId");
		$(this).unbind("click");
		$(this).bind("click",function(){
			var loading = layer.load();
			var url = "/vouching/admin/dealSingleResource";
			var data = {
				token : $("#token").val(),
				trid : trid
			};
			var successCallback = function(data){
				layer.close(loading);
				layer.msg("处理成功!");
				var length = $("#chapterList>tr").length;
				var pageNum = parseInt($("#pageNum").text());
				if(length == 1){
					pageNum = pageNum - 1;
				}
				loadData(pageNum);
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null);
		});
	});
}