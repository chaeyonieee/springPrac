<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    isELIgnored="false"%>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value = "${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
 <style>
 .moo{width:300px}
 
 </style>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���</title>
</head>
<body>
<table border=0 width="100%">
<tr>
<td>
<a href="${contextPath }/main.do">
<img class="moo"src="${contextPath }/resources/image/moo.gif" />
</a>
</td>
<td>
<h1><font size=30>������ �ǽ� Ȩ������!!</font></h1>
</td>

<td>
<!-- <a href ="#"><h3>�α���</h3></a> -->
<c:choose>
<c:when test ="${isLogOn ==true && member!=null }">
<h3>ȯ���մϴ�. ${member.name }��!!</h3>
<a href="${contextPath }/member/logout.do"><h3>�α׾ƿ�</h3></a>
</c:when>
<c:otherwise>
<a href="${contextPath }/member/loginForm.do"><h3>�α���</h3></a>
</c:otherwise>
</c:choose>
</td>
</tr>
</table>
</body>
</html>