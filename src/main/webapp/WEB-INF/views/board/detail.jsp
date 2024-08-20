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
<c:import url="../template/header_css.jsp"></c:import>
<link rel="stylesheet" href="/resources/assets/css/boardDetail.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/css/comments.css">

</head>

<body>
  <div class="page-wrap" id="root">
    <c:import url="../template/header_nav.jsp"></c:import>
    <!-- Content-->
    <div class="md-content">
      <!-- 메인 -->
      <section class="awe-section">
        <div style="height:auto;">
          <div></div>
        </div>
        <div class="container mt-3">
          <div class="row mainContent justify-content-center" style="position: relative;">
            <!-- 블러 처리된 배경을 위한 div -->
            <div style="position: absolute; top: 0; left: 0; right: 0; bottom: 0; z-index: -1;
                        background-image: url(${empty dto.fileName? ('/resources/upload/notices/default.png') : ('/resources/upload/notices/'+= '' += dto.fileName)});
                        background-size: 500px 500px; background-position: top; background-repeat: repeat-x;
                        filter: blur(32px);">
            </div>
            <!-- 게시글 제목 -->
            <div class="col-md-10 col-lg-10">
              <!-- 게시한 사진 -->
              <div class="picture">
                <c:if test="${empty dto.fileName}">
                  <img src="/resources/upload/notices/default.png" style="position: relative;" alt="" class="img-fluid" />
                </c:if>
                <c:if test="${not empty dto.fileName}">
                  <img src="/resources/upload/notices/${dto.fileName}" style="position: relative;" alt="" class="img-fluid" />
                </c:if>
              </div>
              <br>
              <br>
              <!-- title -->
              <div class="boardTitle">
                <h2 class="boardTitle__title">${dto.boardTitle}</h2>
              </div>
              <!-- End / title -->
            </div>
            <!-- 게시글 전체 내용 -->
            <div class="col-md-12 col-lg-12" style="position: relative;">
              <div class="boardContent">
                <!-- 작성자, 작성날짜, 좋아요 -->
                <div class="boardInfo" style="margin-top: 30px;">
                  <div class="d-flex align-items-center justify-content-between" style="width: 100%;">
                    <div class="d-flex align-items-center"></div>
                  </div>
                  <div class="row justify-content-between mt-3" style="width: 100%;">
                    <div class="col-3">
                      <span style="margin-left: 10px;"> • ${dto.createDate}</span>
                    </div>
                    <div class="col-4">
                      <span style="margin-right: 10px;">조회수 ${dto.hit}</span>
                    </div>
                    <div class="col-5 text-end">
                      <button class="btn_like" id="heartButton" style="padding: 0px; display: inline;" data-id-heart="${dto.boardNum}"></button>
                      <p id="heartCount" style="display: inline-block; vertical-align: top;">${heart}명이 좋아합니다</p>
                    </div>
                  </div>
                </div>
                <!-- 게시글 텍스트 내용 -->
                <div class="boardContent_text" style="margin-top: 30px;">
                  <div style="margin-left: 10px;">${dto.boardContent}</div>
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
            </div>
          </div>
        </div>
      </section>
      <!-- End / Section -->
    </div>
    <!-- End / Content-->
  </div>
  <c:import url="../template/footer.jsp"></c:import>
  <script src="/resources/assets/js/heart/noticeHeart.js"></script>
</body>
</html>