<%@page import="java.util.Map"%>
<%@page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>403</title>
</head>
<body>
	<div style="text-align: center;">
		<h1>贝多多提醒您：亲，您没有权限访问该页面!</h1>
		<h2>您可以尝试访问我们的<a href="http://www.bblink.cn">官网</a></h2>
		<br>
		<p><img alt="友情提醒" src="<%=request.getContextPath()%>/img/friend.jpg"></p>
	</div>
</body>
</html>