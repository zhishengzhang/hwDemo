<?xml version="1.0" encoding="UTF-8" ?>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Second Admin page</title>
</head>
<body>
<h1>Second Admin page</h1>
<p>
<a href="<c:url value="/logout" />" >Logout</a> <br/>
<a href="${pageContext.request.contextPath}/index.html">Home page</a><br/>
<c:forEach items="${users}" var="user">
${user.id}
</c:forEach>
</p>
</body>
</html>