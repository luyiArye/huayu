<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorJson=request.getAttribute("errorJson_").toString();
	response.setContentType("application/json;charset=UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.getWriter().print(errorJson);
%>