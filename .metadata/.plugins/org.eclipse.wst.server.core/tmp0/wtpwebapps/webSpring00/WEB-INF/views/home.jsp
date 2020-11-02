<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css"/>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>
</head>
<body>
<h1>
	Hello world!  홈페이지
</h1>

<P>  ${username }<br/>
	<ul>
		<c:forEach var="i" items="${data }">
			<li>${i }</li>
		</c:forEach> 
	</ul>
</P>
<!-- /webapp -->
<!-- 서버로 데이터 보내기 -->
<a href="/myapp/aLink?no=200&name=hong"><img src="<%=request.getContextPath()%>/img/1.jpg" id="i1" onclick="setBorder()"/></a>

<img src="<%=request.getContextPath() %>/img/6.jpg"/>
<div>
	<form method="post" action="/myapp/form1">
		이름 : <input type="text"	 name="username"/><br/>
		연락처 : <input type="text" name="tel"/><br/>
		주소 : <input type="text" name="addr"/><br/>
		<input type="submit" value="전송"/>
	</form>
	<hr/>
</div>
<div>
	<form method="post" action="/myapp/form2">
		이름 : <input type="text"	 name="username"/><br/>
		연락처 : <input type="text" name="tel"/><br/>
		주소 : <input type="text" name="addr"/><br/>
		<input type="submit" value="전송"/>
	</form>
	<hr/>
</div>
<div>
	<form method="post" action="/myapp/form3">
		이름 : <input type="text"	 name="username"/><br/>
		연락처 : <input type="text" name="tel"/><br/>
		주소 : <input type="text" name="addr"/><br/>
		<input type="submit" value="전송"/>
	</form>
	<hr/>
</div>

</body>
</html>
