const socket = new WebSocket("ws://" + window.location.host + "/ws");
const userid = document.getElementById("user-id");
const connectmem = $("#total-mem-container");
const chat_container = $("#chat-container");

socket.onopen = function (event) {
    getsession();
    getRoomSession();
    console.log("연결 성공");
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
    if (e.keyCode === 13) {
        sendMessage();
    }
})

$(document).ready(function () {
    sessionRefresh();
    refreshRoomSession();
})
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
            data.forEach(function(id){
                connetecSessions.append(id+"<br>");
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
        .then(data =>{
            connetecSessions.text(null);
            data.forEach(function(id){
                connetecSessions.append(id+"접속중 <br>");
                console.log(id)
            });
        })
        .catch(error =>{
            console.error('데이터 입력 오류');
        })
}
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
                    "<div class='chat-room-card'>" +
                    "<div class='chat-room-name'>" +
                    "<span>" + room.mem1Id + "</span>" +
                    "</div>" +
                    "<div class='chat-room-number'>" +
                    "<span>" + room.roomId + "</span>" +
                    "</div>" +
                    "<div class='chat-room-icon-container'> " +
                    "<img src='../../images/chat/lock.png' class='chat-room-icon'/> " +
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
                    "<div class='chat-room-card'>" +
                    "<div class='chat-room-name'>" +
                    "<span>" + room.mem1Id + "</span>" +
                    "</div>" +
                    "<div class='chat-room-number'>" +
                    "<span>" + room.roomId + "</span>" +
                    "</div>" +
                    "<div class='chat-room-icon-container'> " +
                    "<img src='../../images/chat/lock.png' class='chat-room-icon'/> " +
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



