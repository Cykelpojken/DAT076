/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldGame;

import java.awt.Point;
import java.util.*;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.logging.Logger;
/**
 *
 * @author huoo
 */
@Named("controller")
@Stateless
public class Controller{

    private static final Logger LOG = Logger.getLogger(Controller.class.getName());
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Game game = new Game();
    public void update(int pos, int player){
        LOG.info("Hej");
        game.bupdate(pos, player);
    }
    public int getboardId(int i){
        return game.getBoardId(i);
    }

    public String getImage(int id)
    {
        LOG.info("asddds");
        id = getboardId(id);
        if(id == 1)
            return "o.png";
        else if(id == 2)
            return "x.png";
        else
           return "blank.png";
    }

}
