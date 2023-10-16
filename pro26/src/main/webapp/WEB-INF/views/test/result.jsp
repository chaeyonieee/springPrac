<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과창</title>
</head>
<body>
	<h1>아이디 : ${userID }</h1>
	<h1>이름 : ${userName }</h1>

</body>
</html>

<!-- 표현언어로 사용할수있는 이뉴는 isELIgnore는 false이기 때문이다 -->