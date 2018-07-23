<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시물 목록</title>
</head>
<body>
<h1>게시물 목록</h1>
<p><a href='form'>새 글</a></p>
<table border='1'>
<tr>
    <th>번호</th><th>제목</th><th>등록일</th>
</tr>
<c:forEach items="${list}" var="board">
<tr>
<td>${board.bno}</td><td><a href='view/${board.bno}'>${board.titl}</a></td>
<td>${board.getCdt() }</td>
</tr>
</c:forEach>

</table>
<c:choose>
    <c:when test="${page > 1 }">
        <a href="?page=${page -1}">[이전]</a>
    </c:when>
    <c:otherwise>
        [이전]
    </c:otherwise>
</c:choose>
<span>${requestScope.page}</span>
 <c:if test="${page < totalPage}">
    <a href="list?page=${page + 1}&size=${size}">[다음]</a>
 </c:if>
 <c:if test="${page >= totalPage}">
    
 </c:if>
    
</body>
</html>
