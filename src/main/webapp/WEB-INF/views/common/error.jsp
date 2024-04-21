<%--
  Created by IntelliJ IDEA.
  User: wkdwl
  Date: 2024-04-21
  Time: 오후 11:24
  To change this template use File | Settings | File Templates.
--%>
<
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>error</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/WEB-INF/views/common/common.jsp" />
    <div style="padding: 30px 50px;" class="container-sm ">
        <div class="card justify-content-md-center" style="width: 80%; margin: 0 auto;">
            <div class="card-header bg-primary text-white">에러 발생</div>
            <div class="card-body" style="padding: 30px">
                <div class="row mb-3">
                    <img class="rounded mx-auto d-block" src="https://cdn-icons-png.flaticon.com/512/5741/5741333.png" style="width: 300px; height: auto;">
                    <h4 class="text-center text-danger pt-2">오류가 발생했습니다!<br>다시 시도해수제요</h4>
                </div>
                <div class="d-grid gap-2">
                    <button type="button" class="btn btn-primary" onclick="location.replace('/')">홈으로</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
