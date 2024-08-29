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

<link rel="stylesheet" href="/resources/assets/css/pingSearch.css">
<link rel="stylesheet" href="/resources/assets/css/banner.css">
<style>
.mapContainer {
	margin-top: 96px;
}

@media ( min-width : 1201px) {
	.mapContainer {
		margin-top: 116px;
	}
}
</style>
<c:import url="./template/header_css.jsp"></c:import>


</head>

<body>
	<c:import url="./template/header_nav.jsp"></c:import>


	<div class="page-wrap" id="root"></div>
	<!-- Content-->
	<div class="md-content">


		<div class="container mapContainer">
			<div>
				<div class="map_wrap" style="height: calc(24vw + 254.54545px);">
					<div id="map" style="width: 100%; height: 100%;"></div>
					<div class="hAddr">
						<span id="centerAddr"></span>
					</div>
				</div>
			</div>
		</div>
		<!-- Section -->
		<section class="awe-section pd-0" id="box-search">
			<div class="box-search-wrapper">
				<div class="container">

					<!-- box-search -->
					<div class="box-search row justify-content-end">

						<!-- form-item -->



						<!-- form-item -->
						<div class="form-item">
							<input class="form-control" type="text" name="input" id="searchPing" placeholder="여행지를 입력하세요" />
						</div>
						<!-- End / form-item -->


						<!-- form-item -->


						<a class="md-btn md-btn--primary md-btn--search " href="#" id="searchButton">Search now</a>
					</div>
					<!-- End / box-search -->

				</div>
			</div>
		</section>
		<!-- End / Section -->


		<!-- Section -->
		<section class="awe-section bg-gray">
			<div class="container">
				<div class="row">
					<div class="col-md-10 col-lg-10 col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1 ">

						<!-- title -->
						<div class="title">
							<h2 class="title__title">
								Pic Map <span class='main-color'>픽맵</span>
								<br /> 인기 여행지를 골라보세요.<br />
								<span class='main-color'>Explore</span> and select your top travel with Pic Map.
							</h2>
							<p class="title__text">Discover the most sought-after spots and plan your next adventure!</p>
						</div>
						<!-- End / title -->

					</div>
					<div class="col-md-12 col-lg-12 ">
						<div class="grid-css grid-css--masonry" data-col-lg="4" data-col-md="3" data-col-sm="2" data-col-xs="1" data-gap="30">
							<div class="grid__inner">
								<div class="grid-sizer"></div>
								
								<div class="grid-item">
									<div class="grid-item__inner">
										<div class="grid-item__content-wrapper">

											<!-- box-image -->
											<div class="box-image" style ="border: 7px solid gold !important; box-shadow: 5px 5px 5px gray !important;">
												<div>
													<a class="box-image__bg" href="/travel/list?search=서울" data-effect="mfp-zoom-in"
													style="background-image: url('/resources/assets/img/image_box_1/1.jpg');"
													data-keyword="서울">
														<div class="box-image__info">
															<h4 class="box-image__country">서울</h4>
															<p class="box-image__tour">${tourList1}</p>
														</div>
													</a>
												</div>
											</div>
											<!-- End / box-image -->

										</div>
									</div>
								</div>
								
								<div class="grid-item">
									<div class="grid-item__inner">
										<div class="grid-item__content-wrapper">

											<!-- box-image -->
											<div class="box-image" style ="border: 7px solid silver !important; box-shadow: 5px 5px 5px gray !important;">
												<div>
													<a class="box-image__bg" href="/travel/list?search=제주" data-effect="mfp-zoom-in"
													style="background-image: url('/resources/assets/img/image_box_1/2.jpg');"
													data-keyword="제주">
														<div class="box-image__info">
															<h4 class="box-image__country">제주</h4>
															<p class="box-image__tour">${tourList2}</p>
														</div>
													</a>
												</div>
											</div>
											<!-- End / box-image -->

										</div>
									</div>
								</div>
								
								<div class="grid-item">
									<div class="grid-item__inner">
										<div class="grid-item__content-wrapper">

											<!-- box-image -->
											<div class="box-image" style ="border: 7px solid #CD7F32 !important; box-shadow: 5px 5px 5px gray !important;">
												<div>
													<a class="box-image__bg" href="/travel/list?search=부산" data-effect="mfp-zoom-in"
													style="background-image: url('/resources/assets/img/image_box_1/3.jpg');"
													data-keyword="부산">
														<div class="box-image__info">
															<h4 class="box-image__country">부산</h4>
															<p class="box-image__tour">${tourList3}</p>
														</div>
													</a>
												</div>
											</div>
											<!-- End / box-image -->

										</div>
									</div>
								</div>
								
								<div class="grid-item">
									<div class="grid-item__inner">
										<div class="grid-item__content-wrapper">

											<!-- box-image -->
											<div class="box-image">
												<div>
													<a class="box-image__bg" href="/travel/list?search=경주" data-effect="mfp-zoom-in"
													style="background-image: url('/resources/assets/img/image_box_1/4.jpg');"
													data-keyword="경주">
														<div class="box-image__info">
															<h4 class="box-image__country">경주</h4>
															<p class="box-image__tour">${tourList4}</p>
														</div>
													</a>
												</div>
											</div>
											<!-- End / box-image -->

										</div>
									</div>
								</div>
								
								<div class="grid-item">
									<div class="grid-item__inner">
										<div class="grid-item__content-wrapper">

											<!-- box-image -->
											<div class="box-image">
												<div>
													<a class="box-image__bg" href="/travel/list?search=강릉" data-effect="mfp-zoom-in"
													style="background-image: url('/resources/assets/img/image_box_1/5.jpg');"
													data-keyword="강릉">
														<div class="box-image__info">
															<h4 class="box-image__country">강릉</h4>
															<p class="box-image__tour">${tourList5}</p>
														</div>
													</a>
												</div>
											</div>
											<!-- End / box-image -->

										</div>
									</div>
								</div>
								
								<div class="grid-item">
									<div class="grid-item__inner">
										<div class="grid-item__content-wrapper">

											<!-- box-image -->
											<div class="box-image">
												<div>
													<a class="box-image__bg" href="/travel/list?search=전주" data-effect="mfp-zoom-in"
													style="background-image: url('/resources/assets/img/image_box_1/6.jpg');"
													data-keyword="전주">
														<div class="box-image__info">
															<h4 class="box-image__country">전주</h4>
															<p class="box-image__tour">${tourList6}</p>
														</div>
													</a>
												</div>
											</div>
											<!-- End / box-image -->

										</div>
									</div>
								</div>
								
								<div class="grid-item">
									<div class="grid-item__inner">
										<div class="grid-item__content-wrapper">

											<!-- box-image -->
											<div class="box-image">
												<div>
													<a class="box-image__bg" href="/travel/list?search=여수" data-effect="mfp-zoom-in"
													style="background-image: url('/resources/assets/img/image_box_1/7.jpg');"
													data-keyword="여수">
														<div class="box-image__info">
															<h4 class="box-image__country">여수</h4>
															<p class="box-image__tour">${tourList7}</p>
														</div>
													</a>
												</div>
											</div>
											<!-- End / box-image -->

										</div>
									</div>
								</div>
								
								<div class="grid-item">
									<div class="grid-item__inner">
										<div class="grid-item__content-wrapper">

											<!-- box-image -->
											<div class="box-image">
												<div>
													<a class="box-image__bg" href="/travel/list?search=속초" data-effect="mfp-zoom-in"
													style="background-image: url('/resources/assets/img/image_box_1/8.jpg');"
													data-keyword="속초">
														<div class="box-image__info">
															<h4 class="box-image__country">속초</h4>
															<p class="box-image__tour">${tourList8}</p>
														</div>
													</a>
												</div>
											</div>
											<!-- End / box-image -->

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</section>
		<!-- End / Section -->



		<!-- Section -->
		<section class="awe-section" id="bestBoard">
			<div class="container">

				<!-- title -->
				<div class="title title__style-02">
					<h2 class="title__title">
						요즘 뜨는 <span class='main-color'
							style="font-weight: bold; font-size: 52px;">BEST</span> 픽
					</h2>
				</div>
				<!-- End / title -->

				<div class="grid-css grid_css_style_02 grid-css--masonry"
					data-col-lg="3" data-col-md="2" data-col-sm="2" data-col-xs="1"
					data-gap="30">

					<div class="grid__inner">
						<div class="grid-sizer"></div>


						<c:forEach items="${bestList}" var="dto">
							<div class="grid-item cat1">
								<div class="grid-item__inner">
									<div class="grid-item__content-wrapper">

										<!-- box-image2 -->
										<div class="box-image2">
											<div>
												<c:if test="${empty dto.fileName}">
													<a class="box-image2__bg"
														href="/travel/detail?boardNum=${dto.boardNum}#boardStartLocation"
														style="background-image: url('/resources/upload/travels/default.png');">
													</a>
												</c:if>
												<c:if test="${not empty dto.fileName}">
													<a class="box-image2__bg"
														href="/travel/detail?boardNum=${dto.boardNum}#boardStartLocation"
														style="background-image: url('/resources/upload/travels/${dto.fileName}');">
													</a>
												</c:if>
												<div class="box-image2__info">
													<p class="box-image2__writer">${dto.memberNickname}</p>
													<p class="box-image2__title">${dto.boardTitle}</p>
												</div>
												<div class="box-image2__info_bot">
													<span class="box-image2__date">${dto.createDate}</span> <span
														class="box-image2__like">${dto.heartCount}</span>
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
					<a class="md-btn mt-60 md-btn--primary md-btn--pill "
						href="/travel/list">Explore more </a>
				</div>

			</div>
		</section>
		<!-- End / Section -->





		<div class="container">





			<!-- End / Section -->

		</div>

		<!-- Section -->

		<!-- End / Section -->


		<!-- Section -->

		<!-- End / Section -->

		<!-- title -->
		<div class="title title__style-03">
			<h5 class="title__title_small">Pic Map 2024</h5>
			<div id="wrap">
				<div class="wrap-box">
					<div class="rolling-list">
						<div class="list">Moments from your travels leave a special
							mark on our hearts, capturing the essence of your journey.</div>
						<div class="list">Moments from your travels leave a special
							mark on our hearts, capturing the essence of your journey.</div>
						<div class="list">Moments from your travels leave a special
							mark on our hearts, capturing the essence of your journey.</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End / title -->

	</div>
	<!-- End / Content-->

	<c:import url="./template/footer.jsp"></c:import>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e11955080502d1bac37823f6b7f43b6&libraries=services"></script>
	<script src="/resources/assets/js/ping/pingList.js"></script>
	<script src="/resources/assets/js/banner.js"></script>
	<script src="/resources/assets/js/board/tourApi.js"></script>
</body>
</html>

