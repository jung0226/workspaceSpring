<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	input[type=text]{
		width:80%;		
	}
	textarea{
	width:80%;
	height:200px;}
</style>
</head>
<body>
<h1>글쓰기</h1>
<form method="post" action="<%=request.getContextPath()%>/boardWriteOk">
	제목: <input type="text" name="subject"/><br/>
	글 내용: <textarea name="content"></textarea><br/>
	<input type="submit" value="등록하기"/>
</form>
</body>
</html>