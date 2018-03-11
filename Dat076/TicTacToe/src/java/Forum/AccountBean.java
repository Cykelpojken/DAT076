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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.registry.infomodel.User;
import Servlets.AccontSettingsServlet;
import Servlets.LogoutServlet;

/**
 *
 * @author nils
 */
@Stateless
@Named
public class AccountBean {
    private static final Logger LOG = Logger.getLogger(AccountBean.class.getName());
    
    @PersistenceContext
    EntityManager em;
    
    @Inject Users user;

    /**
    * Returns a list of all users in the database
    * @return Returns a list of all users in the database
    */
    
    public List getList(){
        return em.createNamedQuery("Users.findAll").getResultList();
    }
    
    /**
    * Returns a user based on the given username
    * @param  name the username to search for
    * @return the user correlating to that username in the database
    */
    public Users getUser(String name){
        return (Users)em.createNamedQuery("Users.findByUsername").setParameter("username", name).getSingleResult();
    }
    
    /**
    * Returns a user based on the given id
    * @param  id the id to search for
    * @return the user correlating to that id in the database
    */
    public Users getUserById(Integer id){
        return (Users)em.createNamedQuery("Users.findById").setParameter("id", id).getSingleResult();
    }
    
    /**
    * Creates a new user and adds it to the database. 
    * A new user is not created if any of the unique attributes or the key is duplicate
    *
    */
    public void create(){ 
      List<Users> l = getList();
      Users u2 = new Users(user.getId(), user.getUsername(), user.getPassword(), user.getEmail()); 
      for(Users u : l){
          if(Objects.equals(u.getId(), u2.getId()) || u.getUsername().equals(u2.getUsername()) || u.getEmail().equals(u2.getEmail())){
           return;
          }
      }
      try{
      em.persist(u2);
      }
      catch(EntityExistsException e){
      e.printStackTrace();}
    }
    
     /**
    * Returns a username based on an input-value username
    * @param  username the username to search for
    * @return the username correlating to that username in the database
    */
    
    public String getUsername(String username){
        return getUser(username).getUsername();
    }
     /**
    * Returns an email based on an input-value username
    * @param  username the username to search for
    * @return the email correlating to that username in the database
    */
    
    public String getEmail(String username){
        return getUser(username).getEmail();
    }
    
     /**
    * Modifies the account, whos primary key is that of the parameter id
    * @param  id the id to search for in the database
    * @param  username the potentially modified username 
    * @param  password the potentially modified password 
    * @param  email the potentially modified email 
    */
    
    public void modifyAccount(int id, String username, String password, String email){
         Users u = getUserById(id);
         
         if(!(u.getEmail().equals(email)) && email != null && !email.equals(""))
            u.setEmail(email);
         
         if(!(u.getPassword().equals(password)) && password != null && !password.equals(""))
            u.setPassword(password);
         
         if(!(u.getUsername().equals(username))&& username != null && !username.equals(""))
            u.setUsername(username);

    }
    
      /**
    * Deletes an account
    * @param  username the username of the user to be deleted
    * @param  password the password of he user to be deleted
    * @return an integer, 0 if successfully removed, otherwise 1
    */
    
    public int deleteAccount(String username, String password){
        Users u = getUser(username);
        if(u.getPassword().equals(password)){
            em.remove(u);
            return 0;
        }
        return 1;
    }
    
}
