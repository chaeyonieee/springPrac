<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value ="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화예매사이트</title>
</head>
<body>
<h1>영화예매사이트</h1>
<form action="main.jsp" method="post">
아이디: <input type="text" name="id"><br>
비밀번호: <input type="password" name="pwd"><br>
<input type="submit" value="로그인">
<input type="reset" value="다시입력">
</form>
<form action="login2.jsp" method="post">
<input type="submit" value="관리자 로그인">
</form>
</body>
</html>