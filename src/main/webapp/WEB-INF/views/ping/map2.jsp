<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8">
        <title>키워드로 장소검색하고 목록으로 표출하기</title>
        <link rel="stylesheet" href="/resources/assets/css/pingSearch.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
                crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"></script>
            <c:import url="../template/header_css.jsp"></c:import>
    </head>

    <body>
        <div>
            <button id="modalButton" type="button" class="btn btn-primary mb-2" data-bs-toggle="modal"
                data-bs-target="#mapModal">위치</button>
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
                                    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;">
                                    </div>
                                    <div class="hAddr"></div>

                                    <div id="menu_wrap" class="bg_white">
                                        <div class="option">
                                            <div>
                                                <form onsubmit="searchPlaces(); return false;">
                                                    키워드 : <input type="text" value="서울 맛집" id="keyword" size="15">
                                                    <button type="submit">검색하기</button>
                                                </form>
                                            </div>
                                        </div>
                                        <hr>
                                        <ul id="placesList"></ul>
                                        <div id="pagination"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div>
                                    <button type="button" class="btn btn-primary" id="mapButton">등록</button>
                                    <button type="button" class="btn btn-secondary" id="mapClose"
                                        data-bs-dismiss="modal">취소</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <form action="/ping/addPing" method="post" id="pingFrm">
                <div>
                    <input type="text" id="loc" name="address"> <input type="hidden" id="lat" name="latitude"> <input
                        type="hidden" id="lon" name="longitude">
                </div>
            </form>

            <script type="text/javascript"
                src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e11955080502d1bac37823f6b7f43b6&libraries=services"></script>
            <script src="/resources/assets/js/ping/ping.js"></script>
            <script src="/resources/assets/js/ping/pingSearch.js"></script>
            <script src="/resources/assets/js/ping/modal.js"></script>
    </body>

    </html>