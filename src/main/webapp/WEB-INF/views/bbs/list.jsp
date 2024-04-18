<%--
  Created by IntelliJ IDEA.
  User: wkdwl
  Date: 2024-04-17
  Time: 오전 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List</title>
</head>
<body>
    <h1>bbs >> list</h1>
    <ul>
    <c:forEach var="bbsDTO" items="${bbsDTOList}">
        <li><a href="/bbs/view?idx=${bbsDTO.idx}"> ${bbsDTO}</a></li>
    </c:forEach>
    </ul>
</body>
</html>
