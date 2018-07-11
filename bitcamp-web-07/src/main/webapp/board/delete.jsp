<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='Refresh' content='1;url=list'>
        <title>게시물 변경</title>
</head>
<body>

<h1>게시물 삭제 결과</h1>
<hr>
<c:if test="${del == 0}">
<h2>게시물을 삭제 하지 못하였습니다.</h2>
</c:if>

<c:if test="${del == 1}">
<h2>게시물을 삭제 하였습니다.</h2>
</c:if>

</body>
</html>