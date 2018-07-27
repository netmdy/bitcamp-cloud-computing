<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>팀 목록</title>
</head>
<body>
<h1>팀 목록</h1>
<p><a href='form'>새 팀</a></p>
<table border='1'>
<tr>
    <th>팀명</th><th>최대인원</th><th>기간</th>
</tr>
<c:forEach items="${list}" var="team">
<tr>
    <td><a href='${team.name}'>${team.name}</a></td>
    <td>${team.maxQty}</td>
    <td>${team.startDate}~${team.endDate}</td>
</tr>
</c:forEach>
</table>

<c:if test="${pageNo <= 1 }">
[이전]
</c:if>
<c:if test="${pageNo > 1 }">
<a href="?pageNo=${pageNo -1}">[이전]</a>
</c:if>
 ${pageNo}
<c:if test="${totalPage > pageNo}">
    <a href="?pageNo=${pageNo + 1}">[다음]</a>
</c:if>
</body>
</html>

    