 let modal = document.querySelectorAll(".modal")
 var modalIndex;
    $(document).ready(function(){
        modalReady();
        loginChk();
    });

    function loginChk() {}

    function modalReady() {
        modal.forEach(function (obj, index) {
            obj.style.display = "none";
        })
    }

    function modalOff(index) {
        modal.item(index).style.display = "none"
        window.removeEventListener("keydown", e => {});
    }
    function modalOn(index) {
        modal.item(index).style.display = "grid"
        console.log(modal.item(index));
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

    const btnModal = document.querySelectorAll('.item-container');

    btnModal.forEach(function (obj, index){
        const totalprice = document.querySelectorAll(".total-price").item(index);
        const price =  document.querySelectorAll(".item-price").item(index);
        const amount =  document.querySelectorAll(".total-amount").item(index);
        const plusbutton =  document.querySelectorAll(".amount-button-plus").item(index);
        const minusbutton =  document.querySelectorAll(".amount-button-minus").item(index);
        const order = document.querySelectorAll(".order").item(index);
        obj.addEventListener("click", e => {
            modalOn(index);
            /* 모달 바깥이나 X버튼 누르면 종료 */
        })
        modal.item(index).addEventListener("click", e => {
            const evTarget = e.target
            if(evTarget.classList.contains("modal") ||
                evTarget.classList.contains("btn-close")) {
                amount.innerHTML = 0;
                totalprice.innerHTML = 0;
                modalOff(index)
            }
        })
        /* esc누르면 종료 */
        window.addEventListener("keydown", e => {
            if (e.keyCode === 27) {
                amount.innerHTML = 0;
                totalprice.innerHTML = 0;
                modalOff(index)
            }
        })
        plusbutton.addEventListener("click", e => {
            amount.innerHTML = parseInt(amount.innerHTML) + 1;
            totalprice.innerHTML = parseInt(price.innerHTML) * parseInt(amount.innerHTML);
        })
        minusbutton.addEventListener("click", e => {
            if(parseInt(amount.innerHTML) === 0) {
                totalprice.innerHTML = "Total amount cannot be less than 0";
                totalprice
                totalprice.innerHTML = 0;
            }else {
                amount.innerHTML = parseInt(amount.innerHTML) - 1;
                totalprice.innerHTML = parseInt(price.innerHTML) * parseInt(amount.innerHTML);
            }
        })
        order.addEventListener("click", e => {
            $.ajax({
                url : '/shop/order',
                type : 'post',
                data : 'json',
                data : {
                    totalprice: parseInt(totalprice.innerHTML)
                }
                }).done(function (data){
                    console.log(data);
                }).fail(function (xhr, status, error) {
                    console.log(status);
                });
            modalOff(index)
        })
    });






