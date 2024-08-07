/**
 * 
 */
 
	document.addEventListener("DOMContentLoaded", () => {
    const commentButton = document.getElementById("commentButton");
    const commentContents = document.getElementById("commentContents");
    const commentList = document.getElementById("commentList");

    console.log("DOM fully loaded and parsed");

    // 댓글 목록 가져오기 함수
    function getList() {
        console.log("Fetching comment list");
        const boardNum = commentButton ? commentButton.getAttribute("data-id") : null;
        if (!boardNum) {
            console.error("Board number not found");
            return;
        }
        console.log("BoardNum:", boardNum);

        fetch(`/comments/list?boardNum=${boardNum}`, {
            method: "GET"
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.text();
        })
        .then(response => {
            commentList.innerHTML = response;
            console.log("Comment list updated");
        })
        .catch(error => console.error("Error fetching comment list:", error));
    }

    // 페이지 로드 시 댓글 목록 가져오기
    if (commentButton) {
        getList();
    }

    // 댓글 등록 버튼 클릭 이벤트
    if (commentButton) {
        commentButton.addEventListener("click", () => {
            const content = commentContents.value;
            console.log("Comment button clicked, content:", content);

            if (content.trim() === "") {
                alert("댓글을 입력하세요");
                return;
            }

            const url = "/comments/add";
            const formData = new FormData();
            formData.append("content", content);
            formData.append("boardNum", commentButton.getAttribute("data-id"));

            console.log("Sending POST request to add comment");

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
                console.log("Response received from server:", response);
                if (response.trim() === "success") {
                    alert("댓글 추가 성공");
                    commentContents.value = "";
                    getList();
                } else {
                    alert("댓글 추가 실패");
                }
            })
            .catch(error => console.error("Error adding comment:", error));
        });
    }
});
	


 