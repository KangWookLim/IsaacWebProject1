const socket = new WebSocket("ws://" + window.location.host + "/chat/socket");

socket.onopen = function (event) {
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