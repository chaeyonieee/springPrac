<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value ="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화등록창</title>
<script type ="text/javascript">

function check(){
	if(form.title.value.length == 0){
		alert("제목을 입력하세요.");
		 form.title.focus();
		 return false;
	}

	if(form.genre.value.length == 0){
		alert("장르를 입력하세요.");
		 form.genre.focus(); 
		 return false;
	}
}

</script>
</head>
<body>
<form method ="post" action ="${contextPath}/movie357/addMovie.do">
<h1 style ="text-align:center">영화등록창</h1>
<table align ="center">
  <tr>
    <td width="200"><p align ="right">영화제목</td>
    <td width="400"><input type ="text" name ="title"></td>
  </tr>
  <tr>
    <td width="200"><p align ="right">영화장르</td>
    <td width="400"><input type ="text" name ="genre" ></td>
  </tr>

  <tr>
    <td width="200"><p>&nbsp;</p></td>
    <td width="400"><input type ="submit" value="등록하기" onclick="return check()">
    <input type ="reset" value="다시입력">
    </td>
  </tr>
</table>
</form>
</body>
</html>