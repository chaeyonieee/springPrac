<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<% request.setCharacterEncoding("utf-8"); %>
<!-- CSS only -->
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
  crossorigin="anonymous"
/>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원정보 출력창</title>
  </head>

  <body>
    <table
      align="center"
      width="80%"
      border="1"
      class="table table-bordered border-primary"
    >
      <tr
        align="center"
        bgcolor="
#6a89cc"
      >
        <td><b>아이디</b></td>
        <td><b>패스워드</b></td>
        <td><b>이름</b></td>
        <td><b>이메일</b></td>
        <td><b>가입일</b></td>
        <td><b>수정</b></td>
        <td><b>삭제</b></td>
      </tr>

      <c:forEach var="member" items="${membersList}">
        <tr align="center">
          <td>${member.id}</td>
          <td>${member.pwd}</td>
          <td>${member.name}</td>
          <td>${member.email}</td>
          <td>${member.joinDate}</td>

          <td>
            <a href="${contextPath }/member/modMember.do?id=${member.id}"
              >수정하기</a
            >
          </td>

          <td>
            <a href="${contextPath }/member/removeMember.do?id=${member.id}"
              >삭제하기</a
            >
          </td>
        </tr>
      </c:forEach>
    </table>
    <a
      class="btn btn-outline-primary"
      href="${contextPath }/member/memberForm.do"
    >
      <h1 style="text-align: center">회원가입</h1></a
    >
  </body>
</html>
