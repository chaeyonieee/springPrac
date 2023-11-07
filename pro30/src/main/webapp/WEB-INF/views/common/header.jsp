<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    isELIgnored="false"%>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value = "${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
 <style>
 .moo{width:300px}
 
 </style>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>헤더</title>
</head>
<body>
<table border=0 width="100%">
<tr>
<td>
<a href="${contextPath }/main.do">
<img class="moo"src="${contextPath }/resources/image/moo.gif" />
</a>
</td>
<td>
<h1><font size=30>스프링 실습 홈페이지!!</font></h1>
</td>

<td>
<!-- <a href ="#"><h3>로그인</h3></a> -->
<c:choose>
<c:when test ="${isLogOn ==true && member!=null }">
<h3>환영합니다. ${member.name }님!!</h3>
<a href="${contextPath }/member/logout.do"><h3>로그아웃</h3></a>
</c:when>
<c:otherwise>
<a href="${contextPath }/member/loginForm.do"><h3>로그인</h3></a>
</c:otherwise>
</c:choose>
</td>
</tr>
</table>
</body>
</html>