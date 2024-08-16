/**
 * 
 */
const commentsList = document.getElementById("commentsList"); // 댓글 목록을 담는 요소를 가져옴

const travelObserverTarget = document.getElementById("commentsObserverTarget"); // IntersectionObserver가 관찰할 대상 요소를 가져옴

let totalCount; // 총 댓글 수를 저장할 변수
fetch('/comments/getTotalCount?boardNum=' + travelObserverTarget.dataset.boardNum, { // 서버에 총 댓글 수를 요청
    method: "GET" // GET 메서드를 사용하여 요청
})
.then((res)=>{return res.json();}) // 응답을 JSON 형식으로 변환
.then((res)=>{
    
    totalCount = res; // 총 댓글 수를 totalCount 변수에 저장
    const observer = new IntersectionObserver((items)=>{ // IntersectionObserver를 생성, items는 관찰된 요소의 배열
        items.forEach((item)=>{ // 관찰된 각 요소에 대해 반복
            if(!item.isIntersecting) return; // 요소가 뷰포트에 들어오지 않았으면 함수 종료
            if(travelObserverTarget.dataset.startRow <= totalCount){ // startRow가 totalCount보다 작거나 같을 때만 실행
                travelObserverTarget.classList.add('loader');
                const formData = new FormData(); // 폼 데이터를 생성
                formData.append("boardNum", travelObserverTarget.dataset.boardNum); // 폼 데이터에 게시글 번호 추가
                formData.append("startRow", travelObserverTarget.dataset.startRow); // 폼 데이터에 시작 행 번호 추가
                formData.append("endRow", travelObserverTarget.dataset.endRow); // 폼 데이터에 끝 행 번호 추가
                const url = '/comments/list'; // 댓글 목록을 가져올 URL
                fetch(url, { // fetch API를 사용해 서버에 POST 요청
                    method: "POST", // POST 메서드를 사용하여 요청
                    body: formData // 요청 본문에 폼 데이터 추가
                })
                .then((res) => {return res.json();}) // 응답을 JSON 형식으로 변환
                .then((commentDTOs) => {
                    
                    commentDTOLoop(commentDTOs);
                    commentsObserverTarget.dataset.startRow = parseInt(commentsObserverTarget.dataset.startRow)+10; // startRow 값을 10 증가
                    commentsObserverTarget.dataset.endRow = parseInt(commentsObserverTarget.dataset.endRow)+10; // endRow 값을 10 증가
                  
                })
            }
        })
    });
    
    observer.observe(travelObserverTarget); // commentsObserverTarget 요소를 관찰 시작
})

