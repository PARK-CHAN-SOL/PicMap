<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<!--
Mercury travel - free HTML5 templates!
by Awe7 (http://awe7.com/freebies)
-->
<link rel="stylesheet" href="/resources/assets/css/mypage.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<c:import url="../template/header_css.jsp"></c:import>
<c:import url="../template/header_nav.jsp"></c:import>
</head>
<body>
    <form class="form" action="/memberI/findID" method="post">
        <h1 class="h3 mb-3 fw-normal">아이디를 잊어버리셨나요? 유감이에요</h1>

        <div class="form-floating">
            <input type="text" class="form-control" id="user_name" name="user_name" placeholder="">
            <label for="user_name">이름</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="user_email" name="user_email" placeholder="">
            <label for="user_email">이메일</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="user_phone" name="user_phone" placeholder="">
            <label for="user_phone">전화번호</label>
        </div>

        <button class="btn btn-primary w-100 py-2" type="submit">아이디 찾기</button>

        <c:if test="${not empty errorMessage}">
            <div style="color: red; margin-top: 10px;">
                ${errorMessage}
            </div>
        </c:if>
    </form>
<c:import url="../template/footer.jsp"></c:import>
 <script type="text/javascript" src="/resources/assets/js/member/memberUpdate.js"></script>
</body>
</html>
