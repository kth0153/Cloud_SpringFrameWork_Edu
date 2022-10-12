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
   <title>게시판 등록</title>
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
<form action="addBoard.do">
 <p class="cls1">게시글 작성</p>
   <table align="center" border="1" >
      <tr align="center" bgcolor="lightgreen">
         <td width="7%" ><b>제목</b></td>
         <td width="7%" ><input type="text" name="title"></td>
      </tr>
      <tr align="center" bgcolor="lightgreen">
         <td width="7%" ><b>내용</b></td>
         <td width="7%" ><input type="content" name="content"></td>
      </tr>
   </table>  
	<p class="cls2"><input type="submit" value="글작성하기"></p>
</form>
</body>
</html>
