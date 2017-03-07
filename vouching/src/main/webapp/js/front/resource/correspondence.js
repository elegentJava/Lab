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
		//为每个类型绑定事件
		$("a[href='1']").each(function() {
			$(this).attr("href","javascript:;");
			$(this).unbind("click");
			$(this).bind("click", function() {
				categoryId = $(this).attr("id").slice(5);
				$("#categoryId").attr("value",categoryId);
				loadHD();
			});
		});
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback);
	
	/**
	 * 装载电文和翻译
	 */
	loadHD();
	
	/**
	 * 发送函电
	 */
	sendHD();
	
});

/**
 * 替换语句
 */
function replaceChange(){
	$("#replace>select").each(function(){
		$(this).unbind("change");
		$(this).bind("change",function(){
			var token = $("#token").val();
			var key = $(this).val();
			var url = "/vouching/resource/replaceChange";
			var data = {
				token : token,
				key : key
			};
			var successCallback = function(data){
				var correspondence = data.detail.correspondence;
				$("#english").html(correspondence.english);
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback);
		});
	});
}

/**
 * 装载电文和翻译
 */
function loadHD(){
	var loading = layer.load();
	var url = "/vouching/resource/loadHD";
	var token = $("#token").val();
	var categoryId = $("#categoryId").val();
	var data = {
		token : token,
		categoryId : categoryId
	};
	var successCallback = function(data){
		var correspondence = data.detail.correspondence;
		var translate = data.detail.translate;
		var list = correspondence.list;
		$("#english").html(correspondence.english);
		$("#translate").html(translate);
		$("#replace").children().remove();
		$("#replace").text("");
		for(var i = 0; i<list.length; i++){
			var innerList = list[i];
			$("#replace").append("替换语句"+(i+1)+":       <select style='width:40%'></select><br/>");
			var obj = $("#replace>select").eq(i);
			for(var j=0;j<innerList.length;j++){
				$(obj).append("<option value='"+i+"-"+j+"'>"+innerList[j]+"</option>");
			}
		}
		replaceChange();
		layer.close(loading);
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}


/**
 * 发送函电
 */
function sendHD(){
	$("#sendHD").unbind("click");
	$("#sendHD").bind("click",function(){
		var categoryId = $("#categoryId").val();
		var subject = $("#stree"+categoryId).text();
		var content = $("#english").html();
		layer.open({
			  type: 1 ,
			  area: ['430px', '250px'] ,
			  shade: 0.6 ,
			  anim: 3 ,
			  closeBtn: 1,
			  title : '发送函电' ,
			  content: '<div style="padding:10px;"><div style="padding:10px;"><table><tbody><tr><td>收件人：</td><td><input id="receiver" value="默认发送给当前用户的邮箱"/></td></tr><tr><td>主题：</td><td><input id="subject" value="'+subject+'"/></td></tr><tr><td>内容：</td><td><textarea id="content" rows="3" cols="30" value="'+content+'"></textarea></td></tr><tr><td> </td><td><button id="send">发送</button></td></tr></tbody></table></div></div>' ,
			  success : function(){
				  $("#send").unbind("click");
				  $("#send").bind("click",function(){
					  var receiver = $("#receiver").val();
					  var subject = $("#subject").val();
					  var content = $("#content").val();
					  var url = "/vouching/resource/sendHD";
					  var token = $("#token").val();
					  var data = {
						  token : token,
						  receiver : receiver,
						  subject : subject,
					      content : content
					  };
					  var successCallback = function(data){
						  	layer.closeAll("page");
							layer.msg("发送成功!");
				     };
					 VCUtils.common.ajax.commonAjax(url, false, data, successCallback);
			    });
			  }
		});
	});

/**
 * 类型改变事件
 */
function categoryChange(){
	
}
	
}