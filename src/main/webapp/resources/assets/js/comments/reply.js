/**
 * 
 */
const isReplyLoaded = {};  // 객체를 사용하여 댓글 ID별로 답글이 로드되었는지 여부를 저장

document.addEventListener("DOMContentLoaded", () => {
    document.addEventListener("click", (event) => {
        if (event.target && event.target.classList.contains("reply-button")) { // 클릭된 요소가 reply-button 클래스를 포함하는지 확인
            const commentNum = event.target.getAttribute("data-comment-num"); // 클릭된 댓글의 ID를 가져옴
            const replyForm = document.getElementById(`replyForm${commentNum}`); // 해당 댓글의 답글 폼 요소를 가져옴
            const replyList = document.getElementById(`replyList${commentNum}`); // 해당 댓글의 답글 리스트를 표시할 요소를 가져옴

            // 답글 폼의 표시 상태를 토글 (보이기/숨기기)
            replyForm.style.display = replyForm.style.display === "none" ? "block" : "none";

            // 이미 해당 댓글의 답글이 로드되었는지 확인
            if (isReplyLoaded[commentNum]) {
                return;  // 이미 로드된 경우 추가 요청 방지
            }

            const url = "/replies/list"; // 답글 목록을 가져올 URL
            const formData = new FormData(); // 폼 데이터 객체 생성
            formData.append("commentNum", commentNum); // 폼 데이터에 댓글 ID 추가

            fetch(url, { // 서버에 POST 요청을 보냄
                method: "POST", // POST 메서드를 사용하여 요청
                body: formData // 요청 본문에 폼 데이터 추가
            })
            .then(response => response.text()) // 응답을 텍스트 형식으로 변환
            .then(response => {
                replyList.innerHTML = response; // 답글 리스트를 응답으로 받은 HTML로 채움
                isReplyLoaded[commentNum] = true;  // 답글이 로드되었음을 기록
            })
            .catch(error => console.error("Error adding reply:", error)); // 에러 처리
        }
    });
});

document.addEventListener("click", (event) => {
    if (event.target && event.target.classList.contains("comment-textarea")) { // 클릭된 요소가 comment-textarea 클래스를 포함하는지 확인
        const memberNum = document.getElementById('commentsObserverTarget').dataset.memberNum; // 현재 로그인한 사용자의 ID를 가져옴

        if (!memberNum) { // 사용자가 로그인하지 않은 상태일 때
            document.getElementById("modalBtn").click(); // 로그인 모달을 띄움
            event.target.blur(); // 텍스트 영역에서 포커스 제거
        }
    }
});

document.addEventListener("click", (event) => {
    if (event.target && event.target.classList.contains("reply-submit-button")) { // 클릭된 요소가 reply-submit-button 클래스를 포함하는지 확인
        const commentNum = event.target.getAttribute("data-comment-num"); // 답글을 추가할 댓글 ID를 가져옴
        const replyContents = document.getElementById(`replyContents${commentNum}`).value; // 답글 내용을 가져옴
        const memberNum = document.getElementById('commentsObserverTarget').dataset.memberNum; // 현재 로그인한 사용자의 ID를 가져옴

        if (!memberNum) {
            // 로그인하지 않은 상태이므로 로그인 모달을 띄움
            document.getElementById("modalBtn").click();
            return; // 함수 종료
        }

        if (replyContents.trim() === "") { // 답글 내용이 비어 있는지 확인
            alert("답글을 입력하세요"); // 경고 메시지 표시
            return; // 함수 종료
        }

        const url = "/replies/add"; // 답글 추가 URL
        const formData = new FormData(); // 폼 데이터 객체 생성
        formData.append("content", replyContents); // 폼 데이터에 답글 내용 추가
        formData.append("commentNum", commentNum); // 폼 데이터에 댓글 ID 추가

        fetch(url, { // 서버에 POST 요청을 보냄
            method: "POST", // POST 메서드를 사용하여 요청
            body: formData // 요청 본문에 폼 데이터 추가
        })
        .then(response => response.text()) // 응답을 텍스트 형식으로 변환
        .then(response => {
            if (response.trim() === "success") { // 성공 응답을 받으면
                alert("답글 추가 성공");
                location.reload(); // 페이지 새로고침
            } else {
                alert(response); // 서버에서 반환된 메시지 표시
            }
        })
        .catch(error => console.error("Error adding reply:", error)); // 에러 처리
    }
});