async function commentDTOLoop(commentDTOs) {
    try{
        let commentsTmp = '';
        for(let commentDTO of commentDTOs){
            const heartCommentsCount = await getHeartCommentsCount(commentDTO);
            const heartCommentsCheck = await getHeartCommentsCheck(commentDTO);

            let createDate = new Date(commentDTO.createDate); // 댓글 작성일을 Date 객체로 변환
            createDate = createDate.getFullYear() + '-' +  String(createDate.getMonth() + 1).padStart(2, '0') + '-' + String(createDate.getDate()).padStart(2, '0'); // 작성일을 YYYY-MM-DD 형식으로 변환
            let comment =
            '<div class="comment">' + // 댓글 컨테이너 시작
                '<a href="/member/mypage?memberNum=' + commentDTO.memberNum + '" class="link-tmp" title="프로필보기">' + // 프로필 이미지 링크 추가
                '<img src="' + commentDTO.profilePath + '" alt="' + commentDTO.memberNickName + '" class="profile-image profile-link" data-member-num="' + commentDTO.memberNum + '" />' + // 프로필 이미지 추가
                '</a>'+
                '<p>작성자: ' + commentDTO.memberNickName + '</p>' + // 댓글 작성자의 회원 번호를 표시
                '<p id="' + commentDTO.commentNum + '" class="comment-content">' + commentDTO.content + '</p>' + // 댓글 내용을 표시
                '<p>작성일: ' + createDate + '</p>'; // 댓글 작성일을 표시
                
                // 하트 아이콘 추가
                comment +=
                '<div class="heart-section">' +
                    '<span id="heartBtn-' + commentDTO.commentNum + '" class="heart-btn" style="width:21px;" data-id="' + commentDTO.commentNum + '">' +
                        '<i class="fas fa-heart" style="color: ' + (heartCommentsCheck == 0 ? 'red' : 'gray') + ';"></i> ' + // 하트 아이콘 색상을 좋아요 여부에 따라 다르게 설정
                        '<span class="heart-count">' + heartCommentsCount + '</span>' +  // heartCount가 undefined일 경우 0으로 대체
                    '</span>' +
                '</div>';


            if(travelObserverTarget.dataset.memberNum == commentDTO.memberNum){ // 현재 사용자가 댓글 작성자인 경우
                comment = comment +
                    '<button data-comment-num="' + commentDTO.commentNum + '" class="comment-button update-button">수정</button>' + // 수정 버튼 추가
                    '<button data-comment-num="' + commentDTO.commentNum + '" class="comment-button delete-button">삭제</button>'; // 삭제 버튼 추가
            } 
            
            // 모든 댓글에 답글 버튼 추가
                comment += 
                '<button data-comment-num="' + commentDTO.commentNum + '" class="comment-button reply-button">답글 ' +
                    (commentDTO.replyCount > 0 ? '(' + commentDTO.replyCount + ')' : '') + // 답글 수를 표시, 답글이 있는 경우만 숫자를 표시
                '</button>' + 
                '<div id="replyForm' + commentDTO.commentNum + '" class="reply-form" style="display:none;">' + // 답글 작성 폼, 기본적으로 숨겨져 있음
                    '<textarea id="replyContents' + commentDTO.commentNum + '" name="reply" placeholder="답글을 입력하세요" class="comment-textarea"></textarea>' + // 답글 입력란
                    '<button data-comment-num="' + commentDTO.commentNum + '" class="comment-button reply-submit-button">답글 남기기</button>' + // 답글 남기기 버튼
                    '<div id="replyList' + commentDTO.commentNum + '" class="reply-list"></div>' + // 답글 리스트를 표시할 요소
                '</div>'; // 답글 작성 폼 종료
                comment += '</div>'; // 댓글 컨테이너 종료
                commentsTmp += comment;
                            
        }
        travelObserverTarget.classList.remove('loader');
        commentsList.innerHTML = commentsList.innerHTML + commentsTmp; // 댓글 리스트에 새 댓글 추가
    } catch (error) {
        console.error('Error CommentDTOLoop', error);
    }
    
}

async function getHeartCommentsCount(commentDTO){
    try{
        const response = await fetch('/heartComments/count?commentNum=' + commentDTO.commentNum,{
            method:"GET"
        });

        return await response.json();

    } catch (error) {
        console.error('Error GetHeartCommentCount', error);
    }
}

