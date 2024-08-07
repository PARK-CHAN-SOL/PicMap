/**
 * 
 */

function readURL(input){
    let preview = document.getElementById("preview")
    if(input.files && input.files[0]){
        let reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        preview.src="";
    }
}