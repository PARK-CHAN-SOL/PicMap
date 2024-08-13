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
			
	
			<!-- 자식글 부모글 페이징 버튼 -->
		    <div>
		        <div class="nav-buttons">
		        	<!-- 부모글로 가는 버튼 -->
		        	<c:choose>
		        		<c:when test="${dto.boardNum ne dto.rootBoard}">
		        			<button type="button" class="btn btn-outline-secondary" id="detailPrevPageBtn"
		        			onclick = "location.href = '/travel/detail?boardNum=${dto.parentBoard}'">&lt;</button>
		        		</c:when>
		        		<c:otherwise>
		        			<button type="button" class="btn btn-outline-secondary" id="detailPrevPageBtn" style="visibility: hidden;">&lt;</button>
		        		</c:otherwise>
		        	</c:choose>
		        	
		        	<!-- 자식글로 가거나 자식글을 추가하는 버튼 -->
		        	<c:choose>
		        		<c:when test="${not empty dto.childBoard}">
		        			<button type="button" class="btn btn-outline-secondary" id="detailNextPageBtn"
		        			onclick = "location.href = '/travel/detail?boardNum=${dto.childBoard}'">&gt;</button>
		        		</c:when>
		        		<c:when test="${empty dto.childBoard && login.memberNum == dto.memberNum}">
			        			<button type="button" class="btn btn-outline-secondary" id="addPlusBoard"
			        			onclick = "location.href = '/travel/addPlus?rootBoard=${dto.rootBoard}&parentBoard=${dto.boardNum}'">&#43;</button>
		        		</c:when>
		        		<c:otherwise>
		        			<button type="button" class="btn btn-outline-secondary" id="detailNextPageBtn"
		        			onclick = "location.href = '/travel/detail?boardNum=${dto.childBoard}'" style="visibility: hidden;">&gt;</button>
		        		</c:otherwise>
		        	</c:choose>
		        </div>      
		    </div>			
			


			<!-- 메인 -->
			<section class="awe-section">
				<div class="container">
					<div class="row mainContent">

						<!-- 게시글 제목 -->
						<div class="col-md-10 col-lg-10 col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1 ">
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
								
								
								
								
								
								
								
								
								<!-- 작성자, 작성날짜, 좋아요 -->
								<div class="boardInfo" style="margin-top: 30px;">

									<div class="d-flex align-items-center justify-content-between" style="width: 100%;">
										<div class="d-flex align-items-center">
											<span class="linear-gradient d-flex align-items-center justify-content-center rounded-circle"
											style="width: 70px; height: 70px; display: inline-block;">
												<span class="border border-4 border-white d-flex align-items-center justify-content-center rounded-circle overflow-hidden"
												style="width: 60px; height: 60px;">
													<c:if test="${not empty member.profilePath}">
														<img src="${member.profilePath}"
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
										
										<span class="box-image2__like_detail" id="heartBtn" data-id="${dto.boardNum}" >${heart}</span>										
										
									</div>
								</div>


								<!-- 게시글 텍스트 내용 -->
								<div class="boardContent_text" style="margin-top: 30px;">
									<div style="margin-left: 10px;">${dto.boardContent}</div>
								</div>
								

							</div>
							
							
							
							
							
							
							
							
							<!-- 여기에 댓글 작성 넣으시면 됩니다 -->							
							<!-- 댓글 리스트 넣으시면 됩니다 -->
							
							
							
							
							
							
							
							
						</div>


					</div>
				</div>
			</section>
			<!-- End / Section -->



		</div>
		<!-- End / Content-->
	</div>
	

	<c:import url="../../template/footer.jsp"></c:import>
	<script src="/resources/assets/js/board/heart.js"></script>
	
	
	
</body>
</html>