<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	JDBC Template
</h1>

<ul>
	<li>
		<c:if test="${logStatus==null || logStatus!='Y' }">
			<a href="/temp/login">로그인</a>
		</c:if>
		<c:if test="${logStatus!=null && logStatus=='Y' }">
			<a href="/temp/logout">로그아웃</a>
		</c:if>
	</li>
	<li><a href="/temp/boardList">게시판</a></li>
</ul>
</body>
</html>
