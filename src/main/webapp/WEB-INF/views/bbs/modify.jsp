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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <span>관심사항 :
                <label for="interest_01"><input type="checkbox" name="interest" id="interest_01" value="sports" <c:if test="${fn:contains(bbsDTO.interest,'sports')}">checked</c:if> >스포츠</label>
                <label for="interest_02"><input type="checkbox" name="interest" id="interest_02" value="travel" <c:if test="${fn:contains(bbsDTO.interest,'travel')}">checked</c:if> >여행</label>
                <label for="interest_03"><input type="checkbox" name="interest" id="interest_03" value="movie" <c:if test="${fn:contains(bbsDTO.interest,'movie')}">checked</c:if> >영화</label>
                <label for="interest_04"><input type="checkbox" name="interest" id="interest_04" value="music" <c:if test="${fn:contains(bbsDTO.interest,'music')}">checked</c:if> >음악</label>
            </span>
        </div>
        <div>
            <button type="submit">제출</button>
            <button type="reset">지우기</button>
        </div>
    </form>
</body>
</html>
