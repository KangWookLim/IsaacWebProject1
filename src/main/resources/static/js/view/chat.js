const socket = new WebSocket("ws://" + window.location.host + "/ws");
const userid = document.getElementById("user-id");
const connectmem = $("#total-mem-container");
const chat_container = $("#chat-container");
const principal_User = $("#user-id").attr("principal");
const modal_radio = document.querySelectorAll(".modal-radio");

socket.onopen = function (event) {
    getsession();
    getRoomSession();
};
socket.onmessage = function (event) {
    showMessage(event.data);
};
function sendMessage() {
    const message = $("#messageInput");
    const checkmessage = message.val().trim();
    if (checkmessage == ""|| checkmessage == null||checkmessage == " ") {
        alert("Message cannot be empty");
        message.val('');
    }else{
        socket.send(message.val());
        console.log(message.val());
        message.val('');
    }
}

socket.onerror = function (error) {
    console.log(error);
}

function showMessage(message) {
    $("#messageArea").append("<br>" + message);
}

$("#sendBtn").on("click", function () {
    sendMessage();
});

window.addEventListener("keydown", e => {
    if (e.keyCode === 13&&modal.style.display==="none") {
        sendMessage();
    }
})
//서버와 웹소켓 연결, 종료, 메세지전송 관련 메서드
$(document).ready(function () {
    sessionRefresh();
    refreshRoomSession();
    modalOff();
})
// /chat으로 매핑된 이후 html세팅용
const connetecSessions = $("#total-mem-container")
function sessionRefresh(){
    fetch('/ws/sessionrefresh')
        .then(response => {
            if(response.ok){
                return response.json();
            }else{
                throw new Error('네트워크 오류');
            }
        })
        .then(data => {
            connetecSessions.text(null);
            connetecSessions.append("<div class='login-mem'> 접속유저 </div>");
            data.forEach(function(id){
                connetecSessions.append("<div class='connect-session-id'>" + id + "</div>");
                console.log(id)
            });
            sessionRefresh();
        })
        .catch(error =>{
            console.error('처리 도중 에러 발생');
        });
}
function getsession(){
    fetch('/ws/getsessions')
        .then(response => {
            if(response.ok){
                return response.json();
            }else{
                throw new Error("네트워크 오류")
            }
        })
        .then(data => {
            connetecSessions.text(null);
            connetecSessions.append("<div class='login-mem'> 접속유저 </div>");
            data.forEach(function(id){
                connetecSessions.append("<div class='connect-session-id'>" + id + "</div>");
                console.log(id)
            });
        })
        .catch(error =>{
            console.error('데이터 입력 오류');
        })
}
//현재 웹소켓으로 /chat에 접속중인 유저 표시(로그인 유저 X)**
//이미 접속하고있던 유저는 서버에 지속적인 요청을 보내 새로 접속한 유저 정보를 들고 옴

const roomSession = $("#chat-room-container")
function refreshRoomSession() {
    fetch('/ws/checkroom')
        .then(response => {
            if(response.ok){
                return response.json();
            }else{
                throw new Error("룸 생성 오류")
            }
        })
        .then(data =>{
            roomSession.text(null);
            data.forEach(function(room){
                console.log(room);
                roomSession.append(
                    "<div class='chat-room-card' onclick='roombtnclick(" + room.room_num + ")'>" +
                    "<div class='chat-room-name'>" +
                    "<span>" + room.mem1_Id + "</span>" +
                    "</div>" +
                    "<div class='chat-room-number'>" +
                    "<span>" + room.room_num + "</span>" +
                    "</div>" +
                    "<div class='chat-room-icon-container'> " +
                    "<img src='../../images/chat/play-button.png' class='chat-room-icon'/> " +
                    "</div>" +
                    "</div>"
                );
            });
            refreshRoomSession();
        })
        .catch(error =>{
            console.error('처리 도중 에러 발생');
        });
}
function getRoomSession() {
    fetch('/ws/getrooms')
        .then(response => {
            if(response.ok){
                return response.json();
            }else{
                throw new Error("룸 생성 오류")
            }
        })
        .then(data =>{
            data.forEach(function(room){
                console.log(room);
                roomSession.append(
                    "<div class='chat-room-card' onclick='roombtnclick(" + room.room_num + ")'>" +
                    "<div class='chat-room-name'>" +
                    "<span>" + room.mem1_Id + "</span>" +
                    "</div>" +
                    "<div class='chat-room-number'>" +
                    "<span>" + room.room_num + "</span>" +
                    "</div>" +
                    "<div class='chat-room-icon-container'> " +
                    "<img src='../../images/chat/play-button.png' class='chat-room-icon'/> " +
                    "</div>" +
                    "</div>"
                );
            });
        })
        .catch(error =>{
            console.error('처리 도중 에러 발생');
        });
}
// 새로운 부분
$(".makeRoom").on("click", function () {
    makeRoom();
});

function makeRoom() {
    fetch('/ws/makerooms')
        .then(response => {
            if(response.ok){
                getModalInfo();
                modalOn();
                refreshModalInfo();
                console.log("룸 생성 완료")
            }else{
                throw new Error("룸 생성 오류")
            }
        })
}

function modalOff() {
    modal.style.display = "none";
    fetch('/ws/deleterooms')
        .then(response => {
            if(response.ok){
                console.log("룸 삭제 완료")
            }else{
                throw new Error("룸 삭제 오류")
            }
        })

}
const radio_container = document.querySelector(".modal-item-chk-container");
function modalOn() {
    modal.style.display = "grid";
    radio_container.style.display = "none";
}
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

const modalMem1 = $("#modal-mem1");
const modalMem2 = $("#modal-mem2");
const modalRoomNum = $("#modal-room-num");
function getModalInfo(){
    fetch('/ws/getroominfo')
        .then(response => {
            if(response.ok){
                return response.json();
            }else{
                throw new Error("네트워크 오류")
            }
        })
        .then(data =>{
            data.forEach(room =>{
                if(room.mem1_Id === principal_User||room.mem2_Id === principal_User){
                    modalSetting(room);
                }
            })
        })
        .catch(error =>{
            console.error('데이터 입력 오류');
        })
}
function refreshModalInfo(){
    fetch('/ws/checkroominfo')
        .then(response => {
            if(response.ok){
                return response.json();
            }else{
                throw new Error("네트워크 오류")
            }
        })
        .then(data =>{
            data.forEach((room) => {
                if (room.mem1_Id === principal_User || room.mem2_Id === principal_User) {
                    modalSetting(room);
                    if(room.mem2_Id == null){
                        refreshModalInfo();
                    }
                }
            })
        })
        .catch(error =>{
            console.error(error);
        })
}


function modalSetting(room){
    modalRoomNum.text(null);
    modalMem1.text(null);
    modalMem2.text(null);
    modalMem1.text(room.mem1_Id);
    modalRoomNum.text(room.room_num);
    modalMem2.text(room.mem2_Id);
    console.log(modalMem2.text())
    if (modalMem2.text().trim() === "") {
        modalMem2.text("대기중");
    }else{
        radio_container.style.display = "flex";
    }
}
//룸 생성 및 생성한 후 보이는 modal창과 정보 세팅

function roombtnclick(room_num){
    intoroom(room_num);
    getModalInfo();
    modalOn();
    refreshModalInfo();
}


function intoroom(room_num){
    $.ajax({
        url: "/ws/intoroom",
        type: "GET",
        data: {
            room_num : room_num
        }
    }).fail(function(xhr, status, error){
            console.log(error)
    })
}



