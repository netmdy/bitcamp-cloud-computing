<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>강의 목록</title>
</head>
<body>
<h1>강의 목록 :D</h1>
<p><a href='form'>새 강의</a></p>
<table border='1'>
<tr>
    <th>번호</th><th>강의명</th><th>기간</th><th>강의실</th>
</tr>
<c:forEach items="${list}" var="classRoom">
<tr>
    <td>${classRoom.crno}</td>
    <td><a href='view/${classRoom.crno}'>${classRoom.title}</a></td>
    <td>${classRoom.startDate}~${classRoom.endDate}</td>
    <td>${classRoom.room}</td>
</tr>
</c:forEach>

</table>
<c:if test="${page <= 1}">
[이전]
</c:if>
<c:if test="${page > 1}">
    <a href="?page=${page -1}&size=${size}">[이전]</a>
</c:if><c:out value="${page}"></c:out>
<c:if test="${totalPage > page}">
    <a href="?page=${page +1}&size=${size}">[다음]</a>
</c:if>
</body>
</html>
