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