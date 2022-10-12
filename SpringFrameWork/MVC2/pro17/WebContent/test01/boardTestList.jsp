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
   <title>게시글 출력 창</title>
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
         <td width="7%" ><b>articleNo</b></td>
         <td width="7%" ><b>parentNo</b></td>
         <td width="7%" ><b>title</b></td>
         <td width="7%"><b>content</b></td>
         <td width="7%" ><b>writeDate</b></td>
         <td width="7%" ><b>id</b></td>
   </tr>

<c:choose>
    <c:when test="${ empty boardList}" >
      <tr>
        <td colspan=5 align="center">
          <b>등록된 게시글이 없습니다.</b>
       </td>  
      </tr>
   </c:when>  
   <c:when test="${!empty boardList }" >
      <c:forEach  var="board" items="${boardList }" >
        <tr align="center">
          <td>${board.articleNo }</td>
          <td>${board.parentNo }</td>
          <td>${board.title}</td>     
          <td>${board.content }</td>     
          <td>${board.writeDate}</td>     
          <td>${board.id}</td>     
       </tr>
     </c:forEach>
</c:when>
</c:choose>
   </table>  
 <a href="#"><p class="cls2">게시글 등록</p></a>
</body>
</html>
