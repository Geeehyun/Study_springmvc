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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <div class="card p-3 mb-2 bg-light">
                    <div class="container">
                        <form class="d-grid gap-3">
                            <div>
                                <div class="d-flex justify-content-between" style="align-items: center">
                                    <div class="form-check" style="width: 100px">
                                        <input class="form-check-input" type="checkbox" value="t" id="search_type1" name="search_type" <c:if test="${(responseDTO['search_type'] == null) || (fn:contains(responseDTO['search_type_st'],'t'))}">checked</c:if> >
                                        <label class="form-check-label" for="search_type1">
                                            제목
                                        </label>
                                    </div>
                                    <div class="form-check" style="width: 100px">
                                        <input class="form-check-input" type="checkbox" value="u" id="search_type2" name="search_type" <c:if test="${(responseDTO['search_type'] == null) || (fn:contains(responseDTO['search_type_st'],'u'))}">checked</c:if> >
                                        <label class="form-check-label" for="search_type2">
                                            작성자
                                        </label>
                                    </div>
                                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search_word" value="${responseDTO['search_word']}">
                                </div>
                            </div>
                            <div>
                                <div  class="d-flex justify-content-between">
                                    <input class="form-control me-2" type="date" name="search_data1" value="${responseDTO['search_data1']}">
                                    <span>&nbsp;&nbsp;~&nbsp;&nbsp;</span>
                                    <input class="form-control me-2" type="date" name="search_data2" value="${responseDTO['search_data2']}">
                                </div>
                            </div>
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary mt-2" type="submit">검색</button>
                                <button class="btn btn-outline-primary mt-2" type="button" onclick="location.href = '/bbs/list'">초기화</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div>
                    총 ${responseDTO.total_count} 건
                </div>
                <div class="list-group ">
                    <c:choose>
                        <c:when test="${empty responseDTO.dtoList}">
                        <div class="p-2 list-group-item list-group-item-action">
                            게시글이 없습니다.
                        </div>
                        </c:when>
                        <c:otherwise>
                            <c:set var="i" value="${responseDTO['total_count'] - ((responseDTO['page_block_size'])* (responseDTO.page - 1))}" />
                            <c:forEach var="bbsDTO" items="${responseDTO.dtoList}" varStatus="status">
                                <div class="p-2 list-group-item list-group-item-action <c:if test="${status.count % 2 == 0}">list-group-item-secondary</c:if> " onclick="location.href = '/bbs/view${responseDTO['linked_params']}&page=${param.page}&idx=${bbsDTO.idx}'">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h6>${bbsDTO.title} ( ${bbsDTO.replyCnt} )</h6>
                                    </div>
                                    <small class="text-mute">
                                        NO :  ${i} |
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
                                <c:set var="i" value="${i-1}" />
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                </div>
                <nav class="mt-3" aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <li class="page-item <c:if test="${!responseDTO.prev_page_flag}">disabled</c:if>">
                            <a class="page-link" href="${responseDTO.linked_params}&page=${((responseDTO.page - responseDTO.page_block_size) >= 1) ? (responseDTO.page - responseDTO.page_block_size) : 1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach var="li" begin="${responseDTO.page_block_start}" end="${responseDTO.page_block_end}">
                            <li class="page-item <c:if test="${responseDTO.page eq li}">active</c:if> ">
                                <a class="page-link" href="${responseDTO.linked_params}&page=${li}">${li}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item <c:if test="${!responseDTO.next_page_flag}">disabled</c:if>">
                            <a class="page-link" href="${responseDTO.linked_params}&page=${(responseDTO.page + responseDTO.page_block_size) <= responseDTO.total_page ? (responseDTO.page + responseDTO.page_block_size) : responseDTO.total_page}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
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
