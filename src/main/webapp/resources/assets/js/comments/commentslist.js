/**
 * 
 */
 
	document.addEventListener("DOMContentLoaded", () => {
    const commentButton = document.getElementById("commentButton");
    const commentContents = document.getElementById("commentContents");
   	const modalBtn = document.getElementById("modalBtn");
   

 
    // 댓글 등록 버튼 클릭 이벤트
    if (commentButton) {
        commentButton.addEventListener("click", () => {
            const content = commentContents.value;
            if (content.trim() === "") {
                alert("댓글을 입력하세요");
                return;
            }

            const url = "/comments/add";
            const formData = new FormData();
            formData.append("content", content);
            formData.append("boardNum", commentButton.getAttribute("data-id"));

            fetch(url, {
                method: "POST",
                body: formData
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.text();
            })
            .then(response => {
                if (response.trim() === "success") {
                    alert("댓글 추가 성공");
                    commentContents.value = "";
                    
                } else {
                    alert("댓글 추가 실패");
                }
            })
            .catch(error => console.error("Error adding comment:", error));
        });
    }
 // 댓글 수정 버튼 클릭 이벤트
    document.addEventListener("click", (event) => { // 문서에 클릭 이벤트 리스너 추가
        if (event.target && event.target.classList.contains("update-button")) { // 클릭된 요소가 update-button 클래스 포함 여부 확인
            const commentNum = event.target.getAttribute("data-comment-num"); // 수정할 댓글 ID 가져오기
            const newContent = prompt("댓글을 수정하세요:"); // 새 댓글 내용 입력 받기
            if (newContent && newContent.trim() !== "") { // 입력된 내용이 비어있지 않으면
                const url = "/comments/update"; // 댓글 수정 URL
                const formData = new FormData(); // 폼 데이터 객체 생성
                formData.append("commentNum", commentNum); // 폼 데이터에 댓글 ID 추가
                formData.append("content", newContent); // 폼 데이터에 수정된 내용 추가
                formData.append("boardNum", commentButton.getAttribute("data-id")); // 폼 데이터에 게시물 ID 추가

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
                        alert("댓글 수정 성공"); // 성공 메시지 표시
                        
                    } else { // 응답이 "success"가 아니면
                        alert("댓글 수정 실패"); // 실패 메시지 표시
                    }
                })
                .catch(error => console.error("Error updating comment:", error)); // 에러 처리
            }
        }
    });
    
});


 