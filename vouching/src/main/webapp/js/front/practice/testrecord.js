$(function(){
	
	$("#pager").hide();
	loadQueryResult(1);

});

/**
 * 装载查询结果
 * 
 * @param pageNum
 */
function loadQueryResult(pageNum){
	var loading = layer.load();
	var url = "/vouching/practice/loadTestRecord";
	var data = {
		pageNum : pageNum,
		token : $("#token").val()
	};
	var successCallback = function(data){
		var records = data.detail.records;
		var tbody = $("#recordList");
		tbody.children().remove();
		if (records.length != 0) {
			for (var i = 0; i < records.length; i++) {
				tbody.append("<tr class='teatbbai' align='center'></tr>");
				var tr = tbody.children().eq(i);
				tr.append("<td align='center'>"+eval(records[i]).chapterName+"</td>");
				tr.append("<td align='center'>"+eval(records[i]).formatDate+"</td>");
				tr.append("<td align='center'>"+eval(records[i]).score+"</td>");
			}
			VCUtils.common.pager.front.loadPage(data);
			$("#pager").show();
		} 
		VCUtils.common.pager.front.registerEvent(loadQueryResult);
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}