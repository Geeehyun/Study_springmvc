<%--
  Created by IntelliJ IDEA.
  User: wkdwl
  Date: 2024-04-17
  Time: 오후 4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/login/login" method="post">
    <div><span>아이디 : <input type="text" name="user_id" id="user_id" <c:if test="${!empty cookie['save_id'].value}">value="${cookie['save_id'].value}" </c:if></span></div>
    <div><span>비밀번호 : <input type="password" name="pwd" id="pwd" value=""></span></div>
    <div>
        <label for="save_id"><input type="checkbox" name="save_id" id="save_id" <c:if test="${!empty cookie['save_id'].value}">checked</c:if> >아이디 저장</label>
        <label for="auto_login"><input type="checkbox" name="auto_login" id="auto_login" <c:if test="${!empty cookie['auto_login'].value}">checked</c:if> >자동 로그인</label>
    </div>
    <div>
        <button type="submit">로그인</button>
    </div>
</form>
<script>

</script>
</body>
</html>
