<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.demo.blog.Blog" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jstl test</title>
</head>
<body>
两种方式输出变量如下：
<br/>
<c:forEach items="${blogList}" var="blog">
	${blog.title}
	<br>
</c:forEach>
---------------------------------
<br/>
<c:forEach items="${blogList}" var="blog">
    <c:out value="${blog.title}"/>
	<br>
</c:forEach>
</body>
</html>

