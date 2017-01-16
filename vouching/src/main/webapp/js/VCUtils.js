var VCUtils = {
	
	//数据模块
	datas : {
		errorUrl : "/vouching/common/forwardError",
		successFlag : "success",
		tempSumPage : 0
	},
	
	//通用模块
	common : {
		
		//提示信息
		tip : {
			errorAlert : function(info){
				layer.alert(info, {icon: 5,});
			}
		},
		
		//分页处理
		pager : {
			
			back : {
				//装载初始化分页
				loadPage :	function(data){
					var page = data.page;
					$("#total").text(page.total);
					$("#pageSum").text(page.pageSum);
					$("#pageNum").text(page.pageNum);
				},
				//上一页点击事件
				prePage : function(loadTable){
					$("#prePage").unbind("click");
					$("#prePage").bind("click",function(){
						var pageNum = parseInt($("#pageNum").text()) - 1;
						if(pageNum > 0 ){
							loadTable(pageNum);
						}
					});
				},
				//下一页点击事件
				nextPage : function(loadTable){
					$("#nextPage").unbind("click");
					$("#nextPage").bind("click",function(){
						var pageNum = parseInt($("#pageNum").text()) + 1;
						var pageSum = parseInt($("#pageSum").text());
						if(pageNum <= pageSum){
							loadTable(pageNum);
						}
					});
				},
				//首页点击事件
				headPage : function(loadTable){
					$("#headPage").unbind("click");
					$("#headPage").bind("click",function(){
						var pageNum = parseInt($("#pageNum").text());
						if(pageNum != 1 ){
							loadTable(1);
						}
					});
				},
				//尾页点击事件
				tailPage : function(loadTable){
					$("#tailPage").unbind("click");
					$("#tailPage").bind("click",function(){
						var pageNum = parseInt($("#pageNum").text());
						var pageSum = parseInt($("#pageSum").text());
						if(pageNum < pageSum){
							loadTable(pageSum);
						}
					});
				},
				registerEvent : function(loadTable){
					VCUtils.common.pager.back.prePage(loadTable);
					VCUtils.common.pager.back.nextPage(loadTable);
					VCUtils.common.pager.back.headPage(loadTable);
					VCUtils.common.pager.back.tailPage(loadTable);
				},
			},
			
			front : {
				//装载初始化分页
				loadPage :	function(data){
					var page = data.page;
					$("#total").text(page.total);
					$("#pageSum").text(page.pageSum);
					$("#pageNum").text(page.pageNum);
					$("#prePage").show();
					$("#nextPage").show();
					if(page.pageNum == 1){
						$("#prePage").hide();
					}
					if(page.pageSum == 1){
						$("#prePage").hide();
						$("#nextPage").hide();
					}
					if(!page.hasNextPage){
						$("#nextPage").hide();
					}
				},
				//上一页点击事件
				prePage : function(loadTable){
					$("#prePage").unbind("click");
					$("#prePage").bind("click",function(){
						var pageNum = parseInt($("#pageNum").text()) - 1;
						loadTable(pageNum);
					});
				},
				//下一页点击事件
				nextPage : function(loadTable){
					$("#nextPage").unbind("click");
					$("#nextPage").bind("click",function(){
						var pageNum = parseInt($("#pageNum").text()) + 1;
						loadTable(pageNum);
					});
				},
				registerEvent : function(loadTable){
					VCUtils.common.pager.front.prePage(loadTable);
					VCUtils.common.pager.front.nextPage(loadTable);
				},
				
				//装载初始化分页
				loadPage1 :	function(data){
					var page = data.page;
					$("#total1").text(page.total);
					$("#pageSum1").text(page.pageSum);
					$("#pageNum1").text(page.pageNum);
					$("#prePage1").show();
					$("#nextPage1").show();
					if(page.pageNum == 1){
						$("#prePage1").hide();
					}
					if(page.pageSum == 1){
						$("#prePage1").hide();
						$("#nextPage1").hide();
					}
					if(!page.hasNextPage){
						$("#nextPage1").hide();
					}
				},
				//上一页点击事件
				prePage1 : function(loadTable){
					$("#prePage1").unbind("click");
					$("#prePage1").bind("click",function(){
						var pageNum = parseInt($("#pageNum1").text()) - 1;
						loadTable(pageNum);
					});
				},
				//下一页点击事件
				nextPage1 : function(loadTable){
					$("#nextPage1").unbind("click");
					$("#nextPage1").bind("click",function(){
						var pageNum = parseInt($("#pageNum1").text()) + 1;
						loadTable(pageNum);
					});
				},
				registerEvent1 : function(loadTable){
					VCUtils.common.pager.front.prePage1(loadTable);
					VCUtils.common.pager.front.nextPage1(loadTable);
				}
			},
		},
		
		//AJAX操作
		ajax : {
			commonAjax : function(url,async,data,successCallback,faildCallback, loading){
				var formatData = {params : data};
				if (data == null) {
					var temp = { unused : "" };
					formatData = { params : temp };
				}
				$.ajax({
					type : "post",
					url : url,
					async: async,
					dataType:'json',
					contentType:'application/json',
					data : JSON.stringify(formatData),
					success : function(data){
						if (VCUtils.datas.successFlag == data.result) {
							if (successCallback != null) {
								successCallback(data);
							}
						} else {
							if (faildCallback != null) {
								faildCallback(data);
							} else {
								if(loading != null){
									layer.close(loading);
								}
								layer.alert(data.errorCode, {icon: 5,});
							}
						}
					},
					error :function(data){
						VCUtils.common.util.simpleHref(VCUtils.datas.errorUrl);
					}
				});
			}
		},
		
		//简单的工具类
		util : {

			isNotNullOrBlank : function(val){
				if (val == null || $.trim(val) == "") {
					return false;
				}
					return true;
			},
			
			simpleHref : function(url){
				window.location.href = url;
			},
			
			selectAll : function(name){
				//全选事件
				$("#checkall").click(function() {
					$("input[name='"+name+"']").each(function() {
						if (this.checked) {
							this.checked = false;
						} else {
							this.checked = true;
						}
					});
				});
			}
			
		}
	},
	
};