$(function(){
	
	//装载下载列表
	var token = $("#token").val();
	var url = "/vouching/resource/loadFilePage";
	var data = {
		token : token
	};
	var successCallback = function(data){
		var uploadList = data.detail.resources;
		$("#uploadList").children().remove();
		for (var i = 0; i < uploadList.length; i++) {
			$("#uploadList").append("<h3>上传" +uploadList[i].name+ "文件</h3>");
			$("#uploadList").append("<div></div>");
			var form = $("#uploadList>div").eq(i);
			form.append("上传文件：");
			form.append("<input type='file' id='" +uploadList[i].id+ "' name='uploadFile'><br/>");
			form.append("<input name='upload' temp='" +uploadList[i].id+ "' type='button' value='马上上传'>");
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
	
	//绑定上传事件
	$("input[name='upload']").each(function(){
		$(this).bind("click",function(){
			var id = $(this).attr("temp");
			var token = $("#token").val();
			$.ajaxFileUpload({
                url:'/vouching/resource/uploadResource',
                secureuri:false,                       
                fileElementId : id,
                data : {
                	token : token,
                	type : id
                },
                dataType:'json',                       
                success:function(data, status){        
                	if (data.result == "success") {
						layer.msg("文件上传成功!");
					} else {
						VCUtils.common.tip.errorAlert(data.errorCode);
					}
                },
                error:function(data, status, e){ 
                	VCUtils.common.tip.errorAlert(data.errorCode);
                }
            });
		});
	});
	
});