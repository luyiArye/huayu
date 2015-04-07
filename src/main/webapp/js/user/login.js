$(function(){
	checkOfc($("#_ofcHidden").val());
	
	$('#loginForm').bootstrapValidator({
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
			formId: "loginForm",
			success: function(data){
				if($("#_redirectUrl").val() && $.trim($("#_redirectUrl").val()).length>0){
					//如果存在跳转url
					window.location.href=$("#_redirectUrl").val();
				}
				else{
					//登录成功
					window.location.href=contextPath_;
				}
			},
			exception: function(data){
				checkOfc(data.ofc);
			}
		});
		
		return false;
	});
});

//检查操作失败的次数
function checkOfc(ofc){
	$("#vcodeRow").hide();
	if(ofc>=3){
		$("#vcodeRow").show();
		var date=new Date();
		$("#vCode_image").attr("src", contextPath_+"/vcode/refresh?_="+date.getTime());
	}
	
	//点击图片刷新验证码
	$("#vCode_image").unbind("click").bind("click", function(){
		var date=new Date();
		$(this).attr("src", contextPath_+"/vcode/refresh?_="+date.getTime());
	});
}