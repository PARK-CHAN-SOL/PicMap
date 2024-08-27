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
.content-box {
    background-color: #ffffff;
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 400px; /* 크기 조정 */
    margin-left: 20px; /* 텍스트와 컨텐츠 박스 사이의 간격 좁힘 */
}

/* 폼 스타일 */
.form-group {
    margin-bottom: 20px;
}

.form-floating {
    position: relative;
}

.form-floating input {
    border: 1px solid #ccc;
    border-radius: 4px;
    padding: 12px;
    font-size: 16px;
}

.form-floating label {
    position: absolute;
    top: 12px;
    left: 12px;
    font-size: 16px;
    color: #999;
    transition: 0.2s;
}

.form-floating input:focus ~ label,
.form-floating input:not(:placeholder-shown) ~ label {
    top: -8px;
    left: 8px;
    font-size: 12px;
    color: #007bff;
}

.btn11 {
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    padding: 12px;
    font-size: 16px;
    cursor: pointer;
    width: 100%;
    transition: background-color 0.3s;
}

.btn11:hover {
    background-color: #0056b3;
}

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
            <h1 class="hero-title">아이디 찾기</h1>
            <p class="hero-subtitle">계정을 찾기 위해 아래 정보를 입력해주세요</p>
        </div>
        <div class="content-box">
            <form class="form" action="/member/findID" method="post">
                <div class="form-group">
                    <div class="form-floating">
                        <input type="text" class="form-control" id="memberName" name="memberName" placeholder="">
                        <label for="memberName">이름</label>
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="form-floating">
                        <input type="text" class="form-control" id="memberPhone" name="memberPhone" placeholder="010-2134-1234">
                        <label for="memberPhone">휴대폰번호</label>
                    </div>
                </div>

                <button class="btn11" type="submit">아이디 찾기</button>
			<div class="find_password my-div1 fs-5 mt-5">
							<a href="/member/findPassword">비밀번호 찾기</a>
						</div>
                <c:if test="${not empty errorMessage}">
                    <div class="error-message">
                        ${errorMessage}
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</div>

<c:import url="../template/footer.jsp"></c:import>
 <script type="text/javascript" src="/resources/assets/js/member/memberUpdate.js"></script>
</body>
</html>
