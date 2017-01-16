$(function(){
	
	//隐藏分页信息
	$("#pager").hide();
	
	//全选事件
	VCUtils.common.util.selectAll("glossaryId");
	
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
	    type : 2,
	};
	var successCallback = function(data){
		var glossaries = data.detail.resources;
		$("#glossaryList").children().remove();
		if (glossaries != null && glossaries.length > 0) {
			for (var i = 0; i < glossaries.length; i++) {
				$("#glossaryList").append("<tr></tr>");
				var tr = $("#glossaryList").children().eq(i);
				if(glossaries[i].flag == 0){
					tr.append("<td><input type='checkbox' name='glossaryId' value='"+glossaries[i].trid+"' /></td>");
				} else {
					tr.append("<td>  </td>");
				}
				tr.append("<td>"+glossaries[i].user.name+"</td>");
				tr.append("<td>"+glossaries[i].formatDate+"</td>");
				tr.append("<td>"+glossaries[i].flagName+"</td>");
				tr.append("<td><div class='button-group'></div><td>");
				if(glossaries[i].flag == 0){
					$(tr).find(">td>div").append("<button glossaryId='"+glossaries[i].trid+"' class='button border-red' name='deal'><span class='con-info-circle'></span>处理</button>");
				}
			}
			VCUtils.common.pager.back.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.back.registerEvent(loadData);
		} else {
			$("#pager").hide();
			$("#glossaryList").append("<tr><td colspan='4'>暂无未处理的章节信息！</td></tr>");
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
		trid = $(this).attr("glossaryId");
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
				var length = $("#glossaryList>tr").length;
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