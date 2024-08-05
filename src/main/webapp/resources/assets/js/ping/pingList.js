// 지번 검색 input
const searchPing = document.getElementById("searchPing");
// 검색 버튼
const searchButton = document.getElementById("searchButton");

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

searchButton.addEventListener("click", () => {
    let searchForm = new FormData();
    searchForm.append("address", searchPing.value);
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
                        image: element.fileName == null ? '/resources/assets/img/default1.png' : element.fileName,
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
                }

            } else {
                alert('검색 결과가 존재하지 않습니다');
            }
        })
        .catch((e) => {
            alert("오류발생");
            console.log(e);
        })
})
