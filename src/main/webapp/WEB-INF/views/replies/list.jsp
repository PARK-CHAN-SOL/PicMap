<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="reply" items="${replies}">
	<div class="reply">
		<p>작성자: ${reply.memberNum}</p>
		<p id="reply-${reply.replyNum}" class="replyContents">${reply.content}</p>
		<p>작성일: ${reply.createDate}</p>
		<c:if test="${member.memberNum == reply.memberNum}">
		<button data-reply-num="${reply.replyNum}" class="comment-button reply-update-button">수정</button>
		<button data-reply-num="${reply.replyNum}" class="comment-button reply-delete-button">삭제</button>
		</c:if>
 	</div>
</c:forEach>