async function getHeartCommentsCheck(commentDTO){
    try{
        const response = await fetch('/heartComments/check?commentNum=' + commentDTO.commentNum,{
            method:"GET"
        });

        return await response.json();

    } catch (error) {
        console.error('Error GetHeartCommentCheck', error);
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const commentButton = document.getElementById("commentButton"); // 댓글 등록 버튼 요소를 가져옴
    const commentContents = document.getElementById("commentContents"); // 댓글 내용 입력란 요소를 가져옴
    const modalBtn = document.getElementById("modalBtn"); // 로그인 모달 버튼 요소를 가져옴

    // 댓글 등록 버튼 클릭 이벤트
    if (commentButton) {
        commentButton.addEventListener("click", () => { // 댓글 등록 버튼 클릭 시 실행
            const content = commentContents.value; // 댓글 내용을 가져옴
            if (content.trim() === "") { // 댓글 내용이 비어 있으면 경고
                alert("댓글을 입력하세요");
                return;
            }

            const url = "/comments/add"; // 댓글 추가 URL
            const formData = new FormData(); // 폼 데이터 객체 생성
            formData.append("content", content); // 폼 데이터에 댓글 내용 추가
            formData.append("boardNum", commentButton.getAttribute("data-id")); // 폼 데이터에 게시글 ID 추가

            fetch(url, { // fetch API를 사용해 서버에 POST 요청
                method: "POST", // POST 메서드를 사용하여 요청
                body: formData // 요청 본문에 폼 데이터 추가
            })
            .then(response => response.text()) // 응답을 텍스트로 변환
            .then(response => {
                if (response.trim() === "success") { // 성공 응답을 받으면
                    alert("댓글 추가 성공");
                    commentContents.value = ""; // 댓글 입력란 초기화
                    location.reload(); // 페이지 새로고침
                } else {
                    alert(response); // 서버에서 반환된 메시지 표시
                }
            })
            .catch(error => console.error("Error adding comment:", error)); // 에러 처리
        });
    }})

    let memberNum;
        document.addEventListener("click", (event) => {
        const profilePopup = document.getElementById("profilePopup"); // 팝업 요소
    
        if (event.target && event.target.classList.contains("profile-link")) {
            event.preventDefault(); // 기본 링크 동작 방지
    
            const link = event.target.closest('.profile-link'); // 클릭된 링크를 가져옴
            const rect = link.getBoundingClientRect(); // 클릭된 링크의 위치 정보 가져오기
           
            profilePopup.style.top = `${event.layerY}px`; // 팝업의 위치 설정
            profilePopup.style.left = `${event.layerX}px`;
    
            // 닉네임 설정
            const nickname = link.getAttribute('alt'); // 이미지의 alt 텍스트를 닉네임으로 사용
            const path = event.target.src;
            profilePopup.querySelector('b').innerText = nickname;
            profilePopup.querySelector('img').src = path;

            // 팔로우 버튼과 회원 번호 설정
            const followButton = profilePopup.querySelector('#followButton');
            memberNum = link.getAttribute('data-member-num');

            

              // 현재 팔로우 상태를 확인하고 버튼 텍스트를 설정
            fetch(`/member/followCheck?toFollow=${memberNum}`)
            .then(response => response.json())
            .then(followCheck => {
                if (followCheck === -1) {
                    followButton.innerText = '팔로잉';
                    followButton.classList.remove('follow_btn');
                    followButton.classList.add('following_btn');
                    followButton.disabled = false;
                } else if (followCheck === 1){
                    followButton.innerText = '팔로우';
                    followButton.classList.remove('following_btn');
                    followButton.classList.add('follow_btn');
                    followButton.disabled = false;
                } else {
                    followButton.innerText = '팔로우';
                    followButton.classList.remove('following_btn');
                    followButton.classList.add('follow_btn');
                    followButton.disabled = true;
                }
            });

              


             // 프로필 보기 버튼 클릭 시 해당 사용자의 프로필로 이동
            profilePopup.querySelector('.view-profile-button').onclick = ()=>{
                location.href = event.target.closest('.link-tmp').href;
            };
            profilePopup.style.display = "block"; // 팝업 보이기
    }
    
        // 팝업 외부 클릭 시 닫기
        if (!profilePopup.contains(event.target) && !event.target.classList.contains("profile-link")) {
            profilePopup.style.display = "none"; // 팝업 숨기기
        }
    });
    
 // 팔로우 버튼 클릭 이벤트 처리
 followButton.onclick = () => {
    fetch(`/member/follow?toFollow=${memberNum}`)
        .then(response => response.json())
        .then(result => {
            if (result > 0) {
                if (followButton.innerText === '팔로우') {
                    followButton.innerText = '팔로잉';
                    followButton.classList.remove('follow_btn');
                    followButton.classList.add('following_btn');
                } else {
                    followButton.innerText = '팔로우';
                    followButton.classList.remove('following_btn');
                    followButton.classList.add('follow_btn');
                }
            }
        });
};

