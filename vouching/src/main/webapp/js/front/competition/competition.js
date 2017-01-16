$(function() {
	
	$("#match").bind("click",function(){
		layer.open({
			  type: 1 //Page层类型
			  ,area: ['430px', '300px']
			  ,shade: 0.6 //遮罩透明度
			  ,anim: 3 //0-6的动画形式，-1不开启
			  ,title : '匹配中,请等待...'
			  ,content: '<div style="padding:10px;"><div style="padding:10px;"><table><tbody><tr><td><img src="/vouching/images/pic_user1.jpg" alt="" width="70px" height="60px"/><h3 style="text-align: center;">User1</h3></td><td><img src="/vouching/images/pic_user1.jpg" alt="" width="70px" height="60px"/><h3 style="text-align: center;">User1</h3></td><td><img src="/vouching/images/pic_user1.jpg" alt="" width="70px" height="60px"/><h3 style="text-align: center;">User1</h3></td><td><img src="/vouching/images/pic_user1.jpg" alt="" width="70px" height="60px"/><h3 style="text-align: center;">User1</h3></td><td><img src="/vouching/images/pic_user1.jpg" alt="" width="70px" height="60px"/><h3 style="text-align: center;">User1</h3></td></tr></tbody></table></div><div style="text-align: center;"><h3>匹配中....</h3></div></div>'
			  ,success : function(){
				  $(this).everyTime('1s','validateMatchQuery',function(){
					  var url = "/vouching/competition/matching";
					  var data = {
						  token : $("#token").val()
					  };
					  var successCallback = function(data){
						  var users = data.queue;
						  
					  };
					  VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null);
					},0,true);
			  }
			});
	});

	// 装载竞技排行榜
	var url = "/vouching/competition/loadRank";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var ranks = data.detail.ranks;
		$("#rankList").append("<tr></tr>");
		for (var i = 0; i < ranks.length; i++) {
			var tr = $("#rankList").children().eq(i);
			tr.append("<td class='bgbai'>"+ranks[i].name+"</td>");
			tr.append("<td class='bgbai'>"+ranks[i].credit+"</td>");
			tr.append("<td class='bgbai'>"+ranks[i].isOnlineName+"</td>");
		}
	};
	var faildCallback = function(data){
		divAlert(data.errorCode);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);

});
