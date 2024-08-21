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
<link rel="stylesheet" href="/resources/assets/css/mypage.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<c:import url="../template/header_css.jsp"></c:import>
<c:import url="../template/header_nav.jsp"></c:import>
</head>
<body>
<div class="hero" id="id-1">
        <div class="hero__wrapper">
          <div style="height: 400px;" class="bg-primary bg-gradient bg-opacity-25">
            <div class="container-fluid col-8 justify-contents-center fs-4">
            <table style="height: 300px;">
              <tbody>
                <tr>
                  <td class="align-bottom"><h1 style="font-style: italic; font-weight: 900; font-size: 80px;">해당 정보로 가입된 아이디입니다 </h1></td>
                </tr>
              </tbody>
            </table>
            <div style="font-family: Spiegel, sans-serif; font-weight: 400; font-size: 18px; line-height: 26px;"></div>
            </div>
          </div>
        </div>
</div>
    <div class="container">
        <div class="row">
            <div class="col">
			
            	<div class="container mt-5">
					<div class="row tm-content-row">
					  <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 tm-block-col">
						<div class="tm-bg-primary-dark tm-block tm-block-products">
							<div class="tm-product-table-container">
							<table class="table table-hover tm-table-small tm-product-table">
							  <thead>
								<tr>
								  <th scope="col"></th>
								  <th scope="col">이름</th>
								  <th scope="col">아이디</th>
								  <th scope="col"></th>
								</tr>
							  </thead>
							  <tbody>
									
										<tr>
											<td></td>
											<td>${memberDTO.memberName}</td>
											<td>${memberDTO.memberId}</td>
											<td><a href="/member/findPassword">비밀번호 찾기</a></td>
										</tr>
									
								</tbody>
							</table>
						  </div>
						  <!-- table container -->
						</div>
					  </div>
					  </div>
					</div>
				  </div>         	
    		</div>
		</div>
	
<c:import url="../template/footer.jsp"></c:import>
 <script type="text/javascript" src="/resources/assets/js/member/memberUpdate.js"></script>
</body>
</html>
