/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var websocket = new WebSocket("ws:/localhost:8080/WebSocketPrj/gameServerEndpoint");

var playerId;
var currentPlayerMove = 0;
var gameId;

var boardElements = document.getElementsByClassName("boardPiece");

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
    }
}

function sendMessage(type, message)
{
    //console.log("sending");
    websocket.send(type + ":" + message);
}

function makeMove(id)
{
    if(currentPlayerMove === 0)
    {
      console.log("asd");
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
    playerId = parseInt(data[1]);
    playerIdText.innerHTML = "You are player " + playerId + "\n";
    statusText.innerHTML = "Waiting...";
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
