const recommendTravelList = document.getElementById("recommendTravelList")
let recommendForm = new FormData();

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();
var map;
var keywordMarkers;
let mapCenter;
const recoModalButton = document.getElementById("modalButton");

recommendForm.append("pingNum", recommendTravelList.dataset.pingNum);
fetch('/ping/getRecommendList', {
    method: "POST",
    body: recommendForm
})
    .then(r => r.json())
    .then(r => {
        let travelListLen = r.travelList.length-1;
        recoModalButton.innerText = r.pingList[travelListLen].address;
        if(travelListLen == 0 || r == null){
            recommendTravelList.innerHTML = '<b class="fs-4">해당 여행지 근처 추천 게시글이 없습니다</b>';
        } else {
            let listTmp = '';

            for(let i = 0; i < travelListLen; i++){
                listTmp += '<a href="/travel/detail?boardNum=' + r.travelList[i].boardNum
                + '"><img src="/resources/upload/travels/'
                + (r.travelList[i].fileName == null ? 'default.png' : r.travelList[i].fileName) + '" class="rounded m-4" style="width:150px; height:150px;" /></a>';
            }

            recommendTravelList.innerHTML = recommendTravelList.innerHTML + listTmp;
        }

        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
            mapOption = {
                center: new kakao.maps.LatLng(r.pingList[r.pingList.length-1].latitude, r.pingList[r.pingList.length-1].longitude), // 지도의 중심좌표
                level: 4 // 지도의 확대 레벨
            };

        // 지도를 생성합니다    
        map = new kakao.maps.Map(mapContainer, mapOption);

        mapCenter = map.getCenter();

        // 지도가 확대 또는 축소되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
        kakao.maps.event.addListener(map, 'zoom_changed', function() {        
            if(map.getLevel() == 14) map.setLevel(13);
        });

        // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);

        // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
        kakao.maps.event.addListener(map, 'idle', function () {
            searchAddrFromCoords(map.getCenter(), displayCenterInfo);
        });

        // 마커를 표시할 객체 배열
        let positions = [];

        // 검색된 게시글과 게시글에 해당되는 핑의 정보를 바탕으로 마커의 값 세팅
        for(let i = 0; i < travelListLen; i++){
            // 추천 게시글의 값 세팅
            let position = {
                title: r.travelList[i].boardTitle,
                latlng: new kakao.maps.LatLng(r.pingList[i].latitude, r.pingList[i].longitude),
                image: r.travelList[i].fileName == null ? '/resources/assets/img/default1.png' : '/resources/upload/travels/' + r.travelList[i].fileName,
                board: '/travel/detail?boardNum=' + r.travelList[i].boardNum
            };
            positions.push(position); // 배열에 추가
        }

        // 해당 게시글의 값 세팅
        let position = {
            title: r.travelList[travelListLen].boardTitle,
            latlng: new kakao.maps.LatLng(r.pingList[travelListLen].latitude, r.pingList[travelListLen].longitude),
            image: r.travelList[travelListLen].fileName == null ? '/resources/assets/img/default1.png' : '/resources/upload/travels/' + r.travelList[travelListLen].fileName,
            board: '/travel/detail?boardNum=' + r.travelList[travelListLen].boardNum
        };
        positions.push(position); // 배열에 추가

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


    })
    .catch((e) => {
        alert('오류 발생');
        console.log(e);
    });


function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
}

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayCenterInfo(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');
        if(infoDiv){
            for (var i = 0; i < result.length; i++) {
                // 행정동의 region_type 값은 'H' 이므로
                if (result[i].region_type === 'H') {
                    infoDiv.innerHTML = result[i].address_name;
                    break;
                }
            }
        }
    }
}