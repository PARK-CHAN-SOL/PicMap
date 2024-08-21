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
<link rel="stylesheet" href="/resources/assets/css/boardDetail.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/css/comments.css">

</head>

<body>
  <div class="page-wrap" id="root">

    <c:import url="../../template/header_nav.jsp"></c:import>

    <!-- Content-->
    <div class="md-content">


      <!-- 자식글 부모글 페이징 버튼 -->
      <div>
        <!-- 부모글로 가는 버튼 -->
        <c:choose>
          <c:when test="${dto.boardNum ne dto.rootBoard}">
            <button type="button" class="btn btn-outline-secondary nav-leftButton" id="detailPrevPageBtn" onclick="location.href = '/travel/detail?boardNum=${dto.parentBoard}&rootBoard=${dto.rootBoard}'">&lt;</button>
          </c:when>
          <c:otherwise>
            <button type="button" class="btn btn-outline-secondary nav-leftButton" id="detailPrevPageBtn" style="visibility: hidden;">&lt;</button>
          </c:otherwise>
        </c:choose>

        <!-- 자식글로 가거나 자식글을 추가하는 버튼 -->
        <c:choose>
          <c:when test="${not empty dto.childBoard}">
            <button type="button" class="btn btn-outline-secondary nav-rightButton" id="detailNextPageBtn" onclick="location.href = '/travel/detail?boardNum=${dto.childBoard}&rootBoard=${dto.rootBoard}'">&gt;</button>
          </c:when>
          <c:when test="${empty dto.childBoard && login.memberNum == dto.memberNum}">
            <button type="button" class="btn btn-outline-secondary nav-rightButton" id="addPlusBoard" onclick="location.href = '/travel/addPlus?rootBoard=${dto.rootBoard}&parentBoard=${dto.boardNum}'">&#43;</button>
          </c:when>
          <c:otherwise>
            <button type="button" class="btn btn-outline-secondary nav-rightButton" id="detailNextPageBtn" style="visibility: hidden;">&gt;</button>
          </c:otherwise>
        </c:choose>
      </div>



      <!-- 메인 -->
      <section class="awe-section">
        <div class="container">
          <div class="row mainContent justify-content-center" style="position: relative;">

            <!-- 블러 처리된 배경을 위한 div -->
            <div style="position: absolute; top: 0; left: 0; right: 0; bottom: 0; z-index: -1;
                        background-image: url(${empty dto.fileName? ('\'/resources/upload/travels/default.png\'') : ('\'/resources/upload/travels/'+= '' += dto.fileName += '\'')});
                        background-size: 500px 500px; background-position: top; background-repeat: repeat-x;
                        filter: blur(32px);"></div>
            <!-- 게시글 제목 -->
            <div class="col-md-10 col-lg-10">
              <!-- 게시한 사진 -->
              <div class=>
                <c:if test="${empty dto.fileName}">
                  <img src="/resources/upload/travels/default.png" style="position: relative;" alt="" class="img-fluid" />
                </c:if>
                <c:if test="${not empty dto.fileName}">
                  <img src="/resources/upload/travels/${dto.fileName}" style="position: relative;" alt="" class="img-fluid" />
                </c:if>
              </div>
              <br> <br>
              <!-- title -->
              <div class="boardTitle">
                <h2 class="boardTitle__title">${dto.boardTitle}</h2>
              </div>
              <!-- End / title -->
            </div>

            <!-- 게시글 전체 내용 -->
            <div class="col-md-12 col-lg-12">
              <div class="boardContent">




                <!-- 여기에 추천 관광지 넣으시면 됩니다 -->
                <div class="row justify-content-center">
                  <div id="recommendTravelList" class="col-lg-7 col-md-10 col-sm-12 align-self-center border border-4 p-2 text-center mt-4" data-ping-num="${dto.pingNum}">
                    <div>
                      <b class="fs-4">다음 여행지 추천</b>
                    </div>
                  </div>
                </div>

                <div class="row justify-content-center mt-3">
                  <div class="col-lg-7 col-md-10 col-sm-12 text-end p-0">
                    <button id="modalButton" type="button" class="border-0 fs-4" data-bs-toggle="modal" data-bs-target="#mapModal">위치</button>
                  </div>
                </div>


                <!-- 작성자, 작성날짜, 좋아요 -->
                <div class="row justify-content-center fs-3" style="margin-top: 30px;">
                  <div class="col-lg-7 col-md-10 col-sm-12 p-0">
                    <div class="d-flex align-items-center justify-content-between" style="width: 100%;">
                      <div class="d-flex align-items-center">
                        <a href="/member/mypage?memberNum=${dto.memberNum}" class="link-tmp" title="프로필보기"> <span class="linear-gradient d-flex align-items-center justify-content-center rounded-circle" style="width: 70px; height: 70px; display: inline-block;"> <span class="border border-4 border-white d-flex align-items-center justify-content-center rounded-circle overflow-hidden" style="width: 60px; height: 60px;"> <c:if test="${not empty member.profilePath}">
                                <img src="${member.profilePath}" onerror="this.src='/resources/upload/members/default.png'" alt="${member.memberNickName}" class="w-100 h-100 profile-image profile-link" data-member-num="${dto.memberNum}">
                              </c:if> <c:if test="${empty member.profilePath}">
                                <img src="/resources/upload/members/default.png" alt="${member.memberNickName}" class="w-100 h-100 profile-image profile-link" data-member-num="${dto.memberNum}">
                              </c:if>
                          </span>
                        </span>

                        </a> <span style="margin-left: 10px;">${member.memberNickName}</span> <span style="margin-left: 10px;"> • ${dto.writeDate}</span>
                      </div>


                      <span>조회수 ${hit}</span>

                    </div>


                    <div class="d-flex align-items-center justify-content-between mt-3" style="width: 100%;">
                      <div class="d-flex align-items-center">
                        <c:choose>
                          <c:when test="${dto.boardNum eq dto.rootBoard}">
                            <button class="btn_like" id="heartButton" data-id-heart="${dto.boardNum}"></button>
                          </c:when>
                          <c:otherwise>
                            <button class="btn_like" id="heartButton" data-id-heart="${dto.rootBoard}"></button>
                          </c:otherwise>
                        </c:choose>

                        <span id="heartCount">${heart}명이 좋아합니다</span>
                      </div>

                      <c:choose>
                        <c:when test="${dto.boardNum eq dto.rootBoard}">
                          <button class="btn_save" id="savePostButton" data-id-savepost="${dto.boardNum}"></button>
                        </c:when>
                        <c:otherwise>
                          <button class="btn_save" id="savePostButton" data-id-savepost="${dto.rootBoard}"></button>
                        </c:otherwise>
                      </c:choose>
                    </div>



                    <!-- 게시글 텍스트 내용 -->
                    <div class="mt-5 mx-3">
                      <div>${dto.boardContent}</div>
                    </div>

                    <!-- 수정,삭제 버튼 -->
                    <div class="d-grid gap-2 d-md-flex justify-content-end mt-5" style="margin: 0 auto; max-width: 720px;">
                      <c:if test="${login.memberNum == dto.memberNum}">
                        <button type="submit" class="btn btn-primary" id="updateBtn" onclick="location.href ='./update?boardNum=${dto.boardNum}' ">수정</button>
                        <form action="./delete?boardNum=${dto.boardNum}" method="post">
                          <button type="submit" class="btn btn-danger" id="deleteBtn">삭제</button>
                        </form>
                      </c:if>
                    </div>


                  </div>


                  <!-- 댓글 -->
                  <div class="row justify-content-center">
                    <div class="col-lg-7 col-md-10 col-sm-12 p-0">
                      <div class="comments-container">
                        <h3>댓글 목록</h3>
                        <div id="commentForm">
                          <c:choose>
                            <c:when test="${empty sessionScope.member}">
                              <textarea id="commentContents" name="comment" placeholder="댓글을 입력하세요" class="comment-textarea" onclick="modalBtn.click()"></textarea>
                              <button id="modalBtn" type="button" class="md-btn md-btn--primary mb-3" data-bs-toggle="modal" data-bs-target="#exampleModal">로그인 후 댓글을 남겨주세요</button>
                            </c:when>
                            <c:otherwise>
                              <textarea id="commentContents" name="comment" placeholder="댓글을 입력하세요" class="comment-textarea"></textarea>

                              <button id="commentButton" type="button" class="comment-button mb-3" data-id="${param.boardNum}">댓글 남기기</button>
                            </c:otherwise>
                          </c:choose>

                        </div>
                        <div id="commentsList"></div>
                        <div id="commentsObserverTarget" data-start-row="1" data-end-row="10" data-member-num="${sessionScope.member.memberNum}" data-board-num="${param.boardNum}" class="container"></div>

                      </div>
                    </div>
                  </div>
                  <!-- 댓글END -->



                </div>

              </div>

            </div>

          </div>
        </div>
      </section>
      <!-- End / Section -->



    </div>
    <!-- End / Content-->

    <!-- 팝업 레이아웃 -->
    <div id="profilePopup" class="profile-popup" style="position: absolute;">
      <div class="profile-popup-content">
        <img src="">
        <p>
          <b>작성자</b>
        </p>
        <button id="followButton" class="like-button">팔로우</button>
        <button class="view-profile-button">프로필 보기</button>
      </div>
    </div>
    <!-- 팝업 레이아웃END -->

    <!-- Modal -->
    <div class="modal fade" id="mapModal" tabindex="-1" aria-labelledby="mapModalLabel" aria-hidden="true">
      <div class="modal-dialog" style="-bs-modal-width: 840px;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">

            <div>
              <div class="map_wrap">
                <div id="map" style="width: 100%; height: 600px; position: relative; z-index: 1000; overflow: hidden;"></div>
                <div class="hAddr"></div>

                <div id="menu_wrap" class="bg_white">
                  <div class="option">
                    <div></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>


  </div>


  <c:import url="../../template/footer.jsp"></c:import>
  <script src="/resources/assets/js/heart/heart.js"></script>
  <script src="/resources/assets/js/savepost/savePost.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/comments/reply.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/comments/commentslist.js"></script>
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e11955080502d1bac37823f6b7f43b6&libraries=services"></script>
  <script src="/resources/assets/js/ping/recommendList.js"></script>
  <script src="/resources/assets/js/ping/modal.js"></script>

</body>
</html>