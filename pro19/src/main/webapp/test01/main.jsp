<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value ="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<style>
a {
text-decoration:none;
color:black;
}
</style>
</head>
<body>
<p><a href="${contextPath}/movie357/addMovies.do">영화 예매하기</a></p>
<p><a href="${contextPath}/movie357/addMovies.do">예매 확인하기</a></p>
<p><a href="${contextPath}/movie357/addMovies.do">예매 취소하기</a></p>
<p><a href="${contextPath}/movie357/addMovies.do">관리자 메뉴로 이동</a></p>
</body>
</html>