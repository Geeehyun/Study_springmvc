<%--
  Created by IntelliJ IDEA.
  User: wkdwl
  Date: 2024-04-17
  Time: 오전 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Join</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/WEB-INF/views/common/common.jsp" />
    <div class="container">
        <div class="card justify-content-md-center" style="width: 80%; margin: 0 auto";>
            <div class="card-header bg-primary text-white">가입하기</div>
            <div class="card-body" style="padding: 30px">
                <form action="/member/join" method="post" id="frm" class="row g-3">
                    <div class="col-12">
                        <label for="user_id" class="form-label">아이디</label>
                        <div class="input-group mb-3 col-12">
                            <input type="text" class="form-control" placeholder="아이디를 입력해주세요" aria-label="Recipient's username" aria-describedby="button-addon2"  id="user_id" name="user_id" maxlength="20" value="${memberDTO['user_id']}" >
                            <button class="btn btn-outline-primary" type="button" id="button-addon2" onclick="alert('미개발')">중복확인</button>
                        </div>
                        <div class="invalid-feedback" id="div_err_user_id"></div>
                    </div>
                    <div class="col-12">
                        <label for="name" class="form-label">이름</label>
                        <input type="text" class="form-control" id="name" name="name" maxlength="100" value="${memberDTO.name}">
                        <div class="invalid-feedback" id="div_err_name"></div>
                    </div>
                    <div class="col-12">
                        <label for="pwd" class="form-label">비밀번호</label>
                        <input type="password" class="form-control" id="pwd" name="pwd" maxlength="100" value="${memberDTO.pwd}">
                        <div class="invalid-feedback" id="div_err_pwd"></div>
                    </div>
                    <div class="col-12">
                        <label for="email" class="form-label">이메일</label>
                        <input type="email" class="form-control" id="email" name="email" maxlength="100" value="${memberDTO.email}">
                        <div class="invalid-feedback" id="div_err_email"></div>
                    </div>
                    <div class="col-12">
                        <label for="birthday" class="form-label">생년월일</label>
                        <input type="date" class="form-control" id="birthday" name="birthday" value="${memberDTO.birthday}">
                        <div class="invalid-feedback" id="div_err_birthday"></div>
                    </div>
                    <div class="col-12">
                        <legend class="col-form-label col-sm-2 pt-0">관심사항</legend>
                        <div class="col-sm-10">
                            <div class="form-check">
                                <div class="form-check">
                                    <input class="form-check-input"  type="checkbox" name="interest" id="interest_01" value="sports" <c:if test="${fn:contains(memberDTO.interest,'sports')}">checked</c:if> ><label class="form-check-label"  for="interest_01">스포츠</label>

                                </div>
                                <div class="form-check">
                                    <input class="form-check-input"  type="checkbox" name="interest" id="interest_02" value="travel" <c:if test="${fn:contains(memberDTO.interest,'travel')}">checked</c:if> ><label class="form-check-label"  for="interest_02">여행</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input"  type="checkbox" name="interest" id="interest_03" value="movie" <c:if test="${fn:contains(memberDTO.interest,'movie')}">checked</c:if> ><label class="form-check-label"  for="interest_03">영화</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input"  type="checkbox" name="interest" id="interest_04" value="music" <c:if test="${fn:contains(memberDTO.interest,'music')}">checked</c:if> ><label class="form-check-label"  for="interest_04">음악</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                        <label for="addr1" class="form-label">주소1</label>
                        <input type="text" class="form-control" id="addr1" name="addr1" maxlength="100" value="">
                    </div>
                    <div class="col-12">
                        <label for="addr2" class="form-label">주소2</label>
                        <input type="text" class="form-control" id="addr2" name="addr2" maxlength="100" value="">
                    </div>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button class="btn btn-outline-primary" type="button" onclick="location.href = '/'">취소</button>
                        <button class="btn btn-outline-primary" type="submit">제출</button>
                    </div>
                </form>
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
            if(${err.getField()!= 'user_id'}) {
                target.previousElementSibling.classList.add('is-invalid');
            } else {
                target.previousElementSibling.firstElementChild.classList.add('is-invalid');
            }

        }
        serverValidResult['${err.getField()}'] = '${err.defaultMessage}';
        console.log('${err.getField()} : ${err.defaultMessage}');
        </c:forEach>
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
</body>
</html>
