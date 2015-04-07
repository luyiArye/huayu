<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../commons/resources.jsp"></jsp:include>
	<script type="text/javascript" src="${contextPath }/js/user/resetPwd.js"></script>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<div id="main" class="container" style="max-width: 600px; margin: 0px auto;">
   		<div class="modal-header">
   			<div class="modal-header">
       			<h4 class="modal-title" id="myModalLabel">重置密码</h4>
       		</div>
       		<form id="resetPwdForm" name="resetPwdForm" method="post" class="form-horizontal" action="${contextPath }/forgetPwd/resetPwd/submit">
       			<div class="modal-body">
       				<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-10">
							<span style="line-height: 35px;"><c:out value="${user.userAccount }"></c:out></span>
							<input type="hidden" name="userAccount" value="<c:out value='${user.userAccount }'></c:out>" />
							<input type="hidden" name="id" value="<c:out value='${user.id }'></c:out>" />
						</div>
					</div>
		      		<div class="form-group">
						<label for="userPassword" class="col-sm-2 control-label">新密码</label>
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
					<div class="modal-footer">
		        		<button type="submit" class="btn btn-primary" data-loading-text="提交中...">提交</button>
		      		</div>
				</div>
       		</form>
   		</div>
   	</div>
   	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>