$(function(){
	
	//初始化隐藏分页
	$("#pager").hide();
	
	//全选事件
	$("#checkall").click(function() {
		$("input[name='userId']").each(function() {
			if (this.checked) {
				this.checked = false;
			} else {
				this.checked = true;
			}
		});
	});
	
	//初始化装载数据
	loadDataForPage(1);
	
	//重置密码事件
	resetPassword();
	
	//删除单个学生
	deleteSingle();
	
	//批量删除
	multiDelete();
	
	//批量导入
	$("#batchInsert").unbind("click");
	$("#batchInsert").bind("click",function(){
		var token = $("#token").val();
		$.ajaxFileUpload({
            url:'/vouching/admin/batchInsertUsers',
            secureuri:false,                       
            fileElementId : "userFile",
            data : {
            	token : token,
            	role : 2
            },
            dataType:'json',                       
            success:function(data, status){        
            	if (data.result == "success") {
            		layer.msg("批量添加成功!");
				} else {
					layer.alert(data.errorCode, {icon: 5,});
				}
            },
            error:function(data, status, e){ 
            	layer.alert(data.errorCode, {icon: 5,});
            }
        });
	});
	
});

/**
 * 分页装载结果
 * @param pageNum
 */
function loadDataForPage(pageNum){
	var loading = layer.load();
	var url = "/vouching/admin/loadUserList";
	var data = {
	    token : $("#token").val(),
	    role : 2,
	    pageNum : pageNum
	};
	var successCallback = function(data){
		var teachers = data.detail.users;
		$("#teacherList").children().remove();
		if (teachers != null && teachers.length > 0) {
			for (var i = 0; i < teachers.length; i++) {
				$("#teacherList").append("<tr></tr>");
				var tr = $("#teacherList").children().eq(i);
				tr.append("<td><input type='checkbox' name='userId' value='"+teachers[i].userId+"' /></td>");
				tr.append("<td>"+teachers[i].account+"</td>");
				tr.append("<td>"+teachers[i].name+"</td>");
				tr.append("<td>"+teachers[i].sexName+"</td>");
				tr.append("<td>"+teachers[i].email+"</td>");
				tr.append("<td>"+teachers[i].isOnlineName+"</td>");
				tr.append("<td>"+teachers[i].formatLastLoginDate+"</td>");
				tr.append("<td>"+teachers[i].className+"</td>");
				tr.append("<div class='button-group'></div>");
				var buttons = $(tr).find(">div");
				buttons.append("<button userId='"+teachers[i].userId+"' class='button border-main' name='resetPassword'><span class='icon-edit'></span>重置密码</button>");
				buttons.append("<button userId='"+teachers[i].userId+"' class='button border-red' name='deleteSingle'><span class='icon-trash-o'></span>删除</button>");
			}
			VCUtils.common.pager.back.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.back.registerEvent(loadDataForPage);
			resetPassword();
			deleteSingle();
		} else {
			$("#teacherList").append("<tr><td colspan=‘8’>暂无教师信息!</td></tr>");
			$("#pager").hide();
		}
		layer.close(loading);
	};
	var faildCallback = function(data){
		layer.close(loading);
		layer.alert("数据加载失败!", {icon: 5,});
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
}

/**
 * 重置密码
 */
function resetPassword(){
	$("button[name='resetPassword']").unbind("click");
	$("button[name='resetPassword']").bind("click",function(){
		var userId = $(this).attr("userId");
		var url = "/vouching/admin/resetPassword";
		var data = {
		    token : $("#token").val(),
		    userId : userId
		};
		var successCallback = function(data){
			layer.msg("重置成功!");
		};
		VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null);
	});
}

/**
 * 删除单个教师
 */
function deleteSingle(){
	$("button[name='deleteSingle']").unbind("click");
	$("button[name='deleteSingle']").bind("click",function(){
		var userId = $(this).attr("userId");
		var url = "/vouching/admin/deleteSinglerUser";
		var data = {
		    token : $("#token").val(),
		    userId : userId
		};
		var successCallback = function(data){
			layer.msg("删除成功!");
			var length = $("#teacherList>tr").length;
			var pageNum = parseInt($("#pageNum").text());
			if(length == 1){
				pageNum = pageNum - 1;
			}
			loadDataForPage(pageNum);
		};
		VCUtils.common.ajax.commonAjax(url, false, data, successCallback, null);
	});
}

/**
 * 批量删除
 */
function multiDelete() {
	$("#multiDelete").unbind("click");
	$("#multiDelete").bind("click",function(){
		var userIds = new Array();
		var checkedObj = $("input[name='userId']:checked");
		if (checkedObj.length > 0) {
			for (var i = 0; i < checkedObj.length; i++) {
				userIds[i] = $(checkedObj[i]).attr("value");
			}
			var url="/vouching/admin/batchDeleteUsers";
			var data = {
				token : $("#token").val(),
				userIds : userIds
			};
			var successCallback = function(data){
				layer.msg("删除成功!");
				loadDataForPage(1);	
			};
			VCUtils.common.ajax.commonAjax(url,false,data,successCallback,null);
		} else {
			layer.alert("请选择一个再删除!", {icon: 5,});
		}
	});
}
