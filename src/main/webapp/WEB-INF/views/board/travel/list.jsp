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

</head>

<body>
  <div class="page-wrap" id="root">

    <c:import url="../../template/header_nav.jsp"></c:import>

    <!-- Content-->
    <div class="md-content" id="boardStartLocation">

      <!-- 글 작성 버튼 -->
      <c:choose>
        <c:when test="${empty member}">
              <button type="button" class="btn btn-danger" id="addBoard" data-bs-toggle="modal" data-bs-target="#exampleModal">글 작성</button>

        </c:when>
        <c:otherwise>

              <button type="button" class="btn btn-danger" id="addBoard" onclick="location.href = 'add'">글 작성</button>

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
                    <td class="align-bottom"><h1 style="font-style: italic; font-weight: 900; font-size: 80px;">전체 게시글 🚩 &nbsp&nbsp&nbsp&nbsp&nbsp🚗💨</h1></td>
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
              <ul class="filter__list">
                <li><a href="#" data-filter="*">All</a></li>
                <li><a href="#" data-filter=".cat1">추천순</a></li>
                <li><a href="#" data-filter=".cat2">최신순</a></li>
              </ul>
            </div>
            <div class="grid__inner">
              <div class="grid-sizer" id="travelList"></div>
            </div>
            <div id="travelObserverTarget" data-start-row="1" data-end-row="9"></div>
          </div>

        </div>
      </section>
      <!-- End / Section -->























    </div>
    <!-- End / Content-->
  </div>

  <c:import url="../../template/footer.jsp"></c:import>
  <script src="/resources/assets/js/board/travelList.js"></script>
</body>
</html>

