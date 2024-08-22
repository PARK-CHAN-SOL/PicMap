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
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">

<c:import url="../template/header_css.jsp"></c:import>
<c:import url="../template/header_nav.jsp"></c:import>
<link rel="stylesheet" href="/resources/assets/css/mypage.css">
<link rel="stylesheet" href="/resources/assets/css/pingSearch.css">
<link rel="stylesheet" href="/resources/assets/css/gridSave.css">


</head>

<body>

	<div class="md-content">

		<div class="container">

			<div class="card overflow-hidden">
				<div class="p-0">
					<div>
						<img id="mypageBack" src="/resources/assets/img/mypageback.png"
							alt="" class="img-fluid">
						<div id="mapWrap" class="map_wrap img-fluid"
							style="display: none; height: calc(18vw + 254.54545px);">
							<div id="map" style="width: 100%; height: 100%;"></div>
							<div class="hAddr">
								<span id="centerAddr"></span>
							</div>
						</div>
					</div>
					<div class="row align-items-center">
						<div class="col-lg-4 order-lg-1 order-2">
							<div class="d-flex align-items-center justify-content-around m-4">


								<div id="followerDiv" class="text-center" data-bs-toggle="modal"
									data-bs-target="#staticBackdrop"
									data-to-follow="${param.memberNum}">
									<i class="fa fa-user fs-6 d-block mb-2"></i>
									<h4 id="follower" class="mb-0 fw-semibold lh-1">${follower}</h4>
									<p class="mb-0 fs-4">팔로워</p>
								</div>

								<!-- Modal -->
								<div class="modal fade" id="staticBackdrop"
									data-bs-backdrop="static" data-bs-keyboard="false"
									tabindex="-1" aria-labelledby="staticBackdropLabel"
									aria-hidden="true">
									<div
										class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
										<div class="modal-content">
											<div class="modal-header">
												<h1 class="fs-3 text-center"
													style="width: 1000px; height: px;" id="staticBackdropLabel">팔로워</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<main id="explore">
													<div>
														<ul class="explore__users u-default-box" id="followerList"></ul>
													</div>

												</main>
												<div id="followerObserverTarget" data-start-row="1"
													data-end-row="12"></div>
											</div>

										</div>
									</div>
								</div>
								<!------------------------------------------------------------------------------------------>
								<div id="followingDiv" class="text-center"
									data-bs-toggle="modal" data-bs-target="#staticBackdrop1"
									data-from-follow="${param.memberNum}">
									<i class="fa fa-check fs-6 d-block mb-2"></i>
									<h4 id="following" class="mb-0 fw-semibold lh-1">${following}</h4>
									<p class="mb-0 fs-4">팔로잉</p>
								</div>

								<div class="modal fade" id="staticBackdrop1"
									data-bs-backdrop="static" data-bs-keyboard="false"
									tabindex="-1" aria-labelledby="staticBackdropLabel"
									aria-hidden="true">
									<div
										class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
										<div class="modal-content">
											<div class="modal-header">
												<h1 class="fs-3 text-center"
													style="width: 1000px; height: px;" id="staticBackdropLabel">팔로잉</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<main id="explore">
													<div>
														<ul class="explore__users u-default-box"
															id="followingList"></ul>
													</div>

												</main>
												<div id="followingObserverTarget" data-start-row="1"
													data-end-row="12"></div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 mt-n3 order-lg-2 order-1">
							<div class="mt-n5">
								<div
									class="d-flex align-items-center justify-content-center mb-2">
									<!-- 기존 프로필 이미지 -->
								
 					<div class="linear-gradient d-flex align-items-center justify-content-center rounded-circle" style="width: 110px; height: 110px; z-index: 10;" data-bs-toggle="modal" data-bs-target="#profileImageModalNew">
	<div class="border border-4 border-white d-flex align-items-center justify-content-center rounded-circle overflow-hidden" style="width: 100px; height: 100px; position: relative; cursor: pointer;">

		<c:if test="${not empty member.profilePath}">
			<img id="profileImage" src="${member.profilePath}"
				onerror="this.src='/resources/upload/members/default.png'"
				alt="" class="w-100 h-100">
		</c:if>
		<c:if test="${empty member.profilePath}">
			<img id="profileImage"
				src="/resources/upload/members/default.png" alt=""
				class="w-100 h-100">
		</c:if>
	</div>
</div>

<!-- 새 모달 구조 -->
<c:if test="${sessionScope.member.memberNum eq member.memberNum}">
<div class="modal fade" id="profileImageModalNew" tabindex="-1" aria-labelledby="profileImageModalNewLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content position-relative"> <!-- position-relative 추가 -->
           <div class="modal-header">
        <h5 class="modal-title">프로필 이미지 변경</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
            <form action="/member/proFileUpdate" method="post" enctype="multipart/form-data">
                <div class="col-6">
                
           <input class="form-control form-control-sm" type="file"style="width: 500px;" name="files" onchange="readURL(this);">
                 
                  	<c:choose>
                  		<c:when test="${member.profilePath != null}">
                  		
                  	<input type="hidden" name="profilePath" value="${member.profilePath}">
		<img id="previewProfile" class="mt-3" style="width: 100%; height: 100%; object-fit: cover;" src="${member.profilePath}"/>
                  		</c:when>
                  		<c:otherwise>
                  					<img id="previewProfile" style="width: 20%; height: 20%; object-fit: cover;" />
                  		</c:otherwise>
                  	</c:choose>
        
									<br /> <br />
								</div>
       <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">이미지 변경</button>
      </div>
            </form>
        </div>
    </div>
