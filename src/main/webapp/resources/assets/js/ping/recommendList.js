const recommendTravelList = document.getElementById("recommendTravelList")
let recommendForm = new FormData();

recommendForm.append("pingNum", recommendTravelList.dataset.pingNum); // 1은 임의로 집어넣은 값임
fetch('/ping/getRecommendList', {
    method: "POST",
    body: recommendForm
})
    .then(r => r.text())
    .then(r => {
        if(r == '' || r == null){
            recommendTravelList.innerHTML = '<b class="fs-4">해당 여행지 근처 추천 게시글이 없습니다</b>';
        } else {
            recommendTravelList.innerHTML = recommendTravelList.innerHTML + r;
        }
    })
    .catch((e) => {
        alert('오류 발생');
        console.log(e);
    });
