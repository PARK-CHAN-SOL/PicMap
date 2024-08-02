// 지번 검색 input
const searchPing = document.getElementById("searchPing");
// 검색 버튼
const searchButton = document.getElementById("searchButton");

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places(); 

searchButton.addEventListener("click", ()=>{
    let searchForm = new FormData();
    searchForm.append("address", searchPing.value);
    fetch('/ping/searchPing', {
        method:"POST",
        body:searchForm
    })
    .then(r=>r.json())
    .then(r=>{
        if(r.lat != 0.0){
            console.log(r);
            var bounds = new kakao.maps.LatLngBounds();
            bounds.extend(new kakao.maps.LatLng(r.lat, r.lon));
            map.setBounds(bounds);
        } else{
            alert('검색 결과가 존재하지 않습니다');
        }
    })
    .catch(()=>{
        alert("오류발생");
    })
})
