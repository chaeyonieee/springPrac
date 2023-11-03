<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>결과창</title>
</head>
<body>
	<h1>업로드가 완료되었습니다</h1>
	<label>아이디: </label>
	<input type="text" name="id" value="${map.id }" readonly><br>
	<label>이름: </label>
	<input type="text" name="name" value="${map.name }" readonly><br>
	<div class="result-images">
	<c:forEach var ="imageFileName" items="${map.fileList }">
	<img src="${pageContext.request.contextPath }/download?imageFileName=${imageFileName}">
	<br><br><br>
	</c:forEach>
	</div><p><a href='${pageContext.request.contextPath }/form'>다시업로드하기</a></p>
	
</body>
</html>