<%@page import="com.lonewolf.gasstation.BuildTree"%>
<%@page import="com.system.model.UserModel"%>
<%@page import="java.util.List"%>
<%@page import="com.lonewolf.jcxx.service.GsqUserService"%>
<%@page import="com.system.query.UserQuery"%>
<%@page import="org.springframework.web.context.ContextLoader"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/commonTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<n:nocache />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="<n:base/>/css/tree.css" type="text/css">
<%
String userId = request.getParameter("userId");
if(null==userId||"".equals(userId))
{
	userId="1";
}
	String data = BuildTree.buildTreeStr(userId);
%>
</head>
<body>
	<div class="tree" align="center">
			<%=data %>
	</div>
</body>
</html>
