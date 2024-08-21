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
<style type="text/css">
/* 전체 배경 스타일 */
.hero {
    background: linear-gradient(to bottom right, #f3f4f6, #e2e8f0); /* 부드러운 그라데이션 */
    height: 80vh; /* 높이 조정 */
    display: flex;
    align-items: center; /* 수직 중앙 정렬 */
    justify-content: center; /* 수평 중앙 정렬 */
    color: #333;
    position: relative;
}

/* Hero 섹션 내의 컨테이너 스타일 */
.hero__wrapper {
      display: flex;
    align-items: center; /* 수직 중앙 정렬 */
    justify-content: space-between; /* 수평 간격 조정 */
    width: 100%;
    max-width: 1200px; /* 최대 너비 조정 */
    height: 60%; /* 높이 조정 */
    padding: 20px;
}

/* 텍스트 스타일 */
.hero-text {
       flex: 1;
    padding-right: 20px; /* 텍스트와 컨텐츠 박스 사이의 간격 좁힘 */
}

.hero-title {
    font-size: 36px;
    font-weight: 700;
    margin: 0;
    color: #2d3748;
    letter-spacing: 1px;
}

.hero-subtitle {
    font-size: 18px;
    color: #4a5568;
    margin-top: 10px;
    line-height: 1.5;
}

/* 컨텐츠 박스 스타일 */
.container-centered {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 20px;
}

.content-box {
  background-color: #ffffff;
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    width: 1000px;
    max-width: 600px; /* 크기 조정 */
    margin-left: 20px; /* 텍스트와 컨텐츠 박스 사이의 간격 좁힘 */
     font-size: 16px;
}

/* 폼 스타일 */
.table {
    width: 100%;
    border-collapse: collapse;
}

.table thead th {
    background-color: #007bff;
    color: #fff;
    padding: 10px;
    text-align: left;
}

.table tbody tr:nth-child(even) {
    background-color: #f9f9f9;
}

.table tbody td {
    padding: 10px;
}

.table tbody a {
    color: #007bff;
    text-decoration: none;
}

.table tbody a:hover {
    text-decoration: underline;
}

/* 텍스트 스타일 */
.tm-block {
    margin: 20px 0;
}

.tm-product-table-container {
    margin-top: 20px;
}

/* 에러 메시지 스타일 */
.error-message {
    color: red;
    margin-top: 10px;
    font-size: 14px;
}


</style>
</head>
<body>
<div class="hero" id="id-1">
    <div class="hero__wrapper">
        <div class="hero-text">
            <h1 class="hero-title">비밀번호</h1>
            <p class="hero-subtitle">아래에서 계정 정보를 확인하세요</p>
        </div>

<div class="container-centered">
    <div class="content-box">
        <div class="tm-block tm-block-products">
            <div class="tm-product-table-container">
                <table class="table tm-table-small tm-product-table">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">아이디</th>
                            <th scope="col">비밀번호</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td></td>
                            <td>${memberDTO.memberId}</td>
                            <td>${memberDTO.memberPassword}</td>
                            <td><a href="/">홈으로가기</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
    </div>
</div>

<c:import url="../template/footer.jsp"></c:import>
 <script type="text/javascript" src="/resources/assets/js/member/memberUpdate.js"></script>
</body>
</html>
