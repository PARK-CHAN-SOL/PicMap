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















			<!-- Section -->
			<section class="awe-section">
				<div class="container">

					<!-- title -->
					<div class="title title__style-02">
						<h2 class="title__title">${board}게시판</h2>
					</div>
					<!-- End / title -->

					<div class="grid-css grid_css_style_02 grid-css--masonry"
						data-col-lg="3" data-col-md="2" data-col-sm="2" data-col-xs="1"
						data-gap="30">
						<div class="filter">
							<ul class="filter__list">
								<li><a href="#" data-filter="*">All</a></li>
								<li><a href="#" data-filter=".cat1">추천순</a></li>
								<li><a href="#" data-filter=".cat2">최신순</a></li>
							</ul>
						</div>
						<div class="grid__inner">
							<div class="grid-sizer"></div>
							
							
							<c:forEach items="${list}" var="dto">
								<div class="grid-item cat1">
									<div class="grid-item__inner">
										<div class="grid-item__content-wrapper">
	
											<!-- box-image2 -->
											<div class="box-image2">
												<div>
													<a class="box-image2__bg" href="#"
													style="background-image: url('/resources/assets/img/image_box_2/3.jpg');">
														<img src="/resources/assets/img/image_box_2/1.jpg" alt="" />
													</a>
													<div class="box-image2__info">
														<p class="box-image2__writer">${dto.memberNickname}</p>
														<p class="box-image2__title">${dto.boardTitle}</p>
													</div>
													<div class="box-image2__info_bot">
														<span class="box-image2__date">${dto.createDate}</span>
														<span class="box-image2__like">좋아요</span> 
													</div>
												</div>
											</div>
											<!-- End / box-image2 -->
	
										</div>
									</div>
								</div>
							</c:forEach>


						</div>
					</div>
					<div class="text-center">
						<a class="md-btn mt-60 md-btn--primary md-btn--pill " href="#">Explore
							more </a>
					</div>
					
					<h1>
						<a href="add">${board}게시글 작성</a>
					</h1>
					
				</div>
			</section>
			<!-- End / Section -->























		</div>
		<!-- End / Content-->
	</div>

	<c:import url="../../template/footer.jsp"></c:import>
</body>
</html>

