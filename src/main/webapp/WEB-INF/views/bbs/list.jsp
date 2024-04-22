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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/WEB-INF/views/common/common.jsp" />
    <div class="container">
        <div class="card justify-content-md-center" style="width: 80%; margin: 0 auto";>
            <div class="card-header bg-primary text-white">게시판</div>
            <div class="card-body" style="padding: 30px">
                <div class="list-group ">
                <c:forEach var="bbsDTO" items="${bbsDTOList}" varStatus="status">
                    <div class="p-2 list-group-item list-group-item-action <c:if test="${status.count % 2 == 0}">list-group-item-secondary</c:if> " onclick="location.href = '/bbs/view?idx=${bbsDTO.idx}'">
                        <div class="d-flex w-100 justify-content-between">
                            <h6>${bbsDTO.title}</h6>
                        </div>
                        <small class="text-mute">
                            작성자 : ${bbsDTO['user_id']} |
                            출력날짜 : ${bbsDTO['display_date']} |
                            관심사항 :
                            <c:if test="${empty bbsDTO.interest}">
                                없음
                            </c:if>
                            <c:if test="${!empty bbsDTO.interest}">
                                ${bbsDTO.interest}
                            </c:if></small>
                    </div>
                </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    if(${!empty error}) {
        alert("잘못된 접근 입니다.");
    }
</script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
