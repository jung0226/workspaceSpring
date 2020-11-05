<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${logStatus==null || logStatus!='Y'}">
	<script>
		location.href="/temp/login";
	</script>
</c:if>
<c:if test ="${logId != vo.userid}" >	
	<script>
		alert("작성자만 글을 수정할 수 있습니다.");
		history.back();
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
<form method="post" action="/temp/boardEditOk">
	<input type="hidden" name="no" value="${vo.no }"/>
	제목:<input type="text" name="subject" size="50" value="${vo.subject }"/><br/>
	글내용:<textarea rows="10" cols="50" name="content">${vo.content }</textarea><br/>
	<input type="submit" value="글수정"/>
</form>
</body>
</html>