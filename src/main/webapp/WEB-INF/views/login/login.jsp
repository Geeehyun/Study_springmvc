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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/WEB-INF/views/common/common.jsp" />
    <div class="container">
        <div class="card justify-content-md-center" style="width: 80%; margin: 0 auto;">
            <div class="card-header bg-primary text-white">로그인</div>
            <div class="card-body" style="padding: 30px">
                <form action="/login/login" method="post">
                    <input type="hidden" name="acc_url" value="${header.referer}">
                    <div class="row mb-3">
                        <label for="user_id" class="col-sm-2 col-form-label">아이디</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="user_id" name="user_id"  <c:if test="${!empty cookie['save_id'].value}">value="${cookie['save_id'].value}" </c:if> >
                            <div class="invalid-feedback" id="div_err_user_id"></div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="pwd" class="col-sm-2 col-form-label">비밀번호</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="pwd" name="pwd">
                            <div class="invalid-feedback" id="div_err_pwd"></div>
                        </div>
                    </div>
                    <div class="row mb-3" style="padding-left: calc(var(--bs-gutter-x)* .5) !important;">
                        <div class="form-check form-switch col-sm-6 mb-3">
                            <input class="form-check-input" type="checkbox" role="switch" id="save_id" name="save_id"<c:if test="${!empty cookie['save_id'].value}">checked</c:if> >
                            <label class="form-check-label" for="save_id">아이디 저장</label>
                        </div>
                        <div class="form-check form-switch col-sm-6 mb-3">
                            <input class="form-check-input" type="checkbox" role="switch" id="auto_login" name="auto_login"<c:if test="${!empty cookie['auto_login'].value}">checked</c:if> >
                            <label class="form-check-label" for="auto_login">자동 로그인</label>
                        </div>
                    </div>
                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-outline-primary">로그인</button>
                        <button type="button" class="btn btn-primary" onclick="location.href = '/member/join'">가입하기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    const serverValidResult = {};
    <c:forEach var="err" items="${errors}">
    if(document.getElementById("div_err_${err.getField()}") != null) {
        let target =  document.getElementById("div_err_${err.getField()}");
        target.innerText = "${err.getField()} 필드는 ${err.defaultMessage}";
        target.style.display = "block";
        target.previousElementSibling.classList.add('is-invalid');
    }
    serverValidResult['${err.getField()}'] = '${err.defaultMessage}';
    console.log('${err.getField()} : ${err.defaultMessage}');
    </c:forEach>
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
