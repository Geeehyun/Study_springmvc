<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<div class="row">
    <%--<h1>header</h1>--%>
    <div class="container">
        <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
            <div class="col-md-3 mb-2 mb-md-0">
                <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
                    <svg xmlns="http://www.w3.org/2000/svg" width="40" height="32" class="d-block my-1" viewBox="0 0 118 94" role="img"><title>Bootstrap</title><path fill-rule="evenodd" clip-rule="evenodd" d="M24.509 0c-6.733 0-11.715 5.893-11.492 12.284.214 6.14-.064 14.092-2.066 20.577C8.943 39.365 5.547 43.485 0 44.014v5.972c5.547.529 8.943 4.649 10.951 11.153 2.002 6.485 2.28 14.437 2.066 20.577C12.794 88.106 17.776 94 24.51 94H93.5c6.733 0 11.714-5.893 11.491-12.284-.214-6.14.064-14.092 2.066-20.577 2.009-6.504 5.396-10.624 10.943-11.153v-5.972c-5.547-.529-8.934-4.649-10.943-11.153-2.002-6.484-2.28-14.437-2.066-20.577C105.214 5.894 100.233 0 93.5 0H24.508zM80 57.863C80 66.663 73.436 72 62.543 72H44a2 2 0 01-2-2V24a2 2 0 012-2h18.437c9.083 0 15.044 4.92 15.044 12.474 0 5.302-4.01 10.049-9.119 10.88v.277C75.317 46.394 80 51.21 80 57.863zM60.521 28.34H49.948v14.934h8.905c6.884 0 10.68-2.772 10.68-7.727 0-4.643-3.264-7.207-9.012-7.207zM49.948 49.2v16.458H60.91c7.167 0 10.964-2.876 10.964-8.281 0-5.406-3.903-8.178-11.425-8.178H49.948z" fill="currentColor"></path></svg>
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

        </header>
    </div>
    <%--        <div class="card">--%>
    <%--            <div class="card-header">--%>
    <%--                Featured--%>
    <%--            </div>--%>
    <%--            <div class="card-body">--%>
    <%--                <h5 class="card-title">Special title treatment</h5>--%>
    <%--                <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>--%>
    <%--                <a href="#" class="btn btn-primary">Go somewhere</a>--%>
    <%--            </div>--%>
    <%--        </div>--%>
</div>

