/**
 * 
 */
const isReplyLoaded = {};  // 댓글 ID별로 답글이 로드되었는지 여부를 저장

// 공통 fetch 요청 함수
const sendFetchRequest = (url, method, formData) => {
    return fetch(url, {
        method: method,
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();
    });
};

document.addEventListener("DOMContentLoaded", () => {
    document.addEventListener("click", (event)=>{
        if(event.target && event.target.name == 'reply'){
            const memberNum = document.getElementById('commentsObserverTarget').dataset.memberNum;
            if (memberNum == '' || memberNum == null) {
                document.getElementById("modalBtn").click();
                return;
            }
        }
    })

    document.addEventListener("click", (event) => {
        if (event.target && event.target.classList.contains("reply-button")) {
            const commentNum = event.target.getAttribute("data-comment-num");
            const replyForm = document.getElementById(`replyForm${commentNum}`);
            const replyList = document.getElementById(`replyList${commentNum}`);

            replyForm.style.display = replyForm.style.display === "none" ? "block" : "none";

            if (isReplyLoaded[commentNum]) {
                return;
            }

            const url = "/replies/list";
            const formData = new FormData();
            formData.append("commentNum", commentNum);
            formData.append("startRow", 1); // 초기 startRow를 1로 설정

            sendFetchRequest(url, "POST", formData)
                .then(response => {
                    replyList.innerHTML = response;
                    isReplyLoaded[commentNum] = true;
                })
                .catch(error => console.error("Error fetching replies:", error));
        }
    });

    document.addEventListener("click", (event) => {
        if (event.target && event.target.classList.contains("load-more-replies")) {
            const commentNum = event.target.getAttribute("data-comment-num");
            const startRow = event.target.getAttribute("data-start-row");
            const replyList = document.getElementById(`replyList${commentNum}`);

            const url = "/replies/list";
            const formData = new FormData();
            formData.append('commentNum', commentNum);
            formData.append('startRow', startRow);

            sendFetchRequest(url, "POST", formData)
                .then(response => {
                    replyList.insertAdjacentHTML('beforeend', response);
                    event.target.remove();
                })
                .catch(error => console.error("Error loading more replies:", error));
        }
    });

    document.addEventListener("click", (event) => {
        if (event.target && event.target.classList.contains("reply-submit-button")) {
            const commentNum = event.target.getAttribute("data-comment-num");
            const replyContents = document.getElementById(`replyContents${commentNum}`).value;
            const memberNum = document.getElementById('commentsObserverTarget').dataset.memberNum;
            if (memberNum == '' || memberNum == null) {
                document.getElementById("modalBtn").click();
                return;
            }

            if (replyContents.trim() === "") {
                alert("답글을 입력하세요");
                return;
            }

            const url = "/replies/add";
            const formData = new FormData();
            formData.append("content", replyContents);
            formData.append("commentNum", commentNum);

            sendFetchRequest(url, "POST", formData)
                .then(response => {
                    if (response.trim() === "success") {
                        alert("답글 추가 성공");
                        location.reload();
                    } else {
                        alert(response);
                    }
                })
                .catch(error => console.error("Error adding reply:", error));
        }
    });

    let replyUpdateButton;
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

                            sendFetchRequest(url, "POST", formData)
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

    document.addEventListener("click", (event) => {
        if (event.target && event.target.classList.contains("reply-delete-button")) {
            const replyNum = event.target.getAttribute("data-reply-num");
            if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
                const url = "/replies/delete";
                const formData = new FormData();
                formData.append("replyNum", replyNum);

                sendFetchRequest(url, "POST", formData)
                    .then(response => {
                        if (response.trim() === "success") {
                            alert("댓글 삭제 성공");
                            location.reload();
                        } else {
                            alert(response);
                        }
                    })
                    .catch(error => console.error("Error deleting comment:", error));
            }
        }
    });
});
