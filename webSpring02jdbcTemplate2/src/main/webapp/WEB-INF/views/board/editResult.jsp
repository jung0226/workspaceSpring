<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${result>0 }">
	<script>
		alert("글이 수정되었습니다. 글 내용 보기로 이동합니다.");
		location.href="/temp/boardView?no=${no}";
	</script>
</c:if>
<c:if test="${result<=0}">
	<script>
		alert("글 수정 실패하였습니다. 글 수정 페이지로 이동합니다.");
		history.go(-1);
	</script>
</c:if>