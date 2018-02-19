/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Point;
import java.util.*;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.Observable;
/**
 *
 * @author huoo
 */
@Named("controller")
@Stateless
public class Controller extends Observable{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void Init(){
        Game game = new Game();
        addObserver(game);
    }
    public void Update(int pos, int palyer){
        Point point = new Point(pos, palyer);
        notifyObservers(point);
    }
    
}
