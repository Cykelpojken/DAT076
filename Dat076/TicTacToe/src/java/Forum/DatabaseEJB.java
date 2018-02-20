/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nils
 */
@Stateless
public class DatabaseEJB {
   @PersistenceContext
   EntityManager em;
   
   public List getList(){
       return em.createNamedQuery("Users.findAll").getResultList();
   }
   
}
