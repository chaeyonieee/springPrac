<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*, movie.*" isELIgnored="false"%>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value = "${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<c:choose>
  <c:when test='${msg=="save"}'>
    <script>
     window.onload=function(){
     alert("영화를 등록했습니다.");
     }
     </script>
     </c:when>
      <c:when test='${msg=="deleted"}' >
      <script>
        window.onload=function(){
        	alert("영화를 삭제했습니다.");
        }
      </script>
      </c:when>
    </c:choose>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
 .cls1 {
      font-size:40px;
      text-align:center;
 }
 .cls2{
      font-size:20px;
      text-align:center;
 }
</style>
</head>
<body>
<h1 class = "cls2">영화목록</h1>
<table align="center" border="1">
<tr align="center" bgcolor="#b3d9ff">
 <td width="7%"><b>영화등록번호</b></td>
 <td width="7%"><b>영화제목</b></td>
 <td width="7%"><b>영화장르</b></td>
 <td width="7%"><b>삭제</b></td>
</tr>
<c:choose>
  <c:when test="${empty moviesList}" >
    <tr>
      <td colspan=5 align ="center">
        <b>등록된 영화가 없습니다.</b>
      </td>
    </tr>
  </c:when>
  <c:when test="${!empty moviesList}" >
    <c:forEach var="movie" items="${moviesList}">
      <tr align ="center">
        <td>${movie.id}</td>
        <td>${movie.title}</td>
        <td>${movie.genre}</td>
        <td><a href ="${contextPath}/movie357/deleteMovie.do?id=${movie.id}">삭제</a></td>
      </tr>
    </c:forEach>
  </c:when>
</c:choose>
</table>
<a href = "${contextPath}/movie357/movieForm.do"><p class = "cls2">등록하기</a></p>
</body>
</html>