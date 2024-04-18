<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Regist</title>
</head>
<body>
    <h1>bbs >> regist</h1>
    <form action="/bbs/regist" method="post" id="frm">
        <div>
            <span>아이디 : <input type="text" name="user_id" id="user_id" maxlength="20" value="${dto.user_id}"></span>
            <div id="div_err_user_id"></div>
        </div>
        <div>
            <span>제목 : <input type="text" name="title" id="title" maxlength="100" value="${dto.title}"></span>
            <div id="div_err_title"></div>
        </div>
        <div>
            <span>내용 :<textarea name="content" id="content" rows="10" cols="60">${dto.content}</textarea></span>
            <div id="div_err_content"></div>
        </div>
        <div>
            <span>출력날짜 : <input type="date" name="display_date" id="display_date" value="${dto.display_date}"></span>
            <div id="div_err_display_date"></div>
        </div>
        <div>
            <span>관심사항 :
                <label for="interest_01"><input type="checkbox" name="interest" id="interest_01" value="sports">스포츠</label>
                <label for="interest_02"><input type="checkbox" name="interest" id="interest_02" value="travel">여행</label>
                <label for="interest_03"><input type="checkbox" name="interest" id="interest_03" value="movie">영화</label>
                <label for="interest_04"><input type="checkbox" name="interest" id="interest_04" value="music">음악</label>
            </span>
            <div id="div_err_interest"></div>
        </div>
        <div>
            <button type="submit">제출</button>
            <button type="reset">지우기</button>
        </div>
    </form>
<script>
    const serverValidResult = {};
    <c:forEach var="err" items="${errors}">
    if(document.getElementById("div_err_${err.getField()}") != null) {
        let target =  document.getElementById("div_err_${err.getField()}");
        target.innerHTML = "<span>${err.getField()} 필드는 ${err.defaultMessage}</span>";
        target.style.color = "red";
    }
    serverValidResult['${err.getField()}'] = '${err.defaultMessage}';
    console.log('${err.getField()} : ${err.defaultMessage}');
    </c:forEach>
</script>
</body>
</html>