</div>
</c:if>


								</div>
								<div class="text-center">
									<h5 class="fs-5 mb-0 fw-semibold">${member.memberNickName}</h5>
									<p class="mb-0 fs-4">${member.memberEmail}</p>
								</div>
							</div>
						</div>
						<div class="col-lg-4 order-last">
							<ul
								class="list-unstyled d-flex align-items-center justify-content-center justify-content-lg-start my-3 gap-3">
								<c:if
									test="${sessionScope.member.memberNum ne member.memberNum}">
									<c:if test="${followCheck eq 1}">
										<li>
											<button class="follow_btn fs-4" type="button" id="follow"
												data-to-follow="${param.memberNum}">팔로우</button>
										</li>
									</c:if>
									<c:if test="${followCheck eq -1}">
										<li>
											<button class="following_btn fs-4" type="button" id="follow"
												data-to-follow="${param.memberNum}">팔로잉</button>
										</li>
									</c:if>
								</c:if>

								<c:if
									test="${sessionScope.member.memberNum eq member.memberNum}">
									<li><a href="/travel/add"><button
												class="btn btn-secondary fs-4">게시글 쓰기</button></a></li>

									<li><a href="/member/delete"><button
												class="btn btn-danger fs-4">탈퇴하기</button></a></li>
									<!-- 조건에 맞지 않는 경우에만 버튼을 표시 -->
									<c:choose>
										<c:when
											test="${ !sessionScope.member.memberId.matches('[0-9]*')}">
											<li><a href="/member/update"><button
														class="btn btn-secondary fs-4">프로필 편집</button></a></li>
										</c:when>
										<c:otherwise>
											<!-- 버튼을 보이지 않도록 설정: 아무 것도 출력하지 않음 -->
										</c:otherwise>
									</c:choose>
								</c:if>
							</ul>
							<div id="wishResult"></div>
						</div>
					</div>
					<ul
						class="nav nav-pills user-profile-tab justify-content-end mt-2 bg-light-info rounded-2"
						id="pills-tab" role="tablist">
						<li class="nav-item" role="presentation">
							<button
								class="nav-link position-relative rounded-0 active d-flex align-items-center justify-content-center bg-transparent fs-3 py-6"
								id="pills-profile-tab" data-bs-toggle="pill"
								data-bs-target="#pills-profile" type="button" role="tab"
								aria-controls="pills-profile" aria-selected="true">
								<i class="fa fa-user me-2 fs-6"></i> <span
									class="d-none d-md-block">내 게시글</span>
							</button>
						</li>
						<c:if test="${sessionScope.member.memberNum eq member.memberNum}">
							<li class="nav-item" role="presentation">
								<button
									class="nav-link position-relative rounded-0 d-flex align-items-center justify-content-center bg-transparent fs-3 py-6"
									id="pills-friends-tab" data-bs-toggle="pill"
									data-bs-target="#pills-friends" type="button" role="tab"
									aria-controls="pills-friends" aria-selected="false"
									tabindex="-1">
									<i class="fa fa-users me-2 fs-6"></i> <span
										class="d-none d-md-block">저장한 글</span>
								</button>
							</li>
						</c:if>
					</ul>

					<div class="tab-content" id="pills-tabContent">
						<!-- 내 게시글 -->
						<div class="tab-pane fade show active" id="pills-profile"
							role="tabpanel" aria-labelledby="pills-profile-tab">
							<!-- 내 게시글 내용 -->
							<div class="grid-css grid_css_style_02 grid-css--masonry"
								data-col-lg="3" data-col-md="2" data-col-sm="2" data-col-xs="1"
								data-gap="30">
								<div class="grid__inner">
									<div class="grid-sizer"></div>
								</div>
								<div id="mypageObserverTarget" data-start-row="1"
									data-end-row="9" data-total-count="${postCount}"></div>
							</div>
						</div>
						<!-- 저장한 글 -->
						<div class="tab-pane fade" id="pills-friends" role="tabpanel"
							aria-labelledby="pills-friends-tab" tabindex="0">
							<!-- 저장한 글 내용 -->
							<div class="grid-css grid_css_style_02 grid-css--masonry"
								data-col-lg="3" data-col-md="2" data-col-sm="2" data-col-xs="1"
								data-gap="30">
								<div class="grid__innerSave">
									<div class="grid-sizerSave"></div>
								</div>
								<div id="mypageObserverTargetSave" data-start-row="1"
									data-end-row="9" data-total-count="${savePostCount}"
									style="display: none;"></div>
							</div>
						</div>
						<script>
							// Bootstrap의 탭 기능 초기화
							const triggerTabList = [].slice.call(document
									.querySelectorAll('#pills-tab button'))
							triggerTabList.forEach(function(triggerEl) {
								const tabTrigger = new bootstrap.Tab(triggerEl)

								triggerEl.addEventListener('click', function(
										event) {
									event.preventDefault()
									tabTrigger.show()
								})
							})
						</script>

					</div>

</div>
</div>
</div>

					<c:import url="../template/footer.jsp"></c:import>
					<script type="text/javascript"
						src="/resources/assets/js/member/memberFollow.js"></script>
 <script type="text/javascript" src="/resources/assets/js/member/memberUpdate.js"></script>
					<script type="text/javascript"
						src="/resources/assets/js/member/myboardList.js"></script>
					<script type="text/javascript"
						src="/resources/assets/js/member/mySaveList.js"></script>
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e11955080502d1bac37823f6b7f43b6&libraries=services"></script>
					<script src="/resources/assets/js/ping/mypagePingList.js"></script>
</body>

</html>