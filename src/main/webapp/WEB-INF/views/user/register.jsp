<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../commons/resources.jsp"></jsp:include>
	<script type="text/javascript" src="${contextPath }/js/user/register.js"></script>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<div id="main" class="container" style="max-width: 600px; margin: 0px auto;">
   		<div class="modal-header">
       		<h4 class="modal-title" id="myModalLabel">邮箱注册</h4>
   		</div>
   		<form id="registerForm" name="registerForm" method="post" class="form-horizontal" action="${contextPath }/user/saveRegister">
      		<div class="modal-body">
	      		<div class="form-group">
					<label for="userAccount" class="col-sm-2 control-label">邮箱</label>
					<div class="col-sm-10">
						<input id="userAccount" name="userAccount" type="text" class="form-control" placeholder="邮箱" data-widget="autocomplete" data-items="8" data-suggestions='["@qq.com","@126.com","@163.com","@hotmail.com","@sina.com","@139.com","@yeah.net"]' />
					</div>
				</div>
				<div class="form-group">
					<label for="userPassword" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input id="userPassword" name="userPassword" type="password" class="form-control" placeholder="密码" />
					</div>
				</div>
				<div class="form-group">
					<label for="userPasswordAgain" class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-10">
						<input id="userPasswordAgain" name="userPasswordAgain" type="password" class="form-control" placeholder="密码" />
					</div>
				</div>
				<div class="form-group">
					<label for="vCode_" class="col-sm-2 control-label">验证码</label>
					<div class="col-sm-10">
						<input name="vCode_" style="width: 100px; display: inline; padding: 6px 12px;" type="text" class="form-control" id="vCode_" placeholder="验证码" maxlength="5" />
						<img src="${contextPath }/vcode/image?_=<%=new Date().getTime() %>" class="code-img" id="vCode_image" title="点击图片刷新验证码">
					</div>
				</div>
				<div id="bottomError" class="alert alert-danger" role="alert" style="display: none;">
				  	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  	<span class="sr-only">Error:</span>
				  	<label id="bottomErrorLabel"></label>
				</div>
				<div class="modal-footer">
	        		<button type="submit" class="btn btn-primary" data-loading-text="提交中...">提交</button>
	        		<button type="button" class="btn btn-default" onclick="javascript:window.location.href='${contextPath }/user/login'">登录>></button>
	      		</div>
      		</div>
      	</form>
	</div>
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>