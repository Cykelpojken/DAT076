/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import Game.Game;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Arvid
 */

@ServerEndpoint("/gameServerEndpoint")
public class GameServerEndpoint {
    static Set<Session> lobby = Collections.synchronizedSet(new HashSet<Session>());
    static Set<Session> queue = Collections.synchronizedSet(new HashSet<Session>());
    private static int currentKey = 0;
    static Map<Integer, Game> games = Collections.synchronizedMap(new HashMap<>());
    
    //static Session player1 = null;
    //static Session player2 = null;
    
    
    @OnOpen
    public void handleOpen(Session userSession) throws IOException
    {
        lobby.add(userSession);
        userSession.getUserProperties().put("status", "lobby");
    }
    
    public void notifyPlayer(String type, String message, Session user) throws IOException
    {
        user.getBasicRemote().sendText(buildJsonData(type, message));
    }
    
    public int createKey()
    {
        currentKey++;
        return currentKey;
    }
    
    public void startGame(Session p1, Session p2) throws IOException
    {
        p1.getUserProperties().put("playerId", 1);
        p2.getUserProperties().put("playerId", 2);
        
        p1.getUserProperties().put("status", "playing");
        p2.getUserProperties().put("status", "playing");
        
        Game game = new Game(p1, p2);
        int key = createKey();
        p1.getUserProperties().put("gameId", key);
        p2.getUserProperties().put("gameId", key);
        
        games.put(key, game);
        
        queue.remove(p1);
        notifyPlayer("start", key + ":" + p1.getUserProperties().get("playerId"), p1);
        notifyPlayer("start", key + ":" + p2.getUserProperties().get("playerId"), p2);
    }
    
    @OnMessage
    public void handleMessage(String message, Session userSession) throws IOException
    {
        Game game = games.get(userSession.getUserProperties().get("gameId"));
        
        String[] data = message.split(":");
        if(data[0].equals("move"))
        {
            if(data[1].contains("board"))
            {
                int move = (int)userSession.getUserProperties().get("playerId");
                
                if(move == game.getPlayerTurn())
                {
                    game.changeBoard(Integer.parseInt(data[1].replace("board", "")));
                }
                
            }
            
            int result = game.checkBoard();
            notifyPlayer("update", game.getBoardState(), game.player1);
            notifyPlayer("update", game.getBoardState(), game.player2);
            if(result != 0)
            {
                game.player1.getUserProperties().put("status", "lobby");
                game.player2.getUserProperties().put("status", "lobby");
                notifyPlayer("finish", "" + result, game.player1);
                notifyPlayer("finish", "" + result, game.player2);
                
                games.remove(userSession.getUserProperties().get("gameId"));
            }
        }
        if(data[0].equals("queue"))
        {
            if(queue.isEmpty())
            {
                queue.add(userSession);
                userSession.getUserProperties().put("status", "queue");
                notifyPlayer("queue", "", userSession);
                
            }
            else if(!queue.contains(userSession))
            {
                Iterator<Session> iterator = queue.iterator();
                Session p1 = iterator.next();
                startGame(p1, userSession);
                queue.remove(p1);
            }
        }
        if(data[0].equals("leave"))
        {
            String status = (String)userSession.getUserProperties().get("status");
            if(status.equals("queue"))
            {
                userSession.getUserProperties().put("status", "lobby");
                queue.remove(userSession);
            }
            else if(status.equals("playing"))
            {
                handleLeaveGame(userSession);
            }
        }
        if(data[0].equals("message"))
        {
            Iterator<Session> iterator = lobby.iterator();
            while(iterator.hasNext()) 
                iterator.next().getBasicRemote().sendText(buildJsonData("message", data[1]));
        }
    }
    
    public void handleLeaveGame(Session userSession) throws IOException
    {
        int i = (int) userSession.getUserProperties().get("gameId");
        Game game = games.get(i);
        if((int)userSession.getUserProperties().get("playerId") == 1)
        {
            notifyPlayer("conlost", "", game.player2);
            game.player2.getUserProperties().put("status", "queue");
            queue.add(game.player2);
            notifyPlayer("queue", "", game.player2);
        }
        else if((int)userSession.getUserProperties().get("playerId") == 2)
        {
            notifyPlayer("conlost", "", game.player1);
            game.player1.getUserProperties().put("status", "queue");
            queue.add(game.player1);
            notifyPlayer("queue", "", game.player1);
        }
        games.remove(i);
    }
    
    public void print(String s)
    {
        System.out.println(s);
    }
    
    @OnClose
    public void handleClose(Session userSession) throws Exception
    {
        lobby.remove(userSession);
        String status = (String)userSession.getUserProperties().get("status");
        if(status.equals("queue"))
        {
            queue.remove(userSession);
        }
        else if(status.equals("playing"))
        {
            handleLeaveGame(userSession);
        }
    }

    private String buildJsonData(String type, String message) 
    {
        JsonObject jsonObject = Json.createObjectBuilder().add("message", type + ":" + message).build();
        StringWriter stringWriter = new StringWriter();
        try(JsonWriter jsonWriter = Json.createWriter(stringWriter))
        {
            jsonWriter.write(jsonObject);
        }
        return stringWriter.toString();
    }
}
