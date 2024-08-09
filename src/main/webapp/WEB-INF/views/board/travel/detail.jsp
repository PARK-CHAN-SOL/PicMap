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
<c:import url="../../template/header_css.jsp"></c:import>
<link rel="stylesheet" href="/resources/assets/css/boardDetail.css">

</head>

<body>
	<div class="page-wrap" id="root">

		<c:import url="../../template/header_nav.jsp"></c:import>

		<!-- Content-->
		<div class="md-content">

			<!-- hero -->
			<div class="hero" id="id-1">
				<div class="hero__wrapper">

					<!-- swiper__module swiper-container -->
					<div
						class="swiper__module swiper-container awe-skin-dark hero__main_slider"
						data-options='{"spaceBetween":0}'>
						<div class="swiper-wrapper">
							<div class="hero__item"
								style="background-image: url('/resources/assets/img/hero/1.jpg');">
								<img src="/resources/assets/img/hero/1.jpg" alt="" />
								<div class="hero__box_info">
									<div class="container">
										<h2 class="hero__title">지도</h2>
										<p class="hero__info">
											<span>3 Day 2 Night</span><span>Tokyo</span><span>709
												Review</span>
										</p>
									</div>
								</div>
							</div>

						</div>
						<div class="swiper-button-custom">
							<div class="swiper-button-prev-custom"></div>
							<div class="swiper-button-next-custom"></div>
						</div>
					</div>
					<!-- End / swiper__module swiper-container -->
				</div>
			</div>
			<!-- End / hero -->
			
			
			
			
			
			
			

		
		    <div>
		        <div class="nav-buttons">
		            <button type="button" class="btn btn-outline-secondary" id="detailPrevPageBtn">&laquo;</button>
		            <button type="button" class="btn btn-outline-secondary" id="detailNextPageBtn">&raquo;</button>
		        </div>
		    </div>			
			
			
			





			<!-- Section -->
			<section class="awe-section">
				<div class="container">
					<div class="row">

						<!-- 게시글 제목 -->
						<div
							class="col-md-10 col-lg-10 col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1 ">

							<!-- title -->
							<div class="boardTitle">
								<h2 class="boardTitle__title">${dto.boardTitle}</h2>
							</div>
							<!-- End / title -->

						</div>



						<!-- 게시글 전체 내용 -->
						<div class="col-md-12 col-lg-12 ">

							<div class="boardContent">

								<!-- 게시한 사진 -->
								<div class="picture">
									<c:if test="${empty dto.fileName}">
										<img src="/resources/upload/travels/default.png" alt=""
											class="img-fluid" />
									</c:if>
									<c:if test="${not empty dto.fileName}">
										<img src="/resources/upload/travels/${dto.fileName}" alt=""
											class="img-fluid" />
									</c:if>
								</div>










								<!-- 여기에 추천 관광지 넣으시면 됩니다 -->










								<!-- 작성자, 게시글 정보 -->
								<div class="boardInfo" style="margin-top: 35px;">

									<div class="d-flex align-items-center justify-content-between"
										style="width: 100%;">

										<div class="d-flex align-items-center">
											<span class="linear-gradient d-flex align-items-center justify-content-center rounded-circle"
											style="width: 70px; height: 70px; display: inline-block;">
												<span class="border border-4 border-white d-flex align-items-center justify-content-center rounded-circle overflow-hidden"
												style="width: 60px; height: 60px;">
													<c:if test="${not empty member.profilePath}">
														<img src="/resources/upload/members/${member.profilePath}"
														onerror="this.src='/resources/upload/members/default.png'" alt="" class="w-100 h-100">
													</c:if>
													<c:if test="${empty member.profilePath}">
														<img src="/resources/upload/members/default.png" alt="" class="w-100 h-100">
													</c:if>
												</span>
											</span>
											
											<span style="margin-left: 10px;">${member.memberNickName}</span>
											<span style="margin-left: 10px;"> • ${dto.writeDate}</span>
										</div>

										<span style="text-align: right;">좋아요(추후 수정)</span>

									</div>

								</div>


								<!-- 게시글 텍스트 내용 -->
								<div class="boardContent_text" style="margin-top: 30px;">
									<div style="margin-left: 10px;">${dto.boardContent}</div>
								</div>


							</div>


						</div>







					</div>
				</div>
			</section>
			<!-- End / Section -->
















		</div>
		<!-- End / Content-->
	</div>

	<c:import url="../../template/footer.jsp"></c:import>
	
	
	
	
	
	
	
	
</body>
</html>

