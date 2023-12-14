const socket = new WebSocket("ws://"+window.location.host+"/chat/socket");

socket.onopen = function (event) {
    console.log("연결 성공");
};
socket.onmessage = function (event) {
    showMessage(event.data);                 
};

function sendMessage() {
    const message = $("#messageInput").val();
    socket.send(message);
    console.log(message);
}

socket.onerror = function(error){
    console.log(error);
}

function showMessage(message) {
    $("#messageArea").append("<br>" + message);
}

$("#sendBtn").on("click", function () {
    sendMessage();
});

$("#message").keypress(function (e) {
    if (e.which === 13) {
        sendMessage();
    }
});