let socket;
let stompClient;

function conn(dest="/my-chat") {
    socket = new SockJS(dest);
    stompClient = Stomp.over(socket);


    stompClient.connect(
        {}, 
        message => console.log("Connected", message), 
        error => console.error("Couldn't connect:", error)
    );
}

function subscribe(dest="/topic/messages") {
    stompClient.subscribe(
        dest,
        message => console.log("received a message:", message) 
    );
}

function sendMessage(message, usr="usir", dest="/app/chat.sendMessage") {
    stompClient.send(dest, {}, JSON.stringify({text: message, senderUsername: usr}));
}


