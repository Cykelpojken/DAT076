/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;


/**
 *
 * @author nils
 */
@Named("AccBean")
@Stateless
public class AccountBean {
    @EJB
    private AccountRegistry areg;
    
    private User tmp = new User(); //Can't be found in dbtest-xhtml....
    
    public void add(){
        try{
            //areg.create(tmp); //Implement with abstract classes for entitymanagers
        }
        catch(Exception e){}
    }
}
