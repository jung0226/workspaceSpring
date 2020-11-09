<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	function boardDelChk(no) {
		if (confirm("글을 삭제하시겠습니까?")) {
			location.href = "/temp/boardDelete?no=" + no;
		}
	}
	$(function() {
		//댓글 리스트 구하기
		function replyListSelect() {
			var url = "/temp/replyList";
			var data = "no=${vo.no}";
			$.ajax({
					url : url,
					data : data,
					success : function(result) {
						var $result = $(result);
						var tag = "";
						$result.each(function(i, v) {
							tag += "<li><div>" + v.userid + " ("+ v.writedate + ") ";
								if (v.userid == '${logId}') {//'abcd' abcd
										tag += "<input type='button' class='edit' value='수정'/>";
										tag += "<input type='button' class='del' value='삭제' title='"+v.re_no+"'/>";
									}
										tag += "<br/>" + v.content+ "<hr/></div>";
										//로그인한 아이디와 현재댓글의 아이디가 같으면 수정폼
										if (v.userid == '${logId}') {
											tag += "<div style='display:none'><form>";
											tag += "<input type='hidden' name='re_no' value='"+v.re_no+"'/>";
											tag += "<textarea name='content' style='width:500px; height:100px;'>"
													+ v.content + "</textarea>";
											tag += "<input type='submit' value='Edit'/></form></div> ";
										}
										tag += "</li>";
									});
							$("#replyList").html(tag);
						},
						error : function() {
							console.log("댓글 선택");
						}

					});

		}
		//글 쓰기
		$('#replyForm').submit(function() {
			if ($('#Content').val() == "") {
				alert("댓글 입력을 등록하세요.");
				return false;
			}
			var url = "/temp/replyWrite";
			var params = $('#replyForm').serialize();//"no=255&content=adfaf"
			$.ajax({
				url : url,
				data : params,
				success : function(result) {
					replyListSelect();
					$('#content').val("");
				},
				error : function() {
					console.log("댓글쓰기 에러발생");
				}
			});

			return false;
		});

		//댓글 수정 버튼 클릭시 -> 수정 폼 보여주기
		$(document).on('click', '.edit', function() {
			$(this).parent().css("display", "none");
			$(this).parent().next().css("display", "block");
		});
		//댓글 Edit버튼 클릭시
		$(document).on('submit', '#replyList form', function() {
			var url = "/temp/replyEdit";
			var params = $(this).serialize();

			$.ajax({
				url : url,
				data : params,
				success : function(result) {
					replyListSelect();
				},
				error : function() {
					console.log("댓글수정 클릭시 에러발생 Ajax");
				}
			});
			return false;
		});
		//댓글 삭제
		$(document).on('click', '.del', function() {
			if (confirm("삭제하시겠습니까?")) {
				var url = "/temp/replyDel";
				var parmas = "re_no=" + $(this).attr("title");

				$.ajax({
					url : url,
					data : parmas,
					success : function(result) {
						replyListSelect();
					},
					error : function() {
						console.log("댓글 삭제 에러...");
					}
				});
			}
		});

		//글내용보기 댓글 보여주기
		replyListSelect();
	});
</script>
</head>
<body>
	<h1>글 내용 보기</h1>
	<ul>
		<li>번호: ${vo.no }</li>
		<li>작성자: ${vo.userid }</li>
		<li>등록일: ${vo.writedate }</li>
		<li>조회수: ${vo.hit }</li>
		<li>제목: ${vo.subject }</li>
		<li>글 내용: ${vo.content }</li>
	</ul>
	<a href="/temp/boardEdit?no=${vo.no }">수정</a>
	<a href="javascript:boardDelChk(${vo.no})">삭제</a>
	<div id="reply">
		<c:if test="${logStatus=='Y'}">
			<!-- 댓글쓰기 -->
			<form method="post" id="replyForm">
				<input type="hidden" name="no" value="${vo.no }">
				<!-- 원글 번호 -->
				<textarea name="content" id="content"
					style="width: 500px; height: 100px"></textarea>
				<input type="submit" value="등록" />
			</form>
		</c:if>
	</div>
	<ul id="replyList">
		<li>hong (2020-10-19) 수정, 삭제<br /> 댓글내용
		</li>
	</ul>
</body>
</html>