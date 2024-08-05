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
<link rel="stylesheet" href="/resources/assets/css/mypage.css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<c:import url="../template/header_css.jsp"></c:import>
    <c:import url="../template/header_nav.jsp"></c:import>

</head>
<body>

<div class="container">
  <div class="card overflow-hidden">
    <div class="card-body p-0">
      <img src="/resources/assets/img/mypageback.png" alt="" class="img-fluid">
      <div class="row align-items-center">

       <div >
          <div class="mt-n5">
            <div class="d-flex align-items-center justify-content-center mb-2">
              <div class="linear-gradient d-flex align-items-center justify-content-center rounded-circle" style="width: 110px; height: 110px;">
                <div class="border border-4 border-white d-flex align-items-center justify-content-center rounded-circle overflow-hidden" style="width: 100px; height: 100px;">
              <c:if test="${not empty member.profilePath}">
                <img src="/resources/upload/members/${member.profilePath}" onerror="this.src='/resources/upload/members/default.png'" alt="" class="w-100 h-100">
              </c:if>
              <c:if test="${empty member.profilePath}"> 
                <img src="/resources/upload/members/default.png" alt="" class="w-100 h-100">
            </c:if>
                  
                </div>
              </div>
            </div>
            <div class="text-center">
              <h5 class="fs-5 mb-0 fw-semibold">${member.memberNickName}</h5>
              <p class="mb-0 fs-4">${member.memberEmail}</p>
            </div>
          </div>
        </div>
       
    <form action="/member/update" method="post"  enctype="multipart/form-data">
    <div>
       <div class="col-12">
        <label for="inputName" class="form-label">이름</label>
        <input type="text" class="form-control" id="inputName" name="memberName" value="${member.memberName}">
      </div>
          <div class="col-12">
        <label for="inputPhone" class="form-label">생년월일</label>
        <input type="date" class="form-control" id="memberBirth" name="memberBirth" value="${member.memberBirth}">
      </div>
          <div class="col-12">
        <label for="inputPhone" class="form-label">닉네임</label>
        <input type="text" class="form-control" id="memberNickName" name="memberNickName" value="${member.memberNickName}">
      </div>
          <div class="col-12">
        <label for="inputPhone" class="form-label">이메일</label>
        <input type="email" class="form-control" id="memberEmail" name="memberEmail" value="${member.memberEmail}">
      </div>
       <div class="col-12">
        <label for="inputPhone" class="form-label">전화번호</label>
        <input type="text" class="form-control" id="inputPhone" name="memberPhone" value="${member.memberPhone}">
      </div>
        <div class="col-12">
	<input type="file"  name="filesUpdate" >
		
      </div>
      
      <div class="col-12">
        <button class="btn btn-primary" type="submit">수정하기</button>
      </div>
     
    </div>
   
    </form>
    
    <div>
  


    
    </div>
</div>
</div>
</div>
</div>
    <c:import url="../template/footer.jsp"></c:import>

</body>
</html>

