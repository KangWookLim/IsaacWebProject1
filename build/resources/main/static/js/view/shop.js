 let modal = document.getElementById("modal")

    $(document).ready(function(){
        modalOff();
        loginChk();
    });

    function loginChk() {}

    function modalOff() {
        modal.style.display = "none"
    }
    function modalOn() {
        modal.style.display = "grid"
    }

    /* 배경 변경  */
    function changeBg1() {
        document.getElementById("preview-bg").src = "/images/map/caves.png";
    }
    function changeBg2() {
        document.getElementById("preview-bg").src = "/images/map/nfloor.png";
    }
    function changeBg3() {
        document.getElementById("preview-bg").src = "/images/map/library.png";
    }

    /* 주문 확인 */
    function confirmOrder() {
        modalOff();
    }



    const  btnModal = document.querySelectorAll('.item-container')
    btnModal.forEach(function (obj, index){
        obj.addEventListener("click", e => {
            modalOn();
        })
    });

    /* 모달 바깥이나 X버튼 누르면 종료 */
    modal.addEventListener("click", e => {
        const evTarget = e.target
        if(evTarget.classList.contains("modal-overlay") ||
            evTarget.classList.contains("btn-close")) {
            modalOff()
        }
    })
    /* esc누르면 종료 */
    window.addEventListener("keydown", e => {
        if (e.keyCode === 27) {
            modalOff()
        }
    })
