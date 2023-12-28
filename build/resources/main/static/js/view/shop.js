const modal = document.getElementById("modal");
const modal_amount_plus_button= document.getElementById("amount-button-plus");
const modal_amount_minus_button = document.getElementById("amount-button-minus");
const modal_total_price = document.getElementById("total-price");
const modal_total_amount = document.getElementById("total-amount");
const modal_item_price = document.getElementById("modal-item-price");
const modal_btn_name = document.getElementById("modal-btn-name");
const modal_item_id = document.getElementById("modal-item-id");
const modal_item_rarity = document.getElementById("modal-item-rarity");
const modal_item_effect = document.getElementById("modal-item-effect");
const modal_preview_ani = $("#modal-preview-anibox");
const preview_ani= [
    '<div></div>'
    ,'<div class="preview-img-crown"></div>'
    ,'<div class="preview-img-darkwings"></div>'
    ,'<div class="preview-img-green"></div>'
    ,'<div class="preview-img-mantle"></div>'
    ,'<div></div>'
    ,'<div></div>'
    ,'<div></div>'
    ,'<div></div>'
    ,'<div class="preview-img-red"></div>'
    ,'<div class="preview-img-wings"></div>'];

$(document).ready(function(){
    modalOff();
});

function modalOff() {
    modal_total_amount.innerHTML = 0;
    modal_total_price.innerHTML = 0;
    modal.style.display = "none";
}
function modalOn() {
    modal.style.display = "grid";

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
const btnPrev = $(".skin-button");
const previewcontainer = $("#preview-anibox");
const btnModal = document.querySelectorAll('.item-container');
const item_skin_id = [1,2,3,4,9,10];
btnModal.forEach(function (obj, index){
    obj.addEventListener("click", e => {
        const evTarget = e.target;
        const itemid = obj.getAttribute("itemid")
        if  (!evTarget.classList.contains("skin-button")) {
            modalSetting(index);
            modalOn();
        }
        modalSetting(index);
    })
    if(!item_skin_id.includes(parseInt(obj.getAttribute("itemid")))){
        btnPrev[index].style.display = "none";
    }
    btnPrev[index].addEventListener("click", e => {
            if(previewcontainer.children().length===2){
                previewcontainer.append(preview_ani[obj.getAttribute("itemid")])
                btnPrev[index].innerText = "Cancel Preview"
            }
            else if(btnPrev[index].innerText==="Cancel Preview") {
                previewcontainer.children().last().remove();
                btnPrev[index].innerText = "Preview";
            }
        console.log(previewcontainer.children().length);
    })
});

modal.addEventListener("click", e => {
    const evTarget = e.target;
    if (evTarget.classList.contains("btn-close")
        ||evTarget.classList.contains("modal-overlay")) {
        modalOff();
    }

})
window.addEventListener("keydown", e => {
    if (e.keyCode === 27){

        modalOff();
    }
})
const btnleft = $("#move-btn-left");
const btnright = $("#move-btn-right");
let localindex = 0;
function modalSetting(index) {
    localindex = index;
    if(modal_preview_ani.children().length >= 3){
        modal_preview_ani.children().last().remove();
    }
    modal_preview_ani.append(preview_ani[index+1]);
    const itemid = btnModal[index].getAttribute("itemid");
    const itemname = btnModal[index].getElementsByClassName("skin-name")[0].getAttribute("itemname");
    const itemprice = btnModal[index].getElementsByClassName("skin-img-btn")[0].getAttribute("itemprice")
    const itemrarity = btnModal[index].getElementsByClassName("item-rarity")[0].getAttribute("itemrarity")
    const itemeffect = btnModal[index].getElementsByClassName("item-effect")[0].getAttribute("itemeffect");
    modal_item_id.innerText = itemid;
    modal_btn_name.innerText = itemname;
    modal_item_price.innerText = itemprice;
    modal_item_rarity.innerText = itemrarity;
    modal_item_effect.innerText = itemeffect;
    if(itemrarity==="epic") {
        modal_btn_name.style.color = "#9932CC";
    }else if(itemrarity==="rare") {
        modal_btn_name.style.color = "#32CD32";
    }else if(itemrarity==="uncommon") {
        modal_btn_name.style.color = "#1290FF";
    }else{
        modal_btn_name.style.color = "gray";
    }
    if(index===0) {
        btnleft.css("display", "none");
    }else{
        btnleft.css("display", "grid");
    }
    if(index===btnModal.length - 1) {
        btnright.css("display", "none");
    }else{
        btnright.css("display", "grid");
    }
}
modal_amount_plus_button.addEventListener("click", e => {

    modal_total_amount.innerText = parseInt(modal_total_amount.innerText) + 1;
    modal_total_price.innerText = parseInt(modal_item_price.innerText) * parseInt(modal_total_amount.innerText);

})
modal_amount_minus_button.addEventListener("click", e => {
    if(modal_total_amount.innerText>0){
        modal_total_amount.innerText = parseInt(modal_total_amount.innerText) - 1;
        modal_total_price.innerText = parseInt(modal_item_price.innerText) * parseInt(modal_total_amount.innerText);
    }else {
        alert("Purchase quantity cannot be less than 0");
    }
})

$('#order').click(function () {
    const orderinfo = {
        ItemID: $("#modal-item-id").text(),
        Amount: parseInt(modal_total_amount.innerText)
    }
    let orderConfirm = window.confirm("구매하시겠습니까?");
    if(orderConfirm) {
            $.ajax({
            url: "/shop/order",
            type: "POST",
            data: orderinfo
        }).done(function (data) {
            window.alert("구매성공");
            modalOff();
        }).fail(function (xhr, status, error) {
            if(xhr.status === 401) {
                alert("You Must be Logged In");
                window.location.href = "/error/401";
            }else{
                alert(xhr.responseText);
            }
        })
    }
})
btnleft.click(function () {
    modalSetting(localindex - 1)
});
btnright.click(function () {
    modalSetting(localindex + 1)
});