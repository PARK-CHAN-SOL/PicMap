
const writeFrm = document.getElementById("writeFrm");
const writeBtn = document.getElementById("writeBtn");

writeBtn.addEventListener("click", function (event) {
	if(locSpan.innerHTML.trim() == "" || locSpan.innerHTML.trim() == null) {
		event.preventDefault();
		alert("위치를 등록해주세요");
        return;
	} else {
		writeFrm.submit();
	    return;
	}
	
});