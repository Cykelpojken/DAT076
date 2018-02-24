
var websocket = new WebSocket("ws:/213.89.13.195:8080/TicTacToe/gameServerEndpoint");

var playerId;
var currentPlayerMove = 0;
var gameId;
var boardElements;



window.addEventListener('load', function() {
  console.log('All assets are loaded')
  boardElements = document.getElementsByClassName("boardPiece");
  handleInit();
})

websocket.onmessage = function processMessage(message)
{
    console.log(message);
    var jsonData = JSON.parse(message.data);
    if(jsonData.message !== null)
    {
        var data = jsonData.message.split(":");
        if(data[0] === "init")
        {
            handleInit(data);
        }
        else if(data[0] === "queue")
        {
            handleQueue(data);
        }
        else if(data[0] === "start")
        {
            handleStart(data);
        }
        else if(data[0] === "move")
        {
            handleMove(data);
        }
        else if(data[0] === "update")
        {
            handleUpdate(data);
        }
        else if(data[0] === "finish")
        {
            handleFinish(data);
        }
        else if(data[0] === "conlost")
        {
            handleConnectionLoss(data);
        }
    }
}

function sendMessage(type, message)
{
    
    //console.log("sending");
    websocket.send(type + ":" + message);
}

function joinQueue()
{
    document.getElementById('preGame').style.display = 'none';
    document.getElementById('inGame').style.display = 'block';
    document.getElementById('gameBoard').style.display = 'block';
    for(var i = 0; i < boardElements.length; i++)
    {
        document.getElementById(boardElements[i].id).innerHTML = "";
    }
    sendMessage("queue", "")
}

function leaveQueue()
{
    document.getElementById('preGame').style.display = 'block';
    document.getElementById('inGame').style.display = 'none';
    document.getElementById('gameBoard').style.display = 'none';
    sendMessage("leave", "")
}

function makeMove(id)
{
    if(currentPlayerMove === 0)
    {
    }
    else if(playerId === currentPlayerMove)
    {
        boardPiece = document.getElementById(id);
        if(boardPiece.innerHTML === "")
        {
            sendMessage("move", id);
        }
        else
        {
            statusText.innerHTML = "Board piece already taken."
        }
    }
    else
    {
        statusText.innerHTML = "Not your turn."
    }
  
}

function handleQueue(data)
{
    playerIdText.innerHTML = "Looking for player.";
    statusText.innerHTML = "Waiting...";
}

function handleInit(data)
{
    playerIdText.innerHTML = "Press find game to search for an opponent.";
}

function handleStart(data)
{
    gameId = parseInt(data[1]);
    playerId = parseInt(data[2]);
    playerIdText.innerHTML = "You are player " + playerId;
    currentPlayerMove = 1;
    statusText.innerHTML = "Player " + currentPlayerMove + " is making their move.";
}

function handleMove(data)
{
    boardPiece = document.getElementById(data[1]);
    boardPiece.innerHTML = data[2];
    currentPlayerMove = parseInt(data[3]);
    statusText.innerHTML = "Player " + currentPlayerMove + " is making their move.";
}

function handleUpdate(data)
{
    var temp = data[1];
    var splitData = temp.split(";");
    currentPlayerMove = parseInt(splitData[0]);
    statusText.innerHTML = "Player " + currentPlayerMove + " is making their move.";
    var i;
    var bp;
    for(i = 1; i < splitData.length; i++)
    {
        bp = splitData[i].split(",");
        document.getElementById("board" + bp[0]).innerHTML = bp[1];
    }
}

function handleFinish(data)
{
    currentPlayerMove = 0;
    if(parseInt(data[1]) === playerId)
    {
        statusText.innerHTML =  "Congratulations! You are the winner!"
    }
    else if(parseInt(data[1]) === 3)
    {
        statusText.innerHTML =  "It's a draw."
    }
    else
    {
        statusText.innerHTML =  "You lost! Git good kid."
    }
}

function handleConnectionLoss(data)
{
    for(var i = 0; i < boardElements.length; i++)
    {
        document.getElementById(boardElements[i].id).innerHTML = "";
    }
    handleInit(data);
}