// 좋아요 하트 이벤트
document.addEventListener("click", (event) => {
    const heartBtn = event.target.closest('.heart-btn'); // 이벤트가 발생한 요소 중 .heart-btn 클래스가 포함된 가장 가까운 부모 요소를 찾습니다.

    if (heartBtn) { // heartBtn이 존재하는 경우에만 실행합니다.
        heartBtn.disabled = true; // 버튼 비활성화
        const commentNum = heartBtn.getAttribute("data-id");

        fetch('/heartComments/click?commentNum=' + commentNum, {
            method: "GET"
        })
        .then((res) => res.json())
        .then((res) => {
            //console.log("Response data: ", res); // 추가: 서버에서 반환된 데이터 로그
            if (res === 0) {
                // 좋아요 또는 좋아요 취소 실패
                alert("좋아요 처리에 실패했습니다.");
            } else if (res === -1000) {
                // 로그인하지 않은 경우
                alert("로그인이 필요합니다.");
            } else {
                // 성공적으로 좋아요 또는 취소 처리
                let heartCountSpan = heartBtn.querySelector('.heart-count');
                let currentCount = parseInt(heartCountSpan.innerHTML);
                    if (res === -1) {
                        // 좋아요 취소된 경우
                        heartCountSpan.innerHTML = currentCount - 1;
                    } else if (res === 1) {
                        // 좋아요 추가된 경우
                        heartCountSpan.innerHTML = currentCount + 1;
                    }
                
                
                // 하트 아이콘 색상 변경
                const heartIcon = heartBtn.querySelector('i');
                if (res === -1) {
                    heartIcon.style.color = 'gray'; // 좋아요 취소 시 색상 변경
                } else if(res === 1){
                    heartIcon.style.color = 'red'; // 좋아요 시 색상 변경
                }
            }
            heartBtn.disabled = false; // 처리 완료 후 버튼 활성화
        })
        .catch((error) => {
            console.error("오류가 발생했습니다:", error);
            alert("오류가 발생했습니다. 다시 시도해주세요.");
            
        });
    }
});
    


    
    let updateButton; // 업데이트 요소를 저장할 변수
    // 댓글 수정 버튼 클릭 이벤트
    document.addEventListener("click", (event) => { // 문서에 클릭 이벤트 리스너 추가
        if (event.target && event.target.classList.contains("update-button") && !event.target.classList.contains("complete")) { // 클릭한 요소가 update-button 클래스를 가지고 있고 complete 클래스가 없는 경우
            updateButton = event.target; // 클릭된 업데이트 버튼 요소를 변수에 저장

            const commentNum = event.target.getAttribute("data-comment-num"); // 수정할 댓글 ID 가져오기
            const pComment = document.getElementById(commentNum); // 댓글 요소를 ID로 가져오기
            
            event.target.classList.add("complete"); // 업데이트 버튼에 complete 클래스 추가
            event.target.innerText = "완료"; // 버튼 텍스트를 완료로 변경

            const commentInput = document.createElement("input"); // 새로운 입력 요소 생성
            commentInput.id = "commentInput"; // 입력 요소 ID 생성
            const commentTmp = pComment.innerText; // 현재 댓글의 내용을 임시 변수에 저장
            commentInput.value = commentTmp; // 입력 요소의 값을 현재 댓글 내용으로 설정
            pComment.innerText = ""; // 댓글 내용을 지움
            pComment.append(commentInput); // 입력 요소를 댓글 요소에 추가
            commentInput.focus(); // 입력 요소에 포커스 이동

            commentInput.addEventListener("blur", (e) => { // 입력 요소에 블러 이벤트 리스너 추가
                if (e.relatedTarget != updateButton) { // 블러 이벤트가 업데이트 버튼에 의해 발생하지 않은 경우
                    commentInput.remove(); // 입력 요소 제거
                    pComment.innerText = commentTmp; // 원래 댓글 내용 복원

                    updateButton.classList.remove("complete"); // 업데이트 버튼의 complete 클래스 삭제
                    updateButton.innerText = "수정"; // 버튼 텍스트를 수정으로 변경
                } else { // 블러 이벤트가 업데이트 버튼에 의해 발생한 경우
                    const newContent = document.getElementById("commentInput").value; // 새 댓글 내용 입력 받기
                    if (newContent && newContent.trim() !== "") { // 입력된 내용이 비어 있지 않으면
                        const url = "/comments/update"; // 댓글 수정 URL
                        const formData = new FormData(); // 폼 데이터 객체 생성
                        formData.append("commentNum", commentNum); // 폼 데이터에 댓글 ID 추가
                        formData.append("content", newContent); // 폼 데이터에 수정된 내용 추가
                        formData.append("boardNum", commentButton.getAttribute("data-id")); // 폼 데이터에 게시물 ID 추가

                        fetch(url, { // fetch API를 사용해 서버에 POST 요청
                            method: "POST", // 요청 방법 설정
                            body: formData // 요청 본문에 폼 데이터 추가
                        })
                        .then(response => response.text()) // 응답을 텍스트로 변환
                        .then(response => {
                            if (response.trim() === "success") { // 성공 응답을 받으면
                                alert("댓글 수정 성공");
                                commentContents.value = ""; // 댓글 입력란 초기화
                                location.reload(); // 페이지 새로고침
                            } else {
                                alert(response); // 서버에서 반환된 메시지 표시
                            }
                        })
                        .catch(error => console.error("Error updating comment:", error)); // 에러 처리
                    }
                    commentInput.remove(); // 입력 요소 제거
                    pComment.innerText = newContent; // 댓글 내용을 새 내용으로 설정

                    updateButton.classList.remove("complete"); // 업데이트 버튼의 complete 클래스 삭제
                    updateButton.innerText = "수정"; // 버튼 텍스트를 수정으로 변경
                }
            });
        }
    });

    // 댓글 삭제 버튼 클릭 이벤트
    document.addEventListener("click", (event) => {
        if (event.target && event.target.classList.contains("delete-button")) { // 클릭한 요소가 delete-button 클래스를 가진 경우
            const commentNum = event.target.getAttribute("data-comment-num"); // 삭제할 댓글 ID 가져오기
            if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) { // 삭제 확인 메시지 표시
                const url = "/comments/delete"; // 댓글 삭제 URL
                const formData = new FormData(); // 폼 데이터 객체 생성
                formData.append("commentNum", commentNum); // 폼 데이터에 댓글 ID 추가

                fetch(url, { // fetch API를 사용해 서버에 POST 요청
                    method: "POST", // POST 메서드를 사용하여 요청
                    body: formData // 요청 본문에 폼 데이터 추가
                })
                .then(response => response.text()) // 응답을 텍스트로 변환
                .then(response => {
                    if (response.trim() === "success") { // 성공 응답을 받으면
                        alert("댓글 삭제 성공");
                        commentContents.value = ""; // 댓글 입력란 초기화
                        location.reload(); // 페이지 새로고침
                    } else {
                        alert(response); // 서버에서 반환된 메시지 표시
                    }
                })
                .catch(error => console.error("Error deleting comment:", error)); // 에러 처리
            }
        }
    });


