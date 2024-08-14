/**
 * 
 */

//모달 close 버튼에 뒷 배경 삭제하는 이벤트 리스너 생성
const btnClose = document.getElementsByClassName("btn-close");

const modalBackdrop = document.getElementsByClassName("modal-backdrop fade show");

for(let btn of btnClose){
    btn.addEventListener("click", ()=>{
        console.log('me');
        console.log(modalBackdrop);
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
const phone = document.getElementById("memberPhone");
const email = document.getElementById("memberEmail");





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
    } else if (pw.value.length < 4) {
        passwordError.innerHTML = "pw must be over 4 characters";
        pw.focus();
        return;
    }

    if (pwchk() == -1) return;

    //id validation
    if (memberId.value == "" || pw.value == "" ||
        memberName.value == "" || phone.value == "" ||
        email.value == ""
    ) {
        alert("missing information");
        return;
    } else {
        alert("로그인성공");
        frm.submit();
        return;
    }
})

//memberId 영문자 포함 

    memberId.addEventListener("change", function () {
        var regId = /^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z\d]{8,16}$/;
        console.log(memberId.value);
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
