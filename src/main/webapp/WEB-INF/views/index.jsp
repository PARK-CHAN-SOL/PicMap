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

<link rel="stylesheet" href="/resources/assets/css/pingSearch.css">
<c:import url="./template/header_css.jsp"></c:import>


</head>

<body>
  <c:import url="./template/header_nav.jsp"></c:import>


  <div class="page-wrap" id="root"></div>
  <!-- Content-->
  <div class="md-content">


    <div class="container">
      <div>
        <div class="map_wrap" style="height: calc(30vw + 254.54545px);">
          <div id="map" style="width: 100%; height: 100%;"></div>
          <div class="hAddr">
            <span id="centerAddr"></span>
          </div>
        </div>
      </div>
    </div>
    <!-- Section -->
    <section class="awe-section pd-0" id="box-search">
      <div class="box-search-wrapper">
        <div class="container">

          <!-- box-search -->
          <div class="box-search row justify-content-end">

            <!-- form-item -->



            <!-- form-item -->
            <div class="form-item">
              <input class="form-control" type="text" name="input" id="searchPing" placeholder="여행지를 입력하세요" />
            </div>
            <!-- End / form-item -->


            <!-- form-item -->


            <a class="md-btn md-btn--primary md-btn--pill " href="#" id="searchButton">Search now </a>
          </div>
          <!-- End / box-search -->

        </div>
      </div>
    </section>
    <!-- End / Section -->


    <!-- Section -->
    <section class="awe-section bg-gray">
      <div class="container">
        <div class="row">
          <div class="col-md-10 col-lg-10 col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1 ">

            <!-- title -->
            <div class="title">
              <h2 class="title__title">
                Pic Map <span class='main-color'>픽맵</span><br /> 인기 여행지를 골라보세요.<br /> <span class='main-color'>Asia</span> for more than two decades.
              </h2>
              <p class="title__text">It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters</p>
            </div>
            <!-- End / title -->

          </div>
          <div class="col-md-12 col-lg-12 ">
            <div class="grid-css grid-css--masonry" data-col-lg="4" data-col-md="3" data-col-sm="2" data-col-xs="1" data-gap="30">
              <div class="grid__inner">
                <div class="grid-sizer"></div>
                <div class="grid-item">
                  <div class="grid-item__inner">
                    <div class="grid-item__content-wrapper">

                      <!-- box-image -->
                      <div class="box-image" href="/resources/assets/img/image_box_1/1.jpg">
                        <div>
                          <a class="box-image__bg" href="/resources/assets/img/image_box_1/1.jpg" style="background-image: url('/resources/assets/img/image_box_1/1.jpg');" data-effect="mfp-zoom-in"><img src="/resources/assets/img/image_box_1/1.jpg" alt="" />
                            <div class="box-image__info">
                              <h4 class="box-image__country">서울</h4>
                              <p class="box-image__tour">309</p>
                            </div></a>
                        </div>
                      </div>
                      <!-- End / box-image -->

                    </div>
                  </div>
                </div>
                <div class="grid-item">
                  <div class="grid-item__inner">
                    <div class="grid-item__content-wrapper">

                      <!-- box-image -->
                      <div class="box-image" href="/resources/assets/img/image_box_1/2.jpg">
                        <div>
                          <a class="box-image__bg" href="/resources/assets/img/image_box_1/2.jpg" style="background-image: url('/resources/assets/img/image_box_1/2.jpg');" data-effect="mfp-zoom-in"><img src="/resources/assets/img/image_box_1/2.jpg" alt="" />
                            <div class="box-image__info">
                              <h4 class="box-image__country">강릉</h4>
                              <p class="box-image__tour">287</p>
                            </div></a>
                        </div>
                      </div>
                      <!-- End / box-image -->

                    </div>
                  </div>
                </div>
                <div class="grid-item">
                  <div class="grid-item__inner">
                    <div class="grid-item__content-wrapper">

                      <!-- box-image -->
                      <div class="box-image" href="/resources/assets/img/image_box_1/3.jpg">
                        <div>
                          <a class="box-image__bg" href="/resources/assets/img/image_box_1/3.jpg" style="background-image: url('/resources/assets/img/image_box_1/3.jpg');" data-effect="mfp-zoom-in"><img src="/resources/assets/img/image_box_1/3.jpg" alt="" />
                            <div class="box-image__info">
                              <h4 class="box-image__country">부산</h4>
                              <p class="box-image__tour">387</p>
                            </div></a>
                        </div>
                      </div>
                      <!-- End / box-image -->

                    </div>
                  </div>
                </div>
                <div class="grid-item">
                  <div class="grid-item__inner">
                    <div class="grid-item__content-wrapper">

                      <!-- box-image -->
                      <div class="box-image" href="/resources/assets/img/image_box_1/4.jpg">
                        <div>
                          <a class="box-image__bg" href="/resources/assets/img/image_box_1/4.jpg" style="background-image: url('/resources/assets/img/image_box_1/4.jpg');" data-effect="mfp-zoom-in"><img src="/resources/assets/img/image_box_1/4.jpg" alt="" />
                            <div class="box-image__info">
                              <h4 class="box-image__country">제주도</h4>
                              <p class="box-image__tour">273</p>
                            </div></a>
                        </div>
                      </div>
                      <!-- End / box-image -->

                    </div>
                  </div>
                </div>
                <div class="grid-item">
                  <div class="grid-item__inner">
                    <div class="grid-item__content-wrapper">

                      <!-- box-image -->
                      <div class="box-image" href="/resources/assets/img/image_box_1/5.jpg">
                        <div>
                          <a class="box-image__bg" href="/resources/assets/img/image_box_1/5.jpg" style="background-image: url('/resources/assets/img/image_box_1/5.jpg');" data-effect="mfp-zoom-in"><img src="/resources/assets/img/image_box_1/5.jpg" alt="" />
                            <div class="box-image__info">
                              <h4 class="box-image__country">여수</h4>
                              <p class="box-image__tour">266</p>
                            </div></a>
                        </div>
                      </div>
                      <!-- End / box-image -->

                    </div>
                  </div>
                </div>
                <div class="grid-item">
                  <div class="grid-item__inner">
                    <div class="grid-item__content-wrapper">

                      <!-- box-image -->
                      <div class="box-image" href="/resources/assets/img/image_box_1/6.jpg">
                        <div>
                          <a class="box-image__bg" href="/resources/assets/img/image_box_1/6.jpg" style="background-image: url('/resources/assets/img/image_box_1/6.jpg');" data-effect="mfp-zoom-in"><img src="/resources/assets/img/image_box_1/6.jpg" alt="" />
                            <div class="box-image__info">
                              <h4 class="box-image__country">전주</h4>
                              <p class="box-image__tour">128</p>
                            </div></a>
                        </div>
                      </div>
                      <!-- End / box-image -->

                    </div>
                  </div>
                </div>
                <div class="grid-item">
                  <div class="grid-item__inner">
                    <div class="grid-item__content-wrapper">

                      <!-- box-image -->
                      <div class="box-image" href="/resources/assets/img/image_box_1/7.jpg">
                        <div>
                          <a class="box-image__bg" href="/resources/assets/img/image_box_1/7.jpg" style="background-image: url('/resources/assets/img/image_box_1/7.jpg');" data-effect="mfp-zoom-in"><img src="/resources/assets/img/image_box_1/7.jpg" alt="" />
                            <div class="box-image__info">
                              <h4 class="box-image__country">경주</h4>
                              <p class="box-image__tour">196</p>
                            </div></a>
                        </div>
                      </div>
                      <!-- End / box-image -->

                    </div>
                  </div>
                </div>
                <div class="grid-item">
                  <div class="grid-item__inner">
                    <div class="grid-item__content-wrapper">

                      <!-- box-image -->
                      <div class="box-image" href="/resources/assets/img/image_box_1/8.jpg">
                        <div>
                          <a class="box-image__bg" href="/resources/assets/img/image_box_1/8.jpg" style="background-image: url('/resources/assets/img/image_box_1/8.jpg');" data-effect="mfp-zoom-in"><img src="/resources/assets/img/image_box_1/8.jpg" alt="" />
                            <div class="box-image__info">
                              <h4 class="box-image__country">가평</h4>
                              <p class="box-image__tour">290</p>
                            </div></a>
                        </div>
                      </div>
                      <!-- End / box-image -->

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End / Section -->


    <!-- Section -->
    <section class="awe-section">
      <div class="container">

        <!-- title -->
        <div class="title title__style-02">
          <h2 class="title__title">베스트 픽플</h2>
        </div>
        <!-- End / title -->

        <div class="grid-css grid_css_style_02 grid-css--masonry" data-col-lg="3" data-col-md="2" data-col-sm="2" data-col-xs="1" data-gap="30">
          <div class="filter">
            <ul class="filter__list">
              <li><a href="#" data-filter="*">All</a></li>
              <li><a href="#" data-filter=".cat1">추천순</a></li>
              <li><a href="#" data-filter=".cat2">최신순</a></li>
            </ul>
          </div>
          <div class="grid__inner">
            <div class="grid-sizer"></div>
            <div class="grid-item cat1">
              <div class="grid-item__inner">
                <div class="grid-item__content-wrapper">

                  <!-- box-image2 -->
                  <div class="box-image2">
                    <div>
                      <a class="box-image2__bg" href="#" style="background-image: url('/resources/assets/img/image_box_2/1.jpg');"><img src="/resources/assets/img/image_box_2/1.jpg" alt="" /></a>
                      <div class="box-image2__info">
                        <p class="box-image2__writer">박찬솔님</p>
                        <p class="box-image2__title">경주 여행 다녀왔습니다~</p>
                      </div>
                      <div class="box-image2__info_bot">
                        <span class="box-image2__date">2 Days / 3 Nights</span><a class="box-image2__like" href="#">View tour</a>
                      </div>
                    </div>
                  </div>
                  <!-- End / box-image2 -->

                </div>
              </div>
            </div>
            <div class="grid-item cat2">
              <div class="grid-item__inner">
                <div class="grid-item__content-wrapper">

                  <!-- box-image2 -->
                  <div class="box-image2">
                    <div>
                      <a class="box-image2__bg" href="#" style="background-image: url('/resources/assets/img/image_box_2/2.jpg');"><img src="/resources/assets/img/image_box_2/2.jpg" alt="" /></a>
                      <div class="box-image2__info">
                        <p class="box-image2__country">Hong Kong</p>
                        <p class="box-image2__tour">Classic Vietnam from Ho Chi Minh City</p>
                      </div>
                      <div class="box-image2__info_bot">
                        <span class="box-image2__date">7 Days / 8 Nights</span><a class="box-image2__view" href="#">View tour</a>
                      </div>
                    </div>
                  </div>
                  <!-- End / box-image2 -->

                </div>
              </div>
            </div>
            <div class="grid-item cat1">
              <div class="grid-item__inner">
                <div class="grid-item__content-wrapper">

                  <!-- box-image2 -->
                  <div class="box-image2">
                    <div>
                      <a class="box-image2__bg" href="#" style="background-image: url('/resources/assets/img/image_box_2/3.jpg');"><img src="/resources/assets/img/image_box_2/3.jpg" alt="" /></a>
                      <div class="box-image2__info">
                        <p class="box-image2__country">Ubud</p>
                        <p class="box-image2__tour">The Silk Road</p>
                      </div>
                      <div class="box-image2__info_bot">
                        <span class="box-image2__date">7 Days / 8 Nights</span><a class="box-image2__view" href="#">View tour</a>
                      </div>
                    </div>
                  </div>
                  <!-- End / box-image2 -->

                </div>
              </div>
            </div>
            <div class="grid-item cat2">
              <div class="grid-item__inner">
                <div class="grid-item__content-wrapper">

                  <!-- box-image2 -->
                  <div class="box-image2">
                    <div>
                      <a class="box-image2__bg" href="#" style="background-image: url('/resources/assets/img/image_box_2/4.jpg');"><img src="/resources/assets/img/image_box_2/4.jpg" alt="" /></a>
                      <div class="box-image2__info">
                        <p class="box-image2__country">Phi Phi Islands</p>
                        <p class="box-image2__tour">Dubai & Maldives Vacation incl. Airfare</p>
                      </div>
                      <div class="box-image2__info_bot">
                        <span class="box-image2__date">7 Days / 8 Nights</span><a class="box-image2__view" href="#">View tour</a>
                      </div>
                    </div>
                  </div>
                  <!-- End / box-image2 -->

                </div>
              </div>
            </div>
            <div class="grid-item cat1">
              <div class="grid-item__inner">
                <div class="grid-item__content-wrapper">

                  <!-- box-image2 -->
                  <div class="box-image2">
                    <div>
                      <a class="box-image2__bg" href="#" style="background-image: url('/resources/assets/img/image_box_2/5.jpg');"><img src="/resources/assets/img/image_box_2/5.jpg" alt="" /></a>
                      <div class="box-image2__info">
                        <p class="box-image2__country">Istanbul</p>
                        <p class="box-image2__tour">Discover Hong Kong and Bangkok incl. Airfare</p>
                      </div>
                      <div class="box-image2__info_bot">
                        <span class="box-image2__date">7 Days / 8 Nights</span><a class="box-image2__view" href="#">View tour</a>
                      </div>
                    </div>
                  </div>
                  <!-- End / box-image2 -->

                </div>
              </div>
            </div>
            <div class="grid-item cat2">
              <div class="grid-item__inner">
                <div class="grid-item__content-wrapper">

                  <!-- box-image2 -->
                  <div class="box-image2">
                    <div>
                      <a class="box-image2__bg" href="#" style="background-image: url('/resources/assets/img/image_box_2/6.jpg');"><img src="/resources/assets/img/image_box_2/6.jpg" alt="" /></a>
                      <div class="box-image2__info">
                        <p class="box-image2__country">Bangkok</p>
                        <p class="box-image2__tour">China Odyssey with Yangtze River Cruise</p>
                      </div>
                      <div class="box-image2__info_bot">
                        <span class="box-image2__date">7 Days / 8 Nights</span><a class="box-image2__view" href="#">View tour</a>
                      </div>
                    </div>
                  </div>
                  <!-- End / box-image2 -->

                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="text-center">
          <a class="md-btn mt-60 md-btn--primary md-btn--pill " href="#">Explore more </a>
        </div>
      </div>
    </section>
    <!-- End / Section -->

    <div class="container">



      <!-- title -->
      <div class="title title__style-03">
        <h5 class="title__title_small">Pic Map 2024</h5>
        <h2 class="title__title">Dubai & Maldives Vacation incl. Airfare</h2>
        <p class="title__text">
          Travel Blocking Period: March 28-30 or March 29-April 01, 2018 <br />Booking Period: Limited Seats <br />Hotel: Fragrance Imperial or similar class<br />Airlines: Via Cebupacific
        </p>
      </div>
      <!-- End / title -->

      <!-- End / Section -->

    </div>

    <!-- Section -->

    <!-- End / Section -->


    <!-- Section -->

    <!-- End / Section -->

  </div>
  <!-- End / Content-->

  <c:import url="./template/footer.jsp"></c:import>
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e11955080502d1bac37823f6b7f43b6&libraries=services"></script>
  <script src="/resources/assets/js/ping/pingList.js"></script>

</body>
</html>

