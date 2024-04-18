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
    <title>Modify</title>
</head>
<body>
    <h1>bbs >> modify</h1>
    <form action="/bbs/modify" method="post" id="frm">
        <input type="hidden" name="idx" id="idx" value="${bbsDTO.idx}">
        <div>
            <span>아이디 : <input type="text" name="user_id" id="user_id" maxlength="20" value="${bbsDTO.user_id}"></span>
        </div>
        <div>
            <span>제목 : <input type="text" name="title" id="title" maxlength="100" value="${bbsDTO.title}"></span>
        </div>
        <div>
            <span>내용 :<textarea name="content" id="content" rows="10" cols="60">${bbsDTO.content}</textarea></span>
        </div>
        <div>
            <span>출력날짜 : <input type="date" name="display_date" id="display_date" value="${bbsDTO.display_date}"></span>
        </div>
        <div>
            <button type="submit">제출</button>
            <button type="reset">지우기</button>
        </div>
    </form>
</body>
</html>