let replyUpdateButton; // 업데이트 버튼을 저장할 변수
document.addEventListener("click", (event) => {
    if (event.target && event.target.classList.contains("reply-update-button") && !event.target.classList.contains("complete")) { // 클릭된 요소가 reply-update-button 클래스를 포함하고, complete 클래스가 없는지 확인
        replyUpdateButton = event.target; // 클릭된 업데이트 버튼 요소를 변수에 저장
        const replyNum = event.target.getAttribute("data-reply-num"); // 수정할 답글 ID를 가져옴
        const pReply = document.getElementById(`reply-${replyNum}`); // 수정할 답글 요소를 ID로 가져옴

        if (pReply) {
            replyUpdateButton.classList.add("complete"); // 업데이트 버튼에 complete 클래스 추가
            replyUpdateButton.innerText = "완료"; // 버튼 텍스트를 완료로 변경

            const replyInput = document.createElement("input"); // 새로운 입력 요소 생성
            replyInput.id = "replyInput"; // 입력 요소 ID 생성
            const replyTmp = pReply.innerText; // 현재 답글 내용을 임시 변수에 저장
            replyInput.value = replyTmp; // 입력 요소의 값을 현재 답글 내용으로 설정
            pReply.innerText = ""; // 답글 내용을 지움
            pReply.append(replyInput); // 입력 요소를 답글 요소에 추가
            replyInput.focus(); // 입력 요소에 포커스 이동

            replyInput.addEventListener("blur", (e) => { // 입력 요소에 블러 이벤트 리스너 추가
                console.log(e);
                if (e.relatedTarget != replyUpdateButton) { // 블러 이벤트가 업데이트 버튼에 의해 발생하지 않은 경우
                    replyInput.remove(); // 입력 요소 제거
                    pReply.innerText = replyTmp; // 원래 답글 내용 복원

                    replyUpdateButton.classList.remove("complete"); // 업데이트 버튼의 complete 클래스 삭제
                    replyUpdateButton.innerText = "수정"; // 버튼 텍스트를 수정으로 변경
                } else {
                    const newContent = document.getElementById("replyInput").value; // 새 답글 내용 입력 받기
                    if (newContent && newContent.trim() !== "") { // 입력된 내용이 비어 있지 않으면
                        const url = "/replies/update"; // 답글 수정 URL
                        const formData = new FormData(); // 폼 데이터 객체 생성
                        formData.append("replyNum", replyNum); // 폼 데이터에 답글 ID 추가
                        formData.append("content", newContent); // 폼 데이터에 수정된 내용 추가

                        fetch(url, { // 서버에 POST 요청을 보냄
                            method: "POST", // POST 메서드를 사용하여 요청
                            body: formData // 요청 본문에 폼 데이터 추가
                        })
                        .then(response => response.text()) // 응답을 텍스트 형식으로 변환
                        .then(response => {
                            if (response.trim() === "success") { // 성공 응답을 받으면
                                alert("댓글 수정 성공");
                                location.reload(); // 페이지 새로고침
                            } else {
                                alert(response); // 서버에서 반환된 메시지 표시
                            }
                        })
                        .catch(error => console.error("Error updating reply:", error)); // 에러 처리
                    }
                    replyInput.remove(); // 입력 요소 제거
                    pReply.innerText = newContent; // 답글 내용을 새 내용으로 설정

                    replyUpdateButton.classList.remove("complete"); // 업데이트 버튼의 complete 클래스 삭제
                    replyUpdateButton.innerText = "수정"; // 버튼 텍스트를 수정으로 변경
                }
            });
        } else {
            console.error("pReply element not found"); // 답글 요소를 찾지 못한 경우 에러 출력
        }
    }
});

 // 댓글 삭제 버튼 클릭 이벤트
 document.addEventListener("click", (event) => {
    if (event.target && event.target.classList.contains("reply-delete-button")) { // 클릭된 요소가 reply-delete-button 클래스를 포함하는지 확인
        const replyNum = event.target.getAttribute("data-reply-num"); // 삭제할 답글 ID를 가져옴
        if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) { // 삭제 확인 메시지 표시
            const url = "/replies/delete"; // 답글 삭제 URL
            const formData = new FormData(); // 폼 데이터 객체 생성
            formData.append("replyNum", replyNum); // 폼 데이터에 답글 ID 추가

            fetch(url, { // 서버에 POST 요청을 보냄
                method: "POST", // POST 메서드를 사용하여 요청
                body: formData // 요청 본문에 폼 데이터 추가
            })
            .then(response => response.text()) // 응답을 텍스트 형식으로 변환
            .then(response => {
                if (response.trim() === "success") { // 성공 응답을 받으면
                    alert("댓글 삭제 성공");
                    location.reload(); // 페이지 새로고침
                } else {
                    alert(response); // 서버에서 반환된 메시지 표시
                }
            })
            .catch(error => console.error("Error deleting comment:", error)); // 에러 처리
        }
    }
});

 
 
 
