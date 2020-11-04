<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${logStatus==null || logStatus!='Y' }">
	<script>
		location.href="/temp/login";
	</script>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>글쓰기</h1>
<form method="post" action="/temp/boardWriteOk">
	제목:<input tpye="text" name="subject" size="50"/><br/>
	글내용:<textarea rows="10" cols="50" name="content"></textarea><br/>
	<input type="submit" value="글등록"/>
</form>
</body>
</html>