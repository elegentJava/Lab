var tree = new dTree("tree");

$(function(){
	
	//加载类型树
	var url = "/vouching/resource/loadCategoryTree";
	var data = {
		token : $("#token").val()
	};
	var successCallback = function(data){
		var categories = data.detail.categories;
		tree.add(0, -1, "生成函电","");
		for (var i = 0; i < categories.length; i++) {
			tree.add(categories[i].ccid, categories[i].fatherId, categories[i].name, categories[i].flag);
		} 
		document.getElementById("dtree").innerHTML=tree;
	};
	VCUtils.common.ajax.commonAjax(url, true, data, successCallback, null, null);
	
	/**
	 * 发送函电
	 */
	sendHD();
	
});

/**
 * 发送函电
 */
function sendHD(){
	$("#sendHD").unbind("click");
	$("#sendHD").bind("click",function(){
		var token = $("#token");
		var content = "";
		layer.open({
			  type: 1 ,
			  area: ['430px', '250px'] ,
			  shade: 0.6 ,
			  anim: 3 ,
			  closeBtn: 1,
			  title : '发送函电' ,
			  content: '<div style="padding:10px;"><div style="padding:10px;"><table><tbody><tr><td>收件人：</td><td><input id="receiver"/></td></tr><tr><td>主题：</td><td><input id="subject"/></td></tr><tr><td>内容：</td><td><textarea rows="3" cols="30">这里是文本域中的文本 ... ... ... ...</textarea></td></tr><tr><td> </td><td><button id="send">发送</button></td></tr></tbody></table></div></div>' ,
			  success : function(){
				  $("#send").unbind("click");
				  $("#send").bind("click",function(){
					  var reveiver = $("#receiver");
					  var subject = $("#subject");
					  var content = $("#content");
					  var url = "/vouching/resource/sendHD";
					  var token = $("#token");
					  var data = {
						  token : token,
						  reveiver : reveiver,
						  subject : subject,
					      content : content
					  };
					  var successCallback = function(data){
							
				     };
					 VCUtils.common.ajax.commonAjax(url, false, data, successCallback);
			    });
			  }
		});
	});
	
}