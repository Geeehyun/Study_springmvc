<%--
  Created by IntelliJ IDEA.
  User: wkdwl
  Date: 2024-04-17
  Time: 오전 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <title>View</title>
</head>
<body>
    <h1>bbs >> view</h1>
<div>
    <span>인덱스 : ${idx}</span>
</div>
<div>
    <span>아이디 : ${user_id}</span>
</div>
<div>
    <span>제목 : ${title}</span>
</div>
<div>
    <span>내용 : ${content}</span>
</div>
<div>
    <span>출력날짜 : ${display_date}</span>
</div>
<div>
    <button type="button" onclick="location.href='/bbs/list'">목록</button>
    <button type="button" onclick="location.href='/bbs/modify?idx=${idx}'">수정</button>
    <button type="button" onclick="location.href='/bbs/delete?idx=${idx}'">삭제</button>
</div>
</body>
</html>