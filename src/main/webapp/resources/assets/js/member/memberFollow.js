 
  //팔로우
  
  const follow = document.getElementById("follow");
  console.log(follow);
  
  const wishResult = document.getElementById("wishResult");
  
  //Arrow Function 화살표 함수 
  //함수 선언 
  //function [함수명] (){}
  //()=>{}
  
  
  follow.addEventListener("click",(e)=>{
      let toFollow =follow.getAttribute("data-to-follow")
      console.log(toFollow);
      fetch("./follow?toFollow="+toFollow,{
      method:"GET" 
       })
       .then((res)=>{return res.text()})
       .then((res)=>{
          if(res>0){
              
          }else{
              alert("팔로우 실패")
          }
       })
       .catch(()=>{
          alert("오류발생");
       })
       if(follow.innerText == '팔로우'){
         follow.innerText = '언팔로우';
         follow.classList.remove('btn-primary');
         follow.classList.add('btn-secondary');
       } else {
         follow.innerText = '팔로우';
         follow.classList.remove('btn-secondary');
         follow.classList.add('btn-primary');
       }

  })
    