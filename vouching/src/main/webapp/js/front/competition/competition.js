$(function() {
	
	$("#match").bind("click",function(){
		
		//加入匹配队列
		var url = "/vouching/competition/joinCompetition";
		var data = {
			token : $("#token").val()
		};
		var successCallback = function(data){
			layer.open({
				  type: 1 ,
				  area: ['430px', '250px'] ,
				  shade: 0.6 ,
				  anim: 3 ,
				  title : '匹配中,请等待...' ,
				  content: '<div style="padding:10px;"><div style="padding:10px;"><table><tbody><tr id="competitionQueue"></tr></tbody></table></div><div style="text-align: center;"><h5>匹配中....</h5></div></div>',
				  success : function(){
					  $(this).everyTime('1s','validateMatchQuery',function(){
						  var url = "/vouching/competition/matching";
						  var data = {
							  token : $("#token").val()
						  };
						  var successCallback = function(data){
							  var users = data.detail.users;
							  for (var i = 0 ; i < users.length ; i++) {
								  if ($("#"+users[i].userId).attr("id") == undefined) {
									  $("#competitionQueue").append("<td id='" +users[i].userId+ "'></td>");
									  var td = $("#competitionQueue>td").eq(i);
									  td.append("<img src='/vouching/images/pic_user1.jpg' width='70px' height='60px'/>");
									  td.append("<h3 style='text-align: center;'>" +users[i].name+ "</h3>");
								  } 
							  }
							  if (users.length == 5) {
								  $(this).stopTime("validateMatchQuery");
								  $(this).oneTime('1s',function(){//匹配成功后，1s后跳转
									  window.parent.menu.location.href = "/vouching/forward/forwardCompetitionExam?token="+$("#token").val();
									});
								  }
						  };
						  var faildCallback = function(data){
							  //nope
						  }
						  VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback, null);
						},0,true);
				  }
				});
		};
		VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
		
	});

	// 装载竞技排行榜
	var url = "/vouching/competition/loadRank";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var ranks = data.detail.ranks;
		for (var i = 0; i < ranks.length; i++) {
			$("#rankList").append("<tr></tr>");
			var tr = $("#rankList").children().eq(i);
			tr.append("<td class='bgbai'>"+ranks[i].name+"</td>");
			tr.append("<td class='bgbai'>"+ranks[i].credit+"</td>");
			tr.append("<td class='bgbai'>"+ranks[i].isOnlineName+"</td>");
		}
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, null);

});
