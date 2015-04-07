<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../commons/resources.jsp"></jsp:include>
	<script type="text/javascript" src="${contextPath }/js/user/login.js"></script>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<input type="hidden" id="_redirectUrl" value="${_redirectUrl }" />
	<input type="hidden" id="_ofcHidden" value="${_ofc }" />
	<div id="main" class="container" style="max-width: 600px; margin: 0px auto;">
   		<div class="modal-header">
       		<h4 class="modal-title" id="myModalLabel">登录</h4>
   		</div>
   		<form id="loginForm" name="loginForm" method="post" class="form-horizontal" action="${contextPath }/user/checkLogin">
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
						<input id="userPassword" name="userPassword" type="password" class="form-control" placeholder="密码">
					</div>
				</div>
				<div id="vcodeRow" class="form-group" style="display: none;">
					<label for="vCode_" class="col-sm-2 control-label">验证码</label>
					<div class="col-sm-10">
						<input id="vCode_" name="vCode_" style="width: 100px; display: inline; padding: 6px 12px;" type="text" class="form-control" id="vCode_" placeholder="验证码" maxlength="5" />
						<img src="" class="code-img" id="vCode_image" title="点击图片刷新验证码">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
	 					<div class="checkbox">
					        <label>
					         	<input name="rememberMe" type="checkbox" value="1" /> 记住我
					        </label>
					        <label style="float: right;">
					         	<a href="javascript:;" onclick="javascript:window.open('${contextPath }/forgetPwd/page');">忘记密码？</a>
					        </label>
	  					</div>
					</div>
				</div>
				<div class="modal-footer">
	        		<button type="submit" class="btn btn-primary" data-loading-text="登录中...">登录</button>
	        		<button type="button" class="btn btn-default" onclick="javascript:window.location.href='${contextPath }/forgetPwd/page'">注册>></button>
	      		</div>
      		</div>
      	</form>
	</div>
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>