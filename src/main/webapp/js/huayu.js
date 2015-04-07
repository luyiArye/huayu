$(function(){
	$.huayu=$.huayu || {};
	$.huayu.NO_LOGIN_OR_TIME_OUT_EX_CODE="EX_000002";
	
	/** 
	 * 公共ajax 
	 */
	$.huayu.ajax=function(param){
		var paramScuuess=param.success;
		var paramComplete=param.complete;
		
		param.complete=commonComplete;
		param.success=commonSuccess;
		param.dataType=param.dataType || "json";
		
		if(param.formId){
			var formElement=$('#'+param.formId);
			
			var data=formElement.serialize();
			if(param.data){
				var dataStr=param.data;
				if(typeof param.data!="string"){
					dataStr=$.param(param.data);
				}
				
				data+="&"+dataStr;
			}
			param.data=data;
			
			var formAction=formElement.attr("action");
			if(!param.url){
				param.url=formAction;
			}
			
			var formMethod=formElement.attr("method") || "GET";
			if(!param.type){
				param.type=formMethod;
			}
		}
		$.ajax(param);
		
		/**
		 * ajax公共调用完成处理
		 */
		function commonComplete(xmlHttpRequest){
			$('#'+param.formId).find(":submit").button('reset');
			
			if(typeof paramComplete=="function"){
				paramComplete(xmlHttpRequest);
			}
		}
		
		/**
		 * ajax调用成功，公共前置处理
		 */
		function commonSuccess(data){
			$.huayu.hideError(param.formId);
			if(data.exCode){
				//如果为未登陆异常，并且定义了未登录回调函数，则不显示公共异常提示
				if(typeof param.noLoginCallback!="function"
					|| data.exCode!=$.huayu.NO_LOGIN_OR_TIME_OUT_EX_CODE){
					if(param.formId){
						$.huayu.showError(param.formId, data.exDesc);
					}
					else{
						$.huayu.alert({
							message: data.exDesc,
							ico: "error"
						});
					}
				}
				
				//操作异常回调
				if(typeof param.exception=="function"){
					param.exception(data);
				}
				
				//登录超时回调
				if(data.exCode==$.huayu.NO_LOGIN_OR_TIME_OUT_EX_CODE
						&& typeof param.noLoginCallback=="function"){
					param.noLoginCallback(data);
				}
				return ;
			}
			
			if(typeof paramScuuess=="function"){
				paramScuuess(data);
			}
		}
	};
	
	/**
	 * 校验登录
	 */
	$.huayu.checkLogin=function(callback){
		$.huayu.ajax({
			url: contextPath_+"/user/checkLogin",
			method: "GET",
			success: function(data){
				if(typeof callback=="function"){
					callback(data.data);
				}
			}
		});
	}
	
	/**
	 * 获取操作失败的次数
	 */
	$.huayu.getOfc=function(callback){
		$.huayu.ajax({
			url: contextPath_+"/getOfc",
			method: "GET",
			success: function(data){
				if(typeof callback=="function"){
					callback(data.data);
				}
			}
		});
	}
	
	/**
	 * 显示form公共错误
	 */
	$.huayu.showError=function(formId, errorMessage){
		if($("#"+formId+"_bottomError").length<=0){
			var bottomErrorDiv='<div id="'+formId+'_bottomError" class="alert alert-danger" role="alert">'+
								  	'<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>'+
								  	'<span class="sr-only">Error:</span>'+
								  	'<a class="close" data-dismiss="alert">×</a>'+
								  	'<label id="'+formId+'_bottomErrorLabel"></label>'+
								'</div>';
			
			$("#"+formId).append(bottomErrorDiv);
		}
		
		$("#"+formId+"_bottomErrorLabel").text(errorMessage);
		$("#"+formId+" :submit").removeAttr("disabled");
		$("#"+formId+"_bottomError").show();
	}
	
	/**
	 * 隐藏form公共错误
	 */
	$.huayu.hideError=function(formId){
		$("#"+formId+"_bottomError").hide();
		$("#"+formId+" :submit").removeAttr("disabled");
	}
	
	$.huayu.WEEK_MAP={
			1: "星期一",
			2: "星期二",
			3: "星期三",
			4: "星期四",
			5: "星期五",
			6: "星期六",
			0: "星期日",
	}
	/**
	 * 日期格式化
	 */
	Date.prototype.format=function(fmt){ 
		var o = {
		    "M+": this.getMonth()+1,                 //月份   
		    "d+": this.getDate(),                    //日   
		    "h+": this.getHours(),                   //小时   
		    "m+": this.getMinutes(),                 //分   
		    "s+": this.getSeconds(),                 //秒   
		    "q+": Math.floor((this.getMonth()+3)/3), //季度   
		    "S" : this.getMilliseconds(),             //毫秒   
		    "W":$.huayu.WEEK_MAP[this.getDay()] || ""                        //星期
		};   
		
		if(/(y+)/.test(fmt)){
			fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		}
		
		for(var k in o){
			if(new RegExp("("+ k +")").test(fmt)){
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
			}
		}
		return fmt;
	}
	
	/**
	 * 公共弹出提示框
	 */
	$.huayu.alert=function(option){
		option=option || {};
		option.title=option.title || "提示";
		
		if($("#_hyModelAlerDiv").length<=0){
			var modelAlerDiv='<div id="_hyModelAlerDiv" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">'+
								'<div class="modal-dialog" style="width: 500px;">'+
							    	'<div class="modal-content">'+
										'<div class="modal-header">'+
								          	'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>'+
								          	'<h4 class="modal-title" id="mySmallModalLabel">'+option.title+'</h4>'+
								        '</div>'+
								        '<div class="modal-body">';
								        	if(option.ico){
								        		modelAlerDiv+='<div class="dialogIcoDiv '+option.ico+'_ico"></div>';
								        	}
								        	
								        	modelAlerDiv+='<div id="_messageLabel"></div>'+
								        '</div>'+
								        '<div class="modal-footer">'+
								        	'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'+
								        '</div>'+
							    	'</div>'+
								'</div>'+
							'</div>';
			
			$("body").append(modelAlerDiv);
		}
		
		$('#_hyModelAlerDiv').modal("show");
		$("#_messageLabel").text(option.message || "");
	}
});