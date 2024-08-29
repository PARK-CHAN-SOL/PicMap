//모달 close 버튼에 뒷 배경 삭제하는 이벤트 리스너 생성
const btnClose = document.getElementsByClassName("btn-close"); 

const modalBackdrop = document.getElementsByClassName("modal-backdrop fade show");

for(let btn of btnClose){
    btn.addEventListener("click", ()=>{
        for(let child of modalBackdrop){
            child.remove();
        }
    })
}


//submit button id
const btn = document.getElementById("btn");

//input tag class
const ch = document.getElementsByClassName("ch");

//each infos' id
const memberId = document.getElementById("memberId");
const pw = document.getElementById("memberPassword");
const pwCheck = document.getElementById("memberPasswordCheck");
const memberName = document.getElementById("memberName");
const memberNickName = document.getElementById("memberNickName");
const memberPhone = document.getElementById("memberPhone");
const memberEmail = document.getElementById("memberEmail");
const memberBirth = document.getElementById("memberBirth");





//form tag id
const frm = document.getElementById("frm");




//password validation div's id
const passwordError = document.getElementById("password-error");
btn.addEventListener("click", function () {
    passwordError.innerHTML = "";
    
    //password validation
    if (pw.value == "") {
        passwordError.innerHTML = "pw is required";
        pw.value = "";
        pw.focus();
        return;
    } else if (pw.value.length < 6) {
        passwordError.innerHTML = "pw must be over 6 characters";
        pw.focus();
        return;
    }

    if (pwchk() == -1) return;

    //id validation
    if (memberId.value == "" || pw.value == "" ||
        memberName.value == "" || phone.value == "" ||
        email.value == "" || memberBirth.value ==""
    ) {
        alert("missing information");
        return;
    } else {
        frm.submit();
        return;
    }
})

//memberId 영문자 포함 

    memberId.addEventListener("change", function () {
        var regId = /^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z\d]{8,16}$/;
        //1. 아이디 체크
        if(!regId.test(frm.memberId.value)){
            alert("아이디: 8-16자 소문자+숫자로 작성하세요.");
            frm.memberId.focus();
            frm.memberId.value = '';
            return;
        } else{
            fetch("/member/idCheck?memberId=" + memberId.value, {
                method: "GET"
            })
            .then((res) => { return res.text(); })
            .then((res) => {
                if (res == 0) {
                    return;
                } else {
                    alert("중복된 ID 입니다");
                    memberId.value = "";
                    memberId.focus();
                }
            })
            .catch((e) => {
                console.log(e);
                alert("오류");
            })
        }
});


memberEmail.addEventListener("change", function () {
    var regEmail = /^[\w\.-]+@[a-zA-Z\d\.-]+\.[a-zA-Z]{2,}$/ ;
   
    if(!regEmail.test(frm.memberEmail.value)){
        alert("이메일을 올바르게 입력해주세요 ex)rlagofls0824@gmail.com");
        frm.memberEmail.focus();
        frm.memberEmail.value = '';
        return;
    } 
    
});

memberPhone.addEventListener("change", function () {
    var regPhone =  /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
   
    if(!regPhone.test(frm.memberPhone.value)){
        alert("ex) 010-1234-1234");
        frm.memberPhone.focus();
        frm.memberPhone.value = '';
        return;
    } else{
        fetch("/member/phoneCheck?memberPhone=" + memberPhone.value, {
            method: "GET"
        })
        .then((res) => { return res.text(); })
        .then((res) => {
            if (res == 0) {
                return;
            } else {
                alert("등록된 전화번호 입니다");
                memberPhone.value = "";
                memberPhone.focus();
            }
        })
        .catch((e) => {
            console.log(e);
            alert("오류");
        })
    }
});





const passwordEqError = document.getElementById("password-eqError");

function pwchk() {
    passwordEqError.innerHTML = "";
    if (pw.value != pwCheck.value) {
        passwordEqError.innerHTML = "wrong password";
        passwordEqError.style.color = "red";
        passwordEqError.value = "";
        return -1;
    } else {
        passwordEqError.innerHTML = "correct";
        passwordEqError.style.color = "green";
        return 1;
    }
}

pwCheck.addEventListener("blur", () => {
    pwchk()
});

function readURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function(e) {
        document.getElementById('preview').src = e.target.result;
      };
      reader.readAsDataURL(input.files[0]);
    } else {
      document.getElementById('preview').src = "";
    }  
  }
