/**
 * 
 */
 
 
 
 
	document.addEventListener("DOMContentLoaded", () => {
    // 답글 버튼 클릭 이벤트 (리스트)
    document.addEventListener("click", (event) => {
        if (event.target && event.target.classList.contains("reply-button")) { // 클릭된 요소가 reply-button 클래스를 포함하는지 확인
            const commentNum = event.target.getAttribute("data-comment-num"); // 답글을 추가할 댓글 ID 가져오기
            const replyForm = document.getElementById(`replyForm${commentNum}`); // 해당 댓글의 답글 폼 요소 가져오기
            const replyList = document.getElementById(`replyList${commentNum}`); // 해당 댓글의 답글 리스트

            replyForm.style.display = replyForm.style.display === "none" ? "block" : "none"; // 답글 폼 표시/숨기기 토글


            const url = "/replies/list"; // 답글 추가 URL
            const formData = new FormData(); // 폼 데이터 객체 생성
            formData.append("commentNum", commentNum); // 폼 데이터에 댓글 ID 추가

            fetch(url, { // fetch API를 사용해 서버에 POST 요청을 보냄
                method: "POST", // 요청 방법 설정
                body: formData // 요청 본문에 폼 데이터 추가
            })
            .then(response => response.text())
            .then(response => {
                replyList.innerHTML = response;
                })
                .catch(error => console.error("Error adding reply:", error)); // 에러 처리

           
    }
    })

    // 답글  클릭 이벤트 (추가)
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
            .then(response => response.text())
            .then(response => {
                if (response.trim() === "success") {
                    alert("답글 추가 성공");
                    location.reload();
                } else {
                    alert(response);
                
                    }
                })
                .catch(error => console.error("Error adding reply:", error)); // 에러 처리
        }
    });
});
let replyUpdateButton
document.addEventListener("click", (event) => {
    if (event.target && event.target.classList.contains("reply-update-button") && !event.target.classList.contains("complete")) {
        replyUpdateButton = event.target;
        const replyNum = event.target.getAttribute("data-reply-num");
        const pReply = document.getElementById(`reply-${replyNum}`);

        if (pReply) {
            replyUpdateButton.classList.add("complete");
            replyUpdateButton.innerText = "완료";

            const replyInput = document.createElement("input");
            replyInput.id = "replyInput";
            const replyTmp = pReply.innerText;
            replyInput.value = replyTmp;
            pReply.innerText = "";
            pReply.append(replyInput);
            replyInput.focus();

            replyInput.addEventListener("blur", (e) => {
                console.log(e);
                if (e.relatedTarget != replyUpdateButton) {
                    replyInput.remove();
                    pReply.innerText = replyTmp;

                    replyUpdateButton.classList.remove("complete");
                    replyUpdateButton.innerText = "수정";
                } else {
                    const newContent = document.getElementById("replyInput").value;
                    if (newContent && newContent.trim() !== "") {
                        const url = "/replies/update";
                        const formData = new FormData();
                        formData.append("replyNum", replyNum);
                        formData.append("content", newContent);

                        fetch(url, {
                            method: "POST",
                            body: formData
                        })
                        .then(response => response.text())
                        .then(response => {
                            if (response.trim() === "success") {
                                alert("댓글 수정 성공");
                                location.reload();
                            } else {
                                alert(response);
                            }
                        })
                        .catch(error => console.error("Error updating reply:", error));
                    }
                    replyInput.remove();
                    pReply.innerText = newContent;

                    replyUpdateButton.classList.remove("complete");
                    replyUpdateButton.innerText = "수정";
                }
            });
        } else {
            console.error("pReply element not found");
        }
    }
});
 
 // 댓글 삭제 버튼 클릭 이벤트
 document.addEventListener("click", (event) => {
    if (event.target && event.target.classList.contains("reply-delete-button")) {
        const replyNum = event.target.getAttribute("data-reply-num");
        if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
            const url = "/replies/delete";
            const formData = new FormData();
            formData.append("replyNum", replyNum);

            fetch(url, {
                method: "POST",
                body: formData
            })
            .then(response => response.text())
            .then(response => {
                if (response.trim() === "success") {
                    alert("댓글 삭제 성공");
                    
                    location.reload();
                } else {
                    alert(response); // 서버에서 반환된 메시지 표시
                }
                })
                .catch(error => console.error("Error deleting comment:", error));
        }
    }
});
