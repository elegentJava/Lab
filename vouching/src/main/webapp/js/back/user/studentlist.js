$(function(){
	
	//初始化隐藏分页
	$("#pager").hide();
	
	//全选事件
	VCUtils.common.util.selectBAll("userId");
	
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
            	role : 1
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
	    role : 1,
	    pageNum : pageNum
	};
	var successCallback = function(data){
		var students = data.detail.users;
		$("#studentList").children().remove();
		if (students != null && students.length > 0) {
			for (var i = 0; i < students.length; i++) {
				$("#studentList").append("<tr></tr>");
				var tr = $("#studentList").children().eq(i);
				tr.append("<td><input type='checkbox' name='userId' value='"+students[i].userId+"' /></td>");
				tr.append("<td>"+students[i].account+"</td>");
				tr.append("<td>"+students[i].name+"</td>");
				tr.append("<td>"+students[i].sexName+"</td>");
				tr.append("<td>"+students[i].email+"</td>");
				tr.append("<td>"+students[i].isOnlineName+"</td>");
				tr.append("<td>"+students[i].formatLastLoginDate+"</td>");
				tr.append("<td>"+students[i].className+"</td>");
				tr.append("<div class='button-group'></div>");
				var buttons = $(tr).find(">div");
				buttons.append("<button userId='"+students[i].userId+"' class='button border-main' name='resetPassword'><span class='icon-edit'></span>重置密码</button>");
				buttons.append("<button userId='"+students[i].userId+"' class='button border-red' name='deleteSingle'><span class='icon-trash-o'></span>删除</button>");
			}
			VCUtils.common.pager.back.loadPage(data);
			$("#pager").show();
			VCUtils.common.pager.back.registerEvent(loadDataForPage);
			resetPassword();
			deleteSingle();
		} else {
			$("#studentList").append("<tr><td colspan=‘8’>暂无学生信息!</td></tr>");
			$("#pager").hide();
		}
		layer.close(loading);
	};
	var faildCallback = function(data){
		layer.close(loading);
		VCUtils.common.tip.errorAlert("数据加载失败!");
	};
	VCUtils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback.loading);
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
 * 删除单个学生
 */
function deleteSingle(){
	$("button[name='deleteSingle']").unbind("click");
	$("button[name='deleteSingle']").bind("click",function(){
		var userId = $(this).attr("userId");
		VCUtils.common.tip.confirm("确定要删除吗？", function(){
			var url = "/vouching/admin/deleteSinglerUser";
			var data = {
			    token : $("#token").val(),
			    userId : userId
			};
			var successCallback = function(data){
				layer.msg("删除成功!");
				var length = $("#studentList>tr").length;
				var pageNum = parseInt($("#pageNum").text());
				if(length == 1){
					pageNum = pageNum - 1;
				}
				loadDataForPage(pageNum);
			};
			VCUtils.common.ajax.commonAjax(url, false, data, successCallback);
		});
	});
}

/**
 * 批量删除
 */
function multiDelete() {
	$("#multiDelete").unbind("click");
	$("#multiDelete").bind("click",function(){
		VCUtils.common.tip.confirm("确定要删除吗？", function(){
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
				VCUtils.common.ajax.commonAjax(url,false,data,successCallback);
			} else {
				VCUtils.common.tip.errorAlert("请选择一个再删除!");
			}
		});
	});
}
