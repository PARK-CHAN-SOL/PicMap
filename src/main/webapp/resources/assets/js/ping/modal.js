/**
 * 댓글 관리
 */
const modalButton = document.getElementById("modalButton");
const mapModal = document.getElementById("mapModal");

const mapButton = document.getElementById("mapButton");
const pingFrm = document.getElementById("pingFrm");

mapButton.addEventListener("click", ()=>{
    // pingFrm.submit();
})

// modalButton.addEventListener("click", function(){
// 	setTimeout(() => {
//         map.relayout();
//     }, 1000);
// });

// 1. 주기적으로 감지할 대상 요소 선정
const target = document.getElementById('mapModal');

// 2. 옵저버 콜백 생성
const callback = (mutationList, observer) => {
    map.relayout(); 
    if(keywordMarkers){searchPlaces();}
};

// 3. 옵저버 인스턴스 생성
const mutationObserver = new MutationObserver(callback); // 타겟에 변화가 일어나면 콜백함수를 실행하게 된다.

// 4. DOM의 어떤 부분을 감시할지를 옵션 설정
const config = { 
    attributes: true, // 속성 변화 할때 감지
    childList: false, // 자식노드 추가/제거 감지
    characterData: false // 데이터 변경전 내용 기록
};

// 5. 감지 시작
mutationObserver.observe(target, config);