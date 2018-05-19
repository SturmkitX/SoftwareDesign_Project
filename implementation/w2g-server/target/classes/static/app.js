var stompClient = null;

//var vid = $("#myAudio");
// alert(vid == null);
//vid.onpause = function() {
//    $("#playStatus").html("Playing");
//    stompClient.send("/app/hello", {}, JSON.stringify({'name': 'audioPaused'}));
//};

function pauseThatShit() {
    $("#playStatus").html("Paused");
    stompClient.send("/app/hello", {}, JSON.stringify({'name': 'audioPaused'}));
    // $("#myAudio").play();
    // document.getElementById("myAudio").pause();
}

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    // $("#greetings").append("<tr><td>" + message + "</td></tr>");
    // alert(message);
    if(message.localeCompare("audioPaused") == 0) {
        document.getElementById("myAudio").pause();
    } else {
        $("#greetings").append("<tr><td>" + message + "</td></tr>");
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#forcePause" ).click(function() { pauseThatShit(); });
});