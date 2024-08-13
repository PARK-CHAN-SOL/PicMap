
const heartBtn = document.getElementById("heartBtn")

function heartCount() {
    fetch("/heart/count?boardNum=" + heartBtn.getAttribute("data-id"), {
		method:"GET"
	})
}

heartBtn.addEventListener("click", ()=> {
    fetch("/heart/click?boardNum=" + heartBtn.getAttribute("data-id"), {
		method:"GET"
	})
    .then(r=>r.text())
    .then(r=>{
        r=r.trim();
        if(r==-1000){
            alert("로그인 바랍니다")
        }else if(r==1) {
            heartCount();
        }
    })
})