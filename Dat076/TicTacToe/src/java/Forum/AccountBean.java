/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import Forum.EntityClasses.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.registry.infomodel.User;

/**
 *
 * @author nils
 */
@Stateless
@Named
public class AccountBean {
    
    @PersistenceContext
    EntityManager em;
    
    @Inject Users user;

    public List getList(){
        return em.createNamedQuery("Users.findAll").getResultList();
    }
    public void h(){
        Users user = new Users();
        user.setId(5);
        user.setEmail("sad");
        user.setUsername("assdsd");
        user.setPassword("password");
        
    }
    public void create(){
      List<Users> l = getList();
      Users u2 = new Users(user.getId(), user.getUsername(), user.getPassword(), user.getEmail()); 
      for(Users u : l){
          if(u.getId() == u2.getId() || u.getUsername() == u2.getUsername() || u.getEmail() == u2.getEmail()){
           return;
          }
      }
      try{
      em.persist(u2);
      }
      catch(EntityExistsException e){
      e.printStackTrace();}
    }
}
