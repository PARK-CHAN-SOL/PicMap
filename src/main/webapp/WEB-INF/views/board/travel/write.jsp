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
<link rel="stylesheet" href="/resources/assets/css/pingSearch.css">

<!-- include summernote css/js-->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">


<!-- summernote -->
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>

<script src="/resources/summernote/summernote-lite.js"></script>
<script src="/resources/summernote/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="/resources/summernote/summernote-lite.css">

</head>

<body>
  <div class="page-wrap" id="root">

    <c:import url="../../template/header_nav.jsp"></c:import>

    <!-- Content-->
    <div class="">

      <!-- hero -->
      <div class="hero" id="id-1">
        <div class="hero__wrapper">

          <div style="height: 400px;" class="bg-primary bg-gradient bg-opacity-25">
            <div class="container-fluid col-8 justify-contents-center fs-4">
              <table style="height: 300px;">
                <tbody>
                  <tr>
                    <td class="align-bottom"><h1 style="font-style: italic; font-weight: 900; font-size: 80px;">게시글 작성 ✍️</h1></td>
                  </tr>
                </tbody>
              </table>
              <div style="font-family: Spiegel, sans-serif; font-weight: 400; font-size: 18px; line-height: 26px;">다른 사람들과 여행 경험을 공유해요</div>
            </div>
          </div>
        </div>
      </div>
      <!-- End / hero -->




    </div>
    <!-- End / Content-->

    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="mb-3 col-8">
          <form method="post" enctype="multipart/form-data">
            <input type="hidden" value="${dto.boardNum}" name="boardNum">
            <table class="table table-bordered">
              <tbody>
                <tr>
                  <th><span class="fs-4">제목</span></th>
                  <th colspan="2"><input type="text" class="form-control fs-4" id="boardTitle" name="boardTitle" value="${dto.boardTitle}"></th>
                </tr>
                <tr>
                  <th><span class="fs-4">작성자</span></th>
                  <td><span class="fs-4">${member.memberNickName}</span></td>
                </tr>
                <tr>
                  <th rowspan="3"><span class="fs-4">내용</span></th>
                  <th colspan="2">
                  	<input type="file" class="form-control fs-4" id="travelFiles" name="files" onchange="readURL(this)" style="display: none;" style="display:none;">
                  	<label for="travelFiles" class="fs-4 btn btn-secondary">게시글 사진을 추가하세요</label>
                  	<c:choose>
                  		<c:when test="${dto.fileName != null}">
                  			<input type="hidden" name="fileName" value="${dto.fileName}">
		                  	<img id="travelPreview" class="mt-3" style="width: 100%; height: 100%; object-fit: cover;" src="/resources/upload/travels/${dto.fileName}"/>
                  		</c:when>
                  		<c:otherwise>
                  			<img id="travelPreview" class="mt-3" style="width: 100%; height: 100%; object-fit: cover;"/>
                  		</c:otherwise>
                  	</c:choose>
                  </th>
                </tr>
                <tr>
                  <td colspan="2"><button id="modalButton" type="button" class="btn btn-secondary me-2 fs-4" data-bs-toggle="modal" data-bs-target="#mapModal">위치</button>
                    <span id="locSpan" class="fs-4">${pingDTO.address}</span></td>
                </tr>
                <tr>
                  <td colspan="2"><textarea class="form-control fs-4" id="editor" name="boardContent" style="height: 100px;">${dto.boardContent}</textarea></td>
                </tr>
              </tbody>
            </table>
            <div id="pingFrm">
            	<input type="hidden" name="pingNum" value="${pingDTO.pingNum}">
				<input type="hidden" id="loc" name="address" value="${pingDTO.address}">
				<input type="hidden" id="lat" name="latitude" value="${pingDTO.latitude}">
				<input type="hidden" id="lon" name="longitude" value="${pingDTO.longitude}">
            </div>
            <div>
				<input type="hidden" name="rootBoard" value="${dto.rootBoard}">
				<input type="hidden" name="parentBoard" value="${dto.parentBoard}">
				<input type="hidden" name="childBoard" value="${dto.childBoard}">
            </div>

            <div class="text-end">
              <button type="submit" class="btn btn-primary fs-4">등록</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <div>
    <!-- Modal -->
    <div class="modal fade" id="mapModal" tabindex="-1" aria-labelledby="mapModalLabel" aria-hidden="true">
      <div class="modal-dialog" style="--bs-modal-width: 840px;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">

            <div>
              <div class="map_wrap">
                <div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
                <div class="hAddr"></div>

                <div id="menu_wrap" class="bg_white">
                  <div class="option">
                    <div>
                      <form onsubmit="searchPlaces(); return false;">
                        키워드 : <input type="text" value="서울 맛집" id="keyword" size="15">
                        <button type="submit" class="mt-2 mb-1 btn btn-primary">검색하기</button>
                      </form>
                    </div>
                  </div>
                  <hr>
                  <ul id="placesList"></ul>
                  <div id="pagination"></div>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer text-end">
            <div>
              <button id="mapModalCloseBtn" type="button" class="btn btn-primary fs-4" id="mapButton" data-bs-dismiss="modal">등록</button>

            </div>
          </div>
        </div>
      </div>
    </div>

    <c:import url="../../template/footer.jsp"></c:import>
    <script type="text/javascript" src="/resources/assets/js/board/boardImage.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

    <script>
					$('#editor')
							.summernote(
									{
										placeholder : '게시글을 입력하세요',
										tabsize : 3,
										height : 200,
										toolbar : [
												[ 'style', [] ],
												[ 'fontsize', [ 'fontsize' ] ],
												[
														'font',
														[ 'bold', 'underline',
																'clear' ] ],
												[ 'color', [ 'color' ] ],
												[
														'para',
														[ 'ul', 'ol',
																'paragraph' ] ],
												[ 'table', [] ],
												[ 'insert', [] ],
												[ 'view', [ 'help' ] ] ],
										fontSizes : [ '8', '9', '10', '11',
												'12', '13', '14', '15', '16',
												'17', '18', '19', '20', '24',
												'30', '36', '48', '64', '82',
												'150' ],
										callbacks : {
											onImageUpload : function(files) {
												// upload image to server and create imgNode...
											}
										}
									});
					$('#editor').summernote('fontSize', 16);
				</script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e11955080502d1bac37823f6b7f43b6&libraries=services"></script>
    <script src="/resources/assets/js/ping/ping.js"></script>
    <script src="/resources/assets/js/ping/modal.js"></script>
    <script src="/resources/assets/js/ping/pingSearch.js"></script>
</body>
</html>

