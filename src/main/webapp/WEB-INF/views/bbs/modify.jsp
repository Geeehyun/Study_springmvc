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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Modify</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/WEB-INF/views/common/common.jsp" />
    <div class="container">
        <div class="card justify-content-md-center" style="width: 80%; margin: 0 auto";>
            <div class="card-header bg-primary text-white">게시글 수정</div>
            <div class="card-body" style="padding: 30px">
                <form action="/bbs/modify" method="post" id="frm" class="row g-3">
                    <input type="hidden" name="idx" id="idx" value="${bbsDTO.idx}">
                    <div class="col-12">
                        <label for="user_id" class="form-label">아이디</label>
                        <input type="text" class="form-control" id="user_id" name="user_id" maxlength="20" value="${sessionScope['user_id']}" readonly>
                    </div>
                    <div class="col-12">
                        <label for="title" class="form-label">제목<span class="text-danger"> *</span></label>
                        <input type="text" class="form-control" id="title" name="title" maxlength="100" value="${bbsDTO.title}">
                        <div class="invalid-feedback" id="div_err_title"></div>
                    </div>
                    <div class="col-12">
                        <label for="content" class="form-label">내용<span class="text-danger"> *</span></label>
                        <textarea class="form-control" aria-label="With textarea" id="content" name="content"  rows="15">${bbsDTO.content}</textarea>
                        <div class="invalid-feedback" id="div_err_content"></div>
                    </div>
                    <div class="col-12">
                        <label for="display_date" class="form-label">출력날짜<span class="text-danger"> *</span></label>
                        <input type="date" class="form-control" id="display_date" name="display_date" value="${bbsDTO.display_date}">
                        <div class="invalid-feedback" id="div_err_display_date"></div>
                    </div>
                    <div class="col-12">
                        <legend class="col-form-label col-sm-2 pt-0">관심사항</legend>
                        <div class="col-sm-10">
                            <div class="form-check">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="interest" id="interest_01" value="sports" <c:if test="${fn:contains(bbsDTO.interest,'sports')}">checked</c:if> ><label class="form-check-label"  for="interest_01">스포츠</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="interest" id="interest_02" value="travel" <c:if test="${fn:contains(bbsDTO.interest,'travel')}">checked</c:if> ><label class="form-check-label"  for="interest_02">여행</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="interest" id="interest_03" value="movie" <c:if test="${fn:contains(bbsDTO.interest,'movie')}">checked</c:if> ><label class="form-check-label"  for="interest_03">영화</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="interest" id="interest_04" value="music" <c:if test="${fn:contains(bbsDTO.interest,'music')}">checked</c:if> ><label class="form-check-label"  for="interest_04">음악</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div  class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button class="btn btn-primary" type="submit">제출</button>
                        <button class="btn btn-outline-primary" type="button" onclick="location.href = '/bbs/view?idx=${bbsDTO.idx}'">취소</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
</body>
</html>