'<div class="comment">' + // 댓글 컨테이너 시작
    '<p>작성자: ${comment.memberNum}</p>' + // 댓글 작성자의 회원 번호를 표시
    '<p id="${comment.commentNum}" class="comment-content">${comment.content}</p>' + // 댓글 내용을 표시
    '<p>작성일: ${comment.createDate}</p>' + // 댓글 작성일을 표시
    '<c:if test="${member.memberNum == comment.memberNum}">' + // 현재 사용자가 댓글 작성자인 경우
        '<button data-comment-num="${comment.commentNum}" class="comment-button update-button">수정</button>' + // 수정 버튼 추가
        '<button data-comment-num="${comment.commentNum}" class="comment-button delete-button">삭제</button>' + // 삭제 버튼 추가
    '</c:if>' +
    '<c:if test="${member.memberNum != null}">' + // 사용자가 로그인 상태인 경우
        '<button data-comment-num="${comment.commentNum}" class="comment-button reply-button">답글</button>' + // 답글 버튼 추가
        '<div id="replyForm${comment.commentNum}" class="reply-form" style="display:none;">' + // 답글 작성 폼, 기본적으로 숨겨져 있음
            '<textarea id="replyContents${comment.commentNum}" name="reply" placeholder="답글을 입력하세요" class="comment-textarea"></textarea>' + // 답글 입력란
            '<button data-comment-num="${comment.commentNum}" class="comment-button reply-submit-button">답글 남기기</button>' + // 답글 남기기 버튼
            '<div id="replyList${comment.commentNum}" class="reply-list"></div>' + // 답글 리스트를 표시할 요소
        '</div>' + // 답글 작성 폼 종료
    '</c:if>' +
'</div>'; // 댓글 컨테이너 종