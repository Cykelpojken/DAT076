package Game;


import java.awt.Point;
import java.util.Observable;
import javax.inject.Named;
import java.util.Observer;
import java.util.Random;

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
public class Game implements Observer{
    int[] board;
    Random rand = new Random();
    public Game(){
       board = new int[9];
    }

    @Override
    public void update(Observable o, Object o1) {
       if(!gameMove((Point)o1))
       {
          //Illigal move
       }
       if(checkBoard()){
           //Vinst
       }
       
    }
    
    private boolean gameMove(Point p)
    {
        if(board[p.x] == 0){
            board[p.x] = p.y;
            
            return true;
        }
        return false;
    }
    private boolean checkBoard(){
        return (checkRows() || checkLines() 
                || (board[0] == board[4] && board[0] == board[8]) 
                || (board[2] == board[4] && board[2] == board[6]));
    }
    
    private boolean checkRows(){
    for(int i = 0; i < board.length ; i += 3){
            if(board[i] == board[i++] && board[i] == board[i+2]){
                return true;
            }
        }
            return false;
    }
    private boolean checkLines(){
        for(int i = 0; i < 3; i ++){
                if(board[i] == board[i + 3] && board[i] == board[i + 6]){
                    return true;
                }
        }
        return false;
    }
    
    private int AIshit(){
        int spots = 0;
        for(int i : board)
        { 
            if(i == 0)
                spots++;
        }
        int a = rand.nextInt(spots);
        for(int i = 0; i < board.length; i ++)
        {
            if(i == 0)
            {
                if(a == 0)
                    return i;
                a--;
            }
        }
        return 10;
    }
}

