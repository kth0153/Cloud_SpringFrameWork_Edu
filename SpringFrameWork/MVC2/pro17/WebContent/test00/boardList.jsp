<%@ page language="java" contentType="text/html; charset=UTF-8"
	import=" java.util.*,sec01.ex01.*"
	pageEncoding="UTF-8"
	isELIgnored="false" 
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");
%>    
<html>
<head>
   <meta  charset="UTF-8">
   <title>회원 정보 출력창</title>
<style>
     .cls1 {
       font-size:40px;
       text-align:center;
     }
    
     .cls2 {
       font-size:20px;
       text-align:center;
     }
  </style>
  
</head>
<body>
 <p class="cls1">게시글</p>
   <table align="center" border="1" >
      <tr align="center" bgcolor="lightgreen">
         <td width="7%" ><b>글번호</b></td>
         <td width="7%" ><b>제목</b></td>
         <td width="7%" ><b>글내용</b></td>
         <td width="7%"><b>작성일</b></td>
   </tr>

<c:choose>
    <c:when test="${ empty boardList}" >
      <tr>
        <td colspan=5 align="center">
          <b>작성된 게시글이 없습니다.</b>
       </td>  
      </tr>
   </c:when>  
   <c:when test="${!empty boardList }" >
      <c:forEach  var="board" items="${boardList }" >
        <tr align="center">
          <td>${board.articleNo }</td>
          <td>${board.title}</td>     
          <td>${board.content }</td>     
          <td>${board.writeDate}</td>     
       </tr>
     </c:forEach>
</c:when>
</c:choose>
   </table>  
 <a href="addForm.do"><p class="cls2">글 작성하기</p></a>
 <a href="addForm.do"><p class="cls2">글 수정하기</p></a>
 <a href="addForm.do"><p class="cls2">글 삭제하기</p></a>
</body>
</html>
