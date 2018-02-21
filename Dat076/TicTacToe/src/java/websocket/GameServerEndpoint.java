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
import game.Game;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Arvid
 */

@ServerEndpoint("/gameServerEndpoint")
public class GameServerEndpoint {
    static Set<Session> queue = Collections.synchronizedSet(new HashSet<Session>());
    
    static Map<Integer, Game> games = Collections.synchronizedMap(new HashMap<>());
    
    //static Session player1 = null;
    //static Session player2 = null;
    
    
    @OnOpen
    public void handleOpen(Session userSession) throws IOException
    {
        print("Hello world");
        if(queue.isEmpty())
        {
            queue.add(userSession);
            notifyPlayer("queue", "", userSession);
        }
        else
        {
            Iterator<Session> iterator = queue.iterator();
            Session p1 = iterator.next();
            startGame(p1, userSession);
        }
    }
    
    public void notifyPlayer(String type, String message, Session user) throws IOException
    {
        user.getBasicRemote().sendText(buildJsonData(type, message));
    }
    
    public int createKey()
    {
        return 1;
    }
    
    public void startGame(Session p1, Session p2) throws IOException
    {
        p1.getUserProperties().put("playerId", "1");
        p2.getUserProperties().put("playerId", "2");
        
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
                int move = Integer.parseInt((String)userSession.getUserProperties().get("playerId"));
                
                if(move == game.getPlayerTurn())
                {
                    game.changeBoard(Integer.parseInt(data[1].replace("board", "")));
                }
            }
            notifyPlayer("update", game.getBoardState(), game.player1);
            notifyPlayer("update", game.getBoardState(), game.player2);
        }
    }
    
    public void print(String s)
    {
        System.out.println(s);
    }
    
    @OnClose
    public void handleClose(Session userSession) throws Exception
    {
        if(!queue.remove(userSession))
        {
            
            String s = (String) userSession.getUserProperties().get("gameId");
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
