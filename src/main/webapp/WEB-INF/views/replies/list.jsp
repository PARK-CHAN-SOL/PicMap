<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="reply" items="${replies}">
	<div class="reply">
		<a href="/member/mypage?memberNum=${reply.memberNum}" class='link-tmp' title="프로필 보기">
			<c:choose>
				<c:when test="${empty reply.profilePath || reply.profilePath eq null || reply.profilePath eq ''}">
					<img src="/resources/upload/members/default.png" alt="${reply.memberNickName}" class="profile-image profile-link" data-member-num="${reply.memberNum}"/>
				</c:when>
				<c:otherwise>
					<img src="${reply.profilePath}" alt="${reply.memberNickName}" class="profile-image profile-link" data-member-num="${reply.memberNum}"/>
				</c:otherwise>
			</c:choose>
		</a>
		<p>작성자: ${reply.memberNickName}</p>
		<p id="reply-${reply.replyNum}" class="replyContents">${reply.content}</p>
		<p>${reply.createDate eq reply.updateDate ? '작성일: ' += reply.createDate : '수정일: ' += reply.updateDate}</p>
		<c:if test="${sessionScope.member.memberNum == reply.memberNum}">
		<button data-reply-num="${reply.replyNum}" class="comment-button reply-update-button">수정</button>
		<button data-reply-num="${reply.replyNum}" class="comment-button reply-delete-button">삭제</button>
		</c:if>
 	</div>
</c:forEach>

<c:if test="${replyCount >= nextStartRow}">
    <button class="btn btn-primary load-more-replies" data-comment-num="${commentNum}" data-reply-count="${replyCount}" data-start-row="${nextStartRow}">더보기</button>
</c:if>



