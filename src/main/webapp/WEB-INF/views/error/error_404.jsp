<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="reset.css" />
<c:import url="../template/header_css.jsp"></c:import>
<c:import url="../template/header_nav.jsp"></c:import>
<link rel="stylesheet" href="/resources/assets/css/banner.css">

<style>
.containerError {
	margin-top: 96px;
	width: 100%;
	height: 800px;
	text-align: center;
	background: url("/resources/assets/img/bg/image.png");
	background-size: contain;
	background-position: bottom;
	background-repeat: no-repeat;
}

@media ( min-width : 1201px) {
	.containerError {
		margin-top: 116px;
	}
}

.title {
	font-size: 50px;
}

.home {
	padding: 15px;
	border: none;
	position: absolute;
	right: 65%;
	top: 50%;
	width: 150px;
}
</style>
</head>
<body>
	<div class="containerError">
		<h5 class="title">잘못된 접근입니다</h5>

	</div>

	<div class="title title__style-03">
		<div id="wrap">
			<div class="wrap-box">
				<div class="rolling-list">
					<div class="list">Moments from your travels leave a special
						mark on our hearts, capturing the essence of your journey.</div>
					<div class="list">Moments from your travels leave a special
						mark on our hearts, capturing the essence of your journey.</div>
					<div class="list">Moments from your travels leave a special
						mark on our hearts, capturing the essence of your journey.</div>
				</div>
			</div>
		</div>
	</div>

	<c:import url="../template/footer.jsp"></c:import>
	<script src="/resources/assets/js/banner.js"></script>

</body>
</html>