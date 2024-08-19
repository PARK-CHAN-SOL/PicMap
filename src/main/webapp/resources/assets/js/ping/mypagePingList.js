// 지번 검색 input
const searchPing = document.getElementById("searchPing");
// 검색 버튼
const searchButton = document.getElementById("searchButton");

const mypageBack = document.getElementById('mypageBack');

var keywordMarkers;


// 마커를 담을 배열
let markers = [];

// 지도 위에 표시되고 있는 마커를 모두 제거
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

if(searchButton){
    searchButton.addEventListener("click", () => {

        // 지도 위에 표시되고 있는 마커를 모두 제거
        removeMarker();

        getPingList(searchPing.value);
        
    })
}

function getPingList() {
    let searchForm = new FormData();
    searchForm.append("memberNum", followerDiv.dataset.toFollow);
    fetch('/ping/getMyPingList', {
        method: "POST",
        body: searchForm
    })
        .then(r => r.json())
        .then(r => {
            if (r.lat != 0.0 && r.travelList.length != 0) {
                mypageBack.src = '';

                document.getElementById('mapWrap').style.display = 'block';

                var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                mapOption = {
                    //서울 시청 37.566826 126.9786567
                    center: new kakao.maps.LatLng(36.71053726279515, 127.39318958702651), // 지도의 중심좌표
                    level: 13 // 지도의 확대 레벨
                };

                // 지도를 생성합니다    
                var map = new kakao.maps.Map(mapContainer, mapOption);

                // 검색 결과를 바탕으로 지도 중심 좌표 재설정
                var bounds = new kakao.maps.LatLngBounds();
                bounds.extend(new kakao.maps.LatLng(r.lat, r.lon));
                map.setBounds(bounds);

                // 지도 레벨(사이즈) 재설정
                map.setLevel(r.level);

                // 마커를 표시할 객체 배열
                let positions = [];

                // 검색된 게시글과 게시글에 해당되는 핑의 정보를 바탕으로 마커의 값 세팅
                r.travelList.forEach(element => {
                    let ping = r.pingMap[element.pingNum]; // 핑의 정보를 담는 변수 선언
                    // 값 세팅
                    let position = {
                        title: element.boardTitle,
                        latlng: new kakao.maps.LatLng(ping.latitude, ping.longitude),
                        image: element.fileName == null ? '/resources/upload/travels/default1.png' : '/resources/upload/travels/' + element.fileName,
                        board: '/travel/detail?boardNum=' + element.boardNum
                    };
                    positions.push(position); // 배열에 추가
                });

                console.log(positions);

                for (var i = 0; i < positions.length; i++) {

                    // 마커 이미지의 이미지 크기 입니다
                    var imageSize = new kakao.maps.Size(64, 64);

                    // 마커 이미지를 생성합니다    
                    var markerImage = new kakao.maps.MarkerImage(positions[i].image, imageSize);

                    // 마커를 생성합니다
                    var marker = new kakao.maps.Marker({
                        map: map, // 마커를 표시할 지도
                        position: positions[i].latlng, // 마커를 표시할 위치
                        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                        image: markerImage, // 마커 이미지 
                        clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
                    });

                    let board = positions[i].board;

                    // 마커에 클릭이벤트를 등록, 클릭시 travel detail로 redirect
                    kakao.maps.event.addListener(marker, 'click', function () {
                        console.log(board);
                        location.href = board;
                    });

                    markers.push(marker);
                    map.relayout();
                }

            }
        })
        .catch((e) => {
            alert("오류발생");
            console.log(e);
        })
};


getPingList();
