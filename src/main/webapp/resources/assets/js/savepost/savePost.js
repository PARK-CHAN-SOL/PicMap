
document.addEventListener("click", (event) => {
    if (event.target.dataset.idSavepost != null && event.target.dataset.idSavepost != '') {
        let savePostButton = event.target; 
        fetch('/savepost/click?boardNum=' + savePostButton.getAttribute("data-id-savepost"), {
            method: "GET"
        })
            .then((res) => { return res.json(); })
            .then((res) => {
                if (res == 0) {// 게시물 저장 실패

                } else if (res == -1000) {//로그인 하지 않음
                    //로그인 모달 표시
                    document.querySelector('button[data-bs-target="#exampleModal" ]').click();
                } else {// 게시물 저장 성공
                    if(res == -1){
                        //게시물 저장 취소
                        savePostButton.classList.remove('saved');
                    }else {
                        //게시물 저장
                        savePostButton.classList.add('saved');
                    }
                }
            })
            .catch((e) => {// 에러 발생
                console.log(e);
            })
    }
})


//게시글을 불러올 때 저장한 게시물인지 판별하여 아이콘 선택  
fetch('/savepost/check?boardNum=' + savePostButton.getAttribute("data-id-savepost"), { 
    method: "GET"
})
    .then((res) => { return res.json(); })
    .then((res) => {
		if(res == 0){
        	//이미 저장
        	savePostButton.classList.add('saved');
    	}
    })
    .catch((e) => {// 에러 발생
        console.log(e);
    })