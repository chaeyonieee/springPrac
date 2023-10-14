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
<script type ="text/javascript">

function checkAdminPassword(){
	if(!checkAdminPassword()){
		alert("비밀번호가 틀립니다.");
		 return false;
	}
}

</script>
</head>
<body>
<h1>영화예매사이트</h1>
<form action="admin.jsp" method="post"> 
비밀번호: <input type="password" name="pwd"><br>
<input type="submit" value="로그인" onclick ="return checkAdminPassword()">
<input type="reset" value="다시입력">
</form>
</body>
</html>
