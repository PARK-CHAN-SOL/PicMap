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
<c:import url="../template/header_css.jsp"></c:import>

</head>

<body>
	<div class="page-wrap" id="root">

		<c:import url="../template/header_nav.jsp"></c:import>

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
            <div class="container-fluid col-6 justify-contents-center">
			<h1>${board}게시판</h1>

			<div style="margin-left: 50px;">
				<table style="width: 800px;" class="table mt-4">
					<thead>
						<tr>
							<th>글번호</th>
							<th>글제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody class="table-group-divider">
						<c:forEach items="${list}" var="dto">
							<tr>
								<td>${dto.boardNum}</td>
								<td>${dto.boardTitle}</td>
								<td>${dto.memberNickname}</td>
								<td>${dto.createDate}</td>
								<td>${dto.updateDate}</td>
								<td>${dto.hit}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<h5>
				<a href="add">${board}게시글 작성</a>
			</h5>





  </div>
		</div>
		<!-- End / Content-->
	</div>

	<c:import url="../template/footer.jsp"></c:import>
</body>
</html>

