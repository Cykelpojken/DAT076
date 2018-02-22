/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Arvid
 */
public class Game {
    public static Session player1;
    public static Session player2;
    private HashMap<Integer, BoardPiece> board;
    private int playerTurn;

    public Game(Session player1, Session player2)
    {
        this.player1 = player1;
        this.player2 = player2;
        playerTurn = 1;
        board = new HashMap<>();
        for(int i = 1; i < 10; i++)
        {
            board.put(i, new BoardPiece(i, ""));
        }
    }

    public void changeBoard(int pos)
    {
        BoardPiece bp = board.get(pos);
        if(playerTurn == 1)
        {
            bp.setValue("X");
        }
        else
        {
            bp.setValue("O");
        }
        changePlayerTurn();
    }

    private void changePlayerTurn()
    {
        if(playerTurn == 1)
            playerTurn = 2;
        else
            playerTurn = 1;
    }

    public String getBoardState()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(playerTurn);
        for(int i = 1; i <= board.size(); i++)
        {
            sb.append(";" + i + "," + board.get(i).getValue() + "");
        }
        return sb.toString();
    }

    public int getPlayerTurn() {
        return playerTurn;
    }
}
