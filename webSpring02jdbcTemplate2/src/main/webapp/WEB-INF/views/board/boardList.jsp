<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	ul, li{
		margin:0;
		padding:0;
		list-style-type:none;
	}
	li{
		width:10%;
		height:40px;
		line-height:40px;
		float:left;
		border-bottom:1px solid gray;
	}
	li:nth-child(5n+2){
		width:60%;
	}
	.cut{
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
</style>
</head>
<body>
<h1>게시판 목록</h1>
<a href="/temp/boardWrite">글쓰기</a>
<ul>
	<li>번호</li>
	<li>제목</li>
	<li>글쓴이</li>
	<li>조회수</li>
	<li>등록일</li>	
	<c:forEach var="v" items="${list}">
		<li>${v.no }</li>
		<li class="cut"><a href="/temp/boardView?no=${v.no}">${v.subject }</a></li>
		<li>${v.userid }</li>
		<li>${v.hit }</li>
		<li>${v.writedate }</li>
	</c:forEach>
</ul>
</body>
</html>