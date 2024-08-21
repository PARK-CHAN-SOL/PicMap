<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header -->

<header class="header awe-skin-dark header--fixed ">
	<div class="container-fluid pd-0">
		<div class="header__inner">
			<div class="header__logo_menu_wrap">
				<div class="header__logo">
					<a href="/"><img src="/resources/assets/img/bg/logopmSize.png"
						alt="" /></a>
				</div>
				<div class="header__menu">

					<!-- onepage-nav -->
					<nav class="onepage-nav">

						<!-- onepage-menu -->
						<ul class="onepage-menu mt-4">
							<li><a href="/">🏠홈</a></li>
							<li><a href="/notice/list">📌공지사항</a></li>
							<li><a href="/#bestBoard">💡베스트 리뷰</a></li>
							<li><a href="/travel/list">📢여행 리뷰</a></li>
							<c:if test="${not empty sessionScope.member}">
								<li><a
									href="/member/mypage?memberNum=${sessionScope.member.memberNum}">✨마이페이지</a></li>
							</c:if>

						</ul>



						<div class="navbar-toggle">
							<span></span><span></span><span></span>
						</div>
					</nav>
					<!-- End / onepage-nav -->

				</div>
			</div>
			<div class="header__hotline_book_wrap">
				<div class="header__lang">

			<div class="header__booking me-4">
					<c:if test="${empty sessionScope.member}">
						<p>로그인을 해주세요     </p>
					</c:if>
				<c:if test="${not empty sessionScope.member}">
    <ul>
        <li>
            <a href="/member/mypage?memberNum=${sessionScope.member.memberNum}" style="color: white; text-decoration: none; display: flex; align-items: center; margin-left: +0px;" class="me-4">
                <span style="margin-right: 10px;">${sessionScope.member.memberName}님</span>
                <c:if test="${not empty sessionScope.member.profilePath}">
                    <img id="profileImage" src="${sessionScope.member.profilePath}" onerror="this.src='/resources/upload/members/default.png'" alt="" style="max-width: 96px; height: 48px;" class="border border-2 border-white rounded-circle">
                </c:if>
                <c:if test="${empty sessionScope.member.profilePath}">
                    <img id="profileImage" src="/resources/upload/members/default.png" alt="" style="max-width: 96px; height: 48px;" class="border border-2 border-white rounded-circle">
                </c:if>
            </a>
        </li>
    </ul>
</c:if>
				</div>
			</div>
				<c:if test="${empty sessionScope.member}">
					<button type="button" class="md-btn md-btn--primary md-btn--pill "
						data-bs-toggle="modal" data-bs-target="#exampleModal">로그인</button>
				</c:if>
				<c:if test="${not empty sessionScope.member}">
					<button type="button" class="md-btn md-btn--primary md-btn--pill "
						onclick="location.href='/member/logout' ">로그아웃</button>
				</c:if>
			</div>

			<!-- Button trigger modal -->


		</div>
	</div>
	</div>
</header>

<!-- Modal -->
<div class="modal fade" tabindex="-1" id="exampleModal"
	aria-labelledby="exampleModalLabel" aria-hidden="true"
	data-bs-backdrop="static" data-bs-keyboard="false"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">

			<div class="modal-header mb-5">
				<h5 class="fs-1 text-center" style="width: 1000px; height: px;">로그인</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>


			<div class="modal-body">
				<div class="member_login">
					<form action="/member/login" method="POST">
						<!--           <form   action="/member/login" method="post" > -->
						<div class="member_login_input my-div fs-4">
							<input type="text" name="memberId" placeholder="아이디"
								style="border-radius: 30px; width: 400px; height: px;">
						</div>

						<div class="member_login_input my-div">
							<input type="password" name="memberPassword" placeholder="비밀번호"
								style="border-radius: 30px; width: 400px">
						</div>

						<div class="member_login_btn  my-div">

							<input type="submit" style="width: 80px; height: 40px"
								class="btn btn-primary fs-5 me-5" id="btn-login" value="로그인">

							<input type="button" id="joinBtn" data-bs-toggle="modal"
								data-bs-dismiss="modal" data-bs-target="#exampleModal1"
								style="width: 80px; height: 40px" class="btn btn-primary fs-5"
								value="회원가입">

						</div>

						<div class="find_password my-div1 fs-5">
							<a href="/member/findID">아이디 또는 비밀번호를 잊으셨나요?</a>
						</div>

						<div class="login_api my-div">
							<a
								href="https://kauth.kakao.com/oauth/authorize?client_id=580555887802ff728f2d9f964d6ad050&redirect_uri=http://localhost/auth/kakao/callback&response_type=code"><img
								src="/resources/assets/img/kakao_login_medium_narrow.png"></a>
						</div>


					</form>
				</div>
			</div>

		</div>
	</div>
