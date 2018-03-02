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
import oldGame.Game;
import Servlets.AccontSettingsServlet;

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

    public List getList(){
        return em.createNamedQuery("Users.findAll").getResultList();
    }
    
    public Users getUser(String name){
        return (Users)em.createNamedQuery("Users.findByUsername").setParameter("username", name).getSingleResult();
    }
    
    public Users getUserById(Integer id){
        System.out.println("ingetuser");
        return (Users)em.createNamedQuery("Users.findById").setParameter("id", id).getSingleResult();
    }
    
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
    
    public String getUsername(String username){
        return getUser(username).getUsername();
    }
    public String getEmail(String username){
        return getUser(username).getEmail();
    }
    
    public void modifyAccount(int id){
         Users u = getUserById(id);
         System.out.println("DB username: " + u.getUsername());
         System.out.println("Input username: " + user.getUsername());
         System.out.println("DB email: " + u.getEmail());
         System.out.println("Input email: " + user.getEmail());
         System.out.println("DB password: " + u.getPassword());
         System.out.println("Input password: " + user.getPassword());
         if(!(u.getUsername().equals(user.getUsername())) && user.getUsername() != null && !user.getUsername().equals("")){
            u.setUsername(user.getUsername());
            try{
            FacesContext.getCurrentInstance().getExternalContext().dispatch("/accountsettings" + u.getUsername());
            }
            catch(Exception e){}
         }
         if(!(u.getEmail().equals(user.getEmail())) && user.getEmail() != null && !user.getEmail().equals(""))
            u.setEmail(user.getEmail());
         if(!(u.getPassword().equals(user.getPassword())) && user.getPassword() != null && !user.getPassword().equals(""))
            u.setPassword(user.getPassword());
                 

    }
    
    public int getRating(){return 0;}
    /*public void editUsername(){
        Users u = getUser(user.getUsername());
        u.setUsername("kalle");
        
    }*/
}
