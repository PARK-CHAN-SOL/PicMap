const mypageObserverTargetSave = document.getElementById("mypageObserverTargetSave");

const pillsFriends = document.getElementById("pills-friends");

// 변경 감지 시 실행할 콜백 함수
const mypageMutaionCallBack = (mutationList, observer) => {
    for (const mutation of mutationList) {
        if (mutation.type === "attributes") {
            if(mypageObserverTargetSave.style.display=='none' && pillsFriends.classList.length == 4){
                mypageObserverTarget.style.display='none';
                mypageObserverTargetSave.style.display='block';
            } else if (mypageObserverTargetSave.style.display!='none' && pillsFriends.classList.length == 2){
                mypageObserverTarget.style.display='block';
                mypageObserverTargetSave.style.display='none';
            }
        }
    }
};

const mypageMutaionObserver = new MutationObserver(mypageMutaionCallBack)

// 설정한 변경의 감지 시작
mypageMutaionObserver.observe(pillsFriends, { attributes: true, childList: false, subtree: false });



const travelObserverSave = new IntersectionObserver((items)=>{ // IntersectionObserver를 생성, items는 관찰된 요소의 배열
    items.forEach(async (item)=>{ // 관찰된 각 요소에 대해 반복
        if(!item.isIntersecting) return; // 요소가 뷰포트에 들어오지 않았으면 함수 종료
        if(mypageObserverTargetSave.dataset.startRow <= mypageObserverTargetSave.dataset.totalCount){ // startRow가 travelTotalCount보다 작거나 같을 때만 실행
            const formData = new FormData(); // 폼 데이터를 생성
            formData.append("startRow", mypageObserverTargetSave.dataset.startRow); // 폼 데이터에 시작 행 번호 추가
            console.log(mypageObserverTargetSave.dataset.startRow);
            formData.append("endRow", mypageObserverTargetSave.dataset.endRow); // 폼 데이터에 끝 행 번호 추가
            console.log(mypageObserverTargetSave.dataset.endRow);
            const url = '/savepost/list'; // 게시글 목록을 가져올 URL
            formData.append("memberNum",followerDiv.dataset.toFollow);
            await makeTravelListSave(url, formData);
           
        }
    });
});


async function makeTravelListSave (url, formData) {
    try{
        const response = await fetch(url, { // fetch API를 사용해 서버에 POST 요청
            method: "POST", // POST 메서드를 사용하여 요청
            body: formData // 요청 본문에 폼 데이터 추가
        });
        const boardDTOs = await response.json();
        
        mypageObserverTargetSave.dataset.startRow = parseInt(mypageObserverTargetSave.dataset.startRow)+9; // startRow 값을 9 증가
        mypageObserverTargetSave.dataset.endRow = parseInt(mypageObserverTargetSave.dataset.endRow)+9; // endRow 값을 9 증가
        await boardDTOLoopSave(boardDTOs);
        
    } catch (error) {
        console.error('Error fetching data', error);
    }
   
};

async function boardDTOLoopSave (boardDTOs) {
    for(let boardDTO of boardDTOs){
        await getHeartCountSave(boardDTO);
    }    
};

async function getHeartCountSave(boardDTO) {
    try{
        const heartResponse = await fetch('/heart/count?boardNum=' + boardDTO.boardNum,{ // 각 게시글의 좋아요 개수 가져오기
            method:"GET"
        });
        const heartCount = await heartResponse.json();
        let createDate = new Date(boardDTO.createDate); // 게시글 작성일을 Date 객체로 변환

        createDate = createDate.getFullYear() + '-' // 작성일을 YYYY-MM-DD 형식으로 변환
                    + String(createDate.getMonth() + 1).padStart(2, '0') + '-' 
                    + String(createDate.getDate()).padStart(2, '0'); 

        let gridItemDiv = document.createElement("div"); // 게시글 하나를 담을 div 생성
        gridItemDiv.classList.add('grid-itemSave');
        gridItemDiv.classList.add('cat1');

        let travel =    // 게시글 하나에 해당하는 HTML 코드를 담는 변수
                '<div class="grid-item__innerSave">'+
                    '<div class="grid-item__content-wrapper">'+
                        '<div class="box-image2">'+
                            '<div>';
        
        if(boardDTO.fileName == '' || boardDTO.fileName == null){
            travel =  travel +
                                '<a class="box-image2__bg" href="/travel/detail?boardNum=' + boardDTO.boardNum + '#boardStartLocation"' +
                                    `style="background-image: url('/resources/upload/travels/default.png');">`+
                                '</a>';
        } else {
            travel = travel +
                                '<a class="box-image2__bg" href="/travel/detail?boardNum=' + boardDTO.boardNum + '#boardStartLocation"' +
                                    `style="background-image: url('/resources/upload/travels/` + boardDTO.fileName + `');">`+
                                '</a>';
        }

        travel = travel +
                                '<a href="/travel/detail?boardNum=' + boardDTO.boardNum + '#boardStartLocation">' +
                                    '<div class="box-image2__info">' + 
                                        '<p class="box-image2__writer">' + boardDTO.memberNickname + '</p>' +
                                        '<p class="box-image2__title">' + boardDTO.boardTitle + '</p>' +
                                    '</div>' +
                                    '<div class="box-image2__info_bot">'+
                                        '<span class="box-image2__date">' + createDate + '</span>'+
                                        '<span class="box-image2__like" data-board-num="' + boardDTO.boardNum + '">' + heartCount + '</span> '+
                                    '</div>'+
                                '</a>';
                            '</div>'+
                        '</div>'+
                    '</div>'+
                '</div>';

        gridItemDiv.innerHTML = travel; // 게시글 div 내에 HTML 내용 추가

        $('.grid__innerSave').append(gridItemDiv); // 게시글 리스트에 게시글 div 추가

        $('.grid__innerSave').masonry('addItems', gridItemDiv); // 게시글 리스트 형태 재정렬
        $('.grid__innerSave').masonry('layout');

        console.log(boardDTO.boardNum);
    } catch (error) {
        console.error('Error fetching heartCount', error);
    }

};

travelObserverSave.observe(mypageObserverTargetSave); // commentsObserverTarget 요소 관찰 시작