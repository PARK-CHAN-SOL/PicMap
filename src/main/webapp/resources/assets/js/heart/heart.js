
const heartCount = document.getElementById("heartCount");

document.addEventListener("click", (event) => {
    if (event.target.dataset.idHeart != null && event.target.dataset.idHeart != '') {
        let heartButton = event.target; 
        fetch('/heart/click?boardNum=' + heartButton.getAttribute("data-id-heart"), {
            method: "GET"
        })
            .then((res) => { return res.json(); })
            .then((res) => {
                if (res == 0) {// 좋아요 또는 좋아요 취소 실패

                } else if (res == -1000) {//로그인 하지 않음
                    //로그인 모달 표시
                    document.querySelectorAll('button[data-bs-target="#exampleModal" ]')[0].click();
                } else {// 좋아요 또는 좋아요 취소 성공
                    if(res == -1){
                        //좋아요 표시 취소
                        heartButton.classList.remove('liked');
                    }else {
                        //좋아요 표시
                        heartButton.classList.add('liked');
                    }
                    heartCount.innerHTML = parseInt(heartCount.innerHTML) + res + '명이 좋아합니다';
                }
            })
            .catch((e) => {// 에러 발생
                console.log(e);
            })
    }
})

// 게시글 좋아요 수 조회, 게시글이 로딩될 때 호출 됨
fetch('/heart/count?boardNum=' + heartButton.getAttribute("data-id-heart"), { 
    method: "GET"
})
    .then((res) => { return res.json(); })
    .then((res) => {
        //res 값을 좋아요 숫자 태그 내부에 넣어줘야함
        heartCount.innerHTML = res + '명이 좋아합니다'
    })
    .catch((e) => {// 에러 발생
        console.log(e);
    })
    

    
//게시글을 불러올 때 좋아요가 체크되어 있는지 아닌지 판별하여 하트 모양 선택  
fetch('/heart/check?boardNum=' + heartButton.getAttribute("data-id-heart"), { 
    method: "GET"
})
    .then((res) => { return res.json(); })
    .then((res) => {
		if(res == 0){
        	//이미 좋아요를 누름
        	heartButton.classList.add('liked');
    	}
    })
    .catch((e) => {// 에러 발생
        console.log(e);
    })