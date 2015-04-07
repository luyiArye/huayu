$(function(){
	$('#registerForm').bootstrapValidator({
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
    	 				message: '邮箱不能为空.'
    	 			},
    	 			stringLength: {
                        min: 6,
                        max: 30,
                        message: '邮箱长度必须介于6到30之间.'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.@]+$/,
                        message: '邮箱只能包含字母、数字、下划线、点.'
                    },
                    remote: {
                        url: contextPath_+'/user/checkUserAccount',
                        message: '该邮箱已经注册.'
                    },
                    emailAddress: {
                    	message: '邮箱格式错误.'
                    }
    	 		}
    	 	},
    	 	userPassword: {
    	 		message: '密码错误.',
    	 		validators: {
    	 			notEmpty: {
    	 				message: '密码不能为空.'
    	 			},
    	 			stringLength: {
                        min: 6,
                        max: 18,
                        message: '密码长度必须介于6到18之间.'
                    }/*,
                    identical: {
                    	field: "userPasswordAgain",
                    	message: "两次密码输入不一致."
                    }*/
    	 		}
    	 	},
    	 	userPasswordAgain: {
    	 		message: '确认密码错误.',
    	 		validators: {
    	 			notEmpty: {
    	 				message: '确认密码错误不能为空.'
    	 			},
    	 			stringLength: {
                        min: 6,
                        max: 18,
                        message: '确认密码长度必须介于6到18之间.'
                    },
                    identical: {
                    	field: "userPassword",
                    	message: "两次密码输入不一致."
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
		return true;
	});
	
	//点击图片刷新验证码
	$("#vCode_image").unbind("click").bind("click", function(){
		var date=new Date();
		$(this).attr("src", contextPath_+"/vcode/refresh?_="+date.getTime());
	});
});