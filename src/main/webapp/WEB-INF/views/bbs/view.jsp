<%--
  Created by IntelliJ IDEA.
  User: wkdwl
  Date: 2024-04-17
  Time: 오전 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View</title>
</head>
<body>
    <h1>bbs >> view</h1>
<div>
    <span>인덱스 : ${bbsDTO.idx}</span>
</div>
<div>
    <span>아이디 : ${bbsDTO.user_id}</span>
</div>
<div>
    <span>제목 : ${bbsDTO.title}</span>
</div>
<div>
    <span>내용 :</span>
    <p>${bbsDTO.content}</p>
</div>
<div>
    <span>출력날짜 : ${bbsDTO.display_date}</span>
</div>
<div>
    <span>관심사항 : ${bbsDTO.interest}</span>
</div>
<div>
    <button type="button" onclick="location.href='/bbs/list'">목록</button>
    <button type="button" onclick="location.href='/bbs/modify?idx=${bbsDTO.idx}'">수정</button>
    <button type="button" onclick="goDelete(${bbsDTO.idx})">삭제</button>
</div>
<ul>
    <li><a href="/login/login">로그인</a></li>
</ul>
<script>
    function goDelete(idx) {
        if(confirm("삭제하시겠습니까?")) {
            let frm = document.createElement("form");
            let input = document.createElement("input");
            frm.action = "/bbs/delete";
            frm.method = "post"
            frm.id = "frm";
            input.name = "idx";
            input.value = idx;
            frm.append(input);
            document.body.append(frm);
            document.querySelector("#frm").submit();
        }
    }
</script>
</body>
</html>
