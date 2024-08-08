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
            .then(response => response.text())
            .then(response => {
                if (response.trim() === "success") {
                    alert("댓글 추가 성공");
                    commentContents.value = "";
                    location.reload();
                } else {
                    alert(response); // 서버에서 반환된 메시지 표시
                }
                })
                .catch(error => console.error("Error adding comment:", error));
        });
    }
    
    let updateButton;//업데이트 요소 저장할 변수 
    // 댓글 수정 버튼 클릭 이벤트
    document.addEventListener("click", (event) => { // 문서에 클릭 이벤트 리스너 추가
        if (event.target && event.target.classList.contains("update-button") && !event.target.classList.contains("complete")) { //클릭했을때 update버튼이 포함되고 complete가 포함되지않을때 실행 
            updateButton = event.target; //클릭된 업데이트 버튼 요소를 변수에 저장 

            const commentNum = event.target.getAttribute("data-comment-num"); // 수정할 댓글 ID 가져오기
            const pComment = document.getElementById(commentNum); //댓글요소를 ID로 가져오기
            
            event.target.classList.add("complete"); //업데이트 버튼에 complete 클래스 추가
            event.target.innerText = "완료"; //버튼텍스트를 완료로 변경 

            const commentInput = document.createElement("input");  //새로운 입력 요소 생성
            commentInput.id = "commentInput"; //입력 요소 id 생성
            const commentTmp = pComment.innerText; //현재 댓글의 내용을 임시변수 commentTmp에 저장
            commentInput.value = commentTmp; //입력요소의 값을 현재 댓글내용으로 설정
            pComment.innerText = ""; //댓글 내용을 지움
            pComment.append(commentInput); //입력요소를 댓글요소에 추가 
            commentInput.focus();//입력요소에 커서 깜박임

            commentInput.addEventListener("blur", (e) => { //입력요소에 블러이벤트리스너 추가
                if (e.relatedTarget != updateButton) { //블러이벤트가 업데이트버튼에 실행되지 않을때 
                    commentInput.remove(); //입력요소 제거 
                    pComment.innerText = commentTmp; //원래 댓글내용 복원

                    updateButton.classList.remove("complete"); //업데이트버튼에 complete 클래스 삭제
                    updateButton.innerText = "수정"; // 버튼텍스트를 수정으로 변경
                } else { //블러이벤트가 업데이트버튼에의해 실행될떄 
                    const newContent = document.getElementById("commentInput").value; // 새 댓글 내용 입력 받기
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
                        .then(response => response.text())
                        .then(response => {
                            if (response.trim() === "success") {
                                alert("댓글 수정 성공");
                                commentContents.value = "";
                                location.reload();
                            } else {
                                alert(response); // 서버에서 반환된 메시지 표시
                            }
                            })
                            .catch(error => console.error("Error updating comment:", error)); // 에러 처리

                    }
                    commentInput.remove(); //입력요소 제거 
                    pComment.innerText = newContent; //댓글 내용을 새내용으로 설정

                    updateButton.classList.remove("complete"); //업데이트버튼의 complete클래스 삭제
                    updateButton.innerText = "수정"; //버튼텍스트를 수정으로 변경
                }
            })
        }
    });

    // 댓글 삭제 버튼 클릭 이벤트
    document.addEventListener("click", (event) => {
        if (event.target && event.target.classList.contains("delete-button")) {
            const commentNum = event.target.getAttribute("data-comment-num");
            if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
                const url = "/comments/delete";
                const formData = new FormData();
                formData.append("commentNum", commentNum);

                fetch(url, {
                    method: "POST",
                    body: formData
                })
                .then(response => response.text())
                .then(response => {
                    if (response.trim() === "success") {
                        alert("댓글 삭제 성공");
                        commentContents.value = "";
                        location.reload();
                    } else {
                        alert(response); // 서버에서 반환된 메시지 표시
                    }
                    })
                    .catch(error => console.error("Error deleting comment:", error));
            }
        }
    });
});


