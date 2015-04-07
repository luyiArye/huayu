<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../commons/resources.jsp"></jsp:include>
	<script type="text/javascript" src="${contextPath }/js/user/forgetPwd.js"></script>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<div id="main" class="container" style="max-width: 600px; margin: 0px auto;">
   		<div class="modal-header">
   			<div class="modal-header">
       			<h4 class="modal-title" id="myModalLabel">找回密码</h4>
       		</div>
       		<form id="forgetPwdForm" name="forgetPwdForm" method="post" class="form-horizontal" action="${contextPath }/forgetPwd/submit">
       			<div class="modal-body">
		      		<div class="form-group">
						<label for="userAccount" class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-10">
							<input id="userAccount" name="userAccount" type="text" class="form-control" placeholder="邮箱" data-widget="autocomplete" data-items="8" data-suggestions='["@qq.com","@126.com","@163.com","@hotmail.com","@sina.com","@139.com","@yeah.net"]' />
						</div>
					</div>
					<div class="form-group">
						<label for="vCode_" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-10">
							<input name="vCode_" style="width: 100px; display: inline; padding: 6px 12px;" type="text" class="form-control" id="vCode_" placeholder="验证码" maxlength="5" />
							<img src="${contextPath }/vcode/image?_=<%=new Date().getTime() %>" class="code-img" id="vCode_image" title="点击图片刷新验证码">
						</div>
					</div>
					<div class="modal-footer">
		        		<button type="submit" class="btn btn-primary" data-loading-text="验证邮件发送中...">发送验证邮件</button>
		      		</div>
				</div>
       		</form>
   		</div>
   	</div>
   	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>