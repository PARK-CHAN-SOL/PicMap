/**
 * 
 */
let roller = document.querySelector('.rolling-list');
roller.id = 'roller1'; // 아이디부여

let clone = roller.cloneNode(true)
// 기본값은 false. 자식 노드까지 복제 할 경우 true
clone.id = 'roller2';
document.querySelector('.wrap-box').appendChild(clone);

//document.querySelector('#roller1').style.left = '0px';
//document.querySelector('#roller2').style.left = document.querySelector('.rolling-list').offsetWidth + 'px';
// offsetWidth는 요소의 너비를 픽셀 단위로 반환
// 요소의 내부 너비, 패딩, 테두리, 수직스크롤바의 너비를 모두 포함
// 부모요소에 flex를 주면 굳이 줄 필요 없음

roller.classList.add('original');
clone.classList.add('clone');