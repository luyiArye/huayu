<%@page import="com.huayu.core.exception.ApplicationException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../commons/resources.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<div id="main" class="container">
		<%
			ApplicationException exception=(ApplicationException)request.getAttribute("exception");
		%>
		
		<%=exception.getExCode() %>
		<BR/>
		<%=exception.getExDesc() %>
		<BR/>
		<%=exception.getExStack() %>
	</div>
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>