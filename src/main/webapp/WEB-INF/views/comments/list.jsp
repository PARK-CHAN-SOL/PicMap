<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>댓글 목록</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/css/comments.css">
</head>
<body>
    <div class="comments-container">
        <h2>댓글 목록</h2>
        <div class="login-prompt">
            <a href="${pageContext.request.contextPath}/login" class="login-link">로그인 후 댓글을 남겨주세요</a>
            <a href="${pageContext.request.contextPath}/login" class="login-button">로그인</a>
        </div>
        <c:forEach var="comment" items="${comments}">
            <div class="comment">
                <p>작성자: ${comment.memberNum}</p>
                <p>${comment.content}</p>
                <p>작성일: ${comment.createDate}</p>
                <c:if test="${member.memberNum == comment.memberNum}">
                    <form method="post" action="${pageContext.request.contextPath}/comments/update" class="comment-form">
                        <input type="hidden" name="commentNum" value="${comment.commentNum}" />
                        <input type="hidden" name="boardNum" value="${boardNum}" />
                        <textarea name="content" class="comment-textarea">${comment.content}</textarea>
                        <input type="submit" value="수정" class="comment-button"/>
                    </form>
                    <form method="post" action="${pageContext.request.contextPath}/comments/delete" class="comment-form">
                        <input type="hidden" name="commentNum" value="${comment.commentNum}" />
                        <input type="hidden" name="boardNum" value="${boardNum}" />
                        <input type="submit" value="삭제" class="comment-button"/>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </div>
</body>
</html>


