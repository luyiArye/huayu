//刷新头部登录信息
function _refreshHeadLoginInfo(loginUser){
	if(loginUser && loginUser.id){
		$("[name='_headNoLoginInfo']").hide();
		$("[name='_headLoginInfo']").show();
		
		$("#_headLoginUserName").text(loginUser.userName);
		$("#_headLoginUserName").attr("title", loginUser.userName);
		$("#_loginUserHeadImage").attr("src", contextPath_+"/user/headIco/1/"+loginUser.id);
		$("#_loginUserHeadImage").attr("title", loginUser.userName);
	}
	else{
		$("[name='_headLoginInfo']").hide();
		$("[name='_headNoLoginInfo']").show();
		
		$("#_headDateSpan").text(new Date().format("yyyy年MM月dd日 W"));
	}
}

function checkOfcModel(ofc){
	if(ofc>=3){
		//操作失败次数达到3次或以上则登录需要验证码
		$("#vcodeRowModel").show();
		var date=new Date();
		$("#vCode_imageModel").attr("src", contextPath_+"/vcode/refresh?_="+date.getTime());
	}
}

//弹出登录框
function _showLoginModel(loginCallback){
	$("#vcodeRowModel").hide();
	//获取session操作失败的次数
	$.huayu.getOfc(function(ofc){
		checkOfcModel(ofc);
	});
	
	$("#loginModelDiv_").modal("show");
	$.huayu.hideError("loginFormModel");
	
	//点击图片刷新验证码
	$("#vCode_imageModel").unbind("click").bind("click", function(){
		var date=new Date();
		$(this).attr("src", contextPath_+"/vcode/refresh?_="+date.getTime());
	});
	
	$('#loginFormModel').bootstrapValidator({
		message: '录入数据存在错误.',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
    	 	userAccount: {
    	 		message: '邮箱错误.',
    	 		validators: {
    	 			notEmpty: {
    	 				message: '登录邮箱不能为空.'
    	 			}
    	 		}
    	 	},
    	 	userPassword: {
    	 		message: '登录密码错误.',
    	 		validators: {
    	 			notEmpty: {
    	 				message: '登录密码不能为空.'
    	 			}
    	 		}
    	 	},
    	 	vCode_: {
    	 		message: '验证码错误.',
    	 		validators: {
    	 			notEmpty: {
    	 				message: '验证码不能为空.'
    	 			},
		            remote: {
		                url: contextPath_+'/vcode/checkVCode',
		                message: '验证码错误或者已失效.'
		            }
    	 		}
    	 	}
      	}
	}).on('success.form.bv', function(e) {
		$(this).find(":submit").button('loading');
		
		$.huayu.ajax({
			type: "POST",
			url: contextPath_+"/user/checkLogin",
			formId: "loginFormModel",
			success: function(data){
				//登录成功
				if(typeof loginCallback=="function"){
					loginCallback(data.data);
				}
				
				//关闭登录窗
				$("#loginModelDiv_").modal("hide");
			},
			exception: function(data){
				checkOfcModel(data.ofc);
			}
		});
		
		return false;
	});
}

var HuayuCategorys_;
$(function(){
	//加载栏目信息
	$.huayu.ajax({
		url: contextPath_+"/category/load",
		type: "GET",
		dataType: "json",
		success: function(data){
			//缓存栏目信息
			HuayuCategorys_=data;
			if(data){
				var dropdownMenu='';
				for(var i=0;i<data.length;i++){
					dropdownMenu+='<li><a href="'+contextPath_+'/production/load/'+data[i].id+'" itemprop="url">'+data[i].categoryName+'</a></li>';
					
					//设置导航条
					if($("#categoryId").val()==data[i].id){
						$("#navigationLable").html('&nbsp;›&nbsp;<a href="'+contextPath_+'/production/load/'+data[i].id+'" itemprop="url">'+data[i].categoryName+'</a>');
					}
				}
				
				$("#huayu_hua_dropdownMenu").html(dropdownMenu);
			}
		}
	});
	
	//校验登录
	$.huayu.checkLogin(_refreshHeadLoginInfo);
	
	//登录模态窗口弹出后清空登录form校验
	$('#loginModelDiv_').on('shown.bs.modal', function (e) {
		$('#loginFormModel').data("bootstrapValidator").resetForm(true);
	});
});