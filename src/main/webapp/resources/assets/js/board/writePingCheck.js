
const frm = document.getElementById("frm");
const writeBtn = document.getElementById("writeBtn");
const locSpan = document.getElementById("locSpan");

writeBtn.addEventListener("submit", function () {
	if(locSpan.innerHTML.trim() == "" || locSpan.innerHTML.trim() == null) {
		event.preventDefault();
		alert("위치를 등록해주세요");
        return;
	} else {
		frm.submit();
	    return;
	}
	
});