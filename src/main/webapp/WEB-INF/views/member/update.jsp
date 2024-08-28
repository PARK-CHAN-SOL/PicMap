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
<style>
.card-body {
	height: auto;
	min-height: 1000px;
}
</style>
</head>
<body>

	<div class="md-content">
		<div class="container">
			<div class="card overflow-hidden">
				<div class="card-body p-0">
					<img src="/resources/assets/img/mypageback.png" alt=""
						class="img-fluid">
					<div class="row align-items-center">

						<div class="mb-3">
							<div class="mt-n5">
								<div
									class="d-flex align-items-center justify-content-center mb-2">
									<div
										class="linear-gradient d-flex align-items-center justify-content-center rounded-circle"
										style="width: 110px; height: 110px;">
										<div
											class="border border-4 border-white d-flex align-items-center justify-content-center rounded-circle overflow-hidden"
											style="width: 100px; height: 100px;">
											<c:if test="${not empty member.profilePath}">
												<img src="${member.profilePath}"
													onerror="this.src='/resources/upload/members/default.png'"
													alt="" class="w-100 h-100">
											</c:if>
											<c:if test="${empty member.profilePath}">
												<img src="/resources/upload/members/default.png" alt=""
													class="w-100 h-100">
											</c:if>
										</div>
									</div>
								</div>
									</div>
							</div>
								<form action="/member/update" method="post" enctype="multipart/form-data">
									<div class="d-flex align-items-center justify-content-center mb-2">
										<button style="width: 80px; height: 40px" class="btn btn-primary fs-5 " type="submit">수정하기</button>
									</div>


						<div class="container overflow-hidden">
							<div class="row gy-5">
								<div class="col-6 ">
									<label for="inputName" class="form-label fs-4">이름</label> <input
										type="text" class="form-control " id="inputName"
										style="width: 500px;" name="memberName"
										value="${member.memberName}">
								</div>
								<div class="col-6">
									<label for="inputPhone" class="form-label fs-4">생년월일</label> <input
										type="date" class="form-control" id="memberBirth"
										style="width: 500px;" name="memberBirth"
										value="${member.memberBirth}">
								</div>
								<div class="col-6">
									<label for="inputPhone" class="form-label fs-4">닉네임</label> <input
										type="text" class="form-control" id="memberNickName"
										style="width: 500px;" name="memberNickName"
										value="${member.memberNickName}">
								</div>
								<div class="col-6">
									<label for="inputPhone" class="form-label fs-4">이메일</label> <input
										type="email" class="form-control" id="memberEmail"
										style="width: 500px;" name="memberEmail"
										value="${member.memberEmail}">
								</div>
								<div class="col-6">
									<label for="inputPhone" class="form-label fs-4">전화번호</label> <input
										type="text" class="form-control" id="inputPhone"
										style="width: 500px;" name="memberPhone"
										value="${member.memberPhone}">
								</div>
						<div class="col-6">
									<label for="inputPhone" class="form-label fs-4">비밀번호</label> <input
										type="text" class="form-control" id="inputPhone"
										style="width: 500px;" name="memberPassword"
										value="${member.memberPassword}">
								</div>
					
							</div>

							</div>
							</form>
						</div>


		
					</div>
				</div>
			</div>
		</div>

	<c:import url="../template/footer.jsp"></c:import>
 <script type="text/javascript" src="/resources/assets/js/member/memberUpdate.js"></script>
</body>
</html>

