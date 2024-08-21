<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="/resources/assets/css/boardList.css">
<link rel="stylesheet" href="/resources/assets/css/gridSave.css">

</head>

<body>
  <div class="page-wrap" id="root">

    <c:import url="../../template/header_nav.jsp"></c:import>

    <!-- Content-->
    <div class="md-content" id="boardStartLocation">

      <!-- 글 작성 버튼 -->
      <c:choose>
        <c:when test="${empty member}">
              <button type="button" class="btn btn-outline-danger" id="addBoard" data-bs-toggle="modal" data-bs-target="#exampleModal"><span>+</span></button>

        </c:when>
        <c:otherwise>

              <button type="button" class="btn btn-outline-danger" id="addBoard" onclick="location.href = 'add'"><span>+</span></button>

        </c:otherwise>
      </c:choose>


      <!-- hero -->
      <div class="hero" id="id-1">
        <div class="hero__wrapper">

          <div style="height: 400px;" class="bg-primary bg-gradient bg-opacity-25">
            <div class="container-fluid col-8 justify-contents-center fs-4">
              <table style="height: 300px;">
                <tbody>
                  <tr>
                    <td class="align-bottom"><h1 style="font-style: italic; font-weight: 900; font-size: 80px;">여행 리뷰 🚩 &nbsp&nbsp&nbsp&nbsp&nbsp🚗💨</h1></td>
                  </tr>
                </tbody>
              </table>
              <div style="font-family: Spiegel, sans-serif; font-weight: 400; font-size: 18px; line-height: 26px;">당신의 발자취가 담긴 여행의 순간들, 우리 마음에 특별한 흔적을 남깁니다</div>
            </div>
          </div>
        </div>
      </div>
      <!-- End / hero -->





      <!-- Section -->
		<section class="awe-section">
			<div class="container">
	
				<div class="grid-css grid_css_style_02 grid-css--masonry" data-col-lg="3" data-col-md="2" data-col-sm="2" data-col-xs="1" data-gap="30">
					<div class="filter">
						<ul class="filter__list" id="pills-tab" role="tablist">
							<li class="current"><a href="#" id="pills-profile-tab" data-bs-toggle="pill" data-bs-target="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="true" class="active">최신순</a></li>
							<li class><a href="#" id="pills-friends-tab" data-bs-toggle="pill" data-bs-target="#pills-friends" role="tab" aria-controls="pills-friends" aria-selected="false" class tabindex="-1">추천순</a></li>
						</ul>
					</div>
				</div>
	            
	            
				<div class="tab-content" id="pills-tabContent">
				
	            	<div class="tab-pane fade show active" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
	            		<div class="grid-css grid_css_style_02 grid-css--masonry" data-col-lg="3" data-col-md="2" data-col-sm="2" data-col-xs="1" data-gap="30">
							<div class="grid__inner">
								<div class="grid-sizer"></div>
							</div>
	            			<div id="travelObserverTarget" data-start-row="1" data-end-row="18"></div>
	            		</div>
	            	</div>
	            	
					<div class="tab-pane fade" id="pills-friends" role="tabpanel" aria-labelledby="pills-friends-tab" tabindex="0">
	    				<div class="grid-css grid_css_style_02 grid-css--masonry" data-col-lg="3" data-col-md="2" data-col-sm="2" data-col-xs="1" data-gap="30">
							<div class="grid__innerSave">
								<div class="grid-sizerSave"></div>
							</div>
							<div id="mypageObserverTargetSave" data-start-row="1" data-end-row="18" style="display:none;"></div>
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
  <script src="/resources/assets/js/board/travelList.js"></script>
  <script type="text/javascript" src="/resources/assets/js/board/sort_likes.js"></script>
  
  
</body>
</html>

