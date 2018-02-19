package Game;


import javax.inject.Named;


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
public class Game {

    public Game(){
        
    }
    public String Test1(){
        System.out.println("asd123");
        return "Hej Arvid";
    }
}

