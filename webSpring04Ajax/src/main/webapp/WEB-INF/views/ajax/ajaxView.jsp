<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		//ajax로 서버에서 문자열을 결과로 리턴 받은 경우
		$("#ajaxString").click(function(){
			var url = "/ajaxtest/ajaxString";
			var data="no=1234&username=홍길동&userid=goguma";
			$.ajax({
				url:url,
				data:data,
				type:'GET',
				success:function(result){
					$("#resultData").append(result);
				},error:function(){
					console.log("문자열 받기 에러");
				}				
			});
		});
		
		//ajax로 서버에서 객체 리턴 받은 경우
		$("#ajaxObject").click(function(){			
			var url = "/ajaxtest/ajaxObject";
			$.ajax({
				url:url,
				success:function(result){
					var tag="<ul>";
					tag +="<li>번호= "+result.no+"</li>";
					tag +="<li>이름= "+result.username+"</li>";
					tag +="<li>주소= "+result.addr+"</li></ul>";
					$("#resultData").append(tag);
				},error:function(){
					console.log("객체 받기 에러");
				}
			});
		});
		$('#ajaxList').click(function(){
			var url = "/ajaxtest/ajaxList";
			var params = "no=900&username=이순신&addr=서울시 마포구";
			$.ajax({
				url:url,
				data: params,
				tpye:'GET',
				success:function(result){
					var $result=$(result);
					var tag="<ol>"					
					//			idx=0,1,2,3 vo,vo,vo,vo
					$result.each(function(idx, vo){
						tag+="<li>"+vo.no+", "+vo.username+", "+vo.addr+"</li>";
					});
					tag+="</ol>";
					$("#resultData").append(tag);
				}, error:function(){
					console.log("List받기 에러");
				}
			});
		});
		
		//ajaxMap
		$('#ajaxMap').click(function(){
			var url="/ajaxtest/ajaxMap";
			$.ajax({
				url:url,
				success:function(result){
					var tag="<ol>";
					tag += "<li>"+result.k1.no+", "+result.k1.username+", "+result.k1.addr+"</li>";
					tag += "<li>"+result.k2.no+", "+result.k2.username+", "+result.k2.addr+"</li>";
					tag += "<li>"+result.k3.no+", "+result.k3.username+", "+result.k3.addr+"</li>";					
					tag+="</ol>";
					$("#resultData").append(tag);
					
				}, error:function(){
					console.log("map에러 발생");
				}
			});
		});
		//json 형식으로 되어있는 문자를 받아와서 json으로 사용
		$('#ajaxJson').click(function(){
			var url="/ajaxtest/ajaxJson";
			$.ajax({
				url: url,
				success:function(result){
					//문자열로 된 json데이터 파싱한다.
					var jsonData = JSON.parse(result);
					var tag = "<ul>";
					tag+="<li>번호 : "+jsonData.no+"</li>";
					tag+="<li>이름 : "+jsonData.username+"</li>";
					tag+="<li>연락처 : "+jsonData.tel+"</li>";
					tag+="<li>주소 : "+jsonData.addr+"</li></ul>";
					$('#resultData').append(tag);
				}, error:function(){
					console.log("json받기 에러 발생");
				}
			});
		});
	});
</script>
</head>

<body>
	<h1>Ajax : 비동기식으로 controller에 접속하여 정보를 리턴받는다.</h1>
	<input type="button" id="ajaxString" value="ajax 문자열"/>
	<input type="button" id="ajaxObject" value="ajax Object"/>
	<input type="button" id="ajaxList" value="ajax List"/>
	<input type="button" id="ajaxMap" value="ajax Map"/>
	<input type="button" id="ajaxJson" value="ajax Json"/>
	<br/>
	<div id="resultData" style="background-color:#ddd"> </div>
</body>
</html>