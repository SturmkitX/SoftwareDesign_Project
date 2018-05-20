var stompClient = null;
var isRemote = false;
var roomId = null;


function pauseAudio() {
    $("#playStatus").html("Paused");
    if(isRemote == false) {
        stompClient.send("/app/hello/" + roomId, {}, JSON.stringify({'head': 'audioPaused', 'time': myAudio.currentTime, 'source': null}));
    } else {
        isRemote = false;
    }
}

function playAudio() {
    $("#playStatus").html("Playing");

    if(isRemote == false) {
        stompClient.send("/app/hello/" + roomId, {}, JSON.stringify({'head': 'audioPlayed', 'time': myAudio.currentTime, 'source': null}));
    } else {
        isRemote = false;
    }

}

function seekAudio() {
    $("#playStatus").html("Seeked");
    if(isRemote == false) {
        stompClient.send("/app/hello/" + roomId, {}, JSON.stringify({'head': 'audioSeeked', 'time': myAudio.currentTime, 'source': null}));
    } else {
        isRemote = false;
    }
}

function changeAudio(newPath) {
    $("#playStatus").html("Changed");
    if(isRemote == false) {
        stompClient.send("/app/hello/" + roomId, {}, JSON.stringify({'head': 'audioChanged', 'time': 0.0, 'source': newPath}));
    } else {
        isRemote = false;
    }
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
    // alert(roomId);
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings/' + roomId, function (greeting) {
            isRemote = true;
            showGreeting(JSON.parse(greeting.body));
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

function showGreeting(message) {
    var myAudio = document.getElementById("myAudio");
    if(message.head.localeCompare("audioPaused") == 0) {
        myAudio.currentTime = message.time;
        myAudio.pause();
    } else if(message.head.localeCompare("audioPlayed") == 0) {
        myAudio.currentTime = message.time;
        myAudio.play();
    } else if(message.head.localeCompare("audioSeeked") == 0) {
        myAudio.currentTime = message.time;
    } else if(message.head.localeCompare("audioChanged") == 0) {
        myAudio.src = message.source;
        myAudio.currentTime = message.time;
        myAudio.play();
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
});