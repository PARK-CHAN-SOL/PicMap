const travelList = document.getElementById("travelList");
const travelObserverTarget = document.getElementById("travelObserverTarget");
const travelObserver = new IntersectionObserver((items)=>{ // IntersectionObserver를 생성, items는 관찰된 요소의 배열
    items.forEach((item)=>{ // 관찰된 각 요소에 대해 반복
        if(!item.isIntersecting) return; // 요소가 뷰포트에 들어오지 않았으면 함수 종료
        if(travelObserverTarget.dataset.startRow <= 15){ // startRow가 travelTotalCount보다 작거나 같을 때만 실행
            const formData = new FormData(); // 폼 데이터를 생성
            formData.append("startRow", travelObserverTarget.dataset.startRow); // 폼 데이터에 시작 행 번호 추가
            formData.append("endRow", travelObserverTarget.dataset.endRow); // 폼 데이터에 끝 행 번호 추가
            const url = '/travel/list'; // 게시글 목록을 가져올 URL
            fetch(url, { // fetch API를 사용해 서버에 POST 요청
                method: "POST", // POST 메서드를 사용하여 요청
                body: formData // 요청 본문에 폼 데이터 추가
            })
            .then((res) => {return res.json();}) // 응답을 JSON 형식으로 변환
            .then((res) => {
                console.log(res); // 응답 결과를 콘솔에 출력
                res.forEach((boardDTO)=>{ // 각 게시글에 대해 반복
                    let createDate = new Date(boardDTO.createDate); // 게시글 작성일을 Date 객체로 변환
                        createDate = createDate.getFullYear() + '-' +  String(createDate.getMonth() + 1).padStart(2, '0') + '-' + String(createDate.getDate()).padStart(2, '0'); // 작성일을 YYYY-MM-DD 형식으로 변환
                    let gridItemDiv = document.createElement("div");
                    gridItemDiv.classList.add('grid-item');
                    gridItemDiv.classList.add('cat1');
                    let travel =
                            '<div class="grid-item__inner">'+
                                '<div class="grid-item__content-wrapper">'+
                                    '<div class="box-image2">'+
                                        '<div>';
                    
                    if(boardDTO.fileName == '' || boardDTO.fileName == null){
                        travel = travel +
                                            '<a class="box-image2__bg" href="detail?boardNum=' + boardDTO.boardNum + '#boardStartLocation"' +
                                                `style="background-image: url('/resources/upload/travels/default.png');">`+
                                            '</a>';
                    } else {
                        travel = travel +
                                            '<a class="box-image2__bg" href="detail?boardNum=' + boardDTO.boardNum + '#boardStartLocation"' +
                                                `style="background-image: url('/resources/upload/travels/` + boardDTO.fileName + `');">`+
                                            '</a>';
                    }

                    travel = travel +
                                            '<div class="box-image2__info">' + 
                                                '<p class="box-image2__writer">' + boardDTO.memberNickname + '</p>' +
                                                '<p class="box-image2__title">' + boardDTO.boardTitle + '</p>' +
                                            '</div>' +
                                            '<div class="box-image2__info_bot">'+
                                                '<span class="box-image2__date">' + createDate + '</span>'+
                                                '<span class="box-image2__like" data-board-num="' + boardDTO.boardNum + '">좋아요</span> '+
                                            '</div>'+
                                        '</div>'+
                                    '</div>'+
                                '</div>'+
                            '</div>';
                    gridItemDiv.innerHTML = travel;
                    $('.grid__inner').append(gridItemDiv);
                    $('.grid__inner').masonry('addItems', gridItemDiv);
                    $('.grid__inner').masonry('layout');
                    // travelList.innerHTML = travelList.innerHTML + travel;
                });
                travelObserverTarget.dataset.startRow = parseInt(travelObserverTarget.dataset.startRow)+9; // startRow 값을 9 증가
                travelObserverTarget.dataset.endRow = parseInt(travelObserverTarget.dataset.endRow)+9; // endRow 값을 9 증가
            })
        }
    })
});

travelObserver.observe(travelObserverTarget); // commentsObserverTarget 요소를 관찰 시작

// window.addEventListener('resize', ()=>{
//     let topPx = document.getElementsByClassName('grid__inner')[0].lastChild.style.top
//     topPx = topPx.substring(0, topPx.indexOf('px'));
//     console.log(parseInt(topPx) + 615);
//     document.getElementsByClassName('grid__inner')[0].style.height = (parseInt(topPx) + 615) + 'px';
// })