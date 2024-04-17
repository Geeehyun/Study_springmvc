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
    <title>Join</title>
</head>
<body>
    <h1>member >> join</h1>
    <form action="/member/join" method="post" id="frm">
        <div>
            <span>아이디 : <input type="text" name="user_id" id="user_id" maxlength="20"></span>
        </div>
        <div>
            <span>제목 : <input type="text" name="title" id="title" maxlength="100"></span>
        </div>
        <div>
            <span>내용 :<textarea name="content" id="content" rows="10" cols="60"></textarea></span>
        </div>
        <div>
            <span>출력날짜 : <input type="date" name="display_date" id="display_date"></span>
        </div>
        <div>
            <button type="submit">제출</button>
            <button type="reset">지우기</button>
        </div>
    </form>
</body>
</html>
