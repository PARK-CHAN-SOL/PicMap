document.addEventListener("click", (event) => {
    if (event.target.dataset.boardNum != null || event.target.dataset.boardNum != '') {
        let hitBtn = event.target;
        fetch('/heart/click?boardNum=' + hitBtn.dataset.boardNum, {
            method: "GET"
        })
            .then((res) => { return res.json(); })
            .then((res) => {
                if (res == 0) {// 좋아요 또는 좋아요 취소 실패

                } else if (res == -1000) {//로그인 하지 않음
                    //로그인 모달 표시
                    let loginModalHit = document.createElement('button');
                    loginModalHit.setAttribute('data-bs-toggle', 'modal');
                    loginModalHit.setAttribute('data-bs-target', 'exampleModal');
                    loginModalHit.click();
                } else {// 좋아요 또는 좋아요 취소 성공
                    if(res == -1){
                        //좋아요 표시 취소
                    }else {
                        //좋아요 표시
                    }
                    hitBtn.innerHTML = parseInt(hitBtn.innerHTML) + res;
                }
            })
            .catch((e) => {// 에러 발생
                console.log(e);
            })
    }
})

// 게시글 좋아요 수 조회, 게시글이 로딩될 때 호출 됨
fetch('/heart/count?boardNum=' + 1, { // boardNum 임시 1 
    method: "GET"
})
    .then((res) => { return res.json(); })
    .then((res) => {
        //res 값을 좋아요 숫자 태그 내부에 넣어줘야함
        //좋아요 숫자 태그.innerHTML = res

        // <div class="box-image2__info_bot">
        //     <span class="box-image2__date">${dto.createDate}</span>
        //     <span class="box-image2__like"> ------->> res <<------- </span> 
        // </div>
    })
    .catch((e) => {// 에러 발생
        console.log(e);
    })