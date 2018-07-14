<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>팀 목록</title>
</head>
<body>
<h1>팀 목록 :D</h1>
<p><a href='form.html'>신규 팀</a></p>
<table border='1'>
<tr>
    <th>팀명</th><th>최대인원</th><th>기간</th>
</tr>
<c:forEach items="${list}" var="team">
<tr>
    <td><a href='view?name=${team.name}'>${team.name}</a></td><td>${team.max_qty}</td><td>${team.startDate}~${team.endDate}</td>
</tr>
</c:forEach>
</table>
</body>
</html>
