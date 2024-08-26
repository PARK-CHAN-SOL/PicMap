
const follow = document.getElementById("follow"); // 마이페이지의 팔로우 버튼

const followingDiv = document.getElementById("followingDiv"); // 마이페이지 내에서 팔로워의 숫자를 표시하는 div
const followingList = document.getElementById("followingList"); // 팔로워 모달창 내부 div (list출력용)

const followerDiv = document.getElementById("followerDiv"); // 마이페이지 내에서 팔로잉의 숫자를 표시하는 div
const followerList = document.getElementById("followerList"); // 팔로잉 모달창 내부 div (list출력용)

// 총 팔로워, 팔로잉 수를 담을 변수 선언
const totalFollower = document.getElementById("follower").innerText;
const totalFollowing = document.getElementById("following").innerText;

// 피감시자 선언 (모달창 내부에 내용 없는 div로서 존재)
const followerObserverTarget = document.getElementById("followerObserverTarget");
const followingObserverTarget = document.getElementById("followingObserverTarget");

// 감시자가 피감시자를 발견할 시 호출되는 함수 선언 (피감시자가 화면에 나타날 시)
const followerObserver = new IntersectionObserver((items)=>{
    items.forEach((item) => {
        if(item.isIntersecting){
         if(followerObserverTarget.dataset.startRow <= totalFollower){ // 로드된 유저 수가 총 유저 수보다 적을 경우에 실행 
            fetch("/member/fromFollowList?toFollow=" + followerDiv.getAttribute("data-to-follow") + '&startRow=' + followerObserverTarget.dataset.startRow + '&endRow=' + followerObserverTarget.dataset.endRow, {
               method: "GET"
            }) // 리스트 요청
               .then((res) => { return res.json(); })
               .then((res) => {
                  appendList(res, followerObserverTarget, followerList); // 유저목록 추가 함수 호출
               })
               .catch((e) => {
                  console.log(e);
                  alert("오류");
               })
            }
        }
    });
});

const followingObserver = new IntersectionObserver((items)=>{
   items.forEach((item) => {
       if(item.isIntersecting){
         if(followingObserverTarget.dataset.startRow <= totalFollowing){// 로드된 유저 수가 총 유저 수보다 적을 경우에 실행 
            fetch("/member/toFollowList?fromFollow=" + followingDiv.getAttribute("data-from-follow") + '&startRow=' + followingObserverTarget.dataset.startRow + '&endRow=' + followingObserverTarget.dataset.endRow, {
               method: "GET"
            }) // 리스트 요청
               .then((res) => { return res.json(); })
               .then((res) => {
                  appendList(res, followingObserverTarget, followingList); // 유저목록 추가 함수 호출
               })
               .catch((e) => {
                  console.log(e);
                  alert("오류");
               })
            }
       }
   });
});

// 팔로우, 팔로잉 리스트(유저 리스트)에 유저목록 추가
function appendList(res, observerTarget, listDiv) {
   res.forEach(element => { // DB에서 조회한 유저 리스트를 반복문을 통해 출력
      fetch("/member/followCheck?toFollow=" + element.memberNum, {
         method: "GET"
      })
         .then((checker) => { return checker.json(); })
         .then((checker) => {
            if (element.profilePath == 'default'|| element.profilePath == null || element.profilePath == '')  element.profilePath = '/resources/upload/members/default.png'; 
         
            let exploreUser =  // 유저 한 명에 해당하는 리스트 생성
            '<li class="explore__user">'+'<a class="followA" href="./mypage?memberNum='+element.memberNum+'">'+
            '<div class="explore__content">'+
            '<img src="'+ element.profilePath + '">'+
            '<div class="explore__info">'+
            '<span class="explore__username">' + element.memberNickName + '</span>'+
            '</div>'+
            '</div>'+'</a>';
            if (checker == 0) { // 유저 리스트에 있는 사용자가 나 자신이거나 로그인 하지 않았을 경우 버튼 출력 X
               exploreUser = exploreUser +
                  '</li>';
            } else if (checker == 1) {// 유저 리스트에 있는 사용자를 팔로우 하지 않은 경우 팔로우 버튼 출력
               exploreUser = exploreUser +
                     '<button class="follow_btn" data-to-follow="' + element.memberNum + '">팔로우</button>'+
                  '</li>';
            } else {// 유저 리스트에 있는 사용자를 팔로우 하지 않은 경우 팔로잉 버튼 출력
               exploreUser = exploreUser +
                     '<button class="following_btn" data-to-follow="' + element.memberNum + '">팔로잉</button>'+
                  '</li>';
            }
            listDiv.innerHTML = listDiv.innerHTML + exploreUser; // 유저 리스트에 추가;
         })
   });
   observerTarget.dataset.startRow = parseInt(observerTarget.dataset.startRow) + 12; // 다음에 조회할 리스트의 startRow 변경
   observerTarget.dataset.endRow = parseInt(observerTarget.dataset.endRow) + 12; // 다음에 조회할 리스트의 endRow 변경
};

followerObserver.observe(followerObserverTarget);
followingObserver.observe(followingObserverTarget);

// 마이(유저)페이지에서 팔로우(팔로잉) 버튼을 클릭할 경우 호출
if (follow) {
   follow.addEventListener("click", (e) => {
      let toFollow = follow.getAttribute("data-to-follow")
      followBtnToggle(follow, toFollow, false);
   })
};

// 팔로우 리스트에서 팔로우(팔로잉) 버튼을 클릭할 경우 호출 (이벤트 위임)
followerList.addEventListener("click", (event) => {
   if(event.target.dataset.toFollow != null){
      let follow = event.target;
      let toFollow = follow.getAttribute("data-to-follow")
      followBtnToggle(follow, toFollow, true); // 팔로우, 언팔로우 및 버튼 토글 함수
   }
})

// 팔로잉 리스트에서 팔로우(팔로잉) 버튼을 클릭할 경우 호출 (이벤트 위임)
followingList.addEventListener("click", (event) => {
   if(event.target.dataset.toFollow != null){
      let follow = event.target;
      let toFollow = follow.getAttribute("data-to-follow")
      followBtnToggle(follow, toFollow, true); // 팔로우, 언팔로우 및 버튼 토글 함수
   }
   
})

// 팔로우, 언팔로우 기능 및 버튼 토글 함수
function followBtnToggle(follow, memberNum, isFollowList) {
   fetch("./follow?toFollow=" + memberNum, {
      method: "GET"
   })
      .then((res) => { return res.text() })
      .then((res) => {
         if (res > 0) {
            if (follow.innerText == '팔로우') {
               follow.innerText = '팔로잉';
               follow.classList.remove('follow_btn');
               follow.classList.add('following_btn');
               if(!isFollowList){
                  let followerCount = parseInt(follower.innerHTML) + 1;
                  follower.innerHTML = followerCount;
               }
            } else {
               follow.innerText = '팔로우';
               follow.classList.remove('following_btn');
               follow.classList.add('follow_btn');
               if(!isFollowList){
                  let followerCount = parseInt(follower.innerHTML) - 1;
                  follower.innerHTML = followerCount;
               }
            }
         } else {
            alert("팔로우 실패")
         }
      })
      .catch(() => {
         alert("오류발생");
      });
}



