<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
    <%--<h1>header</h1>--%>

<header class="border-bottom mb-4 ">
    <div class="container d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 ">
    <div class="col-md-3 mb-2 mb-md-0">
        <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
            <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-house-heart-fill" viewBox="0 0 16 16">
                <path d="M7.293 1.5a1 1 0 0 1 1.414 0L11 3.793V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v3.293l2.354 2.353a.5.5 0 0 1-.708.707L8 2.207 1.354 8.853a.5.5 0 1 1-.708-.707z"/>
                <path d="m14 9.293-6-6-6 6V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5zm-6-.811c1.664-1.673 5.825 1.254 0 5.018-5.825-3.764-1.664-6.691 0-5.018"/>
            </svg>
        </a>
    </div>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="/" class="nav-link px-2 link-secondary">홈으로</a></li>
        <li><a href="/bbs/list" class="nav-link px-2">게시판</a></li>
        <li><a href="/bbs/regist" class="nav-link px-2">게시글 등록</a></li>
    </ul>
    <c:choose>
        <c:when test="${empty sessionScope['user_id']}">
            <div class="col-md-3 text-end">
                <button type="button" class="btn btn-outline-primary me-2" onclick="location.href = '/login/login'">로그인</button>
                <button type="button" class="btn btn-primary" onclick="location.href = '/member/join'">회원가입</button>
            </div>
            <script>
                if(${!empty cookie['auto_login'].value}) {
                    let frm = document.createElement("form");
                    let input = document.createElement("input");
                    frm.action = "/login/autoLogin";
                    frm.method = "post"
                    frm.id = "frm_auto_login";
                    input.name = "auto_login";
                    input.value = "${cookie['auto_login'].value}";
                    frm.append(input);
                    document.body.append(frm);
                    document.querySelector("#frm_auto_login").submit();
                }
            </script>
        </c:when>
        <c:otherwise>
            <div style="display: flex; justify-content: flex-end; align-items: baseline;" class="col-md-3 text-end">
                <button type="button" class="btn btn-primary me-2" onclick="location.href = '/member/view'">${sessionScope['user_id']}님</button>
                <button type="button" class="btn btn-outline-primary me-2" onclick="location.href = '/login/logout'">로그아웃</button>
            </div>
        </c:otherwise>
    </c:choose>
    </div>
</header>

