/**
 * 
 */
 
 
 
 
	document.addEventListener("DOMContentLoaded", () => {
    // 답글 버튼 클릭 이벤트
    document.addEventListener("click", (event) => {
        if (event.target && event.target.classList.contains("reply-button")) { // 클릭된 요소가 reply-button 클래스를 포함하는지 확인
            const commentNum = event.target.getAttribute("data-comment-num"); // 답글을 추가할 댓글 ID 가져오기
            const replyForm = document.getElementById(`replyForm${commentNum}`); // 해당 댓글의 답글 폼 요소 가져오기
            replyForm.style.display = replyForm.style.display === "none" ? "block" : "none"; // 답글 폼 표시/숨기기 토글
        }
    });

    // 답글 등록 버튼 클릭 이벤트
    document.addEventListener("click", (event) => {
        if (event.target && event.target.classList.contains("reply-submit-button")) { // 클릭된 요소가 reply-submit-button 클래스를 포함하는지 확인
            const commentNum = event.target.getAttribute("data-comment-num"); // 답글을 추가할 댓글 ID 가져오기
            const replyContents = document.getElementById(`replyContents${commentNum}`).value; // 답글 내용 가져오기

            if (replyContents.trim() === "") { // 답글 내용이 비어 있는지 확인
                alert("답글을 입력하세요"); // 경고 메시지 표시
                return;
            }

            const url = "/replies/add"; // 답글 추가 URL
            const formData = new FormData(); // 폼 데이터 객체 생성
            formData.append("content", replyContents); // 폼 데이터에 답글 내용 추가
            formData.append("commentNum", commentNum); // 폼 데이터에 댓글 ID 추가

            fetch(url, { // fetch API를 사용해 서버에 POST 요청을 보냄
                method: "POST", // 요청 방법 설정
                body: formData // 요청 본문에 폼 데이터 추가
            })
                .then(response => { // 응답 처리
                    if (!response.ok) { // 응답이 성공적이지 않으면
                        throw new Error(`HTTP error! status: ${response.status}`); // 에러를 발생시킴
                    }
                    return response.text(); // 응답을 텍스트로 변환
                })
                .then(response => { // 변환된 응답 처리
                    if (response.trim() === "success") { // 응답이 "success"이면
                        alert("답글 추가 성공"); // 성공 메시지 표시
                        location.reload(); // 페이지 새로고침
                    } else { // 응답이 "success"가 아니면
                        alert("답글 추가 실패"); // 실패 메시지 표시
                    }
                })
                .catch(error => console.error("Error adding reply:", error)); // 에러 처리
        }
    });
});

 