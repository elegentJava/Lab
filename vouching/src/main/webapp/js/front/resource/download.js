$(function(){
	
	//装载下载列表
	var url = "/vouching/resource/loadFilePage";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var downloadList = data.detail.resources;
		$("#downloadList").children().remove();
		for (var i = 0; i < downloadList.length; i++) {
			$("#downloadList").append("<tr><td class='teatbbai' align='center'>" +downloadList[i].name + "</td><td class='teatbbai' align='center'><a href='" +downloadList[i].href+ "'>下载</a></td></tr>");
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
	
});