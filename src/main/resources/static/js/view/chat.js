const socket = new WebSocket("ws://" + window.location.host + "/ws");
const userid = document.getElementById("user-id");
const connectmem = $("#total-mem-container");
const chat_container = $("#chat-container");

socket.onopen = function (event) {
    getsession();
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
                connetecSessions.append(id+"접속중 <br>");
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



