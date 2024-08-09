const testDiv =  document.getElementById("testDiv");
const formData = new FormData();
const imgData = document.createElement("img");
imgData.src = 'http://k.kakaocdn.net/dn/kHaew/btsG0G023Ny/4sAcqUv9k5EMqh2VjJJJWK/img_640x640.jpg'
formData.append('files', imgData);

fetch('/kakaoTest/img', {
    headers: {
            'Content-Type': 'multipart/form-data',
          },
    method: "POST",
    body: formData
})
.then((res)=>{return res.json();})
.then((res)=>{
    console.log(res);
    testDiv.innerHTML = res;
})