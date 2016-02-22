<?xml version="1.0" encoding="UTF-8" ?>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login page</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
<h1>Login page</h1>

<p>
<c:if test="${error == true}">
	<b class="error">Invalid login or password.</b>
</c:if>
</p>

<form method="post" action="<c:url value='/login'/>" >
<table>
<tbody>
<tr>
<td>Login:</td>
<td><input type="text" name="username" id="username" size="30" maxlength="40"  /></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="password" name="password" id="password" size="30" maxlength="32" /></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Login" /></td>
</tr>
</tbody>
</table>
</form>	

<p>
<a href="${pageContext.request.contextPath}/index.html">Home page</a><br/>
</p>
</body>
</html>