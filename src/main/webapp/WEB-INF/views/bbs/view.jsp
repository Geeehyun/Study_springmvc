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
                    <div>
                        <h5 class="card-title">제목 : ${bbsDTO.title}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">작성자 : ${bbsDTO.user_id}</h6>
                        <h6 class="card-subtitle mb-2 text-muted">출력날짜 : ${bbsDTO.display_date}</h6>
                        <h6 class="card-subtitle mb-2 text-muted">관심사항 :
                            <c:if test="${empty bbsDTO.interest}">
                                없음
                            </c:if>
                            <c:if test="${!empty bbsDTO.interest}">
                                ${bbsDTO.interest}
                            </c:if>
                        </h6>
                        <div class="card-text card mb-3 mt-3">
                            <p class="card-text p-2" >
                                ${bbsDTO.content}
                            </p>
                        </div>
                        <div class="mt-4 d-grid gap-2 d-md-flex justify-content-md-end">
                            <c:set var="linked_params">
                                <c:forEach var="key" items="${paramValues.keySet()}" varStatus="status">
                                    <c:if test="${key != 'idx'}"><c:if test="${status.first}">${key}=${param[key]}</c:if><c:if test="${! status.first}">&${key}=${param[key]}</c:if></c:if>
                                </c:forEach>
                            </c:set>
                            <button class="btn btn-outline-primary" type="button" onclick="location.href='/bbs/list?${linked_params}'">목록</button>
                            <c:if test="${sessionScope.user_id eq bbsDTO['user_id']}">
                                <button class="btn btn-outline-primary" type="button" onclick="location.href='/bbs/modify?idx=${bbsDTO.idx}'">수정</button>
                                <button class="btn btn-outline-primary" type="button" onclick="goDelete(${bbsDTO.idx}, ${bbsDTO['user_id']})">삭제</button>
                            </c:if>
                        </div>
                    </div>
                    <div class="card mt-3 bg-light p-2">
                        <form class="row g-3" action="/bbsReply/regist" method="post">
                            <input type="hidden" name="user_id" value="${sessionScope['user_id']}">
                            <input type="hidden" name="bbs_idx" value="${bbsDTO.idx}">
                            <div class="d-grid gap-2">
                                <input type="text" class="form-control" id="formGroupExampleInput" placeholder="제목을 입력해주세요" name="title" maxlength="20" required <c:if test="${empty sessionScope['user_id']}"> disabled </c:if> >
                                <textarea class="form-control" aria-label="With textarea" name="content" placeholder="내용을 입력해주세요" maxlength="100" required <c:if test="${empty sessionScope['user_id']}"> disabled </c:if> ></textarea>
                            </div>
                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <button class="btn btn-primary" type="submit" <c:if test="${empty sessionScope['user_id']}"> disabled </c:if> >등록</button>
                            </div>
                        </form>
                        <div class="list-group pt-3">
                            <c:choose>
                                <c:when test="${empty replyDTOList}">
                                    <div class="p-2 list-group-item">
                                        댓글이 없습니다.
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="replyDTO" items="${replyDTOList}" varStatus="status">
                                        <div class="p-2 list-group-item <c:if test="${status.count % 2 == 0}">list-group-item-secondary</c:if> ">
                                            <p>${replyDTO.content}</p>
                                            <small class="text-mute">
                                                제목 : ${replyDTO.title} |
                                                작성자 : ${replyDTO['user_id']} |
                                                등록일 : ${replyDTO['reg_date']}
                                            </small>
                                        </div>
                                        <c:set var="i" value="${i-1}" />
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
            </div>
    </div>
</div>
<script>
    function goDelete(idx, user_id) {
        if(confirm("삭제하시겠습니까?")) {
            let frm = document.createElement("form");
            let input = document.createElement("input");
            frm.action = "/bbs/delete";
            frm.method = "post"
            frm.id = "frm";
            input.name = "idx";
            input.value = idx;
            frm.append(input);
            input.name = "user_id";
            input.value = user_id;
            frm.append(input);
            document.body.append(frm);
            document.querySelector("#frm").submit();
        }
    }
</script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
