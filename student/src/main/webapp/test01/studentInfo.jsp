<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
    
  <%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  
  <%
  request.setCharacterEncoding("UTF-8");
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생정보 삽입창</title>
<style>
.clsl{
font-size:40px;
text-align:center;
}

</style>

</head>
<body>
<p class="clsl">학생정보 삽입창</p>
<form method="post" action="${contextPath}/student151/addStudent.do?id=${studentInfo.id}">

<table align ="center">
<tr>
<td width="200"><p align ="right">아이디</p></td>
<td width ="400"><input type="text" name ="id" value="${studentInfo.id}"disabled></td>
</tr>
<tr>
<td width="200"><p align ="right">이름</p></td>
<td width ="400"><input type="password" name ="username" value="${studentInfo.username}"></td>
</tr>
<tr>
<td width="200"><p align ="right">대학교</p></td>
<td width ="400"><input type="text" name ="univ"  value="${studentInfo.univ}"></td>
</tr>
<tr>
<td width="200"><p align ="right">생년월일</p></td>
<td width ="400"><input type="date" name ="birth" value="${studentInfo.birth}"></td>
</tr>
<tr>
<td width="200"><p align ="right">이메일</p></td>
<td width ="400"><input type="text" name =" email" value="${studentInfo.email}" ></td>
</tr>
<tr align="center">
<td colspan="2" width="400">
<input type="submit" value="삽입하기">
<input type ="reset" value="다시입력">
</td>
</tr>
</table>
</form>
</body>
</html>