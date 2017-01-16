$(function(){
	
	//隐藏错误信息
	$("#errorMsg").hide();
	
	//添加班级信息
	$("#add").bind("click",function(){
		$("#errorMsg").hide();
		var className = $("#className").val();
		var bak = $("#bak").val();
		var isActive = $("input[name='status']:checked").val();
		if(VCUtils.common.util.isNotNullOrBlank(className)){
			var url = "/vouching/admin/addClass";
			var data = {
			    token : $("#token").val(),
			    className : className,
			    bak : bak,
			    isActive : isActive,
			};
			var successCallback = function(data){
				layer.msg("添加成功!");
			};
			var faildCallback = function(data){
				$("#errorMsg>div>ul>li").text(data.errorCode);
				$("#errorMsg").show();
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
		}
	});
	
});