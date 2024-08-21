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
                  <td class="align-bottom"><h1 style="font-style: italic; font-weight: 900; font-size: 80px;">아이디 찾기</h1></td>
                </tr>
              </tbody>
            </table>
            <div style="font-family: Spiegel, sans-serif; font-weight: 400; font-size: 18px; line-height: 26px;"></div>
            </div>
          </div>
        </div>
</div>
    <form class="form" action="/member/findID" method="post">
   
        <div class="form-floating">
            <input type="text" class="form-control" id="memberName" name="memberName" placeholder="">
            <label for="memberName">이름</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="memberEmail" name="memberEmail" placeholder="">
            <label for="memberEmail">이메일</label>
        </div>

        <button class="btn btn-primary w-100 py-2" type="submit">아이디 찾기</button>

        <c:if test="${not empty errorMessage}">
            <div style="color: red; margin-top: 10px;">
                ${errorMessage}
            </div>
        </c:if>
    </form>
<c:import url="../template/footer.jsp"></c:import>
 <script type="text/javascript" src="/resources/assets/js/member/memberUpdate.js"></script>
</body>
</html>
