$(function(){
	//点击图片刷新验证码
	$("#vCode_image").unbind("click").bind("click", function(){
		var date=new Date();
		$(this).attr("src", contextPath_+"/vcode/refresh?_="+date.getTime());
	});
});