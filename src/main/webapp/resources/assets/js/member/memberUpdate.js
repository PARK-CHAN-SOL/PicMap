function readURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function(e) {
        document.getElementById('previewProfile').src = e.target.result;
      };
      reader.readAsDataURL(input.files[0]);
    } else {
      document.getElementById('previewProfile').src = "";
    }  
  }
  const btn1 = document.getElementById("btn1");
  const pw1 = document.getElementById("memberPassword1");
  const pwCheck1 = document.getElementById("memberPasswordCheck1");
  const passwordEqError1 = document.getElementById("password-eqError1");

  const memberName1 = document.getElementById("memberName1");
  const memberNickName1 = document.getElementById("memberNickName1");
  const phone1 = document.getElementById("memberPhone1");
  const email1 = document.getElementById("memberEmail1");
  const memberBirth1 = document.getElementById("memberBirth1");
  

  const frm1 = document.getElementById("frm1");
function pwchk1() {
    passwordEqError1.innerHTML = "";
    if (pw1.value != pwCheck1.value) {
        passwordEqError1.innerHTML = "wrong password";
        passwordEqError1.style.color = "red";
        passwordEqError1.value = "";
        return -1;
    } else {
        passwordEqError1.innerHTML = "correct";
        passwordEqError1.style.color = "green";
        return 1;
    }
}

pwCheck1.addEventListener("blur", () => {
    pwchk1()
});



//password validation div's id
const passwordError1 = document.getElementById("password-error1");
btn1.addEventListener("click", function () {
  passwordError1.innerHTML = "";
  
  //password validation
  if (pw1.value == "") {
      passwordError1.innerHTML = "pw is required";
      pw1.value = "";
      pw1.focus();
      return;
  } else if (pw1.value.length < 6) {
      passwordError1.innerHTML = "pw must be over 6 characters";
      pw1.focus();
      return;
  }

  if (pwchk1() == -1) return;

  //id validation
  if (memberBirth1.value == "" || pw1.value == "" ||
      memberName1.value == "" || phone1.value == "" ||
      email1.value == "" || memberNickName1.value == ""
  ) {
      alert("missing information");
      return;
  } else {
      frm1.submit();
      return;
  }
})





phone1.addEventListener("change", function () {
        
  var regPhone1 =  /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
   
  if(!regPhone1.test(frm1.phone1.value)){
      alert("ex) 010-1234-1234");
      frm1.phone1.focus();
      frm1.phone1.value = '';
      return;
        } else{
            fetch("/member/phoneCheck?memberPhone=" + phone1.value, {
                method: "GET"
            })
            .then((res) => { return res.text(); })
            .then((res) => {
                if (res == 0) {
                    return;
                } else {
                    alert("등록된 전화번호 입니다");
                    phone1.value = "";
                    phone1.focus();
                }
            })
            .catch((e) => {
                console.log(e);
                alert("오류");
            })
        }
});
