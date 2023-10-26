<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*,sec01.ex01.*"
    pageEncoding="UTF-8"
    isELIgnored="false"
    
  %>
    <%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:set var="contextPath" value="${pageContext.request.contextPath }" />
  <%
  request.setCharacterEncoding("utf-8");
  %>
<!DOCTYPE html>
<html>
<head>
<c:choose>
<c:when test='${msg=="addStudent"}'>
<script>
window.onload=function(){
	alert("회원을 등록했습니다.")
}
</script>
</c:when>

<c:when test='${msg=="deleted"}'>
<script>
window.onload=function(){
	alert("회원을 삭제했습니다.")
}
</script>
</c:when>
</c:choose>
<meta charset="UTF-8">
<title>회원정보 출력창</title>
<style>
.clsl{
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
<p class="clsl">회원정보</p>
<table align ="center" border="1">
<tr align ="center" bgcolor="lightgreen">
<td width ="7% "><b>아이디</b></td>
<td width ="7% "><b>사용자</b></td>
<td width ="7% "><b>대학교</b></td>
<td width ="7% "><b>생년월일</b></td>
<td width ="7% "><b>이메일</b></td>
<td width ="7% "><b>삽입</b></td>
<td width ="7% "><b>삭제</b></td>

</tr>
<c:choose>
<c:when test ="${empty studentsList } ">
<tr>
<td colspan=5 align="center">
<b>등록된 회원이 없습니다.</b>
</td>
</tr>
</c:when>
<c:when test ="${!empty studentsList}">
<c:forEach var="student" items="${studentsList}">
<tr align="center">
<td>${student.id }</td>
<td>${student.username }</td>
<td>${student.univ }</td>
<td>${student.birth }</td>
<td>${student.email }</td>
<td><a href="${contextPath }/student151/studentInfo.do?id=${student.id }">삽입</a></td>
<td><a href="${contextPath }/student151/delStudent.do?id=${student.id }">삭제</a></td>
</tr>
</c:forEach>
</c:when>
</c:choose>
</table>

</body>
</html>