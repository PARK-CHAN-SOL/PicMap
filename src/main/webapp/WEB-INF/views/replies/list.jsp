<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="reply" items="${replies}">
	<div class="reply">
		<a href="/member/mypage?memberNum=${reply.memberNum}" title="프로필 보기">
		<img src="${reply.profilePath}" alt="프로필 이미지" class="profile-image"/>
		</a>
		<p>작성자: ${reply.memberNum}</p>
		<p id="reply-${reply.replyNum}" class="replyContents">${reply.content}</p>
		<p>작성일: ${reply.createDate}</p>
		<c:if test="${member.memberNum == reply.memberNum}">
		<button data-reply-num="${reply.replyNum}" class="comment-button reply-update-button">수정</button>
		<button data-reply-num="${reply.replyNum}" class="comment-button reply-delete-button">삭제</button>
		</c:if>
 	</div>
</c:forEach>

<c:if test="${replies.size() == 10}">
    <button class="load-more-replies" data-comment-num="${commentNum}" data-start-row="${nextStartRow}">더보기</button>
</c:if>



