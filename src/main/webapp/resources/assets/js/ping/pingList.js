// 지번 검색 input
const searchPing = document.getElementById("searchPing");
// 검색 버튼
const searchButton = document.getElementById("searchButton");

var keywordMarkers;

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        //서울 시청 37.566826 126.9786567
        center: new kakao.maps.LatLng(36.71053726279515, 127.39318958702651), // 지도의 중심좌표
        level: 13 // 지도의 확대 레벨
    };

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption);

// 지도가 확대 또는 축소되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'zoom_changed', function() {        
    if(map.getLevel() == 14) map.setLevel(13);
});

// 마커를 담을 배열
let markers = [];

// 지도 위에 표시되고 있는 마커를 모두 제거
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

searchButton.addEventListener("click", () => {

    // 지도 위에 표시되고 있는 마커를 모두 제거
    removeMarker();

    getPingList(searchPing.value);
    
})

function getPingList(address) {
    let searchForm = new FormData();
    searchForm.append("address", address);
    fetch('/ping/getPingList', {
        method: "POST",
        body: searchForm
    })
        .then(r => r.json())
        .then(r => {
            if (r.lat != 0.0) {

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
                        image: element.fileName == null ? '/resources/assets/img/default1.png' : '/resources/upload/travels/' + element.fileName,
                        board: '/travel/detail?boardNum=' + element.boardNum
                    };
                    positions.push(position); // 배열에 추가
                });

                for (var i = 0; i < positions.length; i++) {

                    let board = positions[i].board;


                    var content =
                    '<div class="border border-4 border-white d-flex align-items-center justify-content-center rounded-circle overflow-hidden">' +
                    '   <a href="' + board + '"><img src="' + positions[i].image + '" style="max-width:120px; height:64px;"></a>'
                    '</div>';

                    var customOverlay = new kakao.maps.CustomOverlay({
                        position: positions[i].latlng,
                        content: content,
                        xAnchor: 0,
                        yAnchor: 1
                    });

                    customOverlay.setMap(map);
                }

            } else {
                alert('검색 결과가 존재하지 않습니다');
            }
        })
        .catch((e) => {
            alert("오류발생");
            console.log(e);
        })
};


getPingList('');
map.relayout();