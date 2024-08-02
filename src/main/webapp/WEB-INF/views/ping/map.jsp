<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="utf-8">
            <title>좌표로 주소를 얻어내기</title>
            <link rel="stylesheet" href="/resources/assets/css/ping.css">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
                crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"></script>
            <c:import url="../template/header_css.jsp"></c:import>
        </head>

        <body>
            <%-- <c:import url="../template/header_nav.jsp"></c:import> --%>
                <br><br><br><br><br><br><br>
                <div>
                    <button id="modalButton" type="button" class="btn btn-primary mb-2" data-bs-toggle="modal"
                        data-bs-target="#mapModal">위치</button>
                </div>

                <div>
                    <!-- Modal -->
                    <div class="modal fade" id="mapModal" tabindex="-1" aria-labelledby="mapModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">

                                    <div>
                                        <div class="map_wrap">
                                            <div id="map" style="width: 100%; height: 100%;"></div>
                                            <div class="hAddr">
                                                <span id="centerAddr"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                  <div>
                                    <input type="text" id="searchPing">
                                    <button type="button" class="btn btn-primary" id="searchButton">검색</button>
                                  </div>
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
                <form action="/ping/addPing" method="post" id="frm">
                    <div>
                        <input type="text" id="loc" name="address"> <input type="hidden" id="lat" name="latitude"> <input type="hidden" id="lon" name="longitude">
                    </div>
                </form>
                    

                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
                    integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
                    crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
                    integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
                    crossorigin="anonymous"></script>
                <%-- <c:import url="../template/footer.jsp"></c:import> --%>
                    <script type="text/javascript"
                        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e11955080502d1bac37823f6b7f43b6&libraries=services"></script>
                    <script src="/resources/assets/js/ping/ping.js"></script>
                    <script src="/resources/assets/js/ping/pingesearch.js"></script>
                    <script src="/resources/assets/js/ping/modal.js"></script>
        </body>

        </html>