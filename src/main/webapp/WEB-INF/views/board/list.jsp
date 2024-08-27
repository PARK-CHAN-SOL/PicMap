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

</head>

<body>
  <div class="page-wrap" id="root">

    <c:import url="../template/header_nav.jsp"></c:import>

    <!-- Content-->
    <div class="md-content">

      <!-- hero -->
      <div class="hero" id="id-1">
        <div class="hero__wrapper">
          <div style="height: 400px;" class="bg-primary bg-gradient bg-opacity-25">
            <div class="container-fluid col-8 justify-contents-center fs-4">
            <table style="height: 300px;">
              <tbody>
                <tr>
                  <td class="align-bottom"><h1 style="font-style: italic; font-weight: 900; font-size: 80px;">공지사항 📰📢</h1></td>
                </tr>
              </tbody>
            </table>
            <div style="font-family: Spiegel, sans-serif; font-weight: 400; font-size: 18px; line-height: 26px;">여행과 관련된 모든 업데이트 소식을 한눈에</div>
            </div>
          </div>
        </div>
      </div>
      <!-- End / hero -->
      <div class="container-fluid col-8 justify-contents-center fs-4 mt-4">
        <div>
          <table class="table mt-4">
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
                  <th><a href="./detail?boardNum=${dto.boardNum}" class="list-group-item list-group-item-action list-group-item-light" style="text-decoration: none; color: black;">${dto.boardTitle}</a></th>
                  <td>${dto.memberNickname}</td>
                  <td>${dto.createDate}</td>
                  <td>${dto.updateDate}</td>
                  <td>${dto.hit}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>

        <c:if test="${sessionScope.member.memberNum eq 1 || sessionScope.member.memberNum eq 24}">
          <div class="d-md-flex justify-content-md-end">
            <a class="btn btn-secondary mb-3 fs-4" href="./add" role="button">공지 추가</a>
          </div>
        </c:if>



		<div class="container col-md-3 mt-5 justify-content-center">
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<li class="page-item ${pager.pre?'':'disabled'}">
						<a class="page-link" href="./list?page=${pager.startNum - 1}" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
					<c:forEach begin="${pager.startNum}" end="${pager.lastNum}"
						step="1" var="i">
						<li class="page-item"><a class="page-link"
							href="./list?page=${i}">${i}</a></li>
					</c:forEach>

					<li class="page-item ${pager.next?'':'disabled'}">
						<a class="page-link" href="./list?page=${pager.lastNum+1}" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
					
					
							
        <!-- container end -->
      </div>
    </div>
    <!-- End / Content-->
  </div>

  <c:import url="../template/footer.jsp"></c:import>
</body>
</html>

