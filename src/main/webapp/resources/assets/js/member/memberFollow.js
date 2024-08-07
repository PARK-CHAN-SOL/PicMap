
//팔로우

const follow = document.getElementById("follow");
console.log(follow);

const follower = document.getElementById("follower");

const wishResult = document.getElementById("wishResult");

const followerDiv = document.getElementById("followerDiv")
const followerList = document.getElementById("followerList");
//Arrow Function 화살표 함수 
//함수 선언 
//function [함수명] (){}
//()=>{}

followerList.addEventListener("click", (event) => {
   if(event.target.dataset.toFollow != null){
      let follow = event.target;
      let toFollow = follow.getAttribute("data-to-follow")
      console.log(toFollow);
      fetch("./follow?toFollow=" + toFollow, {
         method: "GET"
      })
         .then((res) => { return res.text() })
         .then((res) => {
            if (res > 0) {
               if (follow.innerText == '팔로우') {
                  follow.innerText = '팔로잉';
                  follow.classList.remove('follow_btn');
                  follow.classList.add('following_btn');
               } else {
                  follow.innerText = '팔로우';
                  follow.classList.remove('following_btn');
                  follow.classList.add('follow_btn');
               }
            } else {
               alert("팔로우 실패")
            }
         })
         .catch(() => {
            alert("오류발생");
         });
   }
})

if (follow) {
   follow.addEventListener("click", (e) => {
      let toFollow = follow.getAttribute("data-to-follow")
      console.log(toFollow);
      fetch("./follow?toFollow=" + toFollow, {
         method: "GET"
      })
         .then((res) => { return res.text() })
         .then((res) => {
            if (res > 0) {
               if (follow.innerText == '팔로우') {
                  follow.innerText = '팔로잉';
                  follow.classList.remove('follow_btn');
                  follow.classList.add('following_btn');
                  let followerCount = parseInt(follower.innerHTML) + 1;
                  follower.innerHTML = followerCount;
               } else {
                  follow.innerText = '팔로우';
                  follow.classList.remove('following_btn');
                  follow.classList.add('follow_btn');
                  let followerCount = parseInt(follower.innerHTML) - 1;
                  follower.innerHTML = followerCount;
               }
            } else {
               alert("팔로우 실패")
            }
         })
         .catch(() => {
            alert("오류발생");
         });
   })
};

followerDiv.addEventListener("click", function () {
   fetch("/member/fromFollowList?toFollow=" + followerDiv.getAttribute("data-to-follow"), {
      method: "GET"

   })
      .then((res) => { return res.json(); })
      .then((res) => {
         res.forEach(element => {
            let isFollow;
            fetch("/member/followCheck?toFollow=" + element.memberNum, {
               method: "GET"
            })
               .then((checker) => { return checker.json(); })
               .then((checker) => {
                  let exploreUser;
                  console.log(checker);
                  if (element.profilePath == 'default') element.profilePath = 'default.png';
                  if (checker == 0) {
                     console.log(element.profilePath);
                     exploreUser =
                        '<li class="explore__user">'+
                           '<div class="explore__content">'+
                              '<img src="/resources/upload/members/'+ element.profilePath + '"/>'+
                              '<div class="explore__info">'+
                                 '<span class="explore__username">' + element.memberNickName + '</span>'+
                              '</div>'+
                           '</div>'+
                        '</li>';
                  } else if (checker == 1) {
                     exploreUser =
                        '<li class="explore__user">'+
                           '<div class="explore__content">'+
                              '<img src="/resources/upload/members/'+ element.profilePath + '"/>'+
                              '<div class="explore__info">'+
                                 '<span class="explore__username">' + element.memberNickName + '</span>'+
                              '</div>'+
                           '</div>'+
                           '<button class="follow_btn" data-to-follow="' + element.memberNum + '">팔로우</button>'+
                        '</li>';
                  } else {
                     exploreUser =
                        '<li class="explore__user">'+
                           '<div class="explore__content">'+
                              '<img src="/resources/upload/members/'+ element.profilePath + '"/>'+
                              '<div class="explore__info">'+
                                 '<span class="explore__username">' + element.memberNickName + '</span>'+
                              '</div>'+
                           '</div>'+
                           '<button class="following_btn" data-to-follow="' + element.memberNum + '">팔로잉</button>'+
                        '</li>';
                  }
                  followerList.innerHTML = followerList.innerHTML + exploreUser;
               })
         });
         console.log(res);
      })
      .catch((e) => {
         console.log(e);
         alert("오류");
      })
});