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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>View</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/WEB-INF/views/common/common.jsp" />
    <div class="container">
        <div class="card justify-content-md-center" style="width: 80%; margin: 0 auto";>
            <div class="card-header bg-primary text-white">게시글 상세</div>
            <div class="card-body" style="padding: 30px">
                <h5 class="card-title">제목 : ${bbsDTO.title}</h5>
                <h6 class="card-subtitle mb-2 text-muted">작성자 : ${bbsDTO.user_id}</h6>
                <div class="card-text card mb-3 mt-3">
                    <p class="card-text p-2" >
                        ${bbsDTO.content}
                    </p>
                </div>
                <h6 class="card-subtitle mb-2 text-muted">출력날짜 : ${bbsDTO.display_date}</h6>
                <h6 class="card-subtitle mb-2 text-muted">관심사항 : ${bbsDTO.interest}</h6>
                <div class="mt-4 d-grid gap-2 d-md-flex justify-content-md-end">
                    <button class="btn btn-outline-primary" type="button" onclick="location.href='/bbs/list'">목록</button>
                    <button class="btn btn-outline-primary" type="button" onclick="location.href='/bbs/modify?idx=${bbsDTO.idx}'">수정</button>
                    <button class="btn btn-outline-primary" type="button" onclick="goDelete(${bbsDTO.idx})">삭제</button>
                </div>
            </div>
            </div>
    </div>
</div>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
