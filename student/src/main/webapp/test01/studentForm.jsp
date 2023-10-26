<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
    
    
    <%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
</head>
<body>
<form method ="post" action ="${contextPath}/student151/addStudent.do">
<h1 style ="text-align:center">회원 가입창</h1>
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
<tr>
<td width="200"><p>&nbsp;</p></td>
<td width ="400">
<input type="submit" value="가입하기">
<input type ="reset" value="다시입력">
</td>
</tr>
</table>
</form>
</body>
</html>