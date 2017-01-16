var tree = new dTree("tree");

$(function(){
	
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
	
});