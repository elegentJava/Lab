$(function(){
	
	//隐藏分页标签
	$("#pager").hide();
	
	//装载词汇来源类型
	var url = "/vouching/resource/loadGlossarySource";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var glossarySources = data.detail.glossarySources;
		for (var i = 0; i < glossarySources.length; i++) {
			$("#glossarySources").append("<input type='checkbox' name='source' value='"+glossarySources[i].id+"' />"+glossarySources[i].description+"<br/>");
		}
	};
	VCUtils.common.ajax.commonAjax(url, true, data, successCallback, null, null);

	//词汇查询
	$("#query").bind("click",function(){
		loadQueryResult(1);
	});
});

/**
 * 装载查询结果
 * 
 * @param pageNum
 */
function loadQueryResult(pageNum){
	var loading = layer.load();
	var word = $("#word").val();
	var sourceBox = $("input[name='source']:checked");
	var sources = new Array();
	for (var i = 0; i < sourceBox.length; i++) {
		sources[i] = $(sourceBox[i]).val();
	}
	var url = "/vouching/resource/queryGlossary";
	var data = {
		word : word,
		sources : sources,
		pageNum : pageNum,
		token : $("#token").val()
	};
	var successCallback = function(data){
		var glossaries = data.detail.glossaries;
		var tbody = $("#glossaryList");
		tbody.children().remove();
		if (glossaries.length != 0) {
			for (var i = 0; i < glossaries.length; i++) {
				tbody.append("<tr class='teatbbai' align='center'></tr>");
				var tr = tbody.children().eq(i);
				tr.append("<td align='center'>"+eval(glossaries[i]).originalWord+"</td>");
				tr.append("<td align='center'>"+eval(glossaries[i]).translate+"</td>");
				tr.append("<td align='center'>"+eval(glossaries[i]).sourceName+"</td>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
		} else {
			$("#pager").hide();
			tbody.append("<tr class='teatbbai'><td align='center' colspan='3'>未筛选到结果......</td></tr>");
		}
		VCUtils.common.pager.front.registerEvent(loadQueryResult);
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}