package Game;


import java.awt.Point;
import java.util.Observable;
import javax.inject.Named;
import java.util.Observer;
import java.util.Random;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author huoo
 */

@Named("game")
public class Game{
    private static final Logger LOG = Logger.getLogger(Game.class.getName());
    private int[] board;
    Random rand = new Random();
    public Game(){
       board = new int[9];
       for(int i = 0; i < board.length; i ++)
           board[i] = 0;
    }
    
    public void bupdate(int pos, int player) {
       LOG.info(Integer.toString(board[pos]));
       if(!gameMove(pos, player))
       {
          //Illigal move
       }
       else{
       //view.updateCell((char)(97 + pos), player);
       }
      
       board[AIshit()] = 2;
       
       if(checkBoard()){
           for(int i = 0; i < board.length; i++)
           {
               board[i] = 0;
           }
       }
    }
    private boolean gameMove(int pos, int player)
    {
        if(board[pos] == 0){
            board[pos] = player;
            return true;
        }
        return false;
    }
    private boolean checkBoard(){
        
        return (checkRows() || checkLines() 
                || (board[0] == board[4] && board[0] == board[8] && board[0] != 0) 
                || (board[2] == board[4] && board[2] == board[6] && board[2] != 0));
    }
    
    private boolean checkRows(){
    for(int i = 0; i <= 6; i += 3){
            if(board[i] == board[i + 1] && board[i] == board[i + 2] && board[i] != 0){
                return true;
            }
        }
            return false;
    }
    private boolean checkLines(){
        for(int i = 0; i < 3; i ++){
                if(board[i] == board[i + 3] && board[i] == board[i + 6] && board[i] != 0){
                    return true;
                }
        }
        return false;
    }
    
    private int AIshit(){
        int tmp = rand.nextInt(8);
        while(board[tmp] != 0)
        {
           tmp = rand.nextInt(8); 
        }
        return tmp;
    }
    
    public int getBoardId(int i){
        return board[i];
    }
}

