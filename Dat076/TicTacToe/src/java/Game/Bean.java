/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author huoo
 */
@Named("bean")
@Stateless
public class Bean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String been(){
        System.out.println("asd123");
        Game game = new Game();
        return game.Test1();
    }
    
}
