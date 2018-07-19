<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시물 보기</title>
</head>
<body>
<h1>게시물 보기</h1>
<form action='../update' method='post'>
<table border='1'>
<tr><th>번호</th><td>
    <input type='text' name='bno' value='${board.bno}' readonly></td></tr>
<tr><th>제목</th>
    <td><input type='text' name='titl' value='${board.titl}'></td></tr>
<tr><th>내용</th>
    <td><textarea name='cont' rows='10' cols='60'>${board.cont}</textarea></td></tr>
<tr><th>등록일</th><td>${board.cdt}</td></tr>
<p>
<a href='../list'>목록</a>
<button>변경</button>
<a href='../delete?no=${board.bno}'>삭제</a>
</p>
</table>
</form>
</body>
</html>