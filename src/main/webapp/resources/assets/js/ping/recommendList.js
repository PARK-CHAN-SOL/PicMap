const recommendResultTest = document.getElementById("recommendResultTest")
let recommendDiv = document.createElement("div");

let recommendForm = new FormData();

recommendForm.append("pingNum", 1); // 1은 임의로 집어넣은 값임
fetch('/ping/getRecommendList', {
    method: "POST",
    body: recommendForm
})
    .then(r => r.text())
    .then(r => {
        console.log(r);
        recommendDiv.innerHTML = r;
    })
    .catch((e) => {
        alert('오류 발생');
        console.log(e);
    });

recommendResultTest.append(recommendDiv);
