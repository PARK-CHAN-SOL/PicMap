/**
 * 
 */

function readURL(input){
    let travelPreview = document.getElementById("travelPreview")
    if(input.files && input.files[0]){
        let reader = new FileReader();
        reader.onload = function (e) {
            travelPreview.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        travelPreview.src="";
    }
}





window.onload = function() {
    var modal = document.getElementById("myModal");
    var img = document.getElementById("profileImage");
    var modalImg = document.getElementById("img01");
    var span = document.getElementsByClassName("close")[0];

    if (img) {
        img.onclick = function() {
            modal.style.display = "block";  // 모달을 화면에 표시
            modalImg.src = this.src;        // 클릭한 이미지의 소스를 모달에 표시
        }
    }

    if (span) {
        span.onclick = function() { 
            modal.style.display = "none";   // X 버튼 클릭 시 모달 닫기
        }
    }

    // 모달 바깥쪽 클릭 시 닫기
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";  // 배경을 클릭했을 때 모달 닫기
        }
    }
}