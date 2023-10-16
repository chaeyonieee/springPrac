<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
request.setCharacterEncoding("utf-8");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인창</title>
</head>
<body>
<form name="frmLogin" method ="post" action ="${contextPath}/test/login.do"><!-- contextpath는 pro26 이다 -->
<table border="1" width="80%" align="center">
<tr align ="center">

<tr align ="center">
<td><input type = "text" name="userID" size="20"></td>
<td><input type = "text" name="userName" size="20"></td>
</tr>
<tr align="center">
<td colspan=2>
<input type="submit" value="로그인"><!-- submit메서드이다 : 브라우저에 저장되어있는게 호출이됨 -->
<input type="reset" value="다시입력"></td>
</tr>

</table>
</form>
</body>
</html>

<!-- 
test/login.do로 끝나면 .do로 끝나면 리스너가 기다렸다가 mybatis까지 주고 실행시킨다



 -->