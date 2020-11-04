<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	<c:if test="${logStatus==null || logStatus=='N'}">
		<a href="/webDbcp/login">로그인</a>
	</c:if>
	<c:if test="${logStatus!=null && logStatus=='Y' }">
		${username }<a href="/webDbcp/logout">로그아웃</a>
	</c:if>
	<a href="/webDbcp/boardList">게시판</a>
</h1>

</body>
</html>
