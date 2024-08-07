<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>댓글 목록</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/css/comments.css">
    <link rel="stylesheet" type="text/css" id="app-stylesheet" href="/resources/assets/css/main.css">
    <c:import url="../template/header_css.jsp"></c:import>
</head>
<body>
    <c:import url="../template/header_nav.jsp"></c:import>
    <div class="comments-container mt-5">
        <h2>댓글 목록</h2>
        <div id="commentForm">
            <c:choose>
                <c:when test="${empty member}">
                	<textarea id="commentContents" name="comment" placeholder="댓글을 입력하세요" class="comment-textarea" onclick="modalBtn.click()"></textarea>
                    <button id="modalBtn" type="button" class="md-btn md-btn--primary" data-bs-toggle="modal" data-bs-target="#exampleModal">로그인 후 댓글을 남겨주세요</button>
                </c:when>
                <c:otherwise>
                    <textarea id="commentContents" name="comment" placeholder="댓글을 입력하세요" class="comment-textarea"></textarea>
                    <button id="commentButton" type="button" class="comment-button" data-id="${boardNum}">댓글 남기기</button>
                </c:otherwise>
            </c:choose>
       
    </div>
    
    <c:forEach var="comment" items="${comments}">
    <div class="comment">
        <p>작성자: ${comment.memberNum}</p>
        <p id="${comment.commentNum}" class="comment-content">${comment.content}</p>
        <p>작성일: ${comment.createDate}</p>
        <c:if test="${member.memberNum == comment.memberNum}">
        	<button data-comment-num="${comment.commentNum}" class="comment-button update-button">수정</button>
            <button data-comment-num="${comment.commentNum}" class="comment-button delete-button">삭제</button>
        </c:if>
        <c:if test="${member.memberNum != null}">
        	<button data-comment-num="${comment.commentNum}" class="comment-button reply-button">답글</button>
        	<div id="replyForm${comment.commentNum}" class="reply-form" style="display:none;">
                    <textarea id="replyContents${comment.commentNum}" name="reply" placeholder="답글을 입력하세요" class="comment-textarea"></textarea>
                    <button data-comment-num="${comment.commentNum}" class="comment-button reply-submit-button">답글 남기기</button>
            </div>
    	</c:if>
    	
    </div>
</c:forEach>
</div>    

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/comments/reply.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/comments/commentslist.js"></script>
</body>
</html>