</div>


<!-- Modal -->
<div class="modal fade" tabindex="-1" id="exampleModal1"
	aria-labelledby="exampleModalLabel" aria-hidden="true"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg modal-dialog-centered">
		<div class="modal-content">

			<div class="modal-header mb-5">
				<h5 class="fs-1 text-center" style="width: 1000px; height: px;">회원가입</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>


			<div class="modal-body">
				<form action="/member/join" method="POST" id="frm"
					enctype="multipart/form-data">
					<div class="mb-3">
						<label for="formFileSm" class="form-label">프로필 사진</label> <input
							class="form-control form-control-sm" type="file" name="files"
							onchange="readURL(this);"> <br />
						<br /> <img id="preview"
							style="width: 20%; height: 20%; object-fit: cover;" />
					</div>
					<div id="result"></div>

					<div class="col-md-6">
						<label for="inputEmail4" class="form-label fs-4">이름</label> <input
							type="text" class="form-control" name="memberName"
							id="memberName">
					</div>
					<div class="col-md-6">
						<label for="inputPassword4" class="form-label fs-4">아이디</label> <input
							type="text" class="form-control" name="memberId" id="memberId"
							placeholder="※8-16자 소문자+숫자">
					</div>
					<div class="col-md-6">
						<label for="inputEmail4" class="form-label fs-4">비밀번호</label> <input
							type="password" class="form-control" name="memberPassword"
							id="memberPassword" placeholder="6글자 이상">
						<div id="password-error"></div>
						<div class="invalid-feedback" data-sb-feedback="name:required">Password
							is required.</div>
					</div>
					<div class="col-md-6">
						<label for="inputPassword4" class="form-label fs-4">비밀번호
							확인</label> <input type="password" class="form-control"
							id="memberPasswordCheck" placeholder="동일한 비밀번호 입력">
						<div id="password-eqError"></div>
						<div class="invalid-feedback" data-sb-feedback="name:required">Password
							is required.</div>
					</div>

					<div class="col-md-6 ">
						<label for="inputEmail4" class="form-label fs-4">닉네임</label> <input
							type="text" class="form-control " name="memberNickName"
							id="memberNickName" placeholder="중복 가능">
					</div>
					<div class="col-md-6">
						<label for="inputPassword4" class="form-label fs-4">Email</label>
						<input type="email" class="form-control" name="memberEmail"
							id="memberEmail" placeholder="test@naver.com">
					</div>
					<div class="col-md-6">
						<label for="inputEmail4" class="form-label fs-4">생년월일</label> <input
							type="date" class="form-control" name="memberBirth"
							id="memberBirth">
					</div>
					<div class="col-md-6 mb-5">
						<label for="inputPassword4" class="form-label fs-4">휴대폰
							번호</label> <input type="text" class="form-control" name="memberPhone"
							id="memberPhone" placeholder="010-1234-5678">
					</div>

					<div class="col-md-6">
						<input type="button" style="width: 100px; height: 40px"
							class="btn btn-primary fs-5 me-5 mw-3" id="btn" value="회원가입하기">
					</div>

				</form>
			</div>

		</div>
	</div>
</div>
<!-- End / header -->
<script type="text/javascript"
	src="/resources/assets/js/member/memberJoinCheck.js"></script>