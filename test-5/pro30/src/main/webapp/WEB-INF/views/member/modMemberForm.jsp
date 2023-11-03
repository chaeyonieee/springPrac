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
<title>회원정보 수정창</title>
<style>
.clsl{
font-size:40px;
text-align:center;
}

</style>

</head>
<body>
<p class="clsl">회원정보 수정창</p>
<form method="post" action="${contextPath}/member/modMember.do">

<table align ="center">
<tr>
<td width="200"><p align ="right">아이디</p></td>
<td width ="400"><input type="text" value="${memberVO.id}" disabled></td><!-- 정보 못건너감  value="${memberVO.id}는 넘어감-->
</tr>
<tr>
<td width="200"><p align ="right">비밀번호</p></td>
<td width ="400"><input type="password" name ="pwd"  value="${memberVO.pwd}"></td>
</tr>
<tr>
<td width="200"><p align ="right">이름</p></td>
<td width ="400"><input type="text" name ="name"  value="${memberVO.name}"></td>
</tr>
<tr>
<td width="200"><p align ="right">이메일</p></td>
<td width ="400"><input type="text" name ="email"  value="${memberVO.email}"></td>
</tr>

<tr align="center">
<td colspan="2" width="400">
<input type="submit" value="수정하기">
<input type ="reset" value="다시입력">
</td>
</tr>
</table>
<input type="hidden" name ="id" value="${memberVO.id}"> 
</form>
</body>
</html>